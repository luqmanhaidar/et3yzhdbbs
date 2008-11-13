package com.ntsky.bbs.web.webwork.action.user;

import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.mail.HtmlMail;
import com.ntsky.framework.mail.Mail;
import com.ntsky.framework.mail.MailException;
import com.ntsky.framework.mail.Sender;
import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Message;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.MailSenderException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.MessageService;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.WebworkUtil;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.RegisterConfig;

/**
 * 用户注册
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/10/23 16:48:20 $
 */
public class SignupAction extends UserActionSupport implements ModelDriven,SessionAware{

	private User user = new User();
	
	 private String vCode;
	
	private MessageService messageService;
	
	public void setMessageService(MessageService messageService){
		this.messageService = messageService;
	}
	
	/**
	 * 用户注册
	 * <p>
	 * 	注册成功发送信息邮件
	 * </p>
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("注册用户的用户名 : " + user.getUsername());
		}
		try{
			String password = user.getPassword();
			String answer = user.getAnswer();
			user.setFace("images/face/default.gif");
			User tempUser = super.userService.getUser(user.getUsername());
			
			if(!HttpUtil.getAttribute(getRequest().getSession(),"vCode").equals(vCode)){
				logger.warn("错误验证码 : " + vCode);
				setWarnMessage("错误验证码["+vCode+"]");
				return INPUT;
			}
			
			// 该用户不存在，可以注册
			if(tempUser==null){
				user.setLastLoginIp(HttpUtil.getRemoteAddr(super.getRequest()));
				user = (User)BeanUtil.format(user);
				userService.signupUser(user);	
				session.put(Symbols.SESSION_USER,user);
				
				// 注册后就设置Fckeditor的cookie(原因 ： IE不同版本的情况下，Fckediotr中Session传值有问题)
				HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_FCKEDITOR,user.getId().toString(),-1);
				
				// 判断是否需要发送欢迎信息
				if((Symbols.TRUE).equals(RegisterConfig.getPropertyValue("isWelcome"))){
					// 发送欢迎信息
					Message message = new Message();
					message.setSender("admin");
					message.setReceiver(user.getUsername());
					message.setTitle("欢迎信息!");
					message.setContent(RegisterConfig.getPropertyValue("welcomeInfo"));
					message.setStatus(1);
					messageService.createMessage(message);
				}
			}
			else{
				setActionMessage("用户["+user.getUsername()+"]已存在,请选择其它用户名");
				return INPUT;
			}
			
			// 注册成功发送欢迎邮件
		   /* Sender sender = new Sender(EmailConfig.getSmtpHost(), EmailConfig
					.getUserName(), EmailConfig.getPassword());
			Mail mail = new HtmlMail();
			mail.setFrom(EmailConfig.getSystemMail());
			mail.setTo(user.getEmail());
			mail.setTitle(EmailConfig.getPropertyValue(Symbols.MAIL_REGISTER,Symbols.MAIL_SUBJECT));
			// 内容
			String content = EmailConfig.getPropertyValue(Symbols.MAIL_REGISTER,Symbols.MAIL_TEXT);
			content = StringUtil.replace(content,"$username",user.getUsername());
			content = StringUtil.replace(content,"$password",password);
			content = StringUtil.replace(content,"$question",user.getQuestion());
			content = StringUtil.replace(content,"$answer",answer);
			mail.setContent(content);
			sender.send(mail);	*/
		}
		catch(MailException mse){
			logger.error("mail exception info is : \r\n", mse);
			super.setWarnMessage("<span style=\"color:red\">注册成功</span>，但是系统发送欢迎邮件失败!");
			return WARN;
		}		
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.user;
	}
	
	private String isRead;
	public String getIsRead() {
		if(isRead==null){
			isRead = "false";
		}
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	/**
	 * 检测用户是否存在
	 * 
	 * @return string 检测结果 
	 * @throws Exception 
	 */
	public String checkUser() throws Exception{
		String username = WebworkUtil.getParameter("username");
		try{
			User user = super.userService.getUser(username);
			if(user==null){
				setActionMessage("用户["+username+"]可以注册");
			}
			else{
				setActionMessage("用户["+username+"]已存在,请选择其它用户名");
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/**
	 * 打开注册信息
	 * @return 
	 * @throws Exception
	 */
	public String doOpen() throws Exception{
		
		if(logger.isInfoEnabled()){
			logger.info("注册信息准备...");
		}
		// 判断是否可以注册
		if((Symbols.FALSE).equals(RegisterConfig.getPropertyValue("isOpen"))){
			setInfoMessage(RegisterConfig.getPropertyValue("closeInfo"));
			return INFO;
		}
		if(logger.isInfoEnabled()){
			logger.info("是否已经阅读注册条款  : " + getIsRead());
		}
		
		// 判断注册是否已读
		if((Symbols.FALSE).equals(getIsRead())){
			// 是否需要阅读注册条款
			if((Symbols.FALSE).equals(RegisterConfig.getPropertyValue("isRead"))){
				return SUCCESS;
			}
			else{
				// 返回到注册条款页面
				setInfoMessage(RegisterConfig.getPropertyValue("agreement"));
				return "agreement";
			}
		}
		else{
			// 注册资料填写
			return SUCCESS;
		}
		
	}

	private Map session;
	public void setSession(Map sessionUsers) {
		session = sessionUsers;
	}

	public String getVCode() {
		return vCode;
	}

	public void setVCode(String code) {
		vCode = code;
	}

}
