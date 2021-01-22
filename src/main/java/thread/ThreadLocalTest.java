package thread;

public class ThreadLocalTest {
	public static void main(String[] args) throws InterruptedException {
		
		
		//testFunc();
		threadSecurity();
	
	}
	/**
	 * 
	 * @throws InterruptedException
	 */
	public static void testFunc() throws InterruptedException {
		final ThreadLocalTest test =new ThreadLocalTest();
		Thread tA=new Thread() {
			public void run() {
				//test.set();
				System.out.println(test.getId());
				System.out.println(test.getName());
			}
		};
		
		Thread tB=new Thread() {
			public void run() {
				//test.set();
				System.out.println(test.getId());
				System.out.println(test.getName());
			}
		};
		
		tA.start();
		tA.join();
		tB.start();
		tB.join();
		System.out.println(test.getId());
		System.out.println(test.getName());
	}
	
	ThreadLocal<Long> idLocal=new ThreadLocal<Long>() {
		protected Long initialValue() {
			return Thread.currentThread().getId();
		}
	};
	
	ThreadLocal<String> nameLocal=new ThreadLocal<String>() {
		protected String initialValue() {
			return Thread.currentThread().getName();
		}
	};
	
	public void set() {
		idLocal.set(Thread.currentThread().getId());
		nameLocal.set(Thread.currentThread().getName());
	}
	
	public Long getId() {
		return idLocal.get();
	}
	
	public String getName() {
		return nameLocal.get();
	}
	
	public static void threadSecurity() throws InterruptedException {
	
		for(int i= 0 ;i<1000;i++) {
			/*Thread thread= new Thread() {
				public void run() {
					for(int j=0;j<10;j++) {
						Counter.add();
					}
					
				}
			};*/
			
			Thread thread = new ThreadAdd();
			thread.start();
			thread.join();
		}
		//Thread.sleep(5000);
		System.out.println(" Finally: i = "+Counter.i);
	}

}

class Counter{
	public static int i=0;
	static ThreadLocal<Integer> local= new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return i;
		};
	};
	public static void add() {
		i++;
		
	}
}

class ThreadAdd extends Thread{
	
	@Override
	public void run() {
		for(int j = 0 ; j< 1000 ;j++)
		Counter.add();
		
		System.out.println(Thread.currentThread().getName()+"\t i = "+Counter.i);
	}
}

