<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.gdie.gdjrb.User"%>
<%
	String username = (String) session.getAttribute("username");
	String flag = request.getParameter("flag");
	if (flag != null && flag.equals("pw")) {
		username = request.getParameter("username");
		String password = request.getParameter("password");

		if (User.verify(username, password)) {
			session.setAttribute("userid", Table.getValue("j_user",
			"iuserid", "SUSERNAME='" + username + "'", ""));
			session.setAttribute("SPOWER", Table.getValue("j_user",
			"spower", "SUSERNAME='" + username + "'", ""));
			session.setAttribute("username", username);
			response.sendRedirect("index.jsp");
		} else {
			out
			.println("<script>alert('登录失败');location.href='index.jsp';</script>");
			//response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>--广东省产业发展研究院--</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="style/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">  
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top"><table width="69%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="3" align="right"><img src="images/index_11.gif" width="204" height="44" alt="" /></td>
            </tr>
          <tr>
            <td width="5%" align="center">&nbsp;</td>
            <td width="89%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
                
               <%
               		String sql="select * from jview_information where IDENTIFY='NOTIFY' order by IINFOID desc limit 10";
               		ResultSet rs=null;
               		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
              <tr>
                <td width="8%"><img src="images/dot.gif" width="3" height="3" /></td>
                <td width="92%" align="left"><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>"><%=Page.leftStr(rs.getString("SINFOTITLE"),20) %></a></td>
              </tr>
             <%} 
					if(rs!=null){
						Table.close(rs);
					}
				%>
            </table></td>
            <td width="6%" align="center">&nbsp;</td>
          </tr>
        </table></td>
        <td width="321"  align="center" valign="middle" background="images/index_12.gif">
			  <SCRIPT type=text/javascript>
				    <!--
				    
				    var focus_width=280
				    var focus_height=210
				    var text_height=20
				    var swf_height = focus_height+text_height
				    
				    
				    
				    var pics=""
				    var links=""
				    var texts=""
				    
				     <%
							          sql = "select * from j_information where SIFPIC='Y' and SATTM is not null order by IINFOID desc limit 4";
							          rs = Table.getRecordsBySql(sql);
							          boolean tempB=false;
							          for(int i=0;i<4;i++){
							          	if(rs.next()){
							          		if(tempB){
								          		%>
								          		pics=pics+"|";
								          		links=links+"|";
								          		texts=texts+"|";
							          		<%			          		
							          		}else{
							          			tempB=true;
							          		}
							          	
							          	%>
							          		pics=pics+"<%="admin/attachment/upload/"+rs.getString("SATTM") %>"
							          		links=links+"<%="info_detail.jsp?infoid="+rs.getInt("IINFOID") %>"
							          		texts=texts+"<%=Page.leftStr(rs.getString("SINFOTITLE"),20) %>"
							          	<%				        
							          	}
							          
							          }
										if(rs!=null){
											Table.close(rs);
										}
									%>
				    
				    document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ');
					document.write('codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" ');
					document.write('width="'+ focus_width +'" ');
					document.write('height="'+ swf_height +'">');
					document.write('<param name="allowScriptAccess" value="sameDomain">');
					document.write('<param name="movie" value="focus.swf">');
					document.write('<param name="quality" value="high">');
					document.write('<param name="bgcolor" value="#ffffff">');
					document.write('<param name="menu" value="false">');
					document.write('<param name=wmode value="opaque">');
					document.write('<param name="FlashVars" ');
					document.write('value="pics='+pics);
					document.write(		'&links='+links);
					document.write(		'&texts='+texts);
					document.write(		'&borderwidth='+focus_width);
					document.write(		'&borderheight='+focus_height);
					document.write(		'&textheight='+text_height+'">');
					document.write('<embed src="focus.swf" ');
					document.write('wmode="opaque" ');
					document.write('FlashVars="pics='+pics);
					document.write(		'&links='+links);
					document.write(		'&texts='+texts);
					document.write(		'&borderwidth='+focus_width);
					document.write(		'&borderheight='+focus_height);
					document.write(		'&textheight='+text_height+'" ');
					document.write('menu="false" ');
					document.write('bgcolor="#ffffff" ');
					document.write('quality="high" ');
					document.write('width="'+ focus_width +'" ');
					document.write('height="'+ focus_height +'" ');
					document.write('allowScriptAccess="sameDomain" ');
					document.write('type="application/x-shockwave-flash" ');
					document.write('pluginspage="http://www.macromedia.com/go/getflashplayer" />');
					document.write('</object>');
				    
				    //-->
				</SCRIPT>
        
        </td>
        <td width="48%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="230" width="700" valign="top" style="padding-left: 10px;border:sold 1px red">
           <img src="images/news.png"><br>
           <div id="TabbedPanels1" class="TabbedPanels" style="width:98%;height:230;background-color:#f7f5f2" >           
  <ul class="TabbedPanelsTabGroup" >
    <li class="TabbedPanelsTab" tabindex="0" style="font-size:12px;font-weight:bold" >工作动态</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size:12px;font-weight:bold" >通知公告</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size:12px;font-weight:bold" >产业动态</li>
  </ul>
  <div class="TabbedPanelsContentGroup" style="height:100%">
    <div class="TabbedPanelsContent">
   <table width="100%" height="140">
          <%
               		sql="select * from jview_information where IDENTIFY='WORK_DYNAMIC' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
    
    </div>
    <div class="TabbedPanelsContent" >
  		<table width="100%" height="140">
          <%
               		sql="select * from jview_information where IDENTIFY='NOTIFY' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多通知..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
   <table width="100%" height="140">
          <%
               		sql="select * from jview_information where IDENTIFY='INDUSTRY_DYNAMIC' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
  </div>
</div>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
         
            
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/index_20.gif" width="200" height="56" alt="" /></td>
          </tr>
          <tr>
            <td><img src="images/index_23.gif" width="200" height="56" alt="" /></td>
          </tr>
          <tr>
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="images/index_29.gif" width="200" height="34" alt="" /></td>
                </tr>
                <tr>
                  <td height="141" valign="top" background="images/index_32.gif"><table width="96%" border="0" cellspacing="0" cellpadding="3">
									<form action="index.jsp?flag=pw" method="POST">
									<tr>

										<%if(username!=null) {%>
										<td width="29%" align="right">登陆用户</td>
										<td width="71%">
										<%=username%>										</td>
										<%}else{ %>
                                        <td width="29%" align="right">用户名</td>
										<td width="71%">
										<input style="width:100px" type="text"
											name="username" dataType=Require re="请填写帐号"/>										</td>
										<%} %>
									</tr>
									<%if(username==null) {%>
									<tr>
										<td align="right">密&nbsp; 码</td>
										<td><input style="width:100px" type="password"
											name="password" /></td>
									</tr>
									<tr>
										<td align="right">&nbsp;</td>
										<td><select name="select5" id="select5">
											<option>网站会员用户</option>
										</select></td>
									</tr>
									<tr>
										<td align="right">&nbsp;</td>
										<td><input type="image" src="images/login.gif" width="60"
											height="22" border="0" /> <a href="member_register.jsp"><img
											src="images/register.gif" width="55" height="22" border="0" /></a></td>
									</tr>
									<tr>
										<td align="right">&nbsp;</td>
										<td><a style="color:#CB0000" href="#">忘记密码？</a></td>
									</tr>
									<%}else {%>
									<tr>
										<td align="right">&nbsp;</td>
										<td><a style="color:#CB0000" href="#">退出登陆</a> <a style="color:#CB0000" href="#">个人中心</a></td>
									</tr>
									<%} %>
									</form>
								</table></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_35.gif" width="200" height="45" alt="" /></td>
              </tr>
              <tr>
                <td align="center" background="images/index_36.gif"><table width="96%" border="0" cellspacing="0" cellpadding="3">
                  <form action="info_search.jsp" method="post">
                  <tr>
                    <td align="center"><input style="width:150px" type="text" name="keyword"  /></td>
                  </tr>
                  <tr>
                    <td align="center"><select style="width:150px" name="identify">
                      <option value="">请选择----------</option>
                      <option value="NEWS_CENTER">新闻中心</option>
                      <option value="ORGANIZATION_FUNCTION">机构职能</option>
                      <option value="INDUSTRY_POLICY">产业政策</option>
                      <option value="DEVELOP_LAYOUT">发展规划</option>
                      <option value="INDUSTRY_DATA">产业数据</option>
                      <option value="PRODUCT_STUDY">产业研究</option>
                      <option value="MODEL_CASE">典型案例</option>
                    </select>
                    </td>
                  </tr>
                  <tr>
                    <td align="center"><input type="image" src="images/search.gif" width="60" height="22" /></td>
                  </tr>
                  </form>
                </table></td>
              </tr>
              <tr>
                <td><img src="images/index_45.gif" width="200" height="7" alt="" /></td>
              </tr>
              <tr>
                <td><img src="images/index_49.gif" width="200" height="56" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><img src="images/index_50.gif" width="200" height="55" alt="" /></td>
          </tr>
          <tr>
            <td><img src="images/index_53.gif" width="200" height="55" alt="" /></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_60.gif" width="200" height="34" alt="" /></td>
              </tr>
              <tr>
                <td align="center" background="images/index_72.gif"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="center"><img src="images/index_62.gif" width="183" height="96" alt="" /></td>
                  </tr>
                </table>
                  <br />
                  <br />
                  <br />
                  <br />
                  <br />
                  <br /></td>
              </tr>
              <tr>
                <td><img src="images/index_73.gif" width="200" height="11" alt="" /></td>
              </tr>
              <tr>
                <td height="5" background="images/index_74.gif"></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_75.gif" width="200" height="27" alt="" /></td>
              </tr>
              <tr>
                <td align="center" background="images/index_80.gif"><table width="98%" border="0" cellspacing="0" cellpadding="4">
                  <%
                  sql="select * from j_linktype";               		
            		rs=Table.getRecordsBySql(sql);
             		String linktype;
             		int typeid;
            		while(rs.next()){
             			linktype=rs.getString("STYPENAME");
             			typeid=rs.getInt("ITYPEID");
                  %>
                  <tr>
                    <td align="center">
                    <select style="width:170px" name="select" id="select" onChange="window.open(this.options(this.selectedIndex).value);">
                      <option value="#"><%=linktype %></option>
                      <%
                      	sql="select * from j_link where ITYPEID="+typeid;
                      	
                      	ResultSet rs4=Table.getRecordsBySql(sql);
                      	
                      	while(rs4.next()){
                      	out.print("<option value='"+rs4.getString("SURL")+"'>"+rs4.getString("SLINKTITLE")+"</option>");
                     }
                      	Table.close(rs4);%>
                    </select></td>
                  </tr>
                 <%}Table.close(rs);%>
                </table></td>
              </tr>
              <tr>
                <td><img src="images/index_81.gif" width="200" height="3" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          
        </table></td>
        <td width="80%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="2">
          <tr>
            <td colspan="2" align="center"><img src="images/index_22.jpg" width="795" height="105" alt="" /></td>
          </tr>
          <tr>
            <td colspan="2" align="center" height="3"></td>
          </tr>
          <tr>
            <td width="50%" align="right" valign="top">
            <table width="396" cellpadding="0" cellspacing="0" bgcolor="#f7f5f2">
<tr><td><img src="images/index_25.gif" /></td></tr>
<tr><td>
<div id="TabbedPanels2" class="TabbedPanels" style="height:250">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">规划纲要</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">重点专项规划</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">产业规划</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">区域规划</li>
  </ul>
  <div class="TabbedPanelsContentGroup" style="height:100%">
    <div class="TabbedPanelsContent">
		<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='LAYOUT_DESIGN' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
		<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='PIVOTAL_PROJECT' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
		<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='INDUSTRY_LAYOUT' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
    	<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='AREA_LAYOUT' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
    </div>
  </div>
</div>

<script type="text/javascript">
<!--
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2");
//-->
</script>
</td></tr></table>
            
            </td>
            <td width="50%" align="center" valign="top" height="245">
            <table width="396" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_27.gif" width="395" height="25" alt="" /></td>
              </tr>
              <tr>
                <td>
                <div id="TabbedPanels3" class="TabbedPanels" style="height:250">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">广&nbsp;&nbsp;东</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">国&nbsp;&nbsp;内</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">国&nbsp;&nbsp;际</li>
  </ul>
  <div class="TabbedPanelsContentGroup" style="height:100%">
    <div class="TabbedPanelsContent">  
    <table width="100%" height="180">
          <%
               		sql="select * from jview_information where IDENTIFY='GUANGDONG' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table></div>
    <div class="TabbedPanelsContent">
	<table width="100%" height="180">
          <%
               		sql="select * from jview_information where IDENTIFY='DOMESTIC' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
<table width="100%" height="180">
          <%
               		sql="select * from jview_information where IDENTIFY='INTERNATION' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
  </div>
</div>

<script type="text/javascript">
<!--
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3");
//-->
</script>
                
                </td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td align="right" valign="top"><table width="396" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_41.gif" width="396" height="26" alt="" /></td>
              </tr>
              <tr>
                <td valign="top" background="images/index_38.gif"><table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                      <td height="2" colspan="2"></td>
                    </tr>
                   <%
               		sql="select * from jview_information where IDENTIFY='INDUSTRY_DATA' order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
                    <tr>
                      <td width="8%" align="right"><img src="images/dot.gif" width="3" height="3" /></td>
                      <td width="92%" align="left">
                      <a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                      <%=Page.leftStr(rs.getString("SINFOTITLE"),50) %>
                      </a></td>
                    </tr>
                   <%}
               		if(rs!=null){
               			Table.close(rs);
               		}
