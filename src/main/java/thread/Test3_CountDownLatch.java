package thread;
import java.util.concurrent.CountDownLatch;

/**
 *   当三个线程都完成了 各自线程中的Read操作后，才能往后面执行
 *   
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；与Thread.join()相似
 * @author JiaBing
 * @date 2018/12/07
 */
public class Test3_CountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		int n = 1;
		CountDownLatch counter = new CountDownLatch(3);
		for(int  i = 0 ;i < 3 ;i++,n++) new ReadThread(counter, "Thread " + n).start();
		ReadThread.sleep(5000);
		
		System.out.println("CountDownLatch 重用？？？");
		
		for(int  i = 0 ;i < 3 ;i++,n++) new ReadThread(counter, "Thread " + n).start();
		
		//Output：打印结果 
		/**
		 * <p> 标识的输出的位置，重用CountDownLatch时，4 5 6 这三个线程并没有等到全部完成读操作才往后执行。
		 * <p> 因此 CountDownLatch不能重用
		 */
		/**Thread 1 开始进行读操作...
		* Thread 2 开始进行读操作...
		* Thread 3 开始进行读操作...
		* Thread 2 完成了读操作，等待其他线程完成读操作...
		* Thread 1 完成了读操作，等待其他线程完成读操作...
		* Thread 3 完成了读操作，等待其他线程完成读操作...
		* 所有线程都完成了写操作， Thread 3 开始后面的操作
		* 所有线程都完成了写操作， Thread 2 开始后面的操作
		* 所有线程都完成了写操作， Thread 1 开始后面的操作
		* CountDownLatch 重用？？？
		* Thread 4 开始进行读操作...
		* Thread 6 开始进行读操作...
		* Thread 5 开始进行读操作...
		* <p>Thread 6 完成了读操作，等待其他线程完成读操作...
		* <p>Thread 4 完成了读操作，等待其他线程完成读操作...
		* <p>所有线程都完成了写操作， Thread 4 开始后面的操作
		* <p>Thread 5 完成了读操作，等待其他线程完成读操作...
		* <p>所有线程都完成了写操作， Thread 5 开始后面的操作
		* <p>所有线程都完成了写操作， Thread 6 开始后面的操作
		*/
	}

}

class ReadThread extends Thread{
	private CountDownLatch counter;
	
	public ReadThread(CountDownLatch counter, String name){
		super(name);
		this.counter = counter;
	}
	
	@Override
	public void run() {
		System.err.println(Thread.currentThread().getName()+" 开始进行读操作...");
		//sleep(3000)代替写操作
		try {
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+ " 完成了读操作，等待其他线程完成读操作...");
			counter.countDown();
			counter.await();
		} catch (InterruptedException  e) {
			
			e.printStackTrace();
		}
		
		System.out.println("所有线程都完成了写操作， "+Thread.currentThread().getName() +" 开始后面的操作" );

	}
}
