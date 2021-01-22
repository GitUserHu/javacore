package thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CAS: compare and swap.
 * @author JiaBing
 * @date 2019-01-15 22:52:04 
 */
public class Demo_CAS {

	public static void main(String[] args) {
		
	}

}

class UnSafeLock{
	private boolean isLocked = false;
	
	/** 
	 *    如果锁的状态是未锁，则锁住，并且返回true,否则返回false
	 *    但是这个方法在多线程情况下行不通，因为A, B线程可能同时拿到锁的状态为未锁状态
	 * @return
	 */
	public boolean lock() {
		if (!isLocked) {
			isLocked = true;
			return true;
		}
		return false;
	}
}

class SafeLock{
	private AtomicBoolean isLock = new AtomicBoolean();
	
	public boolean lock() {
		// 比较isLock的值 和 false, 如果为false，则isLock的值设置为true,并且返回true,否则不设置，返回false。
		// 这是一个原子性的操作
		return isLock.compareAndSet(false, true);
	}
	
}
