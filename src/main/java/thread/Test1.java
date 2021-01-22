package thread;

public class Test1 {
	volatile static int a = 0;
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0; i< 10 ;i++) {
			Thread thread = new Thread() {
				public void run() {
					for(int j = 0 ;j<100;j++) a++;
				};
			};
			thread.start();
			thread.join();
		}
		
		System.out.println("a = " +a);
		
	}

}
