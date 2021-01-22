package thread;

import java.lang.management.ManagementFactory;

// 这个demo 判断线程是否持有某个对象的锁
public class Test_Lock_Demo_1 {

	public static void main(String[] args) {
		new Thread(()->{
			synchronized (Integer.class) {
				
				if(Thread.holdsLock(Integer.class))
					System.out.println("hold Integer lock...");
				synchronized (String.class) {
					System.out.println("hold String lock...");
				}
			}
		}).start();;
		
		System.out.println(ManagementFactory.getRuntimeMXBean().getSystemProperties());
		

	}

}
