<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<script>
$(document).ready(function(){

		$("#names").focus();
		
		if(""){
        	$.blockUI({ 
        		message: $('#message')
        		}) 
	        setTimeout($.unblockUI, 2000);
	         
	    }
	    
		$("#webSiteForm").validate({
			 rules: { 
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});	
	
	
</script>	
<style>
	.listTable3 tr{
		display:none;
	}
</style>
	</head>
	<body>
      	<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 基本配置  - 站点配置 --><s:text name="enterprise.system.website.current.location" />	
			</div>
			<div class="clear"></div>
		</div>
		<form action="webSiteAction!update.action" method="post" id="webSiteForm" enctype="multipart/form-data" method="post">
		<input type="hidden" name="webSite.id" value="${webSite.id}"/>
	 	<table class="listTable3">
			<tr<security:authorize ifAnyGranted="BASE_WEBSITE_NAME"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 站点名称 --><s:text name="enterprise.system.website.name" />	
					</center>
				</td>
				<td>
					<input type="text" size="60" name="webSite.names" value="${webSite.names}" class="{required: true,maxlength:30}"/>
												<!-- 可自定义 --><s:text name="enterprise.system.website.custom" />	
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_LINK"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 首页链接 --><s:text name="enterprise.system.website.index.link" />	
					</center>
				</td>
				<td>
					<input type="text" size="60"  name="webSite.domain" value="${webSite.domain}"  class="{required: true,url:true,maxlength:50}"/>
						<!-- 请修改为：http://您的域名:端口${ctx}(本地域名可以使用127.0.0.1代替域名) -->
						<s:text name="enterprise.system.website.domain" />	
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_TITLE"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 站点标题 --><s:text name="enterprise.system.website.title" />
					</center>
				</td>
				<td>
					<input type="text" size="60" name="webSite.title" value="${dictionaryInfoMap['webSite'].title}"  class="{required:true,maxlength:200}"/>
												<!-- 显示在网站的标题 --><s:text name="enterprise.system.website.display.title" />
				</td>
		   </tr>
	 		<tr<security:authorize ifAnyGranted="BASE_WEBSITE_PATH"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 主目录名称 --><s:text name="enterprise.system.website.main.path.name" />
					</center>
				</td>
				<td>
					<input type="text" size="60" name="webSite.htmlName" value="${webSite.htmlName}"  class="{required:true,maxlength:20}"/>
												<!-- 生成静态网页时的主目录,例如(kgdoqi) --><s:text name="enterprise.system.website.main.path.generation" />
				</td>
		   </tr>
			<tr<security:authorize ifAnyGranted="BASE_WEBSITE_PORT"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 站点端口 --><s:text name="enterprise.system.website.port" />
					</center>
				</td>
				<td>
					<input type="text" size="60" name="webSite.port" value="${webSite.port}"  class="{required:true,digits:true,maxlength:10}"/>
												<!-- 请不要修改 --><s:text name="enterprise.system.website.port.warn" />
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_COMPANY"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center>
						<!-- 公司名称 --><s:text name="enterprise.system.website.company.name" />
					</center>
				</td>
				<td>
					<input type="text"  size="60" name="webSite.company" value="${webSite.company}" class="{required:true,maxlength:30}"/>
												<!-- 可自定义 --><s:text name="enterprise.system.website.custom" />
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_CORYRIGHT"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center><!-- 版权信息 --><s:text name="enterprise.system.website.copyright" /></center>
				</td>
				<td>
					<textarea  cols="53" rows="10" name="webSite.copyright"  class="{required:true,maxlength:8000}">${webSite.copyright}</textarea>
					<!-- 显示在网页底端的内容,支持HTML(特殊效果需要专业人士编写) --><s:text name="enterprise.system.website.copyright.warn" />
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_ICP"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<center><!-- ICP备案 --><s:text name="enterprise.system.website.icp.record" /></center>
				</td>
				<td>
					<input type="text" size="60"  name="webSite.recodeCode" value="${webSite.recodeCode}"  class="{maxlength:40}"/>
					<!-- ICP备案信息 --><s:text name="enterprise.system.website.icp.record.information" />
				</td>
		   </tr>
		   <tr style="display:table-row;">
				<td>
				</td>
				<td>
					<input type="submit" value='<s:text name="common.word.submit" />' />
					<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()" style="display:none;"/>
				</td>
		   </tr>
		</table>
		</form>
		<div id="message" style="display:none">
			<s:actionmessage />
		</div>
			
	</body>
</html>

