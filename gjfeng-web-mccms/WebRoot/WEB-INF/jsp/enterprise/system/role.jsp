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
		<div class="rpos"><!-- 当前位置: 系统配置 - 角色管理 --><s:text name="enterprise.system.role.current.location" /></div>
		<div class="ropt"><a href="rolesAction!add_page.action"><!-- 新增 --><s:text name="common.word.new" /></a></div>
		<div class="clear"></div>
  	</div>
	
	<table class="listTable3" onmouseover="changeto()"
			onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>
					<!-- 角色名称 --><s:text name="enterprise.system.role.name" />
				</td>
				<td>
					<!-- 授权--><s:text name="enterprise.system.role.grant" />
				</td>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作 --><s:text name="common.word.handle" />
				</td>
			</tr>
         <c:forEach var="rolesList" items="${rolesList}">
        	 <tr>
				<td width="70px">
					${rolesList.name}
				</td>
				<td>
					<c:forEach var="ra" items="${rolesList.rolesAuthoritieses}" varStatus="status">
								${ra.authorities.displayName}&nbsp;&nbsp;						
					</c:forEach>
				</td>
				<td width="40px">
					<c:if test="${rolesList.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
						<c:if test="${rolesList.state == 0}"><font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font></c:if>
				</td>
				<td width="110px">
					<a href="rolesAction!view.action?id=${rolesList.id}">
		            	<!-- 查看  --><s:text name="common.word.view" />
		            </a>&nbsp; &nbsp;
		           <a href="rolesAction!edit.action?id=${rolesList.id}">
		            	<!-- 编辑  --><s:text name="common.word.edit" />
		            </a>&nbsp; &nbsp;
		          <a href="rolesAction!delete.action?id=${rolesList.id}" onclick="return doYouWantToDel();">
		            	<!-- 删除  --><s:text name="common.word.delete" />
		            </a>
				</td>
			</tr>
        </c:forEach>
        </table>
  
		<s:form action="rolesAction" method="get" id="form1" name="form1" theme="simple">
			
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			
		</s:form>
	</body>
</html>

