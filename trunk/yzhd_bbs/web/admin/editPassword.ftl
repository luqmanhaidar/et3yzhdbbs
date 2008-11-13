<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改密码">
  <!-- update password begin -->
  <div class="box1">
    <div class="title"> 修改密码 </div>
    <form action="updatePassword.action" method="post" id="editPasswordForm" onSubmit="return Validator.validate(this,'admin');">
      <div class="content">
        <div class="ibox">
          <div class="it">原始密码: *</div>
          <div class="iv">
            <input name="oldPassword" type="password" class="t" value=""/>
          </div>
        </div>
        <div class="ibox">
          <div class="it">新密码: * </div>
          <div class="iv">
		  	 <input name="password" type="password" class="t" value=""/>
          </div>
        </div>
        <div class="ibox">
          <div class="it">重复新密码: * </div>
          <div class="iv">
		  	 <input name="repeatPassword" type="password" class="t" value=""/>
          </div>
        </div>		
      </div>
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="更新密码" name="submit" class="b"/>
          <input type="reset" value="重置" name="reset" class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end # edit password -->
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
	<#if actionMessage?exists>
  		alert("${actionMessage?if_exists}");
	</#if>		
	<#if warnMessage?exists>
  		alert("${warnMessage?if_exists}");
	</#if>		
	}
	//]]>
</script>
</@layout.html>