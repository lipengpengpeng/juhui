package cc.messcat.front;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public class IndexInfoBean {

	/**
	 *栏目 
	 */
	private EnterpriseColumn enterpriseColumn;
	/**
	 * 子栏目
	 */
	private List enterpriseColumnList;
	/**
	 * 栏目简介
	 */
	private EnterpriseNews enterpriseNews;
	/**
	 * 栏目所有新闻
	 */
	private List enterpriseNewsList;

	/**
	 * 栏目图片新闻
	 */
	private List enterprisePhotoNewsList;
	
	/**
     * 产品信息
     */
    private List mcProductInfoList;

	public IndexInfoBean() {
	}

	public List getEnterpriseNewsList() {
		return enterpriseNewsList;
	}

	public void setEnterpriseNewsList(List enterpriseNewsList) {
		this.enterpriseNewsList = enterpriseNewsList;
	}

	public EnterpriseNews getEnterpriseNews() {
		return enterpriseNews;
	}

	public void setEnterpriseNews(EnterpriseNews enterpriseNews) {
		this.enterpriseNews = enterpriseNews;
	}

	public List getEnterpriseColumnList() {
		return enterpriseColumnList;
	}

	public void setEnterpriseColumn(EnterpriseColumn enterpriseColumn) {
		this.enterpriseColumn = enterpriseColumn;
	}

	public EnterpriseColumn getEnterpriseColumn() {
		return enterpriseColumn;
	}

	public void setEnterpriseColumnList(List enterpriseColumnList) {
		this.enterpriseColumnList = enterpriseColumnList;
	}

	public List getEnterprisePhotoNewsList() {
		return enterprisePhotoNewsList;
	}

	public void setEnterprisePhotoNewsList(List enterprisePhotoNewsList) {
		this.enterprisePhotoNewsList = enterprisePhotoNewsList;
	}

	public List getMcProductInfoList() {
		return mcProductInfoList;
	}

	public void setMcProductInfoList(List mcProductInfoList) {
		this.mcProductInfoList = mcProductInfoList;
	}

}