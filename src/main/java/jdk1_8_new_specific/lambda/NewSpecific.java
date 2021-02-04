package jdk1_8_new_specific.lambda;

public class NewSpecific<Integer> implements FunctionalInterfaceDemo<Integer>{

	public static void main(String[] args) {
		NewSpecific<Object> test = new NewSpecific<>();
		test.addNewFunction("aaaaa");
		display("Jim", 20);
		FunctionalInterfaceDemo.staticFunction();

	}
	/**
	 * 匿名内部类中方法使用局部参数不能修改。
	 * @param name
	 * @param age
	 */
	public static void display( String name, int age) {
		String name1="TOM";
		int age1=30;
		
		class InnerClass{
			void display() {
				//name1="JIM";   // 匿名内部类中方法使用局部参数不能修改, 编译不通过
				System.out.println(name1 + " is "+ age1+" old.");
				
			}
		}
		new InnerClass().display();
	}
	@Override
	public void display(Integer t) {
		System.out.println("NewSpecific Class Implements Interface NewSpecificTestInterface. Implements display() function");
		
	}
//	@Override
//	public void addNewFunction(String newFunction) {
//	
//		System.out.println("addNewFunction in a class");
//	}


}
