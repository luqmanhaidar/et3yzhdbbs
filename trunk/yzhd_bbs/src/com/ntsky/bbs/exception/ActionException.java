package com.ntsky.bbs.exception;

import org.apache.log4j.Logger;

import com.ntsky.framework.mail.Mail;
import com.ntsky.framework.mail.Sender;
import com.ntsky.framework.mail.HtmlMail;
import com.ntsky.framework.mail.MailException;
import com.ntsky.framework.exception.NTRuntimeException;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.SystemConfig;

/**
 * 业务处理异常
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @see com.ntsky.framework.exception.NTRuntimeException
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class ActionException extends NTRuntimeException{

	public static Logger logger = Logger.getLogger(ActionException.class);
	
	// exception
	public ActionException(Exception ex) {
		
		super(ex);
		
		// 取得异常（发送异常信息到管理员MAIL）
        StringBuffer errorMsgBuffer = new StringBuffer();
    	StackTraceElement[] stacks = ex.getStackTrace();
    	errorMsgBuffer.append(ex.getMessage() + " detail message : \r\n");
    	for (int i = 0; i < stacks.length; i++) {
    	    errorMsgBuffer.append("	at ").append(stacks[i].getClassName());
    	    errorMsgBuffer.append(".").append(stacks[i].getMethodName());
    	    errorMsgBuffer.append("(").append(stacks[i].getFileName());
    	    errorMsgBuffer.append(":").append(stacks[i].getLineNumber()).append(")\r\n");	
    	}	
    	//logger.error(errorMsgBuffer.toString());
    	
    	// 判断遇到错误是否发送邮件
		if((Symbols.TRUE).equals(SystemConfig.getInstance().getPropertyValue(Symbols.SYSTEM,Symbols.SYSTEM_IS_SENDMAIL))){
	        Sender sender = new Sender(EmailConfig.getSmtpHost(),EmailConfig.getUserName(),EmailConfig.getPassword());
	        Mail mail = new HtmlMail();
	        mail.setFrom(EmailConfig.getSystemMail());
	        mail.setTo(SystemConfig.getInstance().getPropertyValue(Symbols.BASIC,Symbols.BASIC_MASTER_MAIL));
	        mail.setTitle(EmailConfig.getPropertyValue(Symbols.MAIL_ERROR,Symbols.MAIL_SUBJECT));
	        mail.setContent(EmailConfig.getPropertyValue(Symbols.MAIL_ERROR,Symbols.MAIL_TEXT+"<br/>"+errorMsgBuffer.toString()));
	        try{
	        	sender.send(mail);
	        }
	        catch(MailException mailEx){
	        	logger.error("mail config is error, please config the email.");
	        	logger.error("mail exception info is : \r\n" , mailEx);
	        }
		}
	}
	
}
