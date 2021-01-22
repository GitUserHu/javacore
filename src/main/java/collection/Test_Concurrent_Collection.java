package collection;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 	ConcurrentHashMap
 * @author JiaBing 
 * @date 2018-12-26
 */
public class Test_Concurrent_Collection {

	public static void main(String[] args) {
		Map<KeyGenerator, String> map = new ConcurrentHashMap<>(2);
		KeyGenerator key0 = new KeyGenerator0();
		KeyGenerator key4 = new KeyGenerator4();
		KeyGenerator key1 = new KeyGenerator1();
		KeyGenerator key2 = new KeyGenerator2();
		
		map.put(key0, "0");
		map.put(key1, "1");
		map.put(key2, "2");
		map.put(key4, "4");
		//map.put(new KeyGenerator_Custom(), "11");
		Map<String, Point> locations = new ConcurrentHashMap<String, Point>(16);
		locations.put("1", new Point(1, 2));
		Point point = locations.get("1");
		System.out.println(point.toString());
		locations.replace("1", new Point(2, 3));
		System.out.println(point.toString());
	}

}
class KeyGenerator_Custom extends KeyGenerator{
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 4;
	}
}

class Point{
	public final int x;
	public final int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
