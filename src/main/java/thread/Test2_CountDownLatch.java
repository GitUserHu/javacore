package thread;

import java.util.concurrent.CountDownLatch;

public class Test2_CountDownLatch {

	public static void main(String[] args) {
		ThreadWithCount threadWithCount = new ThreadWithCount();
		Thread tA = new MyThread(threadWithCount.counter, "线程 A ");
		threadWithCount.start();
		
		tA.start();
		
	}
	
	
}

class ThreadWithCount extends Thread{
	public  CountDownLatch counter = new CountDownLatch(1);
	@Override
	public void run() {
		try {
			System.out.println("线程ThreadWithCount Wait()");
			counter.await();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行线程 ThreadWithCount");
	}
}


