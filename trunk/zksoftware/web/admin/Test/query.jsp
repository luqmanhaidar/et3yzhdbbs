<%@page import="com.et3.zksoftware.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<title><%=Test.TABLE_ALIAS%>查询</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/Test/list.do" method="post">
	<input type="submit" value="提交" onclick="return new Validation(document.forms[0]).validate();"/>
	<input type="button" value="返回列表" onclick="window.location='${ctx}/Test/list.do'"/>
	
	<table class="formTable">
	
		<tr bgcolor="#FFFFFF">
		   <td class="tdLabel"><%=Test.ALIAS_TEXT%></td>
		   <td>
				<input  type="text" name="s_text" size="30" maxlength="20" class=""/>
		   </td>
		</tr>
		<tr bgcolor="#FFFFFF">
		   <td class="tdLabel"><%=Test.ALIAS_RR%></td>
		   <td>
				<input  type="text" name="s_rr" size="30" maxlength="10" class=""/>
		   </td>
		</tr>
	
	</table>
</form>
			
</body>

</html>