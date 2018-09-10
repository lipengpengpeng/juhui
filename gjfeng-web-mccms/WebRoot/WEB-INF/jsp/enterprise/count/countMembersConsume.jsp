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
				当前位置: 统计 - 会员消费额度
			</div>
			<div class="ropt">
				<!-- <a href="">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		<form action="countInfoAction!retrieveMemberByCondition.action" method="post">
			<table class="listTable2" >
				<tr>
					<td>
						会员名称：<input type="text" placeholder="请输入会员名称" name="name" id="name" value="${name }"/>&nbsp;&nbsp;
						会员昵称：<input type="text" placeholder="请输入会员昵称" name="nickName" id="nickName" value="${nickName }"/>&nbsp;&nbsp;
						会员类型： <select name="type" id="type">
					             <option value=""  <c:if test="${empty type}">selected="selected"</c:if>>全部</option>
							     <option value="0" <c:if test="${type eq '0' }">selected="selected"</c:if>>普通用户</option>
							     <option value="1" <c:if test="${type eq '1' }">selected="selected"</c:if>>商家</option>
				              </select>&nbsp;&nbsp;
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>会员名称</td>
				<td>会员昵称</td>
				<td>会员性别</td>
				<td>会员类型</td>
				<td>累计消费金额</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.nickName }</td>
					<td>
						<c:choose>
							<c:when test="${bean.sex eq 0}">未知</c:when>
							<c:when test="${bean.sex eq 1}">男</c:when>
							<c:when test="${bean.sex eq 2}">女</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.type eq 0}">普通用户</c:when>
							<c:when test="${bean.type eq 1}">商家</c:when>
						</c:choose>
					</td>
					<td>
						${bean.cumulativeMoney }
					</td>
					<td>
						<a href="countInfoAction!findMemberConsumeHistory.action?id=${bean.id }">查看明细</a>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!retrieveMemberByCondition.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="name" id="name" value="${name }"/>
					<input type="hidden" name="nickName" id="nickName" value="${nickName }"/>
					<select name="type" id="type" style="display:none;">
			             <option value=""  <c:if test="${empty type}">selected="selected"</c:if>>全部</option>
					     <option value="0" <c:if test="${type eq '0' }">selected="selected"</c:if>>普通用户</option>
					     <option value="1" <c:if test="${type eq '1' }">selected="selected"</c:if>>商家</option>
				    </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>

	</body>
</html>