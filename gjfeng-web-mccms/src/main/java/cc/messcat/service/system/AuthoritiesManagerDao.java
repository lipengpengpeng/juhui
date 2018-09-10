// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:28:57
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AuthoritiesManagerDao.java

package cc.messcat.service.system;

import java.util.List;

import cc.messcat.entity.Authorities;
import cc.messcat.gjfeng.common.bean.Pager;

public interface AuthoritiesManagerDao {

	public Authorities getAuthorities(Long long1);

	public Pager findAuthorities(int i, int j, String s);

	public void addAuthorities(Authorities authorities);

	public void updateAuthorities(Authorities authorities);

	public void deleteAuthorities(Long long1);

	public List findAuthorities();

	public List findEpAuthorities();

	public boolean isNameUnique(String s, String s1);

	public boolean isDisplayNameUnique(String s, String s1);

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