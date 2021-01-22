package thread;
/**
 * @author JiaBing
 * @date 2019-03-27 21:37:30 
 * 	测试：当线程wait时被唤醒时，从哪里开始执行:从wait调用之后开始执行
 */
public class Demo_WaitInWhile {

	public static void main(String[] args) throws InterruptedException {
		String lock = "A";
		int count = 0;
		Thread thread = new Thread(()->{
			System.out.println("before synchronized block - " + count);
			synchronized (lock) {
				
				while (lock.contains("A")) {
					System.out.println("before wait - "+ count);
					try {
						lock.wait();
					
						System.out.println("after wait - "+count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("doing something");
			}
		});
		thread.start();
		Thread.sleep(3000);
		synchronized (lock) {
			lock.notifyAll();
		}
		
	}
	

}
