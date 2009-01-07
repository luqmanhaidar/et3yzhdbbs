<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "com.gdie.db.Table"%>
<%
String con1=request.getParameter("con1");
String con2=request.getParameter("con2");
String con3=request.getParameter("con3");
String con4=request.getParameter("con4");
String pop1="VIEW";
String pop2="EDIT";
String pop3="DELETE";
String pop4="AUDIT";


for(int tid=9;tid<40;tid++){
Table.insertRow("popedom","tid,con,pop ",tid+",'"+con1+"','"+pop1+"'");
Table.insertRow("popedom","tid,con,pop ",tid+",'"+con2+"','"+pop2+"'");
Table.insertRow("popedom","tid,con,pop ",tid+",'"+con3+"','"+pop3+"'");
Table.insertRow("popedom","tid,con,pop ",tid+",'"+con4+"','"+pop4+"'");
}

%>