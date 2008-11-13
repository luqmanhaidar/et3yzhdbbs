  <!-- location&orderby begin -->
  <div class="box5" style=" margin-top:16px;">
	<script type="text/javascript">
		//<![CDATA[
		// order by topic.
		function setOrderBy(value){
			$("order").value=value;
			Forms.submit("orderByForm","forum.action?forumId=${req.getParameter("forumId")}","_self");
		}
		//]]>
	</script>
  	<form method="post" action="#" id="orderByForm" method="post">
    <input type="hidden" name="order" value="<#if req.getParameter("order")?exists>${req.getParameter("order")?if_exists}<#else>asc</#if>" id="order"/>
    <div class="ftr" style="padding-right:4px;">查看
      <select name="sort">
        <option value="lastPostTime"<#if req.getParameter("sort")?if_exists=="lastPostTime"> selected="ture"</#if>>回复时间</option>
        <option value="views"<#if req.getParameter("sort")?if_exists=="views"> selected="ture"</#if>>查看次数</option>
        <option value="dateCreated"<#if req.getParameter("dateCreated")?if_exists=="views"> selected="ture"</#if>>发贴时间</option>
        <option value="totalPost"<#if req.getParameter("totalPost")?if_exists=="views"> selected="ture"</#if>>贴子总数</option>
      </select>
      <input type="button" value="升 序" name="asc" class="b" onClick="setOrderBy('asc');"/>
      <input type="button" value="降 序" name="desc"  class="b" onClick="setOrderBy('desc');"/>
    </div>
    </form>
    <script type="text/javascript">
		//<![CDATA[
		function redirectForum(){
			var forumId = $("forumId").value;
			if(forumId != ""){
				window.location.href="forum.action?forumId="+forumId;
			}
		}
		//]]>
	</script>
    <div class="ftl" style="padding-left:4px;">跳转论坛 ：
      <select name="forumId" onChange="redirectForum();" id="forumId">
		<option value="">选择论坛</option>
      	<#list forums as forum>
      	<#if forum.depth==1>
		<optgroup label="${forum.name}"> 
		<#else>
		<option value="${forum.id}"><#list 1..(forum.depth-1) as i> —— </#list>${forum.name}</option>
		<#--<option value="<#if forum.depth==1>""<#else>${forum.id}</#if>">${""?left_pad((forum.depth-1)*24,"&nbsp;")}${forum.name}</option>-->
		</#if>
        </#list>
      </select>
    </div>
  </div>
  <!-- end location&orderby -->