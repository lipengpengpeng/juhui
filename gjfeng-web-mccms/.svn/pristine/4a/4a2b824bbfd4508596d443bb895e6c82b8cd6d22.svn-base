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
		<script type="text/javascript">
				function back(){
					window.location.href="mcProductInfoAction!query.action";
				}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 商品管理 - 评论列表
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户昵称</td>
				<td>手机号码</td>
				<td>评论分数</td>
				<td>评论时间</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.member.nickName }</td>
					<td>${bean.member.mobile }</td>
					<td>${bean.comScore }</td>
					<td>
						<fmt:formatDate value="${bean.comTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<a href="${ctx}/collection/mcProductInfoAction!findProductCommentByIdAndProId.action?id=${bean.id }&productId=${productId}">查看明细</a>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty pager.resultList}">
			<s:form action="mcProductInfoAction!findProductCommentById.action" 
			namespace="/collection" method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" name="productId" value="${productId }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>

	</body>
</html>