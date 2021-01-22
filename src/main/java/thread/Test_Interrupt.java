package thread;

public class Test_Interrupt {

	public static void main(String[] args) throws InterruptedException {
		Thread thread_Interrupt = new Thread_Interrupt();
		thread_Interrupt.start();
		
		thread_Interrupt.interrupt();
		
		System.out.println(thread_Interrupt.isInterrupted());

	}

}

class Thread_Interrupt extends Thread{
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}