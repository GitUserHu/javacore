package stack_heap;

import java.util.Iterator;
import java.util.Stack;

public class Test_Stack {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		Iterator<String> iterator = stack.iterator();
		while(iterator.hasNext()) {
			System.out.println(stack.pop());
		}
		/*for(String string : stack) {
			System.out.println(string);
		}*/
		
		
	}

}
