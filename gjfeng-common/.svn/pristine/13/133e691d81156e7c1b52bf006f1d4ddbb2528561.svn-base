package cc.messcat.gjfeng.common.fastpay.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class NetXmlUtils {
	
	public static String formatXml(String xmlStr) throws Exception {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new Exception("解析XML串失败：" + e.getMessage());
		}
		StringWriter writer = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		//format.setEncoding("gb2312");
		XMLWriter xmlwriter = new XMLWriter(writer, format);
		try {
			xmlwriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	/**
	 * 把XML串转化为MAP
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> Xml2MapAndValidate(String xmlStr,String key) throws Exception {
		Map<String,String> acMap = new HashMap<String,String>();
		Document acDoc = null;
		String md5Desc2 = "";
		try {
			acDoc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new Exception("解析XML串失败：" + e.getMessage() + ",xmlStr=" + xmlStr);
		}
		Element root = acDoc.getRootElement();
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if(!e.getName().equals("md5ConSec")){
				acMap.put(e.getName(), e.getText());
				sb.append(e.getText());
			}else
			{
				md5Desc2 = e.getText();
			}
		}
//		System.out.println(sb.toString());
		String md5Desc = Base.md5s(sb.toString(), key);	
		if(!md5Desc.equals(md5Desc2))
			throw new Exception("验证签名失败！");
		return acMap;
	}
	
	/**
	 * 把XML串转化为MAP
	 * @param xmlStr
	 * @param signSeq 验签顺序
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> Xml2MapAndValidate(String xmlStr,String key,String signSeq) throws Exception {
		Map<String,String> acMap = new HashMap<String,String>();
		Document acDoc = null;
		String md5Desc2 = "";
		try {
			acDoc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new Exception("解析XML串失败：" + e.getMessage() + ",xmlStr=" + xmlStr);
		}
		Element root = acDoc.getRootElement();
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if(!e.getName().equals("md5ConSec")){
				acMap.put(e.getName(), e.getText());
//				sb.append(e.getText());
			}else
			{
				md5Desc2 = e.getText();
			}
		}
		
		String[] nodeArray = signSeq.split(",");
		for(int i=0;i < nodeArray.length;i++){
			sb.append(Base.Translate(acMap.get(nodeArray[i]),"")) ; 
		}
		System.out.println(sb.toString());
		String md5Desc = Base.md5s(sb.toString(), key);	
		if(!md5Desc.equals(md5Desc2))
			throw new Exception("验证签名失败！");
		return acMap;
	}
	
	public static String genXmlFromMapAndSign2(String nodeStr,Map<String,String> map,String key,String rootNodeName) throws Exception{
		String xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		xmlStr += "<" + rootNodeName + ">";
		String[] nodeArray = nodeStr.split(",");
		StringBuffer sb = new StringBuffer();
		for(int i=0;i < nodeArray.length;i++){
			xmlStr += "<" + nodeArray[i] + ">"  + Base.Translate(map.get(nodeArray[i]), "") + "</"  + nodeArray[i] + ">";
			sb.append(Base.Translate(map.get(nodeArray[i]),"")); 
		}
		System.out.println("签名原始串：" + sb.toString());
		String md5Desc = Base.md5s(sb.toString(), key);
		xmlStr += "<md5ConSec>"  + md5Desc + "</md5ConSec>";
		xmlStr += "</" + rootNodeName + ">";
		return xmlStr; 
	}
	
	/**
	 * 把XML串转化为MAP
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> Xml2Map(String xmlStr) throws Exception {
		Map<String,String> acMap = new HashMap<String,String>();
		Document acDoc = null;
		try {
			acDoc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new Exception("解析XML串失败：" + e.getMessage() + ",xmlStr=" + xmlStr);
		}
		Element root = acDoc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if(!e.getName().equals("md5ConSec")){
				acMap.put(e.getName(), e.getText());
			}
		}
		return acMap;
	}
}

