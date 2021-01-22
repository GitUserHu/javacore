package collection;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *	 阻塞队列
 * @author JiaBing
 * @date 2019-01-14 21:42:22 
 */
public class Demo_BlockingQueue {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(4);
		 System.out.println("Queue Add()...");
		 queueAdd(arrayBlockingQueue);
		 display(arrayBlockingQueue);
		 arrayBlockingQueue.clear();
		 System.out.println("Queue Put()...");
		 queuePut(arrayBlockingQueue);
		 display(arrayBlockingQueue);
		 
	}
	
	public static void queuePut(ArrayBlockingQueue<String> queue) {
		try {
			try {
				queue.put("a");
				queue.put("b");
				queue.put("c");
				queue.put("d");
				//queue.put("e");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IllegalStateException exc) {
			// TODO Auto-generated catch block
			System.err.println("Queue is full.");
		}
	}
	
	public static void queueAdd(AbstractQueue<String> queue) {
		try {
			queue.add("1");
			queue.add("2");
			queue.add("3");
			queue.add("4");
			queue.add("5");
		} catch (IllegalStateException exc) {
			// TODO Auto-generated catch block
			System.err.println("Queue is full.");
		}
	}
	
	public static <E> void display(Collection<E> collection) {
		Iterator<E> iterator = collection.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
