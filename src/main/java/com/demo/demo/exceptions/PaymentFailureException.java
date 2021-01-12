package com.demo.demo.exceptions;

public class PaymentFailureException extends RuntimeException {
	
	private String message;
	
	public PaymentFailureException(String message, String customMessage) {
		super(message);
		this.message = customMessage;
	}
}
