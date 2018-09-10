package cc.messcat.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.bases.BaseAction;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Users;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.modules.constants.ActionConstants;

/**
 * @author Messcat
 * @version 1.1
 * 
 */
public class DefaultAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties prop;
	private String isFine;
	private int epMessCount;
	private int userMessCount;
	private BigInteger memberAmount;//会员数量
	private Long productAmount;//商品数量
	private BigInteger sellerMemberAmount;//商户数量
	private int storeAmount; //门店数量
	private BigDecimal[] storeTurnover;
	private BigDecimal[] O2OTurnover;
	private BigDecimal orderTotalAmount;
	private String [] date;

	public String execute() throws Exception {
		try{
			//会员数量
			memberAmount = (BigInteger) countInfoDubbo.findMemberAmountByType("").getResult();
			//商品数量
			/*McProductInfo mcProductInfo = new McProductInfo();
			mcProductInfo.setAduitStatus("1");
			mcProductInfo.setStatus("1");*/
			productAmount = (long) mcProductInfoManagerDao.countTotalProduct();
			//商户数量
			sellerMemberAmount = (BigInteger) countInfoDubbo.findMemberAmountByType("1").getResult();

			storeTurnover = (BigDecimal[]) countInfoDubbo.findAlmostOneMonthStoreTurnover().getResult();
			
			O2OTurnover = (BigDecimal[]) countInfoDubbo.findAlmostOneMonth020Turnover().getResult();
			
			orderTotalAmount = (BigDecimal) countInfoDubbo.findOrderTotalAmount().getResult();
			
			this.date = getAlmostOneMonthDate();
			
			Pager pager = (Pager) storeInfoDubbo.findStoreByPager(1, 10, null, null, null, "1",null).getResult();
			//门店数量
			storeAmount =  pager.getRowCount();
			
			prop = System.getProperties();
			if (this.usersManagerDao.isFullInfo())
				isFine = "1";
			else
				isFine = "0";
			Map session = ActionContext.getContext().getSession();
			Users users = (Users) session.get("users");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ActionConstants.SUCCESS_KEY;
	}
	
	private String[] getAlmostOneMonthDate(){
		int n = 30;
		String [] date = new String[30];
		Calendar now = Calendar.getInstance();
		for (int i = 1; i <= date.length; i++) {
			now.add(Calendar.DATE, -1);
			String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
			date[n-i] = endDate;
		}
		return date;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getIsFine() {
		return isFine;
	}

	public void setIsFine(String isFine) {
		this.isFine = isFine;
	}

	public int getEpMessCount() {
		return epMessCount;
	}

	public void setEpMessCount(int epMessCount) {
		this.epMessCount = epMessCount;
	}

	public int getUserMessCount() {
		return userMessCount;
	}

	public void setUserMessCount(int userMessCount) {
		this.userMessCount = userMessCount;
	}


	public Long getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Long productAmount) {
		this.productAmount = productAmount;
	}


	public BigInteger getMemberAmount() {
		return memberAmount;
	}

	public void setMemberAmount(BigInteger memberAmount) {
		this.memberAmount = memberAmount;
	}

	public BigInteger getSellerMemberAmount() {
		return sellerMemberAmount;
	}

	public void setSellerMemberAmount(BigInteger sellerMemberAmount) {
		this.sellerMemberAmount = sellerMemberAmount;
	}

	public int getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(int storeAmount) {
		this.storeAmount = storeAmount;
	}

	public BigDecimal[] getStoreTurnover() {
		return storeTurnover;
	}

	public void setStoreTurnover(BigDecimal[] storeTurnover) {
		this.storeTurnover = storeTurnover;
	}

	public BigDecimal[] getO2OTurnover() {
		return O2OTurnover;
	}

	public void setO2OTurnover(BigDecimal[] o2oTurnover) {
		O2OTurnover = o2oTurnover;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public String[] getDate() {
		return date;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	
	
	
	

}