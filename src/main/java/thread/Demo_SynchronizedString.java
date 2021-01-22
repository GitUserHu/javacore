package thread;
/**
 * String类型的实例，如果是以 String a = "aaa";的方式创建了String实例，那么String b = "aaa";这个实例是和a指向同一个实例对象。
 *     如果以 String a = new String("aaa");方式创建String实例，那么String b = new String("aaa");这个实例和a指向不同的实例对象。
 * @author JiaBing
 * @date 2019-01-03 21:52:00
 */
public class Demo_SynchronizedString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "aaa";
		String b = "aaa";
		String c = new String("bbb");
		String d = new String("bbb");
		System.out.println(c == d);
		new Thread(()->{
			synchronized (a) {
				System.out.println(Thread.currentThread().getName()+" 拿到了a(String)的锁");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 释放了a(String)的锁");
			}
		}) .start();
		new Thread(()->{
			synchronized (b) {
				System.out.println(Thread.currentThread().getName()+" 拿到了b(String)的锁");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 释放了b(String)的锁");
			}
		}) .start();
 	}

}
