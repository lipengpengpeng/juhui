<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
				当前位置: 交易管理 - 交易明细
			</div>
		<!-- 	<div class="ropt"><a href="tradeInfoAction!retrieveMemberTradeBenefitByPage.action?ste=1" onclick="return doYouWantToExport();">导出</a></div> -->
		</div>
		<div>
			<table class="listTable3">
				<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="4" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">当前条件统计</font>
		 	  	   </td>
			   </tr>
		  		<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">交易金额：${object.tradeMoneys }</font>
		 	  	   </td>
		 	  	     <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">让利金额：${object.benefitMoneys }</font>
		 	  	   </td>
			   </tr>
			   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">产生会员分红权：${object.memberDividendsNums }</font>
		 	  	   </td>
		 	  	     <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">产生商家分红权：${object.merchantsDividendsNums }</font>
		 	  	   </td>
			   </tr>
			</table>
		</div>
		<form action="tradeInfoAction!retrieveMemberTradeBenefitByPage.action?op=1" method="post">
			<table class="listTable2">
				<tr>
					<td>
						&nbsp;&nbsp;时间：<input type="text" placeholder="请输入时间" name="addTime" value="${addTime }"  onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;
						店铺名称：<input type="text" placeholder="请输入店铺名称" name="storeName" value="${storeName }"/>&nbsp;&nbsp;
						会员名称：<input type="text" placeholder="请输入会员名称" name="name" value="${name }"/>&nbsp;&nbsp;
						个人推荐奖励者：<input type="text" placeholder="请输入名称" name="directMemberName" value="${directMemberName }"/>&nbsp;&nbsp;<br/>	
					&nbsp;&nbsp;店铺推荐奖励者：<input type="text" placeholder="请输入名称" name="directMerchantsName" value="${directMerchantsName }"/>&nbsp;&nbsp;		

		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form> 
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户</td>
				<td>商家</td>
				<td>消费金额</td>
				<td>让利金额</td>
				<td>产生会员分红权</td>
				<td>产生商家分红权</td>
				<td>个人推荐奖励</td>
				<td>个人推荐奖励者</td>
				<td>奖励会员</td>
				<td>奖励商家</td>
				<td>支付类型</td>
				<td>交易状态</td>
				<td>时间</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberTradeBenefits}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.name}</td>
					<td>
						${bean.storeId.storeName}
					</td>
					<td>${bean.tradeMoney }</td>
					<td>${bean.benefitMoney}</td>
					<td>${bean.memberDividendsNum }</td>
					<td>${bean.merchantsDividendsNum }</td>
					<td>${bean.directMemberMoney }</td>
					<td>${bean.directMember.name }</td>
					<td>${bean.directMerchantsMoney }</td>
					<td>${bean.directMerchants.name}</td>
					<td>
							<c:choose>
								<c:when test="${bean.payType eq '0'}">微信支付</c:when>
								<c:when test="${bean.payType eq '1'}">支付宝支付</c:when>
								<c:when test="${bean.payType eq '2'}">银联支付</c:when>
								<c:when test="${bean.paytype eq '4'}">授信金额支付</c:when>
							</c:choose>
					</td>
					<td>
							<c:choose>
								<c:when test="${bean.tradeStatus eq '0'}">待支付</c:when>
								<c:when test="${bean.tradeStatus eq '1'}">已支付</c:when>
								<c:when test="${bean.tradeStatus eq '2'}">支付失败</c:when>
								<c:when test="${bean.tradeStatus eq '3'}">取消</c:when>
							</c:choose>
					</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberTradeBenefits}">
			<s:form action="tradeInfoAction!retrieveMemberTradeBenefitByPage.action?op=1"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
						<input type="hidden" name="addTime" value="${addTime }"/>
						<input type="hidden"  name="storeName" value="${storeName }"/>
						<input type="hidden"  name="name" value="${name }"/>
						<input type="hidden"  name="directMemberName" value="${directMemberName }"/>
						<input type="hidden"  name="directMerchantsName" value="${directMerchantsName }"/>	
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>