// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:13:41
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersRolesDao.java

package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.entity.UsersRoles;
import cc.messcat.gjfeng.common.bean.Pager;

public interface UsersRolesDao {

	public UsersRoles get(Long long1);

	public void save(UsersRoles usersroles);

	public void update(UsersRoles usersroles);

	public void delete(UsersRoles usersroles);

	public void delete(Long long1);

	public List findAll();

	public Pager getObjectListByClass(int i, int j, Class class1, String s);

	public List getLinksAndAdByClassAndSize(Class class1, Long long1);

	public List findByUsersId(Long long1);

	public List findByRolesId(Long long1);

	public void deleteUsersRoleByUserId(Long userId);
}