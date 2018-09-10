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
				<!-- 当前位置: 系统配置 - 角色管理 --><s:text name="enterprise.system.role.current.location" /> - 
				<!-- 查看角色信息 --><s:text name="enterprise.system.role.current.location.view" />
			</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
			<div class="clear"></div>
	  	</div>
	  
	  <table align="center" class="listTable3" width="90%">
			<tr>
				<td><!-- 角色名称 --><s:text name="enterprise.system.role.name" /></td><td>${roles.name}</td>
			</tr>
			<tr>
				<td><!-- 授权--><s:text name="enterprise.system.role.grant" /></td>
				<td>
					<c:forEach var="ra" items="${roles.rolesAuthoritieses}"  varStatus="status">
								<c:if test="${status.count==10}"><br/></c:if>
								${ra.authorities.displayName}&nbsp;&nbsp;						
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
						<c:if test="${roles.state == 1}">
							<!-- 已启用 --><s:text name="common.word.has.opened" />
						</c:if>
						<c:if test="${roles.state == 0}">
							<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
						</c:if>
				</td>
			</tr>
	 	</table>
	</body>
</html>

