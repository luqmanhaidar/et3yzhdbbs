<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.util.SelectUtil" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Information" scope="page"/>

<%
StringBuffer sBreakPage = new StringBuffer();
StringBuffer sHi_Value = new StringBuffer();
String uid=(String)session.getAttribute("userid");
String identify=(String)session.getAttribute("identify");
sBreakPage.append("&nbsp;<1>&nbsp;");
sHi_Value.append("<input type='hidden' name='cinfo' id='bodytext1'/>");
int iInfolength = 1;

int iInfoId = Integer.parseInt(request.getParameter("infoid"));
if (!gdie.findRecord(iInfoId)) {
	throw new Exception("找不到记录");
}

String sinfo0=new String();//第一页内容

iInfolength = gdie.getVInfo().size();
for (int i = 1; i <= iInfolength; i++) {
	if (i==1) sinfo0=gdie.getVInfo().elementAt(0).toString().replaceAll("\"", "&quot;");
	if (i>1) sBreakPage.append("<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: getpage(" + i + ")\">&nbsp;<" + i + ">&nbsp;</a>");
	if (i==1) sHi_Value.setLength(0);
	sHi_Value.append("<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + i + "\" value=\"" + gdie.getVInfo().elementAt(i - 1).toString().replaceAll("\"", "&quot;") + "\" />");
}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
</head>
<style type="text/css">
    @import url(../../common/htmlarea/htmlarea.css);
</style>
<script type="text/javascript" src="../../common/htmlarea/htmlarea.js"></script>
<script type="text/javascript" src="../../common/htmlarea/lang/gb.js"></script>
<script type="text/javascript" src="../../common/htmlarea/dialog.js"></script>
<script type="text/javascript" src="../../common/infoAdd.js"></script>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="info_edit_save.jsp?infoid=<%=iInfoId %>" method="post">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>信息管理</u>>><u>信息修改</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="700">
	<tr>
		<td width="115" align="center" height="37">标&nbsp;&nbsp;&nbsp; 题</td>
		<td width="476" height="37"><input type="text" <% if (gdie.getIModuleId()==2){ out.print("readonly");} %>  name="stitle" size="45" dataType="Require" re="请填写标题" value="<%=gdie.getInfoTitle() %>"></td>
	</tr>
	<tr <% if (gdie.getIModuleId()==2){ out.print("style='display:none'");} %>>
		<td width="115" align="center" height="35">栏&nbsp;&nbsp;&nbsp; 目</td>
		<td width="476" height="35">
		<select name='moduleid'>
			<option value='<%=gdie.getModuleId() %>'><%=gdie.getModuleName() %></option>
			<%=SelectUtil.getModuleOptionByIdentify(uid,identify) %>
		</select>
		</td>
	</tr>
	<tr <% if (gdie.getIModuleId()==2){ out.print("style='display:none'");} %>>
		<td width="115" align="center" height="35">图片新闻</td>
		<td width="476" height="35">
		<input type=checkbox name=ifPic value='Y' <%=gdie.getIfPic().equals("Y")?"checked":"" %>
		 onClick="this.checked?document.all.np.style.display='':document.all.np.style.display='none'">是
		<span id='np' style='display:<%=gdie.getIfPic().equals("Y")?"":"none" %>'>
		<input type="text" name="sattm" readonly value='<%=gdie.getAttm()!=null?gdie.getAttm():"" %>'
		 onmouseout='hidden()' onmouseover='showmsg(this,this.value)'>
		<input type=button value='选择图片' onclick='getImages(document.all.sattm);'><span id=myImg></span>
		</span>
		</td>
	</tr>
	<tr <% if (gdie.getIModuleId()==2){ out.print("style='display:none'");} %>>
		<td align="center" height="35">置顶标识</td>
		<td>
		<%int ding=gdie.getDing(); %>
			<select name="ding">			
				<option <%=ding==0?"selected":"" %>>0</option>
				<option <%=ding==1?"selected":"" %>>1</option>
				<option <%=ding==2?"selected":"" %>>2</option>
				<option <%=ding==3?"selected":"" %>>3</option>				
			</select>
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="32">内&nbsp;&nbsp;&nbsp; 容</td>
		<td width="476" height="32">　</td>
	</tr>
	<tr height=30 <% if (gdie.getIModuleId()==2){ out.print("style='display:none'");} %>>
		<td colspan="2" height="61">
		<table width="100%" border="0" cellpadding="2" cellspacing="0" height="51" >
                    <tr>
                        <td width="5%" height="29" align="center">
                            <img src="../../common/htmlarea/images/image.gif" alt="image" onmouseover='style.cursor="hand"' onclick='OpenNewWin("../attachment/upload.jsp?type=img");' width="65" height="22"/>
                        </td>
                        <td width="5%" height="29" align="center">
                            <img src="../../common/htmlarea/images/uploadfile.gif" alt="file" onmouseover='style.cursor="hand"' onclick='OpenNewWin("../attachment/upload.jsp?type=file");' width="65" height="22"/>
                        </td>
                        <td width="5%" height="29" align="center">
                            <img src="../../common/htmlarea/images/highlight.gif" alt="高亮显示" onmouseover='style.cursor="hand"' onclick="highlight();" width="65" height="22"/>
                        </td>
                      
                        <td width="5%" height="29" align="center">
                        	<img src="../../common/htmlarea/images/addpage.gif" alt="增加首页" onmouseover='style.cursor="hand"' onclick="pageAddFirst();" width="65" height="22"/>
                        </td>
                        <td width="5%" height="29" align="center">
                        	<img src="../../common/htmlarea/images/insertpage.gif" alt="插入分页" onmouseover='style.cursor="hand"' onclick="pageInsert();" width="65" height="22"/>
                        </td>
						<td width="5%" height="29" align="center">
                        	<img src="../../common/htmlarea/images/pagebreak.gif" alt="追加分页" onmouseover='style.cursor="hand"' onclick="pageAdd();" width="65" height="22"/>
                        </td>
                        <td width="5%" height="29" align="center">
							<img src="../../common/htmlarea/images/deletepage.gif" alt="删除本页" onmouseover='style.cursor="hand"' onclick="pageDeleteCurrent();" width="65" height="22"/>
                        </td>
						<td width="5%" height="29" align="center">
							<img src="../../common/htmlarea/images/dellastpage.gif" alt="删除尾页" onmouseover='style.cursor="hand"' onclick="pageDelete();" width="65" height="22"/>
                        </td>
                    </tr>
                    <tr>
                    	<td width="5%" height="29" align="center">
                    		<img src="../../common/htmlarea/images/video.gif" alt="video" onmouseover='style.cursor="hand"' onclick='OpenNewWin("../attachment/upload.jsp?type=video");' width="65" height="22"/>
                    	</td>
                    	<td id="breakpage" align="right" colspan=7>
                    	    <div>
                    			<%= sBreakPage.toString() %>
                    		</div>
                    	</td>
                    </tr>
                </table>
		
		</td>
	</tr>

	<tr>
		<td colspan="2" height="393">
		<input type="hidden" name="hi_curpage" value="1"/>
		<input type="hidden" name="hi_pagenum" value="<%=iInfolength %>"/>
		<div id="div_bodytext" style="display: block">
            <textarea id="bodytext" name="bodytext" rows="30" style="width:100%" cols="20">
            <%=sinfo0 %>
            </textarea>
        </div>
        <div id="hi_bodytext"  style="display: none"><%=sHi_Value.toString() %></div>
        </td>
	</tr>
	<tr>
		<td colspan="2" height="39">
		<p align="center"><input type="button" value="提交" name="B1" onclick='gosubmit(document.all.form1);'>
		 <input type="button" value="返回" name="B2" onclick='javascript:window.history.go(-1);'></td>
	</tr>
