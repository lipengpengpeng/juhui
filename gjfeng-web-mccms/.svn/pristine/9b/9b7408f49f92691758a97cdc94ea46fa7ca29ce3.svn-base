<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/left.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
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
		<security:authorize ifAnyGranted="EP_SYSTEM">
			<div class="newslist_tree">
				<div id="PARENT">
					<ul id="newslist_nav">
						<li>
							<a href="#Menu=ChildMenu1" onclick="DoMenu('ChildMenu1')"><!-- 用户相关 --><s:text name="left.system.left.users" /></a>
							<ul id="ChildMenu1" class="expanded">
								<security:authorize ifAnyGranted="SYSTEM_USER">
									<li>
										<div class="father">
											<a href="${ctx}/usersAction.action" target="mainFrame"><!-- 用户管理 --><s:text name="left.system.left.user" /></a>
										</div>
									</li>
								</security:authorize>
								<security:authorize ifAnyGranted="SYSTEM_AUTHORITIES">
									<li>
										<div class="father">
											<a href="${ctx}/authoritiesAction.action"
												target="mainFrame"><!-- 权限管理 --><s:text name="left.system.left.authorities" /></a>
										</div>
									</li>
								</security:authorize>
								<security:authorize ifAnyGranted="SYSTEM_ROLE">
									<li>
										<div class="father">
											<a href="${ctx}/rolesAction.action" target="mainFrame"><!-- 角色管理 --><s:text name="left.system.left.role" /></a>
										</div>
									</li>
								</security:authorize>
							</ul>
						</li>
						
						<li>
							<a href="#Menu=ChildMenu2" onclick="DoMenu('ChildMenu2')"><!-- 栏目相关 --><s:text name="left.system.left.column" /></a>
							<ul id="ChildMenu2" class="collapsed">
	                           	<security:authorize ifAnyGranted="SYSTEM_PAGE_TYPE">
									<li>
										<div class="father">
											<a href="${ctx}/pageTypeAction!retrieveAllPageTypes.action" target="mainFrame"><!-- 页面类型管理 --><s:text name="left.system.left.page.type" /></a>
										</div>
									</li>
								</security:authorize>
	                           	<security:authorize ifAnyGranted="SYSTEM_ALTER_URL">
									<li>
										<div class="father">
											<a href="${ctx}/alterUrlAction.action" target="mainFrame"><!-- 批量修改URL --><s:text name="left.system.left.alert.url" /></a>
										</div>
									</li>
								</security:authorize>
							</ul>
						</li>
						
						<li>
							<a href="#Menu=ChildMenu3" onclick="DoMenu('ChildMenu3')"><!-- 内容相关 --><s:text name="left.system.left.collection" /></a>
							<ul id="ChildMenu3" class="collapsed">
                        		<security:authorize ifAnyGranted="SYSTEM_PARAMETER">
                        			<li>
										<div class="father">
											<a href="${ctx}/mcParameterAction.action" target="mainFrame"><!-- 产品参数管理 --><s:text name="left.system.left.product.parameter" /></a>
										</div>
									</li>
								</security:authorize>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</security:authorize>
	</body>
</html>

