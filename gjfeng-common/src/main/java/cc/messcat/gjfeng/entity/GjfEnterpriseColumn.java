package cc.messcat.gjfeng.entity;

/**
 * GjfEnterpriseColumn entity. @author MyEclipse Persistence Tools
 */
public class GjfEnterpriseColumn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String names;
	
	private String shortName;
	
	private String isValidInNav;
	
	private String frontNum;
	
	private Long orderColumn;
	
	private GjfPageType typeColumn;
	
	private String linkUrl;
	
	private String num;
	
	private String intro;
	
	private Long father;
	
	private Long grade;
	
	private String state;
	
	private String htmlName;
	
	private String pic1;
	
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