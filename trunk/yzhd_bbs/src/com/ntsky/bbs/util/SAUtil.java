package com.ntsky.bbs.util;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.security.Base64;
import com.ntsky.framework.util.security.SHA1;
import com.ntsky.framework.util.security.MD5;

import com.ntsky.bbs.util.Application;

/**
 * signing authority(签字权)
 * 网站安全工具类。
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class SAUtil {
	
	private final static Logger logger = Logger.getLogger(SAUtil.class.getName());
	
	/**
	 * 生成校验码
	 * 
	 * @param data 原始数据
	 * @return string 校验码
	 */
	public static String generateSig(String data){
		// 系统信息
		Application application = Application.getInstance();
		// 数据和系统设定的加密串字符
		StringBuffer sb = new StringBuffer();
		sb.append(encode(data));
		sb.append(application.getSecurity("system"));
		//sb.append("A_ntsky*&_+@toblog");
		/*if(logger.isDebugEnabled()){
			logger.debug("sig : " + MD5.md5(sb.toString()));
		}*/
		return MD5.md5(sb.toString());
	}
	
	/**
	 * 解密Base64加密后的数据
	 * @param base64Data base64加密后的数据
	 * @return
	 */
	public static String decode(String base64Data){
		return Base64.decode(base64Data);
	}
	
	/**
	 * 使用Base64加密数据
	 * @param data 数据
	 * @return 加密后的数据
	 */
	public static String encode(String data){
		return Base64.encode(data);
	}
	
	/**
	 * 生成加密的URL
	 * @param data 原始的URL 例如:rp=1&id=5
	 * @return
	 */
	public static String generateURL(String data){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("data=");
		stringBuffer.append(encode(data));
		stringBuffer.append("&");
		stringBuffer.append("sig=");
		stringBuffer.append(generateSig(data));
		return stringBuffer.toString();
	}
	
	/**
	 * 校验URL是否合法
	 * @param sig 校验串
	 * @param base64Data base64数据
	 * @return boolean 是否合法
	 */
	public static boolean checkSig(String sig,String base64Data){
		if((sig==null) || ("".equals(sig))) return false;
		return sig.equals(generateSig(decode(base64Data)))?true:false;
	}
	
	public static void main(String[] args) {
		System.out.println("decode : " + decode("cnA9M18y"));
		System.out.println(generateSig(decode("cnA9M18y")));
		//data=&sig=b0241ffe1ab555bc1339064fecc870d1
		
		System.out.println(checkSig("b0241ffe1ab555bc1339064fecc870d1","cnA9M18y"));
	}
}
