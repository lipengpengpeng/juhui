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

/**
 * GjfEnterpriseNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_enterprise_news")
public class GjfEnterpriseNews implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ENTERPRISE_COLUMN_")
	private GjfEnterpriseColumn enterpriseColumn;
	
	@Column(name = "TITLE_", length = 20)
	private String title;
	
	@Column(name = "PRI_KEY_", length = 500)
	private String priKey;
	
	@Column(name = "SHORT_META_", length = 500)
	private String shortMeta;
	
	@Column(name = "AUTOR_", length = 20)
	private String autor;
	
	@Column(name = "SOURCE_", length = 50)
	private String source;
	
	@Column(name = "CONTENTS_")
	private String contents;
	
	@Column(name = "CONTENTS1_")
	private String contents1;
	
	@Column(name = "CONTENTS2_")
	private String contents2;
	
	@Column(name = "CONTENTS3_")
	private String contents3;
	
	@Column(name = "CONTENTS4_")
	private String contents4;
	
	@Column(name = "CONTENTS5_")
	private String contents5;
	
	@Column(name = "CONTENTS6_")
	private String contents6;
	
	@Column(name = "CONTENTS7_")
	private String contents7;
	
	@Column(name = "CONTENTS8_")
	private String contents8;
	
	@Column(name = "CONTENTS9_")
	private String contents9;
	
	@Column(name = "CLICK_TIMES_", length = 50)
	private Long clickTimes;
	
	@Column(name = "INIT_TIME_")
	private Date initTime;
	
	@Column(name = "END_TIME_")
	private Date endTime;
	
	@Column(name = "EDIT_TIME_")
	private Date editTime;
	
	@ManyToOne
	@JoinColumn(name="USERS_")
	private GjfUsersInfo users;
	
	@Column(name = "IS_TOP_", length = 1)
	private String isTop;
	
	@Column(name = "IS_COMMEND_", length = 1)
	private String isCommend;
	
	@Column(name = "IS_PRIM_PHOTO_", length = 1)
	private String isPrimPhoto;
	
	@Column(name = "IS_INDEX_PHOTO_", length = 1)
	private String isIndexPhoto;
	
	@Column(name = "IS_ONLY_CONTENT_", length = 10)
	private Long isOnlyContent;
	
	@Column(name = "OTHER_URL_", length = 100)
	private String otherUrl;
	
	@Column(name = "B_OR_S_", length = 1)
	private String BOrS;
	
	@Column(name = "PHOTO_", length = 50)
	private String photo;
	
	@Column(name = "STATE_", length = 1)
	private String state;
	
	@Column(name = "HTML_NAME_", length = 200)
	private String htmlName;
	
	@Column(name = "IS_SHOWED_ON_INDEX_", length = 1)
	private String isShowedOnIndex;
	
	@Column(name = "FILE_INFO_")
	private String fileInfo;
	
	@Column(name = "FILE_SIZE_")
	private Long fileSize;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriKey() {
		return this.priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}

	public String getShortMeta() {
		return this.shortMeta;
	}

	public void setShortMeta(String shortMeta) {
		this.shortMeta = shortMeta;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContents1() {
		return this.contents1;
	}

	public void setContents1(String contents1) {
		this.contents1 = contents1;
	}

	public String getContents2() {
		return this.contents2;
	}

	public void setContents2(String contents2) {
		this.contents2 = contents2;
	}

	public String getContents3() {
		return this.contents3;
	}

	public void setContents3(String contents3) {
		this.contents3 = contents3;
	}

	public String getContents4() {
		return this.contents4;
	}

	public void setContents4(String contents4) {
		this.contents4 = contents4;
	}

	public String getContents5() {
		return this.contents5;
	}

	public void setContents5(String contents5) {
		this.contents5 = contents5;
	}

	public String getContents6() {
		return this.contents6;
	}

	public void setContents6(String contents6) {
		this.contents6 = contents6;
	}

	public String getContents7() {
		return this.contents7;
	}

	public void setContents7(String contents7) {
		this.contents7 = contents7;
	}

	public String getContents8() {
		return this.contents8;
	}

	public void setContents8(String contents8) {
		this.contents8 = contents8;
	}

	public String getContents9() {
		return this.contents9;
	}

	public void setContents9(String contents9) {
		this.contents9 = contents9;
	}

	public Long getClickTimes() {
		return this.clickTimes;
	}

	public void setClickTimes(Long clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Date getInitTime() {
		return this.initTime;
	}

	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public GjfUsersInfo getUsers() {
		return this.users;
	}

	public void setUsers(GjfUsersInfo users) {
		this.users = users;
	}

	public String getIsTop() {
		return this.isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsCommend() {
		return this.isCommend;
	}

	public void setIsCommend(String isCommend) {
		this.isCommend = isCommend;
	}

	public String getIsPrimPhoto() {
		return this.isPrimPhoto;
	}

	public void setIsPrimPhoto(String isPrimPhoto) {
		this.isPrimPhoto = isPrimPhoto;
	}

	public String getIsIndexPhoto() {
		return this.isIndexPhoto;
	}

	public void setIsIndexPhoto(String isIndexPhoto) {
		this.isIndexPhoto = isIndexPhoto;
	}

	public Long getIsOnlyContent() {
		return this.isOnlyContent;
	}

	public void setIsOnlyContent(Long isOnlyContent) {
		this.isOnlyContent = isOnlyContent;
	}

	public String getOtherUrl() {
		return this.otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}

	public String getBOrS() {
		return this.BOrS;
	}

	public void setBOrS(String BOrS) {
		this.BOrS = BOrS;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getIsShowedOnIndex() {
		return this.isShowedOnIndex;
	}

	public void setIsShowedOnIndex(String isShowedOnIndex) {
		this.isShowedOnIndex = isShowedOnIndex;
	}

	public String getFileInfo() {
		return this.fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public GjfEnterpriseColumn getEnterpriseColumn() {
		return enterpriseColumn;
	}

	public void setEnterpriseColumn(GjfEnterpriseColumn enterpriseColumn) {
		this.enterpriseColumn = enterpriseColumn;
	}

}