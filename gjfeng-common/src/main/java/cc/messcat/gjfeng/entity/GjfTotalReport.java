package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class GjfTotalReport implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 130657141664048813L;

	
	private Long id;
	
	
	private BigDecimal benerfitTradeMoney;
	

	private BigDecimal benerfitDateMoney;
	

	private BigDecimal benerfitWeipayMoney;
	

	private BigDecimal benerfitYinlMoney;
	

	private BigDecimal benerfitSxMoney;
	

	private BigDecimal mallWeipayMoney;
	
	
	private BigDecimal mallYinlpayMoney;
	

	private BigDecimal mallZerenpayMoney;
	

	private BigDecimal mallBanlanMoney;
	

	private BigDecimal shouxinLinesMoney;
	

	private BigDecimal shouxinWeixinpayMoney;
	

	private BigDecimal shouxinYinlPayMoney;
	

	private BigDecimal shouxinHoutaipayMoney;
	

	private BigDecimal shouxinConsumptionMoney;
	

	private BigDecimal shouxinShenyuMoney;
	

	private BigDecimal totalWeipayMoney;
	

	private BigDecimal totalWeixShouxupayMoney;
	

	private BigDecimal totalYinlpayMoney;
	

	private BigDecimal totalYinlShouxuMoney;
	

	private BigDecimal totalWeiXinGetMoney;
	

	private BigDecimal totalYinlGetMoney;
	

	private BigDecimal memberAddDivi;
	

	private BigDecimal memberCanCanYu_Divi;
	

	private BigDecimal memberDanJia;
	

	private BigDecimal memberShiJiPayMoney;
	

	private BigDecimal memberPoolMoney;
	

	private BigDecimal memberChaYiMoney;
	

	private BigDecimal merchantsAddDivi;
	

	private BigDecimal merchantsCanCanyuDivi;
	

	private BigDecimal merchantsDanjia;
	

	private BigDecimal merchantsShijipayMoney;
	

	private BigDecimal merchantsPoolMoney;
	

	private BigDecimal merchantsChayiMoney;
	

	private BigDecimal zhituiMemberMoney;
	

	private BigDecimal zhituiMerchantsMoney;
	

	private BigDecimal totalBanlanDateMoney;
	
	
	private BigDecimal totalBanlanPoolMoney;
	

	private BigDecimal totalBanlanZhituiMoney;
	

	private BigDecimal totalBanlanChayiMoney;
	

	private BigDecimal withdrawalMoney;
	

	private BigDecimal withdrawalShuishouMoney;
	

	private BigDecimal withdrawalZenrenMoney;
	

	private BigDecimal withdrawalDaozMoney;
	

	private BigDecimal withdrawalShouxuMoney;
	

	private BigDecimal memberBanlanFafangMoney;
	

	private BigDecimal memberBanlanHuaChuMoney;
	

	private BigDecimal memberBanlanXianFeiMoney;
	

	private BigDecimal memberBanlanShenyuMoney;
	

	private BigDecimal zenrenDateMoney;
	

	private BigDecimal zenrenXiaoHouMoney;
	

	private BigDecimal zenrenShenyuMoney;
	

	private Integer memberNumber;
	

	private Integer merchantsNumber;
	

	private BigDecimal gedaiMoney;
	

	private BigDecimal ereadaiMoney;
	

	private BigDecimal citydaiMoney;
	

	private BigDecimal merchantsPoolTotalYiShou;
	

	private BigDecimal pingTaiTotalYiShou;
	

	private BigDecimal shijiShoukuaiMoney;
	

	private BigDecimal zhichuDShijiPayMoney;
	

	private BigDecimal yishouYuYinShouMoney;
	

	private BigDecimal yiShouShiJiYiShouMoney;
	
	private Date addTime;

	private BigDecimal benerfitZhibaoMoney;
	
	private BigDecimal mallZhibaoMoney;
	
	private BigDecimal shouxinZhibaoMoney;
	
	private BigDecimal totalZhifubaoMoney;
	
	private BigDecimal totalZhifubaoShouxupayMoney;
	
	private BigDecimal totalZhifubaoGetMoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBenerfitTradeMoney() {
		return benerfitTradeMoney;
	}

	public void setBenerfitTradeMoney(BigDecimal benerfitTradeMoney) {
		this.benerfitTradeMoney = benerfitTradeMoney;
	}

	public BigDecimal getBenerfitDateMoney() {
		return benerfitDateMoney;
	}

	public void setBenerfitDateMoney(BigDecimal benerfitDateMoney) {
		this.benerfitDateMoney = benerfitDateMoney;
	}

	public BigDecimal getBenerfitWeipayMoney() {
		return benerfitWeipayMoney;
	}

	public void setBenerfitWeipayMoney(BigDecimal benerfitWeipayMoney) {
		this.benerfitWeipayMoney = benerfitWeipayMoney;
	}

	public BigDecimal getBenerfitYinlMoney() {
		return benerfitYinlMoney;
	}

	public void setBenerfitYinlMoney(BigDecimal benerfitYinlMoney) {
		this.benerfitYinlMoney = benerfitYinlMoney;
	}

	public BigDecimal getBenerfitSxMoney() {
		return benerfitSxMoney;
	}

	public void setBenerfitSxMoney(BigDecimal benerfitSxMoney) {
		this.benerfitSxMoney = benerfitSxMoney;
	}

	public BigDecimal getMallWeipayMoney() {
		return mallWeipayMoney;
	}

	public void setMallWeipayMoney(BigDecimal mallWeipayMoney) {
		this.mallWeipayMoney = mallWeipayMoney;
	}

	public BigDecimal getMallYinlpayMoney() {
		return mallYinlpayMoney;
	}

	public void setMallYinlpayMoney(BigDecimal mallYinlpayMoney) {
		this.mallYinlpayMoney = mallYinlpayMoney;
	}

	public BigDecimal getMallZerenpayMoney() {
		return mallZerenpayMoney;
	}

	public void setMallZerenpayMoney(BigDecimal mallZerenpayMoney) {
		this.mallZerenpayMoney = mallZerenpayMoney;
	}

	public BigDecimal getMallBanlanMoney() {
		return mallBanlanMoney;
	}

	public void setMallBanlanMoney(BigDecimal mallBanlanMoney) {
		this.mallBanlanMoney = mallBanlanMoney;
	}

	public BigDecimal getShouxinLinesMoney() {
		return shouxinLinesMoney;
	}

	public void setShouxinLinesMoney(BigDecimal shouxinLinesMoney) {
		this.shouxinLinesMoney = shouxinLinesMoney;
	}

	public BigDecimal getShouxinWeixinpayMoney() {
		return shouxinWeixinpayMoney;
	}

	public void setShouxinWeixinpayMoney(BigDecimal shouxinWeixinpayMoney) {
		this.shouxinWeixinpayMoney = shouxinWeixinpayMoney;
	}

	public BigDecimal getShouxinYinlPayMoney() {
		return shouxinYinlPayMoney;
	}

	public void setShouxinYinlPayMoney(BigDecimal shouxinYinlPayMoney) {
		this.shouxinYinlPayMoney = shouxinYinlPayMoney;
	}

	public BigDecimal getShouxinHoutaipayMoney() {
		return shouxinHoutaipayMoney;
	}

	public void setShouxinHoutaipayMoney(BigDecimal shouxinHoutaipayMoney) {
		this.shouxinHoutaipayMoney = shouxinHoutaipayMoney;
	}

	public BigDecimal getShouxinConsumptionMoney() {
		return shouxinConsumptionMoney;
	}

	public void setShouxinConsumptionMoney(BigDecimal shouxinConsumptionMoney) {
		this.shouxinConsumptionMoney = shouxinConsumptionMoney;
	}

	public BigDecimal getShouxinShenyuMoney() {
		return shouxinShenyuMoney;
	}

	public void setShouxinShenyuMoney(BigDecimal shouxinShenyuMoney) {
		this.shouxinShenyuMoney = shouxinShenyuMoney;
	}

	public BigDecimal getTotalWeipayMoney() {
		return totalWeipayMoney;
	}

	public void setTotalWeipayMoney(BigDecimal totalWeipayMoney) {
		this.totalWeipayMoney = totalWeipayMoney;
	}

	public BigDecimal getTotalWeixShouxupayMoney() {
		return totalWeixShouxupayMoney;
	}

	public void setTotalWeixShouxupayMoney(BigDecimal totalWeixShouxupayMoney) {
		this.totalWeixShouxupayMoney = totalWeixShouxupayMoney;
	}

	public BigDecimal getTotalYinlpayMoney() {
		return totalYinlpayMoney;
	}

	public void setTotalYinlpayMoney(BigDecimal totalYinlpayMoney) {
		this.totalYinlpayMoney = totalYinlpayMoney;
	}

	public BigDecimal getTotalYinlShouxuMoney() {
		return totalYinlShouxuMoney;
	}

	public void setTotalYinlShouxuMoney(BigDecimal totalYinlShouxuMoney) {
		this.totalYinlShouxuMoney = totalYinlShouxuMoney;
	}

	public BigDecimal getTotalWeiXinGetMoney() {
		return totalWeiXinGetMoney;
	}

	public void setTotalWeiXinGetMoney(BigDecimal totalWeiXinGetMoney) {
		this.totalWeiXinGetMoney = totalWeiXinGetMoney;
	}

	public BigDecimal getTotalYinlGetMoney() {
		return totalYinlGetMoney;
	}

	public void setTotalYinlGetMoney(BigDecimal totalYinlGetMoney) {
		this.totalYinlGetMoney = totalYinlGetMoney;
	}

	public BigDecimal getMemberAddDivi() {
		return memberAddDivi;
	}

	public void setMemberAddDivi(BigDecimal memberAddDivi) {
		this.memberAddDivi = memberAddDivi;
	}

	public BigDecimal getMemberCanCanYu_Divi() {
		return memberCanCanYu_Divi;
	}

	public void setMemberCanCanYu_Divi(BigDecimal memberCanCanYu_Divi) {
		this.memberCanCanYu_Divi = memberCanCanYu_Divi;
	}

	public BigDecimal getMemberDanJia() {
		return memberDanJia;
	}

	public void setMemberDanJia(BigDecimal memberDanJia) {
		this.memberDanJia = memberDanJia;
	}

	public BigDecimal getMemberShiJiPayMoney() {
		return memberShiJiPayMoney;
	}

	public void setMemberShiJiPayMoney(BigDecimal memberShiJiPayMoney) {
		this.memberShiJiPayMoney = memberShiJiPayMoney;
	}

	public BigDecimal getMemberPoolMoney() {
		return memberPoolMoney;
	}

	public void setMemberPoolMoney(BigDecimal memberPoolMoney) {
		this.memberPoolMoney = memberPoolMoney;
	}

	public BigDecimal getMemberChaYiMoney() {
		return memberChaYiMoney;
	}

	public void setMemberChaYiMoney(BigDecimal memberChaYiMoney) {
		this.memberChaYiMoney = memberChaYiMoney;
	}

	public BigDecimal getMerchantsAddDivi() {
		return merchantsAddDivi;
	}

	public void setMerchantsAddDivi(BigDecimal merchantsAddDivi) {
		this.merchantsAddDivi = merchantsAddDivi;
	}

	public BigDecimal getMerchantsCanCanyuDivi() {
		return merchantsCanCanyuDivi;
	}

	public void setMerchantsCanCanyuDivi(BigDecimal merchantsCanCanyuDivi) {
		this.merchantsCanCanyuDivi = merchantsCanCanyuDivi;
	}

	public BigDecimal getMerchantsDanjia() {
		return merchantsDanjia;
	}

	public void setMerchantsDanjia(BigDecimal merchantsDanjia) {
		this.merchantsDanjia = merchantsDanjia;
	}

	public BigDecimal getMerchantsShijipayMoney() {
		return merchantsShijipayMoney;
	}

	public void setMerchantsShijipayMoney(BigDecimal merchantsShijipayMoney) {
		this.merchantsShijipayMoney = merchantsShijipayMoney;
	}

	public BigDecimal getMerchantsPoolMoney() {
		return merchantsPoolMoney;
	}

	public void setMerchantsPoolMoney(BigDecimal merchantsPoolMoney) {
		this.merchantsPoolMoney = merchantsPoolMoney;
	}

	public BigDecimal getMerchantsChayiMoney() {
		return merchantsChayiMoney;
	}

	public void setMerchantsChayiMoney(BigDecimal merchantsChayiMoney) {
		this.merchantsChayiMoney = merchantsChayiMoney;
	}

	public BigDecimal getZhituiMemberMoney() {
		return zhituiMemberMoney;
	}

	public void setZhituiMemberMoney(BigDecimal zhituiMemberMoney) {
		this.zhituiMemberMoney = zhituiMemberMoney;
	}

	public BigDecimal getZhituiMerchantsMoney() {
		return zhituiMerchantsMoney;
	}

	public void setZhituiMerchantsMoney(BigDecimal zhituiMerchantsMoney) {
		this.zhituiMerchantsMoney = zhituiMerchantsMoney;
	}

	public BigDecimal getTotalBanlanDateMoney() {
		return totalBanlanDateMoney;
	}

	public void setTotalBanlanDateMoney(BigDecimal totalBanlanDateMoney) {
		this.totalBanlanDateMoney = totalBanlanDateMoney;
	}

	public BigDecimal getTotalBanlanPoolMoney() {
		return totalBanlanPoolMoney;
	}

	public void setTotalBanlanPoolMoney(BigDecimal totalBanlanPoolMoney) {
		this.totalBanlanPoolMoney = totalBanlanPoolMoney;
	}

	public BigDecimal getTotalBanlanZhituiMoney() {
		return totalBanlanZhituiMoney;
	}

	public void setTotalBanlanZhituiMoney(BigDecimal totalBanlanZhituiMoney) {
		this.totalBanlanZhituiMoney = totalBanlanZhituiMoney;
	}

	public BigDecimal getTotalBanlanChayiMoney() {
		return totalBanlanChayiMoney;
	}

	public void setTotalBanlanChayiMoney(BigDecimal totalBanlanChayiMoney) {
		this.totalBanlanChayiMoney = totalBanlanChayiMoney;
	}

	public BigDecimal getWithdrawalMoney() {
		return withdrawalMoney;
	}

	public void setWithdrawalMoney(BigDecimal withdrawalMoney) {
		this.withdrawalMoney = withdrawalMoney;
	}

	public BigDecimal getWithdrawalShuishouMoney() {
		return withdrawalShuishouMoney;
	}

	public void setWithdrawalShuishouMoney(BigDecimal withdrawalShuishouMoney) {
		this.withdrawalShuishouMoney = withdrawalShuishouMoney;
	}

	public BigDecimal getWithdrawalZenrenMoney() {
		return withdrawalZenrenMoney;
	}

	public void setWithdrawalZenrenMoney(BigDecimal withdrawalZenrenMoney) {
		this.withdrawalZenrenMoney = withdrawalZenrenMoney;
	}

	public BigDecimal getWithdrawalDaozMoney() {
		return withdrawalDaozMoney;
	}

	public void setWithdrawalDaozMoney(BigDecimal withdrawalDaozMoney) {
		this.withdrawalDaozMoney = withdrawalDaozMoney;
	}

	public BigDecimal getWithdrawalShouxuMoney() {
		return withdrawalShouxuMoney;
	}

	public void setWithdrawalShouxuMoney(BigDecimal withdrawalShouxuMoney) {
		this.withdrawalShouxuMoney = withdrawalShouxuMoney;
	}

	public BigDecimal getMemberBanlanFafangMoney() {
		return memberBanlanFafangMoney;
	}

	public void setMemberBanlanFafangMoney(BigDecimal memberBanlanFafangMoney) {
		this.memberBanlanFafangMoney = memberBanlanFafangMoney;
	}

	public BigDecimal getMemberBanlanHuaChuMoney() {
		return memberBanlanHuaChuMoney;
	}

	public void setMemberBanlanHuaChuMoney(BigDecimal memberBanlanHuaChuMoney) {
		this.memberBanlanHuaChuMoney = memberBanlanHuaChuMoney;
	}

	public BigDecimal getMemberBanlanXianFeiMoney() {
		return memberBanlanXianFeiMoney;
	}

	public void setMemberBanlanXianFeiMoney(BigDecimal memberBanlanXianFeiMoney) {
		this.memberBanlanXianFeiMoney = memberBanlanXianFeiMoney;
	}

	public BigDecimal getMemberBanlanShenyuMoney() {
		return memberBanlanShenyuMoney;
	}

	public void setMemberBanlanShenyuMoney(BigDecimal memberBanlanShenyuMoney) {
		this.memberBanlanShenyuMoney = memberBanlanShenyuMoney;
	}

	public BigDecimal getZenrenDateMoney() {
		return zenrenDateMoney;
	}

	public void setZenrenDateMoney(BigDecimal zenrenDateMoney) {
		this.zenrenDateMoney = zenrenDateMoney;
	}

	public BigDecimal getZenrenXiaoHouMoney() {
		return zenrenXiaoHouMoney;
	}

	public void setZenrenXiaoHouMoney(BigDecimal zenrenXiaoHouMoney) {
		this.zenrenXiaoHouMoney = zenrenXiaoHouMoney;
	}

	public BigDecimal getZenrenShenyuMoney() {
		return zenrenShenyuMoney;
	}

	public void setZenrenShenyuMoney(BigDecimal zenrenShenyuMoney) {
		this.zenrenShenyuMoney = zenrenShenyuMoney;
	}

	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public Integer getMerchantsNumber() {
		return merchantsNumber;
	}

	public void setMerchantsNumber(Integer merchantsNumber) {
		this.merchantsNumber = merchantsNumber;
	}

	public BigDecimal getGedaiMoney() {
		return gedaiMoney;
	}

	public void setGedaiMoney(BigDecimal gedaiMoney) {
		this.gedaiMoney = gedaiMoney;
	}

	public BigDecimal getEreadaiMoney() {
		return ereadaiMoney;
	}

	public void setEreadaiMoney(BigDecimal ereadaiMoney) {
		this.ereadaiMoney = ereadaiMoney;
	}

	public BigDecimal getCitydaiMoney() {
		return citydaiMoney;
	}

	public void setCitydaiMoney(BigDecimal citydaiMoney) {
		this.citydaiMoney = citydaiMoney;
	}

	public BigDecimal getMerchantsPoolTotalYiShou() {
		return merchantsPoolTotalYiShou;
	}

	public void setMerchantsPoolTotalYiShou(BigDecimal merchantsPoolTotalYiShou) {
		this.merchantsPoolTotalYiShou = merchantsPoolTotalYiShou;
	}

	public BigDecimal getPingTaiTotalYiShou() {
		return pingTaiTotalYiShou;
	}

	public void setPingTaiTotalYiShou(BigDecimal pingTaiTotalYiShou) {
		this.pingTaiTotalYiShou = pingTaiTotalYiShou;
	}

	public BigDecimal getShijiShoukuaiMoney() {
		return shijiShoukuaiMoney;
	}

	public void setShijiShoukuaiMoney(BigDecimal shijiShoukuaiMoney) {
		this.shijiShoukuaiMoney = shijiShoukuaiMoney;
	}

	public BigDecimal getZhichuDShijiPayMoney() {
		return zhichuDShijiPayMoney;
	}

	public void setZhichuDShijiPayMoney(BigDecimal zhichuDShijiPayMoney) {
		this.zhichuDShijiPayMoney = zhichuDShijiPayMoney;
	}

	public BigDecimal getYishouYuYinShouMoney() {
		return yishouYuYinShouMoney;
	}

	public void setYishouYuYinShouMoney(BigDecimal yishouYuYinShouMoney) {
		this.yishouYuYinShouMoney = yishouYuYinShouMoney;
	}

	public BigDecimal getYiShouShiJiYiShouMoney() {
		return yiShouShiJiYiShouMoney;
	}

	public void setYiShouShiJiYiShouMoney(BigDecimal yiShouShiJiYiShouMoney) {
		this.yiShouShiJiYiShouMoney = yiShouShiJiYiShouMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getBenerfitZhibaoMoney() {
		return benerfitZhibaoMoney;
	}

	public void setBenerfitZhibaoMoney(BigDecimal benerfitZhibaoMoney) {
		this.benerfitZhibaoMoney = benerfitZhibaoMoney;
	}

	public BigDecimal getMallZhibaoMoney() {
		return mallZhibaoMoney;
	}

	public void setMallZhibaoMoney(BigDecimal mallZhibaoMoney) {
		this.mallZhibaoMoney = mallZhibaoMoney;
	}

	public BigDecimal getShouxinZhibaoMoney() {
		return shouxinZhibaoMoney;
	}

	public void setShouxinZhibaoMoney(BigDecimal shouxinZhibaoMoney) {
		this.shouxinZhibaoMoney = shouxinZhibaoMoney;
	}

	public BigDecimal getTotalZhifubaoMoney() {
		return totalZhifubaoMoney;
	}

	public void setTotalZhifubaoMoney(BigDecimal totalZhifubaoMoney) {
		this.totalZhifubaoMoney = totalZhifubaoMoney;
	}

	public BigDecimal getTotalZhifubaoShouxupayMoney() {
		return totalZhifubaoShouxupayMoney;
	}

	public void setTotalZhifubaoShouxupayMoney(BigDecimal totalZhifubaoShouxupayMoney) {
		this.totalZhifubaoShouxupayMoney = totalZhifubaoShouxupayMoney;
	}

	public BigDecimal getTotalZhifubaoGetMoney() {
		return totalZhifubaoGetMoney;
	}

	public void setTotalZhifubaoGetMoney(BigDecimal totalZhifubaoGetMoney) {
		this.totalZhifubaoGetMoney = totalZhifubaoGetMoney;
	}
	

}
