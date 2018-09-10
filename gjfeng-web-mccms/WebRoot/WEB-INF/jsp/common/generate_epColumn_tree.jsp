<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成树</title>
		<script src="${ctx}/js/dtree/dtree.js" type="text/javascript"></script>
		<link href="${ctx}/js/dtree/dtree.css" rel="stylesheet" type="text/css"></link>
		<style>
			.dTreeNode{height:18px;}
		</style>
	</head>
	<body>
		<div class="dtree">
			<p><a href="javascript: tree.openAll();"><!-- 全部展开 --><s:text name="common.word.openall" /></a> | <a href="javascript: tree.closeAll();"><!-- 全部合并 --><s:text name="common.word.closeall" /></a></p>
			<script type="text/javascript">
				
				var returnValue = '<s:text name="common.word.site.center" />';
				var returnId = "";	
				
				tree = new dTree('tree');
				tree.add(0,-1,'<s:text name="common.word.site.center" />',"javascript:selectHandle('<s:text name="common.word.site.center" />',0);",'<s:text name="common.word.site.center" />','mainFrame');
				
				<s:iterator value="treeList">
					tree.add('<s:property value='id'/>','<s:property value='father'/>','<s:property value='names'/>-<span style="color:red"><s:property value='typeColumn.templateType'/><span>',"javascript:selectHandle('<s:property value='names'/>','<s:property value='id'/>');",'<s:property value='names'/>','mainFrame');
				</s:iterator>
				
				document.write(tree);
				tree.config.useCookies = false;
				tree.openTo(${father},true);
				function selectHandle(value,id) {
					returnValue = value;
					returnId = id;
				}
			</script>
		</div>
		<br /><br />
	</body>
</html>

