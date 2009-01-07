<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "com.gdie.util.Md5"%>
<%@ page import= "com.gdie.util.Popedom"%>
<%
request.setCharacterEncoding("GBK");
try{
	String suserid = request.getParameter("userid");
	String susername = request.getParameter("username");
	String suserpassword = request.getParameter("userpassword");
	String srealname = request.getParameter("realname");
	String[] spowerStr = request.getParameterValues("spower");
	String[] popedom =request.getParameterValues("popedom");
	
	
	
	String self = request.getParameter("self");

	Short spower=0x0000;
	if(spowerStr!=null){
		for(int i=0;i<spowerStr.length;i++){
			if(spowerStr[i].equals("fa")){
				spower+=0x0001;
			}
			if(spowerStr[i].equals("sh")){
				spower+=0x0002;
			}
		}
	}
    if(Table.getRecordCount("j_user","SUSERNAME = '" + susername
    		+ "' and IUSERID<>" + suserid) > 0){
%>
		<script language="javascript">
			alert("该帐号已经存在！");
		    window.history.back(1);
		</script>
<%
    }else{
		if (suserpassword!=null && !suserpassword.equals("")) {
		
    		Table.execSql("update j_user set SUSERNAME = '" + susername + "', SPASSWORD = '"
    			+ Md5.encrypt(suserpassword) + "', SNAME = '" + srealname +"', SPOWER = '" + spower + "' where IUSERID = " + suserid);
		} else {
    		Table.execSql("update j_user set SUSERNAME = '" + susername + "', SNAME = '"
    			+ srealname +"', SPOWER = '" + spower + "' where IUSERID = " + suserid);
		}
		Popedom.removeOnesPopedom(suserid);
		if(popedom!=null){			
			for(int i=0;i<popedom.length;i++){				
				Popedom.addOnesPopedom(suserid,popedom[i]);
			}
		}
		response.sendRedirect("user_list.jsp");
    }
}catch(Exception e){
    throw new Exception(e.getMessage());
}
%>

