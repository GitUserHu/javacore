package genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 	泛型
 * 注意： 1> 不能用基本数据类型实例化类型参数 		eg: new Pair<>(8, 'a'); // 编译错误
 * 	   2> 不可实例化类型参数 				eg:public static <E> void append(List<E> list) { E elem = new E();  // 编译错误 list.add(elem);} 
 * 		  但是可以通过反射实例化带有类型参数的对象 ，	具体查看 reflect()
 * 		3> 不能在静态字段上使用泛型   		?????静态变量是类变量，被所有实例共享,那么该静态变量具体是什么类型呢？
 * 		 4> 不能对带有参数化类型的类使用cast或instanceof方法
 *  	  5> 不能创建带有参数化类型的数组        	eg:List<Integer>[] arrayOfLists = new List<Integer>[2]; // 编， 编译错误
 *  		6> 不能创建、捕获泛型异常  (泛型类不能直接或间接继承Throwable类)       eg:class MathException<T> extends Exception {}    //编，编译错误
 *  		 7> 不能重载经过类型擦除后形参转化为相同原始类型的方法
 
 * @author Jia
 *
 */
public class Genericity {

	public static <E> void reflect(List<E> list ,Class<E> cls) throws Exception {
		//E e = cls.newInstance();
		for(E e2:list) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		//上，上限通配符的使用
		List<Student> students=new ArrayList<>();
		students.add(new Student("JIM"));
		students.add(new Student("SAM"));
		students.add(new Student("TOM"));
		extendsUse(students);
		//下，下限通配符的使用
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Mr Lee"));
		persons.add(new Person("Mr Wu"));
		persons.add(new Person("Mr Wang"));
		superUse(persons);
		//无，无限通配符的使用
		List<String> names = new ArrayList<>();
		names.add("Miss A");
		names.add("Miss B");
		names.add("Miss V");
		infiniteUse(names);
		List<Integer> ints= new ArrayList<>();
		ints.add(new Integer(10));
		ints.add(new Integer(20));
		ints.add(new Integer(30));
		infiniteUse(ints);
		
		//，，反射实例化带有类型参数的对象
		
		reflect(names, String.class);
		
		
		
		

	}
	/**
	 * 上限通配符
	 * @param list
	 */
	public static void extendsUse(List<? extends Person> list) {
		for(Person person:list) {
			System.out.print("\t"+person.toString());
		}
	}
	/**
	 * 下限通配符
	 * @param list
	 */
	public  static void superUse(List<? super Student> list) {
		
	}
	/**
	 * 无限通配符
	 * @param list
	 */
	public static void infiniteUse(List<?> list) {
		for(Object obj:list) {
			System.out.print("\t"+obj);
		}
	}
}

class Person {
	String name;
	public Person(String name) {
		this.name=name;
	}
	public String toString() {
		return "name = "+ name;
	}
	
}
class Student extends Person{

	public Student(String name) {
		super(name);
		
	}
	
}
