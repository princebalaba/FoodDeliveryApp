package com.hibernate.demo.advice;


import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hibernate.demo.exception.IdNotFoundException;
import com.hibernate.demo.exception.NameAlreadyExistsException;
import com.hibernate.demo.exception.NoDataFoundException;
import com.hibernate.demo.exception.apierror.ApiError;
@org.springframework.web.bind.annotation.ControllerAdvice 
//will handle all exceptions which are thrown by the controller/restcontroller
//using throws.
public class ControllerAdvice extends ResponseEntityExceptionHandler{
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDateFoundException(NoDataFoundException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", "no data found");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "no data found", e);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(NameAlreadyExistsException.class)// this is reponsible for handling NameAlreadyExistsException.
	public ResponseEntity<?> nameAlreadyExistsException(NameAlreadyExistsException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", "name already exists");
		System.out.println(e);
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e);
		System.out.println(apiError);
		return buildResponseEntity(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { //@Valid being used when posting to the DB 
		// TODO Auto-generated method stub
		ApiError apiError = new ApiError(status);
		apiError.setMessage("Validation Error");
		apiError.addValidationErrors(ex.getFieldErrors());
		apiError.addValidationObjectErrors(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
		
	}
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
		
	}
	@ExceptionHandler
	protected ResponseEntity<?> handleMethodAegumentTypeMismatch(MethodArgumentTypeMismatchException e){
		ApiError  apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(e.getMessage());
		apiError.setDebugMessage(e.getRequiredType().getName());
		return buildResponseEntity(apiError);
	}
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValidException( ConstraintViolationException ex) { 
		// TODO Auto-generated method stub
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
		
	}
		//protected ResponseEntity<?> handleMethodIdNotFoundException(IdNotFoundException e){
		//protected ResponseEntity<?> handleMethodNoDataFoundException(NoDataFoundException e){
		
		@ExceptionHandler(Exception.class)
			protected ResponseEntity<?> handleMethodException(Exception e){
				ApiError  apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
				apiError.setMessage(e.getMessage());
				return buildResponseEntity(apiError);
			
		}
		}	
			
		
		
	
	
	
