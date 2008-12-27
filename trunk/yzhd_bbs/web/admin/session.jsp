<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.ntsky.bbs.Symbols"%>
<%@ page import="com.ntsky.bbs.domain.Admin"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.bbs.service.AdminService"%>
<%
	Object object = session.getAttribute(Symbols.SESSION_ADMIN);
	if( object == null ){
		String username = HttpUtil.getCookieValue(request, Symbols.COOKIE_ADMIN);
		if(username==null){		
			out.print("<script>alert(\"非法登录或登录超时\");top.location.href=\""+request.getContextPath()+"/admin/login.jsp\"</script>");	
		}
		else{
			// cookie存在的场合
			AdminService adminService = (AdminService) BeanFactory.getInstance(getServletConfig().getServletContext())
							.getBean("adminService");
			Admin admin = adminService.getAdmin(username);
			if (admin == null) {
				out.print("<script>alert(\"非法登录或登录超时\");top.location.href=\""+request.getContextPath()+"/admin/login.jsp\"</script>");
			}else{
				session.setAttribute(Symbols.SESSION_ADMIN,admin);
			} 
		}	
	}	
%>
