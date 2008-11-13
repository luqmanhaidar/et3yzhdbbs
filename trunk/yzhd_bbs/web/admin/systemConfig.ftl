<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>开源论坛 :系统设定</title>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">

<!-- basic info begin -->
<div class="box1">
  <div class="title">  系统设置 </div>
  <form action="systemConfig!set.action" method="post">
    <div class="content">
      <div class="ibox">
        <div class="it" style="width:30%">是否允许统计: *</div>
        <div class="iv">
          <input name="isOpenStat" type="radio" value="true" ${propertyMap["isOpenStat"]} <#if propertyMap["isOpenStat"]=="true">checked="true"</#if>/>
          是
          <input name="isOpenStat" type="radio" value="false" <#if propertyMap["isOpenStat"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“是”将记录用户访问网站的信息，您可以在访问记录管理中对其进行管理</span> </div>
      </div>
      <div class="ibox">
        <div class="it" style="width:30%">是否记录操作日志: *</div>
        <div class="iv">
          <input name="isOpenActLog" type="radio" value="true" <#if propertyMap["isOpenActLog"]=="true">checked="true"</#if>/>
          是
          <input name="isOpenActLog" type="radio" value="false" <#if propertyMap["isOpenActLog"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“是”将记录用户对网站的操作，您可以在操作日志管理中对其进行管理</span> </div>
      </div>
	  <div class="ibox">
        <div class="it" style="width:30%">发生错误是否发送邮件给管理员: *</div>
        <div class="iv">
          <input name="isSendMail" type="radio" value="true"  <#if propertyMap["isOpenActLog"]=="true">checked="true"</#if>/>
          是
          <input name="isSendMail" type="radio" value="false" <#if propertyMap["isOpenActLog"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“是”当用户操作发生ERROR错误的时将错误信息发送给系统维护人员</span> </div>
      </div>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设 定" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->

</body>
</html>
