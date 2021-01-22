package thread;

import java.util.concurrent.CountDownLatch;

public class Demo_MutipleThread {
	
	public static void main(String[] args) throws InterruptedException {
		// 普通顺序执行
		//sequenceExecute();
		// 多线程
		//mutipleThread();
		CountDownLatch cdl = new CountDownLatch(2);
		Counter_For cFor = new Counter_For();
		
		new Thread(()->{
			cFor.add(2);
			//cdl.countDown();
		}).start();
		new Thread(()->{
			cFor.add(3);
			//cdl.countDown();
		}).start();
		//cdl.await();
		Thread.sleep(3000);
		System.out.println(cFor.i);
	}
	
	

	public static void mutipleThread() throws InterruptedException {
		String fileA = "A";
		String fileB = "B";
		CountDownLatch cdl = new CountDownLatch(2);
			// 多线程
				long start_Mutiple = System.currentTimeMillis();
				new Thread(()->{
					
					try {
						readFile(fileA);
						handle(fileA);
						cdl.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
				new Thread(()->{
					
					try {
						readFile(fileB);
						handle(fileB);
						cdl.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
				cdl.await();
				System.out.println("用时：" +(System.currentTimeMillis()-start_Mutiple));
	}
	
	public static void sequenceExecute() throws InterruptedException {
		String fileA = "A";
		String fileB = "B";
		long start = System.currentTimeMillis();
		readFile(fileA);
		handle(fileA);
		readFile(fileB);
		handle(fileB);
		System.out.println("用时：" +(System.currentTimeMillis()-start));
	}
	public static void readFile(String fileName) throws InterruptedException {
		System.out.println("reading file "+fileName);
		Thread.sleep(5000);
		
	}
	public static void handle(String fileName) throws InterruptedException {
		System.out.println("handling file "+fileName);
		Thread.sleep(2000);
	}
}

class Counter_For{
	int i;
	public void add(int val) {
		i+= val;
	}
}
