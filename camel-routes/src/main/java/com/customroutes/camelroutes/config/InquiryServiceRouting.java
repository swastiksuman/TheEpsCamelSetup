package com.customroutes.camelroutes.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.customroutes.camelroutes.service.User;

@Service
public class InquiryServiceRouting extends RouteBuilder {

	@Autowired
	Environment env;

	@Value("${camel.component.servlet.mapping.context-path}")
	private String contextPath;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true").enableCORS(true).port(env.getProperty("server.port", "8080"))
				.contextPath(contextPath.substring(0, contextPath.length() - 2));
				
		rest("/users").description("User REST service").consumes("application/json").produces("application/json")

				.get().description("Find all users").outType(User[].class).responseMessage().code(200)
				.message("All users successfully returned").endResponseMessage()
				.to("bean:userService?method=findUsers");
		rest("/google").description("Google").get().responseMessage().code(200).message("google").endResponseMessage()
				.to("http:localhost:8080/InquiryApp/AppHttpService?bridgeEndpoint=true");

	}

}
