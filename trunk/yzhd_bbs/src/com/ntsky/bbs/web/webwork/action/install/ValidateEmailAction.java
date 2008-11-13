package com.ntsky.bbs.web.webwork.action.install;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.mail.Sender;
import com.ntsky.framework.mail.Mail;
import com.ntsky.framework.mail.HtmlMail;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.xml.bean.Email;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;


/**
 * 检验Email是否正常
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:34 $
 */
public class ValidateEmailAction extends BasicActionSupport {

	/**
	 * 检测EMAIL参数是否设置正常(已经保存数据)
	 * <pre>
	 * 		执行成功迁移到 /common/success.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("检测Email的配置信息");
		}
		Sender sender = new Sender(EmailConfig.getSmtpHost(),EmailConfig.getUserName(),EmailConfig.getPassword());
		Mail mail = new HtmlMail();
		mail.setFrom(EmailConfig.getSystemMail());
		mail.setTo(SystemConfig.getInstance().getPropertyValue(Symbols.BASIC,"masterEmail"));
		mail.setTitle("Test ntsky's mail function.");
		mail.setContent("The mail status is ok.You can safely to use it.\r\n<a href=\"www.ntsky.com\" target=\"_blank\">www.ntsky.com</a>");
		try{
			sender.send(mail);
			super.setActionMessage("Email设置正确,可以正常发送Mail");
		}
		catch(Exception ex){
			super.setActionMessage("Email设置错误,请重新设置Mail配置信息");
			return ERROR;
		}
		return SUCCESS;
    }
	
	/**
	 * 检测未保存数据是否正确
	 * @return
	 * @throws Exception
	 */
	public String checkUnSave() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("保存Email配置信息前检测配置是否正确");
			logger.info("检测信息如下　：　\r\n \tsmtpHost : " + super.getParameter("smtpHost") + "\r\n\t username : " + super.getParameter("username") + "\r\n\t password : " + super.getParameter("password"));
		}
		
		try{
			Sender sender = new Sender(super.getParameter("smtpHost"),super.getParameter("username"),super.getParameter("password"));
			Mail mail = new HtmlMail();
			mail.setFrom(super.getParameter("systemMail"));
			mail.setTo(SystemConfig.getInstance().getPropertyValue(Symbols.BASIC,"masterEmail"));
			mail.setTitle("Test ntsky's mail function.");
			mail.setContent("The mail status is ok.You can safely to use it.<br/></br><a href=\"www.ntsky.com\" target=\"_blank\">www.ntsky.com</a>");
			
			sender.send(mail);
			super.setActionMessage("Email设置正确,可以正常发送Mail");
		}
		catch(Exception ex){
			super.setWarnMessage("Email设置错误,请重新设置Mail配置信息");
			logger.warn("Email设置错误,请重新设置Mail配置信息",ex);
			return WARN;
		}
		return SUCCESS;
    }
	
}
