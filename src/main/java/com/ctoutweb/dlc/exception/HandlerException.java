	package com.ctoutweb.dlc.exception;

import java.nio.file.FileAlreadyExistsException;

import com.ctoutweb.dlc.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.ctoutweb.dlc.model.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

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
	
	@ExceptionHandler(value = {TokenExpiredException.class})
	public ResponseEntity<ErrorResponse>TokenExpiredException(TokenExpiredException ex, WebRequest request){		
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = {EmailException.class})
	public ResponseEntity<ErrorResponse>EmailException(EmailException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorResponse>HttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse("les données envoyées ne sont pas correctes");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ErrorResponse>ConstraintViolationException(RuntimeException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse("les données envoyées ne sont pas correctes");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {TokenException.class})
	public ResponseEntity<ErrorResponse>TokenException(TokenException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatusCode.valueOf(403));
	}
	
	@ExceptionHandler(value= {MaxUploadSizeExceededException.class})
	public ResponseEntity<ErrorResponse> maxUploadSizeExceededException(MaxUploadSizeExceededException ex, WebRequest request){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("le fichier est trop volumineux"), HttpStatusCode.valueOf(400));
	}
	
	@ExceptionHandler(value = {AuthenticationException.class})
	public ResponseEntity<ErrorResponse>AuthenticationException(AuthenticationException ex, WebRequest request){
		ex.printStackTrace();
		if(ex instanceof NonceExpiredException) return new ResponseEntity<ErrorResponse>(new ErrorResponse("nonce"), HttpStatusCode.valueOf(403));
		if(ex instanceof InsufficientAuthenticationException) return new ResponseEntity<ErrorResponse>(new ErrorResponse("authentication obligatoire pour acceder à cette ressource"), HttpStatusCode.valueOf(401));
		
		ErrorResponse error = new ErrorResponse(ex.getMessage().toString());
		ex.printStackTrace();
		return new ResponseEntity<ErrorResponse>(error, HttpStatusCode.valueOf(403));
	}
	
	@ExceptionHandler(value = {EncryptionException.class})
	public ResponseEntity<ErrorResponse>EncryptionException(EncryptionException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {CreateAccountException.class})
	public ResponseEntity<ErrorResponse> createAccountException(CreateAccountException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {ActivateAccountException.class})
	public ResponseEntity<ErrorResponse> activateAccountException(ActivateAccountException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(value= {DisabledException.class})
	public ResponseEntity<ErrorResponse> disabledException(DisabledException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse("activation de votre compte obligatoire");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NoHandlerFoundException.class})
	public ResponseEntity<ErrorResponse> noHandlerFoundException(NoHandlerFoundException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {ProductNotFindException.class})
	public ResponseEntity<ErrorResponse> productNotFindException(ProductNotFindException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = {ProductException.class})
	public ResponseEntity<ErrorResponse> productException(ProductException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
