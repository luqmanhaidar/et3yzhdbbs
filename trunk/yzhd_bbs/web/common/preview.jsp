<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>贴子预览</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
</head>
<body style="padding:0px;">
  <!-- preview post begin -->
  <div class="box1" style="margin:8px;background-color: #ffffff;">
    <div class="title" style="text-align:center;"> 贴子预览 </div>
    <div class="content">
      <div class="ibox">
        <div class="it"><strong>标题:</strong> </div>
        <div class="iv"> <%=HttpUtil.getParameter(request,"title")%> </div>
      </div>
      <div class="ibox">
        <div class="it"><strong>心情:</strong> </div>
        <div class="iv"> <%=HttpUtil.getParameter(request,"mood")%> </div>
      </div>
      <div class="ibox" style="border-bottom:0px;">
        <div class="it"><strong>内容:</strong> </div>
        <div class="iv" style="height:160px;"> <%=HttpUtil.getParameter(request,"content")%> </div>
      </div>
    </div>
  </div>
  <!-- end preview post -->
  <div style="text-align:center;">
    <input type="submit" value="关闭" name="agree" class="b" onclick="window.opener=null;window.close()"/>
  </div>
  <!-- end #footer -->
</body>
</html>
