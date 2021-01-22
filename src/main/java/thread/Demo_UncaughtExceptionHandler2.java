package thread;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JiaBing
 * @date 2019-02-27 23:15:09
 *  这里并没有为线程池所有线程设置一个UncaughtExceptionHandler：要设置一个处理器，需要为线程池的构造函数提供一个ThreadFactory
 * 
 * 只有通过execute提交的任务才能将它抛出的异常交给UncaughtExceptionHandler(这种情况，在任务线程运行期间遇到错误会直接将错误抛出来)
 * 而通过submit提交的任务无论是抛出未检查异常还是已检查异常，都将被认为是任务返回状态的一部分。如果一个由sbumit 提交的任务由于抛出了异常而结束，
 * 那么这个异常将被Future.get()封装在ExecutionException中（submit(task)的返回值是Future<?>类型的,）
 * 
 * 具体信息参照运行结果
 */
public class Demo_UncaughtExceptionHandler2 {

	public static void main(String[] args) {
		
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.add(0,"1");
		linkedList.add(0,"2");
		linkedList.forEach((s)->{
			System.out.println(s);
		});
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 15, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(16));
		
		executor.setThreadFactory((r)->{ 
			Thread thread = new Thread(r); 
			thread.setUncaughtExceptionHandler((t, e)->{
				System.out.println(t.getName()+" UncaughtExceptionHandler :" +e.getMessage());
			}); 
			return thread;});
		Future<?> submited = executor.submit(()->{
			causeUncaughtException();
		});
		try {
			submited.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.execute(()->{
			causeUncaughtException();
		});
		executor.execute(()->{
			causeUncaughtException();
		});
		executor.shutdown();
		
	}
	
	private static  void  causeUncaughtException() {
		int i = 10 / 0;
		System.out.println(i);
	}
	
}
