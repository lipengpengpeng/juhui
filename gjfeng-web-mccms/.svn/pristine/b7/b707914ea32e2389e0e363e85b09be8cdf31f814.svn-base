<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<script language="javascript" type="text/javascript">
<!--
	var userAgent = "IE";
	
	if(window.navigator.userAgent.indexOf("Chrome")>=1){
		userAgent = "Chrome";
	}
	var uploadURL = $("#upload").val();
//-->
</script>
<script>
	$(document).ready(function(){
		$("#isPrimPhoto").val("${enterpriseNews.isPrimPhoto}");
		$("#title").focus();
		$("#epNewsForm").validate({
			 rules: {
			 	contents: {
        			maxlength:200000
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
		
		
		
	});		
	//当选择图片时发生
	function doSelectPhotoChanged(){
		filePath = document.getElementById("uploadImage").value;
		
		if(checkFileUpload(filePath)){
			if(userAgent != "Chrome"){
				  document.getElementById("uploadImage").value = "";
				  document.getElementById("imgShowTeacherPhoto").src=filePath;
				  document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
		 	}
		}
	}
	
	//图片上传验证
	function checkFileUpload()
	{
	  var path = $("#uploadImage").attr("value");
	  if(userAgent != "Chrome"){
	  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
	  	   		//图片类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
	  	   		$("#uploadImage").attr("value","");
		   		return false;
	  	   }
	  }else{	
		  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
		    //图片类型不正确，请上传正确的格式！
	  	   	alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
		    $("#uploadImage").attr("value","");
		    return false;
		  }
		  if(sy_GetImageToGetSize(path).fileSize>1024000){
		    //上传图片大小不能够超过1MB！
		    alert('<s:text name="enterprise.collection.message.photosize.error" />');
		    $("#uploadImage").attr("value","");
		    return false;
		  }
	   }
	  return true;
	}
	//提交表单前验证准备上传的文件
	function doBeforeSubmit(){
		if(document.getElementById("uploadImage").value!=""){
			if(!checkFileUpload()){
				return false;
			}
		}
	}
	
	//文件上传验证
	function checkDoFileUpload()
	{
	  var path = document.getElementById("upload").value;
	   if(userAgent != "Chrome"){
	  	   if(path.indexOf(".doc")<1 && path.indexOf(".xls")<1 &&  path.indexOf(".pdf")<1 &&  path.indexOf(".docx")<1 && path.indexOf(".rar")<1 && path.indexOf(".txt")<1 && path.indexOf(".zip")<1){
	  	   		//文件类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.filetype.error" />\n(doc,xls,pdf,docx,rar,txt,zip)');
	  	   		$("#upload").attr("value","");
		   		return false;
	  	   }
	  }else{
		  if(!sy_FilterFile(path,"doc,xls,pdf,docx,rar,txt,zip")){
		  	//文件类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.filetype.error" />\n(doc,xls,pdf,docx,rar,txt,zip)');
		    $("#upload").attr("value","");
		    return false;
		  }
		  if(sy_GetFileToGetSize(path).fileSize>4096000){
		    //上传文件大小不能够超过4MB！
		    alert('<s:text name="enterprise.collection.message.filesize.error" />');
		    $("#upload").attr("value","");
		    return false;
		  }
	  }
	  return true;
	}
	
	//提交表单前验证准备上传的文件
	function doFileBeforeSubmit(){
		if(document.getElementById("upload").value!=""){
			if(!checkDoFileUpload()){
				return false;
			}
		}
	}
</script>
	<style>
		.notview{
			display:none;
		}
	</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos"><!-- 当前位置: 内容管理 --><s:text name="enterprise.collection.current.location" /> - ${enterpriseNews.enterpriseColumn.names} - 
				<!-- 编辑新闻信息 --><s:text name="enterprise.collection.current.location.newsedit" /></div>
			<div class="ropt">
				<!-- 返回列表 -->
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="javascript:window.open('${ctx}/collection/epNewsAction!query.action?father=${enterpriseNews.enterpriseColumn.id}','_self')" />
			</div>
			<div class="clear"></div>
		</div>
 	 	<form action="epNewsAction!update.action" method="post" id="epNewsForm" enctype="multipart/form-data" method="post">
 	 	<%-- pager parameter begin--%>
		<input type="hidden" name="father" value="${father}"/>
		<input type="hidden" name="pageNo" value="${pageNo}"/>
		<input type="hidden" name="pageSize" value="${pageSize}"/>
		<%-- pager parameter end--%>
		<input type="hidden" name="enterpriseNews.id" value="${enterpriseNews.id}"/>
		<table  align="center" class="listTable3">
		
			<tr>
				<td class="pn-flabel"><font color="red">*</font><!-- 栏目 --><s:text name="enterprise.collection.column" /></td>
				<td>
					${enterpriseNews.enterpriseColumn.names}
					<input type="hidden" name="enterpriseNews.enterpriseColumn.id" value="${enterpriseNews.enterpriseColumn.id}"/>
				</td>
			</tr>
			<tr>
			<td class="pn-flabel"><!-- 选择文章类型: --><s:text name="enterprise.collection.select.newstype" /></td>
				<td>
				<!-- 当为下载模板的时候不上传图片，只上传文件 -->
				<input type="hidden" name="enterpriseNews.isPrimPhoto" value="${enterpriseNews.isPrimPhoto}"/>
						<select  id="isPrimPhoto" disabled="disabled">
							<option value="0"><!-- 普通新闻 --><s:text name="enterprise.collection.select.commonnews" /></option>
							<option value="2"><!-- 文件新闻 --><s:text name="enterprise.collection.select.filenews" /></option>
							<option value="1"><!-- 图片新闻 --><s:text name="enterprise.collection.select.photonews" /></option>
						</select>
				</td>
			</tr>
			<c:if test="${enterpriseNews.isPrimPhoto!=0}"><!-- 当为下载模板的时候 图片,文件都上传- -->
			<tr class="pic-box" >
				<td class="pn-flabel"><!-- 上传图片 --><s:text name="enterprise.collection.upload.photo" /></td>
				<td>
					<a href="${ctx}/upload/enterprice/${enterpriseNews.photo}"
								target="_blank" name="imgchange" id="imgchange"
								class="screenshot" rel="doSelectPhotoChanged()"><img
									id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
									width="96"
									src="
										<c:choose>
											<c:when test="${empty enterpriseNews.photo and enterpriseNews.isPrimPhoto==1}">
													${ctx}/upload/MissPhoto.JPG
											</c:when>
											<c:when test="${not empty enterpriseNews.photo and enterpriseNews.isPrimPhoto==1}">
													${ctx}/upload/enterprice/${enterpriseNews.photo}
											</c:when>
											<c:when test="${empty enterpriseNews.photo and enterpriseNews.isPrimPhoto==2}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:when test="${not empty enterpriseNews.photo and enterpriseNews.isPrimPhoto==2}">
													${ctx}/upload/enterprice/${enterpriseNews.photo}
											</c:when>
											<c:otherwise>
												${ctx}/upload/NoExpertPhoto.JPG
											</c:otherwise>
										</c:choose>
										"/>
							</a>
							<br />
							<input type="file" id="uploadImage" name="uploadImage" onchange="doSelectPhotoChanged();" size="30" value=""/>
							<br/>
							<label class="comments" style="color:red">
								<!-- （图片的大小请不要超过20M,建议为50K左右,图片显示的最优像素为：宽:185px * 高:152px。） -->
								*首页轮播图图片规格为：776Px * 300Px
							</label>
							<br/>
							<!-- 是否生成缩略图： --><s:text name="enterprise.collection.upload.photo.thumbnails" />
							<input type="radio" name="autoCutPhoto" value="1" />
							<!-- 是 --><s:text name="common.word.yes" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="autoCutPhoto" value="0" checked="checked"/>
							<!-- 否 --><s:text name="common.word.no" />
					
				</td>
			</tr>
			</c:if>
			<input type="hidden" name="photo" value="${enterpriseNews.photo}" readonly/>
			<c:if test="${enterpriseNews.isPrimPhoto==2}">
			<tr class="pic-box" >
				<td class="pn-flabel"><!-- 上传文件 --><s:text name="enterprise.collection.upload.file" /></td>
				<td>
					<a href="${ctx}/upload/enterprice/files/${enterpriseNews.fileInfo}"
								target="_blank"
								class="screenshot" rel="doSelectPhotoChanged()">
								<!-- 已经上传文件： --><s:text name="enterprise.collection.has.uploaded.file" />${enterpriseNews.fileInfo}
							</a>
							<br />
					<input type="file" id="upload" name="upload"
						onchange="doFileBeforeSubmit();" size="30"/>
						
				</td>
			</tr>
			
			</c:if>
			
			<tr>
				<td class="pn-flabel"><font color="red">*</font>
					<!-- 文档标题 --><s:text name="enterprise.collection.title" />
				</td><td><input type="text" name="enterpriseNews.title" value="${enterpriseNews.title}"  size="100" class="{required:true,maxlength:200}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_LINK"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 外部链接 --><s:text name="enterprise.collection.outside.link" />
				</td><td><input type="text" name="enterpriseNews.otherUrl" value="${enterpriseNews.otherUrl}" size="100" class="{maxlength:100,url:true}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_KEYWORD"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 关键字 --><s:text name="enterprise.collection.keyword" />/首页轮播图背景颜色
				</td><td><input type="text" name="enterpriseNews.priKey" value="${enterpriseNews.priKey}" size="100" class="{maxlength:500}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SHORTMETA"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 简短描述 --><s:text name="enterprise.collection.short.description" />
				</td>
				<td>
					<textarea rows="10" cols="77" name="enterpriseNews.shortMeta" class="{maxlength:500}">${enterpriseNews.shortMeta}</textarea>
				</td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_AUTHOR"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 文档作者 --><s:text name="common.word.author" />
				</td><td><input type="text" name="enterpriseNews.autor" value="${enterpriseNews.autor}" size="100" class="{maxlength:20}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SOURCE"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 来源 --><s:text name="enterprise.collection.reference" />
				</td><td><input type="text" name="enterpriseNews.source" value="${enterpriseNews.source}" size="100"  class="{maxlength:50}"/></td>
			</tr>
			
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SPECIAL"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 特殊设置--><s:text name="enterprise.collection.special.setting" />
				</td>
				<td>
					<div class="notview" style="float:left;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISTOP">display:block;</security:authorize>">
						<!-- 是否为置顶文档:--><s:text name="enterprise.collection.is.top" />
						<select name="enterpriseNews.isTop">
							<option value="0" ><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1" <c:if test="${enterpriseNews.isTop==1}">selected</c:if>><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISCOMMEND">display:block;</security:authorize>">
						<!-- 是否为推荐文档:--><s:text name="enterprise.collection.is.recommend" />
						<select name="enterpriseNews.isCommend">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1" <c:if test="${enterpriseNews.isCommend==1}">selected</c:if>><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISONLYCONTENT">display:block;</security:authorize>">
						<!-- 是否为单独内容样式:--><s:text name="enterprise.collection.is.only.content" />
						<select name="enterpriseNews.isOnlyContent">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1" <c:if test="${enterpriseNews.isOnlyContent==1}">selected</c:if>><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISSHOWONINDEX">display:block;</security:authorize>">
						<!-- 是否为首页滚动显示:--><s:text name="enterprise.collection.is.showed.onindex" />
						<select name="enterpriseNews.isShowedOnIndex">
							<option value="1"><!-- 是 --><s:text name="common.word.yes" /></option>
							<option value="0" <c:if test="${enterpriseNews.isShowedOnIndex==0}">selected</c:if>><!-- 否 --><s:text name="common.word.no" /></option>
						</select>
					</div>
				</td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 点击次数--><s:text name="enterprise.collection.click.times" />
				</td><td><input type="text"  size="100" name="enterpriseNews.clickTimes" value="0" value="${enterpriseNews.clickTimes}"/></td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel"><!-- 显示时间--><s:text name="common.word.time.show" /></td>
				<td>
					<input type="text" name="enterpriseNews.initTime" onchange="checkDate()" id="initTime" 
						value="<fmt:formatDate value="${enterpriseNews.initTime}" pattern="yyyy-MM-dd"/>" readonly/>
					<input id="starttimeBt" type="button" class="default_button" value='<s:text name="enterprise.collection.time.select" />'/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     : "initTime",
							button	  	   : "starttimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
				<!-- 至--><s:text name="enterprise.collection.time.to" />
					<input type="text" name="enterpriseNews.endTime" onchange="checkDate()" id="endTime" 
						value="<fmt:formatDate value="${enterpriseNews.endTime}" pattern="yyyy-MM-dd"/>" readonly/>
					<input id="endtimeBt" type="button" class="default_button" value='<s:text name="enterprise.collection.time.select" />'/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     : "endTime",
							button	  	   : "endtimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
				</td>
			</tr>

			<c:if test="${isAdmin==1}">
			<tr>
				<td class="pn-flabel"><!-- 状态--><s:text name="common.word.status" /></td>
				<td>
					<select name="enterpriseNews.state">
						<option value="1"><!-- 已通过--><s:text name="enterprise.collection.passed" /></option>
						<option value="0"  <c:if test="${enterpriseNews.state==0}">selected</c:if> ><!-- 不通过--><s:text name="enterprise.collection.not.pass" /></option>
						<option value="2"  <c:if test="${enterpriseNews.state==2}">selected</c:if> ><!-- 待审核--><s:text name="enterprise.collection.pending.approve" /></option>
					</select>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="pn-flabel"><!-- 内容--><s:text name="enterprise.collection.contents" /></td>
				<td>
					<FCK:editor instanceName="contents" width="870px" height="400px">
						<jsp:attribute name="value">
							${enterpriseNews.contents}
				    	</jsp:attribute>
					</FCK:editor>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;height:34px;">
					<input style="height:34px;" type="submit" value='<s:text name="common.word.submit" />' />
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

