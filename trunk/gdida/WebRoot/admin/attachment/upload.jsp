<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="com.gdie.common.Page" %>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="java.sql.ResultSet" %>
<%

String url = request.getContextPath();
//��������
String retype = request.getParameter("type");
if (retype==null || retype.equals(""))	retype = "string";

String sCondition = "";
//Ҫ��ʾ��ҳ��
int pageNo = 0;
try{
  pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
  pageNo = 1;
}
int rowCount = Table.getRecordCount("j_attachment", "1=1 " + sCondition);
String strSql = "SELECT * FROM j_attachment WHERE IATTMID>0 " + sCondition +" order by IATTMID DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//��ҳ����
Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);
//��ҳ��
int pageCount = objPage.getPageCount();
//��ǰҳ��
pageNo = objPage.getPageNo();
//��ʼ����
int begin = objPage.getBegin();
//��ʾ����
int viewCount = objPage.getViewCount();
//������¼��
if (begin > 0)
  rs.absolute(begin);
%>

<html>
<head>
<title>��������</title>

<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript">
function fencode(){

}
</script>
</head>
<body>
<div align=center>
<table width="700" height="292" cellspacing="0">
  <tr>
	<td width="347" align="center" height="231"><b>��������</b>
	<div align="center">
	<FORM name=frmFill METHOD="POST" ACTION="upload_do.jsp?type=<%=retype %>" ENCTYPE="multipart/form-data" onsubmit='return form_check()'>
	<table width="87%" border="0" height="114" cellspacing="0" cellpadding="0">
  		<tr>
			<td width="301">���⣺<input type="text" name="sAttmTitle" size="30"/>&nbsp;</td>
		</tr>
	
		<tr>
			<td width="301">������<input type="file" name="sBrief" size="30" onchange='viewimg()'/></td>
		</tr>
		<tr>
			<td width="301"><div align="center">
				<input type="hidden" name="hi_width" value='100'/>
				<input type="hidden" name="hi_height" value='200'/>
				<input type="submit" name="bSubmit" value="�ϴ�����">
			</div></td>
		</tr>
	</table>
	</FORM>
	</div>
	</td>
	<td align="center" height="231" width="349">
		<img id='pic' name='pic' src='../../images/admin/demo.jpg'></td>
  </tr>
  <tr>
	<td align="center" colspan="2">
	<FORM name=frmlist METHOD="POST" ACTION="upload.jsp?type=<%=retype %>">
<table width="90%" align="center">
  <tr>
    <td width="27%">&nbsp;��<%=rowCount%>����¼</td>
    <td width="23%" align="center"></td>
    <td width="50%">
      <div align="right">
          ��<input type="text" name="pageno" size="4" class="int" value="<%= pageNo %>">ҳ <input type="submit" name="but_gopage" value="ת��" style="cursor:hand;">
	  	  			<%
	  				     out.println("<a href='javascript:goPage(1)'>��һҳ</a>");
	  					 out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>��һҳ</a>");
						 out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>��һҳ</a>");
        				 out.println("<a href='javascript:goPage(" + (pageCount) + ")'>���һҳ</a>");
					%>
	  </div>
    </td>
  </tr>
</table>
<table width="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
		<tr bgcolor="#FFFFFF" align=center>
			<td width="57%" height=25>��������</td>
			<td width="25%" height=25>�ļ�</td>
			<td width="18%" height=25>����</td>
		</tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
		<tr bgcolor="#FFFFFF" height="25">
			<td><%=rs.getString("SATTMTITLE") %></td>
			<td><span style='cursor: hand' onmouseout='hidden()'
			 onmouseover=showmsg(this,'<%=rs.getString("SATTMTYPE").trim() %>','<%=rs.getString("SATTMTITLE") %>','<%=rs.getString("SFILENAME") %>')>
			 <%=rs.getString("SFILENAME") %></span></td>
			<td align=center><img src='../../images/admin/save.gif' width="20" height="20" border=0 style='cursor: hand' title='ճ������'
			 onclick=paste('<%=rs.getString("SATTMTITLE") %>','<%=rs.getString("SFILENAME") %>','<%=retype %>')>
			 <a href=javascript:if(confirm('ȷ��ɾ������?'))location='upload_del.jsp?attmid=<%=rs.getInt("IATTMID") %>&type=<%=retype %>'>
			 <img src='../../images/admin/del.gif' width="20" height="20" border=0 style='cursor: hand' title='ɾ������'></a></td>
		</tr>
<%
    }
}
%>
</table>
	</FORM></td>
  </tr>
