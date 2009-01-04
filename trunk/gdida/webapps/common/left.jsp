<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.db.Table"%>
<jsp:useBean id="simplestatis" class="com.gdie.web.SimpleStatistic" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<%String sUrlLeft = request.getContextPath(); %>
<LINK href="<%=sUrlLeft %>/common/public.css" type=text/css rel=stylesheet>
<link href="public.css" rel="stylesheet" type="text/css" />
<table width="237" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=sUrlLeft %>/images/new/index_r19_c1.jpg" class="daohang">
  <tr>
  	<td height="8"></td>
  </tr>
  <tr>
	<td width="169" height="96" align="left" valign="top">
	  <table width="237" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="24" align="left"><img src="<%=sUrlLeft %>/images/new/index_r3_c1.jpg" width="126" height="35"></td>
		</tr>
    	<%
    	String sqlLeft = "select * from j_module where IPARENTID=2 order by IORDER asc";
    	ResultSet rsLeft = Table.getRecordsBySql(sqlLeft);
    	while (rsLeft.next()) {
    	%>
		<tr height="4"><td></td></tr>
    	<tr height="20">
          <td width="100%" align="left" valign="middle"><img src="<%=sUrlLeft %>/images/new/index_r19_c1_r15_c3.jpg" width="18" height="20" border="0">
          	<a title="<%=rsLeft.getString("SMODULENAME") %>" href="<%=sUrlLeft %>/module_judge.jsp?mid=<%=rsLeft.getInt("IMODULEID") %>">
       	    <span class="daohang"><%=rsLeft.getString("SMODULENAME") %></span></a></td>
        </tr>
    	<%}
    	Table.close(rsLeft);%>
    </table>	</td>
  </tr>
  <tr>
  	<td height="8"></td>
  </tr>
  <tr>
	<td width="169" height="144" valign="top">
	<table width="237" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="24" colspan="2" align="left"><img src="<%=sUrlLeft %>/images/new/index_r6_c1.jpg" width="126" height="35"></td>
      </tr>
      <%
    	sqlLeft = "select * from j_module where IPARENTID=4 order by IORDER asc";
    	rsLeft = Table.getRecordsBySql(sqlLeft);
    	while (rsLeft.next()) {
    	%>
		<tr height="4"><td colspan="2"></td></tr>
    	<tr height="20">
          
          <td width="237%" align="left" valign="middle"><img src="<%=sUrlLeft %>/images/new/index_r19_c1_r15_c3.jpg" width="18" height="20" border="0" /><a title="<%=rsLeft.getString("SMODULENAME") %>" href="<%=sUrlLeft %>/module_judge.jsp?mid=<%=rsLeft.getInt("IMODULEID") %>"  >
			<span  class="daohang"><%=rsLeft.getString("SMODULENAME") %></span></a></td>
        </tr>
      <%}
    	Table.close(rsLeft);%>
    </table></td>
  </tr>
  <tr>
  	<td height="8"></td>
  </tr>
  <tr>
	<td width="169" height="120" valign="top">
	  <table width="237" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="24" colspan="2" align="left"><img src="<%=sUrlLeft %>/images/new/index_r11_c1.jpg" width="126" height="35"></td>
		</tr>
    	<%
    	sqlLeft = "select * from j_module where IPARENTID=5 order by IORDER asc";
    	rsLeft = Table.getRecordsBySql(sqlLeft);
    	while (rsLeft.next()) {
    	%>
		<tr height="4"><td colspan="2"></td></tr>
    	<tr height="20">
         
          <td width="100%" align="left" valign="middle"><img src="<%=sUrlLeft %>/images/new/index_r19_c1_r15_c3.jpg" width="18" height="20" border="0" /><a href="<%=sUrlLeft %>/module_judge.jsp?mid=<%=rsLeft.getInt("IMODULEID") %>" title="<%=rsLeft.getString("SMODULENAME") %>" class="daohang">
		  <span class="daohang"><%=rsLeft.getString("SMODULENAME") %></span></a></td>
        </tr>
    	<%}
    	Table.close(rsLeft);%>
    </table>	</td>
  </tr>
  <tr>
  	<td height="8"></td>
  </tr>
  <tr>
	<td width="169" height="96" valign="top">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="24" align="left"><img src="<%=sUrlLeft %>/images/new/index_r17_c1.jpg" width="126" height="35"></td>
		</tr>
    	<%
    	sqlLeft = "select * from j_module where IPARENTID=6 order by IORDER asc";
    	rsLeft = Table.getRecordsBySql(sqlLeft);
    	while (rsLeft.next()) {
    	%>
		<tr height="4"><td></td></tr>
    	<tr height="20">
          <td width="100%" align="left" valign="middle"><img src="<%=sUrlLeft %>/images/new/index_r19_c1_r15_c3.jpg" width="18" height="20" border="0" /><a title="<%=rsLeft.getString("SMODULENAME") %>" href="<%=sUrlLeft %>/module_judge.jsp?mid=<%=rsLeft.getInt("IMODULEID") %>">
          	<span  class="daohang"><%=rsLeft.getString("SMODULENAME") %></span></a></td>
        </tr>
    	<%}
    	Table.close(rsLeft);%>
	  </table>
	</td>
  </tr>
  <tr>
	<td width="169" height="12"></td>
  </tr>
  <tr>
	<td width="169" height="20" valign="middle">
	  <table width="169" align="center" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		  <td width="169" align="center" valign="middle">
		  您是第<%
			simplestatis.visit(request);
			String num = String.valueOf(simplestatis.getCounter());
			String numimg = "";
			for (int i=0;i<num.length();i++) {
				numimg += "<img src='"+sUrlLeft+"/images/"+num.substring(i,(i+1))+".gif' border=0>";
			}
			%><%=numimg %>位访客</td>
		</tr>
	  </table>
	</td>
  </tr>
</table>
