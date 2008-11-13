<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="论坛类别">
<#if req.getParameter("forumId")?exists>
<!-- <h2>${forum.name}</h2> -->
<#if req.getParameter("isEdit")?exists>
<!-- edit category begin -->

<div class="box1">
  <div class="title"> 修改分类 </div>
  <form action="updateCategory.action" method="post" id="categoryForm" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="forumId" value="0"/>
    <input type="hidden" name="id" value="${category.id}"/>
    <div class="content">
      <div class="ibox">
        <div class="it">类别名称: *</div>
        <div class="iv">
          <input name="name" type="text" class="t" value="${category.name}" size="40"/>
        </div>
      </div>
      <div class="ibox">
        <div class="it">论坛序号: * </div>
        <div class="iv">
		  <input name="displayOrder" type="text" class="t" value="${category.displayOrder}" size="4"/> 值越大,位置越前
        </div>
      </div>   
	    <div class="box3" style="text-align:center; clear:both">
	      <div>
	        <input type="submit" value="更新分类" name="agree" class="b"/>
			<input type="reset" value="重置" name="agree" class="b"/>
	      </div>
	    </div>      	  
    </div>
    <!-- end #category -->
  </form>
</div>
<!-- end #create category -->
<#else>
<!-- create category begin -->
<div class="box1">
  <div class="title"> 创建分类 </div>
  <form action="createCategory.action" method="post" id="categoryForm" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="forumId" value="0"/>
    <div class="content">
      <div class="ibox">
        <div class="it">类别名称: *</div>
        <div class="iv">
          <input name="name" type="text" class="t" value="" size="40"/>
        </div>
      </div>
      <div class="ibox">
        <div class="it">论坛序号: * </div>
        <div class="iv">
		  <input name="displayOrder" type="text" class="t" value="0" size="4"/> 值越大,位置越前
        </div>
      </div>   
	    <div class="box3" style="text-align:center; clear:both">
	      <div>
	        <input type="submit" value="添加分类" name="agree" class="b"/>
			<input type="reset" value="重置" name="agree" class="b"/>
	      </div>
	    </div>      	  
    </div>
    <!-- end #content -->
  </form>
</div>
<!-- end #create category -->
</#if>
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:184px;">
      <div class="d" style="width:80px;">序号</div>
      <div class="d" style="width:100px;">操作</div>
    </div>
    <div class="llbox" style="border-right:0px;overflow :hidden; padding-left:20px;">类别名称</div>
  </div>
  <#list categories as category>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:184px; ">
      <div class="d" style="width:80px;">${category.displayOrder}</div>
      <div class="d" style="width:100px;">
        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='categoryManage!edit.action?isEdit=true&amp;categoryId=${category.id}&amp;forumId=0';"/>
        <input type="button" name="" class="tb" value="删除"  onClick="Util.del('确认删除 [${category.name?js_string}] 吗?','categoryManage!delete.action?categoryId=${category.id}&amp;forumId=0');"/>
      </div>
    </div>
    <div class="llbox" style="text-align:left;border-right:0px;overflow :hidden;height:26px;"><a href="${base}/topics.action?forumId=0&amp;categoryId=${category.id}" title="${category.name}">${category.name}</a></div>
  </div>
  </#list>
</div>
<#else>
<!-- explain begin -->
<div class="box1 dashed">
    <ul>
      <li>请点击右边的类别栏目然后在进行操作</li>
    </ul>
</div>
<!-- end #explain -->
</#if>
</@layout.html>
