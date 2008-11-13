package com.ntsky.bbs.web.webwork.action.xml;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 经验值设定管理Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class ExperienceConfigAction extends XmlActionSupport {
	
	/**
	 * 经验值设置
	 * <pre>
	 * 	执行成功迁移到 experienceConfig.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("设置经验配置信息");
		}
		// 修改金钱数据
		super.xmlDataService.editXml(Symbols.CONFIG_EXPERIENCE,super.getRequest().getParameterMap());
		this.setExperienceConfigMap();
		return SUCCESS;
    }

	private void setExperienceConfigMap(){
		// 设置基本信息
		Map experienceConfigMap = super.xmlDataService.select(Symbols.CONFIG_EXPERIENCE);
		super.setPropertyMap(experienceConfigMap);				
	}
	
	/**
	 * 取得经验值信息
	 * <ul>
	 *  <li>查询经验值信息</li>
	 * </ul>
	 */
	public String doSelect() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("检索经验值配置信息");		
		}
		this.setExperienceConfigMap();	
		return SUCCESS;
	}
	
	/**
	 * 设置基本配置信息
	 * @throws Exception
	 */
	public void prepare() throws Exception {

	}
	
}
