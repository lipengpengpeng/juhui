/*
 * ProductColumnManagerDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2014-02-13
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.messcat.gjfeng.common.bean.Pager;

public interface ProductColumnManagerDao {

	public void addProductColumn(ProductColumn productColumn);
	
	public void modifyProductColumn(ProductColumn productColumn);
	
	public void removeProductColumn(ProductColumn productColumn);
	
	public void removeProductColumn(Long id);
	
	public ProductColumn retrieveProductColumn(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllProductColumns();
	
	public Pager retrieveProductColumnsPager(int pageSize, int pageNo);
	
	/**
	 *  
	 * @param mcProduct   产品
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品、栏目分页查找对应列表
	 */
	public Pager findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo,Integer order_is);
	
	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @param mcProduct  产品
	 * @return 产品栏目列表
	 * 
	 * 根据栏目、产品分页查找非该栏目下的产品
	 */
	public Pager findProductByNoColumn(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo,String type);
	
	/**
	 *  
	 * @param mcProduct   产品
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品、栏目查找对应列表
	 */
	public List<?> findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn);
	
	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据栏目查找非该栏目下的产品
	 */
	public List<?> findProductByNoColumn(EnterpriseColumn enterpriseColumn);

	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品搜索条件查找产品
	 */
	public Pager findProductByCondition(McProductInfo mcProductInfo, int pageSize, int pageNo,Integer order_is);

	/**
	 * 获取产品总数量
	 * @param selectColumn 
	 * @return
	 */
	public Integer getProductColumnCount(EnterpriseColumn selectColumn);

	
	/**
	 * @param enterpriseColumn  栏目
	 * @param productSearchCondition  搜索条件
	 * @return  
	 * 
	 * 通过产品、栏目和搜索条件查询 （产品栏目） 返回列表
	 */
	//public List<?> findProductColumnBySearchCondition(EnterpriseColumn enterpriseColumn, ProductSearchCondition productSearchCondition);
	
	/**
	 * @param columnId  栏目
	 * @param level  栏目等级
	 * @param pageSize  页面记录数
	 * @param pageNo  页码
	 * @param order_is  排序
	 */
	public Pager findProductByColumnPro(Long columnId, int level, int pageSize, int pageNo,Integer order_is);
	
	/**
	 * 前台根据栏目查询产品列表
	 * @param columnId
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<ProductColumn> findFrontProductColumnByCol(Long columnId ,int size) throws Exception;
	
	/**
	 * 热卖，抢购商品列表
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<ProductColumn> findFrontProductHot(String type,int size) throws Exception;
	
	/**
	 * 搜索商品
	 * @param pageSize
	 * @param pageNo
	 * @param title
	 * @return
	 */
	public Pager findProductByTitle(int pageSize, int pageNo, String title, Integer order_is);
	

}