</table>
</form>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594">
	<tr><td colspan="2">其他操作：</td></tr>
	<tr>
		<td>
			<form action="info_copy.jsp" method="post">
			复制到：
			<input type="hidden" name="iid" value="<%=iInfoId %>">
			<select name="mid">
				<%=SelectUtil.getModulesByUid(uid) %>
			</select>
			<input type="submit" value="复制">
			</form>
		</td>
		
		<td>
			<form action="info_move.jsp" method="post">
			移动到：
			<input type="hidden" name="iid" value="<%=iInfoId %>">
			<select name="mid">
				<%=SelectUtil.getModulesByUid(uid) %>
			</select>
			<input type="submit" value="移动">
			</form>		
		</td>
	</tr>
	
</table>

<div id='MsgDiv' style='position:absolute;display:none' onMouseOut="this.style.display='none';" onMouseOver="this.style.display='';">
	<table width="153" height="73" cellspacing="1" style="background-color: #000000"><tr class='showImg'>
	<td>&nbsp;图片预览：<br><img id='showimg'></td></tr></table>
	</div>
</div></body></html>
<script>
initEditor('bodytext');
function gosubmit(frm){
	if(Validator.Validate(frm, 3))
	{
		if (form_check()) frm.submit();
	}
}
function change(){
	with(document.all){
		if(itypeid.options(itypeid.selectedIndex).value==28){
			jy.style.display='';
		}else{
			jy.style.display='none';
		}
	}
}
function AddArea(Url)
{
	with(document.all)
	{
		var vReturnValue = window.showModalDialog(Url);
		if(vReturnValue!=null)
		{
				vReturnValues=vReturnValue.split(",");
				modulename.value=vReturnValues[1];
				moduleid.value=vReturnValues[0];	
				/*
				if(moduleid.value=="13"||moduleid.value=="0403"||moduleid.value=="26"){
					np.style.display="";
					sattm.value="";
				}
				else{
					np.style.display="none";
					sattm.value=" ";
				}*/
		}
	}
}
function showmsg(target,img){
	var ss=getoffset(target);
	obj=document.getElementById("MsgDiv")
	document.all.showimg.src="../attachment/upload/"+img;
	obj.style.display='';
	obj.style.left=ss[1];
	obj.style.top=ss[0]+target.offsetHeight;
}
function hidden(){
	with(document.all){
		MsgDiv.style.display='none';
	}
}
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
function getImages(ctrlobj){
	retval = window.open("../attachment/upload.jsp", "", "toolbar=0,scrollbars=1,resizable=1 "  );
}
</script>