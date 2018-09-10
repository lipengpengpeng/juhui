package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_waixi_info")
public class GjfWeiXinPayInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2668975741778874233L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "PAY_MCH_ID", length = 200)
	private String mchId;//微信商户号
	
	@Column(name = "PAY_KEY", length = 200)
	private String payKey;//微信秘钥
	
	@Column(name = "STATUS", length = 1)
	private String status;//状态 0禁用 1启用
	
	@Column(name = "PARTNER", length = 300)
    private String partner;
	
	@Column(name = "PARTNERKEY", length = 300)
	private String partnerKey;
	
	@Column(name = "TYPE", length =1)
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
