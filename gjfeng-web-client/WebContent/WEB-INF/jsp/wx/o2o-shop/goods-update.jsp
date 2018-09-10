<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="release-form" action="${ctx}/wx/product/update" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="id" value="${product.result.id}">
            <div class="release-wrap-1">
                <div class="release-head">
                    <img src="${product.result.imgUrl}" class="release-img ">
                    <input type="file" capture="camera" accept="image/*" name="uploadRelease" onchange="previewImage(this,750,460)" class="release-upload">
                    <div class="release-top-box">
                        <i class="releaseImg-icon"></i>
                        <p class="releaseImg-text">选择商品图片</p>
                    </div>
                </div>
                <div class="release-item">
                    <textarea placeholder="输入商品标题" name="name" class="release-title">${product.result.name}</textarea>
                    <span class="release-span"><i class="release-num">0</i>/30</span>
                </div>
                <ul class="cont-list release-list">
                    <li class="clearfix">
                        <label class="shopInfo-label left">商品价格</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="price" class="shopInfo-input" placeholder="输入售卖的商品价格" value="${product.result.price}">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">门市价格</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="marketPrice" class="shopInfo-input" placeholder="输入商品在店的价格" value="${product.result.marketPrice}">
                        </span>
                    </li> 
                    <li class="clearfix high-box-textarea">
                        <label class="shopInfo-label left">有效期</label>
                        <span class="shopInfo-right right">
                            <textarea name="indate" class="shopInfo-textarea" placeholder="例:2016.7.18至2017.1.17(周末、法定节假日通用)">${product.result.indate}</textarea>
                        </span>
                    </li>
                </ul>
                <ul class="cont-list release-list" style="margin-bottom: 4.6rem">
                    <li class="clearfix">
                        <label class="shopInfo-label left">一级分类</label>
                        <span class="shopInfo-right right">
                            <a href="javascript:void(0);" class="release-link">
                                <select class="goods-select" name="goodsSelect1" onchange="ajaxColumn(this,1)">
                                    <option value="">请选择</option>
                                    <c:forEach items="${column.result}" var="bean">
                                       <c:if test="${bean.id==product.result.columnId.father}">
                                          <option value="${bean.id}" selected="selected">${bean.shortName}</option>
                                       </c:if>
                                        <c:if test="${bean.id!=product.result.columnId.father}">
                                          <option value="${bean.id}" >${bean.shortName}</option>
                                       </c:if>
	                                    
	                                </c:forEach>
	                            </select>
                                <i class="my-icon icon-more"></i>
                            </a>
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">二级分类</label>
                        <c:if test="${not empty product.result.columnId}">
                           <input type="hidden" value="${product.result.columnId.id}" id="clId"/>
                        </c:if>
                         <c:if test="${empty product.result.columnId}">
                           <input type="hidden" value="" id="clId"/>
                        </c:if>
                        <span class="shopInfo-right right">
                            <a href="javascript:void(0);" class="release-link">
                                <select class="goods-select column-class" name="columnId">
                                    <option value="">请选择</option>
                                </select>
                                <i class="my-icon icon-more"></i>
                            </a>
                        </span>
                    </li>
                    <li class="clearfix buy-needknow">
                        <label class="shopInfo-label left">购买须知</label>
                        <span class="shopInfo-right right">
                            <a href="javascript:void(0);" class="release-link" id="release-buy">
                            <c:if test="${not empty product.result.notice}">
                             	<i class="release-text" data-value="1">已填写</i>
                            </c:if>
                            <c:if test="${empty product.result.notice}">
                             	<i class="release-text" data-value="0">请点击填写</i>
                            </c:if>
                                <i class="my-icon icon-more"></i>
                            </a>
                        </span>
                    </li>
                </ul>
                <div class="fixed-btn-box">
                    <button type="submit" class="fixed-btn">立即发布</button>
                </div>
            </div>
            <div class="release-wrap-2 hidden">
                <textarea placeholder="编辑购买须知，让顾客更加放心购买" name="notice" class="buy-text">${product.result.notice}</textarea>
                <div class="fixed-btn-box">
                    <button type="button" class="fixed-btn" id="finished-btn">完成</button>
                </div>
            </div>
        </form>
    </div>
    <div class="loading-middle hidden">
       <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "修改商品";
            $(".release-title").on("input",function(){
                var that = $(this);
                var text = that.val();
                var len = text.length;
                if(len>30){
                    that.val(text.substr(0,30));
                }else{
                    $(".release-num").text(len);
                }
            })

            $(".buy-text").on("input",function(){//根据输入长度，改变输入框高度
                var height = $(this).outerHeight();
                var scrollheight = this.scrollHeight;
                if(scrollheight>height){
                    $(".buy-text").css('height',scrollheight+'px');  
                }
            })

            $("#release-buy").on("click",function(e){
                e.preventDefault();
                $(".release-wrap-2").removeClass("hidden");
                $(".release-wrap-1").addClass("hidden");
                $(document).scrollTop(0);
                $(".release-wrap-2").removeClass("release-wrap-right").addClass("release-wrap-active");
                $(".release-wrap-1").removeClass("release-wrap-active").addClass("release-wrap-left");
            });

            $("#finished-btn").on("click",function(e){
                e.preventDefault();
                if(validateRelease2()){
                    $("#release-buy").find(".release-text").attr("data-value","1");
                    $("#release-buy").find(".release-text").html("已填写");
                    $(".release-wrap-1").removeClass("hidden");
                    $(".release-wrap-1").removeClass("release-wrap-left").addClass("release-wrap-active");
                    $(".release-wrap-2").removeClass("release-wrap-active").addClass("release-wrap-right");
                    $(".release-wrap-2").addClass("hidden");
                }
            });

            function setErrorText(txt){
                var txt = txt || '';
                    txt&& layer.open({
                        content: txt,
                        skin: 'msg',
                        style:'bottom:0;',
                        time: 1 //2秒后自动关闭
                    });
            }

            function validateRelease2() {
                var v1 = $(".buy-text").val();
                if(v1==""){
                    setErrorText('购买须知不能为空');
                    return false;
                }else{
                    return true;
                }
            }

            $(".release-form").validate({
                rules:{
                    name:{
                        required:true,
                        rangelength:[1,100]
                    },
                    price:{
                        required:true,
                        number:true,
                        min:0
                    },
                    marketPrice:{
                        required:true,
                        number:true,
                        min:0
                    },
                    indate:{
                        required:true
                    },
                    goodsSelect1:{
                        required:true
                    },
                    columnId:{
                        required:true
                    }
                },
                messages:{
                    name:{
                        required:"请输入商品标题",
                        rangelength:"1-100个字符"
                    },
                    price:{
                        required:"请输入商品价格",
                        number:"必须为数字",
                        min:"输入值要大于0"
                    },
                    marketPrice:{
                        required:"请输入门市价格",
                        number:"必须为数字",
                        min:"输入值要大于0"
                    },
                    indate:{
                        required:"请输入有效期"
                    },
                    goodsSelect1:{
                        required:"请选择一级分类"
                    },
                    columnId:{
                        required:"请选择二级分类"
                    }
                },
                onfocusout:false,
                onkeyup:false,
                focusInvalid:false,
                onclick:false,
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
                    var num = $("#release-buy").find(".release-text").attr("data-value");
                    if(num == "1"){
                    	$(".loading-middle").removeClass("hidden");
                     	$("#finished-btn").attr("disabled","disabled");
                    	form.submit();
                    }else{
                        layer.open({
                            content: '请先填写购买须知',
                            skin: 'msg',
                            style:'bottom:0;',
                            time: 1 //2秒后自动关闭
                        });
                    }
                }  
            })
        });
        
        ajaxColumn(0,0);
        function ajaxColumn(obj,type){
        	var clId=$("#clId").val()
        	var id="";
        	if(type==0){
        		id=${product.result.columnId.father}
        	}
        	if(type==1){
        	   id=$(obj).find("option:selected").val();
        	}
			if(null == id || "" == id){
				return false;
			}
			$(".column-class").empty();
			$.ajax({
				type : "get",
				url : "${ctx}/wx/column/subColumn/"+id,
				dataType : "json",
				data : null,
				success : function(json) {
					var htmlstr='<option value="">请选择</option>';
					console.log(json);
					$(json.result).each(function(i,v){
						if(clId==v.id){
							htmlstr+='<option value="'+v.id+'" selected="selected">'+v.shortName+'</option>';
						}else{
							htmlstr+='<option value="'+v.id+'">'+v.shortName+'</option>';
						}
						
					});
					$(".column-class").append(htmlstr);
				},
				error : function() {
					alert('网络异常');
				}
			});
		}
    </script>
</body>
</html>