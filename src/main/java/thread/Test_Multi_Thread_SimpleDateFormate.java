package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 	SimpleDateFormate是线程安全的吗？ -> 不是线程安全的。
 *  如何解决多线程中使用SimpleDateFormate，出现的安全问题? -> 使用ThreadLocal<SimpleDateFormate> 给每个线程创建一个本地变量。
 * @author JiaBing
 *
 */
public class Test_Multi_Thread_SimpleDateFormate {

	public static void main(String[] args) {
		//创建固定大小的线程池
		ExecutorService pool = Executors.newFixedThreadPool(10);
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ThreadLocal<SimpleDateFormat> formate = new ThreadLocal<SimpleDateFormat>() {
			@Override
			protected SimpleDateFormat initialValue() {
				
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
		};
		List<String> dateStr = new ArrayList<>();
		dateStr.add("1971-12-10 23:59:59");
		dateStr.add("1973-11-10 23:59:59");
		dateStr.add("1995-05-02 23:59:59");
		dateStr.add("1994-01-27 23:59:59");
		dateStr.add("1994-08-07 23:59:59");
		for(String string : dateStr) {
			pool.execute(()->{
				try {
					System.out.println(formate.get().parse(string));
					//避免ThreadLocal造成内存溢出
					formate.remove();
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			});
		}
		//关闭线程池
		pool.shutdown();
		
	}

}
