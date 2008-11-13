package com.ntsky.bbs.service;

import java.util.List;
import java.util.Map;

import com.ntsky.bbs.exception.ServiceException;

/**
 * 用户表情数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $ 
 */
public interface UserFaceService extends BaseService {

	/**
	 * 取得用户头像集合
	 * 
	 * <p>
	 * list中存储是的是一组Faces
	 * </p>
	 * 
	 * @return Map 用户头像集合
	 */
	public List getFaces () throws ServiceException ;	
	
}
