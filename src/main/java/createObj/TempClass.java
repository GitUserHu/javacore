package createObj;
/**
 * @author JiaBing
 * @date 2019-04-06 15:24:27
 *   TempClass中不提供类构造器，使用静态工厂方法返回实例 
 */
public class TempClass {
	public static TempClass  buildTempClass() {
		return TempClassFactory.getTempClassInstance();
	}
} 

class TempClassFactory{
	private static TempClass temp = new TempClass();
	
	public static TempClass getTempClassInstance() {
		return temp;
	}
}