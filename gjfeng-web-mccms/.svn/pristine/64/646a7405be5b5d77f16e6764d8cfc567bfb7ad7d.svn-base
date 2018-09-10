<%@ page contentType="text/html;charset=UTF-8"%>
<table class="listTable2">
	<tr>
		<td width="50%">
			&nbsp;&nbsp;<!-- 共有 -->共有 <s:property value="pager2.rowCount" /> <!-- 条记录，每页行数： -->条记录，每页行数：
			<s:select name="pageSize2" id="pageSize2" list="pager2.pageSizeIndexs" theme="simple" value="pager2.pageSize"
					onchange="$('#pageNo2').val('1');$('#pageform2').submit();" />
			<!-- 当前第  -->当前第
          	<s:select name="pageNo2" id="pageNo2" list="pager2.pageNoIndexs" theme="simple"
					value="pager2.pageNo" onchange="$('#pageform2').submit();" />
	 		<!-- 页  -->页  
		</td>
		<td width="200px">
			<c:if test="${pager2.pageNo == '1'}">
				<img src="${ctx}/image/jwc/first.gif" width="37" height="15" />
				<img src="${ctx}/image/jwc/back.gif" width="43" height="15" />
			</c:if>
			<c:if test="${pager2.pageNo != '1'}">
				<a href="javascript:$('#pageNo2').val('<s:property value="pager2.firstPageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/first.gif" width="37" height="15" /></a>
				<a href="javascript:$('#pageNo2').val('<s:property value="pager2.prePageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/back.gif" width="43" height="15" /></a>
            </c:if>
            
            <c:if test="${pager2.pageNo == pager2.pageCount}">
            	<img src="${ctx}/image/jwc/next.gif" width="43" height="15" />
            	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" />
			</c:if>
			<c:if test="${pager2.pageNo != pager2.pageCount}">
				<a href="javascript:$('#pageNo2').val('<s:property value="pager2.nextPageNo" />');$('#pageform2').submit();">
					<img src="${ctx}/image/jwc/next.gif" width="43" height="15" /></a>
				<a href="javascript:$('#pageNo2').val('<s:property value="pager2.lastPageNo" />');$('#pageform2').submit();">
                	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" /></a>
            </c:if>
		</td>
	</tr>
</table>