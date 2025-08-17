package kz.mnpartners.mnp.v1.ticketingserviceproviderintegration.config.feign;

import feign.Retryer;
import feign.RetryableException;

public class FixedWaitRetryer implements Retryer {
    private final int maxAttempts;
    private final long backoffMs;
    private int attempt = 1;

    public FixedWaitRetryer(long backoffMs, int maxAttempts) {
        this.backoffMs = backoffMs;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(backoffMs);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new FixedWaitRetryer(backoffMs, maxAttempts);
    }
}
