package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Lock 类   锁类
 * @author Jiabing
 *
 */
public class LockInstance {
	//，锁为类变量，这一点很重要
    //ReentrantLock的意思是“可重入锁”，ReentrantLock是唯一实现了Lock接口的类
	private Lock lock=new ReentrantLock();
	private int counter=0;
	public void add() {
		//获，获取锁
		lock.lock();
		
		try {
			System.out.println(Thread.currentThread().getName() + " 获取到锁");
			for (int i = 0; i < 10; i++) {
				counter++;
			}
			System.out.println("counter = " + counter);
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放锁");
		}
		
	}
	
	/**
	 * 无限通配符
	 * @param list
	 */
	public static  void test(List<?> list) {
		for(Object obj:list) {
			System.out.print("\t"+obj);
		}
	}
	public static void main(String[] args) {
		LockInstance li=new LockInstance();
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				li.add();
		}
		};		
		Thread tA=new Thread(runnable,"Thread A");
		Thread tB=new Thread(runnable,"Thread B");
		tA.start();
		tB.start();
		
		
	}

}
