<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script src="${ctx}/js/jquery-form.js" type="text/javascript"></script>
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
			.item_title {
    			text-align: right;
   				width: 130px;
   				color: #000;
    			background: #e2e8eb;
			}
			.item_input {
   				 text-align: left;
			}
			.button {
			    background: none repeat scroll 0 0 #4e6a81;
			    border-color: #dddddd #000000 #000000 #dddddd;
			    border-style: solid;
			    border-width: 2px;
			    color: #FFFFFF;
			    cursor: pointer;
			    letter-spacing: 0.1em;
			    overflow: visible;
			    padding: 3px 15px;
			    width: auto;
			    cursor: pointer;
			    text-decoration: none;
			}
			.bg-fixed-cover{width: 100%;height: 100%;position: fixed;left: 0;top: 0;z-index: 10000;background-color:rgba(0,0,0,0.4);}
			.cover-content{position: relative;top:50%;left: 50%;width: 60px;height: 60px;margin-left: -30px;margin-top: -30px;background-color: rgba(0,0,0,.5);border-radius: 10px;}
			.cover-cont-img{width: 60%;height: 60%;padding:20%;}
		</style>
		
		<script type="text/javascript">
		$(document).ready(function () {  
			$(".button").click(function(){
				var mobile=$("#mobile").val()
				if(mobile==""){
					alert("电话号码不能为空")
					return false;
				}
				$(".button").attr("disabled", true);
					var r = confirm("确定该操作吗？");
					if(r == true){
						$.ajax({
			        		url : 'benefitInfoAction!updateBenefit.action',
			        		type : 'post',
			        		dataType : "text",
			        		data:{
			        			mobile:$("#mobile").val()
			        		},
			        		timeout:1200000,//超时20分钟
			        		 beforeSend: function () {
				        		$(".bg-fixed-cover").show();
			        		 },
			     			success : function(data){
			     				$(".bg-fixed-cover").hide();
			        			$(".button").attr("disabled", false);
		        				var jsondata = eval("("+data+")");  
		        				alert(jsondata.msg);
		        				window.location.reload();
			        		},
			        		error : function(data){
			        			$(".bg-fixed-cover").hide();
			        			$(".button").attr("disabled", false);
		                        alert("出错啦!请稍后再试");
			        		}
			        	});
					}else{
						$(".button").attr("disabled", false);
					}
				
				return false;
			});
		});
		</script>
		
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 设置 - 分红金额设置
			</div>
			
			<div class="clear"></div>
		</div>
		
	
	
		<table class="listTable3">
		   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px">
		   	   <td colspan="2" style="cl">
		   	   		<font color="#FFFFFF">编辑</font>
		   	   </td>
		   </tr>
		</table>
		<form action="benefitInfoAction!updateBenefit.action" method="post" name="diviForm" id="diviForm">
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td class="item_title">今天实时商户池:</td>
				<td class="item_input">
					<input type="text" disabled="true" value="${merchantSysPoolCur }"/>
				</td>
				<td class="item_title">今天实时会员池:</td>
				<td class="item_input">
					<input type="text" disabled="true" value="${memberSysPoolCur }"/>
				</td>
				<td class="item_title">实时商户分红总数:</td>
				<td class="item_input">
					<input type="text" disabled="true" name="sh_money" value="${merchantDiviOnTime }"/>
				</td>
				<td class="item_title">实时会员分红总数:</td>
				<td class="item_input">
					<input type="text" disabled="true" name="hy_money" value="${memberDiviOntime }"/>
				</td>
			</tr>
			
			<tr id="nc" class="headbg">
				<td class="item_title">实时商户保护线:</td>
				<td class="item_input">
					<input type="text" disabled="true" id="sh_baohu" value="${merchantSysRatio }"/>
				</td>
				<td class="item_title">实时会员保护线:</td>
				<td class="item_input">
					<input type="text" disabled="true" id="hy_baohu" value="${memberSysRatio }"/>
				</td>
				<td class="item_title">商户分红权单价:</td>
				<td class="item_input">
					<input type="text" disabled="true" value="${merchantDiviPrice }"/>
				</td>
				<td class="item_title">会员分红权单价:</td>
				<td class="item_input">
					<input type="text" disabled="true" value="${memberDiviPrice }"/>
				</td>
			</tr>
			
			<tr id="nc" class="headbg">
				<td class="item_title">设置明天商户分红单价:</td>
				<td class="item_input">
					<input type="text" disabled="true" name="sh_univalence" id="sh_univalence" value="${merchantUnitPrice }"/>
				</td>
				<td class="item_title">设置明天会员分红单价:</td>
				<td class="item_input">
					<input type="text" disabled="true" name="hy_univalence" id="hy_univalence" value="${memberUnitPrice }"/>
				</td>
							
			</tr>
			<tr>
			    <td class="item_title">请用户手机号码:</td>
			    <td class="item_input">
					<input type="text"  name="mobile" id="mobile"  value=""/>
				</td>
				<td class="item_title">用户姓名:</td>
				<td class="item_input">
					<input type="text" disabled="true" name="memName" id="memName"  />
				</td>
			    <td class="item_input">
					<input type="submit" class="button" value="手动发放"/>
				</td>	
			</tr>
		</table>
