package com.ntsky.bbs.domain;

import java.util.Date;

/**
 * 投票用户类
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class PollVoter extends Entity{

	private int pollId;
	private int pollResultId;
	private String pollUser;
	private String pollIp;
	
	public int getPollId() {
		return pollId;
	}
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}
	public String getPollIp() {
		return pollIp;
	}
	public void setPollIp(String pollIp) {
		this.pollIp = pollIp;
	}
	public int getPollResultId() {
		return pollResultId;
	}
	public void setPollResultId(int pollResultId) {
		this.pollResultId = pollResultId;
	}
	public String getPollUser() {
		return pollUser;
	}
	public void setPollUser(String pollUser) {
		this.pollUser = pollUser;
	}
	
	
}
