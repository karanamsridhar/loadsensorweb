package com.yash.iot.loadsensorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yash.iot.loadsensorweb.dao.LoadSensorDao;
import com.yash.iot.loadsensorweb.dao.LoadSensorDynamoDbDao;
import com.yash.iot.loadsensorweb.pojo.Sensor;

@Controller
public class LoadSensorWebController {
	
	@Autowired
	private LoadSensorDao loadSensorDao;
	
	@RequestMapping(value={"/getAllSenorData"}, method={RequestMethod.GET})
	public String getAllSensorData(Model model){
		List<Sensor> sensorDataList = loadSensorDao.getAllSensorData();
		model.addAttribute("sensorDataList", sensorDataList);
		return "loadSensorReport";
	}
	
	@RequestMapping(value={"getAllTableNames"}, method={RequestMethod.GET})
	public String getAllTableNames(Model model){
		 List<String> tableNamesList = loadSensorDao.getAllTableNames();
		 model.addAttribute("tableNames", tableNamesList);
		 return "loadSensorReport";
	}
	
}
