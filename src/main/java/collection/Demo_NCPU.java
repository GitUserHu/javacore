package collection;

/**
 * Number of CPUS
 * @author JiaBing
 * @date 2018-12-28
 */
public class Demo_NCPU {

	public static void main(String[] args) {
		int number = Runtime.getRuntime().availableProcessors();
		System.out.println(number);
	}

}
