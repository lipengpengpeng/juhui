<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
      <input name="orderId" type="hidden" id="orderId" value="${orderId}"/>
       <input name="payMoney" type="hidden" id="payMoney" value="${payMoney}"/>
       <input name="type" type="hidden" id="typePay" value="${type}"/>
       <input name="type" type="hidden" id="mobile" value="${mobile}"/>
       <input name="type" type="hidden" id="retUrl" value="${retUrl}"/>
    <ul class="bank-list">
      
    	<c:if test="${not empty resultVo.result}">
        
            <c:forEach var="bank" items="${resultVo.result}">
              <li class="bank-li">
                <a href="${ctx}/wx/trade/choiceBankDrawCash?bankId=${bank.id}" class="bank-link" style="background-color: ${bank.bankColor}">
                 <input type="hidden" value="${bank.id}" class="backId"/>
                    <div class="bank-top">
                        <img src="${ctx}/common/image/bank/${bank.bankPic}" class="bank-icon">
                        <span class="bank-name">${bank.bankName}</span>
                        <span class="bank-type">${bank.bankSub}</span>
                    </div>
                   <div class="bank-num">${fn:replace(fn:substring(bank.bankCard,0,fn:length(bank.bankCard)-4),fn:substring(bank.bankCard,0,fn:length(bank.bankCard)-4),"*** *** ***")} ${fn:substring(bank.bankCard,fn:length(bank.bankCard)-4,fn:length(bank.bankCard))}</div>
                </a>
            </li>
            </c:forEach>             
      
        </c:if>
       </ul>
        <div class="btn-add">
            <a class="bank-btn" href="${ctx}/wx/trade/toBindBank?type=${type}&orderId=${orderId}&retUrl=${retUrl}&payMoney=${payMoney}"><i class="icon-add">+</i>添加银行卡</a>
        </div>
        <div class="fixed-edit-box hidden">
            <ul class="fixed-edit-list">
               <li class="fixed-edit-item">
                    <a href="javascript:void(0);" class="fixed-edit-link" id="submit-card" choseId="">
                        <i class="main-sprite icon-edit-2"></i>提现到该银行卡
                    </a>
                </li> 
                <li class="fixed-edit-item">
                    <a href="javascript:void(0);" class="fixed-edit-link" id="del-card" delBackId="">
                        <i class="main-sprite icon-del"></i>删除银行卡
                    </a>
                </li>
                <li class="fixed-cancel-item" id="cancel-item">
                    <span class="fixed-cancel-text">取消</span>
                </li>
            </ul>
        </div>
        <div class="bg-cover hidden"></div>
    </div>
</body>
</html>
<script type="text/javascript">
        $(function(){
        	document.title = "银行卡列表";
            $(".bank-link").on("click",function(e){
                e.preventDefault();
                var attr = $(this).find(".backId").val();
                $(".fixed-edit-box,.bg-cover").removeClass('hidden');
                $("#del-card").attr("delBackId",attr);
                $("#submit-card").attr("choseId",attr)
            });

             $("#submit-card").on("click",function(){//点提现银行卡
            	 var bankId=$("#submit-card").attr("choseId")           	
                window.location.href = '${ctx}/wx/trade/choiceBankDrawCash?bankId='+bankId+"&type="+$("#typePay").val()+"&orderId="+$("#orderId").val()+"&payMoney="+$("#payMoney").val()+"&retUrl="+$("#retUrl").val();
            }) 

            $("#del-card").on("click",function(e){//点删除银行卡
                $(".fixed-edit-box,.bg-cover").addClass('hidden');
                e.stopPropagation();
                var dataId = $(this).attr("delBackId");
                layer.open({
                    content: '确定删除此银行卡？',
                    btn: ['确定', '取消'],
                    yes:function(index){
                        //console.log(dataId);
                        $.ajax({
                            url: '${ctx}/wx/trade/deleteMemBank',
                            type: 'POST',
                            dataType: 'json',
                            data: {bankId: dataId},
                            success:function(data){
                            	if(data.code=200){
                                     location.replace(location.href);
                            	}else{
                            		 layer.open({
                                         content: data.msg,
                                         btn: '我知道了'
                                     });
                            	}                                                              
                            },
                            error:function(){
                                layer.close(index);
                                layer.open({
                                    content: '异常',
                                    btn: '我知道了'
                                });
                            }
                        })
                        
                    },
                    no:function(index){
                        layer.close(index);
                    }
                });
            })

            $("#cancel-item").on("click",function(e){//取消
                e.stopPropagation();
                $(".fixed-edit-box,.bg-cover").addClass('hidden');
            })
        })
 </script>