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
		$("#linksForm").validate({
			 rules: { 
				names: { 
        			required: true, 
        			maxlength:10
    			},
    			orderColumn:{
        	    	required: true ,
        	    	number:true
        	    },
        	    frontNum:{
        	    	maxlength:20
        	    },
            	intro: {
                    required: true ,
			        maxlength:50
        	    },
        	    address:
        	    {
        	    	required: true ,
        	    	url:true,
			        maxlength:100
        	    }
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});	
</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 互动管理 - 友情链接管理 --><s:text name="enterprise.dynamic.friendlink.current.location" /> - 
				<!-- 修改友情链接信息 --><s:text name="enterprise.dynamic.friendlink.current.location.edit" />
			</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
			<div class="clear"></div>
		</div>
		<form action="enterpriseLinksAction!update.action" method="post"
			id="linksForm">
			<input type="hidden" name="enterpriseLinks.id" value="${enterpriseLinks.id}" />
			<table align="center" class="listTable3" width="90%">
				<tr>
					<td width="100px" class="pn-flabel">
						<!-- 链接名称 --><s:text name="enterprise.dynamic.friendlink.name" />
					</td>
					<td>
						<input type="text" name="names" size="100"
							value="${enterpriseLinks.names}" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 链接标识 --><s:text name="enterprise.dynamic.friendlink.id" />
					</td>
					<td>
						<input type="text" name="frontNum"
							value="${enterpriseLinks.frontNum}" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 链接排序 --><s:text name="enterprise.dynamic.friendlink.order" />
					</td>
					<td>
						<input type="text" name="orderColumn"
							value="${enterpriseLinks.orderColumn}" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 链接地址 --><s:text name="enterprise.dynamic.friendlink.url" />
					</td>
					<td>
						<input type="text" name="address" size="100"
							value="${enterpriseLinks.address}" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 链接简介 --><s:text name="enterprise.dynamic.friendlink.description" />
					</td>
					<td>
						<textarea rows="10" cols="100" name="intro">${enterpriseLinks.intro}</textarea>
					</td>
				</tr>
				<tr style="display:none;">
					<td class="pn-flabel">
						<!-- 显示时间--><s:text name="common.word.time.show" />
					</td>
					<td>
						<input type="text" name="initTime" onchange="checkDate()"
							id="initTime"
							value="<fmt:formatDate value="${enterpriseLinks.initTime}" pattern="yyyy-MM-dd"/>"
							readonly />
						<input id="starttimeBt" type="button" class="default_button"
							value='<s:text name="enterprise.collection.time.select" />' />
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
						<input type="text" name="endTime" onchange="checkDate()"
							id="endTime"
							value="<fmt:formatDate value="${enterpriseLinks.endTime}" pattern="yyyy-MM-dd"/>"
							readonly />
						<input id="endtimeBt" type="button" class="default_button"
							value='<s:text name="enterprise.collection.time.select" />' />
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
				<tr>
					<td class="pn-flabel">
						<!-- 状态--><s:text name="common.word.status" />
					</td>
					<td>
						<select name="state">
							<option value="1">
								<!-- 已启用 --><s:text name="common.word.has.opened" />
							</option>
							<option value="0"
								<c:if test="${enterpriseLinks.state == 0}">selected</c:if>>
								<!-- 已停用 --><s:text name="common.word.has.closed" />
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value='<s:text name="common.word.submit" />' />
					</td>
				</tr>
			</table>
			<s:token></s:token>
		</form>
	</body>
</html>

