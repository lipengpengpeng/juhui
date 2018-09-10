package cc.modules.util;

/**
 * @author handsomeWU
 *
 */
public class ClearHtmlTaglibs {

	public final static String clearHtmlTaglib(String content) {
		String temp = "";

		return temp;
	}

	/**
	 * @param listString
	 * @return
	 * 链接多段字符创为一个字符串
	 */
	public final static String listString(String[] str) {

		StringBuffer result = new StringBuffer("");
		result.append(str[0] == null ? "" : str[0]);
		result.append(str[1] == null ? "" : str[1]);
		result.append(str[2] == null ? "" : str[2]);
		result.append(str[3] == null ? "" : str[3]);
		result.append(str[4] == null ? "" : str[4]);
		result.append(str[5] == null ? "" : str[5]);
		result.append(str[6] == null ? "" : str[6]);
		result.append(str[7] == null ? "" : str[7]);
		result.append(str[8] == null ? "" : str[8]);
		result.append(str[9] == null ? "" : str[9]);

		return result.toString();

	}

	public final static String[] listString(String str1) {
		String[] temp = new String[10];

		if (str1.length() > 8000) {
			temp[0] = str1.substring(0, 7999);
			if (str1.length() > 16000) {
				temp[1] = str1.substring(8000, 15999);
				if (str1.length() > 23999) {
					temp[2] = str1.substring(16000, 23999);
					if (str1.length() > 31000) {
						temp[3] = str1.substring(24000, 31999);
						if (str1.length() > 39999) {
							temp[4] = str1.substring(32000, 39999);
							if (str1.length() > 47999) {
								temp[5] = str1.substring(40000, 47999);
								if (str1.length() > 55999) {
									temp[6] = str1.substring(48000, 55999);
									if (str1.length() > 63999) {
										temp[7] = str1.substring(56000, 63999);

										if (str1.length() > 71999) {
											temp[8] = str1.substring(64000, 71999);

											if (str1.length() > 79999) {
												temp[9] = str1.substring(72000, 79999);
											} else
												temp[9] = str1.substring(72000, str1.length());
										} else
											temp[8] = str1.substring(64000, str1.length() - 1);
									} else
										temp[7] = str1.substring(56000, str1.length() - 1);
								} else
									temp[6] = str1.substring(48000, str1.length() - 1);
							} else
								temp[5] = str1.substring(40000, str1.length() - 1);
						} else
							temp[4] = str1.substring(32000, str1.length() - 1);
					} else
						temp[3] = str1.substring(24000, str1.length() - 1);
				} else
					temp[2] = str1.substring(16000, str1.length() - 1);

			} else
				temp[1] = str1.substring(8000, str1.length() - 1);
		} else
			temp[0] = str1;

		return temp;

	}

	public final static String[] stringToList(String str0, String str1, String str2, String str3, String str4, String str5,
		String str6, String str7, String str8, String str9) {
		String[] temp = new String[10];

		temp[0] = str0;
		temp[1] = str1;
		temp[2] = str2;
		temp[3] = str3;
		temp[4] = str4;
		temp[5] = str5;
		temp[6] = str6;
		temp[7] = str7;
		temp[8] = str8;
		temp[9] = str9;

		return temp;
	}

	public final static String listString(String str1, String str2, String str3) {

		StringBuffer result = new StringBuffer("");
		result.append(str1 == null ? "" : str1);
		result.append(str2 == null ? "" : str2);
		result.append(str3 == null ? "" : str3);
		return result.toString();

	}

}
