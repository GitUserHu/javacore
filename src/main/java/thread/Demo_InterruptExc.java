package thread;
/**
 * @author JiaBing
 * @date 2019-01-30 17:22:00 
 */
public class Demo_InterruptExc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class TaskIn implements Runnable{
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// 恢复 线程的中断状态
			Thread.currentThread().interrupt();
		}
	}
}