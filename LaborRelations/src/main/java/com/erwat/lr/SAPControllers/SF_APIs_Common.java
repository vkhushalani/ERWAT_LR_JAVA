package com.erwat.lr.SAPControllers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import com.sap.security.um.service.UserManagementAccessor;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

public class SF_APIs_Common {
	private static final Logger logger = LoggerFactory.getLogger(SF_APIs_Common.class);
	// LoggerFactory.getLogger(Call_a_GET_API.class);
	// look up the connectivity configuration API "connectivityConfiguration"
	Context ctx = new InitialContext();
	ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx
			.lookup("java:comp/env/connectivityConfiguration");
	// get destination configuration for "myDestinationName"
	DestinationConfiguration destConfiguration = configuration.getConfiguration("erwatSFOData");

	public SF_APIs_Common() throws Exception {
	}

	public CloseableHttpClient makeClient() throws Exception {
		logger.error("hiiiiiiiiiii......"+ destConfiguration.getAllProperties());
		String proxyType = destConfiguration.getProperty("ProxyType");
		logger.error(proxyType, destConfiguration.getAllProperties());
		HttpHost proxy = getProxy(proxyType);
		if (proxy == null)
			throw new Exception("Unable to get system proxy");
		CloseableHttpClient newClient = HttpClientBuilder.create().setProxy(proxy).build();
		return newClient;
	}

	public HttpHost getProxy(String proxyType) {
		String proxyHost = null;
		int proxyPort = 0;
		// Need to get proxy for internet destinations
		if (proxyType.equals("Internet")) {
			proxyHost = System.getProperty("http.proxyHost");
			proxyPort = Integer.parseInt(System.getProperty("http.proxyPort"));
			return new HttpHost(proxyHost, proxyPort);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getLoggedInUser(HttpServletRequest request) {
		UserProvider userProvider;
		String userID = null;
		String emailID = null;
		User user;
		try {
			request.setCharacterEncoding("UTF-8");
			userProvider = UserManagementAccessor.getUserProvider();
			userProvider.getUser(request.getUserPrincipal().getName());
			user = userProvider.getUser(request.getUserPrincipal().getName());
			// logger.error(request.);
			userID = user.getAttribute("name");
			emailID = user.getAttribute("email");
			JSONObject JSONobj = new JSONObject();
			JSONobj.put("user", user);
			JSONobj.put("userID", userID);
			JSONobj.put("mailID", emailID);
			JSONobj.put("firstname", user.getAttribute("firstname"));
			JSONobj.put("lastName", user.getAttribute("lastName"));
			return JSONobj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
