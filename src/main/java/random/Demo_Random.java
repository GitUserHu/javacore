package random;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *    随机数生成器Random
 * @author JiaBing
 * @date 2018-12-27
 */
public class Demo_Random {

	public static void main(String[] args) {
		// TODO 创建一个默认种子的随机数生成器
		Random random = new Random();
		int count = 10000;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		ExecutorService pool = Executors.newFixedThreadPool(count);
		long start = System.currentTimeMillis();
		for(int i = 0; i< count; i++) {
			pool.execute(()->{
				System.out.println(random.nextInt(5));
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("用时:"+(System.currentTimeMillis()-start)+" ms");
		pool.shutdown();
	}

}
