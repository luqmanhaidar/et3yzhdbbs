<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="帖子管理">
  <!-- search info begin -->
  <div class="box1 mtq">
   <form method="post" id="searchForm" action="topics.action">
    <input type="hidden" name="start" value="0" id="start"/>
    <div class="title" style="text-align:center"> 搜索目标 </div>
    <div class="content">
      <div class="ibox">
        <div class="it" style="width:50%"><strong>关键字: * </strong> </div>
        <div class="iv">
          <input type="text" name="keyword" class="t" size="50" value="${req.getParameter("keyword")?if_exists}"/>
        </div>
      </div>
      <div class="ibox">
        <div class="it" style="width:50%"><strong>搜索类型: * </strong> </div>
        <div class="iv">
          <input type="radio" name="type" value="title" checked="true"/>主题 <input type="radio" name="type" value="username"/>作者
        </div>
      </div>      
      <div class="title" style="text-align:center"> 搜索选项 </div>
      <div class="content">
        <div class="ibox">
          <div class="it" style="width:50%"> 论坛:
	      <select name="forumId" id="forumId">
	        <option value="0">全部论坛</option>
	      	<#list forums as forum>
	        <option value="${forum.id}" <#if req.getParameter("forumId")?if_exists==forum.id?string> selected</#if>>${""?left_pad((forum.depth-1)*2,"—")} ${forum.name}</option>
	        </#list>
	      </select></div>
          <div class="iv"> 排序类型:
            <select class="post" name="sort">
              <option value="dateCreated">发表时间</option>
              <option value="lastPostTime">回复时间</option>
              <option value="title">主题</option>
              <option value="username">作者</option>
              <option value="views">查看次数</option>
              <option value="status">精华</option>
              <option value="isTop">置顶</option>
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
        </div>
    </div>
    </form>
  </div>
  <#assign GUEST = "guest">
  <!-- end #search info -->
        <div class="box1 mtq">
		    <div style=" background-color: #f7f7f7; height:26px;">
		      <div class="lrbox" style="width:300px;">
		        <div class="d" style="width:90px;">作者</div>
		        <div class="d" style="width:50px;">精华</div>
		        <div class="d" style="width:50px;">固顶</div>
		        <div class="d" style="width:50px;">回复</div>
		        <div class="d" style="width:50px;">查看</div>
		      </div>
		      <div class="llbox" style="width:40px;">选择</div>
		      <div class="llbox" style="border-right:0px;">主题</div>
		    </div>
		    <form action="#" method="post" id="topics">
		      <input name="order" type="hidden" value="${req.getParameter("order")?if_exists}" />
		      <input name="type" type="hidden" value="${req.getParameter("type")?if_exists}" />
		      <input name="forumId" type="hidden" value="${req.getParameter("forumId")?if_exists}" />
		      <input name="time" type="hidden" value="${req.getParameter("time")?if_exists}" />
		      <input name="way" type="hidden" value="${req.getParameter("way")?if_exists}" />
		      <input name="sort" type="hidden" value="${req.getParameter("sort")?if_exists}" />
			  <input type="hidden" name="keyword" class="t" size="60" value="${req.getParameter("keyword")?if_exists}"/>
		    <#list topics as topic>
		    <div class="lbox1" style="padding:0px;">
		      <div class="lrbox" style="width:300px; ">
		        <div class="d" style="width:90px;"><#if topic.username!=GUEST><a href="../user.action?username=${URLEncoder.encode(topic.username?if_exists)}" target="_blank">${topic.username?if_exists}</a><#else>${topic.username?if_exists}</#if></div>
		        <div class="d" style="width:50px;"><#if topic.status=1><input type="button" name="" class="tb" value="是" onClick="location.href='manageTopics!isStatus.action?topicId=${topic.id}&amp;status=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='manageTopics!isStatus.action?topicId=${topic.id}&amp;status=1&amp;start=${start}'"/></#if></div>
		        <div class="d" style="width:50px;"><#if topic.isTop =1>固顶</#if><#if topic.isTop =2>总固顶</#if><#if topic.isTop =0>没固顶</#if></div>
		        <div class="d" style="width:50px;">${topic.replies?int}</div>
		        <div class="d" style="width:50px;">${topic.views}</div>
		      </div>
		      <div class="llbox" style="width:40px;"><input type="checkbox" name="id" value="${topic.id}"/></div>
		      <div class="llbox" style="text-align:left;border-right:0px;"><a href="../topic.action?topicId=${topic.id}" title="${topic.title}" target="_blank">${topic.title}</a><@fn.topicPagination topicId=topic.id postNum=topic.replies?int /></div>
		    </div>
		    </#list>
		    </form>
	    </div>
      	<div class="box5" style="margin:4px 2%;">
      	 	<span style="padding-left:6px;"><input type="checkbox" name="selectAll" value="全选" onClick="System.checkedAll(this,'topics');"/></span>
      	 	<input type="button" name="delete" class="b" value="删 除"  onClick="System.batchExecute('topics','确认将选中的主题丢到垃圾箱吗？','trashTopics.action');"/>
      	 	<input type="button" name="status" class="b" value="推 精" onClick="System.batchExecute('topics','确认设置选中的主题为精华吗？','manageTopics!statusMore.action?status=1');"/>
      	 	<input type="button" name="unstatus" class="b" value="取消推精" onClick="System.batchExecute('topics','确认将选中的主题取消精华吗？','manageTopics!statusMore.action?status=0');"/>
      	 	<input type="button" name="top" class="b" value="固 顶" onClick="System.batchExecute('topics','确认将选中的主题固顶吗？','manageTopics!isTopMore.action?is=1');"/>
      	 		<input type="button" name="top" class="b" value="总固顶" onClick="System.batchExecute('topics','确认将选中的主题总固顶吗？','manageTopics!isTopMore.action?is=2');"/>
      	 	<input type="button" name="unTop" class="b" value="取消固顶" onClick="System.batchExecute('topics','确认将选中的主题取消固顶吗？','manageTopics!isTopMore.action?is=0');"/>    	 	
      		<select id="newForumId" name="newForumId">
				<option value="">选择论坛</option>
		      	<#list forums as forum>
		      	<#if forum.depth==1>
				<optgroup label="${forum.name}"> 
				<#else>
				<option value="${forum.id}"><#list 1..(forum.depth-1) as i> —— </#list>${forum.name}</option>
				</#if>
		        </#list>
		    </select>
      	 	<input type="button" name="top" class="b" value="移动帖子" onClick="moveTo()"/> 
      	</div> 	 	    
	    <div class="box1" style="clear:both;border:0px; height:26px; margin:4px 0px;">
		    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
				<@fn.pager pagination=pagination />
		    </div>
	  	</div> 	
 <script type="text/javascript">
	//<![CDATA[
		function moveTo(){
		
		var fid=document.getElementsByName("newForumId")[0].value;
		if(fid.length==0){
		}else{
			System.batchExecute('topics','确认移动选中的主题吗？','manageTopics!move.action?is='+fid);
		}
			
		}
  	//]]>
</script>	
</@layout.html>
