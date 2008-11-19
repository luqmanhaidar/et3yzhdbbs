package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;

/**
 * 帖子配置
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:28 $
 */
public class TopicConfigAction extends XmlActionSupport {
	
	/**
	 * 帖子配置信息
	 * 
	 * <pre>
	 * 	执行成功迁移到 topicConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doSet() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("设置帖子配置信息");
		}
		// 修改配置数据
		super.xmlDataService.editXml(Symbols.CONFIG_TOPIC,super.getRequest().getParameterMap());
		
		this.setTopicPropertyMap();
		return SUCCESS;
    }
	
	/**
	 * 设置系统配置映射数组
	 *
	 */
	private void setTopicPropertyMap(){
		Map systemMap = super.xmlDataService.select(Symbols.CONFIG_TOPIC);
		super.setPropertyMap(systemMap);
	}

	/**
	 * 取得帖子信息
	 * <ul>
	 *  <li>帖子信息查询</li>
	 * </ul>
	 */
	public String doOpen() throws Exception {
		if(!isPermisson("4_3")){
			setWarnMessage("您没有帖子控制的权限.");
			return NO_PERMISSION;
		}
		if(logger.isInfoEnabled()){
			logger.info("检索帖子配置信息");		
		}
		this.setTopicPropertyMap();
		return SUCCESS;
	}
	
}
