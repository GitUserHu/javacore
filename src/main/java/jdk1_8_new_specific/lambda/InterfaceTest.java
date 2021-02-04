package jdk1_8_new_specific.lambda;
/**
 * @author JiaBing
 * @date 2019-07-05 17:18:13 
 * 这个Demo证明了，不是只有加了@FunctionalInterface注解的接口才可以使用Lambda表达式。
 * 而是， 只要接口满足了FuntionalInterface的定义都可以使用
 */
public interface InterfaceTest {
	public void test(String a, String b);
	
	public static void main(String[] args) {
		InterfaceTest test = (a, b)->{
			System.out.println(a+":"+b);
		};
		test.test("1", "2");	
	}
}
