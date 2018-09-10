<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/left.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script src="${ctx}/js/dtree/dtree.js" type="text/javascript"></script>
		<link href="${ctx}/js/dtree/dtree.css" rel="stylesheet" type="text/css"></link>
	</head>
	<body>
	<!-- 规则为 -->
	
	<div class="dtree">
		<p><a href="javascript: d.openAll();"><!-- 全部展开 --><s:text name="common.word.openall" /></a>
		 | <a href="javascript: d.closeAll();"><!-- 全部合并 --><s:text name="common.word.closeall" /></a></p>
		
		<script type="text/javascript">
			d = new dTree('d');
			d.add(0,-1,'<s:text name="common.word.site.center" />','collection/epNewsAction!query.action?father=0','<s:text name="common.word.site.center" />','mainFrame');
			${dtree}
			document.write(d);
			/* $("img").each(function(){
				  var url=$(this).attr("src")
				  url=url.replace("js/","ROOT/js/")
				  $(this).attr("src",url);
				 // console.log(url)
			  }) */
		</script>
	</div>
	<br/><br/><br/><br/><br/>
	</body>
</html>