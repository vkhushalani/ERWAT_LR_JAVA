package com.erwat.lr.SAPControllers;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Call_a_GET_API {
	public JSONArray getUserDetails(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://api2preview.sapsf.eu/odata/v2/User?$filter=userId eq '" + userId
				+ "'&$format=json&$select=userId,username,firstName,lastName";
		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "nga_anujk@erandwaterT1:nga@2018";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		// logger.error(request.getHeaders().toString());
		ResponseEntity<String> resStr = restTemplate.exchange(resourceUrl, HttpMethod.GET, request, String.class);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(resStr.getBody());
		jsonObject = (JSONObject) jsonObject.get("d");
		JSONArray jsonArr = (JSONArray) jsonObject.get("results");
		// logger.error(jsonArr.toString());
		return jsonArr;
	}

	public JSONArray searchUser(String searchstr) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://api2preview.sapsf.eu/odata/v2/User?$format=json&$top=10&$filter=substringof(toupper('"
				+ searchstr + "'),toupper(firstName)) or substringof(toupper('" + searchstr
				+ "'),toupper(lastName))&$select=userId,username,defaultFullName";
		HttpHeaders headers = new HttpHeaders();
		String plainCreds = "nga_anujk@erandwaterT1:nga@2018";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		// logger.error(request.getHeaders().toString());
		ResponseEntity<String> resStr = restTemplate.exchange(resourceUrl, HttpMethod.GET, request, String.class);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(resStr.getBody());
		jsonObject = (JSONObject) jsonObject.get("d");
		JSONArray jsonArr = (JSONArray) jsonObject.get("results");
		// logger.error(jsonArr.toString());
		return jsonArr;
	}
}
