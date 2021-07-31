package com.example.demo.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.ErrorMessage;

@ControllerAdvice   
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<ErrorMessage> handlePartnerNotFoundException(ApiRequestException exception, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(404);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<>( errorMessage, HttpStatus.NOT_FOUND);
	}
}
