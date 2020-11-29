package com.quick.start.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.quick.start.Configuration;
import com.quick.start.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration config; 
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitConfiguration() {
		return new LimitConfiguration(config.getMaximum(),config.getMinimum());
	}
	
	@GetMapping("/limits-hystrix")
	@HystrixCommand(fallbackMethod ="fallbackRetriveLimitConfigurationExcpetion" )
	public LimitConfiguration retriveLimitConfigurationExcpetion() {
		throw new RuntimeException();
	}
	
	public LimitConfiguration fallbackRetriveLimitConfigurationExcpetion() {
		return  new LimitConfiguration(1000,1);
	}
	
	

}
