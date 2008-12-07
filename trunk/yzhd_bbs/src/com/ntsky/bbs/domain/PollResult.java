package com.ntsky.bbs.domain;

import java.util.Date;

/**
 * 投票结果类
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class PollResult extends Entity{

	
	private int optionId;
	private String optionText;
	private int votes; // 得票数
	
	private Poll poll;
	public Poll getPoll() {
		return poll;
	}
	public void setPoll(Poll poll) {
		this.poll = poll;
	}
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
}
