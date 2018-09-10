// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:33:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersManagerDaoImpl.java

package cc.messcat.service.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Authorities;
import cc.messcat.entity.Roles;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.entity.Users;
import cc.messcat.entity.UsersRoles;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            UsersManagerDao

public class UsersManagerDaoImpl extends BaseManagerDaoImpl implements UsersManagerDao {

	public UsersManagerDaoImpl() {
	}

	public Users validLogin(String username, String password) {
		return usersDao.get(username, password);
	}

	public Users getUsersById(Long id) {
		return usersDao.get(id);
	}

	public List getUsers(Long id) {
		List urList = usersRolesDao.findByUsersId(id);
		Users users = usersDao.get(id);
		UsersRoles usersRoles;
		for (Iterator iterator = urList.iterator(); iterator.hasNext(); usersRoles.setUsers(users)) {
			usersRoles = (UsersRoles) iterator.next();
			usersRoles.setRoles(rolesDao.get(usersRoles.getRoles().getId()));
		}

		return urList;
	}

	public void addUsers(Users users, List<UsersRoles> usersRolesList) {
		users.setEdittime(new Date());
		usersDao.save(users);
		
		for (UsersRoles usersRoles : usersRolesList) {
			usersRolesDao.save(usersRoles);
		}
	}

	public void deleteUsers(Long id) {
		Users users = usersDao.get(id);
		if(users != null)
			usersDao.delete(users);
	}

	public Pager findUsers(int pageSize, int pageNo, String state, String area) {
		Pager pager = usersDao.getAllUsers(pageSize, pageNo, Users.class, state, area);
		List epPList = pager.getResultList();
		Users users;
		Set kgdoqi;
		for (Iterator iterator = epPList.iterator(); iterator.hasNext(); users.setUsersRoleses(kgdoqi)) {
			users = (Users) iterator.next();
			kgdoqi = new HashSet();
			List u = usersRolesDao.findByUsersId(users.getId());
			UsersRoles temp;
			for (Iterator iterator1 = u.iterator(); iterator1.hasNext(); kgdoqi.add(temp))
				temp = (UsersRoles) iterator1.next();

		}

		return pager;
	}

	public void updateUsers(Users users) {
		users.setEdittime(new Date());
		usersDao.update(users);
	}

	public void updateUsersAndRoles(Users users, List<UsersRoles> usersRolesList) {
		users.setEdittime(new Date());
		usersDao.update(users);
		
		/**
		 * 删除用户的角色，然后进行重新添加
		 */
		usersRolesDao.deleteUsersRoleByUserId(users.getId());
		
		for (UsersRoles usersRoles : usersRolesList) {
			usersRolesDao.save(usersRoles);
		}
	}
	
	public List findUsers() {
		return null;
	}

	public boolean isNameUnique(String name, String orgName) {
		if (!name.equals(orgName))
			return usersDao.isNameUnique(name);
		else
			return true;
	}

	public boolean isOldPasswordUnique(String oldPassword) {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		Users users = usersDao.get(userId);
		oldPassword = oldPassword != null ? oldPassword : "";
		return oldPassword.equals(users.getPassword());
	}

	public Users getUsersByLoginName(String userName) {
		Users users = usersDao.get(userName);
		if (users != null) {
			users.setUsersRoleses(null);
			List usersRoles = usersRolesDao.findByUsersId(users.getId());
			Set q = new HashSet();
			users.setUsersRoleses(q);
			UsersRoles ur;
			for (Iterator iterator = usersRoles.iterator(); iterator.hasNext(); users.getUsersRoleses().add(ur)) {
				ur = (UsersRoles) iterator.next();
				Roles roles = ur.getRoles();
				List rolesAuthorities = rolesAuthoritiesDao.findByRolesId(roles.getId());
				RolesAuthorities ra;
				Authorities authorities;
				for (Iterator iterator1 = rolesAuthorities.iterator(); iterator1.hasNext(); ra.setAuthorities(authorities)) {
					ra = (RolesAuthorities) iterator1.next();
					authorities = authoritiesDao.get(ra.getAuthorities().getId());
				}

				ur.setRoles(roles);
			}

		}
		return users;
	}

	public Users getUserByArea(String area) {
		return usersDao.getUserByUnit(area);
	}

	/** 
	 * 如果管理未完善信息，返回false，否则返回true
	 */
	public boolean isFullInfo() {

		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		Users users = usersDao.get(userId);
		boolean result = true;

		if ("".equals(users.getAddress()) || users.getAddress() == null)
			result = false;

		if ("".equals(users.getWorkphone()) || users.getWorkphone() == null)
			result = false;

		if ("".equals(users.getFax()) || users.getFax() == null)
			result = false;

		if ("".equals(users.getEmail()) || users.getEmail() == null)
			result = false;

		if ("".equals(users.getMobile()) || users.getMobile() == null)
			result = false;

		return result;
	}

	/**
	 * 获取用户列表除了自己以外
	 * @param id
	 * @return
	 */
	public List<Users> getUsersListExceptSelf(Long id) {
		List<Users> usersList = usersDao.findAll();
		Users users = usersDao.get(id); //删除自己
		Users users2 = usersDao.get(1L);//删除超级管理员
		usersList.remove(users);
		usersList.remove(users2);

		return usersList;
	}

}