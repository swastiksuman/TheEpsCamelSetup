package com.inquiryapp.audit;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;

public class AuditWriter implements RequestHandler<Map<String, String>, String> {

	public String handleRequest(Map<String, String> event, Context context) {
		
		final String table_name = "CAMEL_AUDIT";
		HashMap<String, AttributeValue> item_values = new HashMap<String, AttributeValue>();
		System.out.println("Request Id: "+context.getAwsRequestId());
		item_values.put("REQUEST_ID", new AttributeValue(context.getAwsRequestId()));
		for(Map.Entry<String, String> entry : event.entrySet()) {
			
			if(entry.getKey().equalsIgnoreCase("REQUEST_STRING")) {
				item_values.put("REQUEST_STRING", new AttributeValue(entry.getValue()));
			}
			
			if(entry.getKey().equalsIgnoreCase("RESPONSE_STRING")) {
				item_values.put("RESPONSE_STRING", new AttributeValue(entry.getValue()));
			}
		}
		
		System.out.println(item_values);
		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials("MASKED", "MASKED")))
				.build();

		try {
			ddb.putItem(table_name, item_values);
		} catch (ResourceNotFoundException e) {
			System.err.format("Error: The table \"%s\" can't be found.\n", table_name);
			System.err.println("Be sure that it exists and that you've typed its name correctly!");
			System.exit(1);
		} catch (AmazonServiceException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		return event.toString();
	}
}
