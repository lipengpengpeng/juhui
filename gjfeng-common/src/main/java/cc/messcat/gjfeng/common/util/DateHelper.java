/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 */
package cc.messcat.gjfeng.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.beanutils.ConversionException;

/**
 * @author Microcat
 * @version 1.0
 */
public class DateHelper {

	public Calendar cal;
	private Integer year, day, month, hour, minute, second;

	// 用来全局控制 上一周，本周，下一周的周数变化
	private int weeks = 0;
	private int MaxDate;// 一月最大天数
	private int MaxYear;// 一年最大天数

	public DateHelper() {
		cal = Calendar.getInstance();
		year = Integer.valueOf(cal.get(cal.YEAR));
		day = Integer.valueOf(cal.get(cal.DAY_OF_MONTH));
		month = Integer.valueOf(cal.get(cal.MONTH) + 1);
		hour = Integer.valueOf(cal.get(cal.HOUR_OF_DAY));
		minute = Integer.valueOf(cal.get(cal.MINUTE));
		second = Integer.valueOf(cal.get(cal.SECOND));
	}

	/**
	 * 通用日期格式
	 */
	public static final String DATE_COMMON_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static Date getCurrentDate() {
		Calendar calender = Calendar.getInstance();
		return calender.getTime();
	}

	/**
	 * 获取一个日期，该日期是在date的days天之前(days是负数)或之后(days是正数)
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date getDateByCalculateDays(Date date, int days) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, days);
		return calender.getTime();
	}

	/**
	 * 获取一个日期，该日期是在date的seconds秒之前(seconds是负数)或之后(seconds是正数)
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date getDateByCalculateSeconds(Date date, int seconds) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.SECOND, seconds);
		return calender.getTime();
	}

	/**
	 * 把日期按照某个格式转换成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dataToString(Object date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 把字符串按照某个格式转换成日期类型
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String strDate, String format) throws Exception {
		DateFormat df = new SimpleDateFormat(format);
		Date date = null;
		if (strDate != null && !"".equals(strDate)) {
			date = df.parse((String) strDate);
		}
		return date;
	}

	public static String getRandomNum() {
		StringBuffer sb = new StringBuffer();
		sb.append(dataToString(new java.util.Date(), DATE_FORMAT_YYYYMMDDHHMM));
		sb.append(String.valueOf(Math.round(Math.random() * 8999 + 1000)));
		return sb.toString();
	}

	//
	public String getCurrentDate(String language) {

		if (language.equals("EN")) {
			String currentDateEN = month + "-" + day + "-" + year + " " + hour + ":" + minute;
			return currentDateEN;
		}
		String currentDateCN = year + "年" + month + "月" + day + "日" + hour + ":" + minute;
		return currentDateCN;
	}

	public String getCurrentDateTimeNum() {
		String currentDateEN = year + "" + month + "" + day + "" + hour + "" + minute + "";
		return currentDateEN;
	}

	public int compareDate(String strDate1, String strDate2) {
		Date date1 = convertToDate(strDate1);
		Date date2 = convertToDate(strDate2);
		return date1.compareTo(date2);
	}

	public static Date convertToDate(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (strDate != null && !"".equals(strDate)) {
			try {
				date = df.parse((String) strDate);
			} catch (Exception pe) {
				pe.printStackTrace();
				throw new ConversionException("Error converting String to Date");
			}
		}
		return date;
	}

	/**
	 * 判断传入的date是否是新日期，将当前时间减去days天得出一个时间date2，然后再用date和date2比较，
	 * 如果date在date2之后或等于date2返回true，否则返回false
	 * 
	 * @param days
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isNewDate(Date date, int days) throws Exception {
		if (date == null) {
			throw new Exception("日期不能为空！");
		}
		Date date2 = getDateByCalculateDays(new Date(), days * (-1));
		int compareResult = date.compareTo(date2);
		if (compareResult < 0) {
			return false;
		} else {
			return true;
		}
	}

	public static Date getCurrentTimestamp() {
		Calendar calender = Calendar.getInstance();
		return calender.getTime();
	}

	public Integer getCurrentYear() {
		return year;
	}

	/**
	 * 当前日期基础上追加或者追减年月日
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public String getCurrentDate(Integer year, Integer month, Integer day) {
		Integer years = this.year + year;
		Integer months = this.month + month;
		Integer days = this.day + day;
		return years + "-" + months + "-" + days;
	}

	/**
	 * 判断两个日期是否是同一天
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 计算两个日期的天数差值
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param flag
	 *            true(精确度高) or false (精确度低，只做日期天数判断)
	 * @return
	 */
	public static long getDaysBetween(Date beginDate, Date endDate, boolean flag) {
		if (flag) {
			long beginTime = beginDate.getTime();
			long endTime = endDate.getTime();
			return (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
		} else {
			Calendar fromCalendar = Calendar.getInstance();
			fromCalendar.setTime(beginDate);
			fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			fromCalendar.set(Calendar.MINUTE, 0);
			fromCalendar.set(Calendar.SECOND, 0);
			fromCalendar.set(Calendar.MILLISECOND, 0);

			Calendar toCalendar = Calendar.getInstance();
			toCalendar.setTime(endDate);
			toCalendar.set(Calendar.HOUR_OF_DAY, 0);
			toCalendar.set(Calendar.MINUTE, 0);
			toCalendar.set(Calendar.SECOND, 0);
			toCalendar.set(Calendar.MILLISECOND, 0);

			return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
		}
	}

	/**
	 * @描述 字符串的日期格式的计算
	 * @author Karhs
	 * @date 2016年7月27日
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期的分钟差值
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param flag
	 * @return
	 */
	public static long getMinBetween(Date beginDate, Date endDate) {
		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		return (long) ((endTime - beginTime) / (1000 * 60));
	}

	/**
	 * @描述 根据日历比较两个日期相差天数，返回的是2016-7-27，2016-7-28两个日期相差一天
	 * @author Karhs
	 * @date 2016年7月27日
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;

	}

	/**
	 * @描述 获取一个日期，该日期是在date的month月之前或之后
	 * @author Karhs
	 * @date 2016年8月24日
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getDateByCalculateMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 时间戳转化为Date
	 * 
	 * @author Karhs
	 * @date 2016-02-03 10:57
	 * @param timeStamp
	 * @return
	 * @throws ParseException
	 */
	public static Date timeToStamp(Long timeStamp) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(timeStamp);
		String d = format.format(time);
		Date date = format.parse(d);
		return date;
	}

