// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:11:47
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AuthoritiesDao.java

package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.entity.Authorities;
import cc.messcat.gjfeng.common.bean.Pager;

public interface AuthoritiesDao {

	public Authorities get(Long long1);

	public void save(Authorities authorities);

	public void update(Authorities authorities);

	public void delete(Authorities authorities);

	public void delete(Long long1);

	public List findAll();

	public boolean isNameUnique(String s);

	public boolean isDisplayNameUnique(String s);

	public List findAuthoritiesByTypeId(Long long1);

	public Pager getObjectListByClass(int i, int j, Class class1, String s);

	public List getInfoByClassAndSize(String s, Long long1, Long long2, String s1, String s2, String s3, Long long3,
		String s4);

	public Authorities getByName(String name);
	
	//根据权限名与权限类型查找权限
	public Authorities getByNameAndType(String name, String type);
	
	//根据权限类型查询
	public List<Authorities> findAuthoritiesByType(String type);
	
	//根据权限类型查询除某个ID外的权限列表
	public List<Authorities> findAuthoritiesByTypeAndId(String type, Long id);

	//根据显示名称查询
	public Authorities getAuthByDisplayName(String displayName);

}