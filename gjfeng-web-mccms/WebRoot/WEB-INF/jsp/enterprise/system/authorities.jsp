<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script src="${ctx}/js/dtree/dtree.js" type="text/javascript"></script>
		<link href="${ctx}/js/dtree/dtree.css" rel="stylesheet" type="text/css"></link>
		<style>
			.dTreeNode{height:22px;margin-top:-4px;}
		</style>
		<script>
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
			//点击节点后，将右键菜单更改为自定义的菜单
			function loadMenu(obj) {
			    //获取参数
			    param = obj;
			    // ... 通过获取 参数 来，执行方法
			    //............................
			    //............................
			    
			    //重写 -- 页面点击右键菜单事件
			    document.oncontextmenu = function() {
			        //定位,显示
			        contextmenu.style.posLeft = document.body.scrollLeft + event.x + 10;
			        contextmenu.style.posTop = document.body.scrollTop + event.y + 10;
			        contextmenu.style.display = "inline";
			        return false;
			     }
			}
			//单击页面上除了菜单以外的地方，隐藏右键菜单
			document.onclick = function() {
			    //判断单击的是否为右键菜单
			    //如果不是，隐藏右键菜单
			    if (document.activeElement != contextmenu) {
			
			        contextmenu.style.display = "none"
			        //还原页面本来的右键菜单
			        document.oncontextmenu = '';
			    }
			
			}
			
			
			//颜色变量
			var colorTemp = "";
			var param = "";
		</script>
	</head>
	<body>

		<div class="rhead">
		   <div class="rpos"><!-- 当前位置: 系统配置 - 权限管理 --><s:text name="enterprise.system.authority.current.location" /></div>
		   <div class="ropt"><a href="authoritiesAction!add_page.action"><!-- 新增 --><s:text name="common.word.new" /></a></div>
		   <div class="clear"></div>
  		</div>
		
		<table class="listTable3">
			<tr id="nc" class="headbg">
				<c:if test="${not empty menu}">
				<td width=40%>
					<!-- 菜单 --><s:text name="enterprise.system.authority.menu" />
				</td>
				</c:if>
				<c:if test="${not empty column}">
				<td width=40%>
					<!-- 栏目 --><s:text name="enterprise.system.authority.column" />
				</td>
				</c:if>
				<c:if test="${not empty function}">
				<td width=20%>
					<!-- 功能 --><s:text name="enterprise.system.authority.function" />
				</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${not empty menu}">
				<td style="padding-left:20px;" valign="top">
					<div class="dtree">
						<p><a href="javascript: menu.openAll();"><!-- 全部展开 --><s:text name="common.word.openall" /></a> | 
							<a href="javascript: menu.closeAll();"><!-- 全部合并 --><s:text name="common.word.closeall" /></a></p>
						
						<script type="text/javascript">
							menu = new dTree('menu');
							menu.add(0,-1,'MCCMS','','MCCMS');
							${menu}
							document.write(menu);
						</script>
					</div>
					<br /><br />
				</td>
				</c:if>
				<c:if test="${not empty column}">
				<td style="padding-left:20px;" valign="top">
					<div class="dtree">
						<p><a href="javascript: column.openAll();"><!-- 全部展开 --><s:text name="common.word.openall" /></a> | 
							<a href="javascript: column.closeAll();"><!-- 全部合并 --><s:text name="common.word.closeall" /></a></p>
						
						<script type="text/javascript">
							column = new dTree('column');
							column.add(0,-1,'<s:text name="common.word.site.center" />','','<s:text name="common.word.site.center" />');
							${column}
							document.write(column);
						</script>
					</div>
					<br /><br />
				</td>
				</c:if>
				<c:if test="${not empty function}">
				<td style="padding-left:20px;padding-top:15px;" valign="top">
					<c:forEach var="authorities" items="${function}">
						<div><a href="authoritiesAction!edit.action?id=${authorities.id}" title="${authorities.name}">${authorities.displayName}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="authoritiesAction!delete.action?id=${authorities.id}"
							onclick="return doYouWantToDel();"><!-- 删除  --><s:text name="common.word.delete" /></a></div>
					</c:forEach>
					<br /><br />
				</td>
				</c:if>
			</tr>
		</table>
		<!-- 右键菜单开始 -->   
        <div id="contextmenu" align="center" style="border:1px solid #666666;background:#eeeeee; width:100px;padding:0px;display:none;position:absolute">
            <table style="width:100px; font-size:12px; border:dashed 1px black;">
                <tr>
                    <td id="modAttribute" onclick="javascript:window.location.href='authoritiesAction!add_page.action?id='+param"  onmouseover="javascript:colorTemp=this.style.backgroundColor;this.style.backgroundColor='#98B4CE';" onmouseout="javascript:this.style.backgroundColor=colorTemp;" style="  background-color:#F5F5D1; height:15px; border-bottom:1px solid black; cursor:pointer;" > <!-- 编辑事件 -->
                       	新增
                    </td>
                </tr>
                <tr>
                    <td id="modAttribute" onclick="javascript:window.location.href='authoritiesAction!edit.action?id='+param"  onmouseover="javascript:colorTemp=this.style.backgroundColor;this.style.backgroundColor='#98B4CE';" onmouseout="javascript:this.style.backgroundColor=colorTemp;" style="  background-color:#F5F5D1; height:15px; border-bottom:1px solid black; cursor:pointer; " > <!-- 编辑事件 -->
                       	编辑
                    </td>
                </tr>
                <tr>
                    <td id="modEvent" onclick="javascript:if(doYouWantToDel())window.location.href='authoritiesAction!delete.action?id='+param"  onmouseover="javascript:colorTemp=this.style.backgroundColor;this.style.backgroundColor='#98B4CE';" onmouseout="javascript:this.style.backgroundColor=colorTemp;" style="  background-color:#F5F5D1; height:15px; border-top:1px solid black; cursor:pointer; " > <!-- 删除事件 -->
                       	删除
                    </td>
                </tr>            
            </table>            
        </div>    
		<!-- 右键菜单结束 -->
	</body>
</html>

