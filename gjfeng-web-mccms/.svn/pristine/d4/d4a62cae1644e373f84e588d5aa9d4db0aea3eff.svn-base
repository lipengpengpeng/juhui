//通过ID得到元素
function sy_O(obj){
    return typeof(obj)=="string"?document.getElementById(obj):obj
}

//判断浏览器是否为IE
function isIE(){
   return (document.all && window.ActiveXObject && !window.opera) ? true : false;
} 

function sy_Box_Show(boxId,boffet){
	//alert("#"+boffet+"");
    var box=sy_O(boxId);
    box.style.position= 'absolute';
   // box.style.left = (((document.body.offsetWidth)/2+200)-parseInt(box.style.width)/2)+'px';
    //box.style.top = (((document.body.offsetHeight)/2+100)-parseInt(box.style.height)/2)+'px';
    box.style.left = ($("#"+boffet+"").offset().left)+50+'px';
    box.style.top = $("#"+boffet+"").offset().top+'px';
   //alert($("#showButton").offset().top);
    box.style.display = 'block';
}

function sy_Box_Hide(boxId){
    var lay2=sy_O(boxId);
    if(lay2.style.display='block'){
        lay2.style.display='none';
    }
}

function show_DIV(name,obj) {
	//alert("show_DIV"+obj);
	var workunitName = name;
	//alert("暑促"+workunitName);
	$("#divModifyPwd").remove();
	//var closeName = "'"+divModifyPwd+"'";
	var str='<div id="divModifyPwd" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="closes" style="cursor:hand;float:right;color:red">[关闭]</span><center>单位名称:<input type="text" id=keyword size="20"></center></div><div class="sy_box_in" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("divModifyPwd","showButton");
	
	$("#closes").bind("click",function(){
		sy_Box_Hide("divModifyPwd");
	});
	
	$("#keyword").bind("keyup",function(){
		var key = $(this).attr("value");
		
		searchKeyword(workunitName,key,obj);
     	});
	
}


function show_nation(name,obj) {
	//alert("show_DIV"+obj);
	var nationName = name;
	//alert("暑促"+workunitName);
	$("#nationDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="nationDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="nationClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入民族名称:<input type="text" id=nationkeyword size="20"></center></div><div class="box_nation" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("nationDiv","nationButton");
	
	$("#nationClose").bind("click",function(){
		sy_Box_Hide("nationDiv");
	});
	
	$("#nationkeyword").bind("keyup",function(){
		var nationkey = $(this).attr("value");
		
		searchNation(nationName,nationkey,obj);
		
     	});
	
}


function show_business(name,obj) {
	//alert("show_DIV"+obj);
	var businessName = name;
	//alert("暑促"+workunitName);
	$("#businessDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="businessDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="businessClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入职务名称:<input type="text" id=businesskeyword size="20"></center></div><div class="box_business" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("businessDiv","businessButton");
	
	$("#businessClose").bind("click",function(){
		sy_Box_Hide("businessDiv");
	});
	
	$("#businesskeyword").bind("keyup",function(){
		var businesskey = $(this).attr("value");
		
		searchbusiness(businessName,businesskey,obj);
		
     	});
	
}


function show_title(name,obj) {
	//alert("show_DIV"+obj);
	var titleName = name;
	//alert("暑促"+workunitName);
	$("#titleDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="titleDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="titleClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入职务名称:<input type="text" id=titlekeyword size="20"></center></div><div class="box_title" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("titleDiv","titleButton");
	
	$("#titleClose").bind("click",function(){
		sy_Box_Hide("titleDiv");
	});
	
	$("#titlekeyword").bind("keyup",function(){
		var titlekey = $(this).attr("value");
		
		searchtitle(titleName,titlekey,obj);
		
     	});
	
}


function show_degree(name,obj) {
	//alert("show_DIV"+obj);
	var degreeName = name;
	//alert("暑促"+workunitName);
	$("#degreeDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="degreeDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="degreeClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入职务名称:<input type="text" id=degreekeyword size="20"></center></div><div class="box_degree" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("degreeDiv","degreeButton");
	
	$("#degreeClose").bind("click",function(){
		sy_Box_Hide("degreeDiv");
	});
	
	$("#degreekeyword").bind("keyup",function(){
		var degreekey = $(this).attr("value");
		
		searchdegree(degreeName,degreekey,obj);
		
     	});
	
}

function show_expertType(name,obj) {
	//alert("show_DIV"+obj);
	var expertTypeName = name;
	//alert("暑促"+workunitName);
	$("#expertTypeDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="expertTypeDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="expertTypeClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入专家类型名称:<input type="text" id=expertTypekeyword size="20"></center></div><div class="box_expertType" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("expertTypeDiv","expertTypeButton");
	
	$("#expertTypeClose").bind("click",function(){
		sy_Box_Hide("expertTypeDiv");
	});
	
	$("#expertTypekeyword").bind("keyup",function(){
		var expertTypekey = $(this).attr("value");
		
		searchexpertType(expertTypeName,expertTypekey,obj);
		
     	});
	
}

function show_speciality(name,obj) {
	//alert("show_DIV"+obj);
	var specialityName = name;
	//alert("暑促"+workunitName);
	$("#specialityDiv").remove();
	//var closeName = "'"+divModifyPwd+"'";
	
	var str='<div id="specialityDiv" style="display: block; width: 400px;height:500px"><iframe style="position:absolute;width:100%; z-index:-1;" frameborder="0" src="about:blank"></iframe><div class="sy_box_out"><div class="sy_box_center" style="width:400"><span id="specialityClose" style="cursor:hand;float:right;color:red">[关闭]</span><center>输入现从事专业:<input type="text" id=specialitykeyword size="20"></center></div><div class="box_speciality" style="overflow-y:auto;width:100%;height:200px;<[if IE]>display:inline;<[endif]>"> <ul></ul></div></div></div>';

$("body").append(str);

	sy_Box_Show("specialityDiv","specialityButton");
	
	$("#specialityClose").bind("click",function(){
		sy_Box_Hide("specialityDiv");
	});
	
	$("#specialitykeyword").bind("keyup",function(){
		var specialitykey = $(this).attr("value");
		
		searchspeciality(specialityName,specialitykey,obj);
		
     	});
	
}


