// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:30:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersManagerDao.java

package cc.messcat.service.system;

import java.util.List;

import cc.messcat.entity.Users;
import cc.messcat.entity.UsersRoles;
import cc.messcat.gjfeng.common.bean.Pager;

public interface UsersManagerDao {

	public Users getUsersByLoginName(String s);

	public Users validLogin(String s, String s1);

	public List getUsers(Long long1);

	public Users getUsersById(Long long1);

	public Pager findUsers(int pageSize, int pageNo, String state, String area);

	public void addUsers(Users tusers, List<UsersRoles> usersRolesList);

	public void updateUsers(Users users);

	public void deleteUsers(Long long1);

	//    public List findUsersList(int pageSize, int pageNo, String state, String area);

	public boolean isNameUnique(String s, String s1);

	public boolean isOldPasswordUnique(String s);

	public Users getUserByArea(String area);

	public boolean isFullInfo();

	public List<Users> getUsersListExceptSelf(Long id);

	//更新用户信息以及其所属的角色
	public void updateUsersAndRoles(Users users, List<UsersRoles> usersRolesList);

}