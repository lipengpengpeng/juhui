package cc.modules.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cc.messcat.gjfeng.common.util.CalendarHelper;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.StringUtil;

/**
 * 时间转换自定义标签
 * 
 * @author Karhs
 * @date 2016-02-02 18:55
 */

public class DateTransTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String type;
	private String value;
	private String pattern;
	private String timeType;		//时间类型 0：普通时间格式  1：时间戳

	public int doEndTag() throws JspException {
		if(StringUtil.isBlank(pattern)){
			pattern = "yyyy-MM-dd";
		}
		if (StringUtil.isNotBlank(timeType) && timeType.equals("1")) {
			try {
				value=DateHelper.timeStampToDateString(Long.valueOf(value),"yyyy-MM-dd HH:mm:ss");
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotBlank(type)) {
			if ("add".equals(type)) {
				return addTimeStramp(value); 		// 时间相加

			} else if ("subtract".equals(type)) {
				return subtractTimeStamp(value); 	// 时间相减

			} else if ("prenow".equals(type)) {
				return preNow(value); 				// 当前时间减设定时间

			} else if ("afnow".equals(type)) {
				return afNow(value); 				// 设定时间减当前时间

			} else if ("none".equals(type)) {
				return timeStampToDate(value); 		// 时间戳转为date
				
			} else if ("week".equals(type)) {
				return dayForWeek(value); 		// 时间戳转为date
			}
		} else {
			return timeStampToDate(value);			// 时间戳转为date
		}
		return super.doStartTag();
	}

	/**
	 * 当前时间减去设定时间
	 * 
	 * @return
	 * @throws JspException
	 */
	public int preNow(String value) throws JspException {
		if (StringUtil.isNotBlank(value)) {
			try {
				Date date = DateHelper.timeStampToDate(Long.valueOf(value));
				int count = CalendarHelper.daysBetween(date, new Date());
				try {
					this.pageContext.getOut().write(String.valueOf(count));
				} catch (IOException e) {
					System.out.println("间隔时间输出错误");
					e.printStackTrace();
				}
			} catch (ParseException e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
		}
		return super.doStartTag();
	}

	/**
	 * 设定时间减去当前时间
	 * 
	 * @return
	 * @throws JspException
	 */
	public int afNow(String value) throws JspException {
		if (StringUtil.isNotBlank(value)) {
			try {
				Date date = DateHelper.timeStampToDate(Long.valueOf(value));
				int count = CalendarHelper.daysBetween(new Date(), date);
				try {
					this.pageContext.getOut().write(String.valueOf(count));
				} catch (IOException e) {
					System.out.println("间隔时间输出错误");
					e.printStackTrace();
				}
			} catch (ParseException e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
		}
		return super.doStartTag();
	}

	/**
	 * 设定两个时间戳相加
	 * 
	 * @return
	 * @throws JspException
	 */
	public int addTimeStramp(String value) throws JspException {
		// TODO
		return super.doStartTag();
	}

	/**
	 * 设定两个时间戳相减
	 * 
	 * @return
	 * @throws JspException
	 */
	public int subtractTimeStamp(String value) throws JspException {
		String str = String.valueOf(value);
		String[] strArr = str.split(",");
		if (null != strArr && !"".equals(strArr) && strArr.length > 1) {
			String startTimeStamp = strArr[0];
			String endTimeStamp = strArr[1];

			if ((StringUtil.isNotBlank(startTimeStamp) || !"null".equals(startTimeStamp) || !"\"null\"".equals(startTimeStamp))
				&& (StringUtil.isNotBlank(endTimeStamp) || !"null".equals(endTimeStamp) || !"\"null\"".equals(endTimeStamp))) {
				try {
					Date start = DateHelper.timeStampToDate(Long.valueOf(startTimeStamp));
					Date end = DateHelper.timeStampToDate(Long.valueOf(endTimeStamp));
					int count = CalendarHelper.daysBetween(start, end);
					this.pageContext.getOut().write(String.valueOf(count));
					return super.doStartTag();
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return super.doStartTag();
	}

	/**
	 * 时间戳转换为正常格式日期
	 * 
	 * @return
	 * @throws JspException
	 */
	public int timeStampToDate(String timeStamp) throws JspException {
		if (StringUtil.isBlank(timeStamp) || "null".equals(timeStamp) || "\"null\"".equals(timeStamp)) {
			try {
				pageContext.getOut().write("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return super.doStartTag();
		}
		String s = "";
		try {
			s=CalendarHelper.timeStampToDate(timeStamp, pattern);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("时间戳" + timeStamp + "转化日期失败！");
			s = timeStamp.substring(0, timeStamp.indexOf("."));
		}
		try {
			pageContext.getOut().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	
	/**
	 * 判断时间是周几
	 * @author Karhs
	 * @date 2016-03-29 17:38
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public int dayForWeek(String value) throws JspException {
		if (StringUtil.isNotBlank(value)) {
		try {
			Date date = DateHelper.stringToDate(value, pattern);
			String week = CalendarHelper.getWeekOfDate(date,"1");
			this.pageContext.getOut().write(String.valueOf(week));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		return super.doStartTag();
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

}