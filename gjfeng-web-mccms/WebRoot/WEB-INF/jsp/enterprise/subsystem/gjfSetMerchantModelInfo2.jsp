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
			
			.mask{margin:0;padding:0;border:none;width:100%;height:100%;background:#333;opacity:0.6;filter:alpha(opacity=60);z-index:9999;position:fixed;top:0;left:0;display:none;}
			#LoginBox{position:absolute;left:460px;top:150px;background:white;width:426px;height:282px;border:3px solid #444;border-radius:7px;z-index:10000;display:none;}
			.row1{background:#f7f7f7;padding:0px 20px;line-height:50px;height:50px;font-weight:bold;color:#666;font-size:20px;}
			.row{height:77px;line-height:77px;padding:0px 25px;font-family:华文楷体;font-size:x-large;}
			.close_btn{font-family:arial;font-size:30px;font-weight:700;color:#999;text-decoration:none;float:right;padding-right:4px;}
			.inputBox{border:1px solid #c3c3c3;padding:1px 3px 6px 3px;border-radius:5px;margin-left:5px;}
			#txtName{height:27px;width:230px;border:none;}
			#tradeMoney{height:27px;width:230px;border:none;}
			#loginbtn{color:White;background:#4490f7;text-decoration:none;padding:10px 95px;margin-left:87px;margin-top:40px;border-radius:5px;opacity:0.8;filter:alpha(opacity=80);    text-align: center;}
			.warning{float:right;color:Red;text-decoration:none;font-size:20px;font-weight:bold;margin-right:20px;display:none;}
		</style>
		<script>
			function change(type){
				var s = confirm('警告!请慎重执行该操作');
				if(s){
					
				}
			}
			
			//弹出
			function getThis(account) {
				$("body").append("<div id='mask'></div>");				
				$("#lineOfCradeForm").append("<input type='hidden' name='mobile' value='"+account+"'>");
				
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
					var val=$('input:radio[name="tradeType"]:checked').val();
					if(val == null){
						alert("请选择类型!");
						return false;
					}
					
					var tradeMoney = $("#agentMoney").val();
					if (tradeMoney == "" || tradeMoney == undefined || tradeMoney == null) {
						$("#warn2").css({ display: 'block' });
					}else{
						$("#mask").css({ display: 'none' });
		    			//$("#agentMoney").empty();
		    			$("#LoginBox").fadeOut("fast");
						$.ajax({
			        		url : 'memberInfoAction!addMerchantModelInBack.action',
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
				当前位置: 会员管理- 会员列表
			</div>
			<%-- <div class="ropt">
				<a href="${ctx}/subsystem/memberInfoAction!newPage.action">新增</a>
			</div> --%>
			<div class="clear"></div>
		</div>
		
	<form name="lineOfCradeForm" id="lineOfCradeForm">		     
		<div id="LoginBox">
	        <div class="row1">
	              充值：<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
	        </div>
	        <div class="row">
			             类&nbsp;&nbsp;&nbsp;&nbsp;型： 
			            联盟商家：<input type="radio" id="type" name="tradeType" value="2"/>
			            营销公司：<input type="radio" id="type" name="tradeType" value="3" />
	        </div>
	        <div class="row">
	           		 金&nbsp;&nbsp;&nbsp;&nbsp;额：&nbsp;&nbsp;<input type="text" id="agentMoney" name="agentMoney" style="border: 1px solid #e5e5e5;width: 200px;" placeholder="仅数字，小数点后最多两位" onkeyup="clearNoNum(this)"/>
	           		<a href="javascript:void(0)" title="提示" style="color: red" class="warning" id="warn2">*</a>
	        </div>
	        <div class="row" >
	            <a href="javascript:void(0)" id="loginbtn" onclick="">提交</a>
	        </div>
  	  </div>
    </form>
		
		
		
		<form action="${ctx}/subsystem/memberInfoAction!findAllMerchantInfo.action" method="get">
		<table class="listTable2">
			<tr>
				<td>
						 &nbsp;&nbsp;&nbsp;用户名称：<input type="text" placeholder="请输入名称" name="name" value="${name }"/> &nbsp;&nbsp;&nbsp;
						用户昵称：<input type="text" placeholder="请输入昵称" name="nickName" value="${nickName }"/> &nbsp;&nbsp;&nbsp;
					        电话号码： <input type="text" placeholder="请输入电话号码" name="mobile" value="${mobile }"/>&nbsp;&nbsp;&nbsp;
					        用户类型：<select name="type">
					        <option value="2" <c:if test="${type eq '2'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${type eq '0'}">selected="selected"</c:if>>普通用户</option>
					        <option value="1" <c:if test="${type eq '1'}">selected="selected"</c:if>>商家</option>
					     </select>
					     &nbsp;&nbsp;&nbsp;<br/>
					  &nbsp;&nbsp;   用户身份：<select name="identity">
					        <option value="4" <c:if test="${identity eq '4'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${identity eq '0'}">selected="selected"</c:if>>普通用户</option>
					        <option value="1" <c:if test="${identity eq '1'}">selected="selected"</c:if>>个代</option>
					        <option value="2" <c:if test="${identity eq '2'}">selected="selected"</c:if>>区代</option>
					        <option value="3" <c:if test="${identity eq '3'}">selected="selected"</c:if>>市代</option>
					     </select>&nbsp;&nbsp;&nbsp;
					      审核状态：<select name="realNameState">
					        <option value="4" <c:if test="${realNameState eq '4'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${realNameState eq '0'}">selected="selected"</c:if>>未实名认证</option>
					        <option value="1" <c:if test="${realNameState eq '1'}">selected="selected"</c:if>>审核中</option>
					        <option value="2" <c:if test="${realNameState eq '2'}">selected="selected"</c:if>>已实名认证</option>
					        <option value="3" <c:if test="${realNameState eq '3'}">selected="selected"</c:if>>审核失败</option>
					     </select>
					      &nbsp;&nbsp;&nbsp;
					       活跃状态：<select name="isActivityMember">
					        <option value="" selected="selected">全部</option>
					        <option value="0" <c:if test="${isActivityMember eq '0'}">selected="selected"</c:if>>非活跃</option>
					        <option value="1" <c:if test="${isActivityMember eq '1'}">selected="selected"</c:if>>活跃</option>
					       
					     </select>
					      &nbsp;&nbsp;&nbsp;
					  <input type="submit" value="查询"/>
					 
				</td>
			</tr>
		</table>
	</form>
					
					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>微信号</td>
				<td>名称</td>
				<td>手机号码</td>
				<td>上级</td>
				<td>昵称</td>
				<td>性别</td>				
				<td>会员已领比例</td>
				<td>余额</td>
				<td>待领消费金额</td>
				<td>累计消费金额</td>
				<td>可提现额</td>
				<td>责任消费额</td>
				<td>累计分红金额</td>
				<td>分红权个数</td>
				
				<td>用户状态</td>
                <td>实名认证状态</td>
                <td>联盟商家类型</td>
				<td>操作</td>
			</tr>


           <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				  <tr>
					<td>${status.count}</td>
					<td>${bean.unionId}</td>
					<td>${bean.name}</td>
					<td>${bean.mobile}</td>
					<td>
						<a href="${ctx}/subsystem/memberInfoAction!views.action?id=${bean.superId }&token=${bean.superToken}">${bean.superName}</a>
					</td>
					
					<td>${bean.nickName}</td>
					<c:if test="${bean.sex==1}">
					     <td>男</td>
					</c:if>
					<c:if test="${bean.sex==0}">
					     <td>未知</td>
					</c:if>
					<c:if test="${bean.sex==2}">
					     <td>女</td>
					</c:if>
					<fmt:parseNumber value="${bean.cumulativeMoney}" var="pageUnPayAmount" />
					<fmt:parseNumber value="${bean.consumptionMoney}" var="consumptionMoney" />
					 <c:if test="${pageUnPayAmount==0}">
					   <td>0.00</td>					  
					</c:if>
					<c:if test="${pageUnPayAmount ne 0}">					   
					   <td>
						  <fmt:formatNumber value="${(pageUnPayAmount-consumptionMoney)/pageUnPayAmount}" maxFractionDigits="2" type="percent" />
					   </td> 
					</c:if> 
					
					<td>
						<a href="memberInfoAction!findMoneyHistoryByMemberId.action?id=${bean.id }&token=${bean.token}&tradeType=0">${bean.balanceMoney}</a>
					</td>
					<td>${bean.consumptionMoney}</td>
					<td>
						<a href="memberInfoAction!findCumulativeMoneyById.action?id=${bean.id }&token=${bean.token}&op=0">${bean.cumulativeMoney}</a>
					</td>
					<td>
						<a href="memberInfoAction!findMoneyHistoryByMemberId.action?id=${bean.id }&token=${bean.token}&tradeType=1">${bean.withdrawalMoney}</a>
					</td>
					<td>
						<a href="memberInfoAction!findMoneyHistoryByMemberId.action?id=${bean.id }&token=${bean.token}&tradeType=2">${bean.insuranceMoney}</a>
					</td>
					<td>
						<a href="memberInfoAction!findDiviHistoryById.action?id=${bean.id }&op=0">${bean.dividendsTotalMoney}</a>
					</td>
					<td>
						<a href="memberInfoAction!findDiviHistoryById.action?id=${bean.id }&op=1">${bean.dividendsNum}</a>
					</td>
					
					<td>
						<c:if test="${bean.status eq 0}">停用</c:if>
						<c:if test="${bean.status eq 1}">启用</c:if>
					</td>
					<c:if test="${bean.realNameState==0}">
					   <td>未实名认证</td>
					</c:if>
					<c:if test="${bean.realNameState==1}">
					   <td>审核中</td>
					</c:if>
					<c:if test="${bean.realNameState==2}">
					   <td>已实名认证</td>
					</c:if>
					<c:if test="${bean.realNameState==3}">
					   <td>审核失败</td>
					</c:if>
					<c:if test="${empty bean.realNameState}">
					   <td></td>
					</c:if>
                    
                    <td>
						<c:if test="${bean.merchantType eq 0}">普通会员</c:if>
						<c:if test="${bean.merchantType eq 1}">商家版</c:if>
						<c:if test="${bean.merchantType eq 2}">企业版</c:if>
						<c:if test="${bean.merchantType eq 3}">商家代理版</c:if>
						<c:if test="${bean.merchantType eq 4}">企业代理</c:if>
						<c:if test="${bean.merchantType eq 5}">地级市代理</c:if>
						<c:if test="${bean.merchantType eq 6}">市级代理</c:if>
						<c:if test="${bean.merchantType eq 7}">直辖市级代理</c:if>
					</td>
                    
					<td>				   
					    <c:if test="${bean.merchantType == 0&&bean.type==1}">
						   <a href="${ctx}/subsystem/memberInfoAction!toSetMerchantModel.action?mobile=${bean.mobile}">成为联盟商家</a>
						   &nbsp; &nbsp;
					    </c:if>
					    <c:if test="${bean.merchantType != 0&&bean.type==1}">
						   <a href="${ctx}/subsystem/memberInfoAction!toSetMerchantModel.action?mobile=${bean.mobile}">修改联盟商家</a>
						   &nbsp; &nbsp;
					    </c:if>
					    	
					    
					     <!--<c:if test="${bean.merchantModel == 1}">
						   <a href="${ctx}/subsystem/tradeInfoAction!findMerchantGiveModel.action?type=1&id=${bean.id}">赠送尊享版商家</a>
						   &nbsp; &nbsp;
					    </c:if>	
					    <c:if test="${bean.merchantModel == 2}">
						   <a  href="${ctx}/subsystem/tradeInfoAction!findMerchantGiveModel.action?type=2&id=${bean.id}">赠送联盟商家</a>
						   &nbsp; &nbsp;
					    </c:if>	-->								  
					</td>
				</tr>									
			</c:forEach>
 
			

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findAllMerchantInfo.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="name" value="${name }"/>
					<input type="hidden" name="nickName" value="${nickName }"/>
					 <input type="hidden" name="mobile" value="${mobile }"/>
					 <select name="type" style="display: none;">
					        <option value="2" <c:if test="${type eq '2'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${type eq '0'}">selected="selected"</c:if>>普通用户</option>
					        <option value="1" <c:if test="${type eq '1'}">selected="selected"</c:if>>商家</option>
					     </select>
					 <select name="identity" style="display: none;">
					        <option value="4" <c:if test="${identity eq '4'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${identity eq '0'}">selected="selected"</c:if>>普通用户</option>
					        <option value="1" <c:if test="${identity eq '1'}">selected="selected"</c:if>>个代</option>
					        <option value="2" <c:if test="${identity eq '2'}">selected="selected"</c:if>>区代</option>
					        <option value="3" <c:if test="${identity eq '3'}">selected="selected"</c:if>>市代</option>
					        <option value="4" <c:if test="${identity eq '4'}">selected="selected"</c:if>>运营中心</option>
					 </select>
					 <select name="realNameState"  style="display: none;">
					        <option value="4" <c:if test="${realNameState eq '4'}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${realNameState eq '0'}">selected="selected"</c:if>>未提交审核</option>
					        <option value="1" <c:if test="${realNameState eq '1'}">selected="selected"</c:if>>审核中</option>
					        <option value="2" <c:if test="${realNameState eq '2'}">selected="selected"</c:if>>审核成功</option>
					        <option value="3" <c:if test="${realNameState eq '3'}">selected="selected"</c:if>>审核失败</option>
					     </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>