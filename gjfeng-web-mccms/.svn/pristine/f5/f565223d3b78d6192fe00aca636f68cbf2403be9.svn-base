// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:52:02
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: UpdatePasswordAction.java

package cc.messcat.web.system;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

import cc.messcat.bases.BaseAction;
import cc.messcat.entity.Users;

public class UpdatePasswordAction extends BaseAction {

	private static final long serialVersionUID = -9099427351820632055L;
	private String oldpassword;
	private String newpassword;
	private String repassword;
	private String message;
	private Long id;

	public UpdatePasswordAction() {
	}

	public String execute() throws Exception {
		return "success";
	}

	public String resetview() throws Exception {
		/*
		 * 如果修改密码的时候，为修改自己的密码，那么跳转到输入旧密码页面
		 */
		Long userID = (Long) ServletActionContext.getContext().getSession().get("userID");
		if (id.equals(userID))
			return "success";

		return "reset_view";
	}

	public String update() throws Exception {
		if (newpassword.equals(repassword)) {
			Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
			Users users = usersManagerDao.getUsersById(userId);
			if (users == null) {
				setMessage("\u5BC6\u7801\u4FEE\u6539\u5931\u8D25!");
				return "failure";
			} else {
				users.setPassword((new Md5PasswordEncoder()).encodePassword(newpassword, users.getLoginName()));
				usersManagerDao.updateUsers(users);
				setMessage("\u5BC6\u7801\u4FEE\u6539\u6210\u529F!");
				return "view_info";
			}
		} else {
			setMessage("\u8BF7\u8F93\u5165\u76F8\u540C\u7684\u5BC6\u7801,\u518D\u7EE7\u7EED\u4FEE\u6539");
			return "failure";
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		if (newpassword.equals(repassword)) {
			// Long userId = (Long)
			// ServletActionContext.getContext().getSession().get("userID");
			Users users = usersManagerDao.getUsersById(id);
			if (users == null) {
				setMessage("\u5BC6\u7801\u4FEE\u6539\u5931\u8D25!");
				return "failure";
			} else {
				users.setPassword((new Md5PasswordEncoder()).encodePassword(newpassword, users.getLoginName()));
				usersManagerDao.updateUsers(users);
				setMessage("\u5BC6\u7801\u4FEE\u6539\u6210\u529F!");
				return "user_list";
			}
		} else {
			setMessage("\u8BF7\u8F93\u5165\u76F8\u540C\u7684\u5BC6\u7801,\u518D\u7EE7\u7EED\u4FEE\u6539");
			return "failure";
		}
	}

	public String checkPassword() throws Exception {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		Users users = usersManagerDao.getUsersById(userId);
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldpassword = request.getParameter("oldpassword");
		oldpassword = URLDecoder.decode(oldpassword, "UTF-8");
		oldpassword = (new Md5PasswordEncoder()).encodePassword(oldpassword, users.getLoginName());
		if (usersManagerDao.isOldPasswordUnique(oldpassword))
			return renderText("true");
		else
			return renderText("false");
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}