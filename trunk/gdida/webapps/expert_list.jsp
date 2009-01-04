<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<%


String sCondition = "";

//要显示的页数
int pageNo = 0;
try{
  pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
  pageNo = 1;
}
int rowCount = Table.getRecordCount("expert", "1=1 " + sCondition);
String strSql = "SELECT * FROM expert WHERE id>0 " + sCondition +" order by id DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//分页对象
Page objPage = new Page(rowCount,10,pageNo);
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
                    <tr>
                      <td width="43%" align="center"><img src="images/gdida_sub_25.gif" width="72" height="70" /></td>
                      <td width="57%" align="left" valign="top" style="line-height:20px" ><a href="#" style="color:#CB0000"><strong>思想大解放，推动大发展</strong></a></td>
                    </tr>
                    <tr>
                      <td colspan="2" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td width="8%"><img src="images/dot.gif" width="3" height="3" /></td>
                          <td width="92%" align="left">广西缴获近八百公斤疑走私</td>
                        </tr>
                        <tr>
                          <td><img src="images/dot.gif" width="3" height="3" /></td>
                          <td align="left">广西缴获近八百公斤疑走私</td>
                        </tr>
                        <tr>
                          <td><img src="images/dot.gif" width="3" height="3" /></td>
                          <td align="left">广西缴获近八百公斤疑走私</td>
                        </tr>
                        <tr>
                          <td width="8%"><img src="images/dot.gif" width="3" height="3" /></td>
                          <td width="92%" align="left">广西缴获近八百公斤疑走私</td>
                        </tr>
                        <tr>
                          <td><img src="images/dot.gif" width="3" height="3" /></td>
                          <td align="left">广西缴获近八百公斤疑走</td>
                        </tr>
                        <tr>
                          <td><img src="images/dot.gif" width="3" height="3" /></td>
                          <td align="left">广西缴获近八百公斤疑走私</td>
                        </tr>
                        <tr>
                          <td width="8%"><img src="images/dot.gif" width="3" height="3" /></td>
                          <td width="92%" align="left">广西缴获近八百公斤疑走私</td>
                        </tr>

                      </table></td>
                    </tr>
                    
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
          <tr>
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="images/gdida_sub_a.gif" width="200" height="38" alt="" /></td>
                </tr>
                <tr>
                  <td height="141" valign="top" background="images/gdida_sub_aa.gif"><table width="96%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td width="29%" align="right">用户名</td>
                      <td width="71%"><input style="width:100px" type="text" name="textfield" id="textfield" /></td>
                    </tr>
                    <tr>
                      <td align="right">密&nbsp; 码</td>
                      <td><input style="width:100px" type="text" name="textfield2" id="textfield2" /></td>
                    </tr>
                    <tr>
                      <td align="right">&nbsp;</td>
                      <td><select name="select5" id="select5">
                          <option>网站会员用户</option>
                        </select>                      </td>
                    </tr>
                    <tr>
                      <td align="right">&nbsp;</td>
                      <td><img src="images/login.gif" width="60" height="22" /> <img src="images/register.gif" width="55" height="22" /></td>
                    </tr>
                    <tr>
                      <td align="right">&nbsp;</td>
                      <td><a style="color:#CB0000" href="#">忘记密码？</a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
          </tr>
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
                  <td width="760" align="left" bgcolor="#f7f3ef">&nbsp;您现在：<a href='index.jsp'>广东省产业发展研究院首页 </a> > <a href="expert_list.jsp">产业专家</a></td>
                </tr>
                <tr>
                  <td colspan="2" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td align="center"><table width="100%"  border="0" cellspacing="0" cellpadding="7">
                        <form name="myform" action="expert_list.jsp" method="post">
                        <tr>
                          <td height="10" colspan="2" align="center"></td>
                        </tr>
                        <%
                        boolean hasData=false;
						for (int i=0;i<viewCount;i++) {
						if (rs.next()) {hasData=true;
						%>
                        <tr>
                         <td><table width="350" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td align="center"><img src="images/gdida_sub1_11.gif" width="350" height="8" /></td>
                          </tr>
                          <tr>
                            <td align="center" background="images/gdida_sub1_13.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="30%" rowspan="7" align="center">
                               
                                <%
									if(rs.getString("photo")==null||rs.getString("photo").trim().equals("")){
									%>
									<img width="90" height="120" src="images/gdida_sub1_15.gif" name="expertphoto">
									<%	
									}else{
								%>
								<img width="90" height="120" src="admin/attachment/upload/<%=rs.getString("photo") %>" name="expertphoto">
								<%} %>
                                </td>
                                <td width="70%" height="27" align="left"><span class="STYLE1"><%=rs.getString("name") %></span></td>
                              </tr>
                              <tr>
                                <td align="left"><img src="images/gdida_sub1_18.gif" width="223" height="1" /></td>
                              </tr>
                              <tr>
                                <td height="19" align="left" valign="middle"><%=rs.getString("position") %></td>
                              </tr>
                              <tr>
                                <td height="18" align="left"><img src="images/gdida_sub1_18.gif" width="223" height="1" /></td>
                              </tr>
                              <tr>
                                <td height="23" align="left"><%=Page.leftStr(rs.getString("des"),70) %></td>
                              </tr>
                              <tr>
                                <td height="19" align="left"><img src="images/gdida_sub1_22.gif" width="16" height="13" />
                                <%=rs.getString("tel") %>
                                </td>
                              </tr>
                              <tr>
                                <td align="left"><img src="images/gdida_sub1_25.gif" width="16" height="11" />
                                <%=rs.getString("email") %>
                                </td>
                              </tr>
							  <tr>
							  <td height="5" colspan="2">							  </td>
							  </tr>
                            </table></td>
                          </tr>
                          <tr>
                            <td align="center"><img src="images/gdida_sub1_28.gif" width="350" height="4" /></td>
                          </tr>
                        </table>
                         </td>
                         
                         <td>
                         	<% if(rs.next()){i++; %>
                         	
                         	<table width="350" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td align="center"><img src="images/gdida_sub1_11.gif" width="350" height="8" /></td>
                          </tr>
                          <tr>
                            <td align="center" background="images/gdida_sub1_13.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="30%" rowspan="7" align="center">
                               
                                <%
									if(rs.getString("photo")==null||rs.getString("photo").trim().equals("")){
									%>
									<img width="90" height="120" src="images/gdida_sub1_15.gif" name="expertphoto">
									<%	
									}else{
								%>
								<img width="90" height="120" src="admin/attachment/upload/<%=rs.getString("photo") %>" name="expertphoto">
								<%} %>
                                </td>
                                <td width="70%" height="27" align="left"><span class="STYLE1"><%=rs.getString("name") %></span></td>
                              </tr>
                              <tr>
                                <td align="left"><img src="images/gdida_sub1_18.gif" width="223" height="1" /></td>
                              </tr>
                              <tr>
                                <td height="19" align="left" valign="middle"><%=rs.getString("position") %></td>
                              </tr>
                              <tr>
                                <td height="18" align="left"><img src="images/gdida_sub1_18.gif" width="223" height="1" /></td>
                              </tr>
                              <tr>
                                <td height="23" align="left"><%=Page.leftStr(rs.getString("des"),70) %></td>
                              </tr>
                              <tr>
                                <td height="19" align="left"><img src="images/gdida_sub1_22.gif" width="16" height="13" />
                                <%=rs.getString("tel") %>
                                </td>
                              </tr>
                              <tr>
                                <td align="left"><img src="images/gdida_sub1_25.gif" width="16" height="11" />
                                <%=rs.getString("email") %>
                                </td>
                              </tr>
							  <tr>
							  <td height="5" colspan="2">							  </td>
							  </tr>
                            </table></td>
                          </tr>
                          <tr>
                            <td align="center"><img src="images/gdida_sub1_28.gif" width="350" height="4" /></td>
                          </tr>
                        </table>
                         	<%} %>
                         </td>
                        </tr>
                        <%} }if (rs != null) Table.close(rs); %>
                        <%
                        	if(!hasData){
                        %>
                        <tr>
                          <td  colspan="2" align="center">暂无数据</td>
                        </tr>
                        <%} %>
                        <tr>
                          <td  colspan="2" align="center"></td>
                        </tr>
                        <tr>
                          <td colspan="2" align="right" bgcolor="#f7f3ef" >
								共<%=rowCount %>位专家&nbsp;&nbsp;
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