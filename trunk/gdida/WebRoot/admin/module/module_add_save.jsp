<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%request.setCharacterEncoding("GBK"); %>
<%
try {
	String moduleName = request.getParameter("moduleName");
	String parentId = request.getParameter("parentId");
	String identify =  request.getParameter("identify");
	//String parentName = StringUtil.toISO8859_1(request.getParameter("parentName"));
	String order = request.getParameter("order");

    if (Table.getRecordCount("j_module","SMODULENAME='" + moduleName + "' and"
    		+ (parentId==null||parentId.trim().equals("")?" IPARENTID is null":(" IPARENTID="+parentId))) > 0) {
%>
		<script language="javascript">
			alert("您要新增的栏目已经存在！");
		    window.history.back(1);
		</script>
<%
    } else {
		Table.insertRow("j_module", "SMODULENAME"
				+ (parentId==null||parentId.trim().equals("")?"":", IPARENTID")
				+ (order==null||order.trim().equals("")?"":", IORDER")
				+ (identify==null||identify.trim().equals("")?"":", IDENTIFY"),
				"'"+moduleName+"'" + (parentId==null||parentId.trim().equals("")?"":(", "+parentId))
				+  (order==null||order.trim().equals("")?"":(", "+order))
				+  (identify==null||identify.trim().equals("")?"":(", '"+identify+"'")));
		response.sendRedirect("module_list.jsp");
    }
} catch(Exception e) {
    throw new Exception(e.getMessage());
}
%>
