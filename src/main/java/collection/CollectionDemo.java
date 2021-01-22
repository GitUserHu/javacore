package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 集合类
 * @author Jiabing
 *
 */
public class CollectionDemo {

	public static void main(String[] args) throws Exception {
		//setImpl();
		//mapImpl();
		//testForSet();
		//listImpl();
		//queueImpl();
		arrayBlockingQueue();
	}
	
	

	
	public static void arrayBlockingQueue() throws Exception {
		ArrayBlockingQueue<Integer> abq=new ArrayBlockingQueue<>(5);
		Producer producer=new Producer(abq);
		Consumer consumer=new Consumer(abq);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(consumer).start();
	}
	
	/**
	 * 队列
	 */
	public static void queueImpl() {
		//优，优先级队列，构造函数中传入一个控制升序排列的比较器
		PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2-o1;
			}
		});
		priorityQueue.add(3);
		priorityQueue.add(10);
		priorityQueue.add(2);
		priorityQueue.add(6);
		System.out.println("对头元素为："+priorityQueue.poll());
		display(priorityQueue);
		
	}
	
	/**
	 * List列表接口的实现类测试
	 * @throws Exception 
	 */
	public static void listImpl() throws Exception {
		//List 的实现
		//1、   ArrayList
		List<String> l=new ArrayList<>();
		l.add("a");
		l.add("b");
		l.add("b");
		l.add("c");
		display(l);
		List<String> subList=new ArrayList<>();
		subList=l.subList(1, 3);
		System.out.print("subList:");
		display(subList);
		//List的remove(Object obj)方法移除的仅是列表中第一个出现的元素
		boolean removed=l.remove("b");
		System.out.println("removed:"+removed);
		display(l);
		try {
			subList.get(1);
		} catch (Exception e) {
			throw new Exception("主列表发生变化后，只要调用子列表的方法就会出现异常。com.sun.jdi.InvocationException occurred invoking method.");
		}
		
		String replacedValue=l.set(2, "setValue");
		System.out.println("replacedValue:"+replacedValue);
		display(l);
		//2.LinkedList
		l=new LinkedList<>();
		l.add("a");
		l.add("b");
		l.add("b");
		l.add("c");
		boolean remove = l.remove("b");
		System.out.println("remove:"+remove);
		display(l);
		
	}
	
	/**
	 * Set接口实现类的测试（HashSet、LinkedHashSet）
	 */
	public static void testForSet() {
		Set<String> set1=new HashSet<>();
		set1.add("a");
		set1.add("c");
		set1.add("b");
		set1.add("e");
		set1.add("d");
//		displaySet(set1);
		//setFunctions();
		int size=15;
		System.out.println(Math.max((int) (size/.75f) + 1, 16));
		Set<String> set=new HashSet<>(set1);
		display(set);
		
		HashSet< String> hashSet=new HashSet<>();
		hashSet.addAll(set1);
		for(String s:hashSet) {
			System.out.print(s+" ");
		}
		
		Set<String> linkedHashSet=new LinkedHashSet<>();
		linkedHashSet.add("b");
		linkedHashSet.add("e");
		linkedHashSet.add("f");
		linkedHashSet.add("a");
		for(String s:linkedHashSet) {
			System.out.print(s+" ");
		}
	}
	/**
	 * Set接口实现类: HashSet的测试
	 * Set集合是无序的，因此是不重复的。Set集合能包含null的元素，但是只能包含一个。
	 */
	public static void setImpl() {
		Set<String> sets=new HashSet<>();
		sets.add("aaa");
		sets.add("bbb");
		sets.add("aaa");
		sets.add(null);
		Iterator<String> iterator=sets.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	/**
	 * Map接口实现类：HashMap的测试
	 */
	public static void mapImpl() {
		Map<String, String> maps=new HashMap<>();
		maps.put("001", "AAAAA");
		maps.put("002", "bbbbb");
		maps.put("001", "CCCCCC");
		Iterator<String> keyIterator=maps.keySet().iterator();
		while(keyIterator.hasNext()) {
			System.out.println(maps.get(keyIterator.next()));
		}
	}
	
	/**
	 * Set集合中的各个方法：
	 * containsAll、addAll、retainAll、removeAll
	 * 
	 */
	public static void setFunctions() {
		Set<String> set1=new HashSet<>();
		Set<String> set2=new HashSet<>();
		//set1：{a,b,c,d,e}
		set1.add("a");
		set1.add("b");
		set1.add("c");
		set1.add("d");
		set1.add("e");
		//set2:{b,d,c,e,f}
		set2.add("b");
		set2.add("d");
		set2.add("c");
		set2.add("e");
		set2.add("f");
		//set2是否是set1的子集。如果是，则返回true，否则返回false；
		System.out.println(set1.containsAll(set2));
		//将，将set1和set2的并集存入set1.
		set1.addAll(set2);
		System.out.println("set1和set2并集放入Set1:");
		display(set1);
		//将，将set1和set2的交集放入set1.
		set1.retainAll(set2);
		System.out.println("set1和set2交集放入Set1:");
		display(set1);
		//移，移除set1中与set2重复的元素。
		set1.removeAll(set2);
		System.out.println("set1移除与set2中重复的元素后：");
		display(set1);
	}
	/**
	 * 遍历集合
	 * @param coll
	 */
	public static <E> void display(Collection<E> coll) {
		Iterator<E> it=coll.iterator();
		while (it.hasNext()) {
			System.out.print(it.next()+"\t");
			
		}
		System.out.println();
	}
	
}

/**
 * 生产者
 * @author Jiabing
 *
 */
class Producer implements Runnable{
	private final ArrayBlockingQueue<Integer> abq;
	Producer(ArrayBlockingQueue<Integer> queue){
		this.abq=queue;
	}
	@Override
	public void run() {
		while(true) {
			try {
				abq.put(1);
				System.out.println("生产 1 个内容。。。");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("队列满了，不能继续生产。");
				e.printStackTrace();
			}
		}
		
	}
	
}

/**
 * 消费者
 * @author Jiabing
 *
 */
class Consumer implements Runnable{
	private final ArrayBlockingQueue<Integer> abq;
	Consumer(ArrayBlockingQueue<Integer> queue){
		this.abq=queue;
	}
	@Override
	public void run() {
		while(true) {
			try {
				abq.take();
				System.out.println("消费 1 个内容。。。");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("队列空了，不能继续消耗。");
				e.printStackTrace();
			}
		}
		
	}
	
}
