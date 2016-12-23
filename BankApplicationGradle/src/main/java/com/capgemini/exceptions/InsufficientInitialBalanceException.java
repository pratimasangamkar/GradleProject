package com.capgemini.exceptions;

public class InsufficientInitialBalanceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientInitialBalanceException(String msg){
		super(msg);
	}
}
