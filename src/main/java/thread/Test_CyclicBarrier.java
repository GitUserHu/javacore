package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 *    当三个线程都完成了 各自线程中的Read操作后，才能往后面执行。
 * 与 @see Test3_CountDownLatch,很类似。 <p>But,CycliBarrier能够重用, CountDownLatch
 * 
 * CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 *   
 * @author JiaBing
 * @data 2018/12/07
 *
 */
public class Test_CyclicBarrier {

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(3);
		int n =0;
		for(int i = 0 ; i< 3 ; i++) {
			n++;
			new WriteThread(barrier, "Thread "+n).start();;
		}
		//CycliBarrier 重用
		//barrier.wait();
		
		Thread.sleep(5000);
		
		
		System.out.println("CycliBarrier 重用。。。。。。");
		for(int i = 0 ; i< 3 ; i++) {
			n++;
			new WriteThread(barrier, "Thread "+n).start();;
		}

	}

}

class WriteThread extends Thread{
	private CyclicBarrier barrier;
	
	public WriteThread(CyclicBarrier barrier, String name){
		super(name);
		this.barrier = barrier;
	}
	
	@Override
	public void run() {
		System.err.println(Thread.currentThread().getName()+" 开始进行写操作...");
		//sleep(3000)代替写操作
		try {
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+ " 完成了写操作，等待其他线程完成写操作...");
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("所有线程都完成了写操作， "+Thread.currentThread().getName() +" 开始后面的操作" );

	}
}