package thread;
/**
 * 这个Demo有可能出现死锁。
 * @author JiaBing
 *
 */
public class Test_Dead_Lock {

	public static void main(String[] args) {
		
		new Thread(()->{
			f1();
		}).start();; 
		
		new Thread(()->{
			f2();
		}).start();;
		
	}
	
	public static void f1() {
		synchronized (Integer.class) {
			System.out.println(Thread.currentThread().getName()+" 获取Integer 类的锁");
			synchronized (String.class) {
				System.out.println(Thread.currentThread().getName()+" 获取String 类的锁");
			}
			
		}
	}
	
	public static void f2() {
		synchronized (String.class) {
			System.out.println(Thread.currentThread().getName()+" 获取String 类的锁");
			synchronized (Integer.class) {
				System.out.println(Thread.currentThread().getName()+" 获取Integer 类的锁");
			}
			
		}
	}
}
