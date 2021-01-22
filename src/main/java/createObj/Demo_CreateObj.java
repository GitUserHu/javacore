package createObj;
/**
 * @author JiaBing
 * @date 2019-04-06 15:23:53 
 */
public class Demo_CreateObj {

	public static void main(String[] args) {
		
		String str1 = "aaa";
		String str2 = "aaa";
		String str3 = new String("aaa");
		String str4 = new String("aaa");
		System.out.println(str1==str2);
		System.out.println(str1 == str3);
		System.out.println(str1.equals(str3));
		System.out.println(str3==str4);
		
		TempB tempb = (TempB) new TempA();
		TempA tempa = new TempB();
	}

}

class TempA {
	
}
class TempB extends TempA{
	
}


