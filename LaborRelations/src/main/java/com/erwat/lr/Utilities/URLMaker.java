package com.erwat.lr.Utilities;


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
			urlToMake = urlToMake + "/User?$filter=";
			for(String item : list){
			if (item != null) {
				urlToMake = urlToMake + "userId eq '" + item+"'";
				if(!(item.equals(list.get(list.size() - 1))))
				{
					urlToMake = urlToMake + " or "; 
				}
			}}
			
			urlToMake = urlToMake + "&$format=json&$select=userId,username,firstName,lastName";
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
