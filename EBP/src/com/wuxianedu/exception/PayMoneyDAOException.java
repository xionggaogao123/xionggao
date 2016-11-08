package com.wuxianedu.exception;

public class PayMoneyDAOException extends Exception{

	private static final long serialVersionUID = 1L;

	public PayMoneyDAOException() {
		super();
	}

	public PayMoneyDAOException(String message) {
		super(message);
	}

	public PayMoneyDAOException(Throwable cause) {
		super(cause);
	}
}
