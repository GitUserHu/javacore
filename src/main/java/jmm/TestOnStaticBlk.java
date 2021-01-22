package jmm;
/**
 * @author JiaBing
 * @date 2019-05-25 14:50:21 
 * <p>测试 静态代码块和类变量(static 变量)的初始化顺序
 * <p>类加载的全过程:
 * <p>1、加载
 * <p>2、验证
 * <p>3、准备：准备阶段是正式为类变量分配内存冰设置类变量初始值的的阶段。
 * <p>4、解析
 * <p>5、初始化：初始化阶段是执行类构造器 <clinit>() 方法的过程。<clinit>()方法是由编译器自动收集类中的所有类变量的
 *      赋值动作和静态代码块的语句合并产生的。收集顺序是由语句在源文件中出现的顺序决定的。
 * 	
 * <p>6、使用
 * <p>7、卸载
 * 
 * 
 * <p>如果将 类变量的声明与静态代码块的位置互换会出现什么？
 * <p> syso输出的地方报错：在域被定义之前不能引用(Cannot reference a field before it is defined)
 * <p>那为什么 i = 100不会报错？ 参考《深入理解JVM-JVM高级特性与最佳实践》的类加载机制的初始化阶段,原文：
 *     静态代码块种只能访问到定义在静态代码块之前的变量，定义在它之后的变量，在前面的静态代码块可以赋值，但是不能访问
 * <p>先注释掉报错的代码,这时 i初始化之后， i的值是多少？
 * <p> 正如所料 i 的值为10。
 * <p>为什么是10？而不是100呢？
 * <p> 这时候需要回头去看看类加载的初始化阶段做的事情。
 */
public class TestOnStaticBlk {
	static { // 2
		i = 100; 
		//System.out.println("static code block: i = "+i);
	}
	static int i = 10;// 1
	
	public static void main(String[] args) {
		new TestOnStaticBlk();
		System.out.println("TestOnStaticBlk.i is " + TestOnStaticBlk.i);
		//String aString = "";
		//aString.lastIndexOf(ch)
	}
	
}


