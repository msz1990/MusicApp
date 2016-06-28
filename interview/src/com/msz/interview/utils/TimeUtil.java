package com.msz.interview.utils;

public class TimeUtil {
	
	public static String formatLongToTimeStr(Long l) {                  
		  int minute = 0;       
		  int second = 0;        
		  second = l.intValue() / 1000;        
		  if (second > 60) {            
		   minute = second / 60;            
		   second = second % 60;        
		  }              
		  String strtime = minute+":"+second;
		  return strtime;   
		}
}
