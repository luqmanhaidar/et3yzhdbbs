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
	<title><%=Test.TABLE_ALIAS%>信息</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/Test/list.do" method="post">
	<input type="button" value="返回列表" onclick="window.location='${ctx}/Test/list.do'"/>

	<input type="hidden" id="id" name="id" value="${test.id}"/>

	<table class="formTable">
		<tr>	
			<td class="tdLabel"><%=Test.ALIAS_TEXT%></td>	
			<td><c:out value='${test.text}'/></td>
		</tr>
		<tr>	
			<td class="tdLabel"><%=Test.ALIAS_RR%></td>	
			<td><c:out value='${test.rr}'/></td>
		</tr>
	</table>
</form>

</body>

</html>