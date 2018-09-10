<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>

		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 互动管理 - 友情链接管理 --><s:text name="enterprise.dynamic.friendlink.current.location" />
			</div>
			<div class="ropt">
				<a href="enterpriseLinksAction!add_page.action"><!-- 新增 --><s:text name="common.word.new" /></a>
			</div>
			<div class="clear"></div>
		</div>

		<table class="listTable3" onmouseover="changeto()"
			onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>
					<!-- 链接名称 --><s:text name="enterprise.dynamic.friendlink.name" />
				</td>
				<td>
					<!-- 链接标识 --><s:text name="enterprise.dynamic.friendlink.id" />
				</td>
				<td>
					<!-- 链接排序 --><s:text name="enterprise.dynamic.friendlink.order" />
				</td>
				<td>
					<!-- 链接地址 --><s:text name="enterprise.dynamic.friendlink.url" />
				</td>
				<td style="display:none;">
					<!-- 显示时间--><s:text name="common.word.time.show" />
				</td>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作 --><s:text name="common.word.handle" />
				</td>
			</tr>
			<c:forEach var="enterpriseLinks" items="${enterpriseLinksList}">
				<tr>
					<td>
						${enterpriseLinks.names}
					</td>
					<td>
						${enterpriseLinks.frontNum}
					</td>
					<td>
						${enterpriseLinks.orderColumn}
					</td>
					<td>
						${enterpriseLinks.address}
					</td>
					<td style="display:none;">
						<fmt:formatDate value="${enterpriseLinks.initTime}"
							pattern="yyyy-MM-dd" />
						<!-- 至--><s:text name="enterprise.collection.time.to" />
						<fmt:formatDate value="${enterpriseLinks.endTime}"
							pattern="yyyy-MM-dd" />
					</td>
					<td>
						<c:if test="${enterpriseLinks.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
						<c:if test="${enterpriseLinks.state == 0}">
							<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
						</c:if>
					</td>
					<td>
						<a
							href="enterpriseLinksAction!view.action?id=${enterpriseLinks.id}"">
							<!-- 查看  --><s:text name="common.word.view" /> </a>&nbsp; &nbsp;
						<a
							href="enterpriseLinksAction!edit.action?id=${enterpriseLinks.id}">
							<!-- 编辑  --><s:text name="common.word.edit" /> </a>&nbsp; &nbsp;
						<a
							href="enterpriseLinksAction!delete.action?id=${enterpriseLinks.id}"
							onclick="return doYouWantToDel();">
							<!-- 删除  --><s:text name="common.word.delete" /></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<s:form action="enterpriseLinksAction" method="get" id="form1" name="form1"
			theme="simple">
			
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			
		</s:form>
	</body>
</html>

