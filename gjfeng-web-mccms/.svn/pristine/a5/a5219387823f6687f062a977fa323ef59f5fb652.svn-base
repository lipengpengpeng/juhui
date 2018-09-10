<%--

	gjfProductInfo.jsp

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
				<a href="gjfProductInfoAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table id="tableId" class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td><!-- 序号  --><s:text name="common.word.id" /></td>
				<td style="width: 20%;"><!-- 产品名称 --><s:text name="enterprise.collection.product.name" /></td>
				<td>商品货号</td>
				<td>商品类型</td>
				<td>价格</td>
				<td>市场价</td>
				<td>销量</td>
				<td>浏览量</td>
				<td>收藏量</td>
				<td><!--  --><s:text name="common.word.add.time" /></td>
				<td>商品状态 </td>
				<td>审核状态 </td>
			</tr>

			<c:forEach var="bean" items="${gjfProductInfos}"
				varStatus="status">
				<tr>
					<td style="text-align:center;">${status.count}</td>
					<td>${bean.name}</td>
					<td>
						
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.storeId.storePro eq 0 }">O2O</c:when>
							<c:when test="${bean.storeId.storePro eq 1 }">网上商城</c:when>
						</c:choose>
					</td>
					<td>
						市场价：<font color="#333333"><fmt:formatNumber value="${bean.price}" pattern="0.00"></fmt:formatNumber></font><br/>
						零售价：<font color="red"><fmt:formatNumber value="${bean.marketPrice}" pattern="0.00"></fmt:formatNumber></font><br/>
					</td>
					
					<td style="text-align:center;"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd" /></td>
					
					<td style="text-align:center;" class="checkStatusTr">
						 	<c:choose>
								<c:when test="${bean.status == 0}">
								下架
								</c:when>
								<c:when test="${bean.status == 1}">
								正常
								</c:when>
								<c:when test="${bean.status == 2}">
								违规
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
					</td>
					
					
					<td style="text-align:center;" class="checkVerifyTr">
						 	<c:choose>
								<c:when test="${bean.aduitStatus eq '0'}">未通过</c:when>
								<c:when test="${bean.aduitStatus eq '1'}">通过</c:when>
								<c:when test="${bean.aduitStatus eq '2'}">审核中</c:when>
								<c:otherwise> </c:otherwise>
							</c:choose>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfProductInfos}">
			<s:form action="gjfProductInfoAction!retrieveAllGjfProductInfos.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>