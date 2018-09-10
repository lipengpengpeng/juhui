<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 评论明细</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">商品名称</td>
					<td>${gjfProductComment.product.name}</td>
				</tr>	
				<tr>
					<td class="pn-flabel" width="100px">会员名称</td>
					<td>${gjfProductComment.member.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">会员昵称</td>
					<td>${gjfProductComment.member.nickName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">联系方式</td>
					<td>${gjfProductComment.member.mobile}</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">邮箱</td>
					<td>${gjfProductComment.member.email}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">评论内容</td>
					<td>${gjfProductComment.content}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">评论图片</td>
					<td>
						<img width="96" height="96" src="
							<c:choose>
								<c:when test="${empty gjfProductComment.comImg}">
										${ctx}/upload/MissPhoto.JPG
								</c:when>
								<c:otherwise>
									${gjfProductComment.comImg}
								</c:otherwise>
							</c:choose>
						" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">评分</td>
					<td>${gjfProductComment.comScore}分</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">评论时间</td>
					<td><fmt:formatDate value="${gjfProductComment.comTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
		 </table>
	</body>
</html>