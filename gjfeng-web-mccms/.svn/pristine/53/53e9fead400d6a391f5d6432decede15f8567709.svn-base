<%--

	gjfBankInfo.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				当前位置: 模块 - 子模块
			</div>
			<div class="ropt">
				<a href="appUpgradeInfoAction!goNewInfoPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>版本号</td>
				<td>更新链接</td>
				<td>添加时间</td>
				<td>描述</td>
				<td>类型</td>
				
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${resultVo.result}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.version}</td>
					<td>${bean.jumpUrl}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${bean.addTime}"/></td>
					<td>${bean.describe}</td>
					<c:if test="${bean.type==0}">
					   <td>安卓</td>
					</c:if>
					<c:if test="${bean.type==1}">
					   <td>ios</td>
					</c:if>
					
					<td>							
						<a href="appUpgradeInfoAction!deleteAppUpGredeInfo.action?appUpGradeInfo.id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>	
	</body>
</html>