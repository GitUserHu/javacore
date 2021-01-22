package exception;

import java.io.IOException;



/**
 *  异常
 * @author Jiabing
 *
 */
public class ExceptionTest {

	public static void main(String[] args) throws Exception {
		Teacher teacher = new Teacher();
		System.out.println(teacher.student.name);
		teacher.student.name="change name";
		System.out.println(teacher.student.name);
		
		new Teacher().show();
		System.out.println("111");
		
		
		try {
			System.out.println("222");
			//，这里手动抛出一个IOException，但是 try-catch时捕获时却只捕获了ClassCastException，类型不一致，程序终止
			throw new IOException("IOException");
		} catch (ClassCastException e) {
			e.printStackTrace();
			
		}
		
		System.out.println("333");
		

	}

}

class Person {
	Person (String name){
		this.name=name;
	}
	
	void show(String name) {
		
	}
	String name;
}

 class Student extends Person{
	
	Student(String name) {
		super(name);
		
	}

	@Override
	public void show(String name) {
		super.show(name);
	}
	//重，重载1
	public int show() {return 0;}
	public void test() {
		
		show(name);
		System.out.println();
	}
}

class Teacher {
	final Student student= new Student("aaa") ;
	void show() {
		System.out.println(new Person("LOL").name);
	}
}
