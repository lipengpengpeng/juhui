<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<script>
        //默认比例  
		var a1=0;
		var r1=100;
		var a2=15;
		var r2=150;
		var a3=30;
		var r3=180;
		var a5=50;
		var r5=200;
		var ratio ='${sysAgentConfig.ratio}';
        var ratios=ratio.split(";");
        for (i = 0; i < ratios.length; i++) {
              var temp = ratios[i].split(":"); 
			  if (temp[0] == "1") {  
			    var tempRatio = temp[1].split(",");
			    r1=tempRatio[0];
		        a1=tempRatio[1];
			  }
			   else if(temp[0] == "2") {  
			    var tempRatio = temp[1].split(",");
			    r2=tempRatio[0];
		        a2=tempRatio[1];
			  }  
			   else if(temp[0] == "3") {  
			    var tempRatio = temp[1].split(",");
			    r3=tempRatio[0];
		        a3=tempRatio[1];
			  }  
			   else if(temp[0] == "5") {  
			    var tempRatio = temp[1].split(",");
			    r5=tempRatio[0];
		        a5=tempRatio[1];
			  }    
			}
		 $("#agentAmountRatio").val(a1);
         $("#returnAmountRatio").val(r1);
		//根据年份更改比例
        function yearChange(){
          if($("#year").val()==1){
           $("#agentAmountRatio").val(a1);
           $("#returnAmountRatio").val(r1);
            a1=$("#agentAmountRatio").val();
            r1=$("#returnAmountRatio").val();
          }
           else if($("#year").val()==2){
           $("#agentAmountRatio").val(a2);
           $("#returnAmountRatio").val(r2);
            a2=$("#agentAmountRatio").val();
            r2=$("#returnAmountRatio").val();
          }
           else if($("#year").val()==3){
           $("#agentAmountRatio").val(a3);
           $("#returnAmountRatio").val(r3);
            a3=$("#agentAmountRatio").val();
            r3=$("#returnAmountRatio").val();
          }
           else if($("#year").val()==5){
           $("#agentAmountRatio").val(a5);
           $("#returnAmountRatio").val(r5);
            a5=$("#agentAmountRatio").val();
            r5=$("#returnAmountRatio").val();
          }
        }
        //存储各年段授权比例
        function amount(){
         $("#agentAmountRatioErro").hide(); 
        var temp=$("#agentAmountRatio").val();
        if(temp==""||temp==null){
            $("#agentAmountRatio").val(null);  
            return false;
        }
           if($("#year").val()==1){
            a1=temp;
          }
           else if($("#year").val()==2){
            a2=temp;
          }
           else if($("#year").val()==3){
            a3=temp;
          }
           else if($("#year").val()==5){
            a5=temp;
          }
        }
        //存储各年段返回比例
        function reAmount(){
         $("#returnAmountRatioErro").hide();  
          var temp=$("#returnAmountRatio").val();
        if(temp==""){
            $("#returnAmountRatio").val(null);
            return false;
        }
           if($("#year").val()==1){
            r1=temp;
          }
           else if($("#year").val()==2){
            r2=temp;
          }
           else if($("#year").val()==3){
            r3=temp;
          }
           else if($("#year").val()==5){
            r5=temp;
          }
        }
