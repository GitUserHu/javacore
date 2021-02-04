package jdk1_8_new_specific.lambda;

/**
 *	 Java新特性：函数式接口
 *	  该特性需要与Lambda表达式配合使用 
 * @author Jiabing
 *
 */
@FunctionalInterface
interface FunctionalInterfaceDemo<T> {
	void display(T t);
	
	/**
	 * 	在接口中添加一个方法，并且自动实现。但是需要在方法前面加上default修饰。这样，实现该接口的类就不用重写该方法了。
	 * 	如果不在方法前面加上default， 那么所有实现该接口的类都必须挨着挨着重写该方法。 
	 * eg:JDK8 的List接口中新加入了sort()方法{@code java.util.List}/line 476  ，那么所有实现List接口的类都需要重写sort（）方法，那对JDK的编写者来说，估计要骂人。
	 * @param newFunction
	 */
	default void addNewFunction(String newFunction) {
		System.out.println("imlements a function in an interface. Function addNewFunction()");
	}
	
	/**
	 * 	在接口中定义一个Static方法，调用时直接通过接口调用。
	 */
	public static void staticFunction() {
		System.out.println("Static Function in an Interface.");
	}
}
