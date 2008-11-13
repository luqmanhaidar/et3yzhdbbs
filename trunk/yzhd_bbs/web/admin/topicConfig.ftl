<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>开源论坛 : 帖子资料设定</title>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<!-- basic info begin -->
<div class="box1">
  <div class="title"> <a href="#">帖子设置</a></div>
  <form action="topicConfig!set.action" method="post" id="setTopic" onSubmit="return Validator.validate(this,'..');">
    <div class="content">
      <div class="ibox">
        <div class="it">帖子最小字数(字节): *</div>
        <div class="iv">
          <input name="minWord" type="text" class="t" value="${propertyMap["minWord"]}" size="10"/> 
          <span class="red">帖子中包含最少字数</span> </div>
      </div>
      <div class="ibox">
        <div class="it">帖子最大字数(字节): * </div>
        <div class="iv">
		  <input type="text" name="maxWord" value="${propertyMap["maxWord"]}" class="t" size="10"/>
		  <span class="red">帖子中包含最大字数</span></div>
      </div>
      <div class="ibox">
        <div class="it">投票最大选项数:* </div>
        <div class="iv">
          <input type="text" name="ballotOptionNum" value="${propertyMap["ballotOptionNum"]}" class="t" size="10"/>
          <span class="red">投票帖的答案数</span> </div>
      </div>	
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设定帖子属性" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
</body>
</html>
