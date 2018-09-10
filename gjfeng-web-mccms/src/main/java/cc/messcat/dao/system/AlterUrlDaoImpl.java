/*
 * AlterUrlDaoImpl.java
 * 
 * Create by Andy Lin
 * 
 * Create time 2013-08-20
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.front.AlterUrlBean;


public class AlterUrlDaoImpl extends BaseDaoImpl implements AlterUrlDao  {

	//根据查询条件查找记录数
	public Long getCountByCondition(AlterUrlBean condition) {
		
		StringBuilder hql = new StringBuilder();
		//根据查询不同的表构造不同的查询语句
		if(condition.getTableName().equals("enterprise_column")){
			hql.append("select count(linkUrl) from EnterpriseColumn where linkUrl like ");
		}else if(condition.getTableName().equals("enterprise_news")){
			hql.append("select count(contents) from EnterpriseNews where contents like ");
		}else{
			return 0L;
		}
		
		hql.append("'%").append(condition.getOldUrl()).append("%'");
		List<?> result = getHibernateTemplate().find(hql.toString());
		Long count;
		if(result != null && result.size() > 0 && result.get(0) != null){
			count = (Long)result.get(0);
		}else{
			count = 0L;
		}
		return count;
	}

	//更新数据库中的URL记录
	public Long updateUrl(AlterUrlBean alterUrlBean) {
		
		StringBuilder sql = new StringBuilder();
		//根据查询不同的表构造不同的语句
		if(alterUrlBean.getTableName().equals("enterprise_column")){
			sql.append("UPDATE gjf_enterprise_column SET LINK_URL=REPLACE(LINK_URL ,'");
		}else if(alterUrlBean.getTableName().equals("enterprise_news")){
			sql.append("UPDATE gjf_enterprise_news SET CONTENTS=REPLACE(CONTENTS ,'");
		}else{
			return 0L;
		}
		sql.append(alterUrlBean.getOldUrl()).append("','");
		sql.append(alterUrlBean.getNewUrl()).append("');");
		
		SessionFactory factory = this.getSessionFactory();
		Session session = factory.openSession();
		int tempNum = session.createSQLQuery(sql.toString()).executeUpdate();
		
		return new Long((long)tempNum);
	}
	
}