<%@ page contentType="text/html;charset=gbk" %> 

<html>
<body>

<%
  String ta = request.getParameter("ta");
  out.println(ta);
%>
<textarea style="width: 100%; height: 200px"><%=ta%></textarea>
</body>
</html>