package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  Volatile关键字
 * @author JiaBing
 * @date 2018-12-27
 */
public class Demo_Volatile_Identifier {
	 
	
	public static void main(String[] args) throws InterruptedException {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("1", "1");
		new Thread(()->{
			try {
				Thread.sleep(2000);
				map.put("1", "one");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			System.out.println(map.get("1"));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(map.get("1"));
		}).start(); 
		
		
	}

}
