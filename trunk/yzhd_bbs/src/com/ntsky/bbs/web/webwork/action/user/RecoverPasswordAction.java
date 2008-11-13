package com.ntsky.bbs.web.webwork.action.user;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.exception.UserException;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.framework.mail.HtmlMail;
import com.ntsky.framework.mail.Mail;
import com.ntsky.framework.mail.Sender;
import com.ntsky.framework.util.HttpUtil;

/**
 * 取回密码
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class RecoverPasswordAction extends UserActionSupport {

	private String username;
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	
	private String vCode;
	public void setVCode(String vCode){
		this.vCode = vCode;
	}
	
	private User user;
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}
	
	/*private String question;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}*/
	/**
	 * 取回密码(检测用户名)
	 * <p>
	 * 	如果用户名不存在,返回到INPUT页面
	 *  存在的话返回到提示问题页面
	 * </p>
	 * @return 
	 */
	public String doCheckUsername() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("待检测的用户名 : " + username);
		}
		String sessionVCode = (String)HttpUtil.getAttribute(getRequest().getSession(),"vCode"); 
		sessionVCode = (sessionVCode==null)? "" : sessionVCode;
		// 检测验证码是否正确
		if(!sessionVCode.equals(vCode)){
			logger.warn("错误验证码 : " + vCode);
			setWarnMessage("错误验证码["+vCode+"]");
			return INPUT;
		}
		User user = null;
		try {
			user = userService.getUser(username);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		if(user==null){
			logger.warn("用户["+username+"]不存在");
			super.setWarnMessage("该用户["+username+"]不存在!");
			return INPUT;
		}
		else{
			// 设置用户信息到回答问题页面
			setUser(user);
		}
		return SUCCESS;
	}
	
	
	
	// 第二步输入的答案
	private String answer;
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	// 是否发送EMAIL到用户邮箱
	private String isSendMail;
	public void setIsSendMail(String isSendMail){
		this.isSendMail = isSendMail;
	}
	public String getIsSendMail(){
		if(isSendMail==null){
			isSendMail = "false";
		}
		return isSendMail;
	}
	
	// 新密码
	private String newPassword;
	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}
	public String getNewPassword(){
		return this.newPassword;
	}
	
	/**
	 * 检测密码提示问题答案
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("用户编号 : " + this.id);
		}
		String newPassword = null;
		try{
			newPassword = super.userService.checkAnswer(id,answer);
		}
		catch(UserException ue){
			// 密码提示答案错误的情况
			setWarnMessage(ue.getMessage());
			// 取回密码页面数据
			setUser(userService.getUser(id));
			return INPUT;
		}
		catch(ServiceException se){
			logger.error(se.getMessage());
			throw new ActionException(se);
		}
		// 设置新的密码
		this.setNewPassword(newPassword);
		if(logger.isInfoEnabled()){
			logger.info("是否发送邮件 : " + getIsSendMail());
		}
		if((Symbols.TRUE).equals(getIsSendMail())){
	        Sender sender = new Sender(EmailConfig.getSmtpHost(),EmailConfig.getUserName(),EmailConfig.getPassword());
	        Mail mail = new HtmlMail();
	        mail.setFrom(EmailConfig.getSystemMail());
	        mail.setTo(SystemConfig.getInstance().getPropertyValue(Symbols.BASIC,Symbols.BASIC_MASTER_MAIL));
	        //mail.setTitle(EmailConfig.get);
	        //String content = emailSenderBean.getMailContent();
	        //sender.send(mail);
		}
		// 取回密码页面数据
		//setUser(userService.getUser(id));
		// 返回到登陆页面
		return SUCCESS;
	}
	
}
