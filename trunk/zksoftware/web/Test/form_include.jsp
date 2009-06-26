<%@page import="com.et3.zksoftware.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${test.id}"/>

	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Test.ALIAS_TEXT%>:
		</td>		
		<td>
		<form:input path="test.text" id="text" cssClass="required " maxlength="20" />
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Test.ALIAS_RR%>:
		</td>		
		<td>
		<form:input path="test.rr" id="rr" cssClass="required " maxlength="10" />
		</td>
	</tr>	
	


