package com.microservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/***
 * 
 * @author Dionatan Leão
 *
 */

@Component
public class KafkaReceiverRouter extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {

		from("kafka:myKafkaTopic").
		to("log:received-message-from-kafka");
	}

}

