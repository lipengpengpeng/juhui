<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<c:forEach items="${resultVo.result}" var="bean">		
		<option areaId="${bean.areaId}" value="${bean.id}">${bean.area}</option>
	</c:forEach>				      
					   