

#Modules:

1-Eureka: naming server

    server side:
   				annotation:@EnableEurekaServer
   				properties:
   							spring.application.name=netflix-eurika-naming-server
   							server.port=8761
   							eureka.client.register-with-eureka=false
   							eureka.client.fetch-registry=false
   				<dependency>
   					<groupId>org.springframework.cloud</groupId>
   					<artifactId>spring-cloud-starter-config</artifactId>
   				</dependency>
   				<dependency>
   					<groupId>org.springframework.cloud</groupId>
   					<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
   				</dependency>
		
	client side:
			annotation:@EnableDiscoveryClient
			properties: eureka.client.service-url.default-zone=http://localhost:8761/eureka
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			</dependency>



2-Ribbon: load balancing client side
	

    annotation:@RibbonClient(name = "currency-exchange-service")
    	properties:
    	currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001   //without eureka naming server
    	<dependency>
    		<groupId>org.springframework.cloud</groupId>
    		<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    	</dependency>	

3-feign: simple consommation of rest service
	

    annotation:@EnableFeignClients("com.quick.start")/@FeignClient(name = "netflix-zuul-api-gatway-server")
    	exemple :
    				@FeignClient(name = "netflix-zuul-api-gatway-server")
    				@RibbonClient(name = "currency-exchange-service")
    				public interface CurrencyExchangeServiceProxy {
    				//	@GetMapping("/currency-exchange/from/{from}/to/{to}") without Zuul api gateway
    					@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    					public CurrencyConversionBean retriveExchangeValue( @PathVariable String from, @PathVariable String to);
    				}
    	
    	<dependency>
    		<groupId>org.springframework.cloud</groupId>
    		<artifactId>spring-cloud-starter-config</artifactId>
    	</dependency>
    	<dependency>
    		<groupId>org.springframework.cloud</groupId>
    		<artifactId>spring-cloud-starter-openfeign</artifactId>
    	</dependency>

3-zuul : api gateway ===> - auth, autorization,security
						  - Rate Limits
						  - Fault tolerance (default response)
						  - Service Aggregation
		Annotation /implementation: @EnableZuulProxy
		
									@Bean// for spring sleuth
									public Sampler defaultSampler() {
										return Sampler.ALWAYS_SAMPLE;
									}
									@Component
									public class ZuulLogginFilter extends ZuulFilter 
						  
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
						  
4-Distributed tracing: *spring-cloud-sleuth =>set id for every request
					   *Zipkin==> trace requests
					   *RabbitMq ==> transfert all messages from services to zipkin
					   
					    <dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-sleuth</artifactId>
						</dependency>
						<!-- Distributed tracing -->
						<dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-zipkin</artifactId>
						</dependency>
						<!-- rabbit-mq -->
						<dependency>
							<groupId>org.springframework.amqp</groupId>
							<artifactId>spring-rabbit</artifactId>
						</dependency>
						
5-Bus: spring-cloud-starter-bus-amqp use http://localhost:8080/actuator/bus-refresh when we change a config file in git to alert all service about this change

						<dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-bus-amqp</artifactId>
						</dependency>
6-Hystrix: fault tolerance

						<dependency>
							<groupId>org.springframework.cloud</groupId>
							<artifactId>spring-cloud-starter-hystrix</artifactId>
							<version>1.4.7.RELEASE</version>
						</dependency>


# micro-services Urls
#http://localhost:8888/limits-service/default </br>
http://localhost:8888/limits-service/dev        http://localhost:8888/limits-service/qa </br>
http://localhost:8000/h2-console/ </br>

http://localhost:8761/   Eureka </br>
http://localhost:8765/currency-exchange-service/currency-exchange/from/euro/to/dinar   using api Gateway Zuul </br>
http://localhost:8765/currency-conversion-service//currency-converter-feign/from/euro/to/dinar/quantity/1000   using api Gateway Zuul </br>

# Distributed tracing with zipkin
1- installation erlang https://erlang.org/download/otp_versions_tree.html</br>
2-installer RabbitMq  https://www.rabbitmq.com/install-windows.html
	 pour lancer RabbitMq :
		 1 ajouter C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.9\sbin dans path
		 2 tappez rabbitmq-server
	 pour stopper RabbitMq :
		 1-rabbitmqctl  stop
 
3-Running Zipkin on Windows:  https://zipkin.io/pages/quickstart
				curl -sSL https://zipkin.io/quickstart.sh | bash -s
				java -jar zipkin.jar
				
				pour windowq:
				
				set RABBIT_URI=amqp://localhost
				java -jar zipkin-server-2.7.0-exec.jar
				
http://127.0.0.1:9411/zipkin/ 
http://localhost:8080/actuator/bus-refresh  
