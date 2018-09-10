<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
         <div class="sub-nav">
             <ul class="sub-cont-ul clearfix">
                 <li class="sub-cont-li left sub-cont-active" data-value="1">距离最近</li>
                 <li class="sub-cont-li left" data-value="2">人气优先</li>
                 <li class="sub-cont-li left" data-value="3">价格最低</li>
                 <li class="sub-cont-li left" data-value="4">最新发布</li>
             </ul>
         </div>
         <ul class="like-list">
              <div class="loading-middle hidden">
                 <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
             </div>
             <div class="loading-bottom hidden">
                 <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
                 <span class="loadBottom-text">加载中...</span>
             </div>
         </ul>
    </div>
    <script type="text/javascript">
    	document.title = "搜索商品";
    	var pageNo=1;
    	var pageSize=10;
        $(function(){
        	 ajaxLoad("1",1);
            $(".sub-cont-li").on("touchend",function(){
                $(this).addClass("sub-cont-active").siblings().removeClass('sub-cont-active');
                var value = $(this).attr("data-value");
                $(".loading-middle").removeClass("hidden");
                pageNo=1;
                if(value=="1"){//距离近
                	ajaxLoad(value,1);
                }else if(value== "2"){//人气高
                	ajaxLoad(value,1);
                }else if(value =="3"){//价格高
                	ajaxLoad(value,1);
                }else if(value =="4"){//最新
                	ajaxLoad(value,1);
                }
            })


            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    var value = $(".sub-cont-active").attr("data-value");
                    ajaxLoad(value,0);
                }
            });
        })
        
        function ajaxLoad(orderType,status){
        	$.ajax({
                url: '${ctx}/wx/product/o2o/products',
                type: 'POST',
                dataType: 'html',
                data: {
                	"pageNo":pageNo,
                	"pageSize":pageSize,
                	"columnId":"${columnId}",
                	"orderType":orderType,
                	"columnType":"${columnType}",
                	"likeValue":"${likeValue}"
                },
                success:function(data){
                	 if(null != data && "" != data){
                     	++pageNo;
                     	if(status==1){
                     		$(".like-list").empty();
                     	}
                     	$(".like-list").append(data);
                     }
                    $(".loading-bottom").addClass('hidden');
                },
                error:function(){
                	$(".loading-bottom").addClass('hidden');
                }
            });
        }
    </script>
</body>
</html>