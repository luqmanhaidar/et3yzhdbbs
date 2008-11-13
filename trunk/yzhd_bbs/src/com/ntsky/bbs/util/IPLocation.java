package com.ntsky.bbs.util;

import com.ntsky.framework.util.lumaqq.IPSeeker;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.util.Application;

/**
 * IP位置查询
 * @author Administrator
 *
 */
public class IPLocation {

	public final static String IP_DATA_PATH = "WEB-INF/lib/QQWry.dat";
	
	/**
	 * 取得IP所在的位置
	 * @param ip ip地址
	 * @return 物理地址
	 */
	public static String getLocation(String ip){
		IPSeeker ipSeeker = IPSeeker.getInstance(StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),IP_DATA_PATH));
		return ipSeeker.getCountry(ip)+ipSeeker.getArea(ip);
	}
	
}
