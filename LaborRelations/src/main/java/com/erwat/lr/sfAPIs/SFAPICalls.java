package com.erwat.lr.sfAPIs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;

@RestController
@RequestMapping("/SFcalls")
public class SFAPICalls {
	@GetMapping(value = "/getUserDetails")
	public JSONArray getUserDetails(@RequestParam(value="name", defaultValue="null")String name){
		Call_a_GET_API callApiObj=new Call_a_GET_API(); 
		return callApiObj.getUserDetails(name) ;
	}
	@GetMapping(value = "/searchUser")
	public JSONArray searchUser(@RequestParam(value="searchString", defaultValue="null")String searchString){
		Call_a_GET_API callApiObj=new Call_a_GET_API(); 
		return callApiObj.searchUser(searchString) ;
	}
}
