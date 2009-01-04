<%@ page contentType="text/html;charset=GBK" %>

<jsp:useBean id="gdie" class="com.gdie.gdjrb.Attachment" scope="page"/>

<%

out.print("²âÊÔÂÒÂë<br>");
if (gdie.upload(pageContext)) {
	String kk=gdie.ss2;
	kk=new String(kk.getBytes("utf-8"),"gbk");
	out.print(kk+"<br>");	
	
	

	//response.sendRedirect("upload.jsp?type="+request.getParameter("type"));
} else {
	out.println("ÉÏ´«Ê§°Ü,Çë¼ì²é¸½¼þÄÚÈÝ£¡");
}
%>
