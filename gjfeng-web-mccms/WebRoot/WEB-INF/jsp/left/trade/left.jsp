<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		
		<script src="${ctx}/js/jquery.ui/ui/jquery.ui.all.js"
			type="text/javascript"></script>
		<script src="${ctx}/js/layout/jquery.layout.js" type="text/javascript"></script>
		<script src="${ctx}/js/ddaccordion.js" type="text/javascript"></script>
		<link href="${ctx}/js/jquery.ui/themes/default/ui.all.css"
			rel="stylesheet" type="text/css" />

		<link href="${ctx}/image/style_blue5/style.css" rel="stylesheet"
			type="text/css" />
		<script src="${ctx}/image/style_blue5/style.js" type="text/javascript"></script>
		<script src="${ctx}/js/mainframe_nav.js" type="text/javascript"></script>
        <link href="${ctx}/css/ejdh.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var LastLeftID = "ChildMenu1";
			function menuFix() {
			 var obj = $("#newslist_nav li");
			 for (var i=0; i<obj.length; i++) {
			  obj[i].onmouseover=function() {
			   this.className+=(this.className.length>0? " ": "") + "sfhover";
			  }
			  obj[i].onMouseDown=function() {
			   this.className+=(this.className.length>0? " ": "") + "sfhover";
			  }
			  obj[i].onMouseUp=function() {
			   this.className+=(this.className.length>0? " ": "") + "sfhover";
			  }
			  obj[i].onmouseout=function() {
			   this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
			  }
			 }
			}
			function DoMenu(emid)
			{
			 var obj = document.getElementById(emid); 
			 obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
			 if((LastLeftID!="")&&(emid!=LastLeftID)) //关闭上一个Menu
			 {
			  document.getElementById(LastLeftID).className = "collapsed";
			 }
			 LastLeftID = emid;
			}
			function GetMenuID()
			{
			 var MenuID="";
			 var _paramStr = new String(window.location.href);
			 var _sharpPos = _paramStr.indexOf("#");
			 
			 if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
			 {
			  _paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
			 }
			 else
			 {
			  _paramStr = "";
			 }
			 
			 if (_paramStr.length > 0)
			 {
			  var _paramArr = _paramStr.split("&");
			  if (_paramArr.length>0)
			  {
			   var _paramKeyVal = _paramArr[0].split("=");
			   if (_paramKeyVal.length>0)
			   {
			    MenuID = _paramKeyVal[1];
			   }
			  }
			 }
			 
			 if(MenuID!="")
			 {
			  	DoMenu(MenuID)
			 }
			}
			GetMenuID(); //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
			menuFix();
		</script>

