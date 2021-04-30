package chapter04;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		printDate(cal);
		
		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.MONTH, 11); //month-1
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2010, 10, 10);
		cal.add(Calendar.DATE, 1000);
		printDate(cal);
		
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS= { "일", "월", "화", "수", "목", "금", "토"};
		
		
		// 년도
		int year = cal.get(Calendar.YEAR);
		
		// 월 (0~11, +1)
		int month = cal.get(Calendar.MONTH);
		
		// 요일 [1(일)~7[토])
		int days = cal.get(Calendar.DATE);
		
		// 시
		int hours = cal.get(Calendar.HOUR);
		
		// 분
		int minute = cal.get(cal.MINUTE);
		
		int seconds = cal.get(Calendar.SECOND);
		
		System.out.println(year + "-" + month + "-" + days + " " + hours + ":" + minute + ":" +seconds);

				
	}
}
