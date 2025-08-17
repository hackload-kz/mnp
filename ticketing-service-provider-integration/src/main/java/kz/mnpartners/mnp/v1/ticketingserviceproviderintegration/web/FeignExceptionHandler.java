package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.web;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(FeignExceptionHandler.class);

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeign(FeignException ex) {
        int status = ex.status();

        if (status >= 500 && status < 600) {
            String body = null;
            try {
                body = ex.contentUTF8();
            } catch (Exception ignored) { }

            log.error("Feign 5xx: status={}, url={}, body={}",
                status,
                ex.request() != null ? ex.request().url() : "n/a",
                body, ex);

            return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("Внешний сервис недоступен. Попробуйте позже.");
        }

        if (status == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Данные не найдены во внешнем сервисе");
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
            .body("Ошибка при вызове внешнего сервиса");
    }
}