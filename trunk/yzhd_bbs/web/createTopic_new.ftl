<#assign isVote = request.getParameter("isVote")?exists>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><#if isVote>发表投票主题<#else>发表主题</#if></title>

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
		replaceMyTextarea("content","Default",".","400");
	};
	//]]>
</script>
</head>
<body>
<#include "includes/header.ftl">
<#include "includes/front_top.ftl">
  <#assign link = "">
  <#if isVote>
   	<#assign link = " &gt; 发表投票主题">	
  <#else>
  	<#assign link = " &gt; 发表主题">  
  </#if>
 
 
 <!-- 模版 -->

 <!-- 模版 -->
  <!-- register info begin -->
  <div class="box1 mtq">
    <div class="title"> <#if isVote>发表投票主题<#else>发表主题</#if> </div>
    <form action="createTopic.action" method="post" id="createTopic" onSubmit="return Validator.validate(this);">
    <div class="content">
      <div class="ibox">
        <div class="it" style="width:20%;">标题: * </strong> </div>
        <div class="iv">
            <input type="text" name="title" class="t" size="60"/>
        </div>
     </div> 
    	
     <div class="ibox">
     	<div class="it" style="width:20%;">选择论坛: * </strong> </div>
          <div class="iv">
          
          <#assign forums = application["frontTop"]["forums"]?if_exists>
          
          <#if forums.size()!=0>
			   <select name="forumId" id="forumId">
			   		<#if req.getParameter("forumId")?exists>
			   			<#assign fid=req.getParameter("forumId")>
			   			<#list application["frontTop"]["forums"] as forum>
	        				<#if forum.depth==1>
							<optgroup label="${forum.name}"> 
							<#else>		
								<#if (forum.id?string)==fid>					
								<option value="${forum.id}" selected>${forum.name}</option>
								<#else>
								<option value="${forum.id}" >${forum.name}</option>
								</#if>								
							</#if>	
	        			</#list>
			   		<#else>
 	        			<#list application["frontTop"]["forums"] as forum>
	        				<#if forum.depth==1>
							<optgroup label="${forum.name}"> 
							<#else>							
								<option value="${forum.id}">${forum.name}</option>								
							</#if>	
	        			</#list>
	        		</#if>
               </select>
        </#if>   
        
        </div>
       </div>

      <div class="ibox" style="height:60px">
        <div class="it" style="width:20%;">发帖心情: *  </div>
        <div class="iv" style="height:56px;">
	        <div style="width:90％;line-height:150%;padding:4px 0px;">
	        	<input type="radio" name="mood" value="1" checked/><img src="skins/default/topicmood/1.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="2"/><img src="skins/default/topicmood/2.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="3"/><img src="skins/default/topicmood/3.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="4"/><img src="skins/default/topicmood/4.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="5"/><img src="skins/default/topicmood/5.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="6"/><img src="skins/default/topicmood/6.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="7"/><img src="skins/default/topicmood/7.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="8"/><img src="skins/default/topicmood/8.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="9"/><img src="skins/default/topicmood/9.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="10"/><img src="skins/default/topicmood/10.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="11"/><img src="skins/default/topicmood/11.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="12"/><img src="skins/default/topicmood/12.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="13"/><img src="skins/default/topicmood/13.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="14"/><img src="skins/default/topicmood/14.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="15"/><img src="skins/default/topicmood/15.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="16"/><img src="skins/default/topicmood/16.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="17"/><img src="skins/default/topicmood/17.gif" align="absMiddle"/>
	        	<input type="radio" name="mood" value="18"/><img src="skins/default/topicmood/18.gif" align="absMiddle"/>	        	
			</div>
        </div>
      </div>
      <div class="ibox" style="height:408px;">
        <div class="it" style="width:20%;">内容: *  </div>
        <div class="iv" style="height:400px; width:76%">
          <textarea name="content" id="content" style="height:400px;width:100%;"></textarea>
        </div>
      </div>
      </div>
      <#if isVote>
	  <!-- poll option begin -->
	  <div>
      	<div class="title">投票选项</div>
		  <div class="content">
			<div class="ibox">
			  <div class="it" style="width:20%;">投票信息: * </div>
			  <div class="iv">
				<input type="text" name="pollContent" size="80" class="t"/>
			  	<input type="hidden" name="optionNum" value="0" id="optionNum"/>
			  </div>
			</div>
			<div id="options">
			</div>
			<script type="text/javascript">
				//<![CDATA[
				String.prototype.ReplaceAll = function (findText,repText){
					regExp = new RegExp(findText,"g");
					return this.replace(regExp,repText);
				}
				// 投票选项script
				function Poll() {
					this.optionArray = [];
					this.optionHtml = "<div class=\"ibox\" id=\"option#key#\" style=\"display:block;\"><div class=\"it\" style=\"width:20%;\">选项: * </div><div class=\"iv\"><input type=\"hidden\" name=\"optionId#key#\" value=\"#key#\"/><input type=\"text\" name=\"optionText#key#\" class=\"t\" size=\"40\"/><span id=\"addButton#key#\">#addButton#</span><span id=\"deleteButton#key#\"></span></div></div>";
					this.optionNum = 0;
					this.addButton = "<input type=\"button\" class=\"tb\" name=\"option#key#\" value=\"建立选项\" onClick=\"poll.addOption(eval(#key#+1));\"/>";
					this.deleteButton = "<input type=\"button\" class=\"tb\" name=\"deleteOption#key#\" value=\"删除\" onClick=\"poll.deleteOption(#key#);\"/>";
				};
				Poll.prototype.addOption = function(key) {
					// 第一条记录增加删除按钮
					document.getElementById("addButton"+(eval(key-1))).innerHTML = "";
					document.getElementById("deleteButton"+(eval(key-1))).innerHTML = this.deleteButton.ReplaceAll("#key#",eval(key-1));
					// 创建第二条记录
					this.optionArray[this.optionArray.length] = this.optionHtml.replace("#addButton#",this.addButton).ReplaceAll("#key#",key);
					this.appendPoll(key);
				};
				Poll.prototype.deleteOption = function(key) {
					if(this.optionArray.length<1){
						alert("必须含有一个以上投票选项,删除投票选项失败!");
					}
					else{
						Util.hidden("option"+key);
					}
					this.setOptionNum("-");
				};
				Poll.prototype.init = function() {
					this.optionArray[this.optionArray.length] = this.optionHtml.replace("#addButton#",this.addButton).ReplaceAll("#key#","1");
					this.appendPoll(1);
				};
				Poll.prototype.appendPoll = function(key) {
					optionsHtml = this.optionArray[key-1];
					document.getElementById("options").innerHTML = document.getElementById("options").innerHTML + optionsHtml;
					this.setOptionNum("+");
					//alert(document.getElementById("options").innerHTML);
				};
				Poll.prototype.setOptionNum = function( operator ){
					if("+" == operator) {
						this.optionNum = this.optionNum + 1;
					}
					else{
						this.optionNum = this.optionNum - 1;
					}
					document.getElementById("optionNum").value = this.optionNum;
				};
				var poll = new Poll();	
				poll.init();
			  	//]]>
			</script>
			
		  </div>
	  </div>
	  <!-- end poll option -->
	  </#if>      
      <div class="box3" style="text-align:center">
        <div>
          <input type="hidden" value="${request.getParameter("isVote")?if_exists}" name="isVote"/>
          <input type="hidden" value="1" name="categoryId"/>
          <input type="submit" value="<#if isVote>发表投票主题<#else>发表主题</#if>" name="agree" class="b"/>
          <!--<input type="button" value="预览主题" name="preview" class="b" onClick="Util.popupWindow('common/process.jsp','createTopic','common/preview.jsp','500','300');"/>-->
          <input onclick="history.back(-1)" type="reset" value="返回"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #post info -->
  
  <!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</body>
</html>
