package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import feign.RetryableException;

import java.util.Date;

public class RetryableStatusErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if (status == 409) {
            return new RetryableException(
                status,
                "Retryable HTTP " + status,
                response.request().httpMethod(),
                new Date(),
                response.request()
            );
        }
        return defaultDecoder.decode(methodKey, response);
    }
}
