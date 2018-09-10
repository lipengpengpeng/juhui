<%--

	gjfMemberDiviHistory.jsp

	Create by MCGT

	Create time 2017-01-20

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

		<script type="text/javascript">
				function doYouWantToExport() {
					if(confirm('确定要导出所有记录吗?')) {
						return true;
					}
					return false;
				}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 模块 - 子模块
			</div>
			<!-- <div class="ropt">
				<a href="gjfMemberDiviHistoryAction!newPage.action">新增</a>
			</div> -->
			 <div class="ropt"><a href="${ctx}/subsystem/tradeInfoAction!findMerchantUpgradeHistory.action?tradeType=${tradeType }&ste=1" onclick="return doYouWantToExport();">导出</a></div> 
			<div class="clear"></div>
		</div>
		
		<form action="tradeInfoAction!findMerchantUpgradeHistory.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						 商家类型：<select name="tradeType">
					        <option value="" <c:if test="${tradeType eq '4'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeType eq '0'}">selected="selected"</c:if>>普通用户</option>
					        <option value="1" <c:if test="${tradeType eq '1'}">selected="selected"</c:if>>商家版</option>
					        <option value="2" <c:if test="${tradeType eq '2'}">selected="selected"</c:if>>企业版</option>
					        <option value="3" <c:if test="${tradeType eq '3'}">selected="selected"</c:if>>商家代理版</option>
					        <option value="4" <c:if test="${tradeType eq '4'}">selected="selected"</c:if>>企业代理版</option>
					        <option value="5" <c:if test="${tradeType eq '5'}">selected="selected"</c:if>>地级市代理</option>
					        <option value="6" <c:if test="${tradeType eq '6'}">selected="selected"</c:if>>市级代理</option>
					        <option value="7" <c:if test="${tradeType eq '7'}">selected="selected"</c:if>>直辖市代理</option>
					     </select>
					     &nbsp;&nbsp;用户名称：<input type="text" placeholder="请输入用户名称" name="memberName" value="${memberName }"/>
						 &nbsp;&nbsp;用户电话：<input type="text" placeholder="请输入用户电话" name="mobile" value="${mobile }"/>&nbsp;&nbsp;
					     <input type="submit" value="查询"/>
					</td>
				</tr>
			</table>
		</form>

		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名称</td>
				<td>用户号码</td>			
				<td>交易金额</td>				
				<td>交易类型</td>
				<td>支付类型</td>
				<td>赠送类型</td>
				<td>推荐人电话</td>
				<td>推荐人姓名</td>
				<td>推荐人奖励金额</td>
				<td>交易状态</td>			
				<td>添加时间</td>	
		
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberName}</td>
					<td>${bean.memberMobile}</td>
					<td>${bean.tradeMoney}</td>	
					<td>
					  <c:if test="${bean.tradeType==0}">普通会员</c:if>
					  <c:if test="${bean.tradeType==1}">商家版</c:if>
					  <c:if test="${bean.tradeType==2}">企业版</c:if>
					  <c:if test="${bean.tradeType==3}">商家代理版</c:if>
					  <c:if test="${bean.tradeType==4}">企业代理版</c:if>
					  <c:if test="${bean.tradeType==5}">地级市代理</c:if>
					  <c:if test="${bean.tradeType==6}">市级代理</c:if>
					  <c:if test="${bean.tradeType==7}">直辖市代理</c:if>
					</td>			
					<td>
					  <c:if test="${bean.payType==0}">微信</c:if>
					  <c:if test="${bean.payType==1}">支付宝</c:if>
					  <c:if test="${bean.payType==2}">后台充值</c:if>
					  <c:if test="${bean.payType==3}">凤凰宝</c:if>					 
					</td>
					<td>					
					   
					   <c:if test="${bean.giveType==0}">充值</c:if>                      
					   <c:if test="${bean.giveType==1}">赠送</c:if>
					   <%-- <c:if test="${bean.giveType==2}">交易失败</c:if> --%>
					</td>	
					<td>${bean.directMemberName}</td>	
					<td>${bean.directMemberMobile}</td>	
					<td>${bean.directMemberMoney}</td>
					<td>
					  <c:if test="${bean.tradeStatus==0}">待支付</c:if>
					  <c:if test="${bean.tradeStatus==1}">支付成</c:if>
					  <c:if test="${bean.tradeStatus==2}">支付失败</c:if>
								 
					</td>					
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>									
					
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!findMerchantUpgradeHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<input type="hidden" name="memberName" value="${memberName}"/>
				<input type="hidden" name="mobile" value="${mobile}"/>
				<input type="hidden" name="tradeType" value="${tradeType}"/>
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>