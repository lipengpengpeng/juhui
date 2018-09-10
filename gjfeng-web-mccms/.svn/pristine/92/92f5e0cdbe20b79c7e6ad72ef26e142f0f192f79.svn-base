<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
				当前位置: 会员下级消费
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
			<div class="clear"></div>
		</div>
		
		<form action="countInfoAction!findMemberLowLevelConsume.action" method="post">
			<input type="hidden" name="id" value="${id }"/>
			<table class="listTable2" >
				<tr>
					<td>
						 开始时间：<input type="text" readonly="readonly" value="${startTime }" name="startTime" id="startTime" onclick="WdatePicker()" style="width: 75px;"/>
						 -
					  	 结束时间：<input type="text" readonly="readonly" value="${endTime }" name="endTime" id="endTime" onclick="WdatePicker()" style="width: 75px;"/>
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
				<td>会员类型</td>
				<td>累计消费金额</td>
			</tr>

			<c:forEach var="bean" items="${memberInfos}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name}</td>
					<td>${bean.nickName}</td>
					<td>
						<c:choose>
							<c:when test="${bean.type eq 0}">普通用户</c:when>
							<c:when test="${bean.type eq 1}">商家</c:when>
						</c:choose>
					</td>
					<td>
						${bean.cumulativeMoney }
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty memberInfos}">
			<s:form action="countInfoAction!findMemberLowLevelConsume.action"
				namespace="/subsystem"	 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" name="id" id="id" value="${id }" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>