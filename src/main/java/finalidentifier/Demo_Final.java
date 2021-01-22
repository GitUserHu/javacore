package finalidentifier;
/**
 * @author JiaBing
 * @date 2019-01-17 17:03:32 
 */
public class Demo_Final {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FinalObejct f = new FinalObejct();
		System.out.println(FinalObejct.f.x);
	}

}

class FinalObejct{
	final int x, y;
	static FinalObejct f;
	public FinalObejct(){
		f = this;
		x = 10;
		y = 20;
	}
}
