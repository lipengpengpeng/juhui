﻿<%--

	gjfProductAttr.jsp

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
						
			function openInfo(id){
				if(confirm("是否确认删除？")){
					$.ajax({
	      				type: "post",
	      				url: 'tradeInfoAction!deleteDivs.action',
	      				dataType: "text",
	      				data:{
	      					"setDivi.id":id
							},
	      				success:function(data){
	      					 location.reload();
	      				},
	      				error:function(){
	  
	      				}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 设置 -赠送尊享版商家
			</div>+
			<div class="ropt">
				<%-- <input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/>
				&nbsp;&nbsp;&nbsp; --%>
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='tradeInfoAction!goMerchantGivePage.action?type=${type}&id=${id}'"/>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名</td>
				<td>用户电话号码</td>
				
			</tr>

			<c:forEach var="bean" items="${resultVo.result}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberName}</td>
					<td>${bean.memberMobile}</td>
					
				</tr>
			</c:forEach>

		</table>

		
	</body>
</html>