package com.ntsky.bbs.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import org.apache.log4j.Logger;

import com.ntsky.bbs.exception.LoadPropertiesException;

import com.opensymphony.webwork.util.ClassLoaderUtils;
import com.opensymphony.webwork.views.freemarker.FreemarkerManager;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public class FreemarkerStatic {

	private final static Logger logger = Logger
			.getLogger(FreemarkerStatic.class.getName());

	public static void loadProperties(ServletContext servletConext) throws TemplateException {

		//Configuration config = Configuration.getDefaultConfiguration();
		//Configuration config = new Configuration();
		
		Configuration config = FreemarkerManager.getInstance().getConfiguration(servletConext);
		
		BeansWrapper wrapper = (BeansWrapper) BeansWrapper.BEANS_WRAPPER;//BEANS_WRAPPER;
		wrapper.setExposureLevel(BeansWrapper.EXPOSE_ALL);
		//BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		InputStream in = null;
		//SimpleHash root = new SimpleHash();
		try {
			
			if(logger.isDebugEnabled()){
				logger.debug("加载静态类的配置文件freemarkerstatic.properties");
			}
			in = ClassLoaderUtils.getResourceAsStream(
					"/freemarkerstatic.properties", FreemarkerStatic.class);
			if (in != null) {
				Properties props = new Properties();
				props.load(in);
				Enumeration en = props.keys();
				String name, value;
				TemplateHashModel staticModels = wrapper.getStaticModels();
				TemplateHashModel tempStatics = null;
				while (en.hasMoreElements()) {
					name = (String) en.nextElement();
					value = props.getProperty(name);
					if(logger.isDebugEnabled()){
						logger.debug("set static method class : " + value);
					}
					tempStatics = (TemplateHashModel) staticModels.get(value);
					config.setSharedVariable(name, tempStatics);
					//root.put(name,tempStatics);
				}
				/*tempStatics = (TemplateHashModel) staticModels.get("java.io.File");
				config.setSharedVariable("File",tempStatics);
				root.put("File",tempStatics);*/
				//config.setAllSharedVariables(root);
				// 共通Static调用方法
				config.setSharedVariable("statics", BeansWrapper.getDefaultInstance().getStaticModels()); 
			}
		} catch (Exception ex) {
			logger
					.error(
							"Error while loading freemarker settings from /freemarkerstatic.properties",
							ex);
			throw new LoadPropertiesException(
					"load freemarker's static properties error");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				in = null;
			}
		}
		
		/*try {
			config.setAllSharedVariables(root);
		} catch (TemplateModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//config.set
		//config.setObjectWrapper(wrapper);
		//root.setDefaultObjectWrapper(wrapper);
		/*Object[] oarr = config.getSharedVariableNames().toArray();
		for (int i = 0; i < oarr.length; i++) {
			logger.debug("adasf" + oarr[i] +"\r\n");
		}*/
		//config.setDefaultConfiguration(config);
	}

	public static void main(String[] args) {
		//FreemarkerStatic.loadProperties();
	}
}
