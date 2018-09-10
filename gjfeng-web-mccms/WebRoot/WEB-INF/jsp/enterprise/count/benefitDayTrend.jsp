<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
	<link href="${ctx}/image/style_blue5/style.css" rel="stylesheet"
			type="text/css" />
	<script type="text/javascript" src="${ctx}/js/echarts.js"></script>
	<style>
			.data{
				width: 23%;
				margin-left: 2%;
				margin-top: 20px;
				margin-bottom: 20px;
				float: left;
			}
		</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置： 统计 - 分红趋势</div>
			
		</div>
		
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF">最近30天分红趋势</font>
		   	   </td>
		   </tr>
		</table>
		
		<div id="main" style="width: 100%;height: 500px">
		
		</div>
		
		
		<c:forEach items="${memberMoney }" var="m1">
			<input type="hidden" name="memberMoney" value="${m1 }"/>
		</c:forEach>
		
		<c:forEach items="${merchantsMoney }" var="m2">
			<input type="hidden" name="merchantsMoney" value="${m2 }"/>
		</c:forEach>
		
		<c:forEach items="${memberRealMoney }" var="m3">
			<input type="hidden" name="memberRealMoney" value="${m3 }"/>
		</c:forEach>
		
		<c:forEach items="${merchantsRealMoney }" var="m4">
			<input type="hidden" name="merchantsRealMoney" value="${m4 }"/>
		</c:forEach>
		
		<c:forEach items="${date }" var="m5">
			<input type="hidden" name="date" value="${m5 }"/>
		</c:forEach>
		
		
		<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
		
        /* var myDate=new Date();
		//设置日期，当前日期的前七天
		var myDate = new Date(); //获取今天日期
		myDate.setDate(myDate.getDate() - 30);
		var dateArray = []; 
		var dateTemp; 
		var flag = 1; 
		for (var i = 0; i < 30; i++) {
		    dateTemp =myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		    dateArray.push(dateTemp);
		    myDate.setDate(myDate.getDate() + flag);
		} */
		
		var memberMoney = document.getElementsByName("memberMoney");
		var merchantsMoney = document.getElementsByName("merchantsMoney");
		var memberRealMoney = document.getElementsByName("memberRealMoney");
		var merchantsRealMoney = document.getElementsByName("merchantsRealMoney");
		var date = document.getElementsByName("date");
		var memberMoneyArray = [];
		var merchantsMoneyArray = []; 
		var memberRealMoneyArray = [];
		var merchantsRealMoneyArray = []; 
		var dateArray = [];
		for(var i=0;i<memberMoney.length;i++)
        {
			memberMoneyArray.push(memberMoney[i].value);
        }
		for(var i=0;i<merchantsMoney.length;i++)
        {
			merchantsMoneyArray.push(merchantsMoney[i].value);
        }
		for(var i=0;i<memberRealMoney.length;i++)
        {
			memberRealMoneyArray.push(memberRealMoney[i].value);
        }
		for(var i=0;i<merchantsRealMoney.length;i++)
        {
			merchantsRealMoneyArray.push(merchantsRealMoney[i].value);
        }
		for(var i=0;i<date.length;i++)
        {
			dateArray.push(date[i].value);
        }
		
		
		var option = {
			    title: {
			        text: '分红趋势'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['会员分红统计金额','会员分红实际发放金额','商家分红统计金额','商家分红实际发放金额']
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
			        data: dateArray
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'会员分红统计金额',
			            type:'line',
			            stack: '总量',
			            data:memberMoneyArray
			        },
			        {
			            name:'会员分红实际发放金额',
			            type:'line',
			            stack: '总量',
			            data:memberRealMoneyArray
			        },
			        {
			            name:'商家分红统计金额',
			            type:'line',
			            stack: '总量',
			            data:merchantsMoneyArray
			        },
			        {
			            name:'商家分红实际发放金额',
			            type:'line',
			            stack: '总量',
			            data:merchantsRealMoneyArray
			        }
			    ]
			};

		// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
		</script>
	</body>
</html>

