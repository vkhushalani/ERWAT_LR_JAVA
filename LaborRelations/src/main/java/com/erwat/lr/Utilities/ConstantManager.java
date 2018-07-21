package com.erwat.lr.Utilities;

/*
 * Utilities class <h1>ConstantManager</h1>
 * 
 * @author : Aseem Wangoo
 * @version : 2.0
 */
public class ConstantManager {

	// General application constants

	public static final String genAPI = "/SFcalls";

	public static final String genError = "/error";

	public static final String paramLog = "Params : ";

	public static final String urlLog = "URL : ";

	public static final String resultLog = "Resp Body : ";

	public static final String respCodeLog = "Response code of ";
	
	public static final String lineSeparator = System.getProperty("line.separator");
	// End general application constants
	
	//SAP api specific constants
	
	public static final String sapUserDetails = "/UserDetails";
	
	//End SAP SF api specific constants
	
	
	//Logged In User api specific constants
	public static final String loggedInUser = "/LoggedInUser";
	
	public static final String nameLog = "Logged in user : ";

	public static final String nameLogUAT = "Logged in user UAT : ";
	
	public static final String emailLog = "Email of logged in user : ";
	
	public static final String dNameLog = "Display name of logged in user : ";
	
	public static String userName;
	
	public static String email;
	
	public static String companyCode;
	
	public static String companySkillID;
	
	public static String country;
	//End Logged In User api specific constants
}
