package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JiaBing
 * @date 2019-01-16 22:46:03 
 */
public class Demo_AtomicInteger {

	public static void main(String[] args) {
		AtomicInteger count = new AtomicInteger(0);

	}

}

class CustomAtomicInteger{
	private AtomicInteger count = new AtomicInteger(0);
	private boolean updated = false;
	
	/**
	 * 对count变量+1，并且保证线程安全。 
	 * 虽然AtomicXXX类型的数据时原子性的，但是对数据进行++或者+1 不是原子性的操作，即对该类中的count++或者count = count + 1;
	 * 那么该方法如何保证线程安全性？
	 * 分析： 假设count的初始值为0
	 * 		当A、B两个线程同时执行inc()方法时， updated都是false,都能进到while循环中，并且得到的本地变量previous的值0,
	 * 		当A执行到updated = this.count.compareAndSet(previous, previous+1)时，previous和count在内存中的值一致，即都为0。
	 * 			那么count在内存中的值就会被更新为1,并且updated修改为true，退出循环
	 * 		当B执行到updated = this.count.compareAndSet(previous, previous+1)时，previous和count在内存中的值不一致，previous = 0,count = 1.
	 * 			那么count的值不会被更新，并且updated在B线程的堆栈 中的值修改为false（虽然原本就是false），继续执行while循环，previous本地变量拿到
	 * 			count在内存中的值1，B再执行到updated = this.count.compareAndSet(previous, previous+1)时，就能够修改count的值为2，updated变为true,
	 * 			退出循环
	 * 以上称之为  乐观锁，是一种非阻塞的模式。
	 * 		乐观锁适用于共享内存竞用不是非常高的情况。如果共享内存上的内容非常多，仅仅因为更新共享内存失败，就用浪费大量的CPU周期用在拷贝和修改上。
	 */
	public void inc() {
		while (!updated) {
			int previous = this.count.get();
			
			updated = this.count.compareAndSet(previous, previous+1);
		}
	}
	
}

