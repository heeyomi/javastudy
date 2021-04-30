package prob01;

public class Printer {

//	public void println(int num) {
//		System.out.println(num);
//	}
//
//	public void println(String message) {
//		System.out.println(message);
//	}
//
//	public void println(Double num) {
//		System.out.println(num);
//	}
//
//	public void println(boolean flag) {
//		System.out.println(flag);
//	}
	
	//return 타입이 아니라 파라미터가 제네릭이다
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	public <T> void println(T... ts) {
		for (T t : ts) {
			System.out.print(t);
			System.out.print(" ");
		}
		System.out.println("");
	}

	public int sum(Integer... nums) {
		Integer sum = 0;
		for (Integer i : nums) {
			sum+=i;
		}
		return sum;
	}
}
