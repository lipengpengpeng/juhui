package cc.modules.util;


/**
 * 
 * 验证规则，使用正则表达式验证邮箱，手机号之类的
 * 
 * @author Lich Li
 * 
 *
 */
public class ValidationRule {

	
	//是否是手机号码,要根据目前开发的手机段来更新
	public static boolean isMobile(String mobile){		
		//String regex="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$";
		String regex="^(1[1-9])\\d{9}$";
		return isMatch(regex, mobile);

	}
	
	//是否是邮箱
	public static boolean isEmail(String email){
		String regex="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return isMatch(regex, email);
	}
	
	//身份证
	public static boolean isIdCard(String idCard){
		String regex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
		return isMatch(regex, idCard);
	}
	
	//qq号
	public static boolean isQQ(String qqNum){
		String regex = "[1-9][0-9]{4,}";
		return isMatch(regex, qqNum);
	}
	
	//银行卡号验证
	public static boolean isBankCard(String bank_card){
		String regex = "^(\\d{16}|\\d{19})$";
		return isMatch(regex, bank_card);
	}
	
 
	//描述：匹配数据
	private static boolean isMatch(String regex,String param){
		if(param == null || param.equals("")){
			return false;
		}
		return param.matches(regex);
	}
	
	
	
}
