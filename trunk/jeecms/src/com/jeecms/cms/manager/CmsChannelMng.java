package com.jeecms.cms.manager;

import java.util.List;

import com.jeecms.core.JeeCoreManager;
import com.jeecms.cms.entity.CmsChannel;

public interface CmsChannelMng extends JeeCoreManager<CmsChannel> {
	/**
	 * 获得栏目子节点。
	 * <p>
	 * start和count都为0时，不限制结果数量。
	 * </p>
	 * 
	 * @param pid
	 *            父节点ID
	 * @param orderBy
	 *            排序方式。0：优先级升序；1：优先级降序；2：点击次数升序；3：点击次数降序
	 * @param isDisplay
	 *            是否只获取显示的栏目
	 * @param hasChild
	 *            是否只获取可以有内容的栏目
	 * @param start
	 * @param count
	 * @return
	 */
	public List<CmsChannel> getChild(String sysType, Long pid, int orderBy,
			boolean isDisplay, boolean hasChild, int start, int count);

	/**
	 * 通过栏目路径获得栏目对象
	 * 
	 * @param path
	 * @return
	 */
	public CmsChannel getByPath(String path);

	/**
	 * 获得根栏目列表
	 * 
	 * @param sysType
	 *            系统类型
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType);

	/**
	 * 获得根栏目
	 * 
	 * @param sysType
	 * @return
	 */
	public CmsChannel getRoot(String sysType);

	/**
	 * 获得根栏目，启动过滤器，过滤不能有子节点的栏目
	 * 
	 * @param sysType
	 * @param hasChild
	 * @return
	 */
	public List<CmsChannel> getRoots(String sysType, boolean hasChild);
}