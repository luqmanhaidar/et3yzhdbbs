<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Board" scope="page"/>
<%
if (!gdie.updateBoard(request.getParameter("boardid"))) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("board_list.jsp");
}
%>