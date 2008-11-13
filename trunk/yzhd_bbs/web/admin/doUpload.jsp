<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ntsky.framework.upload.*"%>
<%@ page import="com.ntsky.framework.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>BBS管理 —— 文件上传</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
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
		iu.setSaveDirectory("file/");
		//iu.setFilePrefixName("20051101111111");
		iu.setMaxFileSize(2048);
		iu.setFilePrefixName(DateUtil.getDate("yyyyMMddHHmmss"));
		// 文件格式
		// 该目录允许上传的文件总数 
		// 图片上传
		//添加参数
		iu.doUpload(request);

		Request req = iu.getRequest();
		//out.println("text1 = " + req.getParameter("text1")+"<br/>");
		//out.println("text2 = " + req.getParameter("text2")+"<br/>");
		// out.println("text3 = " + req.getParameter("text3"));

		FileItem file = null;
		Collection files = iu.getFileItems();
		file = (FileItem)files.toArray()[0];
		//out.print(pageContext.getServletContext().getRealPath("/") + "--" + file.getFilePath() + "--" + file.getFileName());
		iu.drawText(StringUtil.applyRelativePath(pageContext.getServletContext().getRealPath("/"),file.getFilePath()+file.getFileName()),"成花服饰");
		String elementId = req.getParameter("elementId");

		out.print("<script>");
		out.println("alert(\"成功上传图片!\");");
		//out.print("opener.document."+form+"."+field+".disabled=false;");
		out.println("opener.document.getElementById(\""+elementId+"\").value=\""+file.getFilePath()+file.getFileName()+"\";");
		//out.print("opener.document."+form+"."+field+"View.disabled=false;");
		//out.print("opener.document."+form+"."+field+"View.value=\""+file.getFilePath()+file.getFileName()+"\";");
		//out.print("opener.document."+form+"."+field+"Button.disabled=\"true\";");
		//out.print("opener.document."+form+"."+field+".disabled=true;");
		out.println("window.close();");
		out.print("</script>");
		//out.println("file size = " + file.getFileSize());
		//ImageUpload imageUpload = new ImageUpload();
		//imageUpload.zoomImage();

	}
	catch(FileUploadException iue){
		out.print(iue.getMessage());
	}
%>
</body>
</html>
