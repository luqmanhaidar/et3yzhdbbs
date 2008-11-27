<#import "/lib/layout.ftl" as layout>
<@layout.html title="查看用户">

<!-- basic info begin -->
<div class="box1">
  <div class="title"> 查看系统用户 </div>

    <div class="content">	
      <div class="ibox">
        <div class="it">用户名:  </div>
        <div class="iv">
          ${user.username}
          </div>
      </div>
      <div class="ibox">
        <div class="it">昵 称:   </div>
        <div class="iv">${user.alias?if_exists}</div>
      </div>
      <div class="ibox">
        <div class="it">性 别:  </div>
        <div class="iv"><#if user.sex=1><IMG src="images/zl-f.gif">男<#elseif user.sex=2>女<#else>保密</#if></div>
      </div>
      <div class="ibox">
        <div class="it">最近登录:  </div>
        <div class="iv">${user.lastLoginTime}</div>
      </div>
      <div class="ibox">
        <div class="it">注册日期:  </div>
        <div class="iv">${user.registerTime}</div>
      </div>
      <div class="ibox">
        <div class="it">用户Email:  </div>
        <div class="iv">${user.email?if_exists}</div>
      </div>
      
      	<#assign role = RoleSingleton.getInstance().getRole(user.roles)>
      <div class="ibox">
        <div class="it">用户所在系统组:    </div>
        <div class="iv">${role.name}</div>
      </div> 
      <div class="ibox">
        <div class="it">积 分:  </div>
        <div class="iv">${user.money?if_exists}</div>
      </div>
      <div class="ibox">
        <div class="it">发表主题帖数：  </div>
        <div class="iv">${user.totalTopic?if_exists}</div>
      </div>
	  <div class="ibox">
        <div class="it">回复帖数  </div>
        <div class="iv">${user.totalPost?if_exists}</div>
      </div>    	
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="返回" name="agree" class="b" onClick="history.back(-1);"/>
      </div>
    </div>

</div>
</@layout.html>
