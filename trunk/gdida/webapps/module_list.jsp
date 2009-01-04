<%@ page buffer="none" autoFlush="true" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");
String sModuleid = request.getParameter("mid");
ResultSet rs = null;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>   
    
    <title>列表</title>
   	<link href="commonnew/gdbb.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="text-align:center">
    <table width="1002" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td colspan="2">
    			<jsp:include page="header.jsp" flush="true"/>
    		</td>
    	</tr>
    	
    	<tr>
    		<td  width="215" align="center" valign="top">
				<table width="215" border="0" cellpadding="0" cellspacing="0">
				      <tr>
				        <td height="75" align="center" valign="middle" background="images/bwh2_18.gif">
				        <table width="184" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td height="26" colspan="2">&nbsp;</td>
				            </tr>
				            
				          <tr><form name="myform" action="info_search.jsp" method="post">
				            <td><input onfocus="this.select()" name="keyword" type="text" style=" font-size:12px;width:88px; height:16px;" value="请输入关键字"/></td>
				            <td>
				            <input type="image" src="images/search.gif" width="55" height="21"/>
				            </td>
				            </form>
				          </tr>
				        </table></td>
				        </tr>
				      <tr>
				        <td align="center"><table width="183" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td height="20">&nbsp;</td>
				          </tr>
				          				          
				          <tr>
				            <td height="31" background="images/menubar.gif"><font color="#FFFFFF" style="font-size:16px"><strong><%=Table.getValue("j_module", "SMODULENAME", "IMODULEID="+sModuleid, "") %></strong></font></td>
				          </tr>
				          <tr>
				          	<td height="5"></td>
				          </tr>
				          <%
				          ResultSet menu=null;
				          String menusql;
				          
				          if(session.getAttribute("USERNAME")==null){
				          	  menusql="select * from j_module where ISPUBLIC='Y' AND IPARENTID="+sModuleid;
				          }else{				        	  
				        	  menusql="select * from j_module where IPARENTID="+sModuleid;
				          }
				          menu=Table.getRecordsBySql(menusql);	
				          while(menu.next()){ %>
					        
				          <tr>
				            <td height="26" background="images/menubarli.gif">
				            	<a href="module_judge.jsp?mid=<%=menu.getInt("IMODULEID") %>"><%=menu.getString("SMODULENAME") %></a>
				            </td>
				          </tr>
				          <tr>
				          	<td height="5"></td>
				          </tr>
				         
				         <%}
				          if(menu!=null) Table.close(menu);%>
				         
				        </table></td>
				        </tr>
				    <tr>
        <td align="center">&nbsp;</td>
        </tr>
      <tr>
        <td align="center"><img src="images/bwh2_56.gif" width="190" height="55" alt="" /></td>
        </tr>
      <tr>
        <td align="center">&nbsp;</td>
        </tr>
      <tr>
        <td align="center"><img src="images/bwh2_59.gif" width="190" height="50" alt="" /></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><table width="81%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/bwh2_62.gif" width="190" height="50" alt="" /></td>
          </tr>
          <tr>
            <td align="center" background="images/bwh2_67.gif"><table width="90%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <td><p>文字内容文字内容文字内容<br />
                文字内容文字内容文字内容<br />
                文字内容文字内容<br />
                文字内容文字内容<br />
                  <br />
                文字内容文字内容文字内容<br />
                文字内容</p>
                  </td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><img src="images/bwh2_74.gif" width="190" height="58" alt="" /></td>
          </tr>
        </table></td>
      </tr> 	     
	</table>

			</td>
    		<td width="800" valign="top" align="center">
    		<%
    		
    		String sql;
    		if(session.getAttribute("USERNAME")==null){
    		sql="select * from j_module where ISPUBLIC='Y' AND  IPARENTID="+sModuleid;	
    		}else{
    		sql="select * from j_module where IPARENTID="+sModuleid;
    		}
    		
    		rs=Table.getRecordsBySql(sql);
    		String subsql;
    		ResultSet subrs=null;
    		while(rs.next()){
    			if(!Module.hasSon(Integer.parseInt(rs.getString("IMODULEID")))){
    		%>
    		<table width="752" border="0">
    		
    		<tr>
    			<a href="module_judge.jsp?mid=<%=rs.getString("IMODULEID") %>">
    			<td  align="left" background="images/longbar.gif" height="33" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="#FFFFFF" style="font-size:14px"><strong><%=Table.getValue("j_module", "SMODULENAME", "IMODULEID="+rs.getString("IMODULEID"), "") %></strong></font>
				</td>
				</a>
    		</tr>
    		 
                <tr><td>
                <table width="100%">
    			<%
    			subsql="SELECT top 5 * FROM j_information WHERE ISOK='Y' AND IMODULEID="+rs.getString("IMODULEID") +" order by IINFOID DESC";
    			
    			subrs=Table.getRecordsBySql(subsql);
    			
    			while(subrs.next()){
    				
    			%>
    			
    			<tr>
    				<td height="25" width="5"><img src="images/point.gif"/></td>
    				<td align="left"><a href="info_detail.jsp?infoid=<%=subrs.getInt("IINFOID") %>" target="_blank"><%=subrs.getString("SINFOTITLE") %></a></td>
    				<td width="100"><%=subrs.getDate("DPUBDATE") %></td>
    			</tr>
    			
    			<%} %>
    			 <tr><td height="20"></td></tr>
    			 </table></td></tr>    			 
    		</table>
    		<%}
    		
    		}
    		if(subrs!=null) Table.close(subrs);
    		%>
    		</td>
    	
    	</tr>
    	<tr>
    		<td colspan="2">
    			<jsp:include page="footer.jsp" flush="true"/>
    		</td>
    	</tr>
    			
    </table>
  </body>
</html>
<% if (rs != null) Table.close(rs); 
  
%>
<script LANGUAGE="JavaScript">
	//转页
	function goPage(pageNo) {
		document.myform.pageno.value = pageNo;
		document.myform.submit();
	}
</script>