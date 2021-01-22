package random;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 *    用于多线程的随机数生成器 ThreadLocalRandom
 * @author JiaBing
 * @date 2018-12-27
 */
public class Demo_ThreadLocalRandom {

	public static void main(String[] args) {
		// ThreadLocalRandom random = ThreadLocalRandom.current();
		 int count = 10000;
			CountDownLatch countDownLatch = new CountDownLatch(count);
			ExecutorService pool = Executors.newFixedThreadPool(count);
			long start = System.currentTimeMillis();
			for(int i = 0; i< count; i++) {
				pool.execute(()->{
					System.out.println(ThreadLocalRandom.current().nextInt(5));
					countDownLatch.countDown();
				});
			}
			
			try {
				// TODO 等待线程池中count数量的线程执行完之后，开始main线程，然后统计耗时。
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("用时:"+(System.currentTimeMillis()-start)+" ms");
			pool.shutdown();
	}

}
