<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/left.jsp"%>
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
							<a href="#Menu=ChildMenu1" onclick="DoMenu('ChildMenu1')">会员统计</a>
							<ul id="ChildMenu1" class="expanded">	
								<li>
										<security:authorize ifAnyGranted="GJF_COUNT_MEMBER">
												<div class="father">
													<a href="${ctx}/subsystem/countInfoAction!countMemberInfoAmount.action?op=countMembers" target="mainFrame">会员（商家）总数</a>
												</div>
										</security:authorize>
									</li>
									<%-- <li>
										<security:authorize ifAnyGranted="GJF_COUNT_CUMULATIVE_MONEY_">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!countMemberInfoAmount.action?op=membersConsume" target="mainFrame">会员消费额度</a>
											</div>
										</security:authorize>
									</li> --%>
									<%-- <li>
										<security:authorize ifAnyGranted="GJF_COUNT_STORE_BENEFIT_MONEY">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!countStoresBenefit.action" target="mainFrame">商家让利额度</a>
											</div>
										</security:authorize>
									</li> --%>
							</ul>
						</li>
						
						
						<li>
							<a href="#Menu=ChildMenu2" onclick="DoMenu('ChildMenu2')">资金统计</a>
							<ul id="ChildMenu2" class="collapsed">
									<li>
									 	<security:authorize ifAnyGranted="GJF_COUNT_BENEFIT_POOL">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!countBenefitPool.action" target="mainFrame">资金池</a>
											</div>
										</security:authorize>
									</li>
									<li>
										<security:authorize ifAnyGranted="GJF_COUNT_DATA_SHOW">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!diviDataList.action" target="mainFrame">分红管理</a>
											</div>
										</security:authorize>
									</li>
									<li>
										 <security:authorize ifAnyGranted="GJF_COUNT_DIVI_TREND">	
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!benefitDayTrend.action" target="mainFrame">分红趋势</a>
											</div>
										</security:authorize>
									</li>
									<li>
											<security:authorize ifAnyGranted="GJF_COUNT_AREA_DEEDS">
												<div class="father">
													<a href="${ctx}/subsystem/countInfoAction!countPerformanceByCondition.action" target="mainFrame">区域业绩</a>
												</div>
											</security:authorize>
									</li>
							</ul>
						</li>
						
						
						<li>
							<a href="#Menu=ChildMenu3" onclick="DoMenu('ChildMenu3')">报表统计</a>
							<ul id="ChildMenu3" class="collapsed">
                        			<li>
                        				<%-- <security:authorize ifAnyGranted="GJF_COUNT_SUMMARY_REPORT">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!toSummaryReport.action" target="mainFrame">总报表</a>
											</div>
										</security:authorize> --%>
										<security:authorize ifAnyGranted="GJF_COUNT_SUMMARY_REPORT">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!countTotalDataByTime.action" target="mainFrame">总报表</a>
											</div>
										</security:authorize>
									</li>
									<li>
                        					<security:authorize ifAnyGranted="GJF_COUNT_BENEFIR_REPORT">
												<div class="father">
													<a href="${ctx}/subsystem/countInfoAction!toBenefitReport.action" target="mainFrame">让利日报表</a>
												</div>
											</security:authorize>
									</li>
									<li>
                        				<security:authorize ifAnyGranted="GJF_COUNT_WEAL_PAY_OUT">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!toWealPayoutReport.action" target="mainFrame">福利派发报表</a>
											</div>
										</security:authorize>
									</li>
									<li>
                        				<security:authorize ifAnyGranted="GJF_COUNT_WEAL_OUT_PUT">
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!toWealOutputReport.action" target="mainFrame">福利产出报表</a>
											</div>
										</security:authorize>
									</li>
									<li>
                        					<security:authorize ifAnyGranted="GJF_COUNT_WITHDRAW_REPORT">	
												<div class="father">
													<a href="${ctx}/subsystem/countInfoAction!toWithdrawReport.action" target="mainFrame">提现日报表</a>
												</div>
											</security:authorize>
									</li>
							</ul>
						</li>	
						
						
						<li>
							<a href="#Menu=ChildMenu4" onclick="DoMenu('ChildMenu4')">系统信息统计</a>
							<ul id="ChildMenu4" class="collapsed">
									<li>
                        				<security:authorize ifAnyGranted="GJF_COUNT_MESSAGE_SEND">	
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!findMessageHistory.action" target="mainFrame">短信发送记录</a>
											</div>
										</security:authorize>
									</li>
									<li>
                        				<security:authorize ifAnyGranted="GJF_COUNT_ERROR_MESSAGE">	
											<div class="father">
												<a href="${ctx}/subsystem/countInfoAction!findErrorMsg.action" target="mainFrame">错误信息</a>
											</div>
										</security:authorize>
									</li>
							</ul>
						</li>
						
						
						<%-- <div class="father">
							<a href="${ctx}/subsystem/countInfoAction!countMembersLowLevelConsume.action" target="mainFrame">会员下级总消费额</a>
						</div> --%>
						
				</ul>	
			</div>	
		</div>	
	</body>

</html>

