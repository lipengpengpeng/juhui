<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:if test="${not empty resultVo.result.agentList}">
<c:forEach items="${resultVo.result.agentList}" var="bean" >
  <li class="agentList-li">
      <div class="agentList-box cleafix">
          <div class="agentList-item left">
              <img src="${imagePath}/wx/o2o-shop/shop-icon.png" class="agentList-icon">
              <span class="shop-name"><mtag:cutString value="${bean.storeName}" size="2" mark="***"/></span>
          </div>
          <div class="agentList-item right">
              <mtag:cutString value="${bean.sellerMobile}" size="2" mark="***"/>
          </div>
      </div>
      <div class="agentList-box agentList-middle cleafix">
          <div class="agentList-mbox left">
              <p class="agentList-money">${bean.storeSaleTotalMoney}</p>
              <p class="agentList-money agentList-small">总营业额</p>
          </div>
          <div class="agentList-mbox left">
              <p class="agentList-money red-money">${bean.storeMonthSaleTotalMoney}</p>
              <p class="agentList-money agentList-small">当月营业额</p>
          </div>
      </div>
      <div class="agentList-box cleafix">
          <div class="agentList-item left">
              <span class="agent-sm-label">编码</span>
          </div>
          <div class="agentList-item right">
              <span class="agent-sm-num">${bean.storeNum}</span>
          </div>
      </div>
  </li>
  </c:forEach>
  </c:if>
        