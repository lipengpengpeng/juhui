<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>天天易购</title>
    <style>
*{margin:0;padding:0;box-sizing:border-box;}
.clearfix:after{font-size:0;display:block;visibility:hidden;clear:both;height:0;content:' ';}
.fl{float:left;}
.fr{float:right;}
ul{list-style:none;}
input{border:none;outline:none;}
body{background-color:rgb(246,246,246);}
.search-container{position:fixed;left:0;top:0;width:100%;line-height:36px;padding:5px 8px;background-color:transparent;}
.search-container .search-box{width:88%;padding:0 15px;background-color:#fff;border-radius:20px;}
.search-container .search-box .search-icon{display:inline-block;width:20px;height:20px;vertical-align:middle;}
.search-container .search-box input{padding-left:8px;font-size:15px;height:36px;line-height:36px;width:80%;}
.search-container .message-icon{width:25px;height:25px;margin-top:7px;}
.swiper-container img{width:100%;}
.nav-container,
.nav-container,
.fresh-container,
.activity-container{background-color:#fff;}
.nav-container .nav-item{float:left;width:19%;margin:10px 0;text-align:center;}
.nav-container .nav-item.large{width:22%;}
.nav-container .nav-item img{width:50px;height:50px;}
.nav-container .nav-item p{font-size:12px;color:#9a9595;}
.fresh-container{text-align:center;}
.fresh-container h2{margin:10px 0;font-size:18px;font-weight:400;letter-spacing:5px;}
.fresh-container .fresh-item{width:47%;margin-left:2%;margin-bottom:2%;}
.fresh-container .fresh-item img{width:100%;border-radius:5px;}
.activity-container{margin-top:10px;}
.activity-container .activity-title{float:left;width:100%;font-size:16px;font-weight:400;text-align:center;padding:12px;letter-spacing:2px;border-bottom:1px solid #eee;}
.activity-container .activity-item{width:50%;padding:10px 0;border-right:1px solid #eee;border-bottom:1px solid #eee;}
.activity-container .activity-item:nth-of-type(2n){border-right:none;}
.activity-item .activity-text{width:60%;padding-left:8px;line-height:22px;}
.activity-item .activity-thumb{width:40%;}
.activity-item .activity-thumb img{width:100%;padding-right:8px;}
.activity-item .activity-name{font-size:16px;}
.activity-item .activity-text p{font-size:12px;color:rgb(153,153,153);}
.activity-item .activity-text .activity-desc{color:#fff;font-size:10px;padding:2px 3px;border-radius:3px;}

/*文字渐变*/
.jianbian-text1{display:inline-block;background:-webkit-linear-gradient(left,rgb(255,203,127),rgb(254,104,51));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
.jianbian-text2{display:inline-block;background:-webkit-linear-gradient(left,rgb(254,111,126),rgb(243,4,185));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
.jianbian-text3{display:inline-block;background:-webkit-linear-gradient(left,rgb(254,104,27),rgb(243,25,6));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
.jianbian-text4{display:inline-block;background:-webkit-linear-gradient(left,rgb(87,131,236),rgb(178,63,229));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
.jianbian-text5{display:inline-block;background:-webkit-linear-gradient(left,rgb(255,109,137),rgb(255,82,48));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}

/*背景渐变*/
.jianbian-bg1{background:-webkit-linear-gradient(left,rgb(254,87,94),rgb(255,63,136));}
.jianbian-bg2{background:-webkit-linear-gradient(left,rgb(254,152,3),rgb(255,103,4));}
.jianbian-bg3{background:-webkit-linear-gradient(left,rgb(87,131,236),rgb(178,63,229));}
.jianbian-bg4{background:-webkit-linear-gradient(left,rgb(255,109,137),rgb(255,82,48));}
.ads-container{padding:10px 10px 0 10px;}
.ads-container img{width:100%;border-radius:3px;}
    </style>
</head>

<body>
    <header class="search-container clearfix">
        <div class="search-box fl">
            <img src="${imagePath}/wx/online-shop/newTtyg/search.png" alt="" class="search-icon">
            <input type="text" placeholder="搜索">
        </div>
        <img src="${imagePath}/wx/online-shop/newTtyg/message.png" alt="消息" class="fr message-icon">
    </header>
    <main>
        <section class="swiper-container">
            <div>
                <img onclick="findProId()" src="${imagePath}/wx/online-shop/newTtyg/tt_1.jpg" alt="会员专享">
            </div>
        </section>
        <nav class="nav-container clearfix">
            <c:forEach var="column" items="${indexColumns.result }" begin="0" end="4">
               <div onclick="getColumnPro('${column.linkUrl}${column.id}')" class="nav-item">
                 <img src="${column.pic2}" alt="" style="margin-left: 0.2rem;">
                 <p>${column.names}</p>
               </div>
            </c:forEach>
            <%-- <div href="" class="nav-item">
                <img src="${imagePath}/wx/online-shop/newTtyg/1.png" alt="">
                <p>自营商城</p>
            </div>
            <div href="" class="nav-item">
                <img src="${imagePath}/wx/online-shop/newTtyg/2.png" alt="">
                <p>积分兑换</p>
            </div>
            <div href="" class="nav-item large">
                <img src="${imagePath}/wx/online-shop/newTtyg/3.png" alt="">
                <p>积分+现金兑换</p>
            </div>
            <div href="" class="nav-item">
                <img src="${imagePath}/wx/online-shop/newTtyg/4.png" alt="">
                <p>积分抽奖</p>
            </div>
            <div href="" class="nav-item">
                <img src="${imagePath}/wx/online-shop/newTtyg/5.png" alt="">
                <p>充值中心</p>
            </div> --%>
        </nav>
        <section class="fresh-container">
            <h2 class="jianbian-text1">新人见面礼</h2>
            <ul class="clearfix">
                <li class="fresh-item fl">
                    <img src="${imagePath}/wx/online-shop/newTtyg/6.png" alt="">
                </li>
                <li class="fresh-item fl">
                    <img src="${imagePath}/wx/online-shop/newTtyg/7.png" alt="">
                </li>
                <li class="fresh-item fl">
                    <img src="${imagePath}/wx/online-shop/newTtyg/8.png" alt="">
                </li>
                <li class="fresh-item fl">
                    <img src="${imagePath}/wx/online-shop/newTtyg/9.png" alt="">
                </li>
            </ul>
        </section>
        <section class="activity-container clearfix">
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name jianbian-text2">超级返</h3>
                    <p>短袖连衣裙￥89</p>
                    <span class="activity-desc jianbian-bg1">每日10点上新</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/10.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name jianbian-text3">限量爆款</h3>
                    <p>每日2场抢好货</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/11.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">9块9包邮</h3>
                    <p>每日白菜价</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/12.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">理财返利</h3>
                    <p>返利限时提升</p>
                    <span class="activity-desc jianbian-bg1">月标返120元+1%</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/13.png" alt="">
                </div>
            </div>
        </section>
        <section class="activity-container clearfix">
            <h2 class="activity-title jianbian-text3">每·日·优·选</h2>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">全网秒杀</h3>
                    <p>3斤车厘子￥55抢</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/14.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">好店福利</h3>
                    <p>精品持续上新</p>
                    <span class="activity-desc jianbian-bg2">最高返30%</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/15.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">全球惠选</h3>
                    <p>FX券后84元3件</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/16.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">值·爆料</h3>
                    <p>巴拉巴拉超品日</p>
                    <span class="activity-desc jianbian-bg2">1件5折！</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/17.png" alt="">
                </div>
            </div>
        </section>
        <section class="activity-container clearfix">
            <h2 class="activity-title jianbian-text4">特·色·频·道</h2>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">旅行返利</h3>
                    <p>好房限时6折起</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/18.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">团购返利</h3>
                    <p>好吃的在这里</p>
                    <span class="activity-desc jianbian-bg3">专享返1.4%</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/19.png" alt="">
                </div>
            </div>
        </section>
        <section class="ads-container">
            <img src="${imagePath}/wx/online-shop/newTtyg/20.png" alt="">
        </section>
        <section class="activity-container clearfix">
            <h2 class="activity-title jianbian-text5">有·好·券</h2>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">外卖券</h3>
                    <p>饿了么满20减1</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/21.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">网购券</h3>
                    <p>京东苏宁考拉购</p>
                    <span class="activity-desc jianbian-bg4">苏宁手机880减110</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/22.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">快餐券</h3>
                    <p>任意消费送鸡翅</p>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/23.png" alt="">
                </div>
            </div>
            <div class="activity-item fl">
                <div class="activity-text fl">
                    <h3 class="activity-name">出行券</h3>
                    <p>滴滴ofo哈罗单车</p>
                    <span class="activity-desc jianbian-bg4">ofo免费汽车券</span>
                </div>
                <div class="activity-thumb fr">
                    <img src="${imagePath}/wx/online-shop/newTtyg/24.png" alt="">
                </div>
            </div>
        </section>
        
    </main>
    <%@include file="/common/wx-footer.jsp" %>
    <script>
    var imgHeight = document.querySelector('.swiper-container').offsetHeight,
        a = 0,
        cssText = '';
    var h = document.querySelector('.search-container');
    window.addEventListener('scroll', function() {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        a = t / imgHeight < 1 ? +(t / imgHeight).toFixed(1) : 1;
        if (t > imgHeight) {
            cssText = 'rgba(240,85,108,' + a + ')';
            h.style.backgroundColor = cssText;

        } else {
            cssText = 'transparent';
            h.style.backgroundColor = cssText;
        }
    }, false);
    
    
    //跳转到栏目商品列表
    function getColumnPro(url){
    	window.location.href=url;
    }
    
    function findProId(){
    	window.location.href="http://localhost:8180/gjfeng-web-client/wx/product/online/product/129881";
    }
        
    </script>
</body>

</html>