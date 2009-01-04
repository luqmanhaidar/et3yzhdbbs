<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%String sUrlHeader = request.getContextPath();%>
<head>
<link href="commonnew/jrbCSS.css" rel="stylesheet" type="text/css" />
<title>广东省金融服务办公室</title>
<SCRIPT language=JavaScript>
<!--

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
function JM_cc(ob){
var obj=MM_findObj(ob); if (obj) { 
obj.select();js=obj.createTextRange();js.execCommand("Copy");}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}
//-->
</SCRIPT>
<SCRIPT language=JavaScript>
<!--

menuPrefix = 'menu';  // Prefix that all menu layers must start with
                      // All layers with this prefix will be treated
                      // as a part of the menu system.

var menuTree, mouseMenu, hideTimer, doHide;

function init() {
  ie4 = (document.all)?true:false;
  ns4 = (document.layers)?true:false;
  document.onmousemove = mouseMove;
  if (ns4) { document.captureEvents(Event.MOUSEMOVE); }
}

function expandMenu(menuContainer,subContainer,menuLeft,menuTop) {
    // Hide all submenus thats's not below the current level
    doHide = false;
  if (menuContainer != menuTree) {
      if (ie4) {
      var menuLayers = document.all.tags("DIV");
      for (i=0; i<menuLayers.length; i++) {
        if ((menuLayers[i].id.indexOf(menuContainer) != -1) && (menuLayers[i].id != menuContainer)) {
          hideObject(menuLayers[i].id);
        }
      }
    }
    else if (ns4) {
      for (i=0; i<document.layers.length; i++) {
        var menuLayer = document.layers[i];
        if ((menuLayer.id.indexOf(menuContainer) != -1) && (menuLayer.id != menuContainer)) {
          menuLayer.visibility = "hide";
        }
      }
    }
  }
  // If this is item has a submenu, display it, or it it's a toplevel menu, open it
  if (subContainer) {
    if ((menuLeft) && (menuTop)) {
        positionObject(subContainer,menuLeft,menuTop);
        hideAll();
    }
    else {
      if (ie4) {
          positionObject(subContainer, document.all[menuContainer].offsetWidth + document.all[menuContainer].style.pixelLeft - 10, mouseY);
      }
      else {
          positionObject(subContainer, document.layers[menuContainer].document.width + document.layers[menuContainer].left + 50, mouseY);
      }
    }
    showObject(subContainer);
    menuTree = subContainer;
  }
}

function showObject(obj) {
  if (ie4) { document.all[obj].style.visibility = "visible"; }
  else if (ns4) { document.layers[obj].visibility = "show";  }
}

function hideObject(obj) {
  if (ie4) { document.all[obj].style.visibility = "hidden"; }
  else if (ns4) { document.layers[obj].visibility = "hide"; }
}

function positionObject(obj,x,y) {
  if (ie4) {
    var foo = document.all[obj].style;
    foo.left = x;
    foo.top = y;
  }
  else if (ns4) {
    var foo = document.layers[obj];
    foo.left = x;
    foo.top = y;
   }
}

function hideAll() {
 if (ie4) {
    var menuLayers = document.all.tags("DIV");
    for (i=0; i<menuLayers.length; i++) {
      if (menuLayers[i].id.indexOf(menuPrefix) != -1) {
        hideObject(menuLayers[i].id);
      }
    }
  }
  else if (ns4) {
    for (i=0; i<document.layers.length; i++) {
      var menuLayer = document.layers[i];
      if (menuLayer.id.indexOf(menuPrefix) != -1) {
        hideObject(menuLayer.id);
      }
    }
  }
}

function hideMe(hide) {
    if (hide) {
        if (doHide) { hideAll(); }
    }
    else {
        doHide = true;
        hideTimer = window.setTimeout("hideMe(true);", 2000);
    }
}

function mouseMove(e) {
  if (ie4) { mouseY = window.event.y; }
  if (ns4) { mouseY = e.pageY; }
}

function itemHover(obj,src,text,style) {
  if (ns4) {
    var text = '<nobr><a href="' + src + '" class="' + style + '">' + text + '<\/a><\/nobr>'
    obj.document.open();
    obj.document.write(text);
    obj.document.close();
  }
}

onload = init;
//-->
</SCRIPT>
<style type=text/css>
<!--
  .menu                   { position: absolute; left: 0; top: 0;
                             visibility: hidden;
                             margin: 0px 0px; padding: 0px 0px;
                             z-index: 10;
                             overflow: visible; ; clip:     rect(   )}

-->
</style>
</head>

<body topmargin="0" style="text-align:center">

<table width="790" border="0" cellpadding="0" cellspacing="0" align="center"bgcolor="#FFFFFF">
  <!--DWLayoutTable-->
  <tr>
    <td align="center"><table width="780" border="0" cellspacing="0" cellpadding="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="780" height="21" align="center" background="commonnew/images/preview_04.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="72%" align="left" class="white">欢迎来到广东省金融服务办公室 今天是<script language="JavaScript">

