package com.ntsky.bbs.web.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.xml.DOMData;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.OnlineUserSingleton;

/**
 * 在线用户监听器
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/20 18:35:54 $
 */
public class OnlineUserListener implements HttpSessionAttributeListener{
	
	private final static Logger logger = Logger.getLogger(OnlineUserListener.class);

	// session添加
	 public void attributeAdded(HttpSessionBindingEvent se){
		 
		 if(Symbols.SESSION_ONLINE.equals(se.getName())) {
			 OnlineInfo onlineInfo = (OnlineInfo)(se.getValue());
			 if(logger.isDebugEnabled()){
				 logger.debug("attributeAdded -- 添加用户 : " + onlineInfo.getUsername() + " 访问ip : " + onlineInfo.getIpAddress());
			 }
			 
			 OnlineUserSingleton.getInstance().getOnlineUserMap().put(onlineInfo.getIpAddress(),onlineInfo);
			 
			 // 如果在线用户 > 数据中记录的最大在线用户 更新system.xml文件
			 if(OnlineUserSingleton.getInstance().getOnlineUserSize()>SystemConfig.getInstance().getIntPropertyValue("online","people")){
				 if(logger.isDebugEnabled()){
					 logger.debug("更新最高在线人数信息...");
				 }
				 String strDate = DateUtil.getDate();
				 // 更新XML
				 DOMData domData = DOMData.getInstance(Application.getInstance().getFilePath(Symbols.XML_SYSTEM_CONFIG));
				 Node dataNode = domData.selectNodeByAttribute("data", "id", "online");
				 // 更新最高人数
				 domData.updateNodeValue(dataNode, "property", "key", "people" , String.valueOf(OnlineUserSingleton.getInstance().getOnlineUserSize()));
				 domData.updateNodeValue(dataNode, "property", "key", "time" , strDate);
				 FileUtil.writeXMLByDOM(domData.getDocument(), Application.getInstance()
							.getFilePath(Symbols.XML_SYSTEM_CONFIG));
				 
				 // 更新内存
				 SystemConfig.getInstance().setPropertyValue("online","people",String.valueOf(OnlineUserSingleton.getInstance().getOnlineUserSize()));
				 SystemConfig.getInstance().setPropertyValue("online","time",strDate);
			 }
			 
		 }
	 }

	 // session移除
	 public void attributeRemoved(HttpSessionBindingEvent se){
		 if(Symbols.SESSION_ONLINE.equals(se.getName())){
			 OnlineInfo onlineInfo = (OnlineInfo)(se.getValue());
			 if(logger.isDebugEnabled()){
				 logger.debug("attributeRemoved -- 删除用户 : " + onlineInfo.getUsername() + " 访问ip : " + onlineInfo.getIpAddress());
				 //logger.debug("用户 : " +onlineInfo.getUsername()+ "的在线时长为 " + (DateUtil.getMinute(onlineInfo.getDate(),new Date())));
				 logger.debug("用户 : " +onlineInfo.getUsername()+ "的在线时长为 " + onlineInfo.getDate());
			 }
			 // 统计在线时长
			 if(logger.isDebugEnabled()){
				 logger.debug("记录用户的在线时间......");
			 }
			 if(!Symbols.GUEST.equals(onlineInfo.getUsername())){
				 try{
					 UserService userService = (UserService)BeanFactory.getInstance(Application.getInstance().getServletContext()).getBean("userService");
					 //userService.updateOnlineTime(onlineInfo.getUsername(), (DateUtil.getMinute(onlineInfo.getDate(),new Date())));
				 }
				 catch(Exception ex){
					 logger.error("记录用户在线时间发生错误.",ex);
				 }
			 }
			 OnlineUserSingleton.getInstance().getOnlineUserMap().remove(onlineInfo.getIpAddress());
		 }
	 }

	 // 替换session的值
	 public void attributeReplaced(HttpSessionBindingEvent se){
		 if(Symbols.SESSION_ONLINE.equals(se.getName())){
			 OnlineInfo onlineInfo = (OnlineInfo)(se.getValue());
			 if(logger.isDebugEnabled()){
				 logger.debug("attributeReplaced -- 在线用户 : " + onlineInfo.getUsername() + " 访问ip : " + onlineInfo.getIpAddress());
			 }
			 String sessionKey = onlineInfo.getIpAddress();
			 
			 if(OnlineUserSingleton.getInstance().getOnlineUserMap().containsKey(sessionKey)){
				 try{
					 OnlineUserSingleton.getInstance().getOnlineUserMap().remove(sessionKey);
					 OnlineUserSingleton.getInstance().getOnlineUserMap().put(sessionKey,onlineInfo);
				 }
				 catch(Exception ex){
					 logger.error("更新['"+onlineInfo.getUsername()+"']的在线信息失败");
				 }
			 }
		 }
	 }

	 
}