%>
                   
                    <tr>
                      <td colspan="2" align="center"><img src="images/index_55.gif" width="380" height="117" alt="" /></td>
                      </tr>
                </table></td>
              </tr>
              <tr>
                <td background="images/index_38.gif"><img src="images/index_39.gif" width="396" height="5" alt="" /></td>
              </tr>
            </table></td>
            <td align="center" valign="top"><table width="396" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_42.gif" width="395" height="26" alt="" /></td>
              </tr>
              <tr>
                <td valign="top" background="images/index_38.gif"><table width="100%" height="273" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                      <td height="2" colspan="2"></td>
                    </tr>
                        <%
               		sql="select * from jview_information where IDENTIFY='MODEL_CASE' and  SIFPIC='Y' and SATTM is not null order by IINFOID desc limit 6";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
                    <tr>
                      <td width="22%" align="right"><img src="<%="admin/attachment/upload/"+rs.getString("SATTM") %>" width="75" height="76" /></td>
                      <td width="78%" align="left" valign="top"><table width="96%" height="75" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td height="20" style="border-bottom:dotted 1px #DDDDDD"> <a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>" style="color:#FA8732"><strong><%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></strong></a></td>
                        </tr>
                        <tr>
                          <td style="line-height:20px"><%=Page.leftStr(Table.getValue("j_information_pages","CINFO","IINFOID="+rs.getInt("IINFOID"),""),80) %></td>
                        </tr>
                      </table></td>
                    </tr>
                   <%}
               		if(rs!=null){
               			Table.close(rs);
               		}