today=new Date();
var hours = today.getHours();
var minutes = today.getMinutes();
var seconds = today.getSeconds();
var timeValue = "<FONT COLOR=black>" + ((hours >12) ? hours -12 :hours); timeValue += ((minutes < 10) ? "<BLINK><FONT COLOR=black>:</FONT></BLINK>0" : "<BLINK><FONT COLOR=black>:</FONT></BLINK>") + minutes+"</FONT></FONT>";
timeValue += (hours >= 12) ? "PM" : "AM";
function initArray(){
this.length=initArray.arguments.length
for(var i=0;i<this.length;i++)
this[i+1]=initArray.arguments[i]  }
var d=new initArray("<font color=RED>星期日","<font color=black>星期一","<font color=black>星期二","<font color=black>星期三","<font color=black>星期四","<font color=black>星期五","<font color=red>星期六"); document.write("<font color=black>",today.getYear(),"<font color=black>年","<font color=black>",today.getMonth()+1,"<font color=black>月","<font color=black>",today.getDate(),"<font color=black>日 </FONT>",d[today.getDay()+1]," ",timeValue);  //-->
      </script></td>
            <td width="26%" align="left">·<A 
      href="javascript:;" class="Blue12" 
      onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.gdjrb.gov.cn/');">设为首页</A>·<A 
      href="javascript:window.external.AddFavorite('http://www.gdjrb.gov.cn/','广东金融办')" class="Blue12">加入收藏</A>·<a href="http://mail.gdjrb.gov.cn">邮件系统
     </a></td>
          </tr>
        </table></td>
      </tr>
  <tr>
    <td  height="135" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="780" height="135" valign="top"><img src="commonnew/images/preview_07.jpg" width="780" height="135" alt="" /></td>
        </tr>
    </table></td>
  </tr>
  <tr>
        <td height="31" align="center" background="commonnew/images/preview_08.gif" >
        	<table border="0" cellpadding="0" cellspacing="0" align="center">
        		<tr>
        		<td>
        		<a href="<%=sUrlHeader %>/indexnew.jsp"><img src="commonnew/images/preview_10.gif"alt="" width="74" height="31" border="0" /></a>
        		</td>
        		<td>
        		<a href="#" onMouseOver="expandMenu(null,'menu4',getPos(this,'Left'),getPos(this,'Top')+this.offsetHeight);" ><img src="commonnew/images/preview_11.gif" alt="" width="90" height="31" border="0" /></a>
        		</td>
        		<td>
        		<a href="#" onMouseOver="expandMenu(null,'menu1',getPos(this,'Left'),getPos(this,'Top')+this.offsetHeight);" ><img src="commonnew/images/preview_13.gif" alt="" width="90" height="31" border="0" /></a>
        		</td>
        		<td>
        		<a href="#" onMouseOver="expandMenu(null,'menu2',getPos(this,'Left'),getPos(this,'Top')+this.offsetHeight);" ><img src="commonnew/images/preview_15.gif" width="90" height="31" alt="" border="0"/></a>
        		</td>
        		<td>
        		<a href="#" onMouseOver="expandMenu(null,'menu3',getPos(this,'Left'),getPos(this,'Top')+this.offsetHeight);" ><img src="commonnew/images/preview_16.gif" width="90" height="31" alt="" border="0" /></a>
        		</td>
        		<td>
        		<a href="<%=sUrlHeader %>/module_judgenew.jsp?mid=46"><img src="commonnew/images/preview_18.gif" width="78" height="31" alt="" border="0"/></a>
        		</td>
        		<td>
        		<a href="<%=sUrlHeader %>/board_list.jsp"><img src="commonnew/images/preview_20.gif" width="74" height="31" alt="" border="0"/></a>
        		</td>
        		</tr>
        	</table>
        </td>
      </tr>
  
</table>

<script language="JavaScript">
function getPos(el,sProp) { 
    var iPos = 0
    while (el!=null) {
        iPos+=el["offset" + sProp]
        el = el.offsetParent
    }
    return iPos

}
</script>
<!--政策法规-->
<div id="menu1" class="menu" onMouseOut="hideMe();">
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bgcolor="#99CCFF">
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				String sqlHeader = "select * from j_module where IPARENTID=4 order by IORDER asc";
				ResultSet rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left" >&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a onMouseOver="expandMenu('menu1');" href="<%=sUrlHeader %>/module_judgenew.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
		</td>
	</tr>
</table>
</div>
<!--政策法规-->

<!--专题焦点-->
<div id="menu2" class="menu" onMouseOut="hideMe();">
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bgcolor="#99CCFF">
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				sqlHeader = "select * from j_module where IPARENTID=6 order by IORDER asc";
				rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left" >&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a onMouseOver="expandMenu('menu2');" href="<%=sUrlHeader %>/module_judgenew.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
		</td>
	</tr>
</table>
</div>

<div id="menu3" class="menu" onMouseOut="hideMe();">
<table cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bgcolor="#99CCFF" >
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				sqlHeader = "select * from j_module where IPARENTID=9 order by IORDER asc";
				rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left">&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a onMouseOver="expandMenu('menu3');" href="<%=sUrlHeader %>/module_judgenew.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
	  </td>
	</tr>
</table>
</div>


<div id="menu4" class="menu" onMouseOut="hideMe();">
<table cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bgcolor="#99CCFF" >
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				sqlHeader = "select * from j_module where IPARENTID=2 order by IORDER asc";
				rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left">&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a onMouseOver="expandMenu('menu4');" href="<%=sUrlHeader %>/module_judgenew.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
	  </td>
	</tr>
</table>
</div>



</body>
