package thread;
/**
 * 	让三个线程t1, t2, t3 顺序运行
 * @author JiaBing
 *
 */
public class Test_Join {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+" is running...");
		Thread t1 = new Thread(new Task_Join());
		Thread t2 = new Thread(new Task_Join());
		Thread t3 = new Thread(new Task_Join());
		try {
			t1.start();
			t1.join();
			t2.start();
			t2.join();
			t3.start();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" end...");

	}

}

class Task_Join implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" end...");
	}
}