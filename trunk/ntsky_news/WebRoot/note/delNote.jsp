<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="check.jsp"%>

<%
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ɾ���û�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
%>

<jsp:useBean id="manager" scope="page" class="com.ntsky.news.note.Manager"/>
	
<%	
	int noteId=servlet.requestInt(request,"noteId");
	manager.delNote(noteId);
	DOServlet.responseUrl(response,"default.jsp");
%>