<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "com.gdie.util.Md5"%>
<%
request.setCharacterEncoding("GBK");
try{
	String susername = request.getParameter("username");
	String suserpassword = request.getParameter("userpassword");
	String srealname = request.getParameter("realname");

    if(Table.getRecordCount("j_user","SUSERNAME = '" + susername + "'") > 0){
%>
		<script language="javascript">
			alert("该帐号已经存在！");
		    window.history.back(1);
		</script>
<%
    }else{
		Table.insertRow("j_user", "SUSERNAME, SPASSWORD, SNAME",
				"'" + susername + "', '" + Md5.encrypt(suserpassword) + "', '" + srealname + "'");
		response.sendRedirect("user_list.jsp");
    }
}catch(Exception e){
    throw new Exception(e.getMessage());
}
%>