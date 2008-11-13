<#import "/lib/layout.ftl" as layout>
<@layout.html title="创建用户">
<!-- explain begin -->
<div class="box1 dashed">
  <h2>使用说明</h2>
    <ul>
      <li>请输入添加的用户信息</li>
      <li>务必按规范填好各项信息</li>
      <li>作者（ET-3） : Gmail <a href="mailto:zhangjing@gmail.com">zhangjing@gmail.com</a> MSN nicer_moon@hotmail.com;</li>
    </ul>
</div>
<!-- end #explain -->
<!-- basic info begin -->
<div class="box1">
  <div class="title"> 添加系统用户 </div>
  <form action="createUser.action" method="post" id="createUser" onSubmit="return Validator.validate(this,'admin');">
    <div class="content">	
      <div class="ibox">
        <div class="it">用户名: *</div>
        <div class="iv">
          <input name="username" type="text" class="t" value="" style="width:160px;"/>
          <span class="required">用户名称不为空,长度为2~12</span> </div>
      </div>
      <div class="ibox">
        <div class="it">用户密码: * </div>
        <div class="iv"><input name="password" type="password" class="t" value="" style="width:160px;"/>
          <span class="required">用户密码不为空,长度为6~20</span> </div>
      </div>
      <div class="ibox">
        <div class="it">用户Email: *</div>
        <div class="iv">
          <input name="email" type="text" class="t" value="" size="40"/></div>
      </div>
      <div class="ibox">
        <div class="it">安全提问: *  </div>
        <div class="iv">
          <input type="text" name="question" class="t" size="40"/>
          <span class="required">提示问题不为空</span>
        </div>
      </div>
      <div class="ibox">
        <div class="it">问题答案: *  </div>
        <div class="iv">
          <input type="text" name="answer" class="t" size="40"/>
          <span class="required">问题答案用于取回密码</span>
        </div>
      </div>	
      <div class="ibox">
        <div class="it">用户所在系统组: *  </div>
        <div class="iv">
            <select name="roles">
              <#list managerRoles as role>
              <#if role.id!=0>
              <option value="${role.id}">${role.name}</option>
              </#if>
              </#list>
            </select>
        </div>
      </div>      	
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="添加用户" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
</@layout.html>
