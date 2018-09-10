package cc.modules.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import cc.messcat.entity.Roles;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.entity.Users;
import cc.messcat.entity.UsersRoles;
import cc.messcat.service.system.UsersManagerDao;

public class UserDetailServiceImpl implements UserDetailsService {
	private String roleType;

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public UserDetailServiceImpl() {
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		User userdetail = null;

		boolean accountFlag = true;

		boolean credentialsFlag = true;

		boolean lockFlag = true;

		boolean enabled = false;
		Users users = usersManagerDao.getUsersByLoginName(userName);
		if (users == null) {
			accountFlag = false;
			throw new UsernameNotFoundException((new StringBuffer(userName).append("不存在").toString()));
		}
		List authsList = new ArrayList();
		for (Iterator iterator = users.getUsersRoleses().iterator(); iterator.hasNext();) {
			UsersRoles usersRoles = (UsersRoles) iterator.next();
			Roles roles = usersRoles.getRoles();
			RolesAuthorities rolesAuthorities;
			for (Iterator iterator1 = roles.getRolesAuthoritieses().iterator(); iterator1.hasNext(); authsList
				.add(new GrantedAuthorityImpl(rolesAuthorities.getAuthorities().getName())))
				rolesAuthorities = (RolesAuthorities) iterator1.next();
		}
		if ("1".equals(users.getState()))
			enabled = true;
		else
			enabled = false;

		userdetail = new User(users.getLoginName(), users.getPassword(), enabled, accountFlag, credentialsFlag, lockFlag,
			(GrantedAuthority[]) authsList.toArray(new GrantedAuthority[authsList.size()]));

		return userdetail;
	}

	public UsersManagerDao getUsersManagerDao() {
		return usersManagerDao;
	}

	public void setUsersManagerDao(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	private UsersManagerDao usersManagerDao;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}