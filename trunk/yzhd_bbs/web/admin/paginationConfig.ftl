<#import "/lib/layout.ftl" as layout>
<@layout.html title="设置分页记录数">
<!-- basic info begin -->
<div class="box1">
  <div class="title"> 每页显示记录数设置 </div>
  <form action="paginationConfig!set.action" method="post">
    <div class="content">
      <div class="ibox">
        <div class="it">每页显示主题数: *</div>
        <div class="iv">
		  <select name="topic">
		  	<option value="10"<#if propertyMap["topic"]=="10"> selected="true"</#if>>10条</option>
			<option value="20"<#if propertyMap["topic"]=="20"> selected="true"</#if>>20条</option>
			<option value="30"<#if propertyMap["topic"]=="30"> selected="true"</#if>>30条</option>
		  </select>
          <span class="red">主题列表中每页显示主题数目</span> </div>
      </div>
      <div class="ibox">
        <div class="it">每页显示帖数: * </div>
        <div class="iv">
          <select name="post">
		  	<option value="10"<#if propertyMap["post"]=="10"> selected="true"</#if>>10条</option>
			<option value="20"<#if propertyMap["post"]=="20"> selected="true"</#if>>20条</option>
			<option value="30"<#if propertyMap["post"]=="30"> selected="true"</#if>>30条</option>
		  </select>
          <span class="red">帖子列表中每页显示帖子数目</span> </div>
      </div>
      <!--<div class="ibox">
        <div class="it">每页显示会员数: * </div>
        <div class="iv">
          <select name="member">
		  	<option value="10"<#if propertyMap["user"]=="10"> selected="true"</#if>>10条</option>
			<option value="20"<#if propertyMap["user"]=="20"> selected="true"</#if>>20条</option>
			<option value="30"<#if propertyMap["user"]=="30"> selected="true"</#if>>30条</option>
		  </select>
          <span class="red">会员列表中每页显示会员数目</span> </div>
      </div>	-->
     <input type="hidden" value="20" name="member" class="b"/>  		   
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设 定" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
<script type="text/javascript">
	//<![CDATA[
	//]]>
  </script>
</@layout.html>
