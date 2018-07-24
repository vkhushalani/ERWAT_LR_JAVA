package com.erwat.lr.SAPControllers;

import java.net.URI;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erwat.lr.Connections.HttpConnectionGET;
import com.erwat.lr.Utilities.CommonFunctions;
import com.erwat.lr.Utilities.URLManager;
import com.erwat.lr.controller.AdminAppController;

public class CallingSFAPIs {
	private static final String configName = "configurationname";
	Logger logger = LoggerFactory.getLogger(CallingSFAPIs.class);
	public JSONArray callSFAPI(ArrayList<String> userIDList, String className) {

//		ArrayList<String> userIDList;
//		userIDList = new ArrayList<String>();
//		userIDList.add(parameter);
		
		URLManager genURL = new URLManager(userIDList, className, configName);
		String urlToCall = genURL.formURLToCall();
		logger.debug("urlToCall:"+urlToCall);
//		URI uri = CommonFunctions.convertToURI(urlToCall);
		HttpConnectionGET httpConnectionGET = new HttpConnectionGET(urlToCall, URLManager.dConfiguration);
		String result = httpConnectionGET.connectToServer();
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(result);
		jsonObject = (JSONObject) jsonObject.get("d");
		JSONArray jsonArr = (JSONArray) jsonObject.get("results");
		
		return jsonArr;

	}

}
