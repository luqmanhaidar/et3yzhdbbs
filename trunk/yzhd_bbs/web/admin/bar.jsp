<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<title>打开关闭菜单</title>
<meta http-equiv=content-type content="text/html; charset=utf-8">
<style type="text/css">
body {
	margin-top: 0px; margin-left: 0px;
}
.jd {
	font-size: 9pt; cursor: hand; color: #0074c7; font-family: webdings;
}
</style>
<script type="text/javascript" src="../scripts/ntsky/global.js"></script>
<script type="text/javascript">
	left_weight = 160;
	var slideout = function() {
		if(Browser.isNS) return;
		if (left_weight >= 160) 
			return;
		left_weight = 160;
		document.getElementById("baraction").innerHTML = '3';
		parent.layout.cols = '160,13,*';
	}
	var slidein = function(){
		if(Browser.isNS) return;
		left_weight = 0;
		document.getElementById("baraction").innerHTML = '4';
		parent.layout.cols = '0,13,*';
	}
	
	// 页面初始
	window.onload = function(){
		if(Browser.isNS) {
			document.getElementById("baraction").innerHTML = '&nbsp;';
		}
	}
</script>
</head>
<body style="margin:0px;">
<table class="jd" onMouseOver="slideout();" onClick="slidein();" 
height="100%" cellspacing="0" cellpadding="0" width="12">
  <tbody>
    <tr valign="middle">
      <td class="jd" style="border-right: 1px dashed silver;"><div id="baraction" style="margin:0px;padding:0px;">3</div></td>
    </tr>
  </tbody>
</table>
</body>
</html>
