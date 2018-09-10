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
$(document).ready(function(){

		$("#name").focus();
		$("#linksForm").validate({
			 rules: { 
				name: { 
        			required: true, 
        			maxlength:10,
        			remote: "rolesAction!checkName.action?orgName=''&math="+Math.random()
    			}
			},
			messages: {
				name: {
					//该角色已存在
					remote: '<s:text name="enterprise.system.role.name.exist" />'	
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		/* 全选功能列表 */
		$("#selectAllforCheckedMenu").click(function() {
			if($("#selectAllforCheckedMenu").attr("checked") == true) {
				$(".fn").attr("checked","checked");
			} else {
				$(".fn").removeAttr("checked");
			}
		});
		
		//点击功能选项时检测是否全部选项都选上了
		$(".fn").click(function(){
			checkFnSelect();
		});
		
		//合并单元格
		var col = 0;
		if(${empty column}){
			col = col + 1;
		}
		if(${empty function}){
			col = col + 1;
		}
		if(col != 0){
			$("#menuCol").attr("colspan",col);
			$("#menuCon").attr("colspan",col);
		}
	});	
			
	/*检测是否全部选项都选上了*/
	function checkFnSelect(){
		//检测功能列表是否全部选上了
		if($(".fn[checked]").length == ${fn:length(function)}){
			$("#selectAllforCheckedMenu").attr("checked","checked");
		}else{
			$("#selectAllforCheckedMenu").removeAttr("checked");
		}
	}
</script>
 	</head>
	<body>
  
	<div class="rhead">
		<div class="rpos">
			<!-- 当前位置: 系统配置 - 角色管理 --><s:text name="enterprise.system.role.current.location" /> - 
			<!-- 添加角色信息 --><s:text name="enterprise.system.role.current.location.add" />
		</div>
		<div class="ropt"><input type="button" value="返回" onclick="history.back()"/></div>
		<div class="clear"></div>
  	</div>
 	
	<form action="rolesAction!add.action" method="post" name="linksForm" id="linksForm" enctype="multipart/form-data" method="post">
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td><!-- 角色名称 --><s:text name="enterprise.system.role.name" /></td>
				<td colspan="2"><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td colspan="2">
					<select name="roles.state">
						<option value="1"><!-- 已启用 --><s:text name="common.word.has.opened" /></option>
						<option value="0"><!-- 已停用 --><s:text name="common.word.has.closed" /></option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:center;font-weight:bold;">
					<!-- 授权--><s:text name="enterprise.system.role.grant" />
				</td>
			</tr>
			<tr style="background-color:#C1EBFF;">
				<c:if test="${not empty menu}">
				<td width=40% style="text-align:center;font-weight:bold;" id="menuCol">
					<!-- 菜单 --><s:text name="enterprise.system.authority.menu" />
				</td>
				</c:if>
				<c:if test="${not empty column}">
				<td width=40% style="text-align:center;font-weight:bold;">
					<!-- 栏目 --><s:text name="enterprise.system.authority.column" />
				</td>
				</c:if>
				<c:if test="${not empty function}">
				<td width=20% style="text-align:center;font-weight:bold;">
					<!-- 功能 --><s:text name="enterprise.system.authority.function" />
				</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${not empty menu}">
				<td style="padding-left:20px;" valign="top" id="menuCon">
					<div class="dtree">
						<p><a href="javascript: menu.openAll();"><!-- 全部展开 --><s:text name="common.word.openall" /></a> | 
							<a href="javascript: menu.closeAll();"><!-- 全部合并 --><s:text name="common.word.closeall" /></a></p>
						
						<script type="text/javascript">
							menu = new dTree('menu');
							menu.config.check = true;
							menu.config.checkName = 'checkedMenu';
							menu.add(0,-1,'MCCMS','javascript:void(0);','MCCMS');
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
							column.config.check = true;
							column.config.checkName = 'checkedMenu';
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
					<div><input id="selectAllforCheckedMenu" type="checkbox"
								name="selectAllforCheckedMenu" style="vertical-align: middle;" />
							<!-- 全选功能列表 --><s:text name="enterprise.system.role.select.all" /></div>
					<c:forEach var="authorities" items="${function}">
						<div>
							<input type="checkbox" name="checkedMenu" value="${authorities.id}"
									<c:forEach var="roleAuth" items="${roles.rolesAuthoritieses}">
							<c:if test="${authorities.id == roleAuth.authorities.id}">
								checked
							</c:if>
						</c:forEach> style="vertical-align: middle;" class="fn" />
							${authorities.displayName}
						</div>
					</c:forEach>
					<br /><br />
				</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="3" style="text-align:center;">
				<input type="submit" value='<s:text name="common.word.submit" />'/>
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

