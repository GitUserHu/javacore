package codingrule;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/**
 * 
 * @author JiaBing
 * @date 2018-12-20
 */
public class JavaRuleDemo {

	public static void main(String[] args) {
		// long, Long 这种数据要以L结尾，不能以 l结尾， 不然分不清 2l是2l还是21.
		Long index = 1L;// 正例
		long count = 2l;// 反例
		
		if (count == 1L) {
																													   
		}
		// 遗弃的方法一定不要使用， 被遗弃的方法肯定会有替代的方法。
		System.out.println(URLDecoder.decode("/a/b/c"));
		try {
			System.out.println(URLDecoder.decode("/a/b/c", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 所有的相同类型的包装类对象之间值的比较，全部使用 equals 方法比较。
		Integer numberA = new Integer(10);
		Integer numberB = new Integer(10);
		if (numberA.equals(numberB)) {
			System.out.println("numberA == numberB");
		}
		
		Integer countA = 10;
		Integer countB = 10;
		System.out.println(countA == countB);
 		
		Integer indexA = 129;
		Integer indexB = 129;
		System.out.println(indexA == indexB);
		System.out.println("indexA == 129 ? "+(indexA == 129));
		// 1） 【强制】所有的 POJO 类属性必须使用包装数据类型。
		// 2） 【强制】RPC 方法的返回值和参数必须使用包装数据类型。
		// 3） 【推荐】所有的局部变量使用基本数据类型
		
		// NPE问题指的是： NullPointerException
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(i);
		}
		System.out.println(sb);
		
		// ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		List<String> subList = list.subList(0, 2);
		subList.remove(0);
		System.out.println(subList);
		System.out.println(list);
		
		// 修改原集合的元素
		/*list.remove(0);
		System.out.println(list);
		System.out.println(subList);// 会出现 ConcurrentModificationException
		 */	
		// 直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[]类，若强转其它
		// 类型数组将出现 ClassCastException 错误。
		// 正例
		String[] arrFromCollection  = new String[list.size()];
		arrFromCollection =  list.toArray(arrFromCollection);
		for(String string : arrFromCollection) {
			System.out.println(string);
		}
		// 反例
		/*String[] collectionToArr = (String[]) list.toArray();*/ // list.toArray()返回值为Object[] 数组， 
															// 强转为String[] 会出现 ClassCastException
		
		//
		List<String> asList = Arrays.asList(arrFromCollection);
		
		System.err.println(asList);
		//asList.remove(0); // 修改从数组转来得集合， 运行时会出UnsupportedOperationException
		
		arrFromCollection[0] = "aa"; // 主数组的修改会直接修改 子集合的元素(理解为二者是绑定在一起的)
		System.err.println(asList);
		
		
		List<Integer> integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(20);
		integerList.add(30);
		// 不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁
		// 正例
		Iterator<Integer> iterator = integerList.iterator();
		while (iterator.hasNext()) {
			Integer item = iterator.next();
			// 删除 值为10的元素
			if(item == 30) {
				iterator.remove();
			}
			
		}
		System.err.println("after removing with iterator ->"+ integerList);
		
		
		for (Integer item : integerList) {
			// 如果这里 判断 item == 20, 能够正常remove(), 但是如果该为 判断 item == 30,运行时就会出现ConcurrentModificationException? Why?
			// 而且只有在forEach时才会出现这个异常。 for循环遍历的方式remove()或者使用iterator迭代进行remove(),不会出现这种异常。Why?
			if(item == 20) { 
				integerList.remove(item);
			}
		}
		/*for (int i = 0; i < integerList.size(); i++ ) {
			if (integerList.get(i) == 20) {
				integerList.remove(i);
			}
		}*/
		System.err.println("after removing with forEach ->"+ integerList);
		
	}

}
