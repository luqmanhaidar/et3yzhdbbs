<%@page import="com.et3.zksoftware.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${tadmin.id}"/>

	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Tadmin.ALIAS_USERNAME%>:
		</td>		
		<td>
		<form:input path="tadmin.username" id="username" cssClass="required " maxlength="45" />
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Tadmin.ALIAS_PASSWORD%>:
		</td>		
		<td>
		<form:input path="tadmin.password" id="password" cssClass="required " maxlength="255" />
		</td>
	</tr>	
	


