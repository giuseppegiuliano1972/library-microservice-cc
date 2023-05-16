package com.cc.member.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtility {
	
	public static Date addYear(Date dt) {
		
        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        c.add(Calendar.YEAR, ConstantUtility.ONE_YEAR );

        Date currentDatePlusOne = c.getTime();
		return currentDatePlusOne;
		
	}

}
