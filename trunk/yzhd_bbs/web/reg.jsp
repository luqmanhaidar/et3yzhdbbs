<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.*" %>
<%@ page import="com.ntsky.bbs.util.memory.ForumSingleton" %>
<%@ page import="com.ntsky.bbs.util.memory.StarUserSingleton" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI= name=verify-v1>
<META content="MSHTML 6.00.6000.16705" name=GENERATOR>
<meta http-equiv="content-type" content="text/html; charset=GB2312">



<title>论坛</title>
<link href="style/default.css" type="text/css" rel="stylesheet" />
<link href="style/menu.css" type="text/css" rel="stylesheet" />
</head><body>
<div id="head">

<div id="top">
<DIV class=logo><A href="#" target=_blank><img src="images/logo.gif"></A></DIV>
<div class="banner">
 
</div>
</div>


<div id="nav">
<div class="con1">

<div class="navlink">



<a href="#">首页</a>
→
<a href="#">社区</a>
→ 
<a href="#">论坛</a>





→ <a href="#">powerhawk</a>
</div>
<div class="mail">
		<a href="#"><img src="images/au2.gif" /></a>&nbsp;&nbsp;<a href="#"><img src="images/au5.gif" /></a>
			
			
		</div>
</div>

<div class="con2">
<div class="bulletin"><img src="images/icon1.gif" align="absmiddle">&nbsp;社区公告：<a href="#">说说宝宝最爱的玩具◆参与讨论就有机会上目录！</a></div>
<div class="search">
<form method="post" id="search" action="" name="BBS_Search">
  <select name="Topic1:Categorys" id="Topic1_Categorys">
	<option value="0">全部</option>
	<option value="400" selected="selected">未准妈妈</option>
    <option value="6">孕期滋味</option>
    <option value="2">育儿心经</option>
    <option value="19">0－3早教</option>
    <option value="401">宝宝学艺</option>
    <option value="102">小学生活</option>
    <option value="1">宝宝贴图</option>
    <option value="101">夫妻之间</option>
    <option value="15">婆媳关系</option>
    <option value="402">单亲家庭</option>
    <option value="5">产品评价</option>
    <option value="80">漂亮妈妈</option>
    <option value="215">时尚家居</option>
    <option value="3">美食厨房</option>
    <option value="14">随意聊吧</option>
    <option value="104">休闲旅游</option>
    <option value="403">律师热线</option>
    <option value="8">玛丽医生</option>
    <option value="13">跳蚤市场</option>
    <option value="131">团购天地</option>
    <option value="21">打折信息</option>
    <option value="21">两性学堂</option>
    <option value="404">论坛公告</option>
    <option value="405">活动中心</option>
    <option value="4">意见反馈</option>
    <option value="406">招募申请</option>
    <option value="217">站务交流</option>
    <option value="9">北京论坛</option>
    <option value="10">沈阳论坛</option>
    <option value="110">大连论坛</option>
    <option value="410">长春论坛</option>
    <option value="50">山东论坛</option>
    <option value="7">天津论坛</option>
    <option value="30">武汉论坛</option>
    <option value="20">上海论坛</option>
    <option value="40">南京论坛</option>
    <option value="140">无锡论坛</option>
    <option value="130">苏州论坛</option>
    <option value="221">宁波论坛</option>
    <option value="150">杭州论坛</option>
    <option value="100">广州论坛</option>
    <option value="120">深圳论坛</option>
    <option value="60">成都论坛</option>
    <option value="222">西安论坛</option>
    <option value="219">湖南论坛</option>
    
    <option value="411">太原论坛</option>
    <option value="412">石家庄坛</option>
    <option value="413">郑州论坛</option>

</select>
&nbsp;&nbsp;
<select name="Search_Type">
	<option value="0">主题搜索</option>
	<option value="2">作 者</option>
