// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:16:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EIndexNews.java

package cc.messcat.front;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cc.messcat.entity.EnterpriseNews;

public class EIndexNews implements Serializable {

	private static final long serialVersionUID = -4938970307761275665L;

	/**
	 * 主导航信息
	 */
	private List ecolumn;

	/**
	 *通知公告信息 
	 */
	private List rcolumn;
	private List lcolumn;
	private List indexAllColumnList;
	private List subEpColumn;

	private List enterpriseLinks;
	private List enterpriseAd;
	private List pointNews;
	private List photoNews;
	private List newsList;
	private List tuijianNews;
	private List hotNews;

	private EnterpriseNews news;
	private EnterpriseNews newsone;
	private EnterpriseNews newstwo;
	private EnterpriseNews newsthree;

	public EIndexNews() {
		ecolumn = new ArrayList();
		rcolumn = new ArrayList();
		lcolumn = new ArrayList();
		indexAllColumnList = new ArrayList();
	}

	public EnterpriseNews getNewsone() {
		return newsone;
	}

	public void setNewsone(EnterpriseNews newsone) {
		this.newsone = newsone;
	}

	public EnterpriseNews getNewstwo() {
		return newstwo;
	}

	public void setNewstwo(EnterpriseNews newstwo) {
		this.newstwo = newstwo;
	}

	public EnterpriseNews getNewsthree() {
		return newsthree;
	}

	public void setNewsthree(EnterpriseNews newsthree) {
		this.newsthree = newsthree;
	}

	public EnterpriseNews getNews() {
		return news;
	}

	public void setNews(EnterpriseNews news) {
		this.news = news;
	}

	public List getHotNews() {
		return hotNews;
	}

	public void setHotNews(List hotNews) {
		this.hotNews = hotNews;
	}

	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
	}

	public List getPointNews() {
		return pointNews;
	}

	public void setPointNews(List pointNews) {
		this.pointNews = pointNews;
	}

	public List getSubEpColumn() {
		return subEpColumn;
	}

	public void setSubEpColumn(List subEpColumn) {
		this.subEpColumn = subEpColumn;
	}

	public List getEnterpriseLinks() {
		return enterpriseLinks;
	}

	public void setEnterpriseLinks(List enterpriseLinks) {
		this.enterpriseLinks = enterpriseLinks;
	}

	public List getEnterpriseAd() {
		return enterpriseAd;
	}

	public void setEnterpriseAd(List enterpriseAd) {
		this.enterpriseAd = enterpriseAd;
	}

	public List getEcolumn() {
		return ecolumn;
	}

	public void setEcolumn(List ecolumn) {
		this.ecolumn = ecolumn;
	}

	public List getPhotoNews() {
		return photoNews;
	}

	public void setPhotoNews(List photoNews) {
		this.photoNews = photoNews;
	}

	public List getTuijianNews() {
		return tuijianNews;
	}

	public void setTuijianNews(List tuijianNews) {
		this.tuijianNews = tuijianNews;
	}

	public List getIndexAllColumnList() {
		return indexAllColumnList;
	}

	public void setIndexAllColumnList(List indexAllColumnList) {
		this.indexAllColumnList = indexAllColumnList;
	}

	public List getRcolumn() {
		return rcolumn;
	}

	public void setRcolumn(List rcolumn) {
		this.rcolumn = rcolumn;
	}

	public List getLcolumn() {
		return lcolumn;
	}

	public void setLcolumn(List lcolumn) {
		this.lcolumn = lcolumn;
	}

}