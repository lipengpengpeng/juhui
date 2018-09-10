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
			  <tr>
			  	<td>序号</td>
			  	<td>订单号</td>
			  	<td>用户名称</td>
			  	<td>用户电话号码 </td>
			  	<td>交易金额</td>
			  	<td>交易分红权</td>
			  	<td>交易分红权单价</td>
			  	<td>交易时间 </td>
			  	<td>交易类型</td>	
			  	<td>交易状态</td>				  	
			  	
			  </tr>
			  
			  <c:forEach items="${pager.resultList}" var="bean" varStatus="status">
			  	<tr>
			  		<td>${status.count}</td>		  		
			  		<td>${bean.tradeNo }</td>
			  		<td>${bean.memberName }</td>
			  		<td>${bean.memberMobile }</td>
			  		<td>${bean.tradeMoney }</td>
			  		<td>${bean.tradeDiviNum }</td>
			  		<td>${bean.tradeUnit }</td>
			  		<td><fmt:formatDate value="${bean.tradeTime }" pattern="yyyy-MM-dd"/></td>
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
			  		
			  	</tr>
			  </c:forEach>
			  
		</table>
			<c:if test="${not empty pager.resultList}">
					<s:form action="benefitInfoAction!findSpecialMemTradeHistory.action"
							namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
						<!-- 分页 -->
						<%@ include file="../../common/pager.jsp"%>
					</s:form>
			</c:if>
	</body>
</html>