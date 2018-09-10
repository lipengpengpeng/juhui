package cc.modules.util;

/**
 * @author handsomeWS
 * 
 */
public class Const {

	//防止SQL注入的方法
	public static boolean voidSQL(String str) {
		int i;
		String tempStr = "";
		boolean str2 = true;
		for (i = 0; i < str.trim().length(); i++) {
			tempStr = str.substring(i, i + 1);
			if (tempStr.equals("=") || tempStr.equals("'") || tempStr.equals(":"))
				str2 = false;
			break;
		}
		return str2;
	}

}
