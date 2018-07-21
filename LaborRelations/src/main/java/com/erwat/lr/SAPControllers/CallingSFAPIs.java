package com.erwat.lr.SAPControllers;

import java.net.URI;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.erwat.lr.Connections.HttpConnectionGET;
import com.erwat.lr.Utilities.CommonFunctions;
import com.erwat.lr.Utilities.URLManager;

public class CallingSFAPIs {
	private static final String configName = "configurationname";
	public JSONArray callSFAPI(String parameter, String className) {

		ArrayList<String> userIDList;
		userIDList = new ArrayList<String>();
		userIDList.add(parameter);
		
		URLManager genURL = new URLManager(userIDList, className, configName);
		String urlToCall = genURL.formURLToCall();
		
		URI uri = CommonFunctions.convertToURI(urlToCall);
		HttpConnectionGET httpConnectionGET = new HttpConnectionGET(uri, URLManager.dConfiguration);
		String result = httpConnectionGET.connectToServer();
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(result);
		jsonObject = (JSONObject) jsonObject.get("d");
		JSONArray jsonArr = (JSONArray) jsonObject.get("results");
		
		return jsonArr;

	}

}
