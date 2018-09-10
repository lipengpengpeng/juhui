<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">

<body style="padding:5% 3%;">
	<div class="help-con">
		<div class="notice-title">
			<h3 style="text-align: center;font-size:57px; font-weight: bold;margin:5% 0">${newsInfo.title}</h3>
			<div style="font-size:34px;color:#8E8E8E;position:relative">
				<!--  <img style="border-radius: 150px;width:8%;margin-right:2%" src="${ctx }/upload/enterprice/image/${topic.userImage}"/>-->

				<span style="position:absolute;top:25%">
					<span>${newsInfo.autor }</span>
					
				</span>
				<span style="position:absolute;right:0;top:25%"><fmt:formatDate value="${newsInfo.initTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /> </span>
			</div>
			<p style="text-align: center;font-size: 12px;color: #777;">
				
			</p>
		</div>
		<div class="page-content" style="width:95%;margin-top: 8rem;">${newsInfo.contents}</div>
	</div>
</body>
</html>
