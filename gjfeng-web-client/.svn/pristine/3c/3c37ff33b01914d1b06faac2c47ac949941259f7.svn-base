/**
 * 加飞猫平台常用工具js--後台
 * author：karhs
 * date：2016-06-01 13:14
 * */

//判断字符串是否为空
function stringIsEmpty(str){
	if(""==str || null==str || "undefined"==str){
		return true;	
	}
	return false;
}

//string类型判断
function stringPara(str){			//字符串判断
	var obj=JSON.stringify(str);
	if(null == obj || "null" == obj || "{}" == obj || "" == obj || "undefinded" == obj || "[object Object]" == obj){
		return "";
	}
	var string = str.replace(/\"/g, "");
	return string;
}

//double类型判断
function doublePara(obj){		//double型判断
	obj=obj.toString();
	if(null == obj || "null" == obj || "{}" == obj || "" == obj || "undefinded" == obj || "[object Object]" == obj){
		return 0.00;
	}
	return obj;
};

/**
 * 时间戳转换为时间格式--方法1
 * @param timeValue
 * @param num
 * @returns {String}
 */
function timestampToDate(timeValue,num){
	var obj = timeValue.toString();
	if(null == obj || "null" == obj || "{}" == obj || "" == obj || "undefinded" == obj || "[object Object]" == obj){
		return "";
	}
	var date=new Date(timeValue);
	time = date.getFullYear()+"-"+checkTime(date.getMonth()+1)+"-"+checkTime(date.getDate());
	if(null != num && 1 == num){
		time += " "+checkTime(date.getHours())+":"+checkTime(date.getMinutes())+":"+checkTime(date.getSeconds())
	}
	return time;
}

/**
 * 时间戳转换为时间格式--方法2
 * @param timeValue
 * @param pattern
 * @returns {String}
 */
function timestampToDate2(timeValue,pattern){
	var obj = timeValue.toString();
	if(null == obj || "null" == obj || "{}" == obj || "" == obj || "undefinded" == obj || "[object Object]" == obj){
		return "";
	}
	var date=new Date(timeValue);
	return formatDate(date,pattern);
}

/**
 * 时间转换格式
 * @param date
 * @param pattern
 * @returns {String}
 */
function formatDate(date,pattern){
	date=new Date(date);
	var year=date.getUTCFullYear();     
    var month=date.getUTCMonth()+1;     
    var day=date.getUTCDate();
    var hour=date.getUTCHours();     
    var minute=date.getUTCMinutes();     
    var second=date.getUTCSeconds(); 
	var time="";
    if(null == pattern || "" == pattern){
		time += year+"-"+checkTime(month)+"-"+checkTime(day);
		time += " "+checkTime(hour)+":"+checkTime(minute)+":"+checkTime(second)
	}else if('yyyy' == pattern){
		time += year;
	}else if('yyyy-MM' == pattern){
		time += year+"-"+checkTime(month);
	}else if('yyyy-MM-dd' == pattern){
		time += year+"-"+checkTime(month)+"-"+checkTime(day);
	}else if('yyyy-MM-dd HH:mm' == pattern){
		time += year+"-"+checkTime(month)+"-"+checkTime(day);
		time += " "+checkTime(hour)+":"+checkTime(minute);
	}else if('yyyy-MM-dd HH:mm:ss' == pattern){
		time += year+"-"+checkTime(month)+"-"+checkTime(day);
		time += " "+checkTime(hour)+":"+checkTime(minute)+":"+checkTime(second)
	}else if('MM-dd' == pattern){
		time += checkTime(month)+"-"+checkTime(day);
	}else if('MM-dd HH:mm' == pattern){
		time += checkTime(month)+"-"+checkTime(day);
		time += " "+checkTime(hour)+":"+checkTime(minute);
	}else if('HH:mm:ss' == pattern){
		time += checkTime(hour)+":"+checkTime(minute)+":"+checkTime(second)
	}else if('HH:mm' == pattern){
		time += checkTime(hour)+":"+checkTime(minute);
	}
    return time;
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}

/**获取星期
 * @param date1
 * @param type	0:默认获取  1:返回今天、昨天等值
 */
function getWeek(date1,type){
	if(type == 1){
		date1 = new Date(date1);
		var date2 = new Date();   
		
		var year1 = date1.getUTCFullYear();
		var month1 = date1.getUTCMonth()+1;
		var day1 = date1.getUTCDate();
		
		var year2 = date2.getUTCFullYear();
		var month2 = date2.getUTCMonth()+1;
		var day2 = date2.getUTCDate();
		if(year1==year2 && month1==month2){
			if(day1==day2){
				return "今天";
			}else if((day1-day2==-1)){
				return "昨天";
			}
		}
	}
	var day = new Date(Date.parse(date1));   //需要正则转换的则 此处为 ： var day = new Date(Date.parse(date.replace(/-/g, '/')));
	var today = new Array('周日','周一','周二','周三','周四','周五','周六');  
	var week = today[day.getUTCDay()];  
	return week;
}


/**
 * @param srcStr	要处理的数字
 * @param nAfterDot	小数位数
 * @returns {___anonymous5821_5829}
 */
function FormatNumber(srcStr,nAfterDot){
	var srcStr,nAfterDot;
	var resultStr,nTen;
	srcStr = ""+srcStr+"";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".",0);
	if (dotPos == -1){
		resultStr = srcStr+".";
		for (i=0;i<nAfterDot;i++){
			resultStr = resultStr+"0";
			}
		return resultStr;
		}
	else{
		if ((strLen - dotPos - 1) >= nAfterDot){
			nAfter = dotPos + nAfterDot + 1;
			nTen =1;
			for(j=0;j<nAfterDot;j++){
				nTen = nTen*10;
				}
			resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
			return resultStr;
			}
		else{
			resultStr = srcStr;
			for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
				resultStr = resultStr+"0";
				}
			return resultStr;
			}
		}
}

