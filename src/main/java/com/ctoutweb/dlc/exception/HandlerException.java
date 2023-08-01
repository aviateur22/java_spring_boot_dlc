	package com.ctoutweb.dlc.exception;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ctoutweb.dlc.exception.custom.AnnotationException;
import com.ctoutweb.dlc.exception.custom.CreateAccountException;
import com.ctoutweb.dlc.exception.custom.EmailException;
import com.ctoutweb.dlc.exception.custom.EncryptionException;
import com.ctoutweb.dlc.exception.custom.FileException;
import com.ctoutweb.dlc.exception.custom.FriendNotFindException;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.exception.custom.TokenException;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import org.springframework.security.core.AuthenticationException;
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
	
	@ExceptionHandler(value = {JWTVerificationException.class})
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
	
	@ExceptionHandler(value = {AuthenticationException.class})
	public ResponseEntity<ErrorResponse>AuthenticationException(AuthenticationException ex, WebRequest request){	
		System.out.println("kfmfkgfmmùfkgfgkfgfkgfs");
		if(ex instanceof InsufficientAuthenticationException) return new ResponseEntity<ErrorResponse>(new ErrorResponse("authentication obligatoir pour acceder à cette ressource"), HttpStatusCode.valueOf(403));
		ErrorResponse error = new ErrorResponse(ex.getMessage().toString());
		ex.printStackTrace();
		return new ResponseEntity<ErrorResponse>(error, HttpStatusCode.valueOf(403));
	}
	
	@ExceptionHandler(value = {EncryptionException.class})
	public ResponseEntity<ErrorResponse>EncryptionException(EncryptionException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse("erreur chiffrement");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {CreateAccountException.class})
	public ResponseEntity<ErrorResponse> createAccountException(CreateAccountException ex, WebRequest request){	
		ErrorResponse error = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
