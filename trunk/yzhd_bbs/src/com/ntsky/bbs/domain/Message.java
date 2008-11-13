package com.ntsky.bbs.domain;

import java.util.Date;

public class Message extends Entity{

	private String sender;
	private String receiver;
	private String title;
	private String content;
	private Date sendTime;
	private int flag;
	private int isRead;
	private int isDelSender;
	private int isDelReceiver;	
	private int status;
	
	public int getIsDelReceiver() {
		return isDelReceiver;
	}
	public void setIsDelReceiver(int isDelReceiver) {
		this.isDelReceiver = isDelReceiver;
	}
	public int getIsDelSender() {
		return isDelSender;
	}
	public void setIsDelSender(int isDelSender) {
		this.isDelSender = isDelSender;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
