package com.ntsky.bbs.exception;

import com.ntsky.framework.exception.NTRuntimeException;

/**
 * DAO数据处理异常
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @see com.ntsky.framework.exception.NTRuntimeException
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:27 $
 */
public class ObjectNotExistException extends NTRuntimeException{

	public ObjectNotExistException(String msg) {
		super(msg);
	}
	
}
