package com.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient////Eureka 
public class NetflixZuulApiGatwayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatwayServerApplication.class, args);
	}
	
	@Bean// for spring sleuth
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
