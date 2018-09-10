<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
			
			.textCenter td {
				text-align: center;
			}
		</style>

		<script>

			$(document).ready(function(){
	
				$("#serviceName").focus();
	
				$("#resetButtom").click(function(){
						$("#serviceName").attr("value","");
						$("#initTime").attr("value","");
						$("#endTime").attr("value","");
				});

				if("${showMesscat}"!=null&&"${showMesscat}"!="undefined"&&"${showMesscat}"!=""){
			        $.blockUI({ 
			        	message: $('#message')
			        	}) 
					setTimeout($.unblockUI, 2000); 
				}

			});

			function backupDatabase() {
				//确定备份数据库吗?
				if(confirm('<s:text name="enterprise.data.confirm.backup.database" />')) {
					return true;
				}
				return false;
			}
			
			function backupDataFile() {
				//确定备份数据文件吗，可能这要耗费很长的时间?
				if(confirm('<s:text name="enterprise.data.confirm.backup.datafile" />')) {
					return true;
				}
				return false;
			}
			
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
		</script>

	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 系统数据管理 - 数据备份 --><s:text name="enterprise.data.current.location" />
			</div>
			<div class="ropt">
			</div>
		</div>
		
		<div class="rhead">
			<div>
				<a href="dataHandlerAction!backupDatabase.action"
							onclick="return backupDatabase();">
								<!-- 一键备份数据库 --><s:text name="enterprise.data.click.backup.database" /></a>
				<a href="dataHandlerAction!backupFile.action"
							onclick="return backupDataFile();">
								<!-- 一键备份数据文件 --><s:text name="enterprise.data.click.backup.datafile" /> </a>
			</div>
			<div class="ropt">
			</div>
		</div>

		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>
					<!-- 序号  --><s:text name="common.word.id" />
				</td>
				<td>
					<!-- 备份文件名 --><s:text name="enterprise.data.backup.name" />
				</td>
				<td>
					<!-- 日期  --><s:text name="common.word.date" />
				</td>
				<td>
					<!-- 备份类型 --><s:text name="enterprise.data.backup.type" />
				</td>
				<td>
					<!-- 状态  --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作  --><s:text name="common.word.handle" />
				</td>
			</tr>

			<c:forEach var="historyList" items="${historyList}" varStatus="status">
				<tr class="textCenter">
					<td>
						${status.count}
					</td>
					<td>
						${historyList.name}
					</td>
					<td>
						<fmt:formatDate value="${historyList.dateTime}" pattern="yyyy-MM-dd" />
					</td>
					<td>
						${historyList.type}
					</td>
					<td>
						<c:if test="${historyList.status=='1'}">
							<!-- 正常  --><s:text name="common.word.has.opened" />
						</c:if>
						<c:if test="${historyList.status=='0'}">
							<!-- 已删除  --><s:text name="common.word.has.deleted" />
						</c:if>
					</td>
					<td>
						<c:if test="${historyList.status=='1'}">
							<a href="${ctx}/data/download.action?fileName=${historyList.path}"> <!-- 下载 --><s:text name="common.word.download" /></a>
							&nbsp; &nbsp;
							<a href="dataHandlerAction!delete.action?id=${historyList.id}"
								onclick="return doYouWantToDel();">
									<!-- 删除  --><s:text name="common.word.delete" /></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty historyList}">
			<s:form action="dataHandlerAction!showHistory.action"
				namespace="/data" method="post" name="form1" theme="simple">
				
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>	
				
			</s:form>
		</c:if>

	</body>
</html>

