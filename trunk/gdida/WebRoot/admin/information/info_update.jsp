<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Information" scope="page"/>
<%
if (!gdie.updateBoard(request.getParameter("iinfoid"))) {
%>
	<script language="javascript">
		alert("������");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("info_list.jsp?pageno="+request.getParameter("pageno"));
}
%>