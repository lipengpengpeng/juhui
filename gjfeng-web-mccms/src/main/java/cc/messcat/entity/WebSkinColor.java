// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:03:17
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WebSite.java

package cc.messcat.entity;

import java.io.Serializable;

public class WebSkinColor implements Serializable {

	private static final long serialVersionUID = -5097605749610655673L;
	private Long id;
	private String names;
	private String filename;
	private String content;
	private String state;
	private Long webSkinId;
	private String isDefaultId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getWebSkinId() {
		return webSkinId;
	}

	public void setWebSkinId(Long webSkinId) {
		this.webSkinId = webSkinId;
	}

	public String getIsDefaultId() {
		return isDefaultId;
	}

	public void setIsDefaultId(String isDefaultId) {
		this.isDefaultId = isDefaultId;
	}

}