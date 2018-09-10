<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>
		
		
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理 - 查看下级
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>微信号</td>
				<td>名称</td>
				<td>手机号码</td>
				<td>昵称</td>
				<td>性别</td>
				<td>余额</td>
				<td>用户注册时间</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.unionId}</td>
					<td>${bean.name}</td>
					<td>${bean.mobile}</td>
					<td>${bean.nickName}</td>
					<c:if test="${bean.sex==1}">
					     <td>男</td>
					</c:if>
					<c:if test="${bean.sex==0}">
					     <td>未知</td>
					</c:if>
					<c:if test="${bean.sex==2}">
					     <td>女</td>
					</c:if>
					<td>${bean.balanceMoney}</td>
					<td>
						<fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td><a href="${ctx}/subsystem/memberInfoAction!findLowerMembers.action?id=${bean.id}">查看下级</a></td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findLowerMembers.action"
				namespace="/subsystem"	 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" name="id" value="${id }" readonly="readonly"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>