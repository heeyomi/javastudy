package prob5;

public class Prob5 {

	public static void main(String[] args) {
		int num = 1;

		while (true) {
			if (num == 100) {
				break;
			}
			if (num < 10) {
				if (num % 3 == 0) {
					System.out.println(num + " 짝");
				}
			} else {
				int ten = num/10;
				int one = num%10;
				int cnt = 0;
				
				if (ten % 3 == 0) {
					cnt++;
				}
				
				if (one!=0 && one%3 ==0) {
					cnt++;
				}
				
				if (cnt != 0) {
					System.out.print(num + " ");
					for (int i = 0; i < cnt; i++) {
						System.out.print("짝");
					}
					System.out.println();
				}
				cnt = 0;
			}
			num++;
		}
	}
}
