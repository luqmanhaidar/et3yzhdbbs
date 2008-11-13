package com.ntsky.bbs.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.xml.bean.Badword;
import com.ntsky.bbs.xml.bean.Email;
import com.ntsky.bbs.exception.XMLException;

public interface XmlDataService {

	/**
	 * 查询XML取得property
	 * @param dataId 数据标记
	 * @return
	 */
	public Map select(String dataId) ;
	
	/**
	 * 修改Xml数据
	 * @param dataId 数据标示
	 * @param valueMap 值数组
	 */
	public void editXml(String dataId,Map valueMap) throws XMLException ;
	
	/**
	 * 取得Email的信息
	 * 
	 * @return Email 邮件对象
	 */	
	public Email getEmail() throws XMLException ;
	
	/**
	 * 修改EMAIL
	 * @throws XMLException
	 */
	public void editEmail(Email email) throws XMLException ;
	
	/**
	 * 取得脏话列表
	 * @return
	 * @throws XMLException
	 */
	public List getBadwords() throws XMLException ; 
	
	/**
	 * 创建过滤的文字
	 * 
	 * @param badword 脏话对象
	 * @throws XMLException
	 */
	public void createBadword(Badword badword) throws XMLException ;
	
	/**
	 * 根据ID查找过滤的文字的信息
	 * @param id 索引
	 * @return 查找到的过滤的文字对象
	 * @throws XMLException
	 */
	public Badword getBadword(int id) throws XMLException ;
	
	public void replaceBadword(String Cotent) throws XMLException ;
	
	/**
	 * 更新xml节点
	 * @throws XMLException
	 */
	public void editBadword(Badword badword) throws XMLException ;
	
	public void removeBadword(Badword badword) throws XMLException;
	
	public Badword getBadwordById(int id) throws XMLException ;
}
