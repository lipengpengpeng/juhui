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
				var a = $("#area").val();
				if(null == a || a==''){
					$("#area").hide();
				}
				$("input[name='type']").click(function(){ 
					$("input[name='type']").each(function(i){ 
						if(this.checked){ 
							var value = $("input[name='type']")[i].value; //获得单选框的值
							$("#pri").empty();
							$("#city").empty();
							$("#area").empty();
							if(value==1){
								$("#Adderss").hide(); 
							}else if(value == 2){
								$("#pri").append('<option value="">--省份--</option>');
								$("#city").append('<option value="">--城市--</option>');
								$("#area").append('<option value="">--地区--</option>');
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
								$("#area").show();
							}
							else if(value == 3){
								$("#area").hide();
								$("#pri").append('<option value="">--省份--</option>');
								$("#city").append('<option value="">--城市--</option>');
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
						}else if(value == '3'){
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
			        		url : 'memberInfoAction!updateToAgent.action',
			        		type : 'post',
			        		data : $("#agentForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
				        		 if(jsondata.code == '200'){
				        			alert(jsondata.msg);
				        			window.location.href = "memberInfoAction!findAllMember.action";
				        		 }else{
				        			 alert(jsondata.msg);
				        		 }
			        		},
			        		error : function(data){
			        			 alert("修改出错!请稍后再试");
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
			<div class="rpos">当前位置: 成为代理 </div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>
	<form action="memberInfoAction!updateToAgent.action" method="post" id="agentForm" name="agentForm" onsubmit="return check()">
		<input type="hidden" name="unionId" value="${unionId }" />
		<input type="hidden" name="token" value="${token }" />
		<table align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">代理类型</td>
					<td>
						个代：<input id="indiAgent" name="type" type="radio" value="1" <c:if test="${member.identity eq '1'}">checked="checked"</c:if> />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						区代：<input id="areaAgent" name="type" type="radio" value="2" <c:if test="${member.identity eq '2'}">checked="checked"</c:if>/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						市代：<input id="cityAgent" name="type" type="radio" value="3" <c:if test="${member.identity eq '3'}">checked="checked"</c:if>/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<c:if test="${member.identity eq '2' || member.identity eq '3'}">
					<tr id="Adderss">
						<td class="pn-flabel" width="100px">省市区</td>
						<td>
							<select name="pri" id="pri" onchange="priChange()">
								<option value="${proviceId.provinceId }">${proviceId.province }</option>
								<option value="">--省份--</option>
							</select>
							<select name="city" id="city" onchange="cityChange()">
								<option value="${cityId.cityId }">${cityId.city }</option>
								<option value="">--城市--</option>
							</select>
							<select name="area" id="area">
								<option value="${areaId.areaId }">${areaId.area }</option>
								<option value="">--地区--</option>
							</select>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${member.identity eq '1'}">
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
				</c:if>
				
				<tr>
					<td class="pn-flabel" width="100px">代理开始时间</td>
					<td>
					
						<input id="startTime" name="startTime" value="<fmt:formatDate value="${member.agentStartDate}" pattern="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="WdatePicker()" style="width: 75px;"/>
					</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">代理结束时间</td>
					<td>
						<input id="endTime" name="endTime"  value="<fmt:formatDate value="${member.agentEndDate}" pattern="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="WdatePicker()" style="width: 75px;"/>
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