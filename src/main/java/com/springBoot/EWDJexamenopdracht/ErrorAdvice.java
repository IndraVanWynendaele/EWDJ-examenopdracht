package com.springBoot.EWDJexamenopdracht;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exceptions.GameNotFoundException;
import exceptions.SportNotFoundException;

@RestControllerAdvice
public class ErrorAdvice {

	@ResponseBody
	@ExceptionHandler(GameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String gameNotFoundHandler(GameNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(SportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String sportNotFoundHandler(SportNotFoundException ex) {
		return ex.getMessage();
	}
}
