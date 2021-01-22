package staticIdentifier;

import java.util.ArrayList;
import java.util.List;

/**
 * static 修饰符
 * @author Jiabing
 *
 */
public class StaticIdentifier {
	static Util util=new Util();
	private static final List<String> list=new ArrayList<String>();
	//
	//static int a=10;
	static {
		// a=20;
		System.out.println("父类静态代码块");
	}
	StaticIdentifier(){
		System.out.println("父类构造方法");
	}
	
	static void func1(){
		//a=30;
		//System.out.println(a);
	}
	static void func2(int a){
		a=40;
		//System.out.println(StaticIdentifier.a);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//func1();
		//func2(a);
		Child child=new Child();
		//list 虽然是final修饰的，但是list.add并没有修改容器本身，只是向容器中加入数据
		//list=new ArrayList<>();//修改了容器本身，编译报错
		list.add("a");
		list.add("b");
		for(String s:list){
			System.out.println(s);
		}
	}

}
class Child extends StaticIdentifier{
	static {
		System.out.println("子类 static 代码块");
	}
	public Child() {
		System.out.println("子类构造方法");
	}
	
}
class Util{
	
	Util(){
		System.out.println("初始化Util");
	}
}
