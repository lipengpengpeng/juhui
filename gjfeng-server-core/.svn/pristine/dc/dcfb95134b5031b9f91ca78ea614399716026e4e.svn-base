package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_product_view_history")
public class GjfProductViewHistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID_")
	private GjfProductInfo productId;
	
	@Column(name = "KEY_WORD_", length=50)
	private String keyWord;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

	public GjfProductViewHistory() {
		super();
	}

	public GjfProductViewHistory(Long id, GjfMemberInfo memberId, GjfProductInfo productId, String keyWord, Date addTime) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.productId = productId;
		this.keyWord = keyWord;
		this.addTime = addTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfProductInfo getProductId() {
		return productId;
	}

	public void setProductId(GjfProductInfo productId) {
		this.productId = productId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}


	public GjfMemberInfo getMemberId() {
		return memberId;
	}


	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}
}