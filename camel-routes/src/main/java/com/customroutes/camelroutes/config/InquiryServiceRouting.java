package com.customroutes.camelroutes.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceRouting extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//rest("/InquiryApp").get("/AppHttpService").responseMessage();
		rest("/").get("").to("http://localhost:8090/InquiryApp/AppHttpService");
	}

}
