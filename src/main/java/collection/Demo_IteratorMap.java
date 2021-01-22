package collection;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author JiaBing
 * @date 2019-02-21 14:03:53 
 */
public class Demo_IteratorMap {

	public static void main(String[] args) {
		final List<String> list = new ArrayList<>();
		list.add("11");
		
		Map<String, String> map = new HashMap<String, String>(16);
		
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		// map.forEach(BiConsumer<? extends T, ? extends U> action)
		// BiConsumer 是一个功能性接口，使用JDK8的特性lambda, 实现  void accept(T t, U u); 这里只是打印map中的kv
		
		map.forEach((k, v)->{
			System.out.println("("+k+", "+v+")");
		});
		
	}

}
