package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfEnterpriseColumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_enterprise_column")
public class GjfEnterpriseColumn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "NAMES_", length = 50)
	private String names;
	
	@Column(name = "SHORT_NAME_", length = 50)
	private String shortName;
	
	@Column(name = "IS_VALID_IN_NAV_", length = 1)
	private String isValidInNav;
	
	@Column(name = "FRONT_NUM_", length = 20)
	private String frontNum;
	
	@Column(name = "ORDER_COLUMN_", length=20)
	private Long orderColumn;
	
	@ManyToOne
	@JoinColumn(name="TYPE_COLUMN_")
	private GjfPageType typeColumn;
	
	@Column(name = "LINK_URL_", length = 200)
	private String linkUrl;
	
	@Column(name = "NUM_", length = 20)
	private String num;
	
	@Column(name = "INTRO_", length = 200)
	private String intro;
	
	@Column(name = "FATHER_", precision = 10, scale=0)
	private Long father;
	
	@Column(name = "GRADE_", precision = 10, scale=0)
	private Long grade;
	
	@Column(name = "STATE_", length = 1)
	private String state;
	
	@Column(name = "HTML_NAME_", length = 200)
	private String htmlName;
	
	@Column(name = "PIC1_", length = 255)
	private String pic1;
	
	@Column(name = "PIC2_", length = 255)
	private String pic2;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getIsValidInNav() {
		return this.isValidInNav;
	}

	public void setIsValidInNav(String isValidInNav) {
		this.isValidInNav = isValidInNav;
	}

	public String getFrontNum() {
		return this.frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Long getFather() {
		return this.father;
	}

	public void setFather(Long father) {
		this.father = father;
	}

	public Long getGrade() {
		return this.grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHtmlName() {
		return this.htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}

	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public Long getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Long orderColumn) {
		this.orderColumn = orderColumn;
	}

	public GjfPageType getTypeColumn() {
		return typeColumn;
	}

	public void setTypeColumn(GjfPageType typeColumn) {
		this.typeColumn = typeColumn;
	}
}