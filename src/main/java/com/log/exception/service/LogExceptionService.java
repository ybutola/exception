package com.log.exception.service;

import com.log.exception.vo.LogExcpetion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LogExceptionService {
	
	@RequestMapping(value="/logException")
	public void logException(@PathVariable String appID, Exception exception){
		LogExcpetion logExcpetion = new LogExcpetion();
		logExcpetion.setCause(exception.getCause().toString());
		logExcpetion.setApplication(appID);
		logExcpetion.setLogType("EX");
		logExcpetion.setMessage(exception.getMessage());
		logExcpetion.setLogLevel("error");
		logExcpetion.setStackTrace(exception.getStackTrace().toString());
	}
	
	@RequestMapping(value="/logMessage")
	public void logMessage(String appID, String message){
		
	}
}
