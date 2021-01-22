package thread;

import java.util.concurrent.CountDownLatch;
/**
 * @see CountDownLatch 用于阻塞当前线程，直到count的值变为0，并且count的值不能再次修改
 * @author JiaBing
 * @Date 2018/12/07
 *
 */
public class Test_CountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(2);
		Thread threadA = new MyThread(counter, "threadA");
		Thread threadB = new MyThread(counter, "threadB");
		threadA.start();
		threadB.start();

		counter.await();//主线程一直等待，直到count变为0（每一次counter.countDown()都会使得count-1）
		
		System.out.println("执行完毕");
	    
	}
	
	

}
class MyThread extends Thread{
	private CountDownLatch counter;
	
	public MyThread(CountDownLatch counter, String name) {
		super(name);
		this.counter=counter;
		
	}
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName()+" 正在执行...");
		//TODO 这里执行业务逻辑， 用Thread.sleep(3000)来代替业务逻辑的费时操作
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" 执行完毕...");
		counter.countDown();
	}
	
	
}
