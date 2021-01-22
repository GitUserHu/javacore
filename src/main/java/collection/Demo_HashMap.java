package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

/**
 * 	HashMap
 * @author JiaBing
 * @date 2018-12-20
 */
public class Demo_HashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>(4);
		
		
		map.put("2", "two");
		map.put("1", "one");
		map.put("3", "three");
		
		map.put("4","three");
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(map.get(key));
		}
		int a = 2;
		int n = (a >>> 1) + a + 1;
		System.out.println(a^(a>>>16));
		System.out.println(test(n)+1);
		System.out.println(Integer.toBinaryString(15));
		System.out.println(Integer.toBinaryString(15>>>16));
		/*
		put = map.put("1", "oneee");
		System.out.println(put);
		System.out.println(map.get("1"));
		
		
		System.out.println("1".hashCode()&15);
		String[] arr1 = new String[10];
		arr1[1] = "aa";
		System.out.println("11");
		int n;
		char c = 49;
		System.out.println(c);
		System.out.println("1".hashCode());
		System.out.println(49 >>> 16);
		System.out.println((n = "1".hashCode())^(n >>> 16));
		System.out.println(49 ^ 0);*/
//		map.put("2", "two");
//		map.put("3", "three");
		//Iterator<String> keys = map.keySet().iterator();
		/*Iterator<Entry<String, String>> entrys = map.entrySet().iterator();
		while (entrys.hasNext()) {
			System.out.println(entrys.next());
		}*/
		/*while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(key+":"+map.get(key));
		}*/
		//System.out.println(test(2));
	}
	
	public static int test(int cap) {
		int n = cap -1;
		System.err.println(n);
		n |= n >>> 1;
		System.out.println("1："+ n);
        n |= n >>> 2;
        System.out.println("2："+ n);
        n |= n >>> 4;
        System.out.println("4："+ n);
        n |= n >>> 8;
        System.out.println("8："+ n);
        n |= n >>> 16;
        System.out.println("16："+ n);
        System.out.println(cap);
        return n;
	}

}
