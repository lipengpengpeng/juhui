<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<!-- meta http-equiv="refresh" content="3; url=http://www.baidu.com" />  -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">
			html,body{margin: 0;padding: 0;font-family: 'Microsoft YaHei'}
			.lucky-bg{background-color:#5c95d5;width:100%;height: 100vh;position: relative;}
			.lucky-text{text-align: center;color:#fff;}
			.lucky-500{text-align: center;padding-top:100px;font-size: 100px;font-weight: bold;color:#fff;}
			.lucky-down{width: 100%;position: fixed;bottom: 0;left: 0;text-align: center;}
			.lucky-down img{width:82%;margin: 0;padding: 0;vertical-align: middle;}
		</style>
		<script language="javascript">
		
		function reduce(){
			var num = document.getElementById('num').innerHTML;
			if(num==0){
				window.history.go(-1);
			}else{
				num--;
				document.getElementById('num').innerHTML = num;
			}
			setTimeout("reduce()",1000);
		}
		reduce();
		</script>
		<title>错误页面</title>
	</head>
	<body>	
	<div class="lucky-bg">
		<div class="lucky-500">
			500
			<!-- <img src="404.png"> -->
		</div>
		<div class="lucky-text">
			<p class="lt-1">操作有误!</p>
			<p class="lt-2"><span id="num">5</span>秒后跳转到上一页，如没有跳转，请<a href="javascript:void(0);" onclick="history.go(-1)">点击这里</a></p>
		</div>
		<div class="lucky-down">
			<img src="/image/error/waken.png">
		</div>
	</div>
	

	</body>
</html>
