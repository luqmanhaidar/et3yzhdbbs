<DIV class="part1">
<UL>
  
  <LI class=b1><A class=grey_1 
  href="createTopic-page.action">发表话题</A> 
  <#if item=2>
   <LI class=b2><A class=red_4   
  href="myTopics.action">我发表的主题</A> 
  <#else>
  <LI class=b1><A class=grey_1  
  href="myTopics.action">我发表的主题</A> 
   </#if>
   <#if item=3>
   <LI class=b2><A class=red_4
  href="myRef.action">我参与的主题</A> 
   <#else>
  <LI class=b1><A class=grey_1 
  href="myRef.action">我参与的主题</A> 
  </#if>
  <#if item=4>
  <LI class=b2><A class=red_4
  href="myGift-page.action?username=${URLEncoder.encode(Session["sessionUser"].username)}">我的积分</A> 
  <#else>
  <LI class=b1><A class=grey_1 
  href="myGift-page.action?username=${URLEncoder.encode(Session["sessionUser"].username)}">我的积分</A> 
  </#if>
  <#if item=5>
  <LI class=b2><A class=red_4 
  href="editUser-info.action">个人设置</A> 
    <#else>
     <LI class=b1><A class=grey_1 
    href="editUser-info.action">个人设置</A> 
     </#if>
     <#if item=6>
       <LI class=b2><A class=red_4   
  href="writeMessage-page.action">短信息</A> 
     <#else>
  <LI class=b1><A class=grey_1  
  href="writeMessage-page.action">短信息</A> 
  </#if>
  <#if item=7>
  <LI class=b2><A class=red_4 
  href="myFavorites.action">收藏夹</A> 
  <#else>
  <LI class=b1><A class=grey_1
  href="myFavorites.action">收藏夹</A> 
  </#if>
  <LI class=b1><A class=grey_1 
  href="" 
  target='_blank'>忘记密码</A> 
  <LI class=b1><A class=grey_1 
  href="logout.action">退出</A> 
  </LI></UL></DIV>