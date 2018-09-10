package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PageType entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "gjf_page_type")
public class GjfPageType implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "NAME_", length = 40)
	private String name;
	
	@Column(name = "TEMPLATE_TYPE_", length = 40)
	private String templateType;

	@Column(name = "TEMPLATE_URL_", length = 200)
	private String templateUrl;

	@Column(name = "FEATURES_URL_", length = 200)
	private String featuresUrl;

	@Column(name = "INTRO_", length = 500)
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