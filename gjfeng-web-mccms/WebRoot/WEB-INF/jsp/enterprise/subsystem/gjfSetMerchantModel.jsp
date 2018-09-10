<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
		<script src="${ctx}/js/jquery-form.js" type="text/javascript"></script>
		<script>
			$(document).ready(function(){
				$("input[name='type']").click(function(){ 
					$("input[name='type']").each(function(i){ 
						if(this.checked){ 
							var value = $("input[name='type']")[i].value; //获得单选框的值
							$("#pri").empty();
							$("#city").empty();
							$("#area").empty();
							if(value==4){
								$("#Adderss").hide(); 
							}else if(value == 5){
								$("#pri").append('<option value="">--省份--</option>');
								$("#city").append('<option value="">--城市--</option>');
								$("#area").append('<option value="">--地区--</option>');
								$("#area").show();
								$.ajax({
									url:"addressInfoAction!findAllAddress.action",
									data: {
										    fatherId:"",
					    			   	    addressType:1
					    			   	   },
									type:"post",
									dataType:"json",
									success:function(data){
										var a = "";
										for(var i=0;i<data.length;i++){
											$("#pri").append("<option value='"+data[i]["provinceId"]+"'"+a+">"+data[i]["province"]+"</option>");
									 }
									},
									error:function(){
										console.log("省份获取失败");
									}
								});
								$("#Adderss").show(); 
							}
							else if(value == 6){
								$("#area").empty();
								$("#pri").append('<option value="">--省份--</option>');
								$("#city").append('<option value="">--城市--</option>');
								$("#area").hide();
								$.ajax({
									url:"addressInfoAction!findAllAddress.action",
									data: {
										    fatherId:"",
					    			   	    addressType:1
					    			   	   },
									type:"post",
									dataType:"json",
									success:function(data){
										var a = "";
										for(var i=0;i<data.length;i++){
											$("#pri").append("<option value='"+data[i]["provinceId"]+"'"+a+">"+data[i]["province"]+"</option>");
									 }
									},
									error:function(){
										console.log("省份获取失败");
									}
								});
								$("#Adderss").show(); 
							} 
							else if(value == 7){
								$("#area").empty();
								$("#pri").append('<option value="">--省份--</option>');
								$("#city").append('<option value="">--城市--</option>');
								$("#area").hide();
								$.ajax({
									url:"addressInfoAction!findAllAddress.action",
									data: {
										    fatherId:"",
					    			   	    addressType:1
					    			   	   },
									type:"post",
									dataType:"json",
									success:function(data){
										var a = "";
										for(var i=0;i<data.length;i++){
											$("#pri").append("<option value='"+data[i]["provinceId"]+"'"+a+">"+data[i]["province"]+"</option>");
									 }
									},
									error:function(){
										console.log("省份获取失败");
									}
								});
								$("#Adderss").show(); 
							} 
						}
					}); 
				}); 
				
	  
			});
			function priChange(){
			   	jQuery("#city").empty();
			   	$("#city").append('<option value="">--城市--</option>');
			   	if($("#pri").val()=="nc"){
					return false;
				}
				var pid=jQuery("#pri").val();
	        	$.ajax({
					url:"addressInfoAction!findAllAddress.action",
					data:
						{ 	
							fatherId:pid,
    			   	    	addressType:2
    			   	    },
					type:"post",
					dataType:"json",
					success:function(data){
						for(var i=0;i<data.length;i++){
					 		$("#city").append("<option value=\'"+data[i]["cityId"]+"\'>"+data[i]["city"]+"</option>");
					    }
					},
					error:function(){
						console.log("城市获取失败");
					}
				});
	       	}
			
			function cityChange(){
			   	jQuery("#area").empty();
			   	$("#area").append('<option value="">--地区--</option>');
			   	if($("#city").val()=="nc"){
					return false;
				}
			   	var cid=jQuery("#city").val();
	        	$.ajax({
					url:"addressInfoAction!findAllAddress.action",
					data:{
						fatherId:cid,
			   	    	addressType:3
						},
					type:"post",
					dataType:"json",
					success:function(data){
						for(var i=0;i<data.length;i++){
					 		$("#area").append("<option value=\'"+data[i]["areaId"]+"\'>"+data[i]["area"]+"</option>");
					 }
					},
					error:function(){
						console.log("区域获取失败");
					}
				});
	       	}
			
			function check(){
				$("input[name='type']").each(function(i){ 
					if(this.checked){ 
						var value = $("input[name='type']")[i].value; //获得单选框的值
						if(value == '2'){
							var pri = $("#pri").val();
							if(pri == null || pri == ''){
								alert("请选择省份");
								return false;
							}
							
							var city = $("#city").val();
							if(city == null || city == ''){
								alert("请选择城市");
								return false;
							}	
								
							var area = $("#area").val();
							if(area == null || area == ''){
								alert("请选择地区");
								return false;
							}
						}
						if(value == '3'){
							var pri = $("#pri").val();
							if(pri == null || pri == ''){
								alert("请选择省份");
								return false;
							}
							
							var city = $("#city").val();
							if(city == null || city == ''){
								alert("请选择城市");
								return false;
							}	
						}
						var start = $("#startTime").val(); 
						if(start == '' || start == null){
							alert("请选择开始日期");
							return false;
						}
						
						var end = $("#endTime").val();
						if(end == '' || end== null){
							alert("请选择结束日期");
							return false;
						}
						
						$.ajax({
			        		url : 'memberInfoAction!addMerchantModelInBack.action',
			        		type : 'post',
			        		data : $("#agentForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
				        		 if(jsondata.code == '200'){
				        			alert(jsondata.msg);
				        			window.location.href = "memberInfoAction!findAllMerchantInfo.action";
				        		 }else{
				        			 alert(jsondata.msg);
				        		 }
			        		},
			        		error : function(data){
			        			 alert("添加出错!请稍后再试");
			        		}
			        	});		
					}
				});	
				return false;
			}
		</script>
	</head>
	  
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 成为联盟商家</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>
	<form action="memberInfoAction!addMerchantModelInBack.action" method="post" id="agentForm" name="agentForm" onsubmit="return check()">
		<input type="hidden" name="mobile" value="${mobile }" />
		
		<table align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">商家类型</td>
					<td>
						企业代理：<input id="indiAgent" name="type" type="radio" value="4" checked="checked" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						地级市代理：<input id="areaAgent" name="type" type="radio" value="5" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						市级代理：<input id="cityAgent" name="type" type="radio" value="6" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						直辖市级代理：<input id="cityAgent" name="type" type="radio" value="7" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr id="Adderss" style="display: none">
					<td class="pn-flabel" width="100px">省市区</td>
					<td>
						<select name="pri" id="pri" onchange="priChange()">
							<option value="">--省份--</option>
						</select>
						<select name="city" id="city" onchange="cityChange()">
							<option value="">--城市--</option>
						</select>
						<select name="area" id="area">
							<option value="">--地区--</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">代理开始时间</td>
					<td>
						<input id="startTime" name="startTime" readonly="readonly" type="text" onclick="WdatePicker()" style="width: 75px;"/>
					</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">代理结束时间</td>
					<td>
						<input id="endTime" name="endTime" readonly="readonly" type="text" onclick="WdatePicker()" style="width: 75px;"/>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易金额</td>
					<td>
						<input id="agentMoney" name="agentMoney" type="text"  style="width: 75px;"/>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">操作</td>
					<td><input type="submit" id="ok" value="提交" /></td>
				</tr>
		 </table>
		</form>
	</body>
</html>