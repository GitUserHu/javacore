package exception;
/**
 * @author JiaBing
 * @date 2019-05-18 17:23:08 
 * 
 * 在try catch finally 块中，要根据代码的实际情况，判断方法的实际运行流程：需要注意返回值的位置。
 *
 */
public class Exce_Finally {

	public static void main(String[] args) {
		//System.out.println("i = "+test());
		testWithNoReturn();
	}
	
	public static int test() {
		int i = 1;
		try {
			
			int j = i/0;
			return i;
		} catch (ArithmeticException e) {
			i = 2;
			return i;
		}finally {
			i = 3;

		}
	} 
	
	public static void testWithNoReturn() {
		try {
			System.out.println("111");
			int i = 10/0;
			System.out.println("222");		
		} catch (NumberFormatException e) {
			System.out.println("333");
		}finally {
			System.out.println("444");
		}
	}

}
