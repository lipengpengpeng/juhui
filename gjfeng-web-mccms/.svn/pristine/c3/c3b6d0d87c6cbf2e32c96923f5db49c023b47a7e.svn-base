<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 互动管理 - 友情链接管理 --><s:text name="enterprise.dynamic.friendlink.current.location" /> - 
				<!-- 查看友情链接信息 --><s:text name="enterprise.dynamic.friendlink.current.location.view" />
			</div>
			
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()" />
			</div>
			<div class="clear"></div>
		</div>
		<table align="center" class="listTable3" width="90%">
			<tr>
				<td class="pn-flabel" width="100px">
					<!-- 链接名称 --><s:text name="enterprise.dynamic.friendlink.name" />
				</td>
				<td>
					${enterpriseLinks.names}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 链接标识 --><s:text name="enterprise.dynamic.friendlink.id" />
				</td>
				<td>
					${enterpriseLinks.frontNum}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 链接排序 --><s:text name="enterprise.dynamic.friendlink.order" />
				</td>
				<td>
					${enterpriseLinks.orderColumn}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 链接地址 --><s:text name="enterprise.dynamic.friendlink.url" />
				</td>
				<td>
					${enterpriseLinks.address}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 链接简介 --><s:text name="enterprise.dynamic.friendlink.description" />
				</td>
				<td>
					${enterpriseLinks.intro}
				</td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 显示时间--><s:text name="common.word.time.show" />
				</td>
				<td>
					<!-- yyyy 年 MM 月 dd 日-->
					<fmt:formatDate value="${enterpriseLinks.initTime}"
						pattern="yyyy-MM-dd" />
					&nbsp;<!-- 至--><s:text name="enterprise.collection.time.to" />&nbsp;
					<fmt:formatDate value="${enterpriseLinks.endTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 状态--><s:text name="common.word.status" />
				</td>
				<td>
					<c:if test="${enterpriseLinks.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
					<c:if test="${enterpriseLinks.state == 0}">
						<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
					</c:if>
				</td>
			</tr>
		</table>
		<br />
	</body>
</html>

