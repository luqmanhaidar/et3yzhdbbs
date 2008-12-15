<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.ntsky.bbs.Symbols"%>
<%@ page import="com.ntsky.bbs.domain.Admin"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>BBS管理 —— 图片上传</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="../admin/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	//<![CDATA[
	if(opener==null){
		window.close();
	}	

	<%
		Admin admin = (Admin)session.getAttribute(Symbols.SESSION_ADMIN);
		if( admin == null ){
			out.print("window.close();");
		}
	%>
	function checkUpload(){
		var uploadFile = document.getElementById("file");
		if(uploadFile.value==""){
			alert("请选择上传文件！");
			return false;
		}else{
			return true;
		}
	}
	//]]>
</script>
</head>
<body style="margin: 0px;padding:0px;">
<div id="wrap">
<br/>
<!-- basic info begin -->
<%
	String element = HttpUtil.getParameter(request,"element");
	String preview = HttpUtil.getParameter(request,"preview");
	String action = HttpUtil.getParameter(request,"action");
%>
<div class="box1">
  <div class="title"> 图片上传 </div>
	<form action="<%=request.getContextPath()%>/common/system-<%if("uploadLogo".equals(action)){%>doUploadLogo.jsp<%}else{%>doUploadImage.jsp<%}%>" method="post" onSubmit="return checkUpload()" enctype="multipart/form-data">
    <input type="hidden" value="<%=element%>" name="element"/>
	<input type="hidden" value="<%=preview%>" name="preview"/>
    <div class="content">
      <div class="ibox">
        <div class="it">选择图片: * </div>
        <div class="iv">
          <input id="file" name="file" type="file" class="t" value="" style="padding:0px; "/>
          <span class="red">选择上传的图片</span> </div>
      </div>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="上传图片" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
<!-- upload message begin -->
<div class="box1 dashed">
  <h2>上传信息</h2>
  <ul>
    <li>文件后缀名为gif,jpg</li>
    <li>单个文件大小不大于2M</li>
  </ul>
</div>
<!-- end #upload message -->
</div>
</body>
</html>
