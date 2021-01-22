package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author JiaBing
 * @date 2019-05-01 16:47:31 
 */
public class ReuseLock {

	public static void main(String[] args) {
		synchronized (String.class) {
			System.out.println("get lock in main thread.");
			ReuseLock.getLock();
		}

	}

	public static void getLock() {
		synchronized (String.class) {
			System.out.println("locking in String.class in getLock()");
		}
	}
}
