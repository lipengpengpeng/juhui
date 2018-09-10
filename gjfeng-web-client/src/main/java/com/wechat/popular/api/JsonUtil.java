package com.wechat.popular.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;


public class JsonUtil {

	private JsonUtil(){}

	public static <T> T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	
	public static Map jsonObjectToMap(JSONObject jsonObj){
			Iterator<String> nameItr = jsonObj.keys();
			String name;
			Map<String, String> outMap = new HashMap<String, String>();
			while (nameItr.hasNext()) {
				name = nameItr.next();
				outMap.put(name, jsonObj.getString(name));
			}
			return outMap;
	}
}
