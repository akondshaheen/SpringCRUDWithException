package com.example.demo.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.ErrorMessage;

@ControllerAdvice   
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {ValidationException.class})
	public ResponseEntity<ErrorMessage> handleValidationException(ValidationException validationException, WebRequest request){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(400);
		errorMessage.setMessage(validationException.getMessage());
		return new ResponseEntity<>( errorMessage, HttpStatus.BAD_REQUEST);
	}
}
