<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
    <input type="hidden" value="${type}" id="addType" >
    <c:if test="${isReadName eq '0'}">
        <div class="tip-box">
            <a href="${ctx}/wx/member/toRealName" class="tip-cont">您还未实名认证，请先实名认证！</a>
        </div>
    </c:if>
        <form class="lock-form" action="" id="lock-form">
            <div class="form-row form-top-list">
                <label class="form-label">开户银行</label>
                <select class="form-select" name="bank_name" id="bankId">
                    <option value="">请选择开户银行</option>
                    <c:forEach var="bank" items="${resultVo.result}">
                       <option value="${bank.id}">${bank.bankName}</option>
                    </c:forEach>
                   
                </select>
                <i class="my-icon icon-more"></i>
            </div>
            <div class="form-list">
                <div class="form-row">
                    <label class="form-label">支行名称</label>
                    <input type="text" id="bankSub" name="branch_name" placeholder="请输入支行名称" class="row-input">
                </div>
                <div class="form-row">
                    <label class="form-label">省份城市</label>
                    <span class="shopInfo-right right bank-choose-row">
                        <input type="text" name="bank_city" class="shopInfo-input choose-city" placeholder="请选择" readonly="readonly" id="cityName">
                        <input type="hidden" name="bank_value" id="cityValue">
                        <i class="my-icon icon-more"></i>
                    </span>
                </div>
                <div class="form-row">
                    <label class="form-label">开户人</label>
                    <input type="text" id="hoder" name="people_name" readonly="readonly" placeholder="请输入开户人名称" class="row-input" value="${gjfMemberInfo.name}">
                </div>
                <div class="form-row">
                    <label class="form-label">卡号</label>
                    <input type="text" id="numberCard" name="id_num" placeholder="请输入卡号" class="row-input">
                </div>
            </div>
            <div class="error-wrap"></div>
            <div class="btn-box">
                <button type="submit" class="btn" id="addBack">同意协议并绑定</button>
            </div>
            <div class="agreement-box">
                <a href="${ctx}/wx/rule/service" class="agree-link">《巨惠云商O2O服务协议》</a>
            </div>
        </form>
    </div>
     <script type="text/javascript" src="${plugInPath}/larea/LArea.min.js?ver=1.0"></script>
    <script type="text/javascript" src="${plugInPath}/larea/LAreaData2.js?ver=1.0"></script>
    <script type="text/javascript">
    document.title = "绑卡";
    var area1 = new LArea();
    area1.init({
        'trigger': '#cityName',//触发选择控件的文本框，同时选择完毕后name属性输出到该位置
        'valueTo': '#cityValue',//选择完毕后id属性输出到该位置
        'keys': {
            id: 'value',
            name: 'text'
        },//绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
        'type': 2,
        'data': [provs_data, citys_data]
    });
    area1.value=[18,0];//控制初始位置，注意：该方法并不会影响到input的value，现在是广东省广州市越秀区


      jQuery.validator.addMethod("character", function(value,element){
        var tel = /^[\u4e00-\u9fa5]+$/;
          return this .optional(element) || (tel.test(value));
	  }, "请输入正确的名称");
	
	  jQuery.validator.addMethod("characterAll", function(value,element){//中英文
	        var tel = /^[\u4e00-\u9fa5A-Za-z]*$/;
	          return this .optional(element) || (tel.test(value));
	  }, "请输入正确的真实姓名");
	
	 /*  jQuery.validator.addMethod("cardId", function(value,element){
	        var regCredit = /^(\d)$/;
	          return this .optional(element) || (regCredit.test(value));
	  }, "请输入正确的银行卡卡号"); */
         $(".lock-form").validate({
            rules:{
                bank_name:{
                    required:true,
                },
                branch_name:{
                    required:true,
                    character:true,
                    rangelength:[2,20]
                },
                people_name:{
                    required:true,
                   /*  characterAll:true, */
                    rangelength:[2,20]
                },
                id_num:{
                    required:true,
                   /*  cardId:true */
                },
                bank_city:{
                    required:true
                }
            },
            messages:{
            	bank_name:{
                    required:"开户银行不能为空",
                },
                branch_name:{
                    required:"支行名称不能为空",
                    character:"请输入正确的支行名称",
                    rangelength:"支行名称2-20个字符",
                },
                people_name:{
                    required:"开户人不能为空",
                   /*  characterAll:"请输入正确的开户人姓名", */
                    rangelength:"开户人姓名2-20个字符",
                },
                id_num:{
                    required:"卡号不能为空",
                   /*  cardId:"请输入正确的银行卡卡号" */
                },
                bank_city:{
                    required:"省市不能为空"
                }
            },
            onfocusout:false,
            onkeyup:false,
            focusInvalid:false,
            showErrors: function(errorMap, errorList) {
                var msg = "";  
                $.each(errorList, function(i,v){  
                  msg = (v.message);  
                  return false; 
                });  
                if(msg!=""){
                    layer.open({
                        content: msg,
                        skin: 'msg',
                        style:'bottom:0;',
                        time: 1 //2秒后自动关闭
                    }); 
                } 
            },
            submitHandler:function(form){
            	var type="${type}"
              	  if($("#lock-form").valid()){
              		  $.ajax({
                    		url:"${ctx}/wx/trade/bindBank",
                    		method:"POST",
                    		data:{
                    			bankId:$("#bankId option:selected").val(),
                    			bankSub:$("#bankSub").val(),
                    		    holder:$("#hoder").val(),
                    		    bankCard:$("#numberCard").val(),
                    		  	cityValue:$("#cityValue").val(),
                    		},
                    		success:function(data){
                    			layer.open({
                                  content:data.msg,
                                  btn:['确定', '取消'],
                                  yes: function(index){
                                  	if(data.code==200){
                                  		if(type==3){
                                  			window.location.href="${ctx}/wx/trade/myBanksForPay?orderId=${orderId}&payMoney=${payMoney}&retUrl=${retUrl}";
                                  		}else{
                                  			window.location.href="${ctx}/wx/trade/myBanks?type="+$("#addType").val();	
                                  		}
                            				
                            			}else{
                            				layer.close(index)
                            			}
                                  },
                                  no:function(index){
                                      layer.close(index);
                                  }
                             });
                    		}
                    	})
                    }
            }
        }); 
    </script>
</body>
</html>