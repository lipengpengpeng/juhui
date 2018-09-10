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
			.mask{margin:0;padding:0;border:none;width:100%;height:100%;background:#333;opacity:0.6;filter:alpha(opacity=60);z-index:9999;position:fixed;top:0;left:0;display:none;}
			#LoginBox{position:absolute;left:460px;top:150px;background:white;width:426px;height:282px;border:3px solid #444;border-radius:7px;z-index:10000;display:none;}
			.row1{background:#f7f7f7;padding:0px 20px;line-height:50px;height:50px;font-weight:bold;color:#666;font-size:20px;}
			.row{height:77px;line-height:77px;padding:0px 30px;font-family:华文楷体;font-size:x-large;}
			.close_btn{font-family:arial;font-size:30px;font-weight:700;color:#999;text-decoration:none;float:right;padding-right:4px;}
			.inputBox{border:1px solid #c3c3c3;padding:1px 3px 6px 3px;border-radius:5px;margin-left:5px;}
			#txtName{height:27px;width:230px;border:none;}
			#tradeMoney{height:27px;width:230px;border:none;}
			#loginbtn{color:White;background:#4490f7;text-decoration:none;padding:10px 95px;margin-left:87px;margin-top:40px;border-radius:5px;opacity:0.8;filter:alpha(opacity=80);    text-align: center;}
			.warning{float:right;color:Red;text-decoration:none;font-size:20px;font-weight:bold;margin-right:20px;display:none;}
		</style>
				
		<script type="text/javascript">
		
		//弹出
		function getThis(account,amount) {
			$("body").append("<div id='mask'></div>");	
			$("#lineOfCradeForm").append("<input type='hidden' name='mobile' value='"+account+"'>");
			$("#lineOfCradeForm").append("<input type='hidden' id='amount' name='amount' value='"+amount+"'>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#LoginBox").fadeIn("slow");
		}
		
		function clearNoNum(obj){  
			  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符   
			  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
			  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
			  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
			  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
			   obj.value= parseFloat(obj.value);  
			  }  
			} 
			$(document).ready(function(){
			//
			//按钮的透明度
			$("#loginbtn").hover(function () {
				$(this).stop().animate({
					opacity: '1'
				}, 600);
			}, function () {
				$(this).stop().animate({
					opacity: '0.8'
				}, 1000);
			});
			//文本框不允许为空---按钮触发
			$("#loginbtn").click(function () {
				
				var tradeMoney = $("#tradeMoney").val();
				var amount = $("#amount").val();
				var temp = amount - tradeMoney;
				if(temp<=0){
					alert("超出可结算金额");
					return false;
				}
				var tradeMoney = $("#tradeMoney").val();
				if (tradeMoney == "" || tradeMoney == undefined || tradeMoney == null) {
					$("#warn2").css({ display: 'block' });
				}else{
					$("#mask").css({ display: 'none' });
	    			$("#tradeMoney").empty();
	    			$("#LoginBox").fadeOut("fast");
					$.ajax({
		        		url : 'memberInfoAction!modifyMemberOpcenter.action',
		        		type : 'post',
		        		data : $("#lineOfCradeForm").serialize(),
		        		dataType : "text",
		        		success : function(data){
		        			$("#lineOfCradeForm input[type='hidden']").remove();
		        			var jsondata = eval("("+data+")");  
		        			alert(jsondata.msg);
		        			window.location.reload();
		        		},
		        		error : function(data){
		        			 $("#lineOfCradeForm input[type='hidden']").remove();
		        			 alert("操作出错!请稍后再试");
		        		}
		        	});
				}
			});
			$("#tradeMoney").focus(function () {
				$("#warn2").css({ display: 'none' });
			});
			//关闭
			$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).click(function () {
				$("#LoginBox").fadeOut("fast");
				$("#lineOfCradeForm input[type='hidden']").remove();
				$("#mask").css({ display: 'none' });
			});
		});
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 代理商列表
			</div>
			<div class="clear"></div>
			
		</div>	
				
		<form action="memberInfoAction!findAllAgents.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						代理商名称：<input type="text" placeholder="请输入名称" name="name" value="${name }"/>&nbsp;&nbsp;						
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>		
					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名称</td>
				<td>电话号码</td>
				<td>总金额</td>
				<td>待结算金额</td>
				<td>是否启用</td>
				<td>操作</td>
			</tr>
			
			 
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.mobile }</td>
					<td>${bean.opcenterTotalMoney }</td>							
					<td>${bean.opcenterMoney }</td>					
					<td>
						<c:if test="${bean.status eq '0'}">已停用</c:if>
						<c:if test="${bean.status eq '1'}">已启用</c:if>
					</td>
					<td>
					
					  
					
						<a href="memberInfoAction!views.action?id=${bean.id }&token=${bean.token}">查看信息</a>&nbsp;&nbsp;
					<security:authorize ifAnyGranted="MEMBER_AGENT_LIST_SETTLE">	
						<a onclick="getThis(${bean.mobile},${bean.opcenterMoney})">结算金额</a>&nbsp;&nbsp;
					 </security:authorize>  	
					 <a href="memberInfoAction!findMemberOpcenterHistory.action?id=${bean.id }">查看历史记录</a>&nbsp;&nbsp;															 
					</td>
				</tr>
	</c:forEach>
		</table>
		
		<form name="lineOfCradeForm" id="lineOfCradeForm">	
		<div id="LoginBox">
	        <div class="row1">
	               运营中心结算：<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
	        </div>
	        <!-- <div class="row">
			             类&nbsp;&nbsp;&nbsp;&nbsp;型： 
			            消费：<input type="radio" id="type" name="type" value="0"/>
			            充值：<input type="radio" id="type" name="type" value="1" />
	        </div> -->
	        <div class="row">
	           		 金&nbsp;&nbsp;&nbsp;&nbsp;额：&nbsp;&nbsp;<input type="text" id="tradeMoney" name="agentMoney" style="border: 1px solid #e5e5e5;width: 200px;" placeholder="仅数字，小数点后最多两位" onkeyup="clearNoNum(this)"/>
	           		<a href="javascript:void(0)" title="提示" style="color: red" class="warning" id="warn2">*</a>
	        </div>
	        <div class="row" >
	            <a href="javascript:void(0)" id="loginbtn" onclick="">提交</a>
	        </div>
  	  </div>
    </form>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findMemberOpcenter.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="name" value="${name }"/>
					
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>