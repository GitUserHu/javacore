package interfaceAsRef;
/**
 * @author JiaBing
 * @date 2019-04-29 22:59:39 
 */
public class Action implements Function{

	@Override
	public void create() {}

	@Override
	public void delete() {}

	@Override
	public void update() {}
	
	public void otherAction() {};
	
	public static void main(String[] args) {
		/**
		  *  这里将对象声明 为Function引用， 因此保证使用这个对象时，只能调用Function接口中声明的方法，当后续的版本中需要修改为Function的其他实现类的时候
		  *  也只需要修改 1 处的 Code， 其他对于action这个对象的所有操作都不用修改。这就是接口引用对象的好处。
		 */
		Function action = new Action(); // 1    ->  Function action = new ActionOther();
		action.update();
		action.delete();
		
		
		/*但是， 但是： 如果要调用Action对于Function接口中声明之外的方法: otherAction()，就只有通过Action来声明引用*/
		Action action2 = new Action();
		action2.otherAction();
		
	}
}

class ActionOther implements Function{

	@Override
	public void create() {}

	@Override
	public void delete() {}

	@Override
	public void update() {}
	
}