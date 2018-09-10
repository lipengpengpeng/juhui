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
				<!-- 当前位置: 互动管理 - 广告信息管理 --><s:text name="enterprise.dynamic.advertise.current.location" /> - 
				<!-- 查看广告信息 --><s:text name="enterprise.dynamic.advertise.current.location.viewad" />
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()" />
			</div>
			<div class="clear"></div>
		</div>
		<table  class="listTable3">
			<tr>
				<td width="100px" class="pn-flabel">
					<!-- 广告名称 --><s:text name="enterprise.dynamic.advertise.name" />
				</td>
				<td>
					${enterpriseAd.names}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 广告标识 --><s:text name="enterprise.dynamic.advertise.id" />
				</td>
				<td>
					${enterpriseAd.frontNum}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 广告排序 --><s:text name="enterprise.dynamic.advertise.order" />
				</td>
				<td>
					${enterpriseAd.orderColumn}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 广告图片 --><s:text name="enterprise.dynamic.advertise.picture" />
				</td>
				<td>
					<img
						src="
					<c:choose>
						<c:when test="${empty enterpriseAd.photo}">
								${ctx}/upload/MissPhoto.JPG
						</c:when>
						<c:otherwise>
							${enterpriseAd.photo}
						</c:otherwise>
					</c:choose>
					" />
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 广告链接地址 --><s:text name="enterprise.dynamic.advertise.url" />
				</td>
				<td>
					${enterpriseAd.address}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 广告简介 --><s:text name="enterprise.dynamic.advertise.description" />
				</td>
				<td>
					${enterpriseAd.intro}
				</td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 广告费用 --><s:text name="enterprise.dynamic.advertise.cost" />
				</td>
				<td>
					${enterpriseAd.acost}
				</td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 客户名称 --><s:text name="enterprise.dynamic.client.name" />
				</td>
				<td>
					${enterpriseAd.clientName}
				</td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 显示时间--><s:text name="common.word.time.show" />
				</td>
				<td>
					<fmt:formatDate value="${enterpriseAd.initTime}"
						pattern="yyyy-MM-dd" />
					-- <!-- 至--><s:text name="enterprise.collection.time.to" /> --
					<fmt:formatDate value="${enterpriseAd.endTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 更新时间  --><s:text name="common.word.update.time" />
				</td>
				<td>
					<fmt:formatDate value="${enterpriseAd.editTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 编辑人 --><s:text name="common.word.author" />
				</td>
				<td>
					${enterpriseAd.users.name}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 状态--><s:text name="common.word.status" />
				</td>
				<td>
					<c:if test="${enterpriseAd.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
					<c:if test="${enterpriseAd.state == 0}">
						<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
					</c:if>
				</td>
			</tr>
		</table>
		<br />
	</body>
</html>

