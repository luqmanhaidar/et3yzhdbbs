package com.ntsky.bbs.web.webwork.action;

import java.util.Date;
import java.util.Map;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.ActLog;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.service.ActLogService;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.web.schedule.data.CountSingleton;

/**
 * Action共通处理类
 * 
 * 
 * <ul>
 * </ul>
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/08 13:10:22 $
 */
public abstract class BasicActionSupport extends ActionSupport {

	public static Logger logger = Logger.getLogger(sun.reflect.Reflection.getCallerClass(1)); 
	public static String SELECT = "select";
	public static String INFO = "info";
	public static String WARN = "warn";
	public static String CREATE = "create";
	public static String EDIT = "edit";
	public static String DELETE = "delete";
	public static String UPDATE = "update";
	public static String MANAGE = "manage";
	public static String NO_PERMISSION = "noPermission";
	public static String NO_LOGIN = "noLogin";
	
	// 编号
	protected int id;
	public void setId(int id){
		this.id = id;
	}
	
	// 起始记录
	protected int start;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	// 动作
	protected String action;
	public void setAction(String action){
		this.action = action;
	}
	
	/** 排序参数设置 **/
	protected String sort;
	public void setSort(String sort){
		this.sort = sort;
	}
	protected String order;
	public void setOrder(String order){
		this.order = order;
	}
	
	// search begin	
	/**
	 * 检索框关键字
	 */
	protected String wd; 
	public void setWd(String wd){
		this.wd = wd;
	}
	public String getWd(){
		return this.getParameter("wd");
	}
	// search end
	
	protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();  
    }
    
    /**
     * 取得request中parameter的值
     * @param parameter 参数
     * @return string 参数的值
     */
    protected String getParameter(String parameter) {
    	Object value = getParameters().get(parameter);
    	if(value!=null){
    		return ((String[])value)[0];
    	}
 		return "";
    }
    
    /**
     * 取得整型参数的值
     * @param parameter 参数
     * @return int 当前类别
     */
    protected int getIntParameter(String parameter){
    	Object value = getParameters().get(parameter);
    	if(value!=null){
    		try{
    			return Integer.parseInt(((String[])value)[0]);
    		}
    		catch(Exception e){
    			return 0;
    		}
    	}
 		return 0;    	
    }
    
    /**
     * 取得分页的起始页
     * @return
     */
    protected int getPaginationStart(){
    	return getIntParameter(Symbols.PAGINATION_START);
    }
    
    /**
     * 判断是否有后台管理的权限
     * @param operator 操作
     * @return boolean 是否有操作权限
     */
    protected boolean isAdminPermission(String operator){
    	String permissions = getSessionAdmin().getPermissions();
    	return StringUtil.isArrayContainString(permissions.split(","),operator);
    }
    
    /**
     * Convenience method to get the session
     */
    protected HttpSession getSession() {
    	return getRequest().getSession();
    }

    /**
     * 取得IP
     * @return
     */
    protected String getRemoteAddr() {
    	return getRequest().getRemoteAddr();
    }
    
    /**
     * 
     * @return
     */
    protected Map getParameters(){
        ActionContext actionContext = ActionContext.getContext();
    	Map parameters = (Map)actionContext.getParameters();    
    	return parameters;
    }
    
    /**
     * 从Session中取得管理员信息
     * 
     * @return 管理员对象
     */
    protected Admin getSessionAdmin(){
    	Map sessionMap = ActionContext.getContext().getSession();
    	Object sessionObject = sessionMap.get(Symbols.SESSION_ADMIN);
    	if(sessionObject == null){
    		return null;
    	}
    	else{
    		return (Admin)sessionObject;
    	}
    	/*
    	Admin admin = new Admin();
    	admin.setId(new Long(1));
    	admin.setUsername("ntsky");
    	//admin.set
    	sessionMap.put(Symbols.SESSION_ADMIN,admin);
    	return (Admin)sessionMap.get(Symbols.SESSION_ADMIN);*/
    }
    
    /**
     * 判断是否为论坛管理员（此操作用户肯定为已登录用户）
     * @param forumId
     * @return true 有管理权限 false 没有管理权限
     */
    protected boolean isForumManage(int forumId){
    	User user = getSessionUser();
    	if( user == null ){
    		return false;
    	}
    	else{
    		if(logger.isDebugEnabled()){
    			logger.debug("论坛编号　：　" + forumId);
    		}
    		Forum forum = ForumSingleton.getInstance().getForum(forumId);
    		if(logger.isDebugEnabled()){
    			logger.debug("masters : " + forum.getMasters());
    		}
    		// 系统管理员能正常操作
    		if("1".equals(RoleSingleton.getInstance().getRoleIdByName(user.getUsername())) || "2".equals(RoleSingleton.getInstance().getRoleIdByName(user.getUsername()))){
    			return true;
    		}
    		// 该版块管理员
    		if(StringUtil.isArrayContainString(StringUtil.splitStringToArray(forum.getMasters(),","),user.getUsername())){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    }
    
    /**
     * 是否有访问权限
     * @param operator
     * @return int 0 权限不够
     */
    protected int isAccess(String operator){
    	if(logger.isInfoEnabled()){
    		logger.info("operator : " + operator+getSessionUser().getUsername());
    	}
    	// 用户未登陆,查看guest组是否有权限
    	if(getSessionUser()==null || getSessionUser().getUsername().equals("guest")){
    		if(logger.isInfoEnabled()){
    			logger.info("用户未登陆,检查Guest组是否有操作权限");
    		}
    		Role role = RoleSingleton.getInstance().getRole("0");
    		Object object = role.getPermissionMap().get(operator);
    		logger.info("用户未登陆"+object+"");
    		if(object == null){
    			if(logger.isInfoEnabled()){
    				logger.info("请检查"+ operator + "+"+object+",您没有该操作的权限");
    			}
    			return 0;
    		}
    		else{
    			return Integer.parseInt(String.valueOf(object));
    		}
    	}
    	// 登陆情况下,判断用户所在权限组
    	Role role = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(getSessionUser().getUsername()));
    	if(role==null){
    		return 0;
    	}
    	else{
    		if(logger.isInfoEnabled()){
    			logger.info("用户已登陆,检查用户组是否有操作权限");
    		}    		
    		Object object = role.getPermissionMap().get(operator);
    		if(logger.isInfoEnabled()){
    			logger.info("操作(operator)的值 : " + String.valueOf(object));
    		}    	
    		if(object == null){
    			if(logger.isInfoEnabled()){
    				logger.info("请检查"+ operator + "是否正常");
    			}
    			return 0;
    		}
    		else{
    			return Integer.parseInt(String.valueOf(object));
    		}
    	}
    }
    
    /**
     * 检测权限（对象是后台管理）
     * @param point 操作权限点
     * @return
     */
    public boolean isPermisson(String point){
    	if(logger.isInfoEnabled()){
    		logger.info("point : " + point);
    	}
    	Admin admin = this.getSessionAdmin();
    	if( admin==null ){
    		return false;
    	}
    	else{
    		if("admin".equals(admin.getUsername())&& ("0".equals(admin.getPermissions()))) return true;
    		if(logger.isInfoEnabled())
    			logger.info(admin.getUsername() + "has permissions : " + admin.getPermissions());
    		return StringUtil.isArrayContainString(admin.getPermissions().split(","), point);
    	}
    }
    
    /**
     * 从Session中取得用户信息
     * 
     * @return 管理员对象
     */
    protected User getSessionUser(){
    	Map sessionMap = ActionContext.getContext().getSession();
    	Object sessionObject = sessionMap.get(Symbols.SESSION_USER);
    	if(sessionObject == null){
    		return Symbols.getGuest();
    	}
    	else{
    		return (User)sessionObject;
    	}    	
    	/*User user = new User();
    	user.setId(new Long(1));
    	user.setUsername("ntsky");
    	sessionMap.put(Symbols.SESSION_USER,user);
    	return (User)sessionMap.get(Symbols.SESSION_USER);*/
    }    
    
    /**
     * 返回值的定义
     */
    public String actionMessage;
	public String getActionMessage() {
		return actionMessage;
	}
	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
	
    public String errorMessage;	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String warnMessage;
	public String getWarnMessage() {
		return warnMessage;
	}
	public void setWarnMessage(String warnMessage) {
		this.warnMessage = warnMessage;
	}	
	
	public String infoMessage;
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}	
	
    /**
     * 分页Bean的设置
     */
	private Pagination pagination = null;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	// 未读消息数
	private int noReadMessageNum = 0;
	public void setNoReadMessageNum(int noReadMessageNum) {
		this.noReadMessageNum = noReadMessageNum;
	}
	public int getNoReadMessageNum(){
		return noReadMessageNum;
	}	
	
	/**
	 * 记录操作日志
	 */
	public ActLogService actLogService;
	public void setActLogService(ActLogService actLogService){
		this.actLogService = actLogService;
	}

	/**
	 * 记录用户的操作日志
	 * @param actName
	 */
	protected void recordActLog(String actName) {
		if((Symbols.TRUE).equals(SystemConfig.getInstance().getPropertyValue(Symbols.CONFIG_SYSTEM,"isOpenActLog"))){
			ActLog actLog = new ActLog();
			actLog.setIp(HttpUtil.getRemoteAddr(ServletActionContext.getRequest()));
			
			User user = this.getSessionUser();
			Admin admin= this.getSessionAdmin();
			if( user==null && admin==null ){
				actLog.setUsername("guest");
			}
			else{
				if( user!=null ){
					actLog.setUsername("user : " + user.getUsername());
				}
				else{
					actLog.setUsername("admin : " + admin.getUsername());
				}
			}
			StackTraceElement[] stacks = (new Throwable()).getStackTrace();
			actLog.setName(actName  + " | " + stacks[1].getClassName()+ "." +stacks[1].getMethodName()+"--"+stacks[1].getLineNumber());
			actLog.setTime(new Date());
			if(logger.isDebugEnabled()){
				logger.debug("actLog name is : " + actLog.getName());
			}
			CountSingleton.getInstance().addActLog(actLog);
			//actLogService.createActLog(actLog);
		}
	}

}
