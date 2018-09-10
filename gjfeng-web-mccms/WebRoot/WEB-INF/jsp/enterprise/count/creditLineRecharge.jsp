<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/echarts.js"></script>
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
				function back(){
					window.location.href = "countInfoAction!diviDataList.action";
				}
				
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 授信额度充值数据
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
		</div>	
		<input type="hidden" name="todayCreditLineRecharge" value="${resultVo.result.todayCreditLineRecharge }"/>				
		<input type="hidden" name="totalCreditLineRecharge" value="${resultVo.result.totalCreditLineRecharge }"/>	
		
		<div id="main" style="width:100%;height:420px;">
			
		</div>
		
		<c:forEach items="${resultVo.result.monthCreditLineRecharge }" var='s'>
			<input type="hidden" name="monthRecharge" value="${s }" />
		</c:forEach>
		<c:forEach items="${date }" var="m5">
			<input type="hidden" name="date" value="${m5 }"/>
		</c:forEach>
		
		<script type="text/javascript">
		  	var chart = echarts.init(document.getElementById('main'));
	        var myDate=new Date();
			//设置日期，当前日期的前七天
			/* var myDate = new Date(); //获取今天日期
			myDate.setDate(myDate.getDate() - 30);
			var dateArray = []; 
			var dateTemp; 
			var flag = 1; 
			for (var i = 0; i < 30; i++) {
			    dateTemp =myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
			    dateArray.push(dateTemp);
			    myDate.setDate(myDate.getDate() + flag);
			} */
			var monthRecharge = document.getElementsByName("monthRecharge");
			var date = document.getElementsByName("date");
			var rechargeArray = [];
			var dateArray = [];
			for(var i=0;i<monthRecharge.length;i++)
	        {
				rechargeArray.push(monthRecharge[i].value);
	        }	
			for(var i=0;i<date.length;i++)
	        {
				dateArray.push(date[i].value);
	        }
	        var option = {
	        	    title : {
	        	        text: '近30天授信金额购买趋势',
	        	    },
	        	    tooltip : {
	        	        trigger: 'axis'
	        	    },
	        	    legend: {
	        	        data:['授信金额']
	        	    },
	        	    toolbox: {
	        	        show : true,
	        	        feature : {
	        	            dataView : {show: true, readOnly: false},
	        	            magicType : {show: true, type: ['line', 'bar']},
	        	            restore : {show: true},
	        	            saveAsImage : {show: true}
	        	        }
	        	    },
	        	    calculable : true,
	        	    xAxis : [
	        	        {
	        	            type : 'category',
	        	            data : dateArray
	        	        }
	        	    ],
	        	    yAxis : [
	        	        {
	        	            type : 'value'
	        	        }
	        	    ],
	        	    series : [
	        	        {
	        	            name:'购买金额',
	        	            type:'bar',
	        	            data:rechargeArray,
	        	          
	        	        }
	        	    ]
	        	};
				chart.setOption(option);
		</script>				
						
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>用户</td>
				<td>交易金额</td>
				<td>添加时间</td>
				<td>付款时间</td>
				<td>交易状态</td>
				<td>支付类型</td>
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.memberId.name }</td>
					<td>${bean.tradeMoney }</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${bean.tradeTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq '0'}">未支付</c:when>
							<c:when test="${bean.tradeStatus eq '1'}">交易成功</c:when>
							<c:when test="${bean.tradeStatus eq '2'}">交易失败</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.payType eq '0'}">余额支付</c:when>
							<c:when test="${bean.payType eq '1'}">微信支付</c:when>
							<c:when test="${bean.payType eq '2'}">支付宝支付</c:when>
							<c:when test="${bean.payType eq '3'}">银联支付</c:when>
							<c:when test="${bean.payType eq '4'}">H5支付</c:when>
							<c:when test="${bean.payType eq '5'}">后台充值消费</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!toCreditLineRecharge.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>