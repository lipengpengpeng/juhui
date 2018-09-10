<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos"><!-- 当前位置: 基本配置 - 公共信息管理 --><s:text name="enterprise.system.standby.current.location" /></div>
			<div class="clear"></div>
		</div>
		<table class="listTable3">
			<c:forEach items="${standbys }" var="standby" begin="0" end="0">
				<tr>
					<td style="width: 250px">新浪微博地址：</td>
					<td>${standby.standby1}</td>
				</tr>
				<tr>
					<td style="width: 250px"><!-- 公司英文简称 --><s:text name="enterprise.system.standby.company.short.name" />：</td>
					<td>${standby.standby2}</td>
				</tr>
				 <tr>
					<td style="width: 250px">在线客服：</td>
					<td>${standby.standby3}</td>
				</tr>
				<tr>
					<td style="width: 250px">首页站内搜索框上的快速索引条件：</td>
					<td>${standby.standby4}</td>
				</tr>
				<!--<tr>
					<td style="width: 250px">左栏联系人：</td>
					<td>${standby.standby4}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏地址：</td>
					<td>${standby.standby5}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏电话：</td>
					<td>${standby.standby6}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby7}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby8}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby9}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby10}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby11}</td>
				</tr>
				<tr>
					<td style="width: 250px">左栏传真：</td>
					<td>${standby.standby12}</td>
				</tr> -->
				<tr>
					<td style="width: 250px">公司logo：</td>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto1}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto1}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<td style="width: 250px">微信二维码：</td>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto2}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto2}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<td style="width: 250px">微网站二维码：</td>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto3}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto3}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="button" onclick="javascript:window.location.href='standbyAction!editStandby.htm?id=${standby.id}'" value='<s:text name="common.word.edit" />' />
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>