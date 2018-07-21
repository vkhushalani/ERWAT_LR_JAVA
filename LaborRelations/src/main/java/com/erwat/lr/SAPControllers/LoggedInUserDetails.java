package com.erwat.lr.SAPControllers;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.Utilities.ConstantManager;

@RestController
@RequestMapping(value = ConstantManager.genAPI)
public class LoggedInUserDetails {

	private static final Logger logger = LoggerFactory.getLogger(LoggedInUserDetails.class);
	
	private SF_APIs_Common commonFunObj;

	@GetMapping(value = ConstantManager.loggedInUser)
	public JSONObject getLoggedInUserDetails(HttpServletRequest request) {
		try {
			commonFunObj = new SF_APIs_Common();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return commonFunObj.getLoggedInUser(request);
	}

}
