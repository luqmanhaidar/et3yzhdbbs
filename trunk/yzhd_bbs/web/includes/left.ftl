<SCRIPT type=text/javascript>
function openDiv(obj)
{
	if (SaveId.value!="" && SaveId.value!=obj)
	{
		/*document.all('Child_Board'+SaveId.value).style.display = "none";*/
	}
	document.all('Child_Board' + obj).style.display = (document.all('Child_Board'+obj).style.display=='') ? "none":"";
	SaveId.value = obj;
}
</SCRIPT>
<DIV class=left>
	<DIV class=hhz1>
		<IMG alt="" src="images/hhz1.gif">
	</DIV>
	<DIV class=menu>
		<DIV class=function>
			<UL class=menutop>
				<LI>
					<A href="search.action?forumId=0&sort=lastPostTime&order=desc&time=0&type=title&keyword=&isNewLy=0">最新回复</A>
				</LI>
				<LI>
					<A href="search.action?forumId=0&sort=dateCreated&order=desc&time=0&type=title&keyword=&isNewLy=1">最新主题</A>
				</LI>
				<LI>
					<A href="help.action" target=_blank>新手帮助</A>
				</LI>
				<#if Session["sessionUser"]?exists>
				<LI>
					<A href="logout.action">退出</A>
				</LI>
				<#else>
				<LI>
					<A href="login.action">登录</A>
				</LI>
				</#if>
				<#if Session["sessionUser"]?exists>
				<LI>
					<A href="editUser-info.action">用户中心</A>
				</LI>
				<#else>
				<LI>
					<A href="signup-page.action">注册</A>
				</LI>
				</#if>
				<#if Session["sessionUser"]?exists>
				<#else>
				<LI>
					<A href="recoverPassword-page.action" target=_blank>忘记密码</A>
				</LI>
				</#if>
			</UL>
		</DIV>
		<#assign cou=1>
  		<#list application["frontTop"]["forums"] as forum>
  		<#if forum.depth==1>
  		<#if cou==3>
  			</UL>
  		</#if>
  		<#assign cou=2>
		<DIV class="zt" style="CURSOR: pointer; LINE-HEIGHT: 27px" onclick="openDiv('${forum.id}')">${forum.name}</DIV>
		<#if cou==2>
  			<UL class="sort" id="Child_Board${forum.id}">
  		</#if>
		<#else>		
  		<#if forum.depth==2>
  		<#assign cou=3>
			<LI>
				<A class=red_1 href="forum.action?forumId=${forum.id}">${forum.name}</A>
			</LI>
		</#if>
	  	</#if>
	  	</#list>
		<DIV>
		</DIV>
	</DIV>
</DIV>
