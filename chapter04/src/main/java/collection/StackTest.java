package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		
		stack.push("둘리");
		stack.push("또치");
		stack.push("도우넛");
		
		while (!stack.isEmpty()) {
			String string = stack.pop();
			System.out.println(string);
		}
		
		// 비어있는 경우에는 예외 발생
		// stack.pop();

		
		stack.push("둘리");
		stack.push("또치");
		stack.push("도우넛");
		
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		
	}

}
