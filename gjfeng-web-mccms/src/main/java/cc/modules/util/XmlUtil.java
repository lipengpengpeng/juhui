package cc.modules.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml问价工具
 * @author Administrator
 *
 */
public class XmlUtil {

	/**
	 * 通过字符串解析成xml，再将xml解析成map对象
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> parseStringXmlToMap(StringBuffer str) {
		
		if (str != null && !"".equals(str.toString().trim())) {
			//解析String
			try {
				Map<String,Object> result = new HashMap<String, Object>();
				Document document = DocumentHelper.parseText(str.toString());
				//获取根节点
				Element root = document.getRootElement();
				result = getMap(root);
				return result;
			} catch (DocumentException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String,Object> getMap(Element root) {
		Map<String,Object> result = new HashMap<String, Object>();
		Iterator it = root.elementIterator();
		while(it.hasNext()) {
			Element people = (Element) it.next();
			result.put(people.getName(), people.getText());
		}
		return result;
	}
	
	public static void main(String[] args) {
		parseStringXmlToMap(new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><GetNumResult xmlns=\"http://106.ihuyi.cn/\"><code>2</code><msg>查询成功</msg><num>19455</num></GetNumResult>"));
	}
}
