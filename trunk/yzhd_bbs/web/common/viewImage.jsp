<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.ntsky.framework.util.StringUtil"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>

<%
	String contextPath = request.getContextPath();
	String imagePath = HttpUtil.getParameter(request,"logo");
	String element = HttpUtil.getParameter(request,"element");
	String preview = HttpUtil.getParameter(request,"preview");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>图片控件——图片预览</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="MSSmartTagsPreventParsing" content="true"/>
<script type="text/javascript">
	//<![CDATA[
		// 全屏显示图片
		window.onload = function(){
			window.resizeBy(document.images["image"].width,document.images["image"].height);
		}
	//]]>
</script>
</head>
<body bgcolor="#FFFFFF" style="margin:0px;padding:0px;">
<div align="center" style="margin:0px;padding:0px;">
<script type="text/javascript">
	//<![CDATA[
	if(opener!=null){
		if("<%=imagePath%>"==""){
			alert("请先上传图片");
			window.close();
		}
		else{
			// 输出Image
				<%
				if(imagePath.startsWith("http:")){
				%>
				document.write("<img src=\"<%=imagePath%>\" id=\"image\" style=\"border:0px;\"/>");
				<%
				}else{
				%>
				document.write("<img src=\"<%=contextPath%>/<%=imagePath%>\" id=\"image\" style=\"border:0px;\"/>");
				<%
				}
			%>
			
		}
	}
	//]]>
</script>
</div>
</body>
</html>
