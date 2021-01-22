package thread;
/**
 * @author JiaBing
 * @date 2019-01-23 10:05:01 
 */
public class Demo_Synchronized_Indentifier {

	public static void main(String[] args) {
		Demo demo = new Demo();
		new Thread(()->{
			
			Demo.show();
		}) .start();
		new Thread(()->{
			
			demo.fun1();
		}) .start();
		
		
		System.out.println("11");
	}
	

}

class Demo{
	public synchronized static void show() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"show()...");
	}
	public synchronized void fun1() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"fun1()...");
	}
}