	/**
	 * 时间戳转化为Date
	 * 
	 * @author Karhs
	 * @date 2016-02-03 10:57
	 * @param timeStamp
	 * @return
	 * @throws ParseException
	 */
	public static Date timeStampToDate(Long timeStamp) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(timeStamp);
		String d = format.format(time);
		Date date = format.parse(d);
		return date;
	}

	/**
	 * 时间戳转化为String
	 * 
	 * @author Karhs
	 * @date 2016-02-03 10:57
	 * @param timeStamp
	 * @return
	 * @throws ParseException
	 */
	public static String timeStampToDateString(Long timeStamp,String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Long time = new Long(timeStamp);
		String date = format.format(time);
		return date;
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateHelper.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour 中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	// 计算当月最后一天,返回字符串
	public String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1 号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上月第一天
	public String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1 号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取当月第一天
	public String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本周星期日的日期
	public String getCurrentWeekday() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获取当天时间
	public String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String date = dateFormat.format(now);
		return date;
	}

	// 获得当前日期与本周日相差的天数
	private int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	public String getMondayOFWeek() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得相应周的周六的日期
	public String getSaturday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得上周星期日的日期
	public String getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得上周星期一的日期
	public String getPreviousWeekday() {
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期一的日期
	public String getNextMonday() {
		weeks++;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期日的日期
	public String getNextSunday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	private int getMonthPlus() {
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	// 获得上月最后一天的日期
	public String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月第一天的日期
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月最后一天的日期
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年最后一天的日期
	public String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年第一天的日期
	public String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本年有多少天
	private int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	// 获得本年第一天的日期
	public String getCurrentYearFirst() {
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	// 获得本年最后一天的日期 *
	public String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// 获得上年第一天的日期 *
	public String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	// 获得上年最后一天的日期
	public String getPreviousYearEnd() {
		weeks--;
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks + (MaxYear - 1));
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		getThisSeasonTime(11);
		return preYearDay;
	}

	// 获得本季度
	public String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		int start_days = 1;
		// years+"-"+String.valueOf(start_month)+"-";
		// getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days + ";" + years_value + "-" + end_month + "-"
			+ end_days;
		return seasonDate;
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	private int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

}
