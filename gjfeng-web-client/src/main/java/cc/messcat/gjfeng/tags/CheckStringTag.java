package cc.messcat.gjfeng.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import cc.messcat.gjfeng.common.util.StringUtil;

/**
 * 
 * 区分中英文字符的截取字符串规则
 * JSP自定义标签、函数
 * 
 * @author andyxixi
 * 
 */

public class CheckStringTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CheckStringTag.class);

	String contains;
	String value;

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		String html = checkString(value, contains);
		try {
			this.pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			logger.error("tag CutStringTag error", e);
		}

		return EVAL_PAGE;

	}
	
	public static String checkString(String value, String contains) {
		if(StringUtil.isNotBlank(value) && StringUtil.isNotBlank(contains)){
			String[] v = value.split(",");
			for (String str : v) {
				if (str.equals(contains)) {
					return "checked";
				}
			}
		}
		return "";
	}

	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		this.contains = contains;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}