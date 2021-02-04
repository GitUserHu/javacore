package jdk1_8_new_specific.lambda;

import java.util.function.Function;

/**
 * @author JiaBing
 * @date 2019-09-05 16:20:56 
 * Java 8 新特性之一： Lambda
 * 可以使用Lambda来简化接口的实现，但是接口必须是函数式接口（即：实现该接口时，只有唯一一个方法需要被实现）
 */
public class LambdaDemo1 {
	
	public static void main(String[] args) {
		anonymInnerVsLambda();

	}
	
	/**
	 * 匿名内部类 VS Lambda
	 */
	public static void anonymInnerVsLambda() {
		/**------------------------------匿名内部类方式----------------------------------------**/
		// 整数类型的String
		String numberString = "123";
		/* 将字符串转换为整数 123 */
		// 使用匿名内部类创建Function接口的实现类对象 convert
		// 实现Function接口的时候需要实现其中的apply()方法，apply方法接收一个模板类型T作为输入参数， 返回模板类型R作为输出参数。
		// Function<T, R>
		Function<String, Integer> convert=new Function<String, Integer>() {

			@Override
			public Integer apply(String t) {
				
				return Integer.parseInt(t);
			}
		};
		// 调用convert对象的apply实现转换
		Integer integer=convert.apply(numberString);
		System.out.println("Using Anonymous inner to get Integer:"+integer);
		
		
		/**-----------------------------Lambda方式-------------------------------------------------**/
		// 使用Lambda方式简化
		Function<String, Integer> function=string ->Integer.parseInt(string);
		Integer number=function.apply(numberString);
		System.out.println("Using Lambda to get Integer:"+number);
		
		
		/**
		 * 方法引用：某些lambda表达式里面仅仅是调用了一个已存在的方法，在这种情况下
		 *	直接通过方法名称引用方法的形式可读性更高一些，这种形式就是方法引用
		 */
		// 使用方法引用简化lambda表达式
		Function<String, Integer> function2 = Integer::parseInt;
		Integer num=function2.apply(numberString);
		System.out.println("Using Function Reference to get Integer:"+num);
		
		// andThen方法接收一个Function类的实例，通过andThen可以将任意多个Function的apply方法调用连接起来
		Function<String,String> a =s->{System.out.println("a output "+s); return s;};
		Function<String,String> b =s->{System.out.println("b output "+s); return s;};
		Function<String,String> c =s->{System.out.println("c output "+s); return s;};
		a.andThen(b).andThen(c).apply("Hello");
		a.compose(b).compose(c).apply("world");
		
		FunctionalInterfaceDemo<String> testInter=s->System.out.println("NewSpecificTestIner display "+s);
		
		testInter.display("LOL");
		
	}

}
