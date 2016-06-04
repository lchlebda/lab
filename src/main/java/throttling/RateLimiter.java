package throttling;

import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private final int rate;
    private final TimeUnit timeUnit;
    private final Semaphore semaphore;
    private volatile boolean started;
    private long time;

    public RateLimiter(int rate, TimeUnit timeUnit) {
        this.rate = rate;
        this.timeUnit = timeUnit;
        semaphore = new Semaphore(rate);
        time = LocalTime.now().;
    }

    @SneakyThrows
    public void acquire() {
        if (!started) {
            started = true;
            time
        }
        semaphore.acquire();
    }
}
