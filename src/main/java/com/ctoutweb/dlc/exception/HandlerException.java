	package com.ctoutweb.dlc.exception;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.exception.custom.AnnotationException;
import com.ctoutweb.dlc.exception.custom.FileException;
import com.ctoutweb.dlc.exception.custom.FriendNotFindException;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.ErrorResponse;

@ControllerAdvice
public class HandlerException {
	
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<ErrorResponse>UserNotFindException(UserNotFoundException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = {UserFindException.class})
	public ResponseEntity<ErrorResponse>UserFindException(UserFindException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value= {BadCredentialsException.class})
	public ResponseEntity<ErrorResponse>BadCredentialsException(BadCredentialsException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse("email ou mot de passe invalid");
		return new ResponseEntity<ErrorResponse>(error, HttpStatusCode.valueOf(403));
		
	}
	
	@ExceptionHandler(value= {InsertSQLException.class })
	public ResponseEntity<ErrorResponse>InsertSQLException(InsertSQLException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {AnnotationException.class})
	public ResponseEntity<ErrorResponse>AnnotationException(AnnotationException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {FriendNotFindException.class})
	public ResponseEntity<ErrorResponse>FriendNotFindException(FriendNotFindException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {FileException.class})
	public ResponseEntity<ErrorResponse> FileExtensionException(FileException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {FileAlreadyExistsException.class})
	public ResponseEntity<ErrorResponse>FileAlreadyExistsException(FileAlreadyExistsException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
