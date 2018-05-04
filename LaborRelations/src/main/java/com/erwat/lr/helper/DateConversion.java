package com.erwat.lr.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConversion {
	
	public Long DateToYYYMMDD(Calendar date) {
		 String DATE_FORMAT = "yyyyMMdd";
		    SimpleDateFormat sdf =
		          new SimpleDateFormat(DATE_FORMAT);
		    
		    return Long.valueOf(sdf.format(date.getTime()));
	}
}
