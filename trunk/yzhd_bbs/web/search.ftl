<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛 : 检索条件设定</title>
<#include "includes/head.ftl">
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "检索条件设定">
  <#include "includes/quick.ftl">
  <!-- search info begin -->
  <div class="box1 mtq">
   <form method="post" action="search.action">
    <div class="title" style="text-align:center"> 搜索目标 </div>
    <div class="content">
      <div class="ibox">
        <div class="it" style="width:50%"><strong>关键字: * </strong> </div>
        <div class="iv">
          <input type="text" name="keyword" class="t" size="50"/>
        </div>
      </div>
      <div class="ibox">
        <div class="it" style="width:50%"><strong>搜索类型: * </strong> </div>
        <div class="iv">
          <input type="radio" name="type" value="title" checked="true"/>主题 <input type="radio" name="type" value="username"/>作者  <input type="radio" name="type" value="content"/>内容 
        </div>
      </div>      
      <div class="title" style="text-align:center"> 搜索选项 </div>
      <div class="content">
        <div class="ibox">
          <div class="it" style="width:50%"> 论坛:
	      <select name="forumId" id="forumId">
	        <option value="0">全部论坛</option>
	      	<#list forums as forum>
	        <option value="${forum.id}">${""?left_pad((forum.depth-1)*2,"—")} ${forum.name}</option>
	        </#list>
	      </select>
            <select name="time">
              <option value="0">全部时间</option>
              <option value="1">1 天</option>
              <option value="2">2 天</option>
              <option value="7">1 周</option>
              <option value="30">1 个月</option>
              <option value="90">3 个月</option>
              <option value="180">6 个月</option>
              <option value="365">1 年</option>
            </select>
            <input type="radio" name="way" value="before" checked="checked"/>
            之后
            <input type="radio" name="way" value="after" />
            之前 </div>
          <div class="iv"> 排序类型:
            <select class="post" name="sort">
              <option value="dateCreated">发表时间</option>
              <option value="lastPostTime">回复时间</option>
              <option value="title">主题</option>
              <option value="username">作者</option>
              <option value="views">查看次数</option>
            </select>
            <input name="order" type="radio" value="desc" checked="checked"/>
            降序
            <input name="order" type="radio" value="asc" />
            升序 </div>
        </div>
      </div>
    </div>
    <div class="box3" style="text-align:center">
        <div>
          <input type="submit" value="检索" name="search" class="b"/>
          <input onclick="history.back(-1)" type="reset" value="返回"  class="b"/>
        </div>
    </div>
    </form>
  </div>
  <!-- end #search info -->
<#include "includes/footer.ftl">  
</body>
</html>
