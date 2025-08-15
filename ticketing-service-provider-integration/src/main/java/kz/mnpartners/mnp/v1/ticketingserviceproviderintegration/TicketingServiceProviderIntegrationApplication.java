package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TicketingServiceProviderIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingServiceProviderIntegrationApplication.class, args);
    }

}
