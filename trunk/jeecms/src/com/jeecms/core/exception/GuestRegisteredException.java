package com.jeecms.core.exception;

/**
 * GUEST�Ѿ�ע���쳣��
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
public class GuestRegisteredException extends UserRegisterException {
	public GuestRegisteredException() {
		super();
	}

	public GuestRegisteredException(String msg) {
		super(msg);
	}
}
