package cc.modules.commons;

/**
 * 
 * @ClassName: MemberPointsUtil 会员积分计算工具
 * @Description: 会员金积分转换银积分，银积分转换金积分
 * @author StevenWang
 * @date 2016-7-1 下午04:52:38
 */
public class MemberPointsUtil {
	
	public static void main(String[] args) {
		System.out.println(MemberPointsUtil.silverpointsToGoldpoints(62));
		System.out.println(MemberPointsUtil.goldpointsToSilverpoints(50));
		System.out.println(MemberPointsUtil.sellerAccountsReceivable(50,0.2));
		System.out.println(MemberPointsUtil.buyerDueSilverPoints(50,40));
		System.out.println(MemberPointsUtil.buyerDueSilverPointsByRatio(50,0.2));
	}
	
	/**
	 * 银积分转换金积分
	 * @param silverPoints
	 * @return 银积分*16%  = 金积分
	 */
	public static double silverpointsToGoldpoints(double silverPoints) {
		return (int)(silverPoints * 16)/100;
	}
	
	/**
	 * 金积分转换银积分 
	 * @param goldpoints
	 * @return 金积分 / 16% = 银积分 
	 */
	public static double goldpointsToSilverpoints(double goldpoints) {
		return (int)(goldpoints * 100)/16;
	}
	
	/**
	 *  商家应收账款
	 * @param totalNum 现金或金积分
	 * @param ratio 返还百分比
	 * @return 现金或金积分 - (现金或金积分 x 返还百分比)  = 商家应收账款 
	 */
	public static double sellerAccountsReceivable(double totalNum,double ratio) {
		return totalNum - (totalNum * ratio);
	}
	
	/**
	 * 消费者应得的银积分
	 * @param totalNum 总的现金或金积分
	 * @param sellerAccountsReceivable 商家应收账款
	 * @return （现金或金积分 — 商家应收账款 ）÷ 16% = 消费者应得银积分数额 
	 */
	public static double buyerDueSilverPoints(double totalNum, double sellerAccountsReceivable) {
		return (int)(totalNum - sellerAccountsReceivable)*100/16;
	}
	
	/**
	 * 通过总现金或金积分，返还比例计算消费者应得的银积分
	 * @param totalNum 总现金或金积分
	 * @param ratio 返还百分比
	 * @return
	 */
	public static double buyerDueSilverPointsByRatio(double totalNum, double ratio) {
		return (int)(totalNum*ratio)*100/16;
	}

}
