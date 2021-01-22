package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JiaBing
 * @date 2019-03-20 22:52:45 
 * Usage of CyclicBarrier
 *    便于理解：请参考CyclicBarrier分析图：{@link /sources/pictures/barrier.png}
 */
public class Demo_FurtherTestOnCyclicBarrier {
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException{
		ExecutorService pool = Executors.newCachedThreadPool();
		// 1. 创建一个栅栏，用于拦截21个线程，并且设置最后一个到达该栅栏的线程运行task,这里的task用的lambda表达式
		// MORE: CyclicBarrier源码内部，需要21个线程调用await()(这里的21指的是传入到构造函数中的参数，并且这里所指的线程调用await是指的是在线程内部调用barrier.await())
		// ? BUT: 为什么这里设置21，而不是20？ NOTE:线程池启动了10个producer，10个consumer，按理说只需要设置20，就能让这20个线程同时执行到barrier.await()时（即执行到栅栏处）才接着往下面执行
		//  ? NoTE: 但是在Main线程中也调用了barrier.await()（该await用于让main线程不继续往下执行），因此需要多一个,故设置为21个。
		// NOTE:CyclicBarrier是可以重复使用的：什么叫可以重复使用？ eg:当21个线程到达第一个栅栏处，栅栏会放开，让这21个线程通行（继续执行），接着可以继续使用这个栅栏来拦截线程，拦截21个线程。
		CyclicBarrier barrier = new CyclicBarrier(21,()-> {System.out.println(Thread.currentThread().getName()+" : this thread is the last to arrived...");});
		
			for (int i = 0; i < 10; i++) {
				pool.execute(()->{ 
					try {
						barrier.await(); // 2. 线程执行到这里时，就会被栅栏拦下来，等到具体数量（这里指的是21）的线程到达这里时，所有线程一起执行。
						System.out.println(Thread.currentThread().getName()+" ：producer is running");
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
				pool.execute(()->{ 
					try {
						barrier.await(); // 2. 同理：线程执行到这里时，就会被拦截下来
						System.out.println(Thread.currentThread().getName()+" ：consumer is running");
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					});
			}
		
			System.out.println("waiting for all producers and consumers start...");
			barrier.await();
			System.out.println("All are running...");
			barrier.await();
			System.out.println("All finished...");
			pool.shutdown();

		

	}
	

}
