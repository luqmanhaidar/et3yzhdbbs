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
	<title><%=Test.TABLE_ALIAS%>新增</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<form action="${ctx}/Test/save.do" method="post">
	<input id="submit" name="submit" type="submit" value="提交" />
	<input type="button" value="返回列表" onclick="window.location='${ctx}/Test/list.do'"/>
	
	<table class="formTable">
	<%@ include file="form_include.jsp" %>
	</table>
</form>

<script>
	
	new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
		var finalResult = result;
		
		//在这里添加自定义验证
		
		return disableSubmit(finalResult,'submit');
	}});
</script>

</body>
</html>