package com.erwat.lr.Utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/*
 * Utilities class <h1>URLMaker</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 1.0
 */

public class URLMaker {

	private String className;
	private ArrayList<String> list;
	public String urlToMake = "";

	public URLMaker(String className) {
		this.className = className;
		makeURL();
	}

	public URLMaker(ArrayList<String> list, String className) {
		this.list = list;
		this.className = className;
		makeURL();
	}

	private String makeURL() {

		switch (className) {

		case "UserDetails":
			if (list.get(0) != null) {
				try {
					urlToMake = urlToMake + "/User?%24filter=userId%20eq%20'" + URLEncoder.encode(list.get(0), "UTF-8")
							+ "'&$format=json&$select=userId,username,firstName,lastName";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "SearchUser":
			if (list.get(0) != null) {
				urlToMake = urlToMake + "/User?$format=json&$top=10&$filter=substringof(toupper('" + list.get(0)
						+ "'),toupper(firstName))%20or%20substringof(toupper('" + list.get(0)
						+ "'),toupper(lastName))&$select=userId,username,defaultFullName";
			}
			break;

		default:
			break;
		}

		return urlToMake;
	}
}
