<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%request.setCharacterEncoding("GBK"); %>
<%!
 int isempty(String str)  //判断递交的字符串是否为空
 {
  if(str.length() ==0)
    return (0);
  else return (1);
 }
%>

<%
try {
	String question = request.getParameter("question");
	String item[] = {request.getParameter("item1"),request.getParameter("item2"),
                  request.getParameter("item3"),request.getParameter("item4"),
                  request.getParameter("item5")};
	String is_open = request.getParameter("is_open"); 
	String sql=null;
	
 
	if((isempty(item[0])+isempty(item[1])+isempty(item[2])+isempty(item[3])+
    isempty(item[4]))==0)
 {
 %>
		<script language="javascript">
			alert("选项不能为空！");
		    window.history.back(1);
		</script>
<%
 }else if((isempty(item[0])+isempty(item[1])+isempty(item[2])+isempty(item[3])+
    isempty(item[4]))<2)
 {
  %>
		<script language="javascript">
			alert("至少2个选项！");
		    window.history.back(1);
		</script>
<%
 }else if(question==null||question.length()==0)
 {
 %>
		<script language="javascript">
			alert("问题不能为空！");
		    window.history.back(1);
		</script>
<%

	}else{
	if(is_open.equals("Y")){
		sql="update J_QUESTIONS set is_open = 'N'";
		Table.execSql(sql);	
	}
	
	sql = "insert into J_QUESTIONS(question,is_open) values('"+question+"','"+is_open+"')";
	Table.execSql(sql);
	
	sql="select qid from J_QUESTIONS where question ='"+question+"'";
	ResultSet rs=Table.getRecordsBySql(sql);
	rs.next();
	int qid=rs.getInt(1);
	Table.close(rs);
	for(int i = 0;i<5;i++)
  {
   if(isempty(item[i])==1)
   {
    sql = "insert into J_ITEMS(qid,itemcount,item) values('"+qid+"',0,'"+item[i]+"')";
    Table.execSql(sql);
   }
  }
	
	
	
	
	
	
	response.sendRedirect("vote_list.jsp");
	}    
} catch(Exception e) {
    
}
%>
