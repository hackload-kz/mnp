package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.config.feign;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignRetryConfig {
    @Bean
    public Retryer feignRetryer() {
        return new FixedWaitRetryer(1000, 3);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetryableStatusErrorDecoder();
    }
}
