package com.tiptimes.identity.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getCurrentYear(){
		Calendar date = Calendar.getInstance();
		return String.valueOf(date.get(Calendar.YEAR));
	}
	
	public static String dateToStr(Date date){
		String dateStr = "";
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = format.format(date);
		}
		return dateStr;
	}
	
	public static String dateTimeToStr(Date date){
		String dateStr = "";
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = format.format(date);
		}
		return dateStr;
	}

	/**
	 * 显示年月日时分
	 * @param date
	 * @return
	 */
	public static String dateMinuteToStr(Date date){
		String dateStr = "";
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateStr = format.format(date);
		}
		return dateStr;
	}

	/**
	 * 判断时间是否处于某个时间段内
	 *
	 * @param time 需要比较的时间
	 * @param from 起始时间
	 * @param to 结束时间
	 * @return
	 */
	public static boolean belongCalendar(Date time, Date from, Date to) {
		Calendar date = Calendar.getInstance();
		date.setTime(time);
		Calendar after = Calendar.getInstance();
		after.setTime(from);
		Calendar before = Calendar.getInstance();
		before.setTime(to);
		if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 时间相减
	 * 
	 * @param endTime
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static String timeSubtract(Date startTime, Date endTime) throws ParseException {
		String str = "";
		try {
			long diff = endTime.getTime() - startTime.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			str = days + "天" + hours + "小时" + minutes + "分";
		} catch (Exception e) {

		}

		return str;
	}

	/**
	 * 时间相减得到天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getDaySub(Date beginDate, Date endDate) {
		long day = 0;
		try {
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取之后第几天的日期
	 * 
	 * @param later
	 * @return
	 */
	public static String getlaterDate(int later) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + later);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(today);
		return result;
	}

	/**
	 * timeStamp2Date
	 * 
	 * @param seconds
	 * @param format
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate1(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate2(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}
	/**
	 * 
	 * @param mss
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
	 * @author fy.zhang
	 */
	public static String formatDuring(long mss) {
		long days = mss / (60 * 60 * 24);
		long hours = (mss % (60 * 60 * 24)) / (60 * 60);
		long minutes = (mss % (60 * 60)) / (60);
		// long seconds = (mss % (1000 * 60)) / 1000;
		return days + " 天 " + hours + " 小时 " + minutes + " 分钟 ";
	}
	
	/**
	 * *天*小时*分钟 返回总分钟数
	 * @param date
	 * @return
	 */
	public static Integer strToMinutes(String date){
		Integer days = 0;
		Integer hour = 0;
		Integer minutes = 0;
		Integer allMinutes = 0;
		if(date != null && !"".equals(date)){
			date = date.replaceAll(" ", "");
			if(date.indexOf("天") != -1){
				String[] split = date.split("天");
				days = Integer.valueOf(split[0]);
				if(split[1].indexOf("小时") != -1){
					String[] split2 = split[1].split("小时");
					hour = Integer.valueOf(split2[0]);
					if(split2[1].indexOf("分钟") != -1){
						String[] split3 = split2[1].split("分钟");
						minutes = Integer.valueOf(split3[0]);
						allMinutes = (days * 24 * 60) + (hour * 60) + minutes;
						return allMinutes;
					}
				}
			}else if(date.indexOf("小时") != -1){
				String[] split = date.split("小时");
				hour = Integer.valueOf(split[0]);
				if(split[1].indexOf("分钟") != -1){
					String[] split2 = split[1].split("分钟");
					minutes = Integer.valueOf(split2[0]);
					allMinutes = (hour * 60) + minutes;
					return allMinutes;
				}
				
			}else if(date.indexOf("分钟") != -1){
				String[] split = date.split("分钟");
				minutes = Integer.valueOf(split[0]);
				allMinutes = minutes;
				return allMinutes;
			}
		}
		
		return 0;
	}

	/**
	 * 比较时间大小
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2){
		if(date2.before(date1)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前时间的整数日期
	 * @param date
	 * @return
	 */
	public static Date transferDate(Date date){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(date) + " 00:00:00";
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			return df1.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDateAdd(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
		try {
			sDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();  
        c.setTime(sDate);  
        c.add(Calendar.DAY_OF_MONTH, 1);
        
        sDate = c.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf1.format(sDate);
		return endDate;    
	}

	
	/**
	 * 获得当前时间 返回格式 YYYY-MM
	 * 
	 * @date:2017年4月28日13:33:27
	 * @return
	 */
	public static String getNewDateYM() {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		return bartDateFormat.format(date);
	}
	
	/**
	 * 获得当前时间 返回格式 YYYY-MM-DD
	 * 
	 * @date:2017年4月28日13:33:27
	 * @return
	 */
	public static String getNewDateYMD() {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return bartDateFormat.format(date);
	}
	
	/**
	 * @author zzh
	 * 比较日期大小
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int compareData(String startTime, String endTime) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = formatter.parse(startTime);
			Date dt2 = formatter.parse(endTime);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return 3;
			}else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		String data1="2019-12-31";
		String data2="2020-01-01";
		Date strToDate1 = strToDate1(data1);
		Date strToDate2 = strToDate1(data2);
		System.out.println(compareDate(strToDate1, strToDate2));
	}
}
