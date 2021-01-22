package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JiaBing
 * @date 2019-04-01 22:55:25 
 */
public class Demo_ConditionQueue {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();

	}

}
