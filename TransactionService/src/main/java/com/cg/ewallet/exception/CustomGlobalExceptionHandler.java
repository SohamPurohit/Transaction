package com.cg.ewallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ewallet.entity.CustomErrorDetails;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

	//to get the customize exception message 
	//message and error detail both shown 
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomErrorDetails> mapException(UserNotFoundException exception){
		CustomErrorDetails customError = new CustomErrorDetails(exception.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity(customError,HttpStatus.NOT_FOUND);
	}
}
