package com.h5pay.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.*;

import net.sf.json.JSONObject;


public class EncodingParamsUtils {
	private static Logger log = Logger.getLogger(EncodingParamsUtils.class);
	
	
	//params转Map
	public static Map<String,String> ParamsToMap(HttpServletRequest request){
		Map<String,String[]> requestMap = request.getParameterMap();
		Map<String,String> resultMap = new HashMap<String, String>();
		if(requestMap == null){
			return null;
		}
		for(Map.Entry<String, String[]> tmp : requestMap.entrySet()){
			String key = tmp.getKey();
			String[] values = tmp.getValue();
			String value = "";
			if(values == null){
				value = "";
			}else if(values instanceof String[]){
				for(int i=0;i<values.length;i++){
					value = values[i];
				}
				value = value.substring(0,value.length()-1);
			}else{
				value = values.toString();
			}
			resultMap.put(key,value);
		}
		return resultMap;
	}
	
	//params转JsonObject
	public static JSONObject paramsToJsonObject(HttpServletRequest request){
		JSONObject retJson = new JSONObject();
		Map<String,String[]> params = request.getParameterMap();
		if(params == null || params.size() == 0){
			return null;
		}
		for(Map.Entry<String, String[]> tmp : params.entrySet()){
			String name = tmp.getKey();
			String[] values = tmp.getValue();
			String value = "";
			if(values == null || values.length == 0){
				value = "";
			}else if(values instanceof String[]){
				for(int i=0;i<values.length;i++){
					value = values[i];
				}
				
			}else{
				value = values.toString();
			}
			retJson.put(name, value);
		}
		return retJson;
	}
	
	public static String jsonToPost(JSONObject reqJson,String columns){
		StringBuffer bf = new StringBuffer();
		String[] keys = columns.split(",");
		int i = 0;
		for(String key : keys){
			String value = reqJson.getString(key);
			if(value == null){
				value = "";
			}
			
			if(i!=keys.length-1){
				bf.append(key+"="+value+"&");
			}else{
				bf.append(key+"="+value);
			}
			i++;
		}
		
		return bf.toString();
	}
	
	public static String jsonToPost(JSONObject reqJson){
		StringBuffer bf = new StringBuffer();
		//String[] keys = (String[])reqJson.keySet().toArray();
		Set<String>keys = (Set<String>)reqJson.keySet();
		int i = 0;
		for(String key : keys){
			String value = reqJson.getString(key);
			if(value == null){
				value = "";
			}
			
			if(i!=keys.size()-1){
				bf.append(key+"="+value+"&");
			}else{
				bf.append(key+"="+value);
			}
			i++;
		}
		
		return bf.toString();
	}
	
	
	//报文非空判断
	public static String judgeJson(JSONObject retData,String keys){
		String[] keyArray = keys.split(",");
		for(String key : keyArray){
			Object valueObj = retData.get(key);
			if(valueObj == null || valueObj == ""){
				return key;
			}
		}
		return "ok";
	}
	
	//原始报文串转Json
	public static JSONObject getParamsJson(String queryString, String enc) {
		JSONObject paramsMap = new JSONObject();
		if (queryString != null && queryString.length() > 0) {
			int ampersandIndex, lastAmpersandIndex = 0;
			String subStr, param, value;
			String[] paramPair, values, newValues;
			do {
				ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
				if (ampersandIndex > 0) {
					subStr = queryString.substring(lastAmpersandIndex,
							ampersandIndex - 1);
					lastAmpersandIndex = ampersandIndex;
				} else {
					subStr = queryString.substring(lastAmpersandIndex);
				}
				paramPair = subStr.split("=");
				param = paramPair[0];
				value = paramPair.length == 1 ? "" : paramPair[1];
				try {
					value = URLDecoder.decode(value, enc);
				} catch (UnsupportedEncodingException ignored) {
				}
				if (paramsMap.containsKey(param)) {
					values = (String[]) paramsMap.get(param);
					int len = values.length;
					newValues = new String[len + 1];
					System.arraycopy(values, 0, newValues, 0, len);
					newValues[len] = value;
				} else {
					newValues = new String[] { value };
				}
				
				
				String tmpValue  = "";
				if(newValues == null){
					tmpValue = "";
				}else if(newValues instanceof String[]){
					for(String val : newValues){
						tmpValue = tmpValue + val;
					}

				}else{
					tmpValue = newValues.toString();
				}
				
				
				
				paramsMap.put(param, tmpValue);
			} while (ampersandIndex > 0);
		}
		return paramsMap;
	}
	
	
	public static String json2Xml(JSONObject reqJson){
		StringBuffer bf = new StringBuffer();
		bf.append("<xml>");
		
		Set<String> keys = reqJson.keySet();
		for(String key : keys){
			bf.append("<"+key+">");
			bf.append(reqJson.get(key));
			bf.append("</"+key+">");
		}
		
		bf.append("</xml>");
		return bf.toString();
	}
	
	
	
	
	public static JSONObject xml2Json(String xmlString)
			throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		Element rootElement = doc.getRootElement();
		JSONObject reqJson = new JSONObject();
		ele2Json(reqJson, rootElement);
		return reqJson;
	}

	private static void ele2Json(JSONObject reqJson, Element ele) {
		@SuppressWarnings("unchecked")
		List<Element> elements = ele.elements();
		if (elements.size() == 0) {
			reqJson.put(ele.getName(), ele.getText().trim());
		} else if (elements.size() == 1) {
			JSONObject tmpJson = new JSONObject();
			ele2Json(reqJson, elements.get(0));
			reqJson.put(ele.getName(), tmpJson.get(ele.getName()));
		} else {
			JSONObject tmpJson = new JSONObject();
			for (Element element : elements) {
				tmpJson.put(element.getName(), null);
			}
			Set<String> keySet = tmpJson.keySet();
			for (String key : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				@SuppressWarnings("unchecked")
				List<Element> elements2 = ele
						.elements(new QName(key, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					for (Element element : elements2) {
						JSONObject tmpJson2 = new JSONObject();
						ele2Json(tmpJson2, element);
						list.add(tmpJson2);
					}
					reqJson.put(key, list);
				} else {
					// 同名的数量不大于1则直接递归去
					JSONObject tmpJson2 = new JSONObject();
					ele2Json(tmpJson2, elements2.get(0));
					reqJson.put(key, tmpJson2.get(key));
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("retCode", "500998");
		json.put("retMsg", "上送MD5签名不合法，请检查！");
		
		System.out.println(EncodingParamsUtils.jsonToPost(json));
	}
	
	
	
}
