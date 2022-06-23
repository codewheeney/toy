package org.example;

import static java.util.concurrent.Executors.newScheduledThreadPool;

import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolTest tpt = new ThreadPoolTest(10);

        tpt.waitForever();
    }

    private final ScheduledExecutorService scheduledExecutorService;

    public ThreadPoolTest(int numThreads) {
        this.scheduledExecutorService = newScheduledThreadPool(numThreads);
        this.scheduledExecutorService.scheduleAtFixedRate(() -> {
            int val = ThreadLocalRandom.current().nextInt(0, 100);
            System.out.printf("%s: %d: val = %d\n", Instant.now(), Thread.currentThread().getId(), val);
            if (val == 5) {
                ciao();
            }

        }, 1, 1, TimeUnit.SECONDS);
    }

    public void waitForever() {
        boolean stop = false;
        while (stop) {
            if (scheduledExecutorService.isShutdown()) {
                System.out.println("excecutor shutdown");
                stop = true;
            }

            if (scheduledExecutorService.isTerminated()) {
                System.out.println("exceutor terminated");
                stop = true;
            }
        }
    }

    private void ciao() {
        // https://stackoverflow.com/questions/1660834/unhandled-exceptions-with-java-scheduled-executors
        // This will cause all processing to stop
        System.out.println("Ciao!");
        throw new RuntimeException("Goodnight sweet prince");
    }
}
