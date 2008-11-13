package com.ntsky.bbs.web.webwork.action.poll;

import java.util.List;

import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

import com.ntsky.bbs.service.PollService;
import com.ntsky.bbs.service.PollResultService;
/**
 * 投票
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public abstract class PollActionSupport extends BasicActionSupport{
	
	// 投票信息
	protected PollService pollService;
	public void setPollService(PollService pollService){
		this.pollService = pollService;
	}

	// 投票结果
	protected PollResultService pollResultService;
	public void setPollResultService(PollResultService pollResultService){
		this.pollResultService = pollResultService;
	}
	
}
