package com.wuxianedu.exception;

public class EmploeeException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmploeeException() {
		super();
	}

	public EmploeeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmploeeException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmploeeException(String message) {
		super(message);
	}

	public EmploeeException(Throwable cause) {
		super(cause);
	}

}
