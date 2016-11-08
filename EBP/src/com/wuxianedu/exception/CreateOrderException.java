package com.wuxianedu.exception;

public class CreateOrderException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CreateOrderException() {
		super();
	}

	public CreateOrderException(String message) {
		super(message);
	}

	public CreateOrderException(Throwable cause) {
		super(cause);
	}

}
