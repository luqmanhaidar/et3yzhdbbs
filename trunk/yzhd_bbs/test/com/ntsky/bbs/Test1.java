package com.ntsky.bbs;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

	private static Test1 test = null;

	/**
	 * 取得统计数据实例
	 * @return
	 */
	public synchronized static Test1 getInstance(){
		if(test==null){
			test = new Test1();
		}
		return test;
	}
	
	private List list = new ArrayList();
	public void addList(){
		list.add("1");
		list.add("2");
		for (int i = 0; i < 10000; i++) {
			list.add("aaaaaaaaaaaaaaaaaaaaaa");
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tt");
	}
	
	public void print(){
		System.out.println("aaa");
	}
	
	public static void main(String[] args) {
		Test1.getInstance().addList();
		Test1.getInstance().print();
		
	}
	
}
