package com.erwat.lr.SAPControllers;

import java.net.URI;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.Connections.HttpConnectionGET;
import com.erwat.lr.Utilities.CommonFunctions;
import com.erwat.lr.Utilities.ConstantManager;
import com.erwat.lr.Utilities.URLManager;

@RestController
@RequestMapping(value = ConstantManager.genAPI)
public class SearchUser {

	private static final String configName = "configurationname";
	private static final Logger logger = LoggerFactory.getLogger(SearchUser.class);
	private ArrayList<String> userIDList;

	
	@GetMapping(value = "/searchUser")
	public JSONArray searchUser(@RequestParam(value="search", defaultValue="null")String searchString){
		logger.error(ConstantManager.lineSeparator + ConstantManager.paramLog + searchString);
		userIDList = new ArrayList<>();
		userIDList.add(searchString);
		
		URLManager genURL = new URLManager(userIDList, getClass().getSimpleName(), configName);
		String urlToCall = genURL.formURLToCall();
		logger.error(
				ConstantManager.lineSeparator + ConstantManager.urlLog + urlToCall + ConstantManager.lineSeparator);
		
		URI uri = CommonFunctions.convertToURI(urlToCall);
		HttpConnectionGET httpConnectionGET = new HttpConnectionGET(urlToCall, URLManager.dConfiguration);
		String result = httpConnectionGET.connectToServer();
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(result);
		jsonObject = (JSONObject) jsonObject.get("d");
		JSONArray jsonArr = (JSONArray) jsonObject.get("results");
		
		return jsonArr;
	}

}
