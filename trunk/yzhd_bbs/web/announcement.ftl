<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${announcement.title}</title>
<#include "includes/head.ftl">
</head>
<body style="padding:8px;">
  <div class="box1" style="margin:0px;">
    <div class="title" style="text-align:center">${announcement.title}</div>
    <div class="content" style="background-color: #ffffff;">
      <div style="padding:8px;"> ${announcement.content}</div>
    </div>
    <div class="box3" style="border-top: 1px solid #c2c2c5; text-align:right">
		<span>发布人: ${announcement.author}</span><span style="padding-left:20px;">发布时间 : ${announcement.dateCreated}</span>
    </div>
  </div>
  <!--<div style="text-align:center;padding:8px;"><input type="submit" value="关闭" name="agree" class="b" onclick="window.opener=null;window.close()"/></div>-->
</body>
</html>
