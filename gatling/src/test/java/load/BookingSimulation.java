package load;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class BookingSimulation extends Simulation {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8081");

    private static final int TOTAL_USERS = Integer.parseInt(System.getProperty("totalUsers", "100"));
    private static final int RAMP_MINUTES = Integer.parseInt(System.getProperty("rampMinutes", "10"));

    private static final double PCT_SUCCESS = Double.parseDouble(System.getProperty("pctSuccess", "30"));
    private static final double PCT_CANCEL = Double.parseDouble(System.getProperty("pctCancel", "20"));
    private static final double PCT_CONCUR = Double.parseDouble(System.getProperty("pctConcur", "10"));
    private static final double PCT_FAILRB = Double.parseDouble(System.getProperty("pctFailRb", "20"));
    private static final double PCT_PAGES = Double.parseDouble(System.getProperty("pctPages", "10"));

    private static final int SEATS_PAGE_SIZE = Integer.parseInt(System.getProperty("seatsPageSize", "20"));
    private static final int MAX_PAGES = Integer.parseInt(System.getProperty("maxPages", "200"));

    private final FeederBuilder<String> users = csv("data/users.csv").circular();

    private final FeederBuilder<Object> eventsFeeder = jsonFile("data/events.json").random();


    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl(BASE_URL)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json; charset=UTF-8")
            .userAgentHeader("Gatling/BookingSimulation");


    private static Session withBasicAuth(Session s) {
        String email = s.getString("email");
        String pwd = s.getString("password_hash");
        String basic = "Basic " + Base64.getEncoder().encodeToString((email + ":" + pwd).getBytes(StandardCharsets.UTF_8));
        return s.set("authHeader", basic);
    }


    private static Session prepareEventQuery(Session s) {
        String title = String.valueOf(s.getString("title"));
        String dt = String.valueOf(s.getString("datetime_start"));

        String eventDate = (dt != null && dt.length() >= 10) ? dt.substring(0, 10) : dt;

        String querySub = "конц";
        if (title != null && !title.isBlank()) {
            String[] words = title.trim().split("\\s+");
            String word = words[ThreadLocalRandom.current().nextInt(words.length)];
            word = word.replaceAll("[^\\p{L}\\p{N}]+", "");
            if (!word.isEmpty()) {
                int min = 3, max = Math.min(6, word.length());
                int len = ThreadLocalRandom.current().nextInt(min, max + 1);
                int startMax = Math.max(0, word.length() - len);
                int start = (startMax > 0) ? ThreadLocalRandom.current().nextInt(startMax + 1) : 0;
                querySub = word.substring(start, Math.min(word.length(), start + len));
            }
        }
        return s.set("eventDate", eventDate).set("querySub", querySub);
    }

    private static Session pickRandomSeat(Session s) {
        List<Object> seatIds = s.getList("seatIds");
        if (seatIds.isEmpty()) return s.markAsFailed();
        int idx = ThreadLocalRandom.current().nextInt(seatIds.size());
        return s.set("seatId", String.valueOf(seatIds.get(idx)));
    }

    private static Session ensureOrderId(Session s) {
        if (!s.contains("orderId")) {
            if (s.contains("bookingId")) return s.set("orderId", s.getString("bookingId"));
            return s.set("orderId", String.valueOf(ThreadLocalRandom.current().nextLong(1, 1_000_000_000L)));
        }
        return s;
    }


    private static final ChainBuilder tinyPause = pause(Duration.ofMillis(200), Duration.ofMillis(800));

    // =========================

    private final ChainBuilder getEventAndCreateBooking =
            feed(eventsFeeder)
                    .exec(BookingSimulation::prepareEventQuery)
                    .exec(
                            http("GET /api/events")
                                    .get("/api/events")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("query", "#{querySub}")
                                    .queryParam("date", "#{eventDate}")
                                    .queryParam("page", "1")
                                    .queryParam("pageSize", "20")
                                    .check(status().is(200))
                                    .check(jsonPath("$[0].id").saveAs("eventId"))
                    )
                    .exec(tinyPause)
                    .exec(
                            http("POST /api/bookings")
                                    .post("/api/bookings")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"event_id\": #{eventId}}"))
                                    .check(status().is(201))
                                    .check(jsonPath("$.id").saveAs("bookingId"))
                    );

    private final ChainBuilder pickSeatAndInitiate =
            exec(
                    http("GET /api/seats (FREE)")
                            .get("/api/seats")
                            .header("Authorization", "#{authHeader}")
                            .queryParam("event_id", "#{eventId}")
                            .queryParam("page", "1")
                            .queryParam("pageSize", String.valueOf(SEATS_PAGE_SIZE))
                            .queryParam("status", "FREE")
                            .check(status().is(200))
                            .check(jsonPath("$[?(@.status=='FREE')].id").findAll().saveAs("seatIds"))
            )
                    .exec(BookingSimulation::pickRandomSeat)
                    .exec(
                            http("PATCH /api/seats/select")
                                    .patch("/api/seats/select")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"booking_id\": #{bookingId}, \"seat_id\": #{seatId}}"))
                                    .check(status().in(200, 419))
                    )
                    .exec(
                            http("PATCH /api/bookings/initiatePayment")
                                    .patch("/api/bookings/initiatePayment")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"booking_id\": #{bookingId}}"))
                                    .check(status().is(200))
                    )
                    .exec(BookingSimulation::ensureOrderId)
                    .exec(tinyPause)
                    .exec(
                            http("GET /api/payments/success")
                                    .get("/api/payments/success")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("orderId", "#{orderId}")
                                    .check(status().is(200))
                    );

    // =========================


    private final ScenarioBuilder scnFullSuccess =
            scenario("Full Successful Booking")
                    .feed(users).exec(BookingSimulation::withBasicAuth)
                    .exec(getEventAndCreateBooking).exec(tinyPause)
                    .exec(pickSeatAndInitiate);

    // =========================
    private final ChainBuilder cancelImmediately =
            exec(
                    http("PATCH /api/bookings/cancel (immediately)")
                            .patch("/api/bookings/cancel")
                            .header("Authorization", "#{authHeader}")
                            .body(StringBody("{\"booking_id\": #{bookingId}}"))
                            .check(status().is(200))
            );

    private final ChainBuilder cancelAfterSeat =
            exec(
                    http("PATCH /api/bookings/cancel (after seat)")
                            .patch("/api/bookings/cancel")
                            .header("Authorization", "#{authHeader}")
                            .body(StringBody("{\"booking_id\": #{bookingId}}"))
                            .check(status().is(200))
            )
                    .doIf(session -> session.contains("seatId")).then(
                            exec(
                                    http("PATCH /api/seats/release (best-effort)")
                                            .patch("/api/seats/release")
                                            .header("Authorization", "#{authHeader}")
                                            .body(StringBody("{\"seat_id\": #{seatId}}"))
                                            .check(status().in(200, 419))
                            )
                    );

    private final ChainBuilder cancelAfterInitiate =
            exec(
                    http("PATCH /api/bookings/cancel (after initiate)")
                            .patch("/api/bookings/cancel")
                            .header("Authorization", "#{authHeader}")
                            .body(StringBody("{\"booking_id\": #{bookingId}}"))
                            .check(status().is(200))
            );

    private final ScenarioBuilder scnCancelMultiStage =
            scenario("Cancel at various stages")
                    .feed(users).exec(BookingSimulation::withBasicAuth)
                    .exec(getEventAndCreateBooking).exec(tinyPause)
                    .randomSwitch().on(
                            Choice.withWeight(50, exec(cancelImmediately)),
                            Choice.withWeight(30,
                                    exec(
                                            http("GET /api/seats (before cancel)")
                                                    .get("/api/seats")
                                                    .header("Authorization", "#{authHeader}")
                                                    .queryParam("event_id", "#{eventId}")
                                                    .queryParam("status", "FREE")
                                                    .check(status().is(200))
                                                    .check(jsonPath("$[?(@.status=='FREE')].id").findAll().saveAs("seatIds"))
                                    ).exec(BookingSimulation::pickRandomSeat)
                                            .exec(
                                                    http("PATCH /api/seats/select (before cancel)")
                                                            .patch("/api/seats/select")
                                                            .header("Authorization", "#{authHeader}")
                                                            .body(StringBody("{\"booking_id\": #{bookingId}, \"seat_id\": #{seatId}}"))
                                                            .check(status().in(200, 419))
                                            )
                                            .exec(cancelAfterSeat)
                            ),
                            Choice.withWeight(20,
                                    exec(
                                            http("PATCH /api/bookings/initiatePayment (before cancel)")
                                                    .patch("/api/bookings/initiatePayment")
                                                    .header("Authorization", "#{authHeader}")
                                                    .body(StringBody("{\"booking_id\": #{bookingId}}"))
                                                    .check(status().is(200))
                                    ).exec(cancelAfterInitiate)
                            )
                    );

    // =========================
    private final ScenarioBuilder scnConcurrentSeat =
            scenario("Concurrent booking of ONE seat")
                    .feed(users).exec(BookingSimulation::withBasicAuth)
                    .exec(
                            http("POST /api/bookings (concurrent)")
                                    .post("/api/bookings")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"event_id\": 1}"))
                                    .check(status().is(201))
                                    .check(jsonPath("$.id").saveAs("bookingId"))
                    )
                    .exec(
                            http("PATCH /api/seats/select (concurrent ONE)")
                                    .patch("/api/seats/select")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"booking_id\": #{bookingId}, \"seat_id\":1}")) //TODO:choose one seat
                                    .check(status().in(200, 419))
                    );

    // =========================
    private final ScenarioBuilder scnPaymentFailRollback =
            scenario("Payment FAIL → rollback")
                    .feed(users).exec(BookingSimulation::withBasicAuth)
                    .exec(getEventAndCreateBooking).exec(tinyPause)
                    .exec(
                            http("GET /api/seats (for fail)")
                                    .get("/api/seats")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("event_id", "#{eventId}")
                                    .queryParam("status", "FREE")
                                    .check(status().is(200))
                                    .check(jsonPath("$[?(@.status=='FREE')].id").findAll().saveAs("seatIds"))
                    )
                    .exec(BookingSimulation::pickRandomSeat)
                    .exec(
                            http("PATCH /api/seats/select (before fail)")
                                    .patch("/api/seats/select")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"booking_id\": #{bookingId}, \"seat_id\": #{seatId}}"))
                                    .check(status().in(200, 419))
                    )
                    .exec(
                            http("PATCH /api/bookings/initiatePayment")
                                    .patch("/api/bookings/initiatePayment")
                                    .header("Authorization", "#{authHeader}")
                                    .body(StringBody("{\"booking_id\": #{bookingId}}"))
                                    .check(status().is(200))
                    )
                    .exec(BookingSimulation::ensureOrderId)
                    .exec(tinyPause)
                    .exec(
                            http("GET /api/payments/fail")
                                    .get("/api/payments/fail")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("orderId", "#{orderId}")
                                    .check(status().is(200))
                    )
                    .exec(
                            http("GET /api/seats (after fail)")
                                    .get("/api/seats")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("event_id", "#{eventId}")
                                    .queryParam("status", "FREE")
                                    .check(status().is(200))
                    );

    // =========================
    private final ScenarioBuilder scnSeatsPagination =
            scenario("Seats pagination (large)")
                    .feed(users).exec(BookingSimulation::withBasicAuth)
                    .feed(eventsFeeder)
                    .exec(BookingSimulation::prepareEventQuery)
                    .exec(
                            http("GET /api/events (for pagination)")
                                    .get("/api/events")
                                    .header("Authorization", "#{authHeader}")
                                    .queryParam("query", "#{querySub}")
                                    .queryParam("date", "#{eventDate}")
                                    .queryParam("page", "1")
                                    .queryParam("pageSize", "20")
                                    .check(status().is(200))
                                    .check(jsonPath("$[0].id").saveAs("eventId"))
                    )
                    .exec(session -> session.set("page", 1).set("hasMore", true))
                    .asLongAs(s -> s.getBoolean("hasMore") && s.getInt("page") <= MAX_PAGES).on(
                            exec(
                                    http("GET /api/seats page=#{page}")
                                            .get("/api/seats")
                                            .header("Authorization", "#{authHeader}")
                                            .queryParam("event_id", "#{eventId}")
                                            .queryParam("page", "#{page}")
                                            .queryParam("pageSize", String.valueOf(SEATS_PAGE_SIZE))
                                            .check(status().is(200))
                                            .check(jsonPath("$[*].id").findAll().saveAs("pageSeatIds"))
                            )
                                    .exec(s -> {
                                        List<Object> ids = s.getList("pageSeatIds");
                                        boolean has = !ids.isEmpty();
                                        int next = s.getInt("page") + 1;
                                        return s.set("hasMore", has).set("page", next);
                                    })
                    );

    // =========================

    {
        double sum = PCT_SUCCESS + PCT_CANCEL + PCT_CONCUR + PCT_FAILRB + PCT_PAGES ;
        if (sum <= 0) sum = 1;

        int usersSuccess = (int) Math.round(TOTAL_USERS * (PCT_SUCCESS / sum));
        int usersCancel = (int) Math.round(TOTAL_USERS * (PCT_CANCEL / sum));
        int usersConcur = (int) Math.round(TOTAL_USERS * (PCT_CONCUR / sum));
        int usersFailRb = (int) Math.round(TOTAL_USERS * (PCT_FAILRB / sum));
        int usersPages = (int) Math.round(TOTAL_USERS * (PCT_PAGES / sum));

        setUp(
                scnFullSuccess.injectOpen(rampUsers(usersSuccess).during(Duration.ofMinutes(RAMP_MINUTES))),
                scnCancelMultiStage.injectOpen(rampUsers(usersCancel).during(Duration.ofMinutes(RAMP_MINUTES))),
                scnConcurrentSeat.injectOpen(atOnceUsers(Math.max(1, usersConcur))),
                scnPaymentFailRollback.injectOpen(rampUsers(usersFailRb).during(Duration.ofMinutes(RAMP_MINUTES))),
                scnSeatsPagination.injectOpen(rampUsers(usersPages).during(Duration.ofMinutes(RAMP_MINUTES)))
        )
                .protocols(httpProtocol)
                .assertions(
                        global().successfulRequests().percent().gt(95.0),
                        forAll().responseTime().percentile3().lt(2000)
                );
    }
}
