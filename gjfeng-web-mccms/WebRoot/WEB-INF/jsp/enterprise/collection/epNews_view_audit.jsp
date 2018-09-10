<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<style type="text/css">
h1 {
	text-align: center;
	font-size: 18px;
}

.info {
	text-align: center;
}
</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 内容管理 --><s:text name="enterprise.collection.current.location" />  - ${enterpriseNews.enterpriseColumn.names} - 
				<!-- 查看新闻信息 --><s:text name="enterprise.collection.current.location.newsview" />
			</div>
			<div class="ropt">
				<!-- 返回列表 -->
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="javascript:window.open('${ctx}/collection/epNewsAction!query.action?father=${enterpriseNews.enterpriseColumn.id}','_self')" />
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${isAdmin == 1}">
		<div>
			<div style="display:none;">
			<!-- 已通过-->
			<input type="button" value='<s:text name="enterprise.collection.passed" />' />
			&nbsp;
			<!-- 不通过-->
			<input type="button" value='<s:text name="enterprise.collection.not.pass" />' />
			&nbsp;
			</div>
			<!-- 前台预览-->
			<%-- <input type="button" value='<s:text name="enterprise.collection.front.view" />' onclick="javascript:window.open('${ctx}/navigation.htm?newsId=${enterpriseNews.id}&columnType=news','_blank')"/> --%>
		</div>
		</c:if>
		<div style="border: 1px solid #ccc;">
			<h1>
				${enterpriseNews.title}
			</h1>
			<p class="info">
				<!-- 发布者:  --><s:text name="enterprise.collection.publisher" />${enterpriseNews.users.name} &nbsp;<!-- 来源: --><s:text name="enterprise.collection.origin" />${enterpriseNews.source}
				&nbsp; <!-- 发布时间:  --><s:text name="enterprise.collection.publish.time" />
				<fmt:formatDate value="${enterpriseNews.editTime}"
					pattern="yyyy-MM-dd" />
			</p>
			<div>
				${enterpriseNews.contents}
			</div>
			<br/>
			<hr/>
			<div>
			<c:if test="${enterpriseNews.isPrimPhoto == 2}">
				<p><font color="red"><!-- 已经上传文件： --><s:text name="enterprise.collection.has.uploaded.file" /></font></p>
				<div >
					<a href="${ctx}/upload/enterprice/${enterpriseNews.photo}"
								target="_blank"
								>${enterpriseNews.photo}
						</a>
				</div>
			</c:if>
				<c:if test="${enterpriseNews.isPrimPhoto == 1}">
				<p><font color="red"><!-- 附：图片新闻已上传的图片：  --><s:text name="enterprise.collection.has.uploaded.photo" /></font></p>
					<div class="info">
						<img
							src="
						<c:choose>
							<c:when test="${empty enterpriseNews.photo}">
									${ctx}/upload/MissPhoto.JPG
							</c:when>
							<c:otherwise>
								${ctx}/upload/enterprice/${enterpriseNews.photo}
							</c:otherwise>
						</c:choose>
						"/>
					</div>
				</c:if>
			</div>
			<div>

			</div>
		</div>
			<form action="epNewsAction!updateState.action" method="post">
				<input type="hidden" value="${enterpriseNews.id }" name="id" />
				<table align="center" class="listTable3">
					<tr align="center">
						<td align="center">审核状态</td>
						<td align="center">
							<select name="state" >
								<option value="0">不通过</option>
								<option value="1">通过</option>
							</select>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2" align="center">
							<input type="submit" value="提交" />
						</td>
					</tr>
				</table>
				
			</form>
	</body>
</html>