</table>
</div>
<div id='MsgDiv' style='position:absolute;display:none' onMouseOut="this.style.display='none';" onMouseOver="this.style.display='';">
<table width="153" height="73" cellspacing="1" style="background-color: #000000"><tr class='showImg'>
	<td>&nbsp;�������⣺<span id='showtitle'>title</span></td></tr><tr class='showImg'>
	<td>&nbsp;ͼƬԤ����<br><span id='showme'></span></td></tr></table>
</div>
</body>
</html>

<script type="text/javascript">
//Ԥ�����и���
function showmsg(target,type,tt,img){
	var ss=getoffset(target);
	obj=document.getElementById("MsgDiv")
	//obj.innerHTML=msgArray[msgI];
	document.all.showtitle.innerHTML=tt;
	if(type=="jpg"||type=="gif"||type=="bmp")
	{
		document.all.showme.innerHTML="<img src='upload/"+img+"'>";
	}else if(type=='xls'){
		document.all.showme.innerHTML="<img src='upload/xls.jpg'>";
	}else if(type=='swf'){
		document.all.showme.innerHTML="<object classid='clsid:D27CDB6E-AE6D-11CF-96B8-444553540000' id='obj1' border='0'"
			+" codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0'>"
			+"<param name='movie' value='upload/"+img+"'><param name='quality' value='High'>"
			+"<embed src='upload/"+img+"' pluginspage='http://www.macromedia.com/go/getflashplayer'"
			+" type='application/x-shockwave-flash' name='obj1' quality='High'></object>";
	}else{
		document.all.showme.innerHTML="<img src='upload/word.jpg'>";
	}
	
	obj.style.display='';
	obj.style.left=ss[1];
	obj.style.top=ss[0]+target.offsetHeight;
}
//ҳ��Ԫ��λ��
function getoffset(obj) 
{ 
     var t=obj.offsetTop; 
     var l=obj.offsetLeft; 
     while(obj=obj.offsetParent) 
     { 
     t+=obj.offsetTop; 
     l+=obj.offsetLeft; 
     }
     var rec = new Array(1); 
     rec[0] = t; 
     rec[1] = l;
     return rec
}
function hidden(){
	document.all.MsgDiv.style.display='none';
}
//����ճ������
function paste(FileTitle,FileName,retype) {
	var oParentWindow = window.opener;
	if(retype=="file"){
    	var html = "<b>������<img src='<%=url %>/images/admin/file.gif' border=0 width=16 height=16/>"
    			+"<a href='<%=url %>/admin/attachment/upload/"+FileName+"'>"+FileTitle+"</a></b><br>";
		oParentWindow.insertHTML(html);
    	window.close();
	}
	else if(retype=='video'){
		var html = "<br><embed src='<%=url %>/admin/attachment/upload/"+FileName+"'><br>";
		oParentWindow.insertHTML(html);
    	window.close();
	}
	else if(retype=="img"){
		if(FileName.substring(FileName.length-3,FileName.length)=="swf"){
			var html = "<object classid='clsid:D27CDB6E-AE6D-11CF-96B8-444553540000' id='obj1' border='0'"
					+" codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0'>"
					+"<param name='movie' value='<%=url %>/admin/attachment/upload/" + FileName + "'>"
					+"<param name='quality' value='High'><embed src='<%=url %>/admin/attachment/upload/" + FileName
					+"' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'"
					+" name='obj1' quality='High'></object>";
		}else{
    		var html = "<img src='<%=url %>/admin/attachment/upload/" + FileName + "' border=0>";
	    }
	    oParentWindow.insertHTML(html);
	    window.close();
	}else if(retype=="download"){
		window.opener.document.all.durl.value=FileName;
	    window.close();
    }else{
		window.opener.document.all.sattm.value=FileName;
	    window.close();
    }
}

//�ϴ�ͼƬʱԤ��Ч��
function viewimg() {
	var dva=document.all.sBrief.value;
	var mytype=dva.split(".")[dva.split(".").length-1];
	if(mytype=='jpg'||mytype=='gif'||mytype=='bmp')
	{
    	document.all.pic.src = document.all.sBrief.value;
    }
    document.all.hi_width.value = document.all.pic.width;
    document.all.hi_height.value = document.all.pic.height;
}

function form_check() {
    if (document.all.sAttmTitle.value == "") {
        alert("�ļ�˵������Ϊ�գ�");
        document.all.sAttmTitle.focus();
        return false;
    }
    if (document.all.sBrief.value == "") {
        alert("�밴[���]��ťѡ��һ����Ҫ�ϴ����ļ���");
        document.all.sBrief.focus();
        return false;
    }
    return true;
}

//תҳ
function goPage(pageNo) {
	document.frmlist.pageno.value = pageNo;
	document.frmlist.submit();
}
</script>
<% if (rs != null) Table.close(rs); %>