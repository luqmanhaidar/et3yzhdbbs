package com.ntsky.bbs.web.webwork.action.message;

import java.net.URLDecoder;
import java.util.List;

import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.opensymphony.xwork.ModelDriven;

/**
 * 修改消息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.3 $ $Date: 2008/10/26 10:00:47 $
 */
public class CreateMessageAction extends MessageActionSupport implements ModelDriven{

	private Message message = new Message();
	

	private List announcements;
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private AnnouncementService announcementService=(AnnouncementService)bf.getBean("announcementService");
	public List getAnnouncements() {
		return announcements;
	}
	
	private List forums;

	public List getForums(){
		return forums;
	}
	
	/**
	 * 修改消息
	 */
	public String execute() throws Exception {

		announcements = announcementService.getAnnouncements(-1, 0);
		forums = ForumSingleton.getInstance().getIndexForums();
		if(logger.isInfoEnabled()){
			logger.info("添加的消息 ['"+message.getTitle()+"'] ");
		}
		try{
			message.setSender(super.getSessionUser().getUsername());
			messageService.createMessage(message);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.message;
	}
	
	public String doDefault() throws Exception {
		String rec=super.getParameter("receiver");
		String receiver=new String(rec.getBytes("iso-8859-1"));
		message.setReceiver(receiver);
		return SUCCESS;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
