package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_Thread_Local {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Task());
		Thread t2 = new Thread(new Task());
		t1.start();
		t2.start();
		

	}
	
	public static String threadSafeFormate(Date date) {
		return PerThreadFormatter.getDate().get().format(date);
	}

}

class PerThreadFormatter{
	
	private static final ThreadLocal<SimpleDateFormat> date = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			
			System.out.println(Thread.currentThread().getName()+" creating simpleDateFormate.");
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		};
	};

	public static ThreadLocal<SimpleDateFormat> getDate() {
		return date;
	}
	
	
}

class Task implements Runnable{

	@Override
	public void run() {
		for(int i = 0 ; i < 2 ; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" Formatted Date: "+ Test_Thread_Local.threadSafeFormate(new Date()));
		}
		
	}
	
}
