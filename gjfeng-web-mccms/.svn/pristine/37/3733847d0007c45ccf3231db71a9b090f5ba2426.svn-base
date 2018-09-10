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
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 当前池支出
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
			<div class="clear"></div>
		</div>	
		
		<%-- <form action="countInfoAction!retrieveMemberByCondition.action" method="post">
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
		</form>		 --%>
						
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>金额</td>
				<td>日期</td>
				<td>操作</td>			
			</tr>
			
			<c:forEach var="bean" items="${pager.resultList}"
					varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${bean.expend}</td>
						<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
						<td>
							<c:choose>
								<c:when test="${type eq '5' || type eq '6' || type eq '7'}">
									<a href="countInfoAction!findTradeDiviByAgent.action?type=${type }&addTime=<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/>">查看明细</a>
								</c:when>
								<c:otherwise>
									<a href="countInfoAction!findPoolInDetail.action?type=${type }&addTime=<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/>">查看明细</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!findPoolOut.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="op" value="${op }" />
					<input type="hidden" name="type" value="${type }" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>