package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		String s = "aBcABCabcAbC";
		
		System.out.println(s.length());
		System.out.println(s.charAt(2));
		System.out.println(s.indexOf("abc"));
		System.out.println(s.indexOf("abc", 3));
		System.out.println(s.indexOf("abc", 7)); // 못 찾으면 -1
		System.out.println(s.substring(3));
		System.out.println(s.substring(3,5)); //index-1만큼 가져옴

		
		String s2 = "         ab            cd             ";
		String s3 = "efg,hijk,lmn,opqr";
		
		String s4 = s2.concat(s3);
		System.out.println(s4);
		
		System.out.println("-------"+s2.trim()+"-------"); //문자열의 앞 뒤의 공백만 없애줌
		System.out.println("-------" + s2.replaceAll(" ", "")+"-------");
		
		// 정규 표현식 사용 예 regular expression
		String p = "1000";
		if (p.matches("\\d+")) {
			int price = Integer.parseInt(p); 
			System.out.println(price);
		} else {
			System.out.println("유효한 금액이 아닙니다.");
		}
		
		String[] tokens1 = s3.split(",");
		for(String token : tokens1) {
			System.out.println(token);
		}
		
		String[] tokens2 = s3.split(" ");
		for(String token : tokens2) {
			System.out.println(token);
		}
		
		StringBuffer sb = new StringBuffer("");
		sb.append("Hello ");
		sb.append("World ");
		sb.append("Java ");
		sb.append(1.8);
		
		String s5 = sb.toString();
		System.out.println(s5);
		
		//String s6 = "Hello " + "World " + "Java " + 1.8;
		String s6 = new StringBuffer("Hello ").append("World ").append("Java ").append(1.8).toString();
		System.out.println(s6);
		
		//주의 : 문자열 + 연산을 하지 말아야 할 때
		//String s7 = "";
		//for (int i = 0; i < 1000000; i++) {
			//s7 += i;
			//s7 = new StringBuffer(s7).append(i).toString();
		//}
		
		StringBuffer sb2 = new StringBuffer("");
		for (int i = 0; i < 1000000; i++) {
			sb2.append(i);
		}
		String s7 = sb2.toString();
		System.out.println(s7.length());
	}

}
