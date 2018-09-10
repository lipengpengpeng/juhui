package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="gjf_product_comment")
public class GjfProductComment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 483802981345796034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID_")
	private GjfProductInfo product;//商品
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo member;//
	
	@Column(name = "CONTENT_", length = 200)
	private String content;//评论内容
	
	@Column(name = "COM_IMG_", length = 500)
	private String comImg;//评论图片
	
	@Column(name = "COM_SCORE_")
	private Integer comScore;//评分
	
	@Column(name = "COM_FATHER_ID_")
	private Long comFatherId;//父级评论
	
	@Column(name = "COM_TIME_")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date comTime;//评论时间


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfProductInfo getProduct() {
		return product;
	}

	public void setProduct(GjfProductInfo product) {
		this.product = product;
	}

	public GjfMemberInfo getMember() {
		return member;
	}

	public void setMember(GjfMemberInfo member) {
		this.member = member;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComImg() {
		return comImg;
	}

	public void setComImg(String comImg) {
		this.comImg = comImg;
	}

	public Integer getComScore() {
		return comScore;
	}

	public void setComScore(Integer comScore) {
		this.comScore = comScore;
	}

	public Long getComFatherId() {
		return comFatherId;
	}

	public void setComFatherId(Long comFatherId) {
		this.comFatherId = comFatherId;
	}

	public Date getComTime() {
		return comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	
}
