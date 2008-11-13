<html>
<head>
<title>BBS系统 : 排序顶层类</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<script type="text/javascript" src="../scripts/ntsky/validator.js"></script>

<script type="text/javascript">
	//<![CDATA[
		<#if warnMessage?if_exists!="">
  			alert("${warnMessage}");
		</#if>
	//]]>
</script>

<div class="box1 dashed">
    <ul>
      <li>设置论坛版块显示次序 : <span class="red">值越小,显示越靠前</span></li>
    </ul>
</div>
<br/>

<table align="center" cellpadding="0" class="ltable" cellspacing="1">
    <tr class="ltrt">
      <td width="35%">版块名称</td>
      <td width="38%">显示ID </td>
      <td width="27%">操作</td>
    </tr>
    <#list forums as forum>
    <form action="orderTopForum.action" method="post" id="orderTopForum" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="oldBranchId" value="${forum.branchId}"/>
    <tr class="ltrv">
      <td>${forum.name}</td>
      <td>
        <input name="branchId" type="text" class="t" value="${forum.branchId}"/>
      </td>
      <td width="27%"><input type="submit" name="Submit" value="设定" class="b"/></td>
    </tr>
     </form>
    </#list>
</table>

</body>
</html>
