<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.ntsky.framework.util.StringUtil"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>
<%@ page import="com.ntsky.framework.util.FileUtil"%>
<%--
	 图片上传页面
	 Author: 姚君林/ntsky
	 $Date: 2008/10/26 08:54:02 $
	 $RCSfile: deleteImage.jsp,v $
	 $Revision: 1.2 $
--%>
<%
	String contextPath = request.getContextPath();
	String element = HttpUtil.getParameter(request,"element");
	String realPath = pageContext.getServletContext().getRealPath("/");
	String imagePath = HttpUtil.getParameter(request,"imagePath");
	String preview = HttpUtil.getParameter(request,"preview");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<title>上传组件——图片删除</title>
<link rel="stylesheet" href="<%=contextPath%>/styles/default.css" type="text/css" media="all"/>
</head>
<body style="margin:0px;padding:0px;">
<!-- explain begin -->
<div class="box1 dashed">
  <div class="title">删除图片</div>
  <div class="content" style="padding-top:8px;border-top: 1px dashed silver;">
    <div style="text-align:center"><img src="../images/process.gif" /></div>
    <br/>
    <div style="text-align:center"></div>
  </div>
</div>
<!-- end #explain -->
<script type="text/javascript">
	//<![CDATA[
	if(opener!=null){
		if(opener.document.getElementById("<%=element%>").value==""){
			alert("请先上传图片");
			window.close();
		}
		else{
			alert("删除成功");
			<%
				//FileUtil.deleteFile(StringUtil.applyRelativePath(realPath,imagePath));
			%>
			// input 数据清空
			opener.document.getElementById("<%=element%>").value="";
			window.opener=null;window.close();
		}
	}
	//]]>
</script>

</body>
</html>
