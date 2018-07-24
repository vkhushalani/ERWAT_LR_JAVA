package com.erwat.lr.Connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erwat.lr.Authentication.BasicAuthenticationHeaderProvider;
import com.erwat.lr.Utilities.ConstantManager;
import com.erwat.lr.Utilities.URLManager;
import com.sap.cloud.account.TenantContext;
import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

/*
 * Helper class <h1>HttpConnectionGET</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 1.0
 */
public class HttpConnectionGET {

	private DestinationConfiguration destinationConfiguration;
	private String urlToCall;

	private static final String authTypeBasic = "BasicAuthentication";

//	@SuppressWarnings("rawtypes")
//	private Class className;
	private static final int bufferLength = 8192;
	private static final Logger logger = LoggerFactory.getLogger(HttpConnectionGET.class);

	@Resource
	private TenantContext context = null;

//	@SuppressWarnings("rawtypes")
	public HttpConnectionGET(String urlToCall, DestinationConfiguration dConfig) {
		this.urlToCall = urlToCall;
		this.destinationConfiguration = dConfig;
//		this.className = className;
	}

	/*
	 * This method is used to connect to the server. It takes the basic
	 * authentication headers from the destination
	 * 
	 * @return String
	 * 
	 * @exception IOException and ServletException
	 */
	@SuppressWarnings("deprecation")
	public String connectToServer() {
		StringBuilder builder = new StringBuilder();
		URL url;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		Proxy proxy = GetProxy.getProxy();
		BufferedReader bufferedReader = null;
		String response = "";

		try {
			url = new URL(urlToCall);

			connection = (HttpURLConnection) url.openConnection(proxy);
			connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("SAP-Connectivity-ConsumerAccount", URLManager.tContext.getAccountName());
			connection.setRequestMethod("GET");

			List<AuthenticationHeader> authenticationHeaders = getAuthenticationHeaders(destinationConfiguration);
			for (AuthenticationHeader authenticationHeader : authenticationHeaders) {
				connection.addRequestProperty(authenticationHeader.getName(), authenticationHeader.getValue());
			}

			// Get status code from server
			int respCode = connection.getResponseCode();
//			logger.info(ConstantManager.lineSeparator + ConstantManager.respCodeLog + className + " : " + respCode);
			boolean connected = HttpStatusCodes.respCodeFromServer(respCode);

			if (connected) {
				inputStream = connection.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8),
						bufferLength);
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}

			else {
				inputStream = connection.getErrorStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8),
						bufferLength);
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}
		} catch (IOException e) {
			logger.error(ConstantManager.lineSeparator + "Error description for connectToServer :: ", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(ConstantManager.lineSeparator + "Error description for connectToServer :: ", e);
				}
			}
		}
		return builder.toString();
	}

	public String connectToServerNoProxy() {
		StringBuilder builder = new StringBuilder();
		URL url;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String response = "";

		try {
			url = new URL(urlToCall);

			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Accept", "application/json");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("GET");

			List<AuthenticationHeader> authenticationHeaders = getAuthenticationHeaders(destinationConfiguration);
			for (AuthenticationHeader authenticationHeader : authenticationHeaders) {
				connection.addRequestProperty(authenticationHeader.getName(), authenticationHeader.getValue());
			}

			// Get status code from server
			int respCode = connection.getResponseCode();
//			logger.info(ConstantManager.lineSeparator + ConstantManager.respCodeLog + className + " : " + respCode);
			boolean connected = HttpStatusCodes.respCodeFromServer(respCode);

			if (connected) {
				inputStream = connection.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8),
						bufferLength);
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}

			else {
				inputStream = connection.getErrorStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8),
						bufferLength);
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}
		} catch (IOException e) {
			logger.error(ConstantManager.lineSeparator + "Error description for connectToServerNoProxy :: ", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(ConstantManager.lineSeparator + "Error description for connectToServerNoProxy :: ", e);
				}
			}
		}
		return builder.toString();
	}

	/*
	 * This method is used to get the authentication headers from the hard-coded
	 * string.
	 * 
	 * @param destinationConfiguration
	 * 
	 * @return List<AuthenticationHeader>
	 * 
	 * @exception Exception
	 */
	private List<AuthenticationHeader> getAuthenticationHeaders(DestinationConfiguration destinationConfiguration) {
		List<AuthenticationHeader> authenticationHeaders = new ArrayList<>();
		try {
			String authenticationType = destinationConfiguration.getProperty("Authentication");

			if (authTypeBasic.equalsIgnoreCase((authenticationType))) {
				BasicAuthenticationHeaderProvider headerProvider = new BasicAuthenticationHeaderProvider();
				authenticationHeaders.add(headerProvider.getAuthenticationHeader(destinationConfiguration));
			}
			return authenticationHeaders;

		} catch (Exception e) {
			logger.error(ConstantManager.lineSeparator + "Error description for getAuthenticationHeaders :: ", e);
		}
		return authenticationHeaders;
	}
}