%>
                    
                </table></td>
              </tr>
              <tr>
                <td background="images/index_38.gif"><img src="images/index_39.gif" width="396" height="5" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td align="right" valign="top"><table width="396" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_66.gif" width="396" height="25" alt="" /></td>
              </tr>
              <tr>
                <td>                
                <div id="TabbedPanels4" class="TabbedPanels" style="height:320">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">项目介绍</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">项目进展</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">项目直通车</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">投资通车</li>
  </ul>
  <div class="TabbedPanelsContentGroup" style="height:100%">
    <div class="TabbedPanelsContent">项目介绍</div>
    <div class="TabbedPanelsContent">项目进展</div>
    <div class="TabbedPanelsContent">项目直通车</div>
    <div class="TabbedPanelsContent">投资通车</div>
  </div>
</div>

<script type="text/javascript">
<!--
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4");
//-->
</script>



</td>
              </tr>
              
              <tr>
                <td><img src="images/index_39.gif" width="396" height="5" alt="" /></td>
              </tr>
            </table></td>
            <td align="center" valign="top"><table width="396" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/index_67.gif" width="395" height="25" alt="" /></td>
              </tr>
              <tr>
                <td >
  <div id="TabbedPanels5" class="TabbedPanels" style="height:200;width:396">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">产业概况</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">产业布局</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">热点专题</li>
    <li class="TabbedPanelsTab" tabindex="0" style="font-size: 12px;">研究报告</li>
  </ul>
  <div class="TabbedPanelsContentGroup" style="height:100%">
    <div class="TabbedPanelsContent">
    <table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='INDUSTRY_SURVEY' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" />
               <a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
    </div>
    <div class="TabbedPanelsContent">
		<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='INDUSTRY_POSITION' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
	</div>
    <div class="TabbedPanelsContent">
    <table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='HOT_TOPIC' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
    </div>
    <div class="TabbedPanelsContent">
