package com.quick.start.feign.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.quick.start.bean.CurrencyConversionBean;

/**
 * @author Aymen Chaaben
 *
 */
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000") on a plus besoin de definir l'url avec ribbon 
//elle sera configuree dans application.properties  (currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001)

//@FeignClient(name = "currency-exchange-service")// without Zuul api gateway

@FeignClient(name = "netflix-zuul-api-gatway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
//	@GetMapping("/currency-exchange/from/{from}/to/{to}") without Zuul api gateway
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retriveExchangeValue( @PathVariable String from, @PathVariable String to);
}
