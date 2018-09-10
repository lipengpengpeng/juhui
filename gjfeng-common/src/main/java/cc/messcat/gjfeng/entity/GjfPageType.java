package cc.messcat.gjfeng.entity;

/**
 * PageType entity. @author MyEclipse Persistence Tools
 */

public class GjfPageType implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String templateType;

	private String templateUrl;

	private String featuresUrl;

	private String intro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public String getFeaturesUrl() {
		return featuresUrl;
	}
	public void setFeaturesUrl(String featuresUrl) {
		this.featuresUrl = featuresUrl;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	

}