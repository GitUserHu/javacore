package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 	HashMap多线程安全问题
 * @author JiaBing
 * @Date 2018-12-26
 */
public class Demo_HashMap_MutipleThreadSafety {

	public static void main(String[] args) throws InterruptedException {

		Map<KeyGenerator, String> map = new HashMap<>(2);
		KeyGenerator key0 = new KeyGenerator0();
		KeyGenerator key4 = new KeyGenerator4();
		
		KeyGenerator key1 = new KeyGenerator1();
		KeyGenerator key2 = new KeyGenerator2();
		map.put(key0, "0");
		map.put(key4, "4");
		map.put(key1, "1");
		
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		newFixedThreadPool.execute(()->{
			// TODO 此时会进行数组扩容
			map.put(key2, "2");
			System.out.println("1 put end...");
		});
		newFixedThreadPool.execute(()->{
			
			System.out.println(map.get(key4));
		});
		
		newFixedThreadPool.shutdown();
	}

}
class KeyGenerator{
	
}
class KeyGenerator0 extends KeyGenerator{
	@Override
	public int hashCode() {
		return 0;
	}
}
class KeyGenerator1 extends KeyGenerator{
	@Override
	public int hashCode() {
		return 1;
	}
}
class KeyGenerator2 extends KeyGenerator{
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 2;
	}
}
class KeyGenerator4 extends KeyGenerator{
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}