/**
 * 切割千分位100,122,2.00	方法一
 * @param numStr
 * @returns
 */
function formatNumber(numStr){  
	 var b = /([-+]?\d{3})(?=\d)/g;
     return numStr.replace(b, function($0, $1){
         return $1 + ',';
     });
	}  

/**
 * 切割千分位	方法二
 * @param s 值
 * @param n 精度
 * @return
 */
function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
    t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    var amount = t.split("").reverse().join("") + "." + r;
    return amount;
    }

/**
 * 切割千分位	方法三
 * @param num
 * @param del
 * @param o
 * @returns {String}
 */
function RetainedDecimalPlaces (num, del, o) {//值：num 小数位：del o为true返回值为正数,否则为负数
    try {
        num += "";
        num = parseFloat(num).toFixed(del); //保留小数并四舍五入
        var str = "";
        if (!o) {
            if (num.substring(0, 1) == "-") str = "-";
        }
        //清除字符串中的非数字 非.字符
        num = num.replace(/[^0-9|\.]/g, "");
        //清除字符串开头的0
        if (/^0+/) num = num.replace(/^0+/, "");
        //为整数字符串在末尾添加.0000
        if (!/\./.test(num)) num += ".0000";
        //字符以.开头时，在开头添加0
        if (/^\./.test(num)) num = "0" + num; num += "0000"; //在字符串末尾补零
        if (del == 2) num = num.match(/\d+\.\d{2}/)[0];
        if (del == 4) num = num.match(/\d+\.\d{4}/)[0];
        //千位符
        while (/\d{4}(\.|,)/.test(num)) //符合条件则进行替换
            num = num.replace(/(\d)(\d{3}(\.|,))/, "$1,$2"); //每隔3位添加一个
        return str + num;
    } catch (e) {
        console.log(e);
    }
}

/**
 * js获取当前页面URL
 */
function locationUrl(type){
	if(type == "domain"){
		return document.domain;
	}else if(type == "pathname"){
		//设置或获取对象指定的文件名或路径。
		return window.location.href;
	}else if(type == "href"){
		//设置或获取整个 URL 为字符串。
		return window.location.href;
	}else if(type == "port"){
		//设置或获取与 URL 关联的端口号码。
		return window.location.port;
	}else if(type == "protocol"){
		//设置或获取 URL 的协议部分。
		return window.location.protocol;
	}else if(type == "hash"){
		//设置或获取 href 属性中在井号“#”后面的分段。
		return window.location.hash;
	}else if(type == "host"){
		//设置或获取 location 或 URL 的 hostname 和 port 号码。
		return window.location.host;
	}else if(type == "search"){
		//设置或获取 href 属性中跟在问号后面的部分。
		return window.location.search;
	}else if(type == "param"){
		//获取变量的值(截取等号后面的部分)
	    var url = window.location.search;
	    var loc = url.substring(url.lastIndexOf('=')+1, url.length);
	    return loc;
	}
}

//获取选择的图片路径
function getObjectURL(file) { //获取图片路径
		var url = null ; 
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
}