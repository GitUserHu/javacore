package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
/**
 * Callable<V> 接口跟 Runnable 接口类似，都是代表线程中具体运行的任务，都是FunctionalInterface,
 * 	前者的实现类实现 callable()方法，后者实现 run()方法
 *       前者的运行方式为 Executors.xxxMethod().submit(callable),后者运行方式为传到一个Thread的构造函数中，执行thread.start();
 *       前者 callable()方法有返回值，<V>就是返回值的类型，并且允许抛出异常。后者 run()方法不能有返回值，也不能抛出异常，必须手动捕获，或者设置UncaughtExceptionHandler
 *  
 * @author JiaBing
 * @Date 2018/12/06
 */
public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = () ->{
			System.out.println("callable call()");
			return 10;
		};
			
		System.err.println(Executors.newCachedThreadPool().submit(callable).get());
		
	}

}
