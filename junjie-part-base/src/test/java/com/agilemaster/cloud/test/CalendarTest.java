package com.agilemaster.cloud.test;

import java.util.Calendar;

public class CalendarTest {
	
	public static void main(String[] args){
		Calendar can = Calendar.getInstance();
		System.out.print(can.get(Calendar.YEAR)+"-"+can.get(Calendar.MONTH)+"-"+can.get(Calendar.DAY_OF_MONTH));
		long time = can.getTimeInMillis() - 24*10*3600*1000;
		
	}

}
