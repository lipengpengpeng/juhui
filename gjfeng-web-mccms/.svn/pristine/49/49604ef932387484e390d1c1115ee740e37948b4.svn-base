//ddaccordion菜单插件初始化
ddaccordion.init({
	headerclass: "expandable", //Shared CSS class name of headers group that are expandable
	contentclass: "categoryitems", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click" or "mouseover
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [7], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		$(".arrowlistmenu .menuheader").prepend('<div class="menu_icon"></div>');
		$(".arrowlistmenu .menuheader").wrapInner('<div class="menu_title"></div>');
		$(".arrowlistmenu .categoryitems li").prepend('<div class="menu_item_icon"></div>');
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

$(document).ready(function (){ 
	//layout init
	$layout = $("body").layout({ 
		north__paneSelector:"#top",
		west__paneSelector:"#menu",
		center__paneSelector:"#content",
		south__paneSelector:"#footer",
		resizable:false,
		slidable:false,
		west__slidable:false,
		west__slide_width:5,
		//west__slideTrigger_open:"mouseover",
		togglerLength_open:0,
		togglerLength_closed:"100%",
		north__size:93,
		west__size:270,
		west__minSize:200,
		west__maxSize:350,
		spacing_open:0,
		spacing_closed:5,
		center__onresize:"contentLayout.resizeAll",
		south__size:32,
		west__onopen:function(){
			$("#layout-menu-open").css("display","none");
		},
		west__onclose:function(){
			$("#layout-menu-open").css("display","block");
		},
		north__onopen:function(){
			$("#layout-top-open").css("display","none");
		},
		north__onclose:function(){
			$("#layout-top-open").css("display","block");
		},
		south__onopen:function(){
			$("#layout-footer-open").css("display","none");
		},
		south__onclose:function(){
			$("#layout-footer-open").css("display","block");
		}
	});
	
	window.contentLayout = $("#content").layout({ 
		center__paneSelector:"#container",
		north__paneSelector:"#tabsFrame",
		north__spacing_open:0,
		south__spacing_open:0,
		north__size:22
	});
	
	//layout add button
	$layout.addOpenBtn("#layout-top-open","north");
	
	$layout.addOpenBtn("#layout-menu-open","west");
	
	$layout.addOpenBtn("#layout-footer-open","south");
	
	$layout.addCloseBtn("#layout-top-close","north");
	
	$layout.addCloseBtn("#layout-menu-close","west");
	$layout.addCloseBtn("#menu .line","west");
	
	$layout.addCloseBtn("#layout-footer-close","south");
	
	
	$(".ui-layout-toggler").mouseover(function(){
		$(this).css("background-color","#f5f5f5");
	});
	$(".ui-layout-toggler").mouseout(function(){
		$(this).css("background-color","#cccccc");
	});
	
	find = function(tabId){
		len = $tabs.find("a").length;
		for(i = 0;i < len;i ++){
			href = $tabs.find("a").get(i).href;
			if(href == tabId)
			{
				return i;
			}
		}
		return -1;
	}
	
	window.onresize = function(){
		//setTimeout("tabsMove()",200);
	}
	window.appointLeft = 0;
	window.interval = null;
	window.moveStart = function(speed){
		if(interval == null)
		{
			window.interval = window.setInterval("onmove("+speed+")",200);
		}
	}
	window.moveStop = function(){
		if(window.interval != null)
		{
			window.clearInterval(window.interval);
			window.interval = null;
		}
	}
	window.onmove = function(speed){
		window.appointLeft += speed;
	}
	$("#rightMove").click(function(){
		window.appointLeft -= 100;
	});
	$("#rightMove").mousemove(function(){
		window.moveStart(-30);
	});
	$("#rightMove").mouseout(function(){
		window.moveStop();
	});
	
	$("#leftMove").click(function(){
		window.appointLeft += 100;
	});
	$("#leftMove").mousemove(function(){
		window.moveStart(30);
	});
	$("#leftMove").mouseout(function(){
		window.moveStop();
	});
});