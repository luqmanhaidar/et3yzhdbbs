<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<%
String username = (String) session.getAttribute("username");

boolean iflogin=false;
if(session.getAttribute("userid")!=null)
{
	iflogin=true;
} %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�㶫ʡ��ҵ��չ�о�Ժ</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="common/htmlarea/htmlarea.js"></script>
<script type="text/javascript" src="common/htmlarea/lang/gb.js"></script>
<script type="text/javascript" src="common/htmlarea/dialog.js"></script>
<script type="text/javascript" src="common/infoAdd.js"></script>
<script type="text/javascript" src="common/datacheck.js"></script>
<%


int projectId = Integer.parseInt(request.getParameter("projectId"));
if (!gdie.findRecord(projectId)) {
	throw new Exception("�Ҳ�����¼");
}

String contents="";//����
String pages="";//ҳ��
String con0="";//��һҳ����
int pagenum=0;//����ҳ��

//int iInfolength = 1;
//iInfolength = gdie.getVInfo().size();
//for (int i=0; i<iInfolength; i++) {
//	if (i==0) con0=gdie.getVInfo().elementAt(0).toString().replaceAll("\"", "&quot;");
//	contents += "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + (i+1) + "\" value=\""
//			+ gdie.getVInfo().elementAt(i).toString().replaceAll("\"", "&quot;") + "\" />";
//	if (iInfolength>1) {
//		if (i==0)
//			pages += "&nbsp;<" + (i+1) + ">&nbsp;";
//		else
//			pages += "<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: showValue(" + (i+1) + ")\">&nbsp;<" + (i+1) + ">&nbsp;</a>";
//	}
//}
//pagenum = iInfolength + 1;
//contents += "<input type=\"hidden\" id=\"pagenum\" value=\"" + pagenum + "\" />";
//out.println(contents);
//StringBuffer sBreakPage = new StringBuffer();
//StringBuffer sHi_Value = new StringBuffer();
//sBreakPage.append("&nbsp;<1>&nbsp;");
//sHi_Value.append("<input type='hidden' name='cinfo' id='bodytextsave1'/>");
%>
<body>
<jsp:include page="header.jsp" flush="true" />
<table width="1002" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20%" valign="top" background="images/gdida_sub_37.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><img src="images/gdida_sub_02.gif" width="200"
							height="57" /></td>
					</tr>
					<tr>
						<td><img src="images/gdida_sub_15.gif" width="200"
							height="55" alt="" /></td>
					</tr>
					<tr>
						<td><img src="images/gdida_sub_20.gif" width="200"
							height="56" alt="" /></td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="images/gdida_sub_21.gif" width="200"
									height="36" alt="" /></td>
							</tr>
							<tr>
								<td align="center" valign="top"
									background="images/gdida_sub_22.gif">
								            <table border="0" style="table-layout: fixed;">
            <%      String sql="";//�ȵ����� �������
                   ResultSet rsNews = null;
            //1��ͼƬ����   		
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
               		//������������
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
							<tr>
								<td><img src="images/gdida_sub_b.gif" width="200"
									height="8" alt="" /></td>
							</tr>
							<tr>
								<td height="5" bgcolor="#F7F3EF"></td>
							</tr>
						</table>
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
<%@include file="user_front_login.html" %>

				</table>
				</td>
				<td width="80%" align="center" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td colspan="2" align="center">
						<table width="99%" border="0" cellspacing="0" cellpadding="0">

						</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" valign="top">
						<table width="99%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="34" height="26" align="center" bgcolor="#f7f3ef"><img
									src="images/gdida_sub_30.gif" width="17" height="17" /></td>
								<td width="760" align="left" bgcolor="#f7f3ef">&nbsp;�����ڣ�<a
									href='index.jsp'>�㶫ʡ��ҵ��չ�о�Ժ��ҳ</a> > <a
									href='project_detail.jsp?projectId=<%=request.getParameter("projectId")%>'>Ͷ��ֱͨ��</a></td>
							</tr>
							<tr>
								<td colspan="2" valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="word-break:break-all"><br>
										<span>
										<h2><%=gdie.getProjectTitle() %></h2>
										</span></td>
									</tr>
									<tr>
										<td>
										<div><a href="http://www.gid.gov.cn">http://www.gid.gov.cn</a>&nbsp;&nbsp;&nbsp;&nbsp;<%=gdie.getDPubDate()%>&nbsp;&nbsp;&nbsp;&nbsp;<font
											color="#aaaaaa">��Դ���㶫ʡ��ҵ��չ�о�Ժ</font></div>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="center"><font class="f14">
										<div id='bodytext' style="display: block"><span style="display: block">
										    <textarea
											rows="15" style="width:700px"
											cols="15" readonly="readonly"><%=Page.escapeHtml(gdie.getCinfo())%></textarea></span></div>
										</font></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
							  <td colspan="2">
							  <form name="form1" action="project_addChild_save.jsp?parentid=<%=projectId%>" method="post">
								<table
									height="325" border="0" cellpadding="0" cellspacing="0" id="table1"
									style="border-collapse: collapse" width="100%">
									<tr>
								<td width="48" height="26" align="center" bgcolor="#f7f3ef"><img
									src="images/gdida_sub_30.gif" width="17" height="17" /></td>
								<td align="left" bgcolor="#f7f3ef" colspan="3"><font size="4"><b>�������Ŀ</b></font> </td>
							</tr>
									<tr>
										<td  align="center" height="16"><p>�걨��Ŀ</p>
									    </td>
								      <td width="351"  height="16" align="left"><input
											name="projectTitle" type="text" size="47" datatype="Require" re="����д����" /></td>
									    <td width="54" align="right">��Ŀ���</td>
									  <td width="337" align="left"><input 
											name="projectMode" type="text" size="38" datatype="Require" re="����д���" /></td>
									</tr>
									<tr>
										<td  align="center" height="16">��ϵ��</td>
								      <td width="351"  height="16" align="left"><input
											name="contacts" type="text" size="47" datatype="Require" re="��������ϵ��" /></td>
									    <td width="54" align="right">��ϵ�绰</td>
									  <td width="337" align="left"><input 
											name="tel" type="text" size="38" datatype="MyPhone" re="�밴���ָ�ʽ��д�绰" /></td>
									</tr>
									<tr>
										<td width="48" align="center" height="12">��&nbsp;&nbsp;&nbsp;
										��</td>
									  <td colspan="3" rowspan="2" align="left"><input type="hidden"
											name="hi_curpage" value="1" /> <input type="hidden"
											name="hi_pagenum" value="1" />
									    <span style="display: block">
									    <textarea
											id="bodytextsave" name="bodytextsave" rows="15" style="width:700px"
											cols="15" ></textarea>
									    </span>
									    <div id="div_bodytext" style="display: block"></div>
									  </td>
									</tr>
									<tr>
										<td height="216"></td>
								    </tr>
									<tr>
										<td colspan="4" height="39">

											
											
										<p align="center"><input type="button" value="�ύ" name="B1"
											<%if(iflogin==true){
%>onclick='gosubmit(document.all.form1);'<%}else{ %>onclick="alert('�㻹û�е�½,���ȵ�½�����ύ����Ŀ')"<%} %>/><input
											type="reset" value="����" name="B2">
											
											
											
											
											
											
										</td>
									</tr>
								</table>
								</form>
							  </td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>

</table>
<table width="1002" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<jsp:include page="footer.jsp" flush="true" />
</table>
</body>
</html>
<script>
function gosubmit(frm){
	if(Validator.Validate(frm, 3))
	{
		if (form_check()) frm.submit();
	}
}
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
