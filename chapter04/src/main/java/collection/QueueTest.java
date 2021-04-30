package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<>();

		queue.offer("마이콜");
		queue.offer("또치");
		queue.offer("둘리");
		
		while (!queue.isEmpty()) {
			String s = queue.poll();
			System.out.println(s);
		}
		
		queue.offer("마이콜");
		queue.offer("또치");
		queue.offer("둘리");
		
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		System.out.println(queue.poll());
	}
}
