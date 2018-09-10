<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>

		<div class="rhead">
		   <div class="rpos"><!-- 当前位置: 系统配置 - 用户管理 --><s:text name="enterprise.system.user.current.location" /></div>
		   <div class="ropt"><a href="usersAction!add_page.action"><!-- 新增 --><s:text name="common.word.new" /></a></div>
		   <div class="clear"></div>
  		</div>

		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
		<tr id="nc" class="headbg">
				<td>
					<!-- 用户名 --><s:text name="enterprise.system.user.name" />
				</td>
				<td>
					<!-- 角色 --><s:text name="enterprise.system.user.role.list" />
				</td>
				<td>
					<!-- 真实名字 --><s:text name="enterprise.system.user.real.name" />
				</td>
				<td>
					<!-- 工作单位 --><s:text name="enterprise.system.user.work.company" />
				</td>
				<td>
					<!-- 编辑时间 --><s:text name="common.word.update.time" />
				</td>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作 --><s:text name="common.word.handle" />
				</td>
			</tr>

			<c:forEach var="tusers" items="${usersList}">
				<tr>
					<td>
						
						${tusers.loginName}
					</td>
					<td>
						<c:forEach var="ro" items="${tusers.usersRoleses}">
							${ro.roles.name}&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</td>
					<td>
						${tusers.name}
					</td>
					<td>
						<!--  	<c:if test="${users.sex==1}">男</c:if>
						<c:if test="${users.sex==0}">女</c:if>
						<c:if test="${users.sex!=0 and users.sex!=1}">未知</c:if>-->
						${tusers.workunit}
					</td>
					<td>
						<fmt:formatDate value="${tusers.edittime}" pattern="yyyy-MM-dd" />
					</td>
					<td>
						<c:if test="${tusers.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
						<c:if test="${tusers.state == 0}">
							<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
						</c:if>
					</td>
					<td>
						<a href="usersAction!view.action?id=${tusers.id}"><!-- 查看  --><s:text name="common.word.view" /></a>&nbsp;
						&nbsp;
						<a href="usersAction!edit.action?id=${tusers.id}"><!-- 编辑  --><s:text name="common.word.edit" /></a>&nbsp;
						&nbsp;
						<a href="updatePasswordAction!resetview.action?id=${tusers.id}"><!-- 重置密码  --><s:text name="enterprise.system.user.reset.password" /></a>&nbsp;
						&nbsp;	
									
						    <a href="usersAction!delete.action?id=${tusers.id}" onclick="return doYouWantToDel();">
		            	<!-- 删除  --><s:text name="common.word.delete" />
		            </a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<s:form action="usersAction" method="get" id="form1" name="form1"
				theme="simple">
				
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			
		</s:form>
	</body>
</html>

