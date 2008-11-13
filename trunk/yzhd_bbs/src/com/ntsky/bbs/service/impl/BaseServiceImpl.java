package com.ntsky.bbs.service.impl;

import java.lang.reflect.Field;

import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.reflect.ReflectUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.dao.UserDAO;
import com.ntsky.bbs.service.BaseService;

public class BaseServiceImpl implements BaseService{
	
	protected UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	/**
	 * 建造Script数据
	 * @param data
	 * @return
	 */
	private String buildData(String data){
		return Symbols.SCRIPT_INC + data + Symbols.SCRIPT_INC + Symbols.SCRIPT_COMMA;
	}
	
	/**
	 * 构造scritpt最后一个字段的数据
	 * @param data
	 * @return
	 */
	private String buildLastData(String data){
		return Symbols.SCRIPT_INC + data + Symbols.SCRIPT_INC;
	}
	
	/**
	 * 生成JS数据
	 * 
	 * @param object 数据对象
	 * @param stringBuffer js字符
	 * @param functionName js函数名
	 * @param typeArray 生成js的类型集合
	 */
	protected void makeJsData(Object object,StringBuffer stringBuffer,String functionName,String[] typeArray){
		Field[] fileds = object.getClass().getDeclaredFields();
		String fieldStr = "";
		stringBuffer.append(functionName+Symbols.SCRIPT_LEFT);
		// 顶层Class
		// ID信息
		Field[] superFileds = object.getClass().getSuperclass().getDeclaredFields();
		stringBuffer.append(buildData(ReflectUtil.getGetter(
				object.getClass().getSuperclass(), superFileds[0].getName())
				.get(object).toString()));
		// 本类数据
		int temp = 0;
		for (int i = 0; i < fileds.length; i++) {
			if(StringUtil.isArrayContainString(typeArray,fileds[i].getName())){
				fieldStr = ReflectUtil.getGetter(
						object.getClass(), fileds[i].getName())
						.get(object).toString();
				temp ++ ;
				// 生成不带","的数据
				if(temp==(typeArray.length)){
					stringBuffer.append(buildLastData(fieldStr));
				}
				else{
					stringBuffer.append(buildData(fieldStr));
				}
			}
		}
		stringBuffer.append(Symbols.SCRIPT_RIGHT);
	}
	
}
