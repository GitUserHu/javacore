package thread;

public class Thread_Demo {
	static Integer  i = new Integer(0);
	public static void main(String[] args) {
		
		new Thread(()->{
			i=new Integer(100);
			System.out.println("i in thread:"+i);
			
			}).start();;
		System.out.println(i);
	}

}
