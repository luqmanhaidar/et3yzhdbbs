<html>
<head>
<title>警告信</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<style type="text/css">
	.warns { text-align: left; margin-left: 3%; }
	.warns li { color: #ff0000; }
</style>
<!-- explain begin -->
<div class="box1 dashed">
  <div class="title" style="background:#FFFFFF">警告信息:</div>
  <div class="content" style="padding-top:8px;border-top: 1px dashed silver;">
    <div style="text-align:left;">
      <div class="warns">
        <ol style="margin:0px;padding:0px;">
          <#if fieldErrors?exists>
			<@ww.iterator value="fieldErrors">
				<li><@ww.property value="value[0]"/></li>
			</@ww.iterator>
          </#if> 
          <#if actionMessage?exists>
          	${actionMessage?if_exists}
          </#if>	
        </ol>
      </div>
    </div>
    <br/>
    <div style="text-align:center">
      <input type="button" value="返 回" name="back" class="b" onclick="history.back(-1);"/>
    </div>
  </div>
</div>
<!-- end #explain -->
</body>
</html>
