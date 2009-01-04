<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Information" scope="page"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>广东省产业发展研究院</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" /></head>
<%


String username = (String) session.getAttribute("username");

int infoid = Integer.parseInt(request.getParameter("infoid"));
String sModuleid=Table.getValue("j_information","IMODULEID","IINFOID="+infoid,"");
if (!gdie.findRecord(infoid)) {
	throw new Exception("找不到记录");
}

//****************信息点击率***************
if(session.getAttribute(request.getServletPath()+request.getQueryString())==null)
{
	Table.setHit("j_information","hit","hit+1","IINFOID="+infoid);
	session.setAttribute(request.getServletPath()+request.getQueryString(),"1");
}
//****************信息点击率****************

String contents="";//内容
String pages="";//页码
String con0="";//第一页内容
int pagenum=0;//内容页数

int iInfolength = 1;
iInfolength = gdie.getVInfo().size();
for (int i=0; i<iInfolength; i++) {
	if (i==0) con0=gdie.getVInfo().elementAt(0).toString().replaceAll("\"", "&quot;");
	contents += "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + (i+1) + "\" value=\""
			+ gdie.getVInfo().elementAt(i).toString().replaceAll("\"", "&quot;") + "\" />";
	if (iInfolength>1) {
		if (i==0)
			pages += "&nbsp;<" + (i+1) + ">&nbsp;";
		else
			pages += "<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: showValue(" + (i+1) + ")\">&nbsp;<" + (i+1) + ">&nbsp;</a>";
	}
}
pagenum = iInfolength + 1;
contents += "<input type=\"hidden\" id=\"pagenum\" value=\"" + pagenum + "\" />";
out.println(contents);
%>
<body>
 <jsp:include page="header.jsp" flush="true"/>
<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">
 
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top" background="images/gdida_sub_37.gif"  ><table height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/gdida_sub_15.gif" width="200" height="55" alt="" /></td>
          </tr>
          <tr>
            <td><img src="images/gdida_sub_20.gif" width="200" height="56" alt="" /></td>
          </tr>
          <tr>
            <td>
            <img src="images/gdida_sub_21.gif" width="200" height="36" alt="" /><br>
           


<table border="0" style="table-layout: fixed;">
            <%      String sql="";//热点新闻 点击排行
                   ResultSet rsNews = null;
            //1个图片新闻   		
            sql=sql+"select * from j_information where (IMODULEID=8 or IMODULEID=9 or IMODULEID=11) and ISOK='Y' and SIFPIC='Y' order by hit DESC,IINFOID desc limit 1  ";
            rsNews=Table.getRecordsBySql(sql);
              		int picInt=0;
              		int IINFOID=0;
               		while(rsNews.next()){
               			picInt=picInt+1;  
               			IINFOID=rsNews.getInt("IINFOID") ;
            	%>            	
                    <tr><td align="center" valign="middle"><img src="<%="admin/attachment/upload/"+rsNews.getString("SATTM") %>" width="75" height="75" /></td>
                    <td align="left"><a href="info_detail.jsp?infoid=<%=rsNews.getInt("IINFOID") %>" style="color:#cc0000"><strong><%=Page.leftStr(rsNews.getString("SINFOTITLE"),40) %></strong></a></td></tr>
               <%}
               		if(rsNews!=null){
               			Table.close(rsNews);
               		}
               	out.print("</table>");
               	out.print("<table border='0' style='table-layout: fixed;'>");
               		//后续标题新闻
               		if (picInt>0){               			
               		sql="select * from j_information where (IMODULEID=8 or IMODULEID=9 or IMODULEID=11) and ISOK='Y' and IINFOID<>"+IINFOID+"   order by hit DESC,  IINFOID desc limit 6"; 
                    }else{
                    sql="select * from j_information where (IMODULEID=8 or IMODULEID=9 or IMODULEID=11) and ISOK='Y'    order by hit DESC,  IINFOID desc limit 8"; 
                    }
               		rsNews=Table.getRecordsBySql(sql);
               		while(rsNews.next()){
            	%>            	
               <tr height="20">
               	<td width="15" align="center"><img src="images/dot.gif"></img></td>
               	<td align="left"  nowrap valign="middle"><a href="info_detail.jsp?infoid=<%=rsNews.getInt("IINFOID") %>"><%=Page.leftStr(rsNews.getString("SINFOTITLE"),40) %></a></td>
               </tr>
                <%}
               		if(rsNews!=null){
               			Table.close(rsNews);
               }%> 
            </table>

</td>
              </tr>

            </table>
            </td>
          </tr>
          <%@include file="user_front_login.html" %>
          

        </table></td>
        <td width="80%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="2">             
            <tr>
              <td colspan="2" align="center" valign="top"><table width="99%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="34" height="26" align="center" bgcolor="#f7f3ef"><img src="images/gdida_sub_30.gif" width="17" height="17" /></td>
                  <td width="760" align="left" bgcolor="#f7f3ef">&nbsp;您现在：<%=Module.getPath(Integer.parseInt(sModuleid)) %></td>
                </tr>
               
                <tr>
                  <td colspan="2" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <%if (gdie.getIModuleId()!=2){ //机构职能等不显示信息来源及标题%> 
                    <tr>
                    	<td style="word-break:break-all" ><br>
                    	<span><h2><%=gdie.getInfoTitle() %></h2></span> 
                    	</td>
                    </tr>
             
                    <tr>
                    	<td>
                    		<div>
                    			<a href="http://www.gid.gov.cn">http://www.gid.gov.cn</a>&nbsp;&nbsp;&nbsp;&nbsp;<%=gdie.getPubDate()%>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#aaaaaa">来源：广东省产业发展研究院</font>
                    		</div>
                    	</td>
                    </tr>
                 <%} %>
                    <tr><td>&nbsp;</td></tr>
                    <tr>
                      <td align="left"><font class="f14">
                      <div id='bodytext'></div>
                    </font></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="2">&nbsp;</td>
                </tr>
              </table></td>
              </tr>
          </table></td></tr>
    </table></td>
  </tr>
  
</table>
<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">
<jsp:include page="footer.jsp" flush="true"/>
</table>
</body>
</html>
<script>
function showValue(v){
	with(document.all){
		eval("bodytext.innerHTML=bodytext"+v+".value;");
		var pp=pagenum.value;
		var innerHtml="";
		for (var i=1; i<pp; i++) {
			if (v == i) {
				innerHtml += "&nbsp;<" + i + ">&nbsp;";
			} else {
				innerHtml += "<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: showValue(" + i + ");\">&nbsp;<" + i + ">&nbsp;</a>";
			}
		}
		if(pp>2){
			page.innerHTML=innerHtml;
		}
	}
}
showValue(1);
</script>