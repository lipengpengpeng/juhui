<%@ page contentType="text/html;charset=UTF-8"%>
<table class="listTable2">
	<tr>
		<td width="50%">
			&nbsp;&nbsp;<!-- 共有 --><s:text name="common.pager.total" /> <s:property value="pager.rowCount" /> <!-- 条记录，每页行数： --><s:text name="common.pager.rowperpage" />
			<s:select name="pageSize" id="pageSize" list="pager.pageSizeIndexs" theme="simple" value="pager.pageSize"
					onchange="$('#pageNo').val('1');$('#form1').submit();" />
			<!-- 当前第  --><s:text name="common.pager.current" />
          	<s:select name="pageNo" id="pageNo" list="pager.pageNoIndexs" theme="simple"
					value="pager.pageNo" onchange="$('#form1').submit();" />
	 		<!-- 页  --><s:text name="common.pager.page" />
		</td>
		<td width="200px">
			<c:if test="${pager.pageNo == '1'}">
				<img src="${ctx}/image/jwc/first.gif" width="37" height="15" />
				<img src="${ctx}/image/jwc/back.gif" width="43" height="15" />
			</c:if>
			<c:if test="${pager.pageNo != '1'}">
				<a href="javascript:$('#pageNo').val('<s:property value="pager.firstPageNo" />');$('#form1').submit();">
					<img src="${ctx}/image/jwc/first.gif" width="37" height="15" /></a>
				<a href="javascript:$('#pageNo').val('<s:property value="pager.prePageNo" />');$('#form1').submit();">
					<img src="${ctx}/image/jwc/back.gif" width="43" height="15" /></a>
            </c:if>
            
            <c:if test="${pager.pageNo == pager.pageCount}">
            	<img src="${ctx}/image/jwc/next.gif" width="43" height="15" />
            	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" />
			</c:if>
			<c:if test="${pager.pageNo != pager.pageCount}">
				<a href="javascript:$('#pageNo').val('<s:property value="pager.nextPageNo" />');$('#form1').submit();">
					<img src="${ctx}/image/jwc/next.gif" width="43" height="15" /></a>
				<a href="javascript:$('#pageNo').val('<s:property value="pager.lastPageNo" />');$('#form1').submit();">
                	<img src="${ctx}/image/jwc/last.gif" width="37" height="15" /></a>
            </c:if>
		</td>
	</tr>
</table>