package com.yash.iot.loadsensorweb.pojo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@DynamoDBTable(tableName="LOAD_SENSOR")
public @Data class Sensor {

	@DynamoDBAttribute(attributeName="WAREHOUSE")
	private String warehouse;
	@DynamoDBAttribute(attributeName="LOCATION")
	private String warehouseLocation;
	@DynamoDBHashKey(attributeName="SENSOR_ID")
	private String sensorId;
	@DynamoDBHashKey(attributeName="WEIGHT")
	private String weight;
	@DynamoDBHashKey(attributeName="PART")
	private String part;
	
	
}


