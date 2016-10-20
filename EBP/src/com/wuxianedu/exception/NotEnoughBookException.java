package com.wuxianedu.exception;

public class NotEnoughBookException extends Exception{


	private static final long serialVersionUID = 1L;

	public NotEnoughBookException() {
		super();
	}

	public NotEnoughBookException(String message) {
		super(message);
	}

	public NotEnoughBookException(Throwable cause) {
		super(cause);
	}
}
