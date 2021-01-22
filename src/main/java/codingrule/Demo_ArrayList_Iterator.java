package codingrule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo_ArrayList_Iterator {

	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>(); // 1
		strs.add("aaa"); // 2
		strs.add("bbb"); // 3
		strs.add("ccc"); // 4
		Iterator<String> iterator = strs.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if ("aaa".equals(item)) {
				iterator.remove();
			}
		}

	}

}
