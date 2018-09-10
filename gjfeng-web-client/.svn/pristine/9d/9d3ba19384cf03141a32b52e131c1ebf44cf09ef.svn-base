<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <ul class="goods-list">
        <c:if test="${empty resultVo.result}">
			<div class="data-state-box">
		       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		       <p class="data-state-text">没有商品</p>
		   </div>
        </c:if>
        <c:if test="${not empty resultVo.result}">
        <c:forEach var="bean" items="${resultVo.result}" varStatus="status">
        	<li class="goods-li" data-id="${bean.id}">
                <div class="goods-item-choose" ontouchend="iconSelect(this)" data-value="0">
                    <i class="main-sprite icon-no"></i>
                </div>
                <div class="goods-right-box clearfix">
                    <div class="goods-item-1 left goods-rel">
                       <img src="${bean.imgUrl}" class="goods-img">
                       <span class="goods-state">
	                        <c:if test="${bean.status eq '1' && bean.aduitStatus eq '0'}">审核不通过</c:if>
	                        <c:if test="${bean.status eq '1' && bean.aduitStatus eq '2'}">待审核</c:if>
	                        <c:if test="${bean.status eq '0'}">已下架</c:if>
	                        <c:if test="${bean.status eq '1' && bean.aduitStatus eq '1'}">上架中</c:if>
	                        <c:if test="${bean.status eq '2' && bean.aduitStatus eq '1'}">违规下架</c:if>
                        </span>
                    </div>
                    <div class="goods-item-2 left">
                        <h3 class="goods-title">${bean.name}</h3>
                        <fmt:parseNumber value="${bean.score}" var="score"/>
                        <div class="goods-info clearfix">
                            <div class="goods-left left">
                                <c:if test="${score==0}">
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown"></i>
		                       </c:if>
		                       <c:if test="${score>0 && score<1}">
		                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       <c:if test="${score==1}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                        <c:if test="${score>1 && score<2}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                        <c:if test="${score==2}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       
		                        <c:if test="${score>2 && score<3}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       
		                       <c:if test="${score==3}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       
		                       <c:if test="${score>3 && score<4}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       
		                           
		                       <c:if test="${score==4}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown "></i>
		                       </c:if>
		                       
		                       <c:if test="${score>4 && score<5}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
		                       </c:if>
		                       
		                       <c:if test="${score==5}">
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
		                       </c:if>
                            </div>
                            <div class="goods-left-point left">${score}分</div>
                            <div class="goods-right right">已售${bean.salesNum}份</div>
                        </div>
                        <div class="goods-money clearfix">
                            <span class="goods-m-1 left"><i class="goods-front">¥</i>${bean.price}</span>
                            <span class="goods-m-2 left">门市价:¥${bean.marketPrice}</span>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
        </c:if>
        </ul>
        <c:if test="${not empty resultVo.result}">
        <div class="fixed-btn-box">
            <button type="button" class="fixed-btn">编辑</button>
        </div>
        </c:if>
        <div class="fixed-edit-box hidden">
            <ul class="fixed-edit-list">
                <li class="fixed-edit-item">
                    <a href="#" class="fixed-edit-link" id="edit-goods">
                        <i class="main-sprite icon-edit-2"></i>编辑商品
                    </a>
                </li>
                <li class="fixed-edit-item">
                    <a href="javascript:void(0);" class="fixed-edit-link" id="del-goods">
                        <i class="main-sprite icon-del"></i>下架商品
                    </a>
                </li>
                <li class="fixed-cancel-item" id="cancel-item">
                    <span class="fixed-cancel-text">取消</span>
                </li>
            </ul>
        </div>
        <div class="bg-cover hidden"></div>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "商品列表";
            $(".fixed-btn").on("click",function(e){//点编辑
                e.stopPropagation();
                var len = $(".goods-item-choose").find(".icon-yes").length;
                if(len<=0){
                    layer.open({
                        content: '请至少选中一项',
                        btn: '我知道了'
                    });
                }else{
                    $(".fixed-edit-box,.bg-cover").removeClass('hidden');
                }
            });

            $("#edit-goods").on("click",function(e){//点编辑商品
                e.stopPropagation();
                var len = $(".goods-item-choose").find(".icon-yes").length;
                if(len!=1){
                    layer.open({
                        content: '最多选中一项',
                        btn: '我知道了'
                    });
                }else{
                    var attr = $(".icon-yes").parents("li").attr("data-id");
                    window.location.href="${ctx}/wx/product/toUpdate/"+attr;
                }
            })

            $("#del-goods").on("click",function(e){//点删除商品
                e.stopPropagation();
                var list = [];
                layer.open({
                    content: '确定下架此商品？',
                    btn: ['确定', '取消'],
                    yes:function(index){
                        $(".icon-yes").each(function(index, el) {
                            var thisId = $(this).parents("li").attr("data-id");
                            list.push(thisId);
                        });
                        $.ajax({
            	            url: '${ctx}/wx/product/del',
            	            type: 'post',
            	            dataType: 'json',
            	            data: {
            	            	"ids":list,
            	            	},
            	            success:function(data){
            	            	layer.open({
                                    content: data.msg,
                                    btn: '我知道了'
                                });
            	                if(data.code==200){
            	                	layer.close(index);
            	                	window.location.reload();
                                    /* $(".icon-yes").parents("li").remove();
                                    $(".fixed-edit-box,.bg-cover").addClass('hidden'); */
            	                }
            	            },
            	            error:function(){
            	            	layer.open({
                                    content: "网络异常",
                                    btn: '我知道了'
                                });
            	            }
            	        })
                    },
                    no:function(index){
                        layer.close(index);
                        $(".fixed-edit-box,.bg-cover").addClass('hidden');
                    }
                });
            })

            $("#cancel-item").on("click",function(e){//取消
                e.stopPropagation();
                $(".fixed-edit-box,.bg-cover").addClass('hidden');
            })
            
            
            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                	ajaxLoad();
                }
            });
        });
        
        var pageNo=2;
    	var pageSize=10;
    	var reqType=1;
    
       function ajaxLoad(value){
	        $.ajax({
	            url: '${ctx}/wx/product/my',
	            type: 'get',
	            dataType: 'html',
	            data: {
	            	"pageNo":pageNo,
	            	"pageSize":pageSize,
	            	"reqType":reqType
	            	},
	            success:function(data){
	                if(null != data && "" != data){
	                	++pageNo;
	                	$(".goods-list").append(data);
	                }
	            },
	            error:function(){
					layer.open({
                        content: "网络异常",
                        btn: '我知道了'
                    });
	            }
	        })
       }
       
       function iconSelect(obj){
    	   var that=$(obj);
           var flag = that.attr("data-value");
           if(flag=='0'){//选中
              that.find(".main-sprite").removeClass('icon-no').addClass('icon-yes');
              that.attr("data-value","1");
           }else{//不选中
             that.find(".main-sprite").removeClass('icon-yes').addClass('icon-no');
             that.attr("data-value","0");
           }
       }
    </script>
</body>
</html>