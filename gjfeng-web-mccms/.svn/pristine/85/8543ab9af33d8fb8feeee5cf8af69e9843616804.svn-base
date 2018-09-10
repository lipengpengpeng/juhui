<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
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
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 内容管理 -->当前位置: 内容管理 - 
				<c:if test="${empty column.names}"><!-- 总站 -->总站</c:if>${column.names}</div>
			<div class="clear"></div>
		</div>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>服务类型名称</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${readyServiceColumns}" varStatus="status">
				<tr>
					<td style="text-align:center;">${status.count}</td>
					<td><font style="font-weight:bold;overflow:hidden;">[${bean.enterpriseColumn.names}]</font>${bean.serviceType.name}</td>
					<td style="text-align:center;">
						<a href="server/recommendServiceAction!delServiceColumn.action?id=${bean.id}&colId=${colId}"
								onclick="return doYouWantToDel();"><!-- 删除  -->删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty readyServiceColumns}">
			<s:form action="recommendServiceAction!queryServiceColumn" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="recommendService.serviceType.name" value="${serviceType.name}" />
				<input type="hidden" name="colId" value="${colId}" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
		</c:if>
		
		<div class="rhead" style="margin-top: 50px; margin-bottom: 0;">
			<table  width="100%">
				<tr>
					<td width="20%"><!-- 未关联的产品列表  -->未关联的服务类型列表</td>
					<td width="80%">
						<form action="server/recommendServiceAction!queryServiceColumn.action" id="searchResForm">
							<!-- 按产品名称搜索  -->按服务类型名称搜索 
							<input type="hidden" name="colId" value="<c:if test="${empty colId}">0</c:if><c:if test="${not empty colId}">${colId}</c:if>" />
							<input type="text" name="serviceType.name" value=""/>
							<input type="submit" class="defaultButton" value='搜索'/>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>服务类型名称</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${noServiceColumns}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name}</td>
					<td style="text-align:center;">
						<form action="server/recommendServiceAction!newServiceColumn.action" id="addResForm${status.count}" >
							<input type="hidden" name="recommendService.serviceType.id" value="${bean.id}"/>
							<input type="hidden" name="recommendService.enterpriseColumn.id" value="${colId}"/>
							<input type="hidden" name="colId" value="${colId}"/>
							<input type="submit"  value='添加' />
						</form>
						
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty noServiceColumns}">
			<s:form action="recommendServiceAction!queryServiceColumn"
					method="post" name="pageform2" theme="simple" id="pageform2">
				<!-- 分页 -->
				<%@ include file="../../common/pager2.jsp"%>
				<input type="hidden" name="colId" value="${colId}"/>
			</s:form>
		</c:if>
		
	</body>
</html>