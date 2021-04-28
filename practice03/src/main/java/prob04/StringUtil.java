package prob04;

public class StringUtil {
	public static String concatenate(String[] str) {
		String temp = "";
		
		for (int i = 0; i < str.length; i++) {
			temp += str[i];
		}
		
		return temp;
	}
}
