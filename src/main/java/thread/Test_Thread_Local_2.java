package thread;

public class Test_Thread_Local_2 {

	public static void main(String[] args) {
		
		new Thread(new Test_Task()).start();
	}

}

class Model {
 	private static ThreadLocal<String> strLocal = new ThreadLocal<String>() {
 		@Override
		protected String initialValue() {
 			System.out.println("initialValue()");
			return Thread.currentThread().getName();
		};
	};
	


	public static ThreadLocal<String> getStrLocal() {
		return strLocal;
	}

	public static void setStrLocal(ThreadLocal<String> strLocal) {
		Model.strLocal = strLocal;
	}


	
	
}

class Test_Task implements Runnable{

	@Override
	public void run() {
		
		try {
			System.out.println("Thread Name is " + Model.getStrLocal().get());
		} finally {
			// TODO: handle finally clause
			Model.getStrLocal().remove();
		}
		System.out.println("Thread Name is " + Model.getStrLocal().get());
		
	}
	
}
