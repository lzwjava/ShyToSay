package lzw.shy_to_say.utils;

import java.util.Date;

public class Util{
	public static String agoTime(Date time){
		Date curTime=new Date();
		long day=daySubtract(curTime, time);
		if(day>0){
			return day+"天前";
		}else{ 
			long hour=hourSubtract(curTime,time);
			if(hour>0){
				return hour+"小时前";
			}else{
				long min=minSubtract(curTime,time);
				return min+"分钟前";
			}
		}
	}
	
	public static long minSubtract(Date date1,Date date2){ 
		long minMilliSec=1000*60;
		return dateSubtract(date1, date2, minMilliSec);
	}
	
	public static long hourSubtract(Date date1,Date date2){ 
		long hourMilliSec=1000*60*60;
		return dateSubtract(date1, date2, hourMilliSec);
	}
	
	public static long daySubtract(Date date1,Date date2){
		long dayMilliSec=1000*60*60*24;
		return dateSubtract(date1, date2, dayMilliSec);
	}
 
	public static long dateSubtract(Date date1,Date date2,long unit){
		long len=(date1.getTime()-date2.getTime())/unit;
		return len;
	}
}