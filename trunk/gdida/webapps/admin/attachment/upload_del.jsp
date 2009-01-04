<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "java.io.File" %>

<%
try{
	String id = request.getParameter("attmid");
	String strSql = "delete from j_attachment where IATTMID=" + id;
	String picPath=Table.getValue("j_attachment","SFILENAME","IATTMID=" + id,"");
	String realPath = this.getServletContext().getRealPath("admin/attachment/upload/"+picPath);
	File file=new File(realPath);
	if(file.exists()) file.delete();	
	Table.execSql(strSql);
	response.sendRedirect("upload.jsp?type="+request.getParameter("type"));
}catch(Exception e){
    throw new Exception(e.getMessage());
}
%>
