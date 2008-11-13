package com.ntsky.bbs.util.config;

import java.util.Map;
import com.ntsky.bbs.Symbols;

/**
 * 注册配置
 * 
 * @author Administrator
 *
 */
public class RegisterConfig {
	
	public static Map getPropertyMap(){
		return SystemConfig.getInstance().getPropertyMap(Symbols.REGISTER);
	}
	
	/**
	 * 取得属性的值
	 * @param key 属性标示
	 * @return 属性值 
	 */
	public static String getPropertyValue(String key){
		return (String)getPropertyMap().get(key);
	}
	
}
