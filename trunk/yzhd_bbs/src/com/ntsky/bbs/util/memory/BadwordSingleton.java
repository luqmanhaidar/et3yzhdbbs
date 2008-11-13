package com.ntsky.bbs.util.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.xml.bean.Badword;

public class BadwordSingleton {

	public List badwords = new ArrayList();
	
	private static BadwordSingleton badwordInstance = null;
	
	public synchronized static BadwordSingleton getInstance(){
		if(badwordInstance==null){
			badwordInstance = new BadwordSingleton();
		}
		return badwordInstance;
	}
	
	// 设置过滤文字
	public void setBadwords(List list){
		badwords = list;
	}
	
	// 取得过滤文字列表
	public List getBadwords(){
		return badwords;
	}
	
	/**
	 * 添加过滤文字
	 * @param Badword
	 */
	public void addBadword(Badword badword){
		badwords.add(badword);
	}
	
	/**
	 * 删除过滤的文字
	 *
	 */
	public List deleteBadword(int id){
		Badword badword = null;
		for (int i = 0; i < badwords.size(); i++) {
			badword = (Badword)badwords.get(i);
			if(id == badword.getId()){
				badwords.remove(i);
			}
		}
		return badwords;
	}
	
	/**
	 * 更新
	 * @param badword 过滤文字对象
	 * @return
	 */
	public List replaceBadword(Badword badword){
		Badword tempBadword = null;
		for (int i = 0; i < badwords.size(); i++) {
			tempBadword = (Badword)badwords.get(i);
			if(badword.getId() == tempBadword.getId()){
				badwords.remove(i);
				badwords.add(i, badword);
			}
		}
		return badwords;
	}
	
	/**
	 * 格式化字符串
	 * @param str 待格式化的字符串
	 * @return 格式化后的字符
	 */
	public String replaceString(String str){
		if(str==null){
			return "";
		}
		Badword badword = null;
		for (int i = 0; i < badwords.size(); i++) {
			badword = (Badword)badwords.get(i);
			str = StringUtil.replace(str, badword.getOldStr(), badword.getReplaceStr());
		}
		return str;
	}
	
}
