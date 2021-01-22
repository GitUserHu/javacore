package thread;

public class Test_Wait {

	public static void main(String[] args) {
		Thread_T t1 = new Thread_T("t1");
	synchronized (t1) {
		
	
	try {

		System.out.println(Thread.currentThread().getName()+" start t1");
        t1.start();
        System.out.println(Thread.currentThread().getName()+" wait()");
        //wait()会释放锁
        t1.wait();
        //sleep()不会释放锁
        //Thread.sleep(10000);
      Thread.interrupted();
		
		System.out.println(Thread.currentThread().getName()+" continue");
	} catch (Exception e) {
			
		e.printStackTrace();
	}

	}
	}

}

class Thread_T extends Thread{
	
	public Thread_T(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+" call notify()");
			 notify();
		}
		 
	}
}
