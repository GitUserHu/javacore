package thread;

import java.util.concurrent.Semaphore;

/**
 * @author JiaBing
 * @date 2019-03-14 23:19:23 
 */
public class Demo_Thread_Interrupt {

	public static void main(String[] args) {
		BlockClass bc = new BlockClass();
		Thread thread = new Thread(()-> {
			
			try {
				bc.take();
			} catch (InterruptedException success) {
				// TODO Auto-generated catch block
				success.printStackTrace();
				System.out.println("成功阻塞，并且中断");
			}
			
		});
		
		try {
			thread.start();
			Thread.sleep(3000);
			thread.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

class BlockClass {
	
	Semaphore space = new Semaphore(0);
	
	public void take() throws InterruptedException {
		
			space.acquire();
			System.out.println("信号量获取");
			space.release();
		
	}
}