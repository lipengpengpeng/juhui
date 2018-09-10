<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:forEach items="${dataMap}" var="beanMap">

	
		<td class="goods-td active-td pn-flabel">
		<label>${beanMap.key}</label>
		<select>
			<option value="">请选择</option>
			<c:forEach items="${beanMap.value}" var="bean">
				<option value="${bean[0]}">${bean[1]}</option>
			</c:forEach>
		</select>
		</td>

</c:forEach>

