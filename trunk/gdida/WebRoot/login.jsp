<%@page contentType="text/html; charset=GBK"%>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="com.gdie.gdjrb.User"%>

<%
String username = (String)session.getAttribute("username");
if (username!=null) {
	response.sendRedirect("admin/index.jsp");
}

String flag = request.getParameter("flag");
if (flag!=null&&flag.equals("pw")) {
	username = request.getParameter("username");
	String password = request.getParameter("password");
	
	if(User.verify(username, password)) {
		session.setAttribute("userid",
			Table.getValue("j_user","iuserid","SUSERNAME='"+username+"'",""));	
		session.setAttribute("SPOWER",
			Table.getValue("j_user","spower","SUSERNAME='"+username+"'",""));
		session.setAttribute("username", username);
		response.sendRedirect("admin/index.jsp");
	} else {
		out.println("<script>alert('登录失败');location.href='login.jsp';</script>");
		//response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
	}
}
%>
<html>
<head>
<title>系统管理平台</title>
</head>
<body>
<form action="login.jsp?flag=pw" method="POST">
<div align="center">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr height="60">
            <td>            </td>
        </tr>
    </table>
    <table width="764" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="100%" valign="middle" align="center">
                <table width="669" border="0" align="center" cellspacing="0" cellpadding="0" height="70">
                    <tr align="center" height="80">
                        <td height="1" width="84">                        </td>
                        <td height="1" width="581">                        </td>
                        <td height="1" width="4">                        </td>
                    </tr>
                    <tr align="center">
                        <td width="669" colspan="3" height="8">                        </td>
                    </tr>
                    <tr>
                        <td width="84" height="338">                        </td>
                        <td width="581" height="338" valign="top">                            　
                            <!-- ImageReady Slices (login.psd) -->

                <table width="581" border="0" cellpadding="0" cellspacing="0" height="318">
                  <tr>

                    <td colspan="3" width="581" height="102" valign="bottom" background="images/admin/login_1.gif" style="background-image: url('images/admin/login_1.gif'); background-repeat: no-repeat">
                      　</td>
                                </tr>
                                <tr>

                    <td rowspan="2" width="198" height="216" valign="top"> 
					<img src="images/admin/login_2.gif" alt width="198" height="202"></td>
                                    <td width="322" height="181" valign="top">

                      <table width="303" border="0" cellpadding="0" cellspacing="0" height="157" id="table3">
                        <tr>
                          <td width="112" height="38" align="right"> <span style="font-size: 9pt">用户帐号：</span>
                          </td>
                          <td width="190" height="38"> <span style="font-size: 9pt">
                            <input type="input" name="username" value="" size="20" style="border-style:solid;	border-width:1;	cursor:default;	background-color:E7EDF5;color: 0055AB;font-family:" tahoma=", " verdana=", " 宋体="; font-size: 12px; line-height: 21px;">
                            </span> </td>
                        </tr>
                        <tr>
                          <td width="112" height="38" align="right"> <span style="font-size: 9pt">用户密码：</span>
                          </td>
                          <td width="190" height="38"> <span style="font-size: 9pt">
                            <input type="password" name="password" value="" size="20" style="border-style:solid;	border-width:1;	cursor:default;	background-color:E7EDF5;color: 0055AB;font-family:" tahoma=", " verdana=", " 宋体="; font-size: 12px; line-height: 21px;">
                            </span> </td>
                        </tr>
                     <tr>
                          <td height="43" colspan="2">
                            <table width="96%" border="0" cellpadding="0" cellspacing="0" height="36" id="table4">
                              <tr>
                                <td align="center">
                                  <input type="submit" value="登 入" name="but_submit" style="cursor:hand; width:80px; border-style:solid; border-width:1; background-color:E7EDF5; color:0055AB; font-family:" tahoma=", " verdana=", " 宋体="; font-size: 12px; line-height: 21px;">&nbsp;&nbsp;
                                  <input type="button" value="退 出" name="but_exit" onclick="javascript:navigate('<%=request.getContextPath() %>')" style="cursor:hand; width:60px; border-style:solid; border-width:1; background-color:E7EDF5; color:0055AB; font-family:" tahoma=", " verdana=", " 宋体="; font-size: 12px; line-height: 21px;">
                                </td>
                              </tr>
                            </table>
                          </td>
                        </table>
                                    </td>

                    <td rowspan="2" width="61" height="216" valign="top"> <img src="images/admin/login_4.gif" alt width="61" height="202">
                    </td>
                                </tr>
                                <tr>

                    <td width="322" height="35" valign="top"><img src="images/admin/login_5.gif" alt width="321"  height="24"></td>
                                </tr>
                            </table>
                            <!-- End ImageReady Slices -->
                        </td>
                        <td width="4" height="338">　</td>
                    </tr>
                    <tr>
                        <td width="669" colspan="3" height="5">                        </td>
                    </tr>
                    <tr align="center" height="100">
                        <td height="1" width="84">                        </td>
                        <td height="1" width="581">                        </td>
                        <td height="1" width="4">                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</form>
</body>
</html>
