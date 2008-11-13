<#import "/lib/layout.ftl" as layout>
<@layout.html title="设定金钱信息">
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<div class="box1">
  <div class="title"> <a href="#">积分设置</a></div>
  <form action="moneyConfig!set.action" method="post" id="setMoneyForm" onSubmit="return Validator.validate(this,'admin');">    <div class="content">
      <div class="ibox">
        <div class="it">发表主题增加积分: * </div>
        <div class="iv">
          <input type="text" name="addTopic" class="t" size="10" value="${propertyMap["addTopic"]}"/>
          <span class="red">设置发表主题增加的积分</span> </div>
      </div>
      <div class="ibox">
        <div class="it">发贴增加积分: * </div>
        <div class="iv">
          <input type="text" name="addPost" class="t" size="10" value="${propertyMap["addPost"]}"/>
          <span class="red">设置发贴、回复帖子增加的积分</span> </div>
      </div>
      <div class="ibox">
        <div class="it">设置精华帖增加积分: * </div>
        <div class="iv">
          <input type="text" name="eliteTopic" class="t" size="10" value="${propertyMap["eliteTopic"]}"/>
          <span class="red">设置设定精华帖增加的积分</span> </div>
      </div>
      <div class="ibox">
        <div class="it">删除帖子减少积分: * </div>
        <div class="iv">
          <input type="text" name="deleteTopic" class="t" size="10" value="${propertyMap["deleteTopic"]}"/>
          <span class="red">删除帖子减少积分</span> </div>
      </div>      
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设置金钱" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
</@layout.html>
