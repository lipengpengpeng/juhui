package cc.messcat.gjfeng.common.proprietary.bean;

public class Classify {

	private Long id;
	
	private String name;
	
	private String mobile_name;
	
	private String parent_id;
	
	private String parent_id_path;
	
	private String level;

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

	public String getMobile_name() {
		return mobile_name;
	}

	public void setMobile_name(String mobile_name) {
		this.mobile_name = mobile_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_id_path() {
		return parent_id_path;
	}

	public void setParent_id_path(String parent_id_path) {
		this.parent_id_path = parent_id_path;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
