<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<%
String username = (String) session.getAttribute("username");
String identify = request.getParameter("identify");
String sModuleid=Module.getMidByIdentify(identify);
boolean iflogin=false;
if(session.getAttribute("userid")!=null)
{
	iflogin=true;
}
//要显示的页数
int pageNo = 0;
try{
  pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
  pageNo = 1;
}
int rowCount = Table.getRecordCount("j_project", "PROJECTID>0 AND ISOK='Y' AND IMODULEID="+sModuleid+"");
String strSql = "SELECT * FROM j_project WHERE PROJECTID>0 AND ISOK='Y' AND IMODULEID="+sModuleid+" order by PROJECTID DESC";
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
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {color: #0000FF}
-->
</style>
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top" background="images/gdida_sub_37.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/gdida_sub_02.gif" width="200" height="57" /></td>
          </tr>
          <tr>
            <td><img src="images/gdida_sub_15.gif" width="200" height="55" alt="" /></td>
          </tr>
          <tr>
            <td><img src="images/gdida_sub_20.gif" width="200" height="56" alt="" /></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/gdida_sub_21.gif" width="200" height="36" alt="" /></td>
              </tr>
              <tr>
                <td align="center" valign="top" background="images/gdida_sub_22.gif"><table width="90%" border="0" cellspacing="0" cellpadding="3">
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
            </table></td>
              </tr>
              <tr>
                <td><img src="images/gdida_sub_b.gif" width="200" height="8" alt="" /></td>
              </tr>
              <tr>
                <td height="5" bgcolor="#F7F3EF"></td>
              </tr>
            </table></td>
          </tr>
          <%@include file="user_front_login.html" %>
          

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
                  <td width="760" align="left" bgcolor="#f7f3ef">&nbsp;您现在：<a href='index.jsp'>广东省产业发展研究院首页 
                  	</a> > <a href='project_list.jsp?identify=PROJECT_CAR'>项目直通车</a></td>
                </tr>
                <tr>
                  <td colspan="2" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td align="center"><table width="100%"  border="0" cellspacing="0" cellpadding="7">
                        <form name="myform" action="project_list.jsp?identify=PROJECT_CAR" method="post">
                        <tr>
                          <td height="10" colspan="4" align="right"><input type="button" value="提交新项目" <%if(iflogin==true){ %>onclick="location='project_add.jsp'"<%}else{ %>onclick="alert('你还没有登陆,请先登陆了再提交新项目')"<%} %>/></td>
                        </tr>
                        <%
                        boolean hasData=false;
						for (int i=0;i<viewCount;i++) {
						if (rs.next()) {hasData=true;
						%>
                        <tr>
                          <td width="12%" align="right"><img src="images/gdida_sub_dot.gif" width="4" height="4" /></td>
                          <td width="42%" align="left"><span class="STYLE1"><a href="project_detail.jsp?projectId=<%=rs.getInt("projectid") %>"><%=Page.leftStr(rs.getString("PROJECTTITLE"),60) %></a></span></td>
                          <td width="22%" align="center"><span class="STYLE2">(<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日");
                								%>)</span></td>
                          <td width="24%" align="center"><span class="STYLE1"><a href="project_detail.jsp?projectId=<%=rs.getInt("projectid") %>"><font color="#0000FF">我要该项目</font></a></span></td>
                        </tr>
                        <tr height="0">
                        <td colspan="4" height="0" bgcolor="#f7f3ef"></td>
                        </tr>
                        <%} }if (rs != null) Table.close(rs); %>
                        <%
                        	if(!hasData){
                        %>
                        <tr>
                          <td  colspan="4" align="center">暂无数据</td>
                        </tr>
                        <%} %>
                        <tr>
                          <td  colspan="4" align="center"></td>
                        </tr>
                        <tr>
                          <td colspan="4" align="right" bgcolor="#f7f3ef" >
								共<%=rowCount %>条记录&nbsp;&nbsp;
					          	第<input type="text" name="pageno" size="4" class="int" value="<%=pageNo %>">/<%=pageCount %>页
								<input type="submit" name="but_gopage" value="转到" style="cursor:hand;">
							  	<%
							  		out.println("<a href='javascript:goPage(1)'>第一页</a>");
							  		out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>上一页</a>");
									out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>下一页</a>");
						       		out.println("<a href='javascript:goPage(" + (pageCount) + ")'>最后一页</a>");
								%>							</td>
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