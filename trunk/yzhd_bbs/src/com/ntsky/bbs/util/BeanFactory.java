package com.ntsky.bbs.util;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ntsky.framework.util.StringUtil;

/**
 * Bean工厂类
 * 
 * @author 姚君林
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:26 $
 */
public class BeanFactory {
	
	public static Logger logger = Logger.getLogger(BeanFactory.class);
	
	private static BeanFactory beanFacotory;
	private static ApplicationContext applicationContext;	
	
	private BeanFactory(ServletContext sc){
		try {
			if(logger.isInfoEnabled()){
				logger.info("applicationContext.xml的绝对地址 : " + StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),"WEB-INF/application*.xml"));
			}
			applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			// applicationContext = new FileSystemXmlApplicationContext(StringUtil.applyRelativePath(Application.getAppPath(),"WEB-INF/application*.xml"));
		} 
		catch (Exception exception) {
			logger.error("载入ApplicationContext文件发生错误.",exception);
		}		
	}
	
	public static BeanFactory getInstance(ServletContext sc){
		if(beanFacotory==null){
			beanFacotory = new BeanFactory(sc);
		}
		return beanFacotory;
	}

	/**
	 * 取得BEAN的实例
	 * @param name 实例名称
	 * @return bean对应的对象
	 */
	public Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
}
