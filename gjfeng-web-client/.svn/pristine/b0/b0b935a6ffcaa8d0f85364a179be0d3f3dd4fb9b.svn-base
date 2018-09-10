package cc.messcat.gjfeng.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.StringUtil;

/**
 * 
 * 区分中英文字符的截取字符串规则
 * JSP自定义标签、函数
 * 
 * @author andyxixi
 * 
 */

public class DateHandleTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String value;
	private String pattern;
	
	public int doEndTag() throws JspException {
		String vv = String.valueOf(value);
		if(StringUtil.isBlank(vv)||"null".equals(vv)||"\"null\"".equals(vv)){
			try {
				pageContext.getOut().write("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return super.doStartTag();
		}
		long time = 0;
		String s = "";
		try{
			time = Long.valueOf(vv); 			
			s = DateHelper.timeStampToDateString(time,pattern);
			//s=DateHelper.dataToString(DateHelper.strToDate(s), pattern);
		}catch (Exception e) {
			e.printStackTrace();
			//s = vv.substring(0,vv.indexOf("."));
		}
		try {  
			pageContext.getOut().write(s);  
		} catch (IOException e) {  
			e.printStackTrace();  
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

}