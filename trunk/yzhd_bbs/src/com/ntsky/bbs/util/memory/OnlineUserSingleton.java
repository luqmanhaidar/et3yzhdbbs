package com.ntsky.bbs.util.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.web.listener.OnlineInfo;

import com.ntsky.bbs.util.config.SystemConfig;

public class OnlineUserSingleton {

	private Map onlineInfoMap = new HashMap();
	
	private static OnlineUserSingleton onlineUserSingleton = null;
	
	private int forumUsers;
	private int forumGuests;
	
	public int getForumGuests() {
		return forumGuests;
	}

	public void setForumGuests(int forumGuests) {
		this.forumGuests = forumGuests;
	}

	public int getForumUsers() {
		return forumUsers;
	}

	public void setForumUsers(int forumUsers) {
		this.forumUsers = forumUsers;
	}

	/**
	 * 获取角色内存实例
	 * @return
	 */
	public synchronized static OnlineUserSingleton getInstance(){
		if(onlineUserSingleton==null){
			onlineUserSingleton = new OnlineUserSingleton();
		}
		return onlineUserSingleton;
	}
	
	/**
	 * 添加在线用户
	 * @param onlineInfo
	 */
	public void addOnlineUser(OnlineInfo onlineInfo){
		onlineInfoMap.put(onlineInfo.getIpAddress(),onlineInfo);
	}
	
	 /**
	  * 取得在线用户数
	  * @return int 在线用户数
	  */
	 public int getOnlineUserSize(){
		 return onlineInfoMap.size();
	 }
	 
	 
	 
	 public Map getOnlineUserMap(){
		 return onlineInfoMap;
	 }
	 
	 /**
	  * 取得在线用户
	  * @return　List 取得在线用户Map
	  */
	 public List getOnlineUser(){
		 Object[] onlineUserArray = onlineInfoMap.values().toArray();
		 List onlineUsers = new ArrayList();
		 OnlineInfo onlineInfo = null;
		 for (int i = 0; i < onlineUserArray.length; i++) {
			 onlineInfo = (OnlineInfo)onlineUserArray[i];
			 if( !(Symbols.GUEST.equals(onlineInfo.getUsername())) ){
				 onlineUsers.add(onlineInfo);
			 }
		 }
		 return onlineUsers ;
	 }

	 /**
	  * 取得某个论坛的在线用户
	  * @return　List 取得在线用户列表
	  */
	 public List getOnlineUser(int forumId){
		 Object[] onlineUserArray = onlineInfoMap.values().toArray();
		 List onlineUsers = new ArrayList();
		 OnlineInfo onlineInfo = null;
		 for (int i = 0; i < onlineUserArray.length; i++) {
			 onlineInfo = (OnlineInfo)onlineUserArray[i];
			 if( forumId == onlineInfo.getForumId() ){
				 if( !(Symbols.GUEST.equals(onlineInfo.getUsername())) ){
					 onlineUsers.add(onlineInfo);
				 }
			 }
		 }
		 return onlineUsers ;
	 }
	 
	 
	 /**
	  * 取得某个论坛的在线游客
	  * @param forumId
	  * @return
	  */
	 public List getOnlineGuest(int forumId){
		 Object[] onlineUserArray = onlineInfoMap.values().toArray();
		 List onlineUsers = new ArrayList();
		 OnlineInfo onlineInfo = null;
		 for (int i = 0; i < onlineUserArray.length; i++) {
			 onlineInfo = (OnlineInfo)onlineUserArray[i];
			 if( forumId == onlineInfo.getForumId() ){
					 onlineUsers.add(onlineInfo);
			 }
		 }
		 return onlineUsers ;
	 }
	 
	 /**
	  * 最高访问人数
	  * @return
	  */
	 public String getHighOnlineUserNum(){
		 return SystemConfig.getInstance().getPropertyValue("online","people");
	 }
	 
	 /**
	  * 最高访问时间
	  * @return
	  */	 
	 public String getHappenTime(){
		 return SystemConfig.getInstance().getPropertyValue("online","time");
	 }
	 
}
