package com.ntsky.bbs.util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.ntsky.framework.reflect.ReflectUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.service.impl.XmlDataServiceImpl;

/**
 * 对象工具类
 * 
 * @author Administrator
 * 
 */
public class BeanUtil {

	private final static Logger logger = Logger.getLogger(BeanUtil.class
			.getName());

	/**
	 * 格式化Form对象中的数据
	 * 
	 * @param formObject 表单Object
	 * @return object 新的对象
	 * @throws Exception
	 *             格式化数据产生的Exception
	 */
	public static Object format(Object formObject) {
		try {
			// 过滤字符串
			Field[] fileds = formObject.getClass().getDeclaredFields();
			if (logger.isDebugEnabled()) {
				logger.debug("格式化Bean : " + formObject.getClass().getName());
			}
			
			String newString = "";
			for (int i = 0; i < fileds.length; i++) {
				// 如果格式为字符串，则进行转换
				if (fileds[i].getType().getName().equals("java.lang.String")) {
					// 如果是editor格式，则不进行script转化
					if (EditorUtil.isEditor(formObject, fileds[i].getName())) {
						newString = StringUtil.formatEditorToHtml(ReflectUtil
								.getGetter(formObject.getClass(),
										fileds[i].getName()).get(formObject).toString());
						if (logger.isDebugEnabled()) {
							logger.debug("filed 's name is : "
									+ fileds[i].getName()
									+ " and field's vlaue is " + newString);
						}
					} 
					else {
						newString = StringUtil.formatStringToHtml(ReflectUtil
								.getGetter(formObject.getClass(),
										fileds[i].getName()).get(formObject).toString());
						if (logger.isDebugEnabled()) {
							logger.debug("filed 's name is : "
									+ fileds[i].getName()
									+ " and field's vlaue is " + newString);
						}					
					}
					newString = ("".equals(newString)) ? " " : newString;
					
					ReflectUtil.getSetter(formObject.getClass(),
							fileds[i].getName()).set(formObject, newString);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return formObject;
	}

}
