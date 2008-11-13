package com.ntsky.bbs.web.webwork.action.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.xml.bean.Email;

/**
 * 邮件配置信息
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class EmailConfigAction extends XmlActionSupport implements ModelDriven {
	
	private Email email = new Email();
	
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}

	// 邮件标题
	private String[] subjects;
	public void setSubject(String[] subjects){
		this.subjects = subjects;
	}
	// 邮件内容
	private String[] texts;
	public void setText(String[] texts){
		this.texts = texts;
	}
	
	/**
	 * 基本信息设置
	 * <pre>
	 * 	执行成功迁移到 email.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("设置Email配置信息");
		}
		Map register = new HashMap();
		/*for(int i=0;i<subjects.length;i++){
			bodys.put(subjects[i],texts[i]);
		}*/
		register.put("subject",subjects[0]);
		register.put("text",texts[0]);
		
		Map error = new HashMap();
		error.put("subject",subjects[1]);
		error.put("text",texts[1]);
		
		Map bodys = new HashMap();
		bodys.put("register",register);
		bodys.put("error",error);
		
		email.setBodys(bodys);
		super.xmlDataService.editEmail(email);
		//super.xmlDataService.editXml(Symbols.CONFIG_BASIC,super.getRequest().getParameterMap());
		// 设置Email信息
		this.setEmailConfig();
		return SUCCESS;
    }

	/**
	 * 设置邮件配置信息
	 *
	 */
	private void setEmailConfig(){
		// 设置邮件信息
		Email email = super.xmlDataService.getEmail();
		if(logger.isInfoEnabled()){
			logger.info("邮件服务的用户名 : " + email.getUsername());
		}
		this.setEmail(email);
		//super.setPropertys(email.getBodys());				
	}
	
	/**
	 * 取得邮件配置信息
	 * <ul>
	 *  <li>查询邮件配置信息</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("取得邮件配置信息");	
		}
		this.setEmailConfig();
		return SUCCESS;
	}
	
	public Object getModel() {
		return this.email;
	}
	
}
