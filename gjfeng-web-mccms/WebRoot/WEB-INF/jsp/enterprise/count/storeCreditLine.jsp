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
			$(document).ready(function(){
				$("#sequence").click(function(){
					$("#orderType").val("1");
					$("#searchForm").submit()
				});
				
				$("#reverse").click(function(){
					$("#orderType").val("2");
					$("#searchForm").submit()
				});
			});
			
			function back(){
				window.location.href = "countInfoAction!diviDataList.action";
			}
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 商户剩余授信额度
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
		</div>					
		<form action="countInfoAction!storeCreditLine.action" method="post" name="searchForm" id="searchForm">
			<table class="listTable2">
				<tr>
					<td class="pn-flabel" style="text-align:left;padding-left:15px;">
					 店铺编号：<input type="text" placeholder="请输入店铺编号" name="storeNum" value="${storeNum }"/>&nbsp;&nbsp;
					 店铺名称：<input type="text" placeholder="请输入店铺名称" name="storeName" value="${storeName }"/>&nbsp;&nbsp;
					 用户名称：<input type="text"  placeholder="请输入用户名称" name="memberName" value="${memberName }"/>&nbsp;&nbsp;
					 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					 <input type="hidden" name="orderType" id="orderType" value="${orderType }"/>&nbsp;&nbsp;
					 <input type="button" class="defaultButton" id="sequence" value="顺序"/>
					 <input type="button" class="defaultButton" id="reverse" value="倒序"/>
					</td>
				</tr>
			</table>
		</form>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>店铺编码</td>
				<td>店铺名称</td>
				<td>绑定用户</td>
				<td>额度</td>
				<!-- <td>操作</td> -->
			</tr>
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.num}</td>
					<td>${bean.sName}</td>
					<td>${bean.name}</td>
					<td>
						<a href="countInfoAction!getAllShouXin.action?mobile=${bean.mobile }">${bean.creditLine}</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!storeCreditLine.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="storeNum" value="${storeNum }"/>
					<input type="hidden" name="storeName" value="${storeName }"/>
					<input type="hidden" name="memberName" value="${memberName }"/>
					 <input type="hidden" name="orderType" id="orderType" value="${orderType }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>