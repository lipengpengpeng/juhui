package cc.messcat.gjfeng.common.util;

import java.util.ArrayList;
import java.util.List;

public class FormatStringUtil {
	/**
	 * 按符号分割，返回列表
	 * @param str
	 * @param sign
	 * @return
	 */
	public static List<String> splitBySign(String str, String sign){
		if(!"".equals(str)){
			String [] strs = str.split(sign);
			List<String> strList = new ArrayList<String>();
			for(int i =0;i<strs.length;i++){
				strList.add(strs[i]);
			}
			return strList;
		}else {
			return null;
		}
	}
	
	public static String listToString(List<Object> list){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i < (list.size()-1)) {
				stringBuffer.append(list.get(i).toString()+",");
			}else {
				stringBuffer.append(list.get(i).toString());
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 修改字符串间隔符
	 * @return
	 */
	public static String changeStrSign(String str, String oldSign, String newSign){
		if(!"".equals(str)){
			String [] strs = str.split(oldSign);
			StringBuffer tempAttrValues = new StringBuffer();
			for(int i =0;i<strs.length;i++){
				if(i==strs.length-1){
					tempAttrValues.append(strs[i]);
				}else {
					tempAttrValues.append(strs[i]+newSign);
				}
			}
			return tempAttrValues.toString();
		}
		return str;
	}
}
