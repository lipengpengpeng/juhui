package cc.messcat.gjfeng.entity;

public class GjfWeiXinPayInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2668975741778874233L;
	
	private Long id;
	
	private String mchId;//微信商户号
	
	private String payKey;//微信秘钥
	
	private String status;//状态 0禁用 1启用
	
	private String partner;
	
	private String partnerKey;
	
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
