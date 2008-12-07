<#assign isVote = request.getParameter("isVote")?exists>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改主题 : ${topic.title}</title>

<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/tooltip/tooltip.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Basic",".","400","650");
		Forms.selected("categoryId",${topic.categoryId});
		Forms.selected("mood",${topic.mood});
	};
	
		function onCheck(){
	    var obj=document.getElementById("title")
		if(obj.value.length>50)
		{
				alert("标题不能超过50个字符！");
				obj.focus();
				return false;
		}
		if(obj.value.length<2)
		{
				alert("标题不能小于2个字符！");
				obj.focus();
				return false;
		}
		
		<#if topic.isVote==1>
		var prflag=false;
		var num1=${ballotOptionNum.size()};
		for(i=0;i<num1;i++){
			var pollResult= document.getElementById("optionText"+(i+1)).value;
			if(pollResult.length>0){
			prflag=true;
			}
		}		
		if(!prflag){
			alert("投票选项不能全部为空");
			return false;
		}
	</#if>
		
	var oEditor = FCKeditorAPI.GetInstance('content') ;
    var oDOM = oEditor.EditorDocument ;

    var iLength ;

    if ( document.all )		// If Internet Explorer.
    {
	    iLength = oDOM.body.innerText.length ;
    }
    else					// If Gecko.
    {
	    var r = oDOM.createRange() ;
	    r.selectNodeContents( oDOM.body ) ;
	    iLength = r.toString().length ;
    }
	if(iLength==0)
	{
		alert("请写上内容");
		return false;
	}
	if(iLength<${propertyMap["minWord"]})
	{
		alert("内容不能小于${propertyMap["minWord"]}个字符！");
		return false;
	}
	if(iLength>${propertyMap["maxWord"]})
	{
		alert("内容不能大于${propertyMap["maxWord"]}个字符！");
		return false;
	}
	

	}
	//]]>
</script>
</head>
<body>
<#include "includes/header.ftl">
<#include "includes/front_top.ftl">
<#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; <a href=\"topic.action?topicId="+topic.id+"\" title=\""+topic.title+"\">"+topic.title+"</a> &gt; 修改主题">

  <!-- register info begin -->
  <form action="updateTopic.action" method="post" id="editTopic" onSubmit="return onCheck();">
 <DIV id=page>
  
   <div class="reply">
<div class="tit"><#if topic.isVote==1>修改投票主题<#else>修改主题</#if></div>

<div class="con">
	标题：
       <input type="text" id="title2" name="title" class="t" size="30" value="${topic.title}"/>
      
        文章类型：
        <#if categories.size()!=0>
           <select name="categoryId" id="categoryId">
              <#list categories as category>
              
              <option value="${category.id}">${category.name}</option>
              
              </#list>
            </select>
        </#if>  
</div>
<#if topic.isVote==1>
<div class="con">
<div class="info2">
<ul>
	
	
	
	<#list poll.pollResults as pr>
		<li>选项${pr.optionId}： <input type="text" class="headline" name="optionText${pr.optionId}" size="30" maxlength="100" value="${pr.optionText}"/></li>		
	</#list>
	<#list ballotOptionNum as num>		
		<li>选项${num}： <input type="text" class="headline" name="optionText${num}" size="30" maxlength="100"/></li>		
	</#list>
	
</ul>
</div>
</div>
</#if>
<div class="con">
    <div class="img">图标：</div>
        <div class="icon">
          <input type="radio" value="0" name="mood"/>
          无
          <input type="radio" value="1" name="mood"/>
          <img width="18" height="16" alt="求助" src="images/1.gif" align="middle"/>
          <input type="radio" value="2" name="mood"/>
          <img width="16" height="15" alt="示好" src="images/2.gif" align="middle"/>
          <input type="radio" checked="checked" value="3" name="mood"/>
          <img alt="贡献" src="images/3.gif" align="middle"/>
          <input type="radio" value="4" name="mood"/>
          <img alt="音乐" src="images/4.gif" align="middle"/>
          <input type="radio" value="5" name="mood"/>
          <img alt="光碟" src="images/5.gif" align="middle"/>
          <input type="radio" value="6" name="face"/>
          <img alt="游戏" src="images/6.gif" align="middle"/>
          <input type="radio" value="7" name="mood"/>
          <img alt="照片" src="images/7.gif" align="middle"/><br/>
          <input type="radio" value="8" name="mood"/>
          <img alt="诈唬" src="images/8.gif" align="middle"/>
          <input type="radio" value="9" name="mood"/>
          <img alt="播放" src="images/9.gif" align="middle"/>
          <input type="radio" value="10" name="mood"/>
          <img alt="点火" src="images/10.gif" align="middle"/>
          <input type="radio" value="11" name="mood"/>
          <img alt="体育" src="images/11.gif" align="middle"/>
          <input type="radio" value="12" name="mood"/>
          <img alt="提示" src="images/12.gif" align="middle"/>
          <input type="radio" value="13" name="mood"/>
          <img alt="阳光" src="images/13.gif" align="middle"/>
          <input type="radio" value="14" name="mood"/>
          <img alt="代码脚本" src="images/14.gif" align="middle"/>
          <input type="radio" value="15" name="mood"/>
          <img alt="玫瑰" src="images/15.gif" align="middle"/> </div>
</div>
<div class="con" >
  <div class="info2" >
        <div class="editorbody">
       <textarea name="content" id="content" style="height:400px;width:50%;">${topic.firstPost.content}</textarea>
        </div>
    </div>
</div>
<div class="con">
	
	<input type="hidden" name="pollContent" size="80" class="t"/>
        <input type="hidden" value="${forum.id}" name="forumId"/>
          <input type="hidden" value="${topic.id}" name="id"/>
          <input type="hidden" value="${topic.username}" name="username"/>
          <input type="hidden" value="${topic.firstPost.id}" name="firstPostId"/>
          <input type="hidden" value="${topic.isVote}" name="isVote"/>
          <input type="hidden" value="${optionNum}" name="optionNum"/>
        <input  type="image" src="images/zx-b3.gif" style=" margin:0 0 0 250px; height:30px; width:90px;cursor:hand"/>
	
</div>
</div>
</DIV>
 </form> 
  <!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</body>
</html>
