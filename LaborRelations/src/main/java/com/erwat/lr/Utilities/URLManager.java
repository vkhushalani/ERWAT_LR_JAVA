package com.erwat.lr.Utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import com.sap.cloud.account.TenantContext;
import javax.annotation.Resource;

/*
 * Helper class <h1>URLManager</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 1.0
 */

public class URLManager {

	public static DestinationConfiguration dConfiguration;

	private ArrayList<String> list;
	private String controllerClass;
	private String configName;

	private URLMaker urlMaker;

	private static final Logger logger = LoggerFactory.getLogger(URLManager.class);

	@Resource
	public static TenantContext tContext = null;

	public URLManager(ArrayList<String> list, String className, String config) {
		this.list = list;
		this.controllerClass = className;
		this.configName = config;
	}

	public URLManager(String className, String config) {
		this.controllerClass = className;
		this.configName = config;
	}

	/*
	 * Form the url to be called in the servlet class.
	 * 
	 * @return String
	 */
	public String formURLToCall() {
		String dUrl = destinationURL();

		switch (controllerClass) {

		case "UserDetails":
			urlMaker = new URLMaker(list, controllerClass);
			dUrl = dUrl + urlMaker.urlToMake;
			break;
			
		case "SearchUser":
			urlMaker = new URLMaker(list, controllerClass);
			dUrl = dUrl + urlMaker.urlToMake;
			break;
			
		default:
			break;
		}
		return dUrl;
	}

	/*
	 * Get the configuration details from SAP HCP.
	 * 
	 * @return String
	 * 
	 * @exception NamingException
	 */
	private String destinationURL() {
		String dUrl = null;
		try {
			Context context = new InitialContext();
			ConnectivityConfiguration configuration = (ConnectivityConfiguration) context
					.lookup("java:comp/env/connectivityConfiguration");

			// Extract Values from properties file
			LinkedHashMap<Object, Object> hashMap = new ExtractProperties().extractProps();
			logger.debug("extract properties"+hashMap);
			String config = (String) hashMap.get(this.configName);
			logger.debug("config"+config);
			// Get value of Tenant Context
			Context ctx = (Context) new InitialContext().lookup("java:comp/env");
			tContext = (TenantContext) ctx.lookup("TenantContext");

			// Access the url
			dConfiguration = configuration.getConfiguration(config);
			logger.debug("dConfiguration" +dConfiguration);

			dUrl = dConfiguration.getProperty("URL");
			logger.debug("dUrl" +dUrl);
			return dUrl;
		} catch (NamingException e) {
			logger.error(ConstantManager.lineSeparator + "NamingException: Error description for destinationURL :: ", e);
		} catch (Exception f) {
			logger.error(ConstantManager.lineSeparator + "Exception: Error description for destinationURL :: ", f);
		}
		return dUrl;
	}
}
