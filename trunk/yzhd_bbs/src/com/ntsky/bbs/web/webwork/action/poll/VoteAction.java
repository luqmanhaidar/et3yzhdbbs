package com.ntsky.bbs.web.webwork.action.poll;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.PollVoter;
import com.ntsky.bbs.exception.PollException;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 投票
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public class VoteAction extends PollActionSupport {
	
	private int pollId;
	public int getPollId() {
		return pollId;
	}
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}
	
	private int topicId;
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	// 投票
	private Poll poll;
	public Poll getPoll(){
		return this.poll;
	}
	
	/**
	 * 投票
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("用户投票....");
		}
		
		// ---------- 权限 ------------
		if(super.isAccess("canVote")==0){
			setWarnMessage("您没有投票的权限.");
			return NO_LOGIN;
		}
		// ---------------------------
		
		PollVoter pollVoter = new PollVoter();
		pollVoter.setPollId(getPollId());
		pollVoter.setPollResultId(id);
		pollVoter.setPollUser(super.getSessionUser().getUsername());
		pollVoter.setPollIp(super.getRemoteAddr());
		try{			
			pollResultService.vote(pollVoter);
			super.setActionMessage("投票成功!");
			poll = pollService.findPoll(topicId);
			if(logger.isInfoEnabled()){
				logger.info("投票成功...");
			}		
		}
		catch(PollException pe){
			//System.out.println(pe.getMessage()+" ==============================");
			super.setWarnMessage(pe.getMessage());
		}
		catch(ServiceException se){
			//throw new ActionException(se);
		}
		return SUCCESS;
	}
	
}
