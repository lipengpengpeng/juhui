<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>MCCMS</title>
<script>
        //根据年份更改比例
		var a1=0;
		var r1=100;
		var a2=15;
		var r2=150;
		var a3=30;
		var r3=180;
		var a5=50;
		var r5=200;
        function yearChange(){
          if($("#year").val()==1){
           $("#agentAmountRatio").val(a1);
           $("#returnAmountRatio").val(r1);
            a1=$("#agentAmountRatio").val();
            r1=$("#returnAmountRatio").val();
          }
           if($("#year").val()==2){
           $("#agentAmountRatio").val(a2);
           $("#returnAmountRatio").val(r2);
            a2=$("#agentAmountRatio").val();
            r2=$("#returnAmountRatio").val();
          }
           if($("#year").val()==3){
           $("#agentAmountRatio").val(a3);
           $("#returnAmountRatio").val(r3);
            a3=$("#agentAmountRatio").val();
            r3=$("#returnAmountRatio").val();
          }
           if($("#year").val()==5){
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
           if($("#year").val()==2){
            a2=temp;
          }
           if($("#year").val()==3){
            a3=temp;
          }
           if($("#year").val()==5){
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
           if($("#year").val()==2){
            r2=temp;
          }
           if($("#year").val()==3){
            r3=temp;
          }
           if($("#year").val()==5){
            r5=temp;
          }
        }
        //积分数根据珍珠数变化
        function checkAmount(){
           $("#jifen").html('');
           var jifen=$("#agentAmount").val();
           var regExp = /^(?:0|[1-9]\d*)(\.\d{1,2})?$/;
	      if(regExp.test(jifen))
              $("#jifen").html((jifen*10).toFixed(2));
        }
$(document).ready(function(){
         jQuery.validator.addMethod("priCheck", function(value, element) {       
         return this.optional(element) || /^(?:0|[1-9]\d*)(\.\d{1,2})?$/.test(value);       
    }, "请输入正确金额"); 
     jQuery.validator.addMethod("ratioCheck", function(value, element) {       
         return this.optional(element) || /^(?:0|[1-9]\d*)(\.\d{1,2})?$/.test(value);       
    }, "请输入正确比例"); 

        $("#region").load("${ctx}/areaTest.htm?agentArea=true");
        $("#pri").focus(function(){
           $("#agentArea").hide();
        });
        //全选
        $("#allpowerType").click(function(){
		       $('input[name="powerType"]').attr("checked",this.checked); 
		  });
		  $('input[name="powerType"]').click(function(){
		      var code_Values = document.getElementsByName("powerType");  
			  for (i = 0; i < code_Values.length; i++) {  
			  if (code_Values[i].type == "checkbox") {  
			     $("#allpowerType").attr("checked",false);
			  }  
			}  
		  });
        
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
				agentAmountRatio :{
				    required: true, 
				    ratioCheck :true
				},
				targetAmount :{
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
				agentAmountRatio  :{
				    required: '请输入授权金额递增比例'
				},
				targetAmount :{
				     required: '请输入金额' 
				},
			},
			errorPlacement: 
				     function(error, element) { error.appendTo( element.next("span") );}, 
		submitHandler:function() {
			  if($("#pri").val()=='nc'){
			    $("#agentArea").show();
			    return false;
			  }
			  if(!$('input[id="payType"]').is(':checked')){
			    $("#payTypeErro").show();
			    return false;
			  }
			  if(!$('input[id="powerType"]').is(':checked')){
			    $("#powerTypeErro").show();
			    return false;
			  }
			   $("#payTypeErro").hide();
			   $("#powerTypeErro").hide();
			   $("#agentArea").hide();
				 var pri = $("#pri").val();
				 var city = $("#city").val();
            	 var area = $("#area").val();
            	 var agentAmount = $("#agentAmount").val();
            	 var returnAmountRatio = $("#returnAmountRatio").val();
            	 var payType = "";
            	 var powerType = "";
            	 var targetAmount = $("#targetAmount").val();
            	 //获取支付方式
            	 obj = document.getElementsByName("payType");
			     for(k in obj){
			        if(obj[k].checked)
			           payType+=obj[k].value+",";
			     }
			     var lastIndex = payType.lastIndexOf(',');
				if (lastIndex > -1) {
					payType = payType.substring(0, lastIndex) + payType.substring(lastIndex + 1, payType.length);
                }
                 //获取授权单位
            	 obj2 = document.getElementsByName("powerType");
			     for(k in obj2){
			        if(obj2[k].checked)
			           powerType+=obj2[k].value+",";
			     }
			     var lastIndex2 = powerType.lastIndexOf(',');
				if (lastIndex2 > -1) {
					powerType = powerType.substring(0, lastIndex2) + powerType.substring(lastIndex2 + 1, powerType.length);
                }
                //获取区域
                 var areaInfo = $("#pri").find("option:selected").text()+"  ";
                 if($("#city").val()!='nc'){
                   areaInfo+=$("#city").find("option:selected").text()+"  ";
			    }
			     if($("#area").val()!='nc'){
			        if($("#area").val()!='all')
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
							$(".payType").attr("value",payType);
							$(".powerType").attr("value",powerType);
							$(".targetAmount").attr("value",targetAmount);
							  	if(city!='nc'){
				                   $(".cityid").attr("value",city);
							    }
							     if(area!='nc'){
							       if($("#area").val()!='all')
							         $(".areaid").attr("value",area);
							    }
							 var areainfos = "";
							 var areaids = "";
							if($("#area").val()=='all'){	    
								$("#area").find("option").each(function(){
								     if($(this).val()!='nc' && $(this).val()!='all'){
									     areaids+=$(this).val()+",";
									     areainfos+=$(this).text()+"  ,";
									  }   
								});
								$(".areainfos").attr("value",areainfos);
								$(".areaids").attr("value",areaids);
							}
							 //获取各年比例
                            var amountRatio = "1:"+r1+","+a1+";"+"2:"+r2+","+a2+";"+"3:"+r3+","+a3+";"+"5:"+r5+","+a5;	
                            $(".amountRatio").attr("value",amountRatio);
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
	} -->
	.ratio{
			
				width: 55px;
			}

 </style>
	</head>
	<body>
		<div class="rhead">
		<div class="rpos">
			当前位置: 系统配置 - 代理区域配置 - 添加代理区域
		</div>
		<div class="ropt"><input type="button" class="defaultButton" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
		<div class="clear"></div>
		</div>
		<form action="sysAgentConfigAction!newSysAgentConfig.action" method="post" id="sysAgentConfigForm"
		 name="sysAgentConfigForm"  enctype="multipart/form-data">
		
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td width="60px"  class="pn-flabel">代理区域:</td><td><div id="region"></div><label id="agentArea" generated="true" class="error" style="display: none;">请选择代理区域</label></td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">支付方式:</td><td>
				 珍珠: <input type="checkbox" name="payType" id="payType" value="0"/>
				 积分: <input type="checkbox" name="payType" id="payType" value="1"/><!--
				 混合 : <input type="checkbox" name="payType" id="payType" value="2"/>
				 --><label id="payTypeErro" generated="true" class="error" style="display: none;">请选择支付方式</label>
				<span></span></td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">定价:</td>
				<td>珍珠：<input style="width:70px;" id="agentAmount" name="agentAmount" onchange="checkAmount()"/>&nbsp;&nbsp;&nbsp;<span></span>
			    <div width="100px"></div>
				积分：<label width="100px" id="jifen"></label>(积分为珍珠的10倍)</td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">收费及返还:</td>
				<td>返还年限：<select id="year" onchange="yearChange()">
				<option value="1">1年</option>
				<option value="2">2年</option>
				<option value="3">3年</option>
				<option value="5">5年</option>
				</select>
				返还递增：<input class="ratio" style="width:70px;" id="returnAmountRatio" name="returnAmountRatio" value="100" onchange="reAmount()"/>&nbsp;&nbsp;%<span><label id="returnAmountRatioErro" generated="true" class="error" style="display: none;">请输入返还比例</label></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				授权金额：<input class="ratio" style="width:70px;" id="agentAmountRatio" name="agentAmountRatio" value="0" onchange="amount()"/>&nbsp;&nbsp;%<span><label id="agentAmountRatioErro" generated="true" class="error" style="display: none;">请输入授权金额递增比例</label></span>
			    </td>
			</tr>
			<tr>
				<td width="80px"  class="pn-flabel">授权购买单位:</td><td>
				 普通会员: <input type="checkbox" name="powerType" id="powerType" value="1"/>
				 创业会员: <input type="checkbox" name="powerType" id="powerType" value="2"/>
				 联盟商家: <input type="checkbox" name="powerType" id="powerType" value="3"/>
				 联盟企业: <input type="checkbox" name="powerType" id="powerType" value="3.5"/>
				 &nbsp;&nbsp;全选: <input type="checkbox" name="allpowerType" id="allpowerType" />
				 <label id="powerTypeErro" generated="true" class="error" style="display: none;">请选择授权单位</label>
				<span></span></td>
			</tr>
			<tr>
				<td width="60px"  class="pn-flabel">定价:</td>
				<td>目标业绩：<input style="width:70px;" id="targetAmount" name="targetAmount"/>&nbsp;元<span></span>
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
			--><tr>
				<td></td>
				<td  align="center" align="center">
				<input type="submit" id="submitButton" class="defaultButton" value='<s:text name="common.word.submit" />' />
			
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	<form action="sysAgentConfigAction!newSysAgentConfig.action" method="post" id="sysAgentConfigForm2">
	<input type="hidden" class="areainfo" name="sysAgentConfig.areainfo"/>
	<input type="hidden" class="areainfos" id="areainfos" name="areainfos"/>
	<input type="hidden" class="areaid" name="sysAgentConfig.areaid"/>
	<input type="hidden" class="areaids" id="areaids" name="areaids"/>
	<input type="hidden" class="cityid" name="sysAgentConfig.cityid"/>
	<input type="hidden" class="provinceid" name="sysAgentConfig.provinceid"/>
	<input type="hidden" class="agentAmount" name="sysAgentConfig.agentAmount"/>
	<input type="hidden" class="amountRatio" name="sysAgentConfig.ratio"/>
	<input type="hidden" class="returnAmountRatio" name="sysAgentConfig.returnAmountRatio"/>
    <input type="hidden" class="payType" name="sysAgentConfig.payType"/>
    <input type="hidden" class="powerType" name="sysAgentConfig.powerType"/>
    <input type="hidden" class="targetAmount" name="sysAgentConfig.targetAmount" />
    <input type="hidden" class="state" name="sysAgentConfig.state" value="1"/>
	</form>
	</body>
</html>

