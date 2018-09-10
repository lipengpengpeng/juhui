<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
		   		<!-- 当前位置: 用户管理 --><s:text name="enterprise.system.user.current.location" /> - 
				<!-- 查看用户信息  --><s:text name="enterprise.system.user.current.location.view" />
		   	</div>
		   	<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
           	<div class="clear"></div>
  		</div>
	  <table align="center" class="listTable3" width="90%">
			<tr>
				<td class="pn-flabel" width="100px"><!-- 用户名 --><s:text name="enterprise.system.user.name" /></td><td>${tusers.loginName}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 真实名字 --><s:text name="enterprise.system.user.real.name" /></td><td>${tusers.name}</td>
			</tr>
			<!--<tr>
				<td class="pn-flabel">性别</td><td>
				<c:if test="${tusers.sex==1}">男</c:if>
				<c:if test="${tusers.sex==0}">女</c:if>
				<c:if test="${tusers.sex!=0 and tusers.sex!=1}">未知</c:if>
				</td>
			</tr>-->
			<tr>
				<td class="pn-flabel"><!-- 地区 --><s:text name="enterprise.system.user.area" /></td><td>${tusers.area}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 区/县 --><s:text name="enterprise.system.user.region" /></td>
				<td>
					${tusers.county}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 地址 --><s:text name="enterprise.system.user.address" /></td><td>${tusers.address}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 工作单位 --><s:text name="enterprise.system.user.work.company" /></td><td>${tusers.workunit}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 联系电话 --><s:text name="enterprise.system.user.phone" /></td><td>${tusers.workphone}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 传真号码 --><s:text name="enterprise.system.user.fax" /></td><td>${tusers.fax}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 联系E-mail --><s:text name="enterprise.system.user.email" /></td><td>${tusers.email}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 移动电话 --><s:text name="enterprise.system.user.mobile" /></td><td>${tusers.mobile}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 编辑时间 --><s:text name="common.word.update.time" /></td>
				<td>
					<fmt:formatDate value="${tusers.edittime}" 
					pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 角色 --><s:text name="enterprise.system.user.role.list" /></td>
				<td>
					<c:forEach var="usersRolesList" items="${usersRolesList}">
						${usersRolesList.roles.name}&nbsp;&nbsp;
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 操作 --><s:text name="common.word.handle" /></td>
				<td>&nbsp;&nbsp;<a href="${ctx}/usersAction!editInfo.action?id=${tusers.id}"><font color="red"><!-- 编辑  --><s:text name="common.word.edit" /></font></a></td>

			</tr>
	 	</table>
	</body>
</html>

