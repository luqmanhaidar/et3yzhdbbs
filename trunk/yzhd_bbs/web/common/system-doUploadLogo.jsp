<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ntsky.bbs.Symbols"%>
<%@ page import="com.ntsky.bbs.domain.Admin"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.framework.upload.*"%>
<%@ page import="com.ntsky.framework.util.*"%>
<html>
<head>
<title>BBS管理 —— 图片上传</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript">
	//<![CDATA[
	if(opener==null){
		window.close();
	}	

	<%
		Admin admin = (Admin)session.getAttribute(Symbols.SESSION_ADMIN);
		if( admin == null ){
			out.print("window.close();");
		}
	%>

	//]]>
</script>
</head>
<body style="margin: 0px;padding:0px;">
<%
	try{
		ImageUpload iu = new ImageUpload();
		// jsp调用初始化
		iu.initialize(pageContext);
		
		// 允许文件格式
		iu.setAllowedFileType("gif,jpg");
		// 禁止的文件格式
		//iu.setForbidedFileType("pdf");
		// 设置文件前缀名称
		iu.setSaveDirectory("images/");
		//iu.setFilePrefixName("20051101111111");
		iu.setMaxFileSize(2048);
		//iu.setFilePrefixName(DateUtil.getDate("yyyyMMddHHmmss"));
		// 文件格式
		// 该目录允许上传的文件总数 
		// 图片上传
		//添加参数
		iu.doUpload(request);

		Request req = iu.getRequest();

		FileItem file = null;
		Collection files = iu.getFileItems();
		file = (FileItem)files.toArray()[0];
		//out.print(pageContext.getServletContext().getRealPath("/") + "--" + file.getFilePath() + "--" + file.getFileName());
		//iu.drawText(StringUtil.applyRelativePath(pageContext.getServletContext().getRealPath("/"),file.getFilePath()+file.getFileName()),"成花服饰");
		String element = req.getParameter("element");
		String preview = req.getParameter("preview");
%>
		<script>
			alert("成功上传图片!");
			if(opener!=null){
				opener.document.getElementById("<%=element%>").value = "<%out.print(file.getFilePath()+file.getFileName());%>";
				if(!("false"=="<%=preview%>")){
					opener.document.images["view<%=element%>"].src = "<%out.print(file.getFilePath()+file.getFileName());%>";
				}
			}
			window.close();
		</script>
<%
	}
	catch(FileUploadException iue){
		out.print(iue.getMessage());
	}
%>
</body>
</html>
