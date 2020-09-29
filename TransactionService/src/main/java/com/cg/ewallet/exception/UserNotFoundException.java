package com.cg.ewallet.exception;


//User define Exception class 
//if the user not found then it handle the exception
//and return a message that user does not exist

public class UserNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
