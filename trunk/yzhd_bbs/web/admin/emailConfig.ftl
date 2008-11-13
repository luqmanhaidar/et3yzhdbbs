<#import "/lib/layout.ftl" as layout>
<@layout.html title="设定邮件配置信息">
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>

<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
		replaceMyTextarea("text_register");
		replaceMyTextarea("text_error");
	}
	//]]>
</script>

<div class="box1 dashed">
  <h2>邮件内容说明</h2>
  <div>
    <ol>
      <li>用户注册后,发送的邮件信息</li>
	  <li>系统发生错误后,发送的邮件信息</li>
    </ol>
  </div>
</div>

<br/>
<table cellspacing="0" align="center" class="mtable">
  <form action="emailConfig!set.action" method="post" id="setEmailForm" onSubmit="return Validator.validate(this,'admin');">
  <tr>
    <td colspan="2" class="mt">邮件设置</td>
  </tr>
  <tr>
    <td width="22%" class="mtdt">发送邮件服务器： *</td>
    <td width="78%" class="mtdv"><input name="smtpHost" type="text" class="t" value="${email.smtpHost}"/>
      <span class="red">发送邮件的服务器(请资源邮件服务商的配置说明)</span></td>
  </tr>
  <tr>
    <td class="mtdt">登陆邮件用户名： *</td>
    <td class="mtdv"><input name="username" type="text" class="t" value="${email.username}"/>
      <span class="red">登陆邮件用户名</span></td>
  </tr>
  <tr>
    <td class="mtdt">登陆邮件密码: *</td>
    <td class="mtdv"><input name="password" type="password" class="t" value="${email.password}"/>
      <span class="red">登陆邮件使用的密码</span></td>
  </tr>
  <tr>
    <td class="mtdt">系统邮件: *</td>
    <td class="mtdv"><input name="systemMail" type="text" class="t" value="${email.systemMail}"/>
      <span class="red">发送邮件的地址</span></td>
  </tr>  
  <tr>
    <td class="mtdt">邮件内容设置: *</td>
    <td class="mtdv"><!-- tab-pane begin -->
      <div class="tab-pane" id="tabPane1" style="margin:4px;">
        <div class="tab-page" id="tabPage1">
          <h2 class="tab">注 册(1)</h2>
          <div style="border: 1px solid silver;">
            <div class="ibox">
              <div class="it">邮件标题: *</div>
              <div class="iv">
                <input name="subject" type="text" class="t" value="${email.bodys["register"]["subject"]}" size="50"/>
              </div>
            </div>
            <div class="ibox" style="height:208px;border-bottom:0px;">
              <div class="it">邮件内容: *</div>
              <div class="iv" style="height:200px; width:68%">
                <textarea name="text" style="height:200px; width:100%" id="text_register">${email.bodys["register"]["text"]}</textarea>
              </div>
            </div>
          </div>
        </div>
        <div class="tab-page" id="tabPage2">
          <h2 class="tab">错误(2)</h2>
          <div style="border: 1px solid silver;">
            <div class="ibox">
              <div class="it">邮件标题: *</div>
              <div class="iv">
                <input name="subject" type="text" class="t" value="${email.bodys["error"]["subject"]}" size="50"/>
              </div>
            </div>
            <div class="ibox" style="height:208px;border-bottom:0px;">
              <div class="it">邮件内容: *</div>
              <div class="iv" style="height:200px; width:68%">
                <textarea name="text" style="height:200px; width:100%" id="text_error">${email.bodys["error"]["text"]}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end #tab-pane  -->
    </td>
  </tr>
  <tr>
    <td colspan="2" class="mtda"><input type="button" name="validateMail" value="检测邮件设置" class="b" onClick="Util.popupWindow('${base}/common/process.jsp','setEmailForm','${base}/install/popup-validateUnSaveMail.action',400,160);"/><input type="submit" name="Submit" value="设定邮件参数" class="b"/></td>
  </tr>
  </form>
</table>
</@layout.html>