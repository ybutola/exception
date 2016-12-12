package com.log.exception.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/search")
class SearchExceptionService {

	@RequestMapping(value="/date")
	public void searchWithDate(String date){
		
	}
	
	@RequestMapping(value="/appID")
	public void searchForApplication(String appID){
		
	}
	
	@RequestMapping(value="/exception")
	public void searchForException(String exception){
		
	}
}
