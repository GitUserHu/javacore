package thread;

public class ThreadInstance extends Thread{
	public  static int i;
	static {
		i=0;
	}
	
	public  ThreadInstance(String name) {
		super(name);
	}
	@Override
	public  void run() {
		for(int j=0;j<10;j++) {
			synchronized (this) {
				i++;
			
			
			System.out.println(Thread.currentThread().getName()+" i = "+i);
			}
		}
	}
	public static void main(String[] args) {
		Thread threadA=new ThreadInstance("A");
		Thread threadB=new ThreadInstance("B");
		Thread threadC=new ThreadInstance("C");
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
