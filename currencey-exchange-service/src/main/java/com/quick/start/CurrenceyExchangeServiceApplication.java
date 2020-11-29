package com.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient //Eureka 
public class CurrenceyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenceyExchangeServiceApplication.class, args);
	}
	@Bean //for spring sleuth
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