</select>&nbsp;&nbsp;
<input name="SearchKeys" value="" size="16" onkeydown="ClickUp();" type="text"><input id="Butt" name="Butt" value="搜索" onclick="searchStart1();" type="button">
</form>
</div>
</div>
</div> 
</div>
<!--头部结束-->

<!--中间开始-->
<DIV id=page>
<DIV class=login2>
<DIV class=login><IMG class=tfimg height=40 src="images/dl-p112.gif" 
width=290> <IMG class=tfimg height=40 src="images/dl-p2.gif" width=262> <IMG 
class=tfimg height=40 src="images/dl-p3.gif" width=238> <IMG class=tfimg 
height=40 src="images/dl-p4.gif" width=190> </DIV>
<DIV class=login>
<DIV class=info>
<DIV><IMG height=14 src="images/dl-p11.gif" width=530></DIV>
<DIV class=con>

<DIV class=info2>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>用 户 名：</SPAN> 
<INPUT class=name id=UserName name=UserName> <SPAN id=RequiredFieldValidator1 
style="DISPLAY: none; COLOR: red"></SPAN></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>密　　码：</SPAN> 
<INPUT class=password id=Password type=password name=Password> <SPAN 
id=RequiredFieldValidator2 style="DISPLAY: none; COLOR: red"></SPAN><A 
class=red_3 
href="http://www.redbaby.com.cn/Users/ForgetPassword.aspx"></A></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>确认密码：</SPAN> 
<INPUT class=password id=Password type=password name=Password> <SPAN 
id=RequiredFieldValidator2 style="DISPLAY: none; COLOR: red"></SPAN><A 
class=red_3 
href="http://www.redbaby.com.cn/Users/ForgetPassword.aspx"></A></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>验 证 码：</SPAN> 
<INPUT class=yzm id=ConfirmCode name=ConfirmCode> <IMG title=看不清楚，请点击图片刷新 
style="CURSOR: hand" 
onclick="this.src='Common/ConfirmCode.aspx?q='+Math.random();" 
src="images/ConfirmCode.gif" align=middle> <SPAN id=Requiredfieldvalidator3 
style="DISPLAY: none; COLOR: red"></SPAN></DIV>
<DIV class=qt></DIV>
<DIV class=qt>
  <INPUT 
onclick="location.href='https://www.redbaby.com.cn/Users/Reg.aspx?http://bbs.redbaby.com.cn/Login.aspx?gotourl=/default.aspx';return false" 
type=image src="images/dl-b2.gif" name=Input> </DIV></DIV></DIV>
<DIV><IMG src="images/dl-p12.gif"></DIV></DIV>
<DIV class=gg>
<DIV><BR><BR><BR><BR><BR><BR><BR><BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;尊敬的客户，如果您无法正常登录我们的网站，请您检查系统时间是否为当前日期，如果还登录不上，请检查您的浏览器是否限制我们写入cookies。 
</DIV></DIV></DIV>
<DIV class=login><IMG class=tfimg height=39 src="images/dl-p7.gif" 
width=290> <IMG class=tfimg height=39 src="images/dl-p8.gif" width=262> <IMG 
class=tfimg height=39 src="images/dl-p9.gif" width=238> <IMG height=39 
src="images/dl-p10.gif" width=190> </DIV></DIV></DIV>


<input name="SaveId" id="SaveId" type="hidden">

<!--底部开始-->
<div id="bottom">
<div class="about">
<a href="#"></a>
<a href="#">客服中心</a> | 
<a href="#">配送说明</a> | 
<a href="#">加盟合作</a> | 
<a href="#">公司介绍</a> | 
<a href="#">诚聘英才</a> | 
<a href="#">与红孩子合作</a>|
<a href="#">联系我们</a> | 
<a href="#">友情链接</a> | 
<a href="#">广告服务</a> | 
<a href="#"><b>意见反馈</b></a></div>
<div class="copyright">版权所有 广州博商软件技术有限公司<a class="red_1" href="#">京ICP证060411号</a> Copyright &#169; 2008</div>
</div> 
<!--底部结束-->




</body></html>