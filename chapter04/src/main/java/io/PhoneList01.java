package io;

import java.io.File;

public class PhoneList01 {
	
	public static void main(String[] args) {
		File file = new File("phone.txt");
		
		if (!file.exists()) {
			System.out.println("file not found");
			return;
		}
		
		System.out.println(file.length() + "bytes");
	}

}
