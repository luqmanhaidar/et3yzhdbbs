package com.ntsky.bbs;

import java.net.URLEncoder;
import java.net.URLDecoder;

public class GeneralTest extends NTskyTest{

	public void testEqual() throws Exception {
		int i = -1;
		System.out.println("i boolean : " + (i==-1));
	}
	
	public void testURLEncode() throws Exception {
		System.out.println(URLEncoder.encode("姚君林")); 
		System.out.println(URLEncoder.encode("ntsky")); 
		System.out.println(URLDecoder.decode("%D2%A6%BE%FD%C1%D6")); 
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(GeneralTest.class);
	}	
}
