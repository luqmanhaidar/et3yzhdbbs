package com.ntsky.bbs.util.config;

import java.util.Map;

public class SystemConfig {
	
	protected static Map datasMap = null;
	
	private static SystemConfig systemConfig = null;
	
	public synchronized static SystemConfig getInstance(){
		if(systemConfig==null){
			systemConfig = new SystemConfig();
		}
		return systemConfig;
	}
	
	/**
	 * 设置数据
	 * @param map
	 */
	public void setDatas(Map map){
		datasMap = map;
	}
	
	/**
	 * 设置数据
	 * @param dataId 数据编号
	 * @param propertyMap 属性Map
	 */
	public void setData(String dataId,Map propertyMap){
		DataUtil.setData(datasMap,dataId,propertyMap);
	}
	
	/**
	 * 根据ID,取得属性Map
	 * @param dataId 数据编号
	 * @return 数据数组
	 */
	public Map getPropertyMap(String dataId){
		return DataUtil.getPropertyMap(datasMap,dataId);
	}
	
	/**
	 * 根据编号和属性Key取得属性的值
	 * 
	 * @param dataMap 数据集合
	 * @param dataId 数据编号
	 * @param propertyKey 属性
	 * @return String 属性值
	 */
	public String getPropertyValue(String dataId,String key){
		return DataUtil.getPropertyValue(datasMap,dataId,key);
	}
	
	public int getIntPropertyValue(String dataId,String key){
		return Integer.parseInt(getPropertyValue(dataId,key));
	}
	
	/**
	 * 设置新值
	 * @param dataId
	 * @param key
	 * @param value
	 */
	public void setPropertyValue(String dataId,String key,String value){
		getPropertyMap(dataId).put(key,value);
	}
	
}
