package com.erwat.lr.Connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erwat.lr.Authentication.BasicAuthenticationHeaderProvider;
import com.erwat.lr.Connections.POJO.CustomStatus;
import com.erwat.lr.Utilities.ConstantManager;
import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

/*
 * Helper class <h1>HttpConnectionPOST</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 2.0
 */
public class HttpConnectionPOST {

	private String data;
	private DestinationConfiguration destinationConfiguration;

	@SuppressWarnings("rawtypes")
	private Class className;
	
	private URI uri;
	private static final String authType = "BasicAuthentication";
	private static final String message = "Success";
	private static final String zeroLength = "0";

	private static final Logger logger = LoggerFactory.getLogger(HttpConnectionPOST.class);

	@SuppressWarnings("rawtypes")
	public HttpConnectionPOST(URI uri, DestinationConfiguration dConfig, String data, Class className) {
		this.uri = uri;
		this.destinationConfiguration = dConfig;
		this.data = data;
		this.className = className;
	}

	/*
	 * This method is used to connect to the server. It takes the basic
	 * authentication headers from the destination
	 * 
	 * @return String
	 * 
	 * @exception IOException and ServletException
	 */
	public String connectToServer() {

		StringBuilder builder = new StringBuilder();
		URL url;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String response = "";

		try {
			url = new URL(uri.normalize().toString());

			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			List<AuthenticationHeader> authenticationHeaders = getAuthenticationHeaders(destinationConfiguration);
			for (AuthenticationHeader authenticationHeader : authenticationHeaders) {
				connection.addRequestProperty(authenticationHeader.getName(), authenticationHeader.getValue());
			}

			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
			bufferedWriter.write(data);
			bufferedWriter.flush();
			bufferedWriter.close();

			// Get status code from server
			int respCode = connection.getResponseCode();
			logger.info(ConstantManager.lineSeparator + ConstantManager.respCodeLog + className + " : " + respCode);
			boolean connected = HttpStatusCodes.respCodeFromServer(respCode);

			if (connected) {
				inputStream = connection.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}

				// Custom message
				if (builder.length() == 0) {
					CustomStatus customStatus = new CustomStatus();
					customStatus.setStatusCode(String.valueOf(respCode));
					customStatus.setLength(zeroLength);
					customStatus.setMessage(message);
					builder.append(customStatus.toString()).append("\n");
				}
			}

			else {
				inputStream = connection.getErrorStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}

		} catch (IOException e) {
			logger.error(ConstantManager.lineSeparator + "IO error description for connectToServerResp :: ", e);

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(ConstantManager.lineSeparator + "IO error description(finally block) for connectToServerResp :: ", e);
				}
			}
		}
		return builder.toString();

	}

	/*
	 * This method is used to connect to the server with the binary data. It
	 * takes the basic authentication headers from the destination
	 * 
	 * Note: Changed the o/p streams and accept encoding removed.
	 * 
	 * @return String
	 * 
	 * @exception IOException and ServletException
	 */
	public String connectToServerWithBinaryData() {

		StringBuilder builder = new StringBuilder();
		URL url;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String response = "";

		try {
			url = new URL(uri.normalize().toString());

			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			List<AuthenticationHeader> authenticationHeaders = getAuthenticationHeaders(destinationConfiguration);
			for (AuthenticationHeader authenticationHeader : authenticationHeaders) {
				connection.addRequestProperty(authenticationHeader.getName(), authenticationHeader.getValue());
			}

			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(data);
			outputStream.flush();
			outputStream.close();

			// Get status code from server
			int respCode = connection.getResponseCode();
			logger.info(ConstantManager.lineSeparator + ConstantManager.respCodeLog + className + " : " + respCode);
			boolean connected = HttpStatusCodes.respCodeFromServer(respCode);

			if (connected) {
				inputStream = connection.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}

				// Custom message
				if (builder.length() == 0) {
					CustomStatus customStatus = new CustomStatus();
					customStatus.setStatusCode(String.valueOf(respCode));
					customStatus.setLength(zeroLength);
					customStatus.setMessage(message);
					builder.append(customStatus.toString()).append("\n");
				}
			}

			else {
				inputStream = connection.getErrorStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				while ((response = bufferedReader.readLine()) != null) {
					builder.append(response).append("\n");
				}
			}

		} catch (IOException e) {
			logger.error(ConstantManager.lineSeparator + "IO error description for connectToServerResp :: ", e);

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(ConstantManager.lineSeparator + "IO error description(finally block) for connectToServerResp :: ", e);
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

			if (authType.equalsIgnoreCase((authenticationType))) {
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
