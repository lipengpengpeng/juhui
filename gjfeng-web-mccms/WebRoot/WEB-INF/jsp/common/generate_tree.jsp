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
			<p><a href="javascript: tree.openAll();">全部展开</a> | <a href="javascript: tree.closeAll();">全部合并</a></p>
			
			<script type="text/javascript">
			
				var returnValue = '${returnValue}';
				
				function selectHandle(value) {
					returnValue = value;
				}
				
				tree = new dTree('tree');
				tree.config.useCookies = false;
				
				${tree}
				document.write(tree);
				
				tree.openTo(${returnValueId},true);
			</script>
		</div>
		<br /><br />
	</body>
</html>

