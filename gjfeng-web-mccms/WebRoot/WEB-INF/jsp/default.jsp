<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${dictionaryInfoMap["webSite"].title}</title>
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
			<div class="rpos"><!-- 当前位置: 基本配置 - 欢迎信息 --><s:text name="default.current.location" /></div>
			<security:authorize ifAnyGranted="BASE_WELCOME_INSTRUCTION">
				<div class="clear">
					<%-- <a href="${ctx}/manual/InstructionsAdmin.rar"><span style="color: red;float: right;" >
						<!-- 操作说明书 --><s:text name="default.operation.guideline" />
					</span></a> --%>
				</div>
			</security:authorize>
		</div>
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF"> 凤凰云商管理平台  首页</font>
		   	   </td>
		   </tr>
			<c:if test="${isFine==0}">
				<tr>
					<td class="listtd1" colspan="2">
						<center><font color="red">
							<!-- 您还没有完善所有信息,请点击左边的'管理员用户信息'进行编辑完善. -->
							<s:text name="default.message.has.not.completed" />
						</font></center>
					</td>
			   </tr>
		    </c:if>
		</table>
		
		<div class="data" style="height: 150px;background-color:#395898;text-align:center;line-height:150px;">
			<font color="#FFFFFF">当前版本：1.0</font>
		</div>
		<div class="data" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;">
			<font color="#FFFFFF">订单累计成交额 ${orderTotalAmount }</font>
		</div>
		<div class="data" style="height: 150px;background-color:#32CD32;text-align:center;line-height:150px;">
			<font color="#FFFFFF">平台会员总数 ${memberAmount }</font>
		</div>
		<div class="data" style="height: 150px;background-color:#D03A45;text-align:center;line-height:150px;">
			<font color="#FFFFFF">上线的商品数 ${productAmount }</font>
		</div>
		<!-- 
		<div class="data" style="height: 150px;background-color:#7D37BB;text-align:center;line-height:150px;">
			<font color="#FFFFFF">上线的团购数 0</font>
		</div>
		<div class="data" style="height: 150px;background-color:#3AB8C4;text-align:center;line-height:150px;">
			<font color="#FFFFFF">上线的优惠券数 0</font>
		</div>
		 <div class="data" style="height: 150px;background-color:#253B48;text-align:center;line-height:150px;">
			<font color="#FFFFFF">上线的活动数 0</font>
		</div> -->
		<div class="data" style="height: 150px;background-color:#0961B8;text-align:center;line-height:150px;">
			<font color="#FFFFFF">平台共入驻 ${sellerMemberAmount } 家商户
			共计 ${storeAmount }家门店 </font>
		</div>
		
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF"> 最近30天运营数据</font>
		   	   </td>
		   </tr>
		</table>
		
		<div id="main" style="width: 100%;height: 500px">
		
		</div>
		
		
		<c:forEach items="${storeTurnover }" var="s">
			<input type="hidden" name="storeTurnover" value="${s }"/>
		</c:forEach>
		
		<c:forEach items="${O2OTurnover }" var="o">
			<input type="hidden" name="O2OTurnover" value="${o }"/>
		</c:forEach>
		<c:forEach items="${date }" var="m5">
			<input type="hidden" name="date" value="${m5 }"/>
		</c:forEach>
		
		<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
		
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
		
		var store = document.getElementsByName("storeTurnover");
		var O2O = document.getElementsByName("O2OTurnover");
		var date = document.getElementsByName("date");
		var storeArray = [];
		var O2OArray = []; 
		var dateArray = [];
		for(var i=0;i<store.length;i++)
        {
			storeArray.push(store[i].value);
        }
		for(var i=0;i<O2O.length;i++)
        {
			O2OArray.push(O2O[i].value);
        }
		for(var i=0;i<date.length;i++)
        {
			dateArray.push(date[i].value);
        }
		
		var option = {
			    title: {
			        text: '运营数据'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['网上商城交易额','O2O交易额']
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
			            name:'网上商城交易额',
			            type:'line',
			            stack: '总量',
			            data:storeArray
			        },
			        {
			            name:'O2O交易额',
			            type:'line',
			            stack: '总量',
			            data:O2OArray
			        }
			    ]
			};

		// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
		</script>
	</body>
</html>

