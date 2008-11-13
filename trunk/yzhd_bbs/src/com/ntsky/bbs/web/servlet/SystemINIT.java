package com.ntsky.bbs.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.service.RoleService;
import com.ntsky.bbs.service.UserService;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.util.AdvertisementUtil;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.EditorUtil;
import com.ntsky.bbs.util.FreemarkerStatic;
import com.ntsky.bbs.util.FrontTopUtil;
import com.ntsky.bbs.util.IndexRightUtil;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.util.config.ResourceConfig;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.BadwordSingleton;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.memory.StarUserSingleton;
import com.ntsky.bbs.web.schedule.IndexTimer;
import com.ntsky.bbs.xml.EmailHandler;
import com.ntsky.bbs.xml.SecurityHandler;
import com.ntsky.bbs.xml.SystemHandler;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.xml.SAXHelper;

/**
 * 系统初始化
 * 
 * <pre>执行信息的步骤如下 : </pre>
 * <ol>
 * 		<li>初始化xml配置文件路径</li>
 * 		<li>设置ServletContext</li>
 * 		<li>
 * 			<pre>载入XML文件，并将解析的数据写入内存</pre>
 * 		</li>
 * 		<li>初始化Freemarker的静态类</li>
 * 		<li>
 * 			读取数据库信息到内存
 *		</li>
 * 		<li>
 * 			初始化JS数据
 *		</li>
 * 		<li>启动定时器</li>
 * <ol>
 * 
 * @author 姚君林
 * 
 * @version $Revision: 1.7 $ $Date: 2008/10/23 03:20:47 $
 */
public class SystemINIT extends HttpServlet {
	
    private final static Logger logger = Logger.getLogger(SystemINIT.class.getName());
    
    //Initialize global variables
    public void init() throws ServletException {
    	try {
	
	    	if(logger.isDebugEnabled()) 
	    		logger.debug("web real path is : " + Application.getInstance().getWebRealPath());    		
    		
    		// 1、初始化 xml 配置文件路径
    		initXmlPath();
    		
    		// 2、设置ServletContext
	    	Application.getInstance().setServletContext(getServletContext());
	    		    	
	    	// 3、载入XML文件，并将解析的数据写入内存
	    	setApplicationData(getServletContext());
	    
	    	// 4、初始化Freemarker静态变量,对应配置文件在freemarkerstatic.properties
			FreemarkerStatic.loadProperties(getServletContext());
			
			// 5、读取数据库信息到内存
			setDBToMemory(getServletContext());
			
			// 6、初始化JS数据
			//initJsData();
			
			// 7、启动定时器
			//startTimer();
			
		} catch (Exception exception) {
			logger.error("初始化失败，请检查配置信息");
			logger.error("Init Application has error.", exception);
			// 初始化失败，停止web服务器
			// System.exit(0);
		}
		
    }
    
    /**
     * 初始化JS数据
     *
     */
    private void initJsData(){
		AnnouncementService announcementService = (AnnouncementService)getBean(getServletContext(), "announcementService");
		List forums = ForumSingleton.getInstance().getForums();
		Forum forum = null;
		for (int i = 0; i < forums.size(); i++) {
			forum = (Forum)forums.get(i);
			announcementService.makeAnnouncementJSData(forum.getId().intValue());
		}    	
    } 
    
