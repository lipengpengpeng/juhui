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

		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 代理下店铺列表
			</div>
			<div class="clear"></div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>	
				
					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>店铺名称</td>
				<td>店铺编码</td>
				<td>店铺地址</td>
				<td>店铺申请时间</td>
				<td>分红权个数</td>
				<td>销售总额</td>
				<td>让利总额</td>
				<td>店铺状态</td>
			</tr>
			
			 
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.storeName }</td>
					<td>${bean.storeNum }</td>
					<td>${bean.province }${bean.city }${bean.area }</td>
					<td>
						<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${bean.diviNum }
					</td>
					<td>
						${bean.saleTotalMoney }
					</td>
					<td>
						${bean.benefitTotalMoney }
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.storeStatus eq '0' }">关闭</c:when>
							<c:when test="${bean.storeStatus eq '1' }">开启</c:when>
							<c:when test="${bean.storeStatus eq '2' }">审核中</c:when>
							<c:when test="${bean.storeStatus eq '3' }">审核失败</c:when>
						</c:choose>
					</td>
				</tr>
	</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="storeInfoAction!findStoreByAgent.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="id" value="${id }"/>
					<input type="hidden" name="token" value="${token }"/>
					<input type="hidden" name="identity" value="${identity }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>