<%@ page contentType="text/html;charset=UTF-8"%>
<table class="listTable2">
	<tr>
		<td width="50%">
			&nbsp;&nbsp;<!-- 共有 -->共有 <s:property value="resultVo.result.rowCount" /> <!-- 条记录，每页行数： -->条记录，每页行数：
			<s:select name="pageSize" id="pageSize" list="resultVo.result.pageSizeList" theme="simple" value="resultVo.result.pageSize"
					onchange="$('#pageNo').val('1');$('#pageform2').submit();" />
			<!-- 当前第  -->当前第
          	<s:select name="pageNo" id="pageNo" list="resultVo.result.pageNoIndexs" theme="simple"
					value="resultVo.result.pageNo" onchange="$('#pageform2').submit();" />
	 		<!-- 页  -->页  
		</td>
		<td width="200px">
		    
			<c:if test="${resultVo.result.pageNo == '1'}">			 		   
				<img src="${ctx}/image/jwc/first.gif" width="37" height="15" />
				<img src="${ctx}/image/jwc/back.gif" width="43" height="15" />
			</c:if>
			<c:if test="${resultVo.result.pageNo != '1'}">
				<a href="javascript:$('#pageNo').val('<s:property value="resultVo.result.firstPageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/first.gif" width="37" height="15" /></a>
				<a href="javascript:$('#pageNo').val('<s:property value="resultVo.result.prePageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/back.gif" width="43" height="15" /></a>
            </c:if>
            
            <c:if test="${resultVo.result.pageNo == resultVo.result.pageCount}">
            	<img src="${ctx}/image/jwc/next.gif" width="43" height="15" />
            	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" />
			</c:if>
		
			<c:if test="${resultVo.result.pageNo != resultVo.result.pageCount}">
				<a href="javascript:$('#pageNo').val('<s:property value="resultVo.result.nextPageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/next.gif" width="43" height="15" /></a>
				<a href="javascript:$('#pageNo').val('<s:property value="resultVo.result.lastPageNo" />');$('#pageform2').submit();">
                	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" /></a>
            </c:if>
		</td>
	</tr>
</table>