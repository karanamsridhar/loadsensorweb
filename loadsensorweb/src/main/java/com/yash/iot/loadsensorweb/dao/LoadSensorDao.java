package com.yash.iot.loadsensorweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemResult;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.yash.iot.loadsensorweb.pojo.Sensor;

@Repository
public class LoadSensorDao {
	
	private AmazonDynamoDB dynamoDB =  null;
	private DynamoDBMapper mapper = null;
	
	public LoadSensorDao() {
		dynamoDB = AmazonDynamoDBClientBuilder.standard().
					withCredentials(new DefaultAWSCredentialsProviderChain())
					.build();
		
		mapper = new DynamoDBMapper(dynamoDB);
	}
	
	public List<Sensor> getAllSensorData() {
		
/*		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS("1"));

        DynamoDBQueryExpression<Sensor> queryExpression = new DynamoDBQueryExpression<Sensor>()
            .withKeyConditionExpression("SENSOR_ID = :val1")
            .withExpressionAttributeValues(eav);

        List<Sensor> sensorDataList = mapper.query(Sensor.class, queryExpression);*/
        PaginatedScanList<Sensor> sensorDataList = mapper.scan(Sensor.class, new DynamoDBScanExpression());
        
        
        
/*		List<Sensor> sensorDataList = new ArrayList<Sensor>();
		QueryRequest request = new QueryRequest();
		request.setTableName("LOAD_SENSOR");
		request.setKeyConditionExpression("SENSOR_ID = :SENSOR_ID");
		Map<String, AttributeValue> attributeValueMap = new HashMap<String, AttributeValue>();
		AttributeValue value = new AttributeValue();
		value.setS("1");
		attributeValueMap.put("SENSOR_ID", value);
		request.setExpressionAttributeValues(attributeValueMap);
		
		QueryResult queryResult = dynamoDB.query(request);
		List<Map<String, AttributeValue>> itemsList = queryResult.getItems();
		if(itemsList!=null && itemsList.size() > 0){
			for (Map<String, AttributeValue> sensorDataMap : itemsList) {
				Sensor sensor = new Sensor();
				sensor.setPart(sensorDataMap.get("PART").getS());
				sensor.setSensorId(sensorDataMap.get("SENSOR_ID").getS());
				sensor.setWarehouseLocation(sensorDataMap.get("LOCATION").getS());
				sensor.setWeight(Float.valueOf(sensorDataMap.get("WEIGHT").getN()));
				sensor.setWarehouse(sensorDataMap.get("WAREHOUSE").getS());
				sensorDataList.add(sensor);
			}
		}
		return sensorDataList;
*/	
        return sensorDataList;    
	}
	
	public List<String> getAllTableNames(){
		ListTablesResult listTablesResult = dynamoDB.listTables();
		return listTablesResult.getTableNames();
	}
	
	public void createTable(String hashKeyName, String sortKeyName){
		CreateTableRequest createTableRequest = new CreateTableRequest();
		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName(hashKeyName).withKeyType(KeyType.HASH));
		keySchema.add(new KeySchemaElement().withAttributeName(sortKeyName).withKeyType(KeyType.RANGE));
		
	}
	
	public void populateData(){
		
	}
	
}
