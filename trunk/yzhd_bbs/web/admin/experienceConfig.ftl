<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>开源论坛 : 积分设定</title>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<!-- basic info begin -->
<div class="box1">
  <div class="title"> <a href="#">积分设置</a></div>
  <form action="setExperience.action" method="post" id="setExperience" onSubmit="return Validator.validate(this,'..');">
    <div class="content">
      <div class="ibox">
        <div class="it">发新帖增加积分: * </div>
        <div class="iv">
          <input type="text" name="addTopic" class="t" size="10" value="${propertyMap["addTopic"]}"/>
          <span class="red">设置发表帖子增加的积分值</span> </div>
      </div>
      <div class="ibox">
        <div class="it">回复增加积分: * </div>
        <div class="iv">
          <input type="text" name="addPost" class="t" size="10" value="${propertyMap["addPost"]}"/>
          <span class="red">设置发表帖子增加的积分值</span></div>
      </div>
      <div class="ibox">
        <div class="it">精华增加积分: * </div>
        <div class="iv">
          <input type="text" name="eliteTopic" class="t" size="10" value="${propertyMap["eliteTopic"]}"/>
          <span class="red">设置设定精华帖增加的积分值</span></div>
      </div>
      <div class="ibox">
        <div class="it">被删帖扣除积分: * </div>
        <div class="iv">
          <input type="text" name="deleteTopic" class="t" size="10" value="${propertyMap["deleteTopic"]}"/>
          <span class="red">设置删除帖子扣除的积分值</span></div>
      </div>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设置积分" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
</body>
</html>
