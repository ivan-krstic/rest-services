package xyz.krstic.restservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import xyz.krstic.restservices.exception.CustomBadRequestException;
import xyz.krstic.restservices.exception.CustomInternalServerError;
import xyz.krstic.restservices.exception.CustomNoContentException;
import xyz.krstic.restservices.vo.ExceptionResponse;

@RestController
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(CustomNoContentException.class)
	public final ResponseEntity handleCustomNoContentException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(204, ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(CustomBadRequestException.class)
	public final ResponseEntity handleCustomBadRequestException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(400, ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(CustomInternalServerError.class)
	public final ResponseEntity handleCustomInternalServerError(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(500, ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public final ResponseEntity handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(500, ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}