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
		$(function(){	
			$("#clear").click(function(){
	       		jQuery("#pri").empty();
	       		jQuery("#city").empty();
	       		jQuery("#area").empty();
	       		$("#priValue").empty();
				$("#cityValue").empty();
				$("#areaValue").empty();
				$("#startTime").val("");
				$("#endTime").val("");
				$("input[type='hidden']").each(function(){
					$(this).val("");
				});
				
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
						var a = $("#Pri").val();
						for(var i=0;i<data.length;i++){
							if(a == data[i]["provinceId"]){
								
							}else{
								$("#pri").append("<option value='"+data[i]["provinceId"]+"'>"+data[i]["province"]+"</option>"); 
							}
					 }
					},
					error:function(){
						console.log("省份获取失败");
					}
				});
	       	});
			
			$.ajax({
				url:"addressInfoAction!findAllAddress.action",
				data: {
					    fatherId:"",
    			   	    addressType:1
    			   	   },
				type:"post",
				dataType:"json",
				success:function(data){
					var a = $("#Pri").val();
					for(var i=0;i<data.length;i++){
						if(a == data[i]["provinceId"]){
							
						}else{
							$("#pri").append("<option value='"+data[i]["provinceId"]+"'>"+data[i]["province"]+"</option>"); 
						}
				 }
				},
				error:function(){
					console.log("省份获取失败");
				}
			});
		
			var p = $("#Pri").val();
			var c = $("#City").val();
			var e = $("#Area").val();
			
			if(p != "" && p != null){
				$.ajax({
					url:"addressInfoAction!findAllAddress.action",
					data:
						{ 	
							fatherId:p,
    			   	    	addressType:2
    			   	    },
					type:"post",
					dataType:"json",
					success:function(data){
						for(var i=0;i<data.length;i++){
							if(c == data[i]["cityId"]){
								
							}else{
						 		$("#city").append("<option value=\'"+data[i]["cityId"]+"\'>"+data[i]["city"]+"</option>");
							}
					    }
					},
					error:function(){
						console.log("城市获取失败");
					}
				});
			}
			
			
			if(c != '' && c != null){
				$.ajax({
					url:"addressInfoAction!findAllAddress.action",
					data:{
							fatherId:c,
    			   	    	addressType:3
						},
					type:"post",
					dataType:"json",
					success:function(data){
						for(var i=0;i<data.length;i++){
							if(e == data[i]["areaId"]){
								
							}else{
					 			$("#area").append("<option value=\'"+data[i]["areaId"]+"\'>"+data[i]["area"]+"</option>");
							}
					 }
					},
					error:function(){
						console.log("区域获取失败");
					}
				});
			}
			
			$(".default_button").click(function(){
				var pri = $('#pri option:selected').text();
				var city = $('#city option:selected').text();
				var area = $('#area option:selected').text();
				
				$("#priValue").val(pri);
				$("#cityValue").val(city);
				$("#areaValue").val(area);
			});
		});
		
				function priChange(){
				   	jQuery("#city").empty();
				   	jQuery("#area").empty();
				   	$("#city").append('<option value="">--城市--</option>');
				   	$("#area").append('<option value="">--地区--</option>');
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
		       	
		       
	  
		</script>
		</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 区域业绩
			</div>
			<div class="ropt">
				
			</div>
			<div class="clear"></div>
		</div>
		<form action="countInfoAction!countPerformanceByCondition.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						地区：
						<select name="pri" id="pri" onchange="priChange()">
							<c:if test="${not empty priValue }">
								<option value="${pri }">${priValue }</option>
							</c:if>
							<c:if test="${ empty priValue }">
								<option value="">--省份--</option>
							</c:if>
						</select>&nbsp;&nbsp;
						<select name="city" id="city" onchange="cityChange()">
							<c:if test="${not empty cityValue }">
								<option value="${city }">${cityValue }</option>
							</c:if>
							<c:if test="${ empty cityValue }">
								<option value="">--城市--</option>
							</c:if>
						</select>&nbsp;&nbsp;
						<select name="area" id="area" >
							<c:if test="${not empty areaValue }">
								<option value="${area }">${areaValue }</option>
							</c:if>
							<c:if test="${ empty areaValue }">
								<option value="">--地区--</option>
							</c:if>
						</select>
						&nbsp;&nbsp;
						开始时间：<input type="text" readonly="readonly" placeholder="请选择日期" value="${startTime }" name="startTime" id="startTime" onclick="WdatePicker()" style="width: 75px;"/>
						&nbsp;&nbsp; -&nbsp;&nbsp;
					  	 结束时间：<input type="text" readonly="readonly" placeholder="请选择日期" value="${endTime }" name="endTime" id="endTime" onclick="WdatePicker()" style="width: 75px;"/>
						 <input type="button" style="margin-left:30px;" class="default_button" id="clear" value="清除"/>&nbsp;&nbsp;
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
			<input type="hidden" name="priValue" id="priValue"></input>
			<input type="hidden" name="cityValue" id="cityValue"></input>
			<input type="hidden" name="areaValue" id="areaValue"></input>
		</form>
		<div>
			<table class="listTable3">
				<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="4" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">当前条件业绩：${performance }</font>
		 	  	   </td>
			   </tr>
			</table>
		</div>
		<input type="hidden"  id="Pri" value="${pri }"></input>
		<input type="hidden"  id="City" value="${city }"></input>
		<input type="hidden"  id="Area" value="${area }"></input>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>买家</td>
				<td>店铺</td>
				<td>店铺位置</td>
				<td>金额</td>
				<td>时间</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.storeName }分</td>
					<td>${bean.province }${bean.city }${bean.area }</td>
					<td>${bean.tradeMoney }</td>
					<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty pager.resultList }">
			<s:form action="countInfoAction!countPerformanceByCondition.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="pri" id="pri0"  value="${pri }"></input>
					<input type="hidden" name="city" id="city0"  value="${city }"></input>
					<input type="hidden" name="area" id="area0"  value="${area }"></input>
					<input type="hidden" name="priValue" id="priValue0"  value="${priValue }"></input>
					<input type="hidden" name="cityValue" id="cityValue0"   value="${cityValue }"></input>
					<input type="hidden" name="areaValue"  id="areaValue0"  value="${areaValue }"></input>
					<input type="hidden" value="${startTime }" name="startTime" />
					<input type="hidden" value="${endTime }" name="endTime"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>

	</body>
</html>