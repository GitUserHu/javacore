package thread;
/**
 * sleep() wait() join() 
 * @author Jiabing
 *
 */
public class RunnableImpl {
	
	public static void main(String[] args) throws InterruptedException {
		createRunnableThread();
		//testNoJoin();
		//testWithJoin();
		//testInterrupt();
	}
	
	public static void testInterrupt() throws InterruptedException {
		Runnable ri=new RunnableInstance();
		Thread tA=new Thread(ri,"A");
		tA.start();
		//tA.join();
		tA.interrupt();
		System.out.println("Thread A 被中断。。。");
		
	}
	/**
	 * 	将runnable实现类实例作为参数传入Thread的构造函数，用Runnable的run()方法覆盖掉Thread类的run()方法。
	 */
	public static void createRunnableThread() {
		Runnable ri=new RunnableInstance();
		Thread tA=new Thread(ri,"A");
		//tA.setPriority(5);
		Thread tB=new Thread(ri,"B");
		//tA.setPriority(1);
		Thread tC=new Thread(ri,"C");
		//tA.setPriority(10);
		tA.start();
		tB.start();
		tC.start();
	}
	
	/**
	 * 没有加入Thread.join()
	 */
	public static void testNoJoin() {
		Runnable ri = new RunnableInstance();
		Thread thread =new Thread(ri,"Thread A");
		thread.start();
		
		//syso 时，这里count的值为100，而不是0. 
		//因为程序中存在两个线程，一个是主线程main，一个是子线程threadA。
		//两个线程并发执行，thread.start()只是让threadA进入就绪状态，并不一定会立即执行。
		//同时main主线程不会等待threadA执行完毕，而是执行后面的语句，此时变量count还没有被threadA改变，打印出的结果是100
		System.out.println("count is " +((RunnableInstance) ri).getCount());
	}
	/**
	 * 加入join()
	 * @throws InterruptedException
	 */
	public static void testWithJoin() throws InterruptedException {
		Runnable ri = new RunnableInstance();
		Thread thread =new Thread(ri,"Thread A");
		thread.start();
		//join()方法的作用是等到thread执行完之后才往下执行
		thread.join();
		//syso时，count is 0
		System.out.println("count is " +((RunnableInstance) ri).getCount());
	}
	
}


class RunnableInstance implements Runnable{
	public  int count=100;
	@Override
	public  void run() {
		
			while(count>=10) {
				synchronized (this) {
					if(count<10) break;
				//synchronized代码块  保证只有一个线程执行该代码。
				//比如：当线程A执行到synchronized代码时，线程B将不能执行，并且等到线程A执行完后才能执行,保证了数据的一致性
				count=count-10;
				System.err.println(Thread.currentThread().getName()+"  取出10元，当前余额为："+ count);
			}
			
			
		}
		
	}
	
	public static void doSomething() {
		synchronized (RunnableInstance.class) {
			//在类的静态方法中使用synchronized代码块，锁所在的位置是Class而不是某一个对象实例(this)
		}
	}
	
	public int getCount() {
		return count;
	}
}
