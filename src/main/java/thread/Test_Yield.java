package thread;

public class Test_Yield {
	static Object obj = new Object();
	public static void main(String[] args) {
		
		
		Thread t1 = new YieldTest("t1");
		Thread t2 = new YieldTest("t2");
		t1.start();
		t2.start();

	}
	static class YieldTest extends Thread{
		
		public YieldTest(String name) {
			super(name);
		}
		
		@Override
		public synchronized void run() {
			synchronized(obj) {
				for(int i = 0 ; i < 10 ; i ++) {
					System.out.println(Thread.currentThread().getName()+" i = "+i);
					if(i % 4 == 0) {
						// yield()不会释放锁
						Thread.yield();
					}
				}
			}
			
		}
	}

}
