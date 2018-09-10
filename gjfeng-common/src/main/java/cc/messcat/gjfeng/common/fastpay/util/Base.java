package cc.messcat.gjfeng.common.fastpay.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;



public class Base {
	private static Document doc;

	/**
	 * CMMBase ������ע�⡣

	 */
	public Base() {
		super();
	}

	public static Vector read(String path) {
		// �洢�ļ���������
		BufferedReader br;
		Vector v = new Vector();
		// ��ȡ�ļ�
		File f = new File(path);
		// �ж��ļ��Ƿ�ɷ���

		if (!f.exists() || !f.isFile() || !f.canRead()) {
			System.out.println("�ļ����ɷ��ʣ�");
			return v;
		}
		try {
			// ͨ������������ļ�����
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "GBK"));
			// ���Vector
			if (v != null) {
				v.removeAllElements();
			}
			// ���ļ������뵽Vector���͵ļ��ϱ���v��

			while (br.ready()) {
				v.addElement(br.readLine());
				
			}
			br.close();
			return v;
		} catch (IOException ioe) {
			return v;
		} finally {
		}
	}
	public static boolean write(String path, String content) {

		// ��ȡ�ļ�
		File f = new File(path);
		// д���ļ��������
		BufferedWriter bw;
		// �ж��ļ��Ƿ�ɷ���

		if (!f.exists() || !f.isFile() || !f.canWrite()) {
			System.out.println("�ļ����ɷ��ʣ�");
			return false;
		}
		try {
			// ͨ�������������д���ļ�
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, true), "GBK"));
			if (f.length() != 0) {
				bw.write("\r\n");
			}
			bw.write(content);
			bw.close();
			return true;
		} catch (IOException ioe) {
			return false;
		}
	}
	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getDateInWeek(String strDate) {
		int SUNDAY = 1;
		int MONDAY = 2;
		int TUESDAY = 3;
		int WEDNESDAY = 4;
		int THURSDAY = 5;
		int FRIDAY = 6;
		int SATURDAY = 7;

		if (!Base.isDate(strDate)) {
			return -1;
		}

		java.sql.Date date = null;
		date = date.valueOf(strDate);
		java.text.SimpleDateFormat week = new SimpleDateFormat("E");
		String weekstr = week.format(date);
		if (weekstr.equalsIgnoreCase("SUNDAY")) {
			return SUNDAY;
		} else if (weekstr.equalsIgnoreCase("MONDAY")) {
			return MONDAY;
		} else if (weekstr.equalsIgnoreCase("TUESDAY")) {
			return TUESDAY;
		} else if (weekstr.equalsIgnoreCase("WEDNESDAY")) {
			return WEDNESDAY;
		} else if (weekstr.equalsIgnoreCase("THURSDAY")) {
			return THURSDAY;
		} else if (weekstr.equalsIgnoreCase("FRIDAY")) {
			return FRIDAY;
		} else if (weekstr.equalsIgnoreCase("SATURDAY")) {
			return SATURDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return SUNDAY;
		} else if (weekstr.equalsIgnoreCase("����һ")) {
			return MONDAY;
		} else if (weekstr.equalsIgnoreCase("���ڶ�")) {
			return TUESDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return WEDNESDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return THURSDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return FRIDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return SATURDAY;
		} else {
			return -1;
		}

	}

	public static String getDateWeek(String strDate) {
		String SUNDAY = "������";
		String MONDAY = "����һ";
		String TUESDAY = "���ڶ�";
		String WEDNESDAY = "������";
		String THURSDAY = "������";
		String FRIDAY = "������";
		String SATURDAY = "������";

		if (!Base.isDate(strDate)) {
			return SUNDAY;
		}

		java.sql.Date date = null;
		date = date.valueOf(strDate);
		java.text.SimpleDateFormat week = new SimpleDateFormat("E");
		String weekstr = week.format(date);
		if (weekstr.equalsIgnoreCase("SUNDAY")) {
			return SUNDAY;
		} else if (weekstr.equalsIgnoreCase("MONDAY")) {
			return MONDAY;
		} else if (weekstr.equalsIgnoreCase("TUESDAY")) {
			return TUESDAY;
		} else if (weekstr.equalsIgnoreCase("WEDNESDAY")) {
			return WEDNESDAY;
		} else if (weekstr.equalsIgnoreCase("THURSDAY")) {
			return THURSDAY;
		} else if (weekstr.equalsIgnoreCase("FRIDAY")) {
			return FRIDAY;
		} else if (weekstr.equalsIgnoreCase("SATURDAY")) {
			return SATURDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return SUNDAY;
		} else if (weekstr.equalsIgnoreCase("����һ")) {
			return MONDAY;
		} else if (weekstr.equalsIgnoreCase("���ڶ�")) {
			return TUESDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return WEDNESDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return THURSDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return FRIDAY;
		} else if (weekstr.equalsIgnoreCase("������")) {
			return SATURDAY;
		} else {
			return SUNDAY;
		}

	}

	public static int getStrDate(String strDate, String style) {
		int year;
		int month;
		int day;
		int firstDash;
		int secondDash;
		if (strDate == null) {
			return 0;
		}
		firstDash = strDate.indexOf('-');
		secondDash = strDate.indexOf('-', firstDash + 1);
		if ((firstDash > 0) & (secondDash > 0)
				& (secondDash < strDate.length() - 1)) {
			year = Integer.parseInt(strDate.substring(0, firstDash));
			month = Integer.parseInt(strDate.substring(firstDash + 1,
					secondDash));
			day = Integer.parseInt(strDate.substring(secondDash + 1));
		} else {
			return 0;
		}
		if (style.equalsIgnoreCase("Y")) {
			return year;
		} else if (style.equalsIgnoreCase("M")) {
			return month;
		} else if (style.equalsIgnoreCase("D")) {
			return day;
		} else {
			return 0;
		}
	}

	// ȡ��ǰϵͳʱ��

	public static java.sql.Date CurrentDate() {
		java.util.Date CurrentDate = new java.util.Date();
		java.sql.Date Current = new java.sql.Date(CurrentDate.getTime());
		return Current;
	}

	public static String formatDate(String value, String pattern) {
		if (isBlank(value))
			return "";
		if (isBlank(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date dt;
		try {
			dt = d.parse(value);
			// return formatDateTime(dt,pattern);
		} catch (Exception ex) {
			System.out.println("formatDateTime error:" + ex.toString());
			return "";
		}
		return formatDateTime(dt, pattern);

	}

	public static String formatDateTime(String value, String pattern) {
		if (isBlank(value))
			return "";
		if (isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date dt;
		try {
			dt = d.parse(value);
			// return formatDateTime(dt,pattern);
		} catch (Exception ex) {
			System.out.println("formatDateTime error:" + ex.toString());
			return "";
		}
		return formatDateTime(dt, pattern);
		// if(isBlank(value)) return "";
		// if (isBlank(pattern)){
		// pattern="yyyy-MM-dd HH:mm:ss";
		// }
		// try{
		// if (!Base.isDate(value)) {
		// return "-1";
		// }
		// java.sql.Date date = null;
		// date = java.sql.Date.valueOf(value);
		// java.text.SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// String s_date = sdf.format(date);
		// return s_date;
		// // dt=d.parse(value);
		// }catch(Exception ex){
		// System.out.println("formatDateTime
		// error:"+ex.toString()+"\r\n"+value+"\r\n"+pattern);
		// return "";
		// }
	}

	public static String formatDateTime(java.util.Date value, String pattern) {
		if (value == null)
			return "";
		if (isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern(pattern);
		return d.format(value);
	}

	public static String getDate() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}
	/**
	 * ���ָ����ʽ��ȡ��ǰ����
	 * @param format yyyy-MM-dd
	 * @return
	 */
	public static String getDate(String format) {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern(format);
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}

	public static String getDateTime() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss:SSS");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}
	public static String getDateTimeTemp() {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyyMMddHHmmssSSS");
		java.util.Date nowdate = new java.util.Date();
		String str_date = d.format(nowdate);
		return str_date;
	}
	public static String getWeek() {
		return getWeek(Base.getDate());
	}
	public static String yesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}

	public static String getWeek(String strDate) {
		if (!Base.isDate(strDate)) {
			return "-1";
		}
		java.sql.Date date = null;
		date = date.valueOf(strDate);
		java.text.SimpleDateFormat year_week = new SimpleDateFormat("yyyy-ww");
		String s_year_week = year_week.format(date);
		return s_year_week;
	}

	public static String getMonth(String strDate) {
		if (!Base.isDate(strDate)) {
			return "-1";
		}
		java.sql.Date date = null;
		date = date.valueOf(strDate);
		java.text.SimpleDateFormat year_month = new SimpleDateFormat("yyyy-MM");
		String s_year_month = year_month.format(date);
		return s_year_month;
	}

	public static String getQuarter(String strDate) {
		if (!Base.isDate(strDate)) {
			return "-1";
		}
		java.sql.Date date = null;
		date = date.valueOf(strDate);

		java.text.SimpleDateFormat year = new SimpleDateFormat("yyyy");
		String s_year = year.format(date);
		java.text.SimpleDateFormat month = new SimpleDateFormat("M");
		String s_month = month.format(date);
		int i_month = Translate(s_month, 1);
		if (i_month >= 1 && i_month <= 3) {
			return s_year + "-1";
		} else if (i_month >= 4 && i_month <= 6) {
			return s_year + "-2";
		} else if (i_month >= 7 && i_month <= 9) {
			return s_year + "-3";
		} else {
			return s_year + "-4";
		}
	}

	public static String getTime() {

		java.util.Date date = new java.util.Date();
		java.sql.Time time;
		time = new Time(date.getTime());
		String strTime = time.toString();
		return strTime;
	}

	public static boolean isDate(String str) {
		try {
			if ((str == null) || (str.equals(""))) {
				return false;

			} else {
				str = str.trim();
			}

			// java.sql.Date date = null;
			// date = date.valueOf(str);
			// if (!str.equals(date.toString())) {
			// return false;
			// }
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isInteger(String str) throws NumberFormatException {
		try {
			if ((str == null) || (str.equals("")) || (str.equals("null"))) {
				return false;

			} else {
				str = str.trim();
			}
			int thisInt = (new Integer(str)).intValue();
			if (thisInt < 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * �ڴ˴����뷽��˵���� �������ڣ�(00-7-27 13:56:31)
	 * 
	 * @return boolean
	 */
	public static boolean isNumeric(String str) throws NumberFormatException {
		try {
			if ((str == null) || (str.equals(""))) {
				return false;

			} else {
				str = str.trim();
			}
			float thisfloat = (new Float(str)).floatValue();
			if (thisfloat < 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static String replaceString(String Str, String Sou, String Des) {

		// str ��Ҫ������ַ�

		// suo ��Ҫ�滻�����ַ�
		// des��Ҫ�滻Ϊ���ַ�
		// liyu

		int startIndex;
		int endIndex;
		String startString;
		String endString;
		String compString = "";
		int i, j;
		if (Str == null) {
			Str = "";
			compString = Str;
		} else {
			compString = Str;
		}
		i = 0;
		j = Str.length();
		while (i < j) {
			startIndex = Str.indexOf(Sou);
			if (startIndex >= 0) { // ���ڳ���λ��
				endIndex = startIndex + Sou.length(); // ����λ��
				startString = Str.substring(0, startIndex); // ����֮ǰ���ַ�

				if (startString == null) {
					startString = "";
				}
				endString = Str.substring(endIndex, Str.length()); // ����֮����ַ�

				if (endString == null) {
					endString = "";
				}
				compString = startString + Des + endString; // �����ַ�

				Str = compString; // ԭ�ַ��Ϊ�´�
				i = startIndex + Des.length(); // �����µĲ�ѯ���
				j = compString.length(); // �����µĲ�ѯ�����

			} else {
				i = j;

			}
		}
		return compString;

	}

	public static float stringToFloat(String str) {
		float result = (float) 0.0;
		try {
			if (str == null) {
				return 0;
			} else {
				if (str.equals("")) {
					return 0;
				} else {
					result = (new Float(str.trim())).floatValue();
				}
			}
		} catch (Exception e) {
			result = (float) 0.0;
		}
		return result;
	}

	public static int stringToInt(String str) {

		int result = 0;
		try {
			if (str == null) {
				return 0;
			} else {
				if (str.equals("")) {
					return 0;
				} else {
					result = (new Integer(str.trim())).intValue();
				}
			}
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

	public static String unreplaceSqlString(String str) {
		// ��������һЩ��Ҫ���ε��ַ��Ϊ��Ӧ�������ַ�������ҳ����ʾ��ɴ���
		// liyu

		if (str == null) {
			str = "";
		} else {
			str = str.trim();
			str = replaceString(str, "��", "<");
			str = replaceString(str, "��", ">");
			str = replaceString(str, "��", "\"");
		}
		return str;

	}

	public static String replaceSqlString(String str) {
		// ��������һЩ��Ҫ���ε��ַ��Ϊ��Ӧ�������ַ�������ҳ����ʾ��ɴ���
		// liyu

		if (str == null) {
			str = "";
		} else {
			str = str.trim();
			str = replaceString(str, "<", "��");
			str = replaceString(str, ">", "��");
			str = replaceString(str, "'", "��");
			str = replaceString(str, "\"", "��");
		}
		return str;

	}

	// �ж��Ƿ�Ϊ��
	public static boolean isBlank(String param) {

		if (param == null || param.trim().equals("")
				|| "null".equalsIgnoreCase(param)) {
			return true;
		} else {
			return false;
		}
	}

	// ���ַ�ת��Ϊint��

	public static int Translate(String value, int def) {
		int i;
		if (value == null) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = Integer.parseInt(value);
		} catch (Exception e) {
			i = def;
		}
		return i;
	}

	// ���ַ�ת��Ϊfloat��

	public static float Translate(String value, float def) {
		float i;
		if (value == null) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = Float.parseFloat(value);
		} catch (Exception e) {
			i = def;
		}
		return i;
	}

	// ���ַ�ת��Ϊdouble��

	public static double Translate(String value, double def) {
		double i;
		if (value == null) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = Double.parseDouble(value);
		} catch (Exception e) {
			i = def;
		}
		return i;

	}

	// ���ַ�ת��ΪBigDecimal��

	public static BigDecimal Translate(String value, BigDecimal def) {
		BigDecimal i;
		if (value == null) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = new BigDecimal(value);
		} catch (Exception e) {
			i = def;
		}
		return i;

	}

	// ���ַ�ת��ΪDate��

	public static java.sql.Date Translate(String value, java.sql.Date def) {
		java.sql.Date i;
		if (value == null) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = java.sql.Date.valueOf(value);
		} catch (Exception e) {
			i = def;
		}
		return i;

	}

	// ���ַ�ת��ΪString��

	public static String Translate(String value, String def) {
		String i;
		if (value == null || "null".equalsIgnoreCase(value)) {
			return def;
		} else {
			value = value.trim();
		}
		try {
			i = String.valueOf(value);
		} catch (NumberFormatException e) {
			i = def;
		}
		return i;

	}

	// �ַ�תΪ����

	public static String[] splitString(String str, String spl) {

		int length = 0;
		String[] res = null;

		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			for (int i = 0; i < str.length(); i++) {
				if (String.valueOf(str.charAt(i)).equals(spl)) {
					length++;
				}
			}
			res = new String[length + 1];
			int index = 0;
			for (int j = 0; j < length + 1; j++) {
				index = str.indexOf(spl);
				if (index == -1) {
					res[j] = str;
				} else {
					res[j] = str.substring(0, index);
					str = str.substring(index + 1);
				}
			}

		} catch (Exception e) {
			System.out.println("�ַ�ָ��쳣��" + e.toString() + "   ");
			return null;
		}
		return res;
	}
	
	// �����ַ��������Ƿ���������ַ�
	public static boolean findStringFormArrary(String strArrary[], String str) {
		boolean flag = false;
		for (int i = 0; i < strArrary.length; i++) {
			if (strArrary[i].equals(str)) {
				flag = true;
			}
		}
		
		return flag;
	}


	/**
	 * ת����ֵΪ���Ҹ�ʽ
	 * 
	 * @param number
	 *            ת��ֵ

	 * @param n
	 *            С����λ��
	 * @return ת�����ַ�(99,999,999.99)
	 */
	public static String toCurrencyString(double number, int n) {
		String sRtn = new String();
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(n);
		nf.setMinimumFractionDigits(n);
		if (number != 0) {
			sRtn = nf.format(number);
		}
		sRtn = replaceString(sRtn, "��", ""); // ȥ�����ҷ��
		return sRtn;
	}

	// �ַ�ת����HTML
	public static String inHTML(String str) {
		if (isBlank(str))
			return "";
		String sTemp;
		sTemp = str;
		sTemp = sTemp.replaceAll("&", "&amp;");
		sTemp = sTemp.replaceAll("<", "&lt;");
		sTemp = sTemp.replaceAll(">", "&gt;");
		sTemp = sTemp.replaceAll("\"", "&quot;");
		sTemp = sTemp.replaceAll("\'", "&apos;");

		return sTemp;
	}

	// �ַ�ת����HTML
	public static String HTMLtoStr(String str) {
		if (isBlank(str))
			return "";
		String sTemp;
		sTemp = str;
		sTemp = sTemp.replaceAll("&amp;", "&");
		sTemp = sTemp.replaceAll("&lt;", "<");
		sTemp = sTemp.replaceAll("&gt;", ">");
		sTemp = sTemp.replaceAll("&quot;", "\"");
		sTemp = sTemp.replaceAll("&apos;", "\'");

		return sTemp;
	}

	/**
	 * ��ʽ�����

	 * 
	 * @param s
	 *            Ҫ����ʽ�������
	 * @param Length
	 *            ����С���λ��

	 * @return
	 */
	public static String formatAmount(String s, int Length) {
		String s_Format = "#.";
		if (isBlank(s)) {
			return "";
		}
		if (Length <= 0) {
			s_Format = "#";
			Length = 0;
		}

		for (int i = 0; i < Length; i++) {
			s_Format += "#";
		}
		DecimalFormat df = new DecimalFormat(s_Format);
		s = df.format(Double.parseDouble(s));
		return s;
	}

	/**
	 * ���ַ�ض̣�ȡǰn���ַ�Ӣ�������ַ�

	 * 
	 * @param orignalString
	 *            ԭ�ַ�
	 * @param length
	 *            ����
	 * @param chopedString
	 *            ����ֵı�ʾ�ַ�
	 * @return ��ȡ���ַ�
	 */
	public static String chop(String orignalString, int length,
			String chopedString) {
		if (orignalString == null || orignalString.length() == 0) {
			return orignalString;
		}
		orignalString = orignalString.replaceAll(" ", " ");
		if (orignalString.length() < length) {
			return orignalString;
		}
		StringBuffer buffer = new StringBuffer(length);
		length = length * 2;
		int count = 0;
		int stringLength = orignalString.length();
		int i = 0;
		for (; count < length && i < stringLength; i++) {
			char c = orignalString.charAt(i);
			if (c < '\u00ff') {
				count++;
			} else {
				count += 2;
			}
			buffer.append(c);
		}
		if (i < stringLength) {
			buffer.append(chopedString);
		}
		return buffer.toString();
	}

	/**
	 * ����N��֮�������
	 * 
	 * @param bgdate
	 * @param days
	 * @return
	 */
	public static Date DaysBeforeDate(Date bgdate, int days) {
		long Time = (bgdate.getTime() / 1000) + 60 * 60 * 24 * days;
		Date ret = new Date();
		ret.setTime(Time * 1000);
		return ret;
	}

	/**
	 * �õ�����������֮����������
	 * 
	 * @param rq1
	 * @param rq2
	 * @return
	 */
	public static long DaysBetween(Date bgdate, Date enddate) {
		long beginTime = bgdate.getTime();
		long endTime = enddate.getTime();
		long days = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
		return days;
	}

       /**
      * ��ȡĳ��ĳ�µ����һ��

      * @param year ��

      * @param month ��

      * @return ���һ��

      */
    public static int getLastDayOfMonth(int year, int month) {
         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                    || month == 10 || month == 12) {
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
         return -1;
    }
   /**
     * �Ƿ�����
     * @param year ��

     * @return
     */
  public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
   }
	/**
	 * ��html�еı�ǩ���˵�ֻ�õ�����
	 * 
	 * @param str
	 * @return newZf
	 */
	public static String getChatOfHtml(String str) {
		StringBuffer zf = new StringBuffer();
		char ch = 'd';
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch == '<') {
				int k = str.indexOf('>', i);
				str = str.substring(k);
				i = 0;
			} else {
				zf.append(str.charAt(i));
			}
		}
		String newZf = zf.toString().replaceAll("&nbsp;", "").replaceAll(
				"&apos;", ",");

		return newZf;
	}

	public static String getString(int positon, String str) {
		if (str == null) {
			return "";
		} else {
			if (str.length() > positon) {
				str = str.substring(0, positon);
			}
		}
		return str;

	}

	/**
	 * �����ֶ��е�hao929����
	 * 
	 * @param str
	 * @return
	 */
	public static String delhao929(String str) {
		String startString = "</";
		String endString = "!--";
		int start = -1;
		int end = -1;
		start = str.indexOf(startString);
		end = str.indexOf(endString);
		if (start >= 0 && end > 0) {
			str = str.substring(0, start);
		}
		return str;
	}

	/**
	 * ��js�еı�ǩ���˵�ֻ�õ�����
	 * 
	 * @param str
	 * @return newZf
	 */
	public static String delJS(String str) {
		String regex1 = "<script[\\s\\S]+</script *>";
		String regex2 = " href *= *[\\s\\S]*script *:";
		String regex3 = " on[\\s\\S]*=";
		String regex4 = "<iframe[\\s\\S]+</iframe *>";
		String regex5 = "<frameset[\\s\\S]+</frameset *>";
		Pattern pat = Pattern.compile(regex1 + "|" + regex2 + "|" + regex3
				+ "|" + regex4 + "|" + regex5, Pattern.CASE_INSENSITIVE);
		Matcher m = pat.matcher(str);
		str = m.replaceAll("");

		str = str.replaceAll(regex2, ""); // ����href=javascript: (<A>) ����

		str = str.replaceAll(regex3, "_disibledevent=");// ��������ؼ���on...�¼�
		str = str.replaceAll(regex4, ""); // ����iframe
		str = str.replaceAll(regex5, ""); // ����frameset
		return str;
	}

	/**
	 * ����html javascrip
	 * 
	 * @param str
	 * @return
	 */
	public static String delHJ(String str) {
		str = delJS(str);
		str = getChatOfHtml(str);
		return str;
	}

	/**
	 * sql �ַ����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) {
		String inj_str = "'/and/exec/insert/select/delete/update/count/*/%/chr/mid/master/truncate/char/declare/;/or/-/+/,";

		String inj_stra[] = inj_str.split("/");

		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}



	public static Document getDoc() {
		return doc;
	}

	public static void setDoc(Document doc) {
		Base.doc = doc;
	}
	/** 
	* @Title: getQbsql 
	* @Description: ����ṩ��������ȡ�ڱ�list 
	* @param beginQueryCyc
	* @param endQueryCyc
	* @return String    �������� 
	* @throws 
	* @author ���ŷ�

	*/ 
	public static String getQbsql(String beginQueryCyc,String endQueryCyc){
		String sql="";
		if((beginQueryCyc==null||beginQueryCyc.length()==0)&&(endQueryCyc==null||endQueryCyc.length()==0)){
			sql="select a.sjqb from (SELECT distinct sjqb FROM sjqb order by sjqb desc FETCH FIRST 12 ROWS ONLY) a order by sjqb asc";
		}
		else if((beginQueryCyc!=null&&beginQueryCyc.length()!=0)&&(endQueryCyc==null||endQueryCyc.length()==0)){
			sql="SELECT distinct sjqb FROM sjqb where sjqb>='"+beginQueryCyc+"' order by sjqb asc";
		}
		else if((endQueryCyc!=null&&endQueryCyc.length()!=0)&&(beginQueryCyc==null||beginQueryCyc.length()==0)){
			sql="SELECT distinct sjqb FROM sjqb where sjqb<='"+endQueryCyc+"' order by sjqb asc";
		}
		else{
			sql="SELECT distinct sjqb FROM sjqb where sjqb>='"+beginQueryCyc+"' and sjqb<='"+endQueryCyc+"' order by sjqb asc";
		}
		return sql;
	}
	/**
	 * 
	 * flex����ת��
	 * */
	public static String flexToJava(String param){
		String temp="";
		try{
			temp =java.net.URLDecoder.decode(param,"UTF-8");
			return temp;
			}
			catch(Exception ex){
				temp = "";
			return temp;
			}
	}
	public static String md5s(String plainText,String key) {
//		DesCrypt desCrypt = new DesCrypt();
////		String key = "89d8aeb09f214eef9b6fb3447e6b44f9";
//		String sign = desCrypt.calcDigest(plainText, key); // ��ȡmd5ǩ��
		return md5s(plainText+key);
	}
	public static String md5s(String plainText) {
    	StringBuffer buf = new StringBuffer("");
    	  try {
    	   MessageDigest md = MessageDigest.getInstance("MD5");
    	   try {
			md.update(plainText.getBytes("GBK"));
    	   } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	   }
    	   byte b[] = md.digest();

    	   int i;

    	   
    	   for (int offset = 0; offset < b.length; offset++) {
    	    i = b[offset];
    	    if (i < 0)
    	     i += 256;
    	    if (i < 16)
    	     buf.append("0");
    	    buf.append(Integer.toHexString(i));
    	  }
    	   return buf.toString();
    	} catch (NoSuchAlgorithmException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();

    	}
    	return buf.toString();
    }
	public static String md5s(String plainText, String key, String charset) {
    	StringBuffer buf = new StringBuffer("");
    	  try {
    	   MessageDigest md = MessageDigest.getInstance("MD5");
    	   try {
			md.update((plainText + key).getBytes(charset));
    	   } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	   }
    	   byte b[] = md.digest();

    	   int i;

    	   
    	   for (int offset = 0; offset < b.length; offset++) {
    	    i = b[offset];
    	    if (i < 0)
    	     i += 256;
    	    if (i < 16)
    	     buf.append("0");
    	    buf.append(Integer.toHexString(i));
    	  }
    	   return buf.toString();
    	} catch (NoSuchAlgorithmException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();

    	}
    	return buf.toString();
    }
	public static String returnHtml(String url,String message){
		return "<html><form id='myform' action='"+url+"' method='post'><input type='hidden' name='message' value="+message+" /></form><script>document.getElementById('myform').submit();</script></html>";
	}
	/**
	 * ��ȡ�������͵������
	 * @param lenght ָ��������
	 * @return �����
	 */
	public static String getRandomNum(int lenght)
	{
//		String[] randomValues = new String[]{"0","1","2","3","4","5","6","7","8","9",
//				"a","b","c","d","e","f","g","h","i","j","k","l","m","n","u",
//				"t","s","o","x","v","p","q","r","w","y","z"};
				
		String[] randomValues = new String[]{"0","1","2","3","4","5","6","7","8","9"};
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}
		return str.toString();
	}
	/**
	 * ��ȡ��������ĸ���͵������
	 * @param lenght ָ��������
	 * @return �����
	 */
	public static String getRandomStr(int lenght)
	{
		String[] randomValues = new String[]{"0","1","2","3","4","5","6","7","8","9",
				"a","b","c","d","e","f","g","h","i","j","k","l","m","n","u",
				"t","s","o","x","v","p","q","r","w","y","z"};
				
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}
		return str.toString();
	}
	
	/**
	 * ��õ�ǰ��--��ʼ����
	 * @param date 2014-02-03
	 * @param format yyyy-MM-dd
	 * @return
	 */
    public static String getMinMonthDate(String date,String format) { 
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
             Calendar calendar = Calendar.getInstance();   
              try {
                 calendar.setTime(dateFormat.parse(date));
                 calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
                 return dateFormat.format(calendar.getTime());
               } catch (java.text.ParseException e) {
               e.printStackTrace();
              }
            return null;
    }

	/**
	 * ��õ�ǰ��--��������
	 * @param date 2014-02-03
	 * @param format yyyy-MM-dd
	 * @return
	 */
    public static String getMaxMonthDate(String date,String format){ 
         Calendar calendar = Calendar.getInstance();   
         SimpleDateFormat dateFormat = new SimpleDateFormat(format);
         try {
                calendar.setTime(dateFormat.parse(date));
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                return dateFormat.format(calendar.getTime());
         }  catch (java.text.ParseException e) {
                e.printStackTrace();
          }
        return null;
}
    /**
     * �����ʽ��
     * @param sd
     * @return
     */
    public static String getMillisToDate(long sd,String formatTemp){
        Date dat=new Date(sd);
		   GregorianCalendar gc = new GregorianCalendar(); 
		   gc.setTime(dat);
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   if(!Base.isBlank(formatTemp)){
			   format = new java.text.SimpleDateFormat(formatTemp);
		   } 
		   String sb=format.format(gc.getTime());
//		   System.out.println(sb);
		   return sb;
    }
    /**
     * �����ʽ��
     * @param sd
     * @return
     */
    public static String getMillisToDate(long sd){
        Date dat=new Date(sd);
		   GregorianCalendar gc = new GregorianCalendar(); 
		   gc.setTime(dat);
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   String sb=format.format(gc.getTime());
//		   System.out.println(sb);
		   return sb;
    }
    
    public static String getACPoolName(){
    	return "DefaultPool";
    }
    
    public static String getDynamicRAndom() {
    	return getDateTimeTemp() + getRandomStr(10);
    }
    
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
              sb = new StringBuffer();
              sb.append("0").append(str);// ��(ǰ)��0
           // sb.append(str).append("0");//��(��)��0
              str = sb.toString();
              strLen = str.length();
        }
        return str;
    }
    
    public static String addBlankForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
              sb = new StringBuffer();
