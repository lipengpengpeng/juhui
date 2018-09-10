<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form action="" class="openShop-form" id="storeAddress">
            <div class="shopInfo-title">编辑店铺信息</div>
            <ul class="cont-list shopInfo-list">
                <li class="clearfix">
                    <label class="shopInfo-label left">联系电话</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="sellerMobile" class="shopInfo-input" placeholder="请输入手机号码" value="${resultVo.result.sellerMobile}">
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">省份城市</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="shopCity" class="shopInfo-input choose-city" placeholder="请选择" readonly="readonly" id="cityName" value="${resultVo.result.provinceId.province},${resultVo.result.cityId.city},${resultVo.result.areaId.area}">
                        <input type="hidden" name="cityValue" id="cityValue">
                        <i class="my-icon icon-more"></i>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">详细地址</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="addressDetail" class="shopInfo-input" placeholder="街道、楼牌号码等" value="${resultVo.result.addressDetail}">
                    </span>
                </li>
            </ul>
            <div class="btn-box">
                <button type="submit" class="btn">保存</button>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="${jsPath}/wx/o2o-shop/LArea.min.js"></script>
    <script type="text/javascript" src="${jsPath}/wx/o2o-shop/LAreaData2.js"></script>
    <script type="text/javascript">
    	document.title = "编辑店铺";
        $(function() {      
            var area = new LArea();
            area.init({
                'trigger': '#cityName',//触发选择控件的文本框，同时选择完毕后name属性输出到该位置
                'valueTo': '#cityValue',//选择完毕后id属性输出到该位置
                'keys': {
                    id: 'value',
                    name: 'text'
                },//绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area.value=[18,0,4];//控制初始位置，注意：该方法并不会影响到input的value，现在是广东省广州市天河区
            
            jQuery.validator.addMethod("isMobile", function(value,element) {
            	var length = value.length;
            	var mobile = /^1[3|4|5|7|8][0-9]{9}$/;
            	
            	return this.optional(element) || (mobile.test(value));

            	}, "输入的手机号码不正确");
                      
            $(".openShop-form").validate({
                rules:{
                	sellerMobile:{
                        required:true,
                        isMobile:true
                    },
                    shopCity:{
                        required:true,
                    },
                    addressDetail:{
                        required:true,    
                    }
                },
                messages:{
                	sellerMobile:{
                        required:"请输入手机号码",
                    },
                    shopCity:{
                        required:"请选择店铺身份城市",
                    },
                    addressDetail:{
                        required:"请输入店铺详细地址",    
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
                	$.ajax({
                		url:"${ctx}/wx/store/updateAddressInfo",
                		method:"POST",
                		data: $(form).serialize(),
                		success:function(data){
                			if(data.code==200){
                				layer.open({
                                    content: '修改成功',
                                    btn: ['确定'],
                                    yes: function(index){//点击去认证页面，index为该特定层的索引
                                        location.href='${ctx}/wx/store/my';
                                        layer.close(index);
                                    },
                                   
                                });  
                			}else{
                				layer.open({
                                    content: data.msg,
                                    btn: ['确定'],
                                    yes: function(index){//点击去认证页面，index为该特定层的索引                                     
                                        layer.close(index);
                                    },
                                   
                                });  
                			}
                			
                		}
                	})
                  	//ajaxAddOrEdit(form,"${ctx}/wx/store/updateAddressInfo");
                  	//window.location.href="${ctx}/wx/store/my"
                }
                
            })
        });
    </script>
</body>
</html>