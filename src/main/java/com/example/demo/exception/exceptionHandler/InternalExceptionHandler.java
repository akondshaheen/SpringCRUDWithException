package com.example.demo.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.demo.exception.InternalException;
import com.example.demo.model.ErrorMessage;

@ControllerAdvice
public class InternalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Object> handle(InternalException internalException) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(500);
		errorMessage.setMessage(internalException.getMessage());
		return new ResponseEntity<>( errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}