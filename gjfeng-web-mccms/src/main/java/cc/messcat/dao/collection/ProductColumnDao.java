/*
 * ProductColumnDao.java
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
package cc.messcat.dao.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.messcat.gjfeng.common.bean.Pager;

public interface ProductColumnDao {

	public void save(ProductColumn productColumn);
	
	public void update(ProductColumn productColumn);
	
	public void delete(ProductColumn productColumn);
	
	public void delete(Long id);
	
	public int delete(Long productId,Long columnId);
	
	public ProductColumn get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
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
	 * 根据栏目分页查询产品列表
	 * @param columnId
	 * @param level
	 * @param pageSize
	 * @param pageNo
	 * @param order_is
	 * @return
	 */
	public Pager findProductByColumnPro(Long columnId, int level, int pageSize, int pageNo,Integer order_is);
	
	/**
	 * 前台根据栏目查询产品列表
	 * @param columnId
	 * @param size
	 * @return
	 */
	public List<ProductColumn> findFrontProductColumnByCol(Long columnId ,int size) throws Exception;
	
	/**
	 * 热卖商品
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<ProductColumn> findFrontProductHot(int size) throws Exception;
	
	/**
	 * 抢购商品列表
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<ProductColumn> findFrontProductQbuy(int size) throws Exception;
	
	/**
	 * 搜索商品
	 * @param pageSize
	 * @param pageNo
	 * @param title
	 * @return
	 */
	public Pager findProductByTitle(int pageSize, int pageNo, String title,Integer order_is);
	
}