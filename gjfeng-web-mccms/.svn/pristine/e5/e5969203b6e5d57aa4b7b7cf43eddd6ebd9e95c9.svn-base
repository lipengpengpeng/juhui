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
				当前位置: 商铺分红权列表
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
			<div class="clear"></div>
		</div>	
		
		<form action="storeInfoAction!retrieveStoreByPager.action" method="post">
			<table class="listTable2" >
				<tr>
					<td>
						关键字：<input type="text" placeholder="请输入关键字" name="likeValue" id="likeValue" value="${likeValue }"/>&nbsp;&nbsp;
						<input type="hidden" name="op" value="${op }" />
						<input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>		
						
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>店铺名称</td>
				<td>店铺编码</td>
				<td>店铺申请时间</td>
				<td>分红权个数</td>
				<td>店铺类型</td>
				<td>店铺状态</td>		
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.storeName }</td>
					<td>${bean.storeNum }</td>
					<td>
						<fmt:formatDate value="${bean.storeAddTime }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${bean.storeDividendsNum }
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.storeType eq '0'}">O2O</c:when>
							<c:when test="${bean.storeType eq '1'}">网上商城</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.storeStatus eq '0'}">关闭</c:when>
							<c:when test="${bean.storeStatus eq '1'}">开启</c:when>
							<c:when test="${bean.storeStatus eq '2'}">审核中</c:when>
							<c:when test="${bean.storeStatus eq '3'}">审核失败</c:when>
						</c:choose>
					</td>
					
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="storeInfoAction!retrieveStoreByPager.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="op" value="${op }" />
					<input type="hidden"  name="likeValue" id="likeValue" value="${likeValue }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>