package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_access_token")
public class GjfAccessToken implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236829327354806511L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "APPID_", length = 100)
	private String appId;
	
	@Column(name = "APPSECRET_", length = 200)
	private String appsecret;
	
	
	@Column(name = "EXPIRATION_TIME_")
    private Date expirationTime;
	
	@Column(name = "TYPE_", length = 1)
	private String type;
	
	@Column(name = "TOKEN_", length = 600)
	private String token;
	
	@Column(name = "OPENID_", length = 500)
	private String openId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