//            sb.append("0").append(str);// ��(ǰ)���ո�
              sb.append(str).append(" ");//��(��)���ո�
              str = sb.toString();
              strLen = str.length();
        }
        return str;
    }
    
    public static Map<String, String> string2Map(String str) throws Exception {
        HashMap<String, String> rspMap = new HashMap<String, String>();
        String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            if ("signature".equals(tmp[0])) {
            } else {
            	rspMap.put(tmp[0], tmp[1]);
            }
        }
        return rspMap;
    }
    
	public static void main(String[] args) {
		// String ss="ɴ˵��˾</title></pre>"><scrip</title></pre>"><script
		// src=http://1.hao929.cn/ads.js></script><!--";
//		 System.out.println(Base.getDateTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
//		 System.out.println(Base.getDateTimeTemp());
//		 System.out.println(md5s("admin1"));
//		 System.out.println("c4ca4238a0b923820dcc509a6f75849b".length());
		
//		for(int i=0;i<10;i++){
//			System.out.println(Base.getRandomNum(12));
//		}
//		Calendar calendar = Calendar.getInstance();   
//		System.out.println(getMinMonthDate(calendar.get(Calendar.YEAR)+"0418","yyyyMMdd"));
//		System.out.println(getMaxMonthDate(calendar.get(Calendar.YEAR)+"0418","yyyyMMdd"));
//		System.out.println(Base.md5s("1","1"));
//		System.out.println(Base.md5s("GBK2.0.105002111111111111111162791013025201451100282015020419061920150204640854486216261000000000018150123456781144062219861111123102", "89d8aeb09f214eef9b6fb3447e6b44f9"));
		
		
//		formatDateTime(Base.getDateTimeTemp(),"MMddHHmmss");
//		System.out.println(getDynamicRAndom());
//		String[] strs = splitString("58.16.16.10,1.202.150.3,1.202.150.5", ",");
//		System.out.println(strs[0]);
//		System.out.println(findStringFormArrary(strs, "58.16.16.10"));
		
		System.out.println(getFixedLenString(8, "*"));
	}

	public static String Translate(Object object, String def) {
		if(object == null || object.toString().equalsIgnoreCase( "null"))
			return def;
		return object.toString();
	}
	
	public static String getFixedLenString(int len, String c) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() < len) {
			sb.append(c);
		}
		return sb.toString();
	}
}