package com.erwat.lr.Authentication;

import com.sap.core.connectivity.api.authentication.AuthenticationHeader;

/*
 * Pojo class <h1>AuthenticationHeaderImpl</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 1.0
 */
public class AuthenticationHeaderImpl implements AuthenticationHeader {

	private final String name;
	private final String value;

	public AuthenticationHeaderImpl(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
