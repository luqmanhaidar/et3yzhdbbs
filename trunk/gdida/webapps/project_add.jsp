<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.util.Date"%>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>广东省产业发展研究院</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="common/htmlarea/htmlarea.js"></script>
<script type="text/javascript" src="common/htmlarea/lang/gb.js"></script>
<script type="text/javascript" src="common/htmlarea/dialog.js"></script>
<script type="text/javascript" src="common/infoAdd.js"></script>
<script type="text/javascript" src="common/datacheck.js"></script>
<%

String username = (String) session.getAttribute("username");

String contents="";//内容
String pages="";//页码
String con0="";//第一页内容
int pagenum=0;//内容页数


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
								<table width="90%" border="0" cellspacing="0" cellpadding="3">
									<tr>
										<td width="43%" align="center"><img
											src="images/gdida_sub_25.gif" width="72" height="70" /></td>
										<td width="57%" align="left" valign="top"
											style="line-height:20px"><a href="#"
											style="color:#CB0000"><strong>思想大解放，推动大发展</strong></a></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="5">
											<tr>
												<td width="8%"><img src="images/dot.gif" width="3"
													height="3" /></td>
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
												<td width="8%"><img src="images/dot.gif" width="3"
													height="3" /></td>
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
												<td width="8%"><img src="images/dot.gif" width="3"
													height="3" /></td>
												<td width="92%" align="left">广西缴获近八百公斤疑走私</td>
											</tr>

										</table>
										</td>
									</tr>

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
								<td width="760" align="left" bgcolor="#f7f3ef">&nbsp;您现在：<a
									href='index.jsp'>广东省产业发展研究院首页</a> > <a
									href='project_list.jsp?identify=PROJECT_CAR'>项目直通车</a> ><a
									href='project_add.jsp'> 提交新项目</a></td>
							</tr>
							<tr>
							  <td colspan="2">
							  <form name="form1" action="project_add_save.jsp" method="post">
								<table
									height="469" border="0" cellpadding="0" cellspacing="0" id="table1"
									style="border-collapse: collapse" width="96%">
									<tr>
										<td  align="center" height="16">申报项目</td>
								      <td width="308"  height="16" align="left"><input
											name="projectTitle" type="text" size="44" datatype="Require" re="请填写标题" /></td>
									    <td width="71" align="center">项目类别</td>
									  <td width="321" align="left"><input
											name="projectMode" type="text" size="40" datatype="Require" re="请填写类别" /></td>
									</tr>
									<tr>
										<td width="58" align="center" height="12">内&nbsp;&nbsp;&nbsp;
										容</td>
									  <td colspan="3" rowspan="2" align="left"><input type="hidden"
											name="hi_curpage" value="1" /> <input type="hidden"
											name="hi_pagenum" value="1" />
									    <span style="display: block">
									    <textarea
											id="bodytextsave" name="bodytextsave" rows="30" style="width:700px"
											cols="15"></textarea>
									    </span>
									    <div id="div_bodytext" style="display: block"></div>
</td>
									</tr>
									<tr>
										<td height="402"></td>
								    </tr>
									<tr>
										<td colspan="4" height="39">
										<p align="center"><input type="button" value="提交"
											name="B1" onclick='gosubmit(document.all.form1);'><input
											type="reset" value="重置" name="B2">
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
</script>
