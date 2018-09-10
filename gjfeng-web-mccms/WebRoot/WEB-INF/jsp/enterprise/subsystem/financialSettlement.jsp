
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
				var id = $("#id").val();
				var token = $("#token").val();
				var agentMoney = $("#agentMoney").val();
				if(agentMoney == 0 || agentMoney == "" || agentMoney == null){
					alert("未结算代理金额为零");
					return false;
				}
				var r = confirm("确定该操作吗？");
				if(r == true){
					$.ajax({
		        		url : 'memberInfoAction!agentClearing.action',
		        		type : 'post',
		        		data:{
		        			id : id,
		        			token : token
		        		},
		        		dataType : "text",
		        		beforeSend: function () {
			        		$(".bg-fixed-cover").show();
		        		},
		     			success : function(data){
		     				$(".bg-fixed-cover").hide();
	        				var jsondata = eval("("+data+")");  
	        				alert(jsondata.msg);
	        				window.location.reload();
		        		},
		        		error : function(data){
		        			$(".bg-fixed-cover").hide();
	                        alert("出错啦!请稍后再试");
		        		}
		        	});
				}
				return false;
			});
		});
		
		function back(){
			window.location.href = "memberInfoAction!findAllAgents.action";
		}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 商家列表- 财务结算
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
		</div>
		<table class="listTable3">
			<tbody>
				<tr>
					<th colspan="6">账户信息</th>
				</tr>
				<tr>
					<th style="text-align:center;" width="33.3%">总销售额</th>
					<th style="text-align:center;" width="33.3%">可提现金额</th>
					<th style="text-align:center;" width="33.3%">提现金额</th>
				</tr>
				<tr>
					<th style="text-align:center;" width="33.3%">${storeSaleTotalMoney }</th>
					<th style="text-align:center;" width="33.3%">${withdrawalMoney }</th>
					<th style="text-align:center;" width="33.3%">${withdrawHistoryMoney }</th>
				</tr>
			</tbody>
		</table>
		<div style="text-align:center;font-size:20px;color:red;margin-bottom: 5px;">
				<input type="submit" class="button" value="结算金额"/>
		</div>
		<input type="hidden" name="id" id="id" value="${id }" />
		<input type="hidden" name="token" id="token" value="${token }" />
		<input type="hidden" name="agentMoney" id="agentMoney" value="${agentMoney }" />
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>分红交易流水号</td>
				<td>交易金额</td>
				<td>交易分红权数</td>
				<td>交易分红单价</td>
				<td>交易实际分红单价</td>
				<td>交易比例</td>
				<td>添加时间</td>
				<td>交易类型</td>
				<td>交易状态</td>
			</tr>
			
			 	 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.tradeMoney }</td>
					<td>${bean.tradeDiviNum }</td>
					<td>${bean.tradeUnit }</td>
					<td>${bean.tradeRealUnit }</td>
					<td>${bean.tradeRatio }</td>
					<td>
						<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeType eq '0'}">直推会员分红</c:when>
							<c:when test="${bean.tradeType eq '1'}">直推商家分红</c:when>
							<c:when test="${bean.tradeType eq '2'}">普通用户分红</c:when>
							<c:when test="${bean.tradeType eq '3'}">普通商家分红</c:when>
							<c:when test="${bean.tradeType eq '4'}">市代分红</c:when>
							<c:when test="${bean.tradeType eq '5'}">区代分红</c:when>
							<c:when test="${bean.tradeType eq '6'}">个代分红</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq '0'}">提交中</c:when>
							<c:when test="${bean.tradeStatus eq '1'}">交易成功</c:when>
							<c:when test="${bean.tradeStatus eq '2'}">交易失败</c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			 
			

		</table>
		
		<c:if test="${not empty pager.resultList }">
			<s:form action="memberInfoAction!settlement.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="id" value="${id }" />
					<input type="hidden" name="token" value="${token }" />
					<input type="hidden" name="agentMoney" value="${agentMoney }" />
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
</html>