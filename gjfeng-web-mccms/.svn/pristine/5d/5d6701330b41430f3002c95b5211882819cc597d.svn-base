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

		<script>
			function back(){
				window.location.href="countInfoAction!diviDataList.action";
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 可提现额列表
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
			<div class="clear"></div>
		</div>		
		<form action="countInfoAction!retrieveMemberByCondition.action" method="post">
			<table class="listTable2" >
				<tr>
					<td>
						名称：<input type="text" placeholder="请输入名称" name="name" id="name" value="${name }"/>&nbsp;&nbsp;
						昵称：<input type="text" placeholder="请输入昵称" name="nickName" id="nickName" value="${nickName }"/>&nbsp;&nbsp;
						<input type="hidden" name="op" value="${op }" />
						<input type="hidden" name="type" value="${type }" />
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>			
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>名称</td>
				<td>昵称</td>
				<td>联系方式</td>
				<td>性别</td>
				<td>可提现额</td>
				<td>类型</td>
				<td>操作</td>		
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.nickName }</td>
					<td>${bean.mobile }</td>
					<td>
						<c:choose>
							<c:when test="${bean.sex eq '0'}">未知</c:when>
							<c:when test="${bean.sex eq '1'}">男</c:when>
							<c:when test="${bean.sex eq '2'}">女</c:when>
						</c:choose>
					</td>
					<td>
						${bean.balanceMoney }
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.type eq '0'}">普通用户</c:when>
							<c:when test="${bean.type eq '1'}">商户</c:when>
						</c:choose>
					</td>
					<td>
						<a href="memberInfoAction!findMoneyHistoryByMemberId.action?id=${bean.id }&token=${bean.token}&tradeType=1">查看明细</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!retrieveMemberByCondition.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="op" value="${op }" />
					<input type="hidden" name="type" value="${type }" />
					<input type="hidden"  name="name" id="name" value="${name }"/>
					<input type="hidden" name="nickName" id="nickName" value="${nickName }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>