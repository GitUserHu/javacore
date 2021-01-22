package thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author Jiabing
 *
 */
public class ThreadPool {

	public static void main(String[] args) {
		
		//mutiTaskPool();
		//singleTaskPool();
		createThreadPool();
	}
	/**
	 * 单任务线程池：单任务线程池可保证顺序地执行各个任务
	 */
	public static void singleTaskPool() {
		ThreadTT tA=new ThreadTT("Thread A");
		ThreadTT tB=new ThreadTT("Thread B");
		ThreadTT tC=new ThreadTT("Thread C");
		ThreadTT tD=new ThreadTT("Thread D");
		ThreadTT tE=new ThreadTT("Thread E");
		ExecutorService pool =Executors.newSingleThreadExecutor();
		pool.execute(tA);
		pool.execute(tB);
		pool.execute(tC);
		pool.execute(tD);
		pool.execute(tE);
		pool.shutdown();
	}
	/**
	 * 多任务线程池，线程池并不保证按照线程加入池中的顺序来执行。
	 */
	public static void mutiTaskPool() {
		
		ThreadTT tA=new ThreadTT("Thread A");
		ThreadTT tB=new ThreadTT("Thread B");
		ThreadTT tC=new ThreadTT("Thread C");
		ThreadTT tD=new ThreadTT("Thread D");
		ThreadTT tE=new ThreadTT("Thread E");
		//，，创建一个可重用固定线程数的线程池
		ExecutorService excutor=Executors.newFixedThreadPool(1);
		excutor.execute(tA);
		excutor.execute(tB);
		excutor.execute(tC);
		excutor.execute(tD);
		excutor.execute(tE);
		excutor.shutdown();
	}

    private static int queueDeep = 4;
    
	public static void createThreadPool() {
		/*
		 * 创建线程池，最小线程数为2，最大线程数为4，线程池维护线程的空闲时间为3秒，
		 * 使用队列深度为4的有界队列，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，
		 * 然后重试执行程序（如果再次失败，则重复此过程），里面已经根据队列深度对任务加载进行了控制。
         */
		ThreadPoolExecutor tpe=new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS	, new ArrayBlockingQueue<Runnable> (queueDeep)
				,new ThreadPoolExecutor.DiscardOldestPolicy());
		
		for(int i=0; i<10;i++) {
			while(getQueueSize(tpe.getQueue())>=queueDeep) {
				try {
					System.out.println("队列满了，3秒后再添加任务。。。");
					ThreadTT.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ThreadTT task=new ThreadTT((i+1)+"");
			System.out.println("put 第 "+(i+1)+" 个任务到线程池");
			tpe.execute(task);
		}
		tpe.shutdown();
			
	}
	
	public synchronized static int getQueueSize(Queue queue) {
		return queue.size();
		
	}
}

class ThreadTT extends Thread{
	private String name;
	
	public void run() {
		//System.out.println(Thread.currentThread().getName()+" 正在执行。。。");
		System.out.println("任务"+name+" 正在执行。。。");
		try {
			ThreadTT.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ThreadTT(String name) {
		this.name=name;
	}
}
