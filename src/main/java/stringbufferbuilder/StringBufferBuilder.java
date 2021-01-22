package stringbufferbuilder;



/**
 * 一、String的操作都是改变赋值地址而不是改变值操作
 * 
 * 
 * 
 * String、StringBuffer、StringBuilder 使用原则和注意事项
 * 
 * 如果要操作少量的数据用 String

	1、单线程操作字符串缓冲区下操作大量数据用StringBuilder

	2、多线程操作字符串缓冲区下操作大量数据用StringBuffer

	3、如果一个字符串变量是在方法里面定义的，这种情况可能只有一个线程访问它，不存在不安全的因素，则用StringBuilder，如果要在类里面定义成员变量，
	
		并且这个类的实例对象会在多线程环境下使用那么最好使用StringBuffer。

	4、String是不可变的对象, 因此在每次对String 类型进行改变的时候，都会生成一个新的 String 对象，然后将指针指向新的 String 
	
		对象，所以经常改变内容的字符串最好不要用 String ，因为每次生成对象都会对系统性能产生影响，
	
		特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，性能就会降低。

	5、不要使用String类的"+"来进行频繁的拼接，因为那样的性能极差的，应该使用StringBuffer或StringBuilder类，这在Java的优化上是一条比较重要的原则。
 * 
 * @author Jiabing
 *
 */
public class StringBufferBuilder {

	public static void main(String[] args) {
		//String
		String a="a";
		//a.
		TestA testA=new TestA(10);
		System.out.println(testA.objectAddr()+" \t"+testA.n);
		
		testA=new TestA(20);
		System.out.println(testA.objectAddr()+" \t"+testA.n);
		//StringBuffer
		StringBuffer sb=new StringBuffer("aaa");
		//StringBuffer的字符序列的长度
		System.out.println("length:"+sb.length());
		//新插入字符的可用容量 capacity=str.length+16   (str="aaa")
		System.out.println("capacity:"+sb.capacity());
		
		//StringBuilder
		
		String str1 = "1 2 3";
		String str2 = "123";
		StringBuffer sb1 = new StringBuffer("123");
		String replace = str1.replace(" ", "%20");
		System.out.println("Replaced: " + replace);

	}

}

class TestA{
	 final int n;
	
	TestA(int i){
		this.n=i;
	}
	
	String objectAddr(){
		return this.toString();
	}
	
}
