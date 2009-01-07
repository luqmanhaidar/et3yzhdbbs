<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%String sUrlHeader = request.getContextPath();%><style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<table width="1000" height="191" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="800" height="168" valign="top"><img src="<%=sUrlHeader %>/images/new/index_r1_c1.jpg" /></td>
	</tr>
	
	<tr>
		<td width="1000" height="32" align="center" valign="baseline"
		 class="daohang" background="<%=sUrlHeader %>/images/new/index_r2_c2.jpg">
			<table height="35" cellpadding="0" cellspacing="0" background="<%=sUrlHeader %>/images/new/index_r2_c2.jpg">
			  <tr><td valign="middle"><a href="<%=sUrlHeader %>">网站首页</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="<%=sUrlHeader %>/module_judge.jsp?mid=1">机构介绍</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="<%=sUrlHeader %>/module_judge.jsp?mid=3">规章制度</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="<%=sUrlHeader %>/module_judge.jsp?mid=4"
			  onMouseOver="MM_showHideLayers('Layer1','','show')"
			  onMouseOut="MM_showHideLayers('Layer1','','hide')">政策法规</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="<%=sUrlHeader %>/module_judge.jsp?mid=5"
			  onMouseOver="MM_showHideLayers('Layer2','','show')"
			  onMouseOut="MM_showHideLayers('Layer2','','hide')">专题焦点</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="<%=sUrlHeader %>/module_judge.jsp?mid=6"
			  onMouseOver="MM_showHideLayers('Layer3','','show')"
			  onMouseOut="MM_showHideLayers('Layer3','','hide')">政务公开</a></td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /></td>
			  <td valign="middle"><a href="#">金融数据库</a> </td>
			  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /> </td>
			  <td valign="middle">
		  <a href="#">网站导航</a></td>
		  <td valign="top"><img src="<%=sUrlHeader %>/images/new/index_r2_c7.jpg" width="20" height="32" /> </td>
			  <td valign="middle">
		  <a href="<%=sUrlHeader %>/bbs">论坛</a></td>
			  </tr></table>		</td>
	</tr>
</table>

<!--政策法规-->
<div id="Layer1" style="position:absolute; left: 376px; top: 188px; visibility: hidden"
 onMouseOver="MM_showHideLayers('Layer1','','show')" onMouseOut="MM_showHideLayers('Layer1','','hide')">
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
					<td height="22" align="left" bgcolor="#FDD64A">&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a href="<%=sUrlHeader %>/module_judge.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
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
<div id="Layer2" style="position:absolute; left: 465px; top: 188px; visibility: hidden"
 onMouseOver="MM_showHideLayers('Layer2','','show')" onMouseOut="MM_showHideLayers('Layer2','','hide')">
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bgcolor="#D23603">
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				sqlHeader = "select * from j_module where IPARENTID=5 order by IORDER asc";
				rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left" bgcolor="#FDD64A">&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a href="<%=sUrlHeader %>/module_judge.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
		</td>
	</tr>
</table>
</div>
<!--专题焦点-->

<!--政务公开-->
<div id="Layer3" style="position:absolute; left: 554px; top: 188px; visibility: hidden"
 onMouseOver="MM_showHideLayers('Layer3','','show')" onMouseOut="MM_showHideLayers('Layer3','','hide')" >
<table cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" height="8" align="right" valign="bottom"><img src="<%=sUrlHeader %>/images/dh01.gif"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan=2 bordercolor="#000000" bgcolor="#D23603">
			<table class="daohang2" border="0" cellpadding="0" cellspacing="0">
				<%
				sqlHeader = "select * from j_module where IPARENTID=6 order by IORDER asc";
				rsHeader = Table.getRecordsBySql(sqlHeader);
				while (rsHeader.next()) {
				%>
				<tr>
					<td height="22" align="left" bgcolor="#FDD64A">&nbsp;<img src="<%=sUrlHeader %>/images/item001.gif">
					 <a href="<%=sUrlHeader %>/module_judge.jsp?mid=<%=rsHeader.getInt("IMODULEID") %>"><%=rsHeader.getString("SMODULENAME") %>&nbsp;</a>
				  </td>
				</tr>
				<%}
				Table.close(rsHeader);%>
			</table>
	  </td>
	</tr>
</table>
</div>
<!--政务公开-->

<script type="text/javascript"> 
<!--
function MM_showHideLayers() { //v6.0
	for (var i=1; i<=3; i++) {	//i<=层的数目
		layerObj=eval('Layer'+i);
		layerObj.style.visibility='hidden';
	}
	var i,p,v,obj,args=MM_showHideLayers.arguments;
	for (i=0; i<(args.length-2); i+=3)  
	if ((obj=MM_findObj(args[i]))!=null) {
		v=args[i+2];
		if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
		obj.visibility=v;
	}
}
function MM_findObj(n, d) { //v4.01
	var p,i,x;
	if (!d) d=document;
	if ((p=n.indexOf("?"))>0 && parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document;
		n=n.substring(0,p);
	}
	if (!(x=d[n]) && d.all) x=d.all[n];
	for (i=0; !x&&i<d.forms.length; i++) x=d.forms[i][n];
	for (i=0; !x&&d.layers&&i<d.layers.length; i++) x=MM_findObj(n,d.layers[i].document);
	if (!x && d.getElementById) x=d.getElementById(n);
	return x;
}
//-->
</script>

<!--map name="MapHeader1">
  <area shape="rect" coords="699,2,771,21" href="<%=sUrlHeader %>">
  <area shape="rect" coords="588,2,677,21" href="<%=sUrlHeader %>">
  <area shape="rect" coords="495,2,567,21" href="<%=sUrlHeader %>/module_judge.jsp?mid=6">
  <area shape="rect" coords="402,2,474,21" href="<%=sUrlHeader %>/module_judge.jsp?mid=5">
  <area shape="rect" coords="309,2,381,21" href="<%=sUrlHeader %>/module_judge.jsp?mid=4">
  <area shape="rect" coords="216,2,288,21" href="<%=sUrlHeader %>/module_judge.jsp?mid=3">
  <area shape="rect" coords="123,2,195,21" href="<%=sUrlHeader %>/module_judge.jsp?mid=1">
  <area shape="rect" coords="31,2,103,21" href="<%=sUrlHeader %>">
</map-->
