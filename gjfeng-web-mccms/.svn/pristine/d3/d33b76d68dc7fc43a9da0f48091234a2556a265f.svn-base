package cc.modules.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 区分中英文字符的截取字符串规则
 * JSP自定义标签、函数
 * 
 * @author andyxixi
 * 
 */

public class CutStringTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Logger for this class
	 * 
	 */

	private static final Logger logger = LoggerFactory.getLogger(CutStringTag.class);

	String value;
	String mark = "";
	Integer size;

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		String html = cutString(value, size, mark);

		try {
			this.pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			logger.error("tag CutStringTag error", e);
		}

		return EVAL_PAGE;

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public static String cutString(String str, int len, String mark) {

		len = len * 2;
		StringBuffer sb = new StringBuffer();
		int counter = 0;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 255) {
				counter++;
			} else {
				counter = counter + 2;
			}

			if (counter > len) {
				String result = sb.toString().trim();
				result += mark;
				return result;
			}

			sb.append(c);

		}

		return sb.toString();

	}
}