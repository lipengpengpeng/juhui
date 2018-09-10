package cc.modules.util;

import java.io.UnsupportedEncodingException;

public class StrLen {

	public static String splitStr(String str, int len) throws UnsupportedEncodingException {

		String result = str.substring(0, len);

		if (str.length() > len)
			result += "...";

		return result;
	}
}
