package com.ntsky.bbs.domain;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ntsky.bbs.util.memory.LevelSingleton;

public class User extends Entity{

	private String username;
	private String password;
	private int sex;
	private String email;
	private String question;
	private String answer;
	private String roles;
	private Date registerTime;
	private int loginTimes;
	private Date lastLoginTime;
	private String lastLoginIp;
	private int isLock;
	private int isStar;
	private String birthday;
	private String website;
	private String imQq;
	private String imMsn;
	private String imIcq;
	private String imYahoo;
	private String gmail;
	private String address;
	private String face;
	private String signature;
	private String intro; 
	private int money;
	private int experience;
	private int integral;
	private int totalTopic;
	private int totalPost;
	private int onlineTime;
	private String alias;
	
	private Level level;
	
	//头像
	private UserFace userFace;
	private List indexTopics;
	
	private Role role;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getTotalTopic() {
		return totalTopic;
	}
	public void setTotalTopic(int totalTopic) {
		this.totalTopic = totalTopic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getImIcq() {
		return imIcq;
	}
	public void setImIcq(String imIcq) {
		this.imIcq = imIcq;
	}
	public String getImMsn() {
		return imMsn;
	}
	public void setImMsn(String imMsn) {
		this.imMsn = imMsn;
	}
	public String getImQq() {
		return imQq;
	}
	public void setImQq(String imQq) {
		this.imQq = imQq;
	}
	public String getImYahoo() {
		return imYahoo;
	}
	public void setImYahoo(String imYahoo) {
		this.imYahoo = imYahoo;
	}
	public int getIsLock() {
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getIsStar() {
		return isStar;
	}
	public void setIsStar(int isStar) {
		this.isStar = isStar;
	}
	public UserFace getUserFace() {
		return userFace;
	}
	public void setUserFace(UserFace userFace) {
		this.userFace = userFace;
	}
	public List getIndexTopics() {
		return indexTopics;
	}
	public void setIndexTopics(List indexTopics) {
		this.indexTopics = indexTopics;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Level getLevel() {
		List levels=LevelSingleton.getInstance().getLevels();
		Iterator it=levels.iterator();
		
		Level temp=new Level();
		
		while(it.hasNext()){
			Level t=(Level)it.next();
			if(this.money>=t.getMoney()){
				temp=t;
			}else{
				break;
			}			
		}		
		
		return temp;
	}
	public void setLevel(Level level) {
		this.level = level;
	}		
}
