package com.quick.start.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.quick.start.beans.ExchangeValue;
import com.quick.start.repository.ExchangeValueRepository;

/**
 * @author Aymen Chaaben
 *
 */
@RestController
public class currencyExchangeController {
	Logger log= LoggerFactory.getLogger(currencyExchangeController.class);
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue( @PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromIgnoreCaseAndToIgnoreCase(from, to);
		if (exchangeValue!=null) {
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		}
		log.info("{}",exchangeValue);
		return exchangeValue;
	}

}
