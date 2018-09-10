/*
 * ProductColumnManagerDaoImpl.java
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

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.messcat.gjfeng.common.bean.Pager;

public class ProductColumnManagerDaoImpl extends BaseManagerDaoImpl implements ProductColumnManagerDao {

	public void addProductColumn(ProductColumn productColumn) {
		this.productColumnDao.delete(productColumn.getMcProduct().getId(), productColumn.getEnterpriseColumn().getId());
		this.productColumnDao.save(productColumn);
	}
	
	public void modifyProductColumn(ProductColumn productColumn) {
		this.productColumnDao.update(productColumn);
	}
	
	public void removeProductColumn(ProductColumn productColumn) {
		this.productColumnDao.delete(productColumn);
	}

	public void removeProductColumn(Long id) {
		this.productColumnDao.delete(id);
	}
	
	public ProductColumn retrieveProductColumn(Long id) {
		return (ProductColumn) this.productColumnDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllProductColumns() {
		return this.productColumnDao.findAll();
	}
	
	public Pager retrieveProductColumnsPager(int pageSize, int pageNo) {
		return this.productColumnDao.getPager(pageSize, pageNo);
	}
	
	public Pager findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo,Integer order_is){
		return this.productColumnDao.findProductByColumnPro(mcProduct, enterpriseColumn, pageSize, pageNo,order_is);
	}
	
	public Pager findProductByNoColumn(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo,String type){
		return this.productColumnDao.findProductByNoColumn(mcProduct, enterpriseColumn, pageSize, pageNo,type);
	}
	
	public List<?> findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn){
		return this.productColumnDao.findProductByColumnPro(mcProduct, enterpriseColumn);
	}
	
	public List<?> findProductByNoColumn(EnterpriseColumn enterpriseColumn){
		return this.productColumnDao.findProductByNoColumn(enterpriseColumn);
	}

	public Pager findProductByCondition(McProductInfo mcProductInfo, int pageSize, int pageNo,Integer order_is) {
		return this.productColumnDao.findProductByCondition(mcProductInfo, pageSize, pageNo,order_is);
	}
	
	/**
	 * 获取产品总数量
	 * @return
	 */
	public Integer getProductColumnCount(EnterpriseColumn selectColumn) {
		return this.productColumnDao.getProductColumnCount(selectColumn);
	}

	public Pager findProductByColumnPro(Long columnId, int level, int pageSize, int pageNo,Integer order_is) {
		return this.productColumnDao.findProductByColumnPro(columnId, level, pageSize, pageNo, order_is);
	}
	
	public List<ProductColumn> findFrontProductColumnByCol(Long columnId ,int size) throws Exception {
		return this.productColumnDao.findFrontProductColumnByCol(columnId, size);
	}
	
	public List<ProductColumn> findFrontProductHot(String type,int size) throws Exception {
		if(type.equals("hot"))
			return this.productColumnDao.findFrontProductHot(size);
		else
			return this.productColumnDao.findFrontProductQbuy(size);
	}
	
	public Pager findProductByTitle(int pageSize, int pageNo, String title,Integer order_is) {
		return this.productColumnDao.findProductByTitle(pageSize, pageNo, title, order_is);
	}
}