</head>
	<body>
	<div class="newslist_tree">
				<div id="PARENT">
					<ul id="newslist_nav">
						<li>
							<a href="#Menu=ChildMenu1" onclick="DoMenu('ChildMenu1')">订单记录</a>
								<ul id="ChildMenu1" class="expanded">	
									<li>
										<security:authorize ifAnyGranted="GJF_TRADE_ORDER_LIST">
											<div class="father">
												<a href="${ctx}/subsystem/orderInfoAction!findAllOrderInfo.action" target="mainFrame">订单列表</a>
											</div> 
										</security:authorize>
									</li>
									<li>
										<security:authorize ifAnyGranted="GJF_TRADE_WITHDRAW">
											<div class="father">
												<a href="${ctx}/subsystem/tradeInfoAction!findAllTrade.action" target="mainFrame">提现列表</a>
											</div>
										</security:authorize> 
									</li>
									
									<li>
									
											<div class="father">
												<a href="${ctx}/subsystem/tradeInfoAction!goPaidPayPage.action" target="mainFrame">代付充值</a>
											</div>

									</li>
									
									<%-- <li>
									<security:authorize ifAnyGranted="GJF_TRADE_DETAIL">
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!retrieveMemberTradeBenefitByPage.action?op=1" target="mainFrame">交易明细</a>
										</div> 
									</security:authorize> 
									</li> --%>
									
									<li>
									<security:authorize ifAnyGranted="GJF_TRADE_PAY_DETAIL">
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!retrieveTradeLogByPage.action" target="mainFrame">支付明细</a>
										</div> 
									</security:authorize> 
									</li>
									<li>
							    <%-- <security:authorize ifAnyGranted="GJF_TRADE_WITHDRAW"> --%>
									<div class="father">
										<a href="${ctx}/subsystem/orderInfoAction!checkAccount.action" target="mainFrame">京东对账</a>
									</div>
							       <%-- </security:authorize>  --%>
						           </li>
								</ul>
						</li>
						<li>
							<a href="#Menu=ChildMenu2" onclick="DoMenu('ChildMenu2')">分红记录</a>
							<ul id="ChildMenu2" class="collapsed">
									<li>
									<security:authorize ifAnyGranted="GJF_TRADE_DIVI_HISTORY">
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!retrieveDiviByPager.action" target="mainFrame">分红权记录</a>
										</div>
									</security:authorize> 
									</li>
									<li>
									<security:authorize ifAnyGranted="GJF_TRADE_DIVI_TRADE_HISTORY">
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!retrieveDiviHistoryByPager.action" target="mainFrame">分红交易记录</a>
										</div>
									</security:authorize> 
									</li>
							</ul>
						</li>
						<li>
							<a href="#Menu=ChildMenu3" onclick="DoMenu('ChildMenu3')">销售录入</a>
							<ul id="ChildMenu3" class="collapsed">
                        			<li>
                        				<security:authorize ifAnyGranted="GJF_TRADE_BENEFIT_HISTORY">
											<div class="father">
												<a href="${ctx}/subsystem/tradeInfoAction!retrieveMemberTradeBenefitByPage.action" target="mainFrame">销售录入</a>
											</div> 
										</security:authorize> 
									</li>
							</ul>
						</li>
						<li>
							<a href="#Menu=ChildMenu4" onclick="DoMenu('ChildMenu4')">转移记录</a>
							<ul id="ChildMenu4" class="collapsed">
                        			<li>
                        				<%-- <security:authorize ifAnyGranted="GJF_TRADE_MEMBER_POINT_HISTORY"> --%>
											<div class="father">
												<a href="${ctx}/subsystem/tradeInfoAction!findMemberPointHistory.action" target="mainFrame">积分转移记录</a>
											</div> 
										<%-- </security:authorize>  --%>
									</li>
									<%-- <li>
                        				<security:authorize ifAnyGranted="GJF_TRADE_MEMBER_POINT_HISTORY"> 
											<div class="father">
												<a href="${ctx}/subsystem/tradeInfoAction!findMergeMemberHistory.action" target="mainFrame">用户合并记录</a>
											</div> 
										</security:authorize>  
									</li>--%>
							</ul>
						</li>
						
						<li>
							<a href="#Menu=ChildMenu5" onclick="DoMenu('ChildMenu5')">授信记录</a>
							<ul id="ChildMenu5" class="collapsed">                       			
									<li>
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!findShouxinHistory.action" target="mainFrame">授信记录列表</a>
										</div> 
									</li>
							</ul>
						</li>
						
						<li>
							<a href="#Menu=ChildMenu6" onclick="DoMenu('ChildMenu6')">凤凰宝交易记录</a>
							<ul id="ChildMenu6" class="collapsed">                       			
									<li>
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!findFhTreasureTradeHistory.action" target="mainFrame">交易记录</a>
										</div> 
									</li>
							</ul>
						</li>
						<li>
							<a href="#Menu=ChildMenu7" onclick="DoMenu('ChildMenu7')">代金券</a>
							<ul id="ChildMenu7" class="collapsed">                       			
									<li>
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!findMemberVoucherc.action" target="mainFrame">交易记录</a>
										</div> 
									</li>
							</ul>
						</li>
						<li>
							<a href="#Menu=ChildMenu8" onclick="DoMenu('ChildMenu8')">商家升级记录</a>
							<ul id="ChildMenu8" class="collapsed">                       			
									<li>
										<div class="father">
											<a href="${ctx}/subsystem/tradeInfoAction!findMerchantUpgradeHistory.action" target="mainFrame">交易记录</a>
										</div> 
									</li>
							</ul>
						</li>			
																					
					</ul>
				</div>
	</div>
					
		 
		 
		
	</body>

</html>

