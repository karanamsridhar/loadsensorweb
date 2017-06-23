package com.yash.iot.loadsensorweb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.yash.iot.loadsensorweb.pojo.Sensor;

public class LoadSensorDynamoDbDao {
	
	private AmazonDynamoDB dynamodb = null;
	
	public LoadSensorDynamoDbDao() {
		dynamodb = AmazonDynamoDBClientBuilder.standard().
		withEndpointConfiguration(new EndpointConfiguration("http://localhost:8000", Regions.US_EAST_1.getName())).
		build();
	}
	
	public List<Sensor> getAllSensorData(){
		return null;
	}
	
	public List<String> getAllTablesNames(){
		ListTablesResult list = dynamodb.listTables();
		return list.getTableNames();		
	}
}
