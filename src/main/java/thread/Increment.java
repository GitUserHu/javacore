/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/3 23:05
 */
package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Increment {
    @Test
    void test() throws InterruptedException {
        int n = 1000000;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                count.incrementAndGet();
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("Count = " + count.get());
    }
}
