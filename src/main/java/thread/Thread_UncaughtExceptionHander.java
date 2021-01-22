package thread;
/**
 *  新建的线程中，run()方法中 2/0 会出现 算术异常。然而，如果给这个线程设置一个UncaughtExceptionHandler实例，
 *  并且该实例中实现 uncaughtException(Thread t, Throwable e) 方法，{@code Thread.UncaughtExceptionHandler} 
 * <p>源码中这样说：
 *  Method invoked when the given thread terminates due to the given uncaught exception.
   <p>Any exception thrown by this method will be ignored by JVM
         
         根据本例的意思是： 本例中线程会抛异常，因此  uncaughtException()方法会被调用，但是异常会被JVM忽略掉。
 * @author JiaBing
 * @Date 2018/12/06
 *
 */
public class Thread_UncaughtExceptionHander {

	public static void main(String[] args) {
		
		Thread thread =new Thread() {
			int i = 0;
			@Override
			public void run(){
					i=2/0;
					System.out.println("end");
				
			}
		};
		Thread.UncaughtExceptionHandler handler = (t, e) ->{
			System.err.println(t.getName()+" shutdown, cause by Exception:"+ e);
		};
		
		thread.start();
		thread.setUncaughtExceptionHandler(handler);
		
	
	}
	
}