</form>
  
  <form action="" method="post" name="setUnitPrice" id="setUnitPrice">
      <table class="listTable3" >
			<tr id="nc" class="headbg">
				<td class="item_title">会员分红单价:</td>
				<td class="item_input">
					<input type="text"  name="gjfBenefitInfos[0].unitPrice" id="unitPrice" value="${gjfBenefitInfos[0].unitPrice}"/>
					<div style="color: red;display: none" id="msg"></div>
					<input type="hidden"  name="gjfBenefitInfos[0].id"  value="${gjfBenefitInfos[0].id}"/>
					<input type="hidden" name="gjfBenefitInfos[0].ratioType" value="${gjfBenefitInfos[0].ratioType}"/>
                    <input type="hidden"  name="gjfBenefitInfos[0].sysRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].sysRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].diviPoolsRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].diviPoolsRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].platformRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].platformRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"   name="gjfBenefitInfos[0].agentRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].agentRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].agentCityRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].agentCityRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"   name="gjfBenefitInfos[0].agentAreaRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].agentAreaRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].agentIndiRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].agentIndiRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].directMembersRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].directMembersRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].directMerchantsRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].directMerchantsRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden" name="gjfBenefitInfos[0].withdrawalRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].withdrawalRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].insuranceRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].insuranceRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].taxRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].taxRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[0].issueRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[0].issueRatio}' pattern='##.#####'></fmt:formatNumber>" />
				</td>
				<td class="item_title">商户分红单价:</td>
				<td class="item_input">
					<input type="text"  name="gjfBenefitInfos[1].unitPrice" id="unitPrice1" value="${gjfBenefitInfos[1].unitPrice}"/>
					<div style="color: red;display: none" id="msg1"></div>
					<input type="hidden"  name="gjfBenefitInfos[1].id"  value="${gjfBenefitInfos[1].id}"/>
					<input type="hidden" name="gjfBenefitInfos[1].ratioType" value="${gjfBenefitInfos[1].ratioType}"/>
                    <input type="hidden"  name="gjfBenefitInfos[1].sysRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].sysRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[1].diviPoolsRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].diviPoolsRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[1].platformRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].platformRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden" name="gjfBenefitInfos[1].withdrawalRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].withdrawalRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[1].insuranceRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].insuranceRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[1].taxRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].taxRatio}' pattern='##.#####'></fmt:formatNumber>" />
					<input type="hidden"  name="gjfBenefitInfos[1].issueRatio" value="<fmt:formatNumber value='${gjfBenefitInfos[1].issueRatio}' pattern='##.#####'></fmt:formatNumber>" />
				</td>	
						
				<td class="item_input">
					<input type="button" onclick="ajaxUpdate()" id="setbe" value="确认"/>
				</td>	
			</tr>	
		</table>
  </form>
   


		<table class="listTable3">
			  <tr>
			  	<td>序号</td>
			  	<td>交易日期</td>
			  	<td>实际发放金额</td>
			  	<td>实际发放分红权 </td>
			  	<td>特殊发放人姓名</td>
			  	<td>特殊发放人电话</td>
			  	<td>交易状态  </td>
			  	<td>交易类型</td>			  	
			  	<td>操作</td>
			  </tr>
			  
			  <c:forEach items="${pager.resultList}" var="bean" varStatus="status">
			  	<tr>
			  		<td>${status.count}</td>
			  		<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
			  		<td>${bean.actualIssueMoney }</td>
			  		<td>${bean.actualIssueDivi }</td>
			  		<td>${bean.specialMemberName }</td>
			  		<td>${bean.specialMemberMobile }</td>
			  		<td>
			  			<c:choose>
			  				<c:when test="${bean.tradeStatus eq 0}">交易失败</c:when>
			  				<c:when test="${bean.tradeStatus eq 1}">交易成功</c:when>			  				
			  			</c:choose>
			  		</td>	  				  	
			  		<td>
			  			<c:choose>
			  				<c:when test="${bean.tradeType eq 0}">会员</c:when>
			  				<c:when test="${bean.tradeType eq 1}">商家</c:when>			  				
			  			</c:choose>
			  		</td>
			  		<td>
			  		   <c:if test="${bean.tradeType eq 0}">
			  		      <a href="${ctx}/subsystem/benefitInfoAction!findSpecialMemTradeHistory.action?type=0&id=${bean.specialMemberId}&addTime=<fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/>">查看交易详情</a>
			  		   </c:if>
			  		   <c:if test="${bean.tradeType eq 1}">
			  		       <a href="${ctx}/subsystem/benefitInfoAction!findSpecialMemTradeHistory.action?type=1&id=${bean.specialMemberId}&addTime=<fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/>">查看交易详情</a>
			  		   </c:if>
			  			
			  		</td>
			  	</tr>
			  </c:forEach>
			  
		</table>
			<c:if test="${not empty pager.resultList}">
					<s:form action="benefitInfoAction!toDividentAmountSetting.action"
							namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
						<!-- 分页 -->
						<%@ include file="../../common/pager.jsp"%>
					</s:form>
			</c:if>
			
			<div class="bg-fixed-cover" style="display:none">
				<div class="cover-content">
					<img src="${ctx}/image/load.gif" class="cover-cont-img">
				</div>
			</div>

	</body>
	<script>
	
	   //获取用户信息
	   $("#mobile").change(function(){
		   $.ajax({
			   url:"${ctx}/subsystem/benefitInfoAction!getMemberInfo.action",
			   method:"get",
			   data:{
				   mobile:$("#mobile").val()
				   },
			   success:function(data){
				   	var json=eval("("+data+")");
                     if(json.code==200){						
                    	$("#memName").val(json.result.name)
					}else{
						$("#memName").val("")
						alert(json.msg);
					}				
			   }
		   })
	   })
	
	   /*  $("#unitPrice").blur(function(){
	    	if( $("#unitPrice").val()>$("#hy_univalence").val()){
	    		$("#msg").html("分红单价不能大于明天分红单价")
	    		$("#msg")[0].style.display = "block"
	    		$("#setbe")
	    	}
	    })
	     $("#unitPrice1").blur(function(){
	    	if( $("#unitPrice1").val()>$("#sh_univalence").val()){
	    		$("#msg1").html("分红单价不能大于明天分红单价")
	    		$("#msg1")[0].style.display = "block"
	    	}
	    }) */
	    
	    $("#unitPrice").focus(function(){	    		
	    	$("#msg")[0].style.display = "none"	    	
	    })
	     $("#unitPrice1").focus(function(){	    		
	    	$("#msg1")[0].style.display = "none"	    	
	    })
		function ajaxUpdate(){
	    	var fal=true;
	    	/* if( $("#unitPrice").val()>$("#hy_univalence").val()){
	    		$("#msg").html("分红单价不能大于明天分红单价")
	    		$("#msg")[0].style.display = "block"
	    		$("#setbe")
	    		fal=false
	    	}
	    	
	    	if( $("#unitPrice1").val()>$("#sh_univalence").val()){
	    		$("#msg1").html("分红单价不能大于明天分红单价")
	    		$("#msg1")[0].style.display = "block"
	    		fal=false
	    	} */
	    	if(fal){
	    		$.ajax({
	 				type: "post",
	 				url: "${ctx}/ajaxBenefitInfoAction!update.action",
	 				dataType: "json",
	 				data: $("#setUnitPrice").serialize(),
	 				success:function(data){
	 					alert(data.msg);
	 					window.location.href="${ctx}/subsystem/benefitInfoAction!toSpecialDividentAmountSetting.action"
	 				},
	 				error:function(){
	 					alert('网络异常');
	 				}
	 			});
	    	}
			
		}
	</script>
</html>