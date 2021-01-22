package thread;
/**
 * @author JiaBing
 * @date 2019-01-23 16:36:43 
 */
public class NoVisibility {
	private static boolean ready;
	private static int number;
	public static void main(String[] args) {
		
		new Thread(()->{
			while (!ready) {
				System.out.println("false");
			}
			System.out.println(number);
		}).start(); 
		number = 40;
		ready = true;
	}

	
}
