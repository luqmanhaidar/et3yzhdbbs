<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<%

String identify = request.getParameter("identify");
String username = (String) session.getAttribute("username");


//System.out.println("value="+Table.getValue("j_module","iparentid","identify='NEWS_CENTER'",""));

String sCondition = "";
String sModuleid=Module.getMidByIdentify(identify);
//System.out.println();

if (Module.hasSon(Integer.parseInt(sModuleid))) {
	sCondition = sCondition + " AND ISOK='Y' AND IMODULEID in (select IMODULEID from j_module WHERE IPARENTID ="+sModuleid+")";
} else {
	sCondition = sCondition + " AND ISOK='Y' AND IMODULEID="+sModuleid;
}


//要显示的页数
int pageNo = 0;
try{
  pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
  pageNo = 1;
}
int rowCount = Table.getRecordCount("j_information", "1=1 " + sCondition);
String strSql = "SELECT * FROM j_information WHERE IINFOID>0 " + sCondition +" order by DING DESC, IINFOID DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//分页对象
Page objPage = new Page(rowCount,20,pageNo);
//总页数
int pageCount = objPage.getPageCount();
//当前页号
pageNo = objPage.getPageNo();
//开始条数
int begin = objPage.getBegin();
//显示条数
int viewCount = objPage.getViewCount();
//滚动记录集
if (begin > 0)
  rs.absolute(begin);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>广东省产业发展研究院</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" /></head>
<body><jsp:include page="header.jsp" flush="true"/>
<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">
 <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top" background="images/gdida_sub_37.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/gdida_sub_15.gif" width="200" height="55" alt="" /></td>
          </tr>
          <tr>
            <td><img src="images/gdida_sub_20.gif" width="200" height="56" alt="" /></td>
          </tr>
          <tr><td>
          <img src="images/gdida_sub_21.gif"><br>
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
          </td></tr> 
              
              <%@include file="user_front_login.html" %>
              
             
          <tr>
            <td>&nbsp;</td>
          </tr>
          

        </table></td>
        <td width="80%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="2">
            <tr>
              <td colspan="2" align="center"><table width="99%" border="0" cellspacing="0" cellpadding="0">
               
              </table></td>
          </tr>             
            <tr>
              <td colspan="2" align="center" valign="top"><table width="99%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="34" height="26" align="center" bgcolor="#f7f3ef"><img src="images/gdida_sub_30.gif" width="17" height="17" /></td>
                  <td width="760" align="left" bgcolor="#f7f3ef">&nbsp;您现在：<%=Module.getPath(Integer.parseInt(sModuleid)) %></td>
                </tr>
                <tr>
                  <td colspan="2" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td align="center"><table width="100%"  border="0" cellspacing="0" cellpadding="7">
                        <form name="myform" action="info_list.jsp?identify=<%=request.getParameter("identify") %>" method="post">
                        <tr>
                          <td height="10" colspan="3" align="center"></td>
                        </tr>
                        <%
                        boolean hasData=false;
						for (int i=0;i<viewCount;i++) {
						if (rs.next()) {hasData=true;
						%>
                        <tr>
                          <td width="7%" align="right"><img src="images/gdida_sub_dot.gif" width="4" height="4" /></td>
                          <td width="74%" align="left"><%
													  int ding=rs.getInt("ding");
													  if(ding!=0){
													  %>
													  <img src="images/ding.gif">
													  <%} %>
						  <span class="STYLE1"><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>"><%=Page.leftStr(rs.getString("sinfotitle"),60) %></a></span>
                          
                          </td>
                          <td width="19%" align="center"><span class="STYLE2"> [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print((date.getMonth()+1)+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]</span></td>
                        </tr>
                        <%} }if (rs != null) Table.close(rs); %>
                        <%
                        	if(!hasData){
                        %>
                        <tr>
                          <td  colspan="3" align="center">暂无数据</td>
                        </tr>
                        <%} %>
                        <tr>
                          <td  colspan="3" align="center"></td>
                        </tr>
                        <tr>
                          <td colspan="3" align="right" bgcolor="#f7f3ef" >
								共<%=rowCount %>条记录&nbsp;&nbsp;
					          	第<input type="text" name="pageno" size="4" class="int" value="<%=pageNo %>">/<%=pageCount %>页
								<input type="submit" name="but_gopage" value="转到" style="cursor:hand;">
							  	<%
							  		out.println("<a href='javascript:goPage(1)'>第一页</a>");
							  		out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>上一页</a>");
									out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>下一页</a>");
						       		out.println("<a href='javascript:goPage(" + (pageCount) + ")'>最后一页</a>");
								%>
							</td>
                          </tr>
                          </form>
                      </table></td>
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
<script LANGUAGE="JavaScript">
	//转页
	function goPage(pageNo) {
		document.myform.pageno.value = pageNo;
		document.myform.submit();
	}
</script>