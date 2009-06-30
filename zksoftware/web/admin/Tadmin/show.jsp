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
	<title><%=Tadmin.TABLE_ALIAS%>信息</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/zksoftware/Tadmin/list.do" method="post">
	<input type="button" value="返回列表" onclick="window.location='${ctx}/zksoftware/Tadmin/list.do'"/>

	<input type="hidden" id="id" name="id" value="${tadmin.id}"/>

	<table class="formTable">
		<tr>	
			<td class="tdLabel"><%=Tadmin.ALIAS_USERNAME%></td>	
			<td><c:out value='${tadmin.username}'/></td>
		</tr>
		<tr>	
			<td class="tdLabel"><%=Tadmin.ALIAS_PASSWORD%></td>	
			<td><c:out value='${tadmin.password}'/></td>
		</tr>
	</table>
</form>

</body>

</html>