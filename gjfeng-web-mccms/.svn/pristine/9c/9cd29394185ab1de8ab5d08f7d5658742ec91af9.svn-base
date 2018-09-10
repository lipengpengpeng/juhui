<%--

	gjfMemberBenefitRecord_edit.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<script>
			$(document).ready(function(){
				$("#gjfMemberBenefitRecordForm").validate({
					 rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/tradeInfoAction!findShouxinHistory.action'"/></div>
		</div>

		<form action="tradeInfoAction!aduilShouxinStatus.action" method="post" id="gjfMemberBenefitRecordForm" name="gjfMemberBenefitRecordForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${resultVo.result.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">充值的用户</td>
					<td><input type="text" id="memberId" disabled="disabled" value="${resultVo.result.memberId.name}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请金额</td>
					<td><input type="text" id="storeId"  disabled="disabled" value="${resultVo.result.applyMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易金额 </td>
					<td><input type="text" id="benefitMoney"  disabled="disabled" value="${resultVo.result.tradeMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td><fmt:formatDate value="${resultVo.result.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付凭证</td>
					<td>
					<a href="${resultVo.result.shouxinCredntialsImg}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.shouxinCredntialsImg}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.shouxinCredntialsImg}
												</c:otherwise>
											</c:choose>
											"/>
								</a>					  					  
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易状态</td>
					<td>
					  <select id="tradeStatus" name="tradeStatus">     
					        <option value="1">交易成功</option>
					        <option value="2">交易失败</option>
					   </select>
					   </td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>		
</html>