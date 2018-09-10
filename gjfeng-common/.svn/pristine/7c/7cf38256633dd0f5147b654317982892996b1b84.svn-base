package cc.messcat.gjfeng.common.util;

import java.text.DecimalFormat;

public class NumberUtil {

	public static boolean isIntegerNotZero(Integer number){
		if(null != number && 0 != number.intValue()){
			return true;
		}
		return false;
	}
	
	public static boolean isIntegerZero(Integer number){
		if(null == number || 0 == number.intValue()){
			return true;
		}
		return false;
	}
	
	public static boolean isDoubleNotZero(Double number){
		if(null != number && 0 != number.doubleValue()){
			return true;
		}
		return false;
	}
	
	public static boolean isDoubleZero(Double number){
		if(null == number || 0 == number.intValue()){
			return true;
		}
		return false;
	}
	
	/**
	 * double保留2位小数（防止精度丢失）
	 * @param number
	 * @return
	 */
	public static double decimalFormat2(Double number){
		  DecimalFormat f = new DecimalFormat("#0.00");  
	      String s = f.format(number);  
	      return Double.parseDouble(s);  
	}
}
