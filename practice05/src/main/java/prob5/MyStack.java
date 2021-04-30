package prob5;

public class MyStack {

	private String[] stack;
	private int top = -1;
	
	public MyStack(int size) {
		stack = new String[size];
	}

	public void push(String string) {
		if (stack.length -1 == top) {
			resize();
		}
		
		stack[++top] = string;
	}
	
	public void resize() {
		String[] temp = new String[stack.length * 2];
		
		for (int i = 0; i < top; i++) {
			temp[i] = stack[i];
		}
		
		stack = temp;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public String pop() throws MyStackException{
		if (isEmpty()) {
			throw new MyStackException("Stack is Empty");
		}
		
		String result = stack[top];
		stack[top--] = null;
		
		return result;
	}
	
}