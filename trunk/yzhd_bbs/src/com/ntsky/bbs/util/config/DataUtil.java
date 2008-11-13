package com.ntsky.bbs.util.config;

import java.util.Map;

public final class DataUtil {
	
	/**
	 * 根据Data编号从DataMap中取得数据
	 * @param dataMap 数据集合
	 * @param dataId data编号
	 * @return 属性集合
	 */
	public static Map getPropertyMap(Map dataMap,String dataId){
		return (Map)dataMap.get(dataId);
	}
	
	/**
	 * 设置dataId对应的Map信息
	 * 
	 * @param dataId 数据标记
	 * @param propertyMap 属性Map
	 */
	public static void setData(Map dataMap,String dataId,Map propertyMap){
		// 移除
		dataMap.remove(dataId);
		// 重新设置
		dataMap.put(dataId,propertyMap);
	}
	
	/**
	 * 根据编号和属性Key取得属性的值
	 * 
	 * @param dataMap 数据集合
	 * @param dataId 数据编号
	 * @param propertyKey 属性
	 * @return 属性值
	 */
	public static String getPropertyValue(Map dataMap,String dataId,String key){
		return (String)getPropertyMap(dataMap,dataId).get(key);
	}
	
}