    /**
     * 初始化xml文件路径 : 
     * <ol>
     * 	<li>web实际路径</li>
     *  <li>editor.xml</li>
     *  <li>security.xml</li>
     *  <li>system.xml</li>
     *  <li>email.xml</li>
     *  <li>resource.xml</li>
     *  <li>badwords.xml</li>
     * </ol>
     */
    private void initXmlPath(){
    	// app's path
    	Application.getInstance().setWebRealPath(getServletContext().getRealPath("/"));
		// Editor.xml
		Application.getInstance().setFilePathMap(Symbols.XML_EDITOR,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(), "WEB-INF/config/Editor.xml"));
		// Security.xml
		Application.getInstance().setFilePathMap(Symbols.XML_SECURITY,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/config/Security.xml"));
		// System.xml
		Application.getInstance().setFilePathMap(Symbols.XML_SYSTEM_CONFIG,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/config/System.xml"));
    	// Email.xml
		Application.getInstance().setFilePathMap(Symbols.XML_EMAIL,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/config/Email.xml"));
		// Resources.xml
		Application.getInstance().setFilePathMap(Symbols.XML_RESOURCE,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/config/Resources.xml"));
		// Badwords.xml
		Application.getInstance().setFilePathMap(Symbols.XML_BADWORDS,StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/config/Badwords.xml"));
    }
    
    
    /**
     * Convenience method to get Spring-initialized beans
     *
     * @param name
     * @return Object bean from ApplicationContext
     */
    private Object getBean(ServletContext sc, String name) {
    	if(logger.isDebugEnabled()){
			logger.debug("加载spring配置文件......");
		}
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(sc);
        return ctx.getBean(name);
    }

    /**
     * 读取xml信息并载入到内存
     * 
     * <ol>
     * 	<li>网站加密信息 : Security.xml</li>
     *  <li>系统全局配置信息 : System.xml</li>
     *  <li>Email的配置信息 : Email.xml</li>
     *  <li>系统资源信息，用于权限功能 : Resource.xml</li>
     *  <li>文本编辑器过滤配置信息 : Editor.xml</li>
     *  <li>脏话过滤配置 : Badwords.xml</li>
     *  <li>设置基础信息到Application中,包含System的basic和SEO节点</li>
     * </ol>
     * 
     * @param ServletContext sc
     */
    public void setApplicationData(ServletContext sc){
    	
    	Application application = Application.getInstance();
		// 网站加密信息
    	SecurityHandler securityHandler = new SecurityHandler();
    	SAXHelper.parseXML(application.getFilePath(Symbols.XML_SECURITY),securityHandler);
    	application.setSecurityMap(securityHandler.getSecruityMap());
    	
    	// 系统全局配置信息
    	SystemHandler systemHandler = new SystemHandler();
    	SAXHelper.parseXML(application.getFilePath(Symbols.XML_SYSTEM_CONFIG),systemHandler);
    	SystemConfig.getInstance().setDatas(systemHandler.getDatas());
    	
    	// Email的配置信息
    	EmailHandler emailHandler = new EmailHandler();
    	SAXHelper.parseXML(application.getFilePath(Symbols.XML_EMAIL),emailHandler);
    	EmailConfig.setEmail(emailHandler.getEmail());
    	
    	// 系统资源信息，用于权限功能
    	ResourceConfig.init();
    	
    	// 文本编辑器过滤配置信息
    	EditorUtil.setEditorMap();

    	// 脏话过滤配置
    	XmlDataService xmlDataService = (XmlDataService)getBean(getServletContext(), "xmlDataService");
    	BadwordSingleton.getInstance().setBadwords(xmlDataService.getBadwords());
    	
    	// 设置基础信息到Application中
    	sc.setAttribute(Symbols.BASIC,SystemConfig.getInstance().getPropertyMap(Symbols.BASIC));
    	sc.setAttribute(Symbols.SEO,SystemConfig.getInstance().getPropertyMap(Symbols.SEO));
    } 
    
    /**
     * 读取数据库信息到内存
     * <ul>
     * 	<li>设置论坛数据到内存</li>
     *  <li>设置角色数据到内存</li>
     * </ul>
     * @param sc
     */
    private void setDBToMemory(ServletContext sc){
		// set fourms to memory
		ForumService forumService = (ForumService)getBean(getServletContext(), "forumService");
		ForumSingleton.getInstance().setForums(forumService.getForums());	
		
		ForumSingleton.getInstance().setForumsInIndex(forumService.findForumsIsTop());
		// set user faces to memory
		/*UserFaceService userFaceService = (UserFaceService)getBean(getServletContext(), "userFaceService");
		UserFaceMemory.setFaces(userFaceService.getFaces());*/

		// set role data to memory
		//UserService userService=(UserService)getBean(getServletContext(), "userService");
		//List stars=userService.findStarUser(2);
		//StarUserSingleton.getInstance().setStars(stars);
				
		getServletContext().setAttribute("advertise",new AdvertisementUtil());
		getServletContext().setAttribute("frontTop",new FrontTopUtil());
		getServletContext().setAttribute("indexRight",new IndexRightUtil());
		
		RoleService roleService = (RoleService)getBean(getServletContext(), "roleService");
		roleService.setRolesToMemory();
		
		
    }
    
    /**
     * 启动定时器
     * 
     */
    private void startTimer(){
		logger.debug("开始启动定时器....");
		IndexTimer it = new IndexTimer(getServletContext());
		it.start();
		logger.debug("定时器启动完成....");    	
    }
    
    
    //Process the HTTP Get request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    //Clean up resources
    public void destroy() {
    }
    
}
