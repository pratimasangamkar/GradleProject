package com.capgemini.exceptions;

public class InvalidAccountNumberException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumberException(String msg){
		super(msg);
	}
}
