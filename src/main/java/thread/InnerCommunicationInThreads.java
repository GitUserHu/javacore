package thread;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class InnerCommunicationInThreads {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedBlockingQueue<>(10);
		Thread c = new Creater(queue, "厂家");
		Thread e1 = new Eater(queue, "买家 1");
		Thread e2 = new Eater(queue, "买家 2");
		c.start();
		e1.start();
		e2.start();

	}

}

class Creater extends Thread{
	
	private final Queue queue;
	
	public Creater(Queue queue,String name) {
		super(name);
		this.queue=queue;
	}
	@Override
	public void run() {
		while(true) {
			synchronized (queue) {
				try {
					
					while(queue.size()>=10) {
						System.err.println(Thread.currentThread().getName()+": 队列满了");
						queue.wait();
					}
					System.out.println(Thread.currentThread().getName()+": 生产一个");
					
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				queue.add(1);
				queue.notifyAll();
				
			}
			
		}
	}
}

class Eater extends Thread{
	
private final Queue queue;
	
	public Eater(Queue queue,String name) {
		super(name);
		this.queue=queue;
	}
	@Override
	public void run() {
		while(true) {
			synchronized (queue) {
				try {
				while(queue.size()==0) {
					
						System.err.println(Thread.currentThread().getName()+": 队列空了");
						queue.wait();
					
				}
				
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+"： 吃掉一个");
					queue.poll();
					queue.notifyAll();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
}


