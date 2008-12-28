package com.jeecms.core.exception;

/**
 * GUESTÒÑ¾­×¢²áÒì³£¡£
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
