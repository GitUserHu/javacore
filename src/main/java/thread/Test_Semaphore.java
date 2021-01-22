package thread;

import java.util.concurrent.Semaphore;

/** 
   *    模拟这样一个场景： 5个可用的提款机，有8个人要提款。（每个提款机只能一个人同时提款，提款完成后，下一个人才能使用）
 *  tryAcquire()和acquire()区别：
 *    1.tryAcquire 方法(非阻塞方法)：尝试获取一个Permit，如果没有可用的permit， 返回false.有则获取一个permit并且返回true(permit总数-1)
 *    2.acquire 方法(阻塞方法)： 获取一个permit，如果没有一个一个可用的permit，则阻塞，直到有可用的permit，有则直接获取(permit总数-1)。
 *  
 * @author JiaBing
 * @date 2018/12/07
 */
public class Test_Semaphore {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);//5个可用的提款机
		int pNumber = 8;
		
		for(int i = 1; i <= pNumber; i++ ) {
			new DrawMoney(semaphore, "Person " + i).start();;
		}

	}
	

}

class DrawMoney extends Thread{
	private Semaphore semaphore;
	public DrawMoney(Semaphore semaphore, String name) {
		super(name);
		this.semaphore = semaphore;
	}
	@Override
	public void run() {
		
		try {
			// TODO 尝试获取一个（使用）许可，如果没有可用的机器，就阻塞，直到有一个可用的机器
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " 正在使用提款机。。。");
			// TODO 取钱操作
			Thread.sleep(3000);

			// TODO 释放提款机，让其他线程使用      （挪坑）
			
			System.err.println(Thread.currentThread().getName() + " 结束使用提款机。。。000");
			semaphore.release();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
