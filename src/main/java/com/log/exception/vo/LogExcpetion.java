package com.log.exception.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogExcpetion {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	long logID;
	String logType; //Message, Exception
	String message;
	String stackTrace;
	String cause;
	Date logDate;
	String application;
}
