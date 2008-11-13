package com.ntsky.bbs.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 投票类
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class Poll extends Entity{

	private int topicId;
	private String content;
	private Date dateCreated;
	
	private Set pollResults;	
	public Set getPollResults() {
		return pollResults;
	}
	public void setPollResults(Set pollResults) {
		this.pollResults = pollResults;
	}
	
	public void addPollResult(PollResult pollResult) {
		pollResult.setPoll(this);
		pollResults.add(pollResult);
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	
	
}
