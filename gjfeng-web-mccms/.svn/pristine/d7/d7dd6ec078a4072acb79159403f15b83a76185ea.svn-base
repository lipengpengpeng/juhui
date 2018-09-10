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
			.data{
				background-color:#66CDAA;
				height: 60px;
				width: 33.3%;
				float: left;
				text-align: center;
				line-height: 60px;
				font-size: 18px;
			}
			.data1{
				height: 80px;
				width: 33.3%;
				float: left;
				text-align: center;
				line-height: 60px;
			}
			.top{
				width: 100%;
				height: 50%;
				background-color: #1F6D9E;
				text-align: center;
				line-height: 40px;
				font-weight: bold;
			}
			.bottom{
				width: 100%;
				height: 50%;
			}
			.d1{
				width: 49%;
				height: 100%;
				background-color: #253B48;
				float: left;
				text-align: center;
				line-height: 40px;
				font-weight: bold;
				margin-top:1px;
				margin-left: 4px;
				margin-bottom: 1px;
			}
			.d2{
				width: 49%;
				height: 100%;
				background-color: #253B48;
				float: left;
				text-align: center;
				line-height: 40px;
				font-weight: bold;
				margin-top:1px;
				margin-left: 1px;
				margin-bottom: 1px;
			}
			
		</style>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#memberPoolIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=1&type=8";
			});
			$("#memberPoolOut").click(function(){
				window.location.href = "countInfoAction!findPoolOut.action?op=1&type=0";
			});
			$("#storePoolIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=2&type=9";
			});
			$("#storePoolOut").click(function(){
				window.location.href = "countInfoAction!findPoolOut.action?op=2&type=1";
			});	
			$("#platFormIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=3&type=10";
			});
			$("#agentCityPoolIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=4&type=13";
			});
			$("#agentCityPoolOut").click(function(){
				window.location.href = "countInfoAction!findPoolOut.action?op=3&type=5";
			});
			$("#agentAreaPoolIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=5&type=14";
			});
			$("#agentAreaPoolOut").click(function(){
				window.location.href = "countInfoAction!findPoolOut.action?op=4&type=6";
			});
			$("#agentIndiPoolIn").click(function(){
				window.location.href = "countInfoAction!findPoolIn.action?op=6&type=15";
			});
			$("#agentIndiPoolOut").click(function(){
				window.location.href = "countInfoAction!findPoolOut.action?op=5&type=7";
			});
			
		})
				
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 资金池
			</div>
			<div class="ropt">
				<!-- <a href="">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		<table class="listTable2">
			<%-- <tr>
				<td>
					<div class="data" style="background-color:#253B48;">
						<font color="#FFFFFF">会员资金池总金额：<strong>${gjfBenefitPool.memberSysPoolTotal }</strong></font>
					</div>
					
					<div class="data" style="background-color: #253B48;">
						<font color="#FFFFFF">商家资金池总金额：<strong>${gjfBenefitPool.merchantSysPoolTotal }</strong></font>
					</div>
					
					<div class="data" style="background-color: #253B48	">
						<font color="#FFFFFF">平台资金池总金额：<strong>${gjfBenefitPool.platformSysPoolTotal }</strong></font>
					</div>
				</td>
			</tr> --%>
			<tr>
				<td>
					<div class="data" style="background-color: #253B48">
						<font color="#FFFFFF">当前会员资金池金额：<strong>${gjfBenefitPool.memberSysPoolCur }</strong></font>
					</div>
					
					<div class="data" style="background-color: #253B48">
						<font color="#FFFFFF">当前商家资金池金额：<strong>${gjfBenefitPool.merchantSysPoolCur }</strong></font>
					</div>
					
					<div class="data" style="background-color: #253B48">
						<font color="#FFFFFF">当前平台资金池金额：<strong>${gjfBenefitPool.platformSysPoolCur }</strong></font>
					</div>				
				</td>
			</tr>
			<tr>
				<td>
					<div class="data1">
						<div class="top">
							<font color="aliceblue">会员池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="memberPoolIn" style="cursor:pointer;">
								<font color="aliceblue">收入:${memberPoolIn }</font>
							</div>
							<div class="d2" id="memberPoolOut" style="cursor:pointer;">
								<font color="aliceblue">支出:${memberPoolOut }</font>
							</div>
						</div>
					</div>
					
					<div class="data1">
						<div class="top">
							<font color="aliceblue">商户池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="storePoolIn" style="cursor:pointer;">
								<font color="aliceblue">收入:${storePoolIn }</font>
							</div>
							<div class="d2" id="storePoolOut" style="cursor:pointer;">
								<font color="aliceblue">支出:${storePoolOut }</font>
							</div>
						</div>
					</div>
					
					<div class="data1">
						<div class="top">
							<font color="aliceblue">平台池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="platFormIn" style="cursor:pointer;">
								<font color="aliceblue">收入:${platFormIn }</font>
							</div>
							<div class="d2">
								<font color="aliceblue" >提现手续费:${platFormTex }</font>
							</div>
						</div>
					</div>				
				</td>
			</tr>
			<tr>
				<td>
					<div class="data1">
						<div class="top">
							<font color="aliceblue">市代池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="agentCityPoolIn" style="cursor:pointer;">
								<font color="aliceblue" >收入:${agentCityPoolIn }</font>
							</div>
							<div class="d2" id="agentCityPoolOut" style="cursor:pointer;">
								<font color="aliceblue" >支出:${agentCityPoolOut }</font>
							</div>
						</div>
					</div>
					
					<div class="data1">
						<div class="top">
							<font color="aliceblue">区代池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="agentAreaPoolIn" style="cursor:pointer;">
								<font color="aliceblue" >收入:${agentAreaPoolIn }</font>
							</div>
							<div class="d2" id="agentAreaPoolOut" style="cursor:pointer;">
								<font color="aliceblue" >支出:${agentAreaPoolOut }</font>
							</div>
						</div>
					</div>
					
					<div class="data1">
						<div class="top">
							<font color="aliceblue">个代池</font>
						</div>
						<div class="bottom">
							<div class="d1" id="agentIndiPoolIn" style="cursor:pointer;">
									<font color="aliceblue" >收入:${agentIndiPoolIn }</font>
							</div>
							<div class="d2" id="agentIndiPoolOut" style="cursor:pointer;">
									<font color="aliceblue" >支出:${agentIndiPoolOut }</font>
							</div>
						</div>
					</div>				
				</td>
			</tr>
		</table>
		<%-- <table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>当次资金池分红金额</td>
				<td>当次资金池分红前金额</td>
				<td>当次资金池分红后金额</td>
				<td>当次资金池分红时间</td>
				<td>当次资金池分红类型</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.benefitMoney }</td>
					<td>${bean.benefitMoneyBf }</td>
					<td>${bean.benefitMoneyAf }</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.benefitType eq 0}">抽出会员分红池</c:when>
							<c:when test="${bean.benefitType eq 1}">抽出商家分红池</c:when>
							<c:when test="${bean.benefitType eq 2}">抽出平台分红池</c:when>
							<c:when test="${bean.benefitType eq 3}">抽出直推会员分红池</c:when>
							<c:when test="${bean.benefitType eq 4}">抽出直推商家分红池</c:when>
							<c:when test="${bean.benefitType eq 5}">抽出市代代分红池</c:when>
							<c:when test="${bean.benefitType eq 6}">抽出区代分红池</c:when>
							<c:when test="${bean.benefitType eq 7}">抽出个代分红池</c:when>
							<c:when test="${bean.benefitType eq 8}">充进会员池</c:when>
							<c:when test="${bean.benefitType eq 9}">充进商家分红池</c:when>
							<c:when test="${bean.benefitType eq 10}">消费充进平台分红池</c:when>
							<c:when test="${bean.benefitType eq 11}">充进直推会员分红池</c:when>
							<c:when test="${bean.benefitType eq 12}">充进直推商家分红池</c:when>
							<c:when test="${bean.benefitType eq 13}">充进市代分红池</c:when>
							<c:when test="${bean.benefitType eq 14}">充进区代分红池</c:when>
							<c:when test="${bean.benefitType eq 15}">充进个代分红池</c:when>
							<c:when test="${bean.benefitType eq 16}">提现充进平台池</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!countBenefitPool.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if> --%>

	</body>
</html>