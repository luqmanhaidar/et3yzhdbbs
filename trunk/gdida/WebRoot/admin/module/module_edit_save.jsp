<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%request.setCharacterEncoding("GBK"); %>
<%
try {
	String moduleId = request.getParameter("moduleId");
	String moduleName = request.getParameter("moduleName");
	String parentId = request.getParameter("parentId");
	String identify = request.getParameter("identify");
	//String parentName = StringUtil.toISO8859_1(request.getParameter("parentName"));
	String order = request.getParameter("order");

    if (Table.getRecordCount("j_module","SMODULENAME='" + moduleName + "' and"
    		+ (parentId==null||parentId.trim().equals("")?" IPARENTID is null":(" IPARENTID="+parentId))
    		+ " and IMODULEID<>" + moduleId) > 0) {
%>
		<script language="javascript">
			alert("已经存在相同的栏目！");
		    window.history.back(1);
		</script>
<%
    } else {
    	String sql = "update j_module set SMODULENAME='" + moduleName + "'"
    		+ (parentId==null||parentId.trim().equals("")?", IPARENTID=null":(", IPARENTID="+parentId))
			+ (order==null||order.trim().equals("")?"":(", IORDER="+order))
			+ (identify==null||identify.trim().equals("")?"":(", IDENTIFY='"+identify+"'"))
    		+ " where IMODULEID=" + moduleId;
    	Table.execSql(sql);
		response.sendRedirect("module_list.jsp");
    }
} catch(Exception e) {
    throw new Exception(e.getMessage());
}
%>
