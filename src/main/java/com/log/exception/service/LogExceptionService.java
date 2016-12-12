package com.log.exception.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LogExceptionService {
	
	@RequestMapping(value="/logException")
	public void logException(String appID, Exception exception){

	}
	
	@RequestMapping(value="/logMessage")
	public void logMessage(String appID, String message){
		
	}
}
