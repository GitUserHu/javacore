package jdk1_8_new_specific.optional;

import java.util.Optional;

/**
 * Java 8 新特性： Optional类   Optional 类的引入很好的解决了空指针异常
 * @author JiaBing
 * @date 2019-09-05 18:03:30 
 */
public class OptionalDemo {

	public static void main(String[] args) {
		String str1 = null;
		String str2 = "hello optional class";
		forTesting(Optional.ofNullable(str1), Optional.ofNullable(str2));
	}
	
	public static void forTesting(Optional<String> a, Optional<String> b) {
		System.out.println("第一个参数值存在: " + a.isPresent());
	      System.out.println("第二个参数值存在: " + b.isPresent());
	      String valueA = a.orElse(" valueA is null");
	      String valueB = b.orElse(" valueB is null");
	      System.out.println(valueA+" | "+valueB);
	}
}
