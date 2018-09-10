<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
		<script type="text/javascript">
			function back(){
				window.location.href = 'countInfoAction!countMemberInfoAmount.action?op=membersConsume';
			}
		</script>
		</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 会员消费明细（网上商城消费）
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back()"/></div>
			<div class="clear"></div>
		</div>
		<form action="countInfoAction!findMemberConsumeHistory.action" method="post">
			<input type="hidden" name="id" value="${id }"/>
			<table class="listTable2" >
				<tr>
					<td>
						 开始时间：<input type="text" readonly="readonly" placeholder="请选择日期" value="${startTime }" name="startTime" id="startTime" onclick="WdatePicker()" style="width: 75px;"/>
						 -
					  	 结束时间：<input type="text" readonly="readonly" placeholder="请选择日期" value="${endTime }" name="endTime" id="endTime" onclick="WdatePicker()" style="width: 75px;"/>
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		
		
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单编号</td>
				<td>消费金额</td>
				<td>让利金额</td>
				<td>订单完成时间</td>
				<td>订单状态</td>
			</tr>

			<c:forEach var="bean" items="${orderInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.orderSn }</td>
					<td>${bean.totalAmount }</td>
					<td>${bean.benefitAmount }</td>
					<td><fmt:formatDate value="${bean.finishedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.orderStatus eq '0' }">未付款</c:when>
							<c:when test="${bean.orderStatus eq '1' }">已付款</c:when>
							<c:when test="${bean.orderStatus eq '2' }">已发货</c:when>
							<c:when test="${bean.orderStatus eq '3' }">已收货</c:when>
							<c:when test="${bean.orderStatus eq '4' }">已过期</c:when>
							<c:when test="${bean.orderStatus eq '5' }">已取消</c:when>
							<c:when test="${bean.orderStatus eq '6' }">已退款</c:when>
						</c:choose>
						
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty orderInfos}">
			<s:form action="countInfoAction!findMemberConsumeHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" readonly="readonly" name="id" value="${id }"/>
				<input type="hidden" readonly="readonly"  value="${startTime }" name="startTime" id="startTime" />
				<input type="hidden" readonly="readonly"  value="${endTime }" name="endTime" id="endTime"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
		
		<div id="main" style="width: 100%;height: 500px;margin-top: 50px">
		
		</div>
		
		<c:forEach items="${memberMonthsTurnover }" var="m">
			<input type="hidden" name="memberMonthsTurnover" value="${m }"/>
		</c:forEach>
		
		<script type="text/javascript">
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));
		        var memberMonthsTurnover = document.getElementsByName("memberMonthsTurnover");
		        var memberTurnoverArray = [];
		        for(var i=0;i<memberMonthsTurnover.length;i++)
		        {
		        	memberTurnoverArray.push(memberMonthsTurnover[i].value);
		        }
		        var option = {
					    title: {
					        text: '近三月消费数据'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['月消费额']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    toolbox: {
					        feature: {
					            saveAsImage: {}
					        }
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: ['上上个月','上一个月','当前月'],
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [
					        {
					            name:'月消费额',
					            type:'line',
					            stack: '元',
					            data:memberTurnoverArray
					        }
					    ]
					};
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		</script>

	</body>
</html>