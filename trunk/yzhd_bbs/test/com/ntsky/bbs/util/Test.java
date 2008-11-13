package com.ntsky.bbs.util;

public class Test {
	public static void main(String[] args){
		/*String content="三多地方第三方地方第三方第三<img height=\"120\" width=\"120\" alt=\"\" src=\"/yzhd_bbs/UserFiles/4/Image/33(1).gif\" />三大幅洒";
		String con[]=content.split("\"");
		for(int i=0;i<con.length;i++){
			if(con[i].trim().indexOf("<img")!=-1){
				System.out.println(con[i+7]);
			}
		}
		for(int i=0;i<con.length;i++){
			System.out.println("con["+i+"]="+con[i]);
		}*/
		
		String str="阿斯顿佛，手多福多寿是打发斯蒂芬动阀。。：：“大幅度”";
		str=str.replaceAll("[，。“”：‘]", "");
		int length=str.length();
		
		int index=2;
		String arr[]=new String[length-1];
		for(int i=2;i<=length;i++){
			arr[i-2]=str.substring(i-2, i);
			System.out.println(arr[i-2]);
		}
		
		
	}
}
