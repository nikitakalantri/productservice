package com.tradeservice.exception;

import org.springframework.http.HttpStatus;

public class TradeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3312382402628898122L;
	
	private final String message;
	private final HttpStatus statusCode;
	
	public TradeException(String message, HttpStatus status) {
		super(message);
		this.message =  message;
		this.statusCode = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
}
