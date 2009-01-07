<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.sql.ResultSet"%>
<%request.setCharacterEncoding("GBK"); 

      response.setHeader("Pragma","No-Cache");
       response.setHeader("Cache-Control","No-Cache");
      response.setDateHeader("Expires", 0);

     String sParentid=request.getParameter("sParentid");
     int iLevel=0;
     if (request.getParameter("iLevel")!=null){
     iLevel=Integer.parseInt(request.getParameter("iLevel"));}
     iLevel=iLevel+1;
  //  out.print("kk"+iLevel);
int j=0;
int childCount=0;
String str="";
ResultSet rs=null;
ResultSet rs1=null;
try{	 
 rs = Table.getRecordsBySql("select * from j_module where IPARENTID="+sParentid+" order by IORDER asc");
while(rs.next()) {
	
	rs1=Table.getRecordsBySql("select count(*) from j_module where IPARENTID="+rs.getInt("IMODULEID"));
	if (rs1.next())	childCount=rs1.getInt(1);
	Table.close(rs1);
	
	str="";
	for(j=0;j<iLevel;j++)
	{str=str + "&nbsp;&nbsp;";}
    //out.print("d"+str+"d");
    if (childCount==0){
    out.print("<div>"+str + "<IMG SRC='../images/admin/file.gif' /><a href='information/information_filter.jsp?IMODULEID="+rs.getInt("IMODULEID")+"&identify="+rs.getString("IDENTIFY")+"' target='mainFrame'>" + rs.getString("SMODULENAME")+"</a></div>"); 
    }
    else{
	out.print("<div>"+str + "<IMG SRC='../images/admin/open.gif' onclick=\"javascript:clkit('child" + rs.getInt("IMODULEID") + "',this,'" + rs.getInt("IMODULEID") +"','"+iLevel+"')\" style='cursor:hand' title='" + rs.getString("SMODULENAME") +"'/>" + rs.getString("SMODULENAME")+"</div>"); 
	out.print("<span id='child" + rs.getInt("IMODULEID") + "'></span>");
    }
    
}}catch(Exception e){
    throw new Exception(e.getMessage());
}finally {
    if (rs != null) Table.close(rs);
}

%>
  