package com.ntsky.bbs;

import com.ntsky.bbs.domain.User;

/**
 * 常用变量包含类
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:33 $
 */
public final class Symbols {

	public final static String ERROR = "error";
	public final static String NO_LOGIN = "noLogin";

	public final static String SUCCESS_MSG = "successMsg";
	public final static String WARN_MSG = "warnMsg";
	public final static String ERROR_MSG = "errorMsg";
	public final static String INPUT_MSG = "inputMsg";
	
	public final static String FALSE = "false";
	public final static String TRUE = "true";
	public final static String GUEST = "guest";
	
	/**
	 * XML-FILE
	 */
	public final static String XML_SYSTEM_CONFIG = "xml_system_config";
	public final static String XML_BADWORDS = "xml_badwords";
	public final static String XML_EMAIL = "xml_email";
	public final static String XML_EDITOR = "xml_editor";
	//public final static String XML_RESOURCE_USER = "xml_resource_user";
	public final static String XML_RESOURCE = "xml_resource";
	public final static String XML_SECURITY = "xml_security";
	
	/**
	 * MemoryData
	 */
	public final static String BASIC = "basic";
	public final static String BASIC_IS_CLOSE = "isClose";
	public final static String BASIC_CLOSE_INFO = "closeInfo";
	public final static String BASIC_MASTER_MAIL = "masterMail";

	public final static String REGISTER = "register";
	
	public final static String SYSTEM = "system";
	public final static String SYSTEM_IS_SENDMAIL = "isSendMail";
	
	public final static String SEO = "seo";
	
	public final static String MONEY = "money";
	
	public final static String MONEY_ADD_TOPIC = "addTopic";
	public final static String MONEY_ADD_POST = "addPost";
	public final static String MONEY_ELITE_TOPIC = "eliteTopic";
	public final static String MONEY_DELETE_POST = "deletePost";
	public final static String MONEY_DELETE_TOPIC = "deleteTopic";
	
	public final static String PAGINATION = "pagination";
	public final static String PAGINATION_TOPIC = "topic";
	public final static String PAGINATION_POST = "post";
	public final static String PAGINATION_USER = "user";
	
    /**
     * PARA 参数请求
     */
	public final static String PARA_EMPTY = "";
	public final static String PARA_NULL = null;
	public final static String PARA_ID = "id";
	public final static String PARA_START = "start";
	public final static String PARA_FORUM_ID = "forumId";
	public final static String PARA_TOPIC_ID = "topicId";
	public final static String PARA_REDIRECT_URL = "redirectURL";
	
	
	public final static String PAGINATION_START = "start";
	
	
	public final static String TOPIC = "topic";
	public final static String POST = "post";
	// 减少
	public final static String DECREASE = "decrease";
	// 增加
	public final static String INCREASE = "increase";
	
    /**
     * session
     */
    public final static String SESSION_ADMIN = "sessionAdmin";
    public final static String SESSION_USER = "sessionUser";
    public final static String SESSION_ONLINE = "sessionOnline";
    
    /**
     * cookie
     */
    public final static String COOKIE_ADMIN = "cookieAdmin";
    public final static String COOKIE_FCKEDITOR = "cookieFckeditor";
    public final static String COOKIE_USER = "cookieUser";

	
    /**
     * FILE 公共部分
     */
    public final static String SCRIPT_FULL = "/";
    public final static String SCRIPT_LEFT = "(";
    public final static String SCRIPT_RIGHT = ");\r\n";
    public final static String SCRIPT_INC = "\"";
    public final static String SCRIPT_COMMA = ",";
    public final static String SCRIPT_UNDERLINE = "_";
    public final static String SCRIPT_DIR = "scripts/system/";
    
    /**
     * Config
     */
    public final static String CONFIG_BASIC = "basic";
    public final static String CONFIG_SYSTEM = "system";
    public final static String CONFIG_REGISTER = "register";
    public final static String CONFIG_TOPIC = "topic";
    public final static String CONFIG_MONEY = "money";
    public final static String CONFIG_EXPERIENCE = "experience";
    public final static String CONFIG_PAGINATION = "pagination";
    public final static String CONFIG_SEO = "seo";
    
    /**
     * MAIL
     */ 
    public final static String MAIL_ERROR = "error";
    public final static String MAIL_REGISTER = "register";    
    public final static String MAIL_SUBJECT = "subject";
    public final static String MAIL_TEXT = "text";
    
    public final static String SORT = "sort";
    public final static String ORDER = "order";
    
    public final static String MESSAGE_RECEIVER = "receiver";
    public final static String MESSAGE_SENDER = "sender";
    
    
    public final static User getGuest(){
    	User user = new User();
		user.setId(new Long(0));
		user.setUsername("guest");
		user.setRoles("0");
		user.setGmail("");
		user.setImQq("");
		user.setWebsite("");
		return user;    	
    }
    
}
