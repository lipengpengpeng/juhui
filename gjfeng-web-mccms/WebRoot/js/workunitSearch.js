//通过ID得到元素
function sy_O(obj){
    return typeof(obj)=="string"?document.getElementById(obj):obj
}

//判断浏览器是否为IE
function isIE(){
   return (document.all && window.ActiveXObject && !window.opera) ? true : false;
} 

function sy_Box_Show(boxId,top){
    var box=sy_O(boxId);
    box.style.position= 'absolute';
    box.style.left = (((document.body.offsetWidth)/2+200)-parseInt(box.style.width)/2)+'px';
    box.style.top = top;
    box.style.display = 'block';
}

function sy_Box_Hide(boxId){
    var lay2=sy_O(boxId);
    if(lay2.style.display='block'){
        lay2.style.display='none';
    }
}

function searchWorkunit(id) {
		sy_Box_Hide("divModifyPwd");

		$("select[@name='wor.id']").val(id);
		
	}

$(document).ready(function(){

$("#keyword").keyup(function(){
		var keyword = $("#keyword").attr("value");
		$.ajax({
			type: "POST",
			url: "expert!searchWorkunit.action",
			data: "keyword="+keyword,
			success: function(msg){
				
				var str = msg.split(" ");
				var strIn;
				$(".sy_box_in ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					
					$(".sy_box_in ul").append("<li><a href='javascript:searchWorkunit("+strIn[0]+")' workunitID="+strIn[0]+">"+strIn[1]+"</a></li>");
					
				}
			
				
			}
			});
		});

});