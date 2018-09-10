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
				width: 45%;
				margin-left: 2%;
				margin-top: 20px;
				margin-bottom: 20px;
				float: left;
			}
		</style>

		<script>
			$(document).ready(function(){
				$("#diviCount").click(function(){
					$.ajax({
	      				type: "post",
	      				url: 'benefitInfoAction!diviCount.action',
	      				dataType: "text",
	      				success:function(data){
	      					var jsondata = eval("("+data+")");  
		        			alert(jsondata.msg);
	      				},
	      				error:function(){
	      					alert("出错啦！");
	      				}
					});
				});
				
				$("#diviGrant").click(function(){
					$.ajax({
	      				type: "post",
	      				url: 'benefitInfoAction!diviGrant.action',
	      				dataType: "text",
	      				success:function(data){
	      					var jsondata = eval("("+data+")");  
		        			alert(jsondata.msg);
	      				},
	      				error:function(){
	      					alert("出错啦！");
	      				}
					});
				});
				
				$("#agentCount").click(function(){
					$.ajax({
	      				type: "post",
	      				url: 'benefitInfoAction!agentCount.action',
	      				dataType: "text",
	      				success:function(data){
	      					var jsondata = eval("("+data+")");  
		        			alert(jsondata.msg);
	      				},
	      				error:function(){
	      					alert("出错啦！");
	      				}
					});
				});
				
				$("#agentGrant").click(function(){
					window.location.href = "memberInfoAction!findAllAgents.action";
				});
			})
			
			
			
			function actCount(type){
				$.ajax({
      				type: "post",
      				url: 'benefitInfoAction!updateTestAct.action',
      				dataType: "text",
      				data:{
      					"type":type
      				},
      				success:function(data){
      					var jsondata = eval("("+data+")");  
	        			alert(jsondata.msg);
      				},
      				error:function(){
      					alert("出错啦！");
      				}
				});
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 设置- 测试控制面板
			</div>
			<div class="clear"></div>
		</div>					
		
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF">分红池</font>
		   	   </td>
		   </tr>
		</table>
		<div class="data" id="diviCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;">
			<font color="#FFFFFF">统计</font>
		</div>
		<div class="data" id="diviGrant" style="height: 150px;background-color:#32CD32;text-align:center;line-height:150px;cursor:pointer;">
			<font color="#FFFFFF">手动发放</font>	
		</div>
		
		
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF">代理</font>
		   	   </td>
		   </tr>
		</table>
		<div class="data" id="agentCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;">
			<font color="#FFFFFF">统计
			</font>
		</div>
		<div class="data" id="agentGrant" style="height: 150px;background-color:#32CD32;text-align:center;line-height:150px;cursor:pointer;">
			<font color="#FFFFFF">手动发放</font>
		</div>
		
		
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF">统计</font>
		   	   </td>
		   </tr>
		</table>
		<div class="data" id="actCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(1)">
			<font color="#FFFFFF">统计总报表</font>
		</div>
		<div class="data" id="actCount" style="height: 150px;background-color:#32CD32;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(2)">
			<font color="#FFFFFF">统计让利日报表</font>
		</div>
		<div class="data" id="actCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(3)">
			<font color="#FFFFFF">统计福利产出日报表</font>
		</div>
		<div class="data" id="actCount" style="height: 150px;background-color:#32CD32;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(4)">
			<font color="#FFFFFF">统计福利派发报表</font>
		</div>
		<div class="data" id="actCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(5)">
			<font color="#FFFFFF">统计提现日报表</font>
		</div>
		<div class="data" id="actCount" style="height: 150px;background-color:#EE9A00;text-align:center;line-height:150px;cursor:pointer;" onclick="actCount(6)">
			<font color="#FFFFFF">统计资金池报表</font>
		</div>
	</body>
</html>