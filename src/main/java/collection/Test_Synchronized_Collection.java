package collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author JiaBing
 *
 */
public class Test_Synchronized_Collection {
	
	public static void main(String[] args) throws InterruptedException {
		int n = 100;
		CountDownLatch count = new CountDownLatch(n);
		ExecutorService pool = Executors.newFixedThreadPool(n);
		Set<String> set = new HashSet<>();
		for(int j = 0 ; j<10000;j++) {
			set.add(j+"");
		}
		Date start = new Date();
		
		Collection<String> synSet = Collections.synchronizedCollection(set);
		for(int i = 0; i < n; i++) {
			pool.execute(()->{
				
				iterate(synSet);
				count.countDown();
			});
			
		}
		count.await();
		System.out.println("耗时:"+(new Date().getTime()-start.getTime()));
		pool.shutdown();
		
	}
	
	public static <T> void iterate(Collection<T> collection) {
		Iterator<T> it =  collection.iterator();
		System.out.println(Thread.currentThread().getName()+" is iterating collection...");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
