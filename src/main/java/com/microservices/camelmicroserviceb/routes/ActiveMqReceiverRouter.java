package com.microservices.camelmicroserviceb.routes;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.microservices.camelmicroserviceb.CurrencyExchange;

/***
 * 
 * @author Dionatan Leão
 *
 */

//@Component
public class ActiveMqReceiverRouter extends RouteBuilder{
		
//	@Autowired
//	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
//	
//	@Autowired
//	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;
	
	@Override
	public void configure() throws Exception {
		
		//JSON
		//CurrencyExchange
		//{ "id": 1000, "from": "USD", "to": "INR", "conversionMultiple": 70 }
		
//		from("activemq:my-active-mq-queue").
//			unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class).
//			bean(myCurrencyExchangeProcessor).
//			bean(myCurrencyExchangeTransformer).
//		to("log:received-message-from-active-mq");
		
		from("activemq:my-active-mq-xml-queue").
			unmarshal().
			jacksonxml(CurrencyExchange.class).
		to("log:received-message-from-active-mq");
	}

}

@Component
class MyCurrencyExchangeProcessor {
	
	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
	
	public void processMessage(CurrencyExchange currencyExchange) {
		logger.info("Do some processing with currencyExchange.getConversionMultiple() value which is {}",
				currencyExchange.getConversionMultiple());
	}
}

@Component
class MyCurrencyExchangeTransformer {
	
	public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
		
		currencyExchange.setConversionMultiple(
				currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));	
		
		return currencyExchange;
	}
}