<table width="100%" height="185">
          <%
               		sql="select * from jview_information where IDENTIFY='STUDY_REPORT' order by IINFOID desc limit 5";               		
              		rs=Table.getRecordsBySql(sql);
               		while(rs.next()){
            	%>
             <tr><td align="left" height="23">
               <img src="images/dot.gif" width="3" height="3" /><a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>">
                <%=Page.leftStr(rs.getString("SINFOTITLE"),40) %></a>
                </td><td width="100">
               [<%
                
                                                Date date=rs.getDate("dpubdate");
                								Date time=rs.getTime("dpubdate");
                								out.print(date.getMonth()+"月"+date.getDate()+"日 ");
                								out.print(time.getHours()+":"+time.getMinutes());%>]
                </td></tr>
           		<%}
	if(rs!=null){
		Table.close(rs);
	}
%><tr><td colspan="2" height="20" align="right" valign="bottom">更多..</td></tr></table>
</div>
  </div>
  <img src="images/index_79.gif" width="380" height="117" alt="" />
</div>

<script type="text/javascript">
<!--
var TabbedPanels5 = new Spry.Widget.TabbedPanels("TabbedPanels5");
//-->
</script>
                
            	</td>
              </tr>
              <tr>
                <td background="images/index_38.gif"></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table>
    <br /></td>
  </tr>
 <jsp:include page="footer.jsp" flush="true"/>
</table>
</body>
</html>