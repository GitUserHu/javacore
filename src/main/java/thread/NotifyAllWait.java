package thread;

public class NotifyAllWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test2();
		
		//test();
		
		test3();
		
	}
	public static void test3() {
		Calculator calculator=new Calculator();
		
		ThreadMon m1=new ThreadMon(calculator);
		ThreadMon m2=new ThreadMon(calculator);
		ThreadMon m3=new ThreadMon(calculator);
		m1.start();
		m3.start();
		m2.start();
		calculator.start();
		
		
		//现在，这个房的 程序会出现bug的可能。
		 /*下面是出现bug时的打印结果：
			Thread-3 等待计算结果：
			*Thread-2 等待计算结果：
			Thread-1 等待计算结果：
			Thread-2 结果为：5050
			Thread-3 结果为：5050*/
		//分心，分析：calculator线程run完之后，notifyAll（）唤醒了正在等待的 3 和2
		//注意：但，线程1并没有真正运行起来。（虽然调用start()方法）.'
		//在线：而是notifyAll()后，线程1才运行起来，,所以线程1等不到第二次notifyAll()，因此会一直等待。
		

	}
	
	public static void test2() {
		ThreadIns tA=new ThreadIns();
		tA.start();
		synchronized (tA) {
			System.out.println("等待对象b完成计算。。。");
			try {
				tA.wait();//当前线程等待tA 完成，tA会 使用notify()唤醒当前线程，然后继续往下执行。	
				System.out.println(tA.total);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static  void test() {
		ThreadTest tt=new ThreadTest();
		ThreadTest tt2=new ThreadTest();
		ThreadTest tt3=new ThreadTest();
		tt.start();
		tt2.start();
		tt3.start();
	} 
	
}

class ThreadIns extends Thread{
	int total;
	public void run() {
		System.out.println("Thread start---");
			
		synchronized (this) {
			
		
		for(int i=0;i<100;i++){
			total += i;
		}
		
		
		System.out.println("after running, total is "+ total );
		notify();
		}
	}
}

class ThreadTest extends Thread{
	public void run() {
		System.out.println(Thread.currentThread().getName()+" run");
	}
}

class ThreadMon extends Thread{
	private Calculator calculator; 
	
	public  ThreadMon(Calculator calculator) {
		this.calculator=calculator;
	}
	public void run() {
		synchronized (calculator) {
			System.out.println(Thread.currentThread().getName()+" 等待计算结果：");
			
			try {
			
					calculator.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" 结果为："+calculator.total);
		}
	}
}
class Calculator extends Thread{
	int total;
	public void run() {
		synchronized(this) {
			System.out.println("Calculator start---");
			for(int i =0 ;i<101;i++) {
				total+=i;
			}
			notifyAll();
			
		}
		
	}
}




