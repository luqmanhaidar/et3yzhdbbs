package com.ntsky.bbs;

import java.net.URLEncoder;
import java.beans.Encoder;

public class Test {
	
	private static Short a = 1;
	
	
	public static void main(String[] args) {
		System.out.println(URLEncoder.encode("的"));
		
		if(a==1){
			System.out.println("aaa");
		}
		
		String abc = "aaa";
		abc.substring(0, abc.length()-1);
		
		String aa = "123";
		System.out.println(aa.split(":")[0]);
		
		String bb = "123:";
		System.out.println(bb.split(":")[0]);
		
	}
	
}