$(document).ready(function(){
         jQuery.validator.addMethod("priCheck", function(value, element) {       
         return this.optional(element) || /^(?:0|[1-9]\d*)(\.\d{1,2})?$/.test(value);       
    }, "请输入正确金额"); 
     jQuery.validator.addMethod("ratioCheck", function(value, element) {       
         return this.optional(element) || /^(?:0|[1-9]\d*)(\.\d{1,2})?$/.test(value);       
    }, "请输入正确比例"); 

        $("#region").load("${ctx}/areaTest.htm");
        $("#pri").focus(function(){
           $("#agentArea").hide();
        });
        function checkName(){
        
        
        };
		$("#sysAgentConfigForm").validate({
			 rules: { 
				agentAmount :{
				    required: true, 
				    priCheck :true
				},
				returnAmountRatio :{
				    required: true, 
				    ratioCheck :true
				},
			},
			messages: {
			   agentAmount :{
				    required: '请输入金额'
				},
				returnAmountRatio  :{
				    required: '请输入返还比例'
				},
				
			},
			errorPlacement: 
				     function(error, element) { error.appendTo( element.parent("td").find("span") );}, 
		submitHandler:function() {
			  if($("#pri").val()=='nc'){
			    $("#agentArea").show();
			    return false;
			  }
			   $("#agentArea").hide();
				 var pri = $("#pri").val();
				 var city = $("#city").val();
            	 var area = $("#area").val();
            	 var agentAmount = $("#agentAmount").val();
            	 var returnAmountRatio = $("#returnAmountRatio").val();
                 var areaInfo = $("#pri").find("option:selected").text()+"  ";
                 if($("#city").val()!='nc'){
                   areaInfo+=$("#city").find("option:selected").text()+"  ";
			    }
			     if($("#area").val()!='nc'){
			        areaInfo+=$("#area").find("option:selected").text()+"  ";
			    }
                 debugger;
            	$.ajax({
            		type: "post",
            		url: "sysAgentConfigAction!checkArea.action",
        			dataType: "json",
        			data: {
        				"areaInfo": areaInfo
        			},
                    success:function(result){
                        if(result.msg == 'true') {
							$(".returnAmountRatio").attr("value",returnAmountRatio);
							$(".agentAmount").attr("value",agentAmount);
							$(".provinceid").attr("value",pri);
							$(".areainfo").attr("value",areaInfo);
							  	if(city!='nc'){
				                   $(".cityid").attr("value",city);
							    }
							     if(area!='nc'){
							      $(".areaid").attr("value",area);
							    }
							$("#sysAgentConfigForm2").submit();
                        }else{
							alert("已有该区域！请重新选择");
						}	
							
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                    /* 	alert("系统异常请联系客服"+textStatus,{
								  skin: 'layui-layer-lan'
								  ,closeBtn: 0
								}, function(){
								  window.location.href="${ctx}/member/memberAction!retrieveAllMembers.action";
							});       */     
							alert("系统异常请联系客服"+textStatus);       
                    }
                });
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});		
 </script>
 <style>
 <!--
	#userRole label.error{
	 	position: absolute;
		background-position-y: 6px;
	 	margin-left: 341px;
	}
 -->
 </style>
	</head>
	<body>
		<div class="rhead">
		<div class="rpos">
			当前位置: 系统配置 - 代理区域配置 - 查看代理区域
		</div>
		<div class="ropt"><input type="button" class="defaultButton" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
		<div class="clear"></div>
		</div>
		<form action="sysAgentConfigAction!newSysAgentConfig.action" method="post" id="sysAgentConfigForm"
		 name="sysAgentConfigForm"  enctype="multipart/form-data">
		
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td width="60px"  class="pn-flabel">代理区域:</td><td>${sysAgentConfig.areainfo }</td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">支付方式:</td><td>
				<c:set value="${ fn:split(sysAgentConfig.payType, ',') }" var="str" />
	            <c:set value="${fn:length(str)}" var="str0" />
				 珍珠: <input disabled="disabled" type="checkbox" name="payType" id="payType" <c:forEach var="pay" items="${str}"><c:if test="${pay eq '0'}">checked="checked"</c:if></c:forEach> />
				 积分: <input disabled="disabled" type="checkbox" name="payType" id="payType" <c:forEach var="pay" items="${str}"><c:if test="${pay eq '1'}">checked="checked"</c:if></c:forEach> /><!--
				 混合 : <input disabled="disabled" type="checkbox" name="payType" id="payType" <c:forEach var="pay" items="${str}"><c:if test="${pay eq '2'}">checked="checked"</c:if></c:forEach> />
				--><span></span></td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">定价:</td>
				<td>珍珠：<input disabled="disabled" style="width:70px;" id="agentAmount" name="sysAgentConfig.agentAmount" value="${sysAgentConfig.agentAmount }" onchange="checkAmount()"/>&nbsp;&nbsp;&nbsp;<span></span>
			    <div width="100px"></div>
				积分：<label width="100px" id="jifen"><fmt:formatNumber type="number" value="${sysAgentConfig.agentAmount*10 }" pattern="0.00" maxFractionDigits="2"/></label>(积分为珍珠的10倍)</td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">收费及返还:</td>
				<td>返还年限：<select id="year" onchange="yearChange()">
				<option value="1">1年</option>
				<option value="2">2年</option>
				<option value="3">3年</option>
				<option value="5">5年</option>
				</select>
				返还递增：<input disabled="disabled" class="ratio" style="width:70px;" id="returnAmountRatio" name="returnAmountRatio" value="100" onchange="reAmount()" />&nbsp;&nbsp;%<span><label id="returnAmountRatioErro" generated="true" class="error" style="display: none;">请输入返还比例</label></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				授权金额：<input disabled="disabled" class="ratio" style="width:70px;" id="agentAmountRatio" name="agentAmountRatio" value="0" onchange="amount()"/>&nbsp;&nbsp;%<span><label id="agentAmountRatioErro" generated="true" class="error" style="display: none;">请输入授权金额递增比例</label></span>
			    </td>
			</tr>
			<tr>
				<td width="80px"  class="pn-flabel">授权购买单位:</td><td>
				<input type="hidden" id="power" name="sysAgentConfig.powerType" value="${sysAgentConfig.payType }"/>
				<c:set value="${ fn:split(sysAgentConfig.powerType, ',') }" var="str2" />
				 普通会员: <input disabled="disabled" type="checkbox" name="powerType" id="powerType" value="1" <c:forEach var="power" items="${str2}"><c:if test="${power eq '1'}">checked="checked"</c:if></c:forEach>/>
				 创业会员: <input disabled="disabled" type="checkbox" name="powerType" id="powerType" value="2" <c:forEach var="power" items="${str2}"><c:if test="${power eq '2'}">checked="checked"</c:if></c:forEach>/>
				 联盟商家: <input disabled="disabled" type="checkbox" name="powerType" id="powerType" value="3" <c:forEach var="power" items="${str2}"><c:if test="${power eq '3'}">checked="checked"</c:if></c:forEach>/>
				 联盟企业: <input disabled="disabled" type="checkbox" name="powerType" id="powerType" value="3.5" <c:forEach var="power" items="${str2}"><c:if test="${power eq '3.5'}">checked="checked"</c:if></c:forEach>/>
				 &nbsp;&nbsp;全选: <input type="checkbox" name="allpowerType" id="allpowerType" />
				 <label id="powerTypeErro" generated="true" class="error" style="display: none;">请选择授权单位</label>
				<span></span></td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">定价:</td>
				<td>目标业绩：<input disabled="disabled" style="width:70px;" id="targetAmount" name="sysAgentConfig.targetAmount" value="${sysAgentConfig.targetAmount }"/>&nbsp;元<span></span>
				</td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">状态:</td>
				<td>
				   <c:if test="${sysAgentConfig.state eq 1}">已开启</c:if>
				   <c:if test="${sysAgentConfig.state eq 0}">已关闭</c:if>
				</td>
			</tr>
			<!--<tr>
				<td class="pn-flabel"> 状态 <s:text name="common.word.status" /></td>
				<td>
					<select name="state">
						<option value="1"> 已启用 <s:text name="common.word.has.opened" /></option>
						<option value="0"><font color="red"> 已停用 <s:text name="common.word.has.closed" /></font></option>
					</select>
				</td>
			</tr>
			<tr>
				<!--<td></td>
				<td  align="center" align="center">
				<input type="submit" id="submitButton" class="defaultButton" value='<s:text name="common.word.submit" />' />
			
				</td>
			</tr>-->
	 	</table>
	 	<s:token></s:token>
	</form>
	<form action="sysAgentConfigAction!newSysAgentConfig.action" method="post" id="sysAgentConfigForm2">
	<input type="hidden" class="areainfo" name="sysAgentConfig.areainfo"/>
	<input type="hidden" class="areaid" name="sysAgentConfig.areaid"/>
	<input type="hidden" class="cityid" name="sysAgentConfig.cityid"/>
	<input type="hidden" class="provinceid" name="sysAgentConfig.provinceid"/>
	<input type="hidden" class="agentAmount" name="sysAgentConfig.agentAmount"/>
	<input type="hidden" class="returnAmountRatio" name="sysAgentConfig.returnAmountRatio"/>
	</form>
	</body>
</html>

