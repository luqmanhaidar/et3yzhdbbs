package com.jeecms.core.exception;

/**
 * 用户注册异常。所有用户注册异常的父类。
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public class UserRegisterException extends RuntimeException {
	public UserRegisterException() {
		super();
	}

	public UserRegisterException(String msg) {
		super(msg);
	}
}
