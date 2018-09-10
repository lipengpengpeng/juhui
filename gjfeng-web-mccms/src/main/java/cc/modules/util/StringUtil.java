package cc.modules.util;

/**
 * @author Administrator
 * @version 1.1
 */
public class StringUtil {
	
	//后向补零
	public static String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();
//	           sb.append("0").append(str);// 左(前)补0
	         sb.append(str).append("0");//右(后)补0
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }

	public static boolean isBlank(String str) {
		boolean event = false;
		if ("".equals(str) || str == null)
			event = true;
		return event;
	}

	public static boolean isNotBlank(String str) {
		boolean event = false;
		if ((!"".equals(str)) && str != null)
			event = true;
		return event;
	}
	
	/**
     * 判断是否为int值.
     * 
     * @param str 待判断的字符串
     * @return 是否为int值
     */
    public static boolean isIntValue(final String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }
    
    /**
     * 判断是否为long值.
     *
     * @param str 待判断的字符串
     * @return 是否为long值
     */
    public static boolean isLongValue(final String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }
    
    /**
     * 判断是否为boolean值.
     * 
     * @param str 待判断的字符串
     * @return 是否为boolean值
     */
    public static boolean isBooleanValue(final String str) {
        return Boolean.TRUE.toString().equalsIgnoreCase(str) || Boolean.FALSE.toString().equalsIgnoreCase(str);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
