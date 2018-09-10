package cc.messcat.gjfeng.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日历计算，用于数据库的一周，一个月的数据查询
 * @author Karhs
 *
 */
public class CalendarHelper {
	
	
	public static List<String> calendDate(String flag){
		Date endDate = new Date();
		//创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		//距离今天，一个月内数据
		 if(flag.equals("month")){
		  cl.add(Calendar.MONTH, -1);
		}
		 //距离今天，一周内的数据,如果(flag！=month) &&  (flag！=week), 则查询的就是当天的数据
		if(flag.equals("week")){
		  cl.add(Calendar.DATE, -7);
		 }
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化开始日期和结束日期
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		List<String> calendList = new ArrayList<String>();
		calendList.add(0,start);
		calendList.add(1,end);
		return calendList;
	}

	
	public static List<String> nextCalendDate(Date endDate,int month,int day){
		//Date endDate = new Date();
		//创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		//距离今天，一个月内数据
		if (0 != month) {
			cl.add(Calendar.MONTH, month);
		}
		  //当前时间开始计算天数
		if (0 != day) {
			 cl.add(Calendar.DATE, day);
		}
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化开始日期和结束日期
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		List<String> calendList = new ArrayList<String>();
		calendList.add(0,start);
		calendList.add(1,end);
		return calendList;
	}
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
	/** 
	*字符串的日期格式的计算 
	*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    
    /**
     * 计算两个日期之间的月数
     * @param sDate
     * @param eDate
     * @return
     * @throws ParseException
     */
    public static int monthBetween(Date sDate,Date eDate) throws ParseException{
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(sdf.format(sDate)));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
         
        c.setTime(sdf.parse(sdf.format(eDate)));
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);
         
        int result;
        if(year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12*(year1 - year2) + month1 - month2;
        }
        return result;
    }
    
    
    /**
     * 根据日期获取星期几
     * @author Karhs
     * @since 2015-10-15
     * @param date
     * @return
     * @throws ParseException 
     */
    public static String getWeekOfDate(Date date,String type) throws ParseException {
    	if(StringUtil.isBlank(type) || "1".equals(type)){
    		int count = daysBetween(new Date(), date);
    		if(count == 0){
    			return "今天";
    		}else if(count == -1){
    			return "昨天";
    		}else{
    			String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    			Calendar calendar = Calendar.getInstance();      
                if(date != null){        
                     calendar.setTime(date);      
                }        
                int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
                if (w < 0){        
                    w = 0;      
                }      
                return weekOfDays[w];  
    		}
    	}else {
    		String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Calendar calendar = Calendar.getInstance();      
            if(date != null){        
                 calendar.setTime(date);      
            }        
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
            if (w < 0){        
                w = 0;      
            }      
            return weekOfDays[w];   
    	}
    	 
    }
    
    /**
     * 计算x小时之前或之后
     * @param dateDay
     * @param hour
     * @return
     */
    public static String getHourOfDate(String dateDay,int hour){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制 
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制 
        Date date = null; 
        try 
        { 
            date = format.parse(dateDay); 
        } 
        catch (Exception ex)    
        { 
            ex.printStackTrace(); 
        } 
        if (date == null) return ""; 
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.HOUR_OF_DAY, hour);//24小时制 
        //cal.add(Calendar.HOUR, x);12小时制 
        date = cal.getTime(); 
        System.out.println("front:" + date); 
        cal = null; 
        return format.format(date); 
    }
    
    
    /** 
     * 时间戳转换成日期格式字符串 
     * @author Karhs
     * @date 2016-02-14 14:27
     * @param seconds 精确到毫秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStampToDate(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(null == format || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }
}
