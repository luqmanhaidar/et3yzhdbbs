<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import="com.gdie.util.Popedom" %>
<%
request.setCharacterEncoding("GBK");
String identify=request.getParameter("identify");
String IMODULEID=request.getParameter("IMODULEID");
session=request.getSession();

String uid=(String)session.getAttribute("userid");

boolean flag=Popedom.validateModule(uid,identify);
if(!flag){
	
	%>	
	<script language="javascript">
		alert("你没有该权限");
	    window.history.back(1);
	</script>
	<%	
}else{
session.setAttribute("identify",identify);
session.setAttribute("IMODULEID",IMODULEID);
%>
<script language="javascript">		
	    document.location.replace("info_list.jsp"); 
	</script>
<%
}
%>