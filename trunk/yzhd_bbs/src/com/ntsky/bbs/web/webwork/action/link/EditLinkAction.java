package com.ntsky.bbs.web.webwork.action.link;

import org.apache.log4j.Logger;

import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.BeanUtil;
import com.opensymphony.xwork.ModelDriven;
/**
 * 修改友情链接
 * <ul>
 * 	<li>select -- 根据ID检索友情链接数据</li>
 *  <li>execute -- 更新友情链接数据</li>
 * </ul>
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.6 $ $Date: 2008/10/25 11:47:04 $
 */ 

public class EditLinkAction extends LinkActionSupport implements ModelDriven{

	public static Logger logger = Logger.getLogger(EditLinkAction.class);
	
	private int linkId;
	private int isLogo;
	private int adverType;
	private String name;
	private String url;
	private String description;
	private int displayOrder;
	private String logo;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsLogo() {
		return isLogo;
	}
	public void setIsLogo(int isLogo) {
		this.isLogo = isLogo;
	}
	public void setLinkId(int linkId){
		this.linkId = linkId;
	}
	
	// 链接数据
	private Link link=new Link();
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * 更新友情链接
	 */
	public String execute() throws Exception {
		
		try{
			link.setId(Long.parseLong(Integer.toString(linkId)));
			link.setIsLogo(Short.parseShort(Integer.toString(isLogo)));
			link.setAdverType(adverType);
			link.setDescription(description);
			link.setDisplayOrder(displayOrder);
			link.setName(name);
			link.setUrl(url);
			if(isLogo==1)
			{
				link.setLogo(logo);
			}
			linkService.editLink((Link)BeanUtil.format(link));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据链接编号检索链接数据
	 * @return SELECT
	 * @throws Exception
	 */
	public String doSelect() throws Exception{
		
		/* ---------- 权限判断 ------------ */
		//　修改友情链接
		if(!isPermisson("6_2")){
			setWarnMessage("您没有修改友情链接的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("修改友情链接");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("修改编号["+linkId+"]的链接信息");
		}
		try{
			setLink(linkService.getLink(linkId));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SELECT;
	}

	public Object getModel() {
		return this.link;
	}
	public int getLinkId() {
		return linkId;
	}
	public int getAdverType() {
		return adverType;
	}
	public void setAdverType(int adverType) {
		this.adverType = adverType;
	}
	
}