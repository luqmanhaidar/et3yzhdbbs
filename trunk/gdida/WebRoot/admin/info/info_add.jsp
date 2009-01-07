<%@ page contentType="text/html; charset=GBK" %>
<%
StringBuffer sBreakPage = new StringBuffer();
StringBuffer sHi_Value = new StringBuffer();
sBreakPage.append("&nbsp;<1>&nbsp;");
sHi_Value.append("<input type='hidden' name='cinfo' id='bodytext1'/>");
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
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
<form name="form1" action="info_add_save.jsp" method="post">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>信息管理</u>>><u>信息发布</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="700">
	<tr>
		<td width="115" align="center" height="35">标&nbsp;&nbsp;&nbsp; 题</td>
		<td width="476" height="35"><input type="text" name="stitle" size="45" dataType="Require" re="请填写标题"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">栏&nbsp;&nbsp;&nbsp; 目</td>
		<td width="476" height="35"><input type=hidden name='moduleid'><input type=text name='modulename' readonly dataType="Require" re="请选择信息栏目">
		<input value='选择' type=button onclick='AddArea("../module/module.jsp?master=2&t=<%=System.currentTimeMillis() %>")'>
		
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">图片新闻</td>
		<td width="476" height="35">
		<input type=checkbox name=ifPic value='Y' onClick="this.checked?document.all.np.style.display='':document.all.np.style.display='none'">是
		<span id='np' style='display:none'>
		<input type="text" name="sattm" readonly onmouseout='hidden()' onmouseover='showmsg(this,this.value)'>
		<input type=button value='选择图片' onclick='getImages(document.all.sattm);'><span id=myImg></span>
		</span>
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">内&nbsp;&nbsp;&nbsp; 容</td>
		<td width="476" height="35">　</td>
	</tr>
	<tr height=30>
		<td colspan="2" height="61">
		<table width="100%" border="0" cellpadding="2" cellspacing="0" height="51">
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
                        	<img src="../../common/htmlarea/images/video.gif" alt="video" onmouseover='style.cursor="hand"' onclick='OpenNewWin("../attachment/upload.jsp?type=video");' width="65" height="22"/></td>
                        <td width="5%" height="29" align="center">
                        </td>
						<td width="5%" height="29" align="center">
                        </td>
                        <td width="5%" height="29" align="center">
							
                        </td>
						<td width="5%" height="29" align="center">
                        </td>
                    </tr>
                    <tr>
                    	<td width="5%" align="center">
                    	</td>
                    	<td align="right" colspan=7>
                    	    <div>
                    			
                    		</div>
                    	</td>
                    </tr>
                </table>
		
		</td>
	</tr>
	<tr>
		<td colspan="2" height="393">
		<input type="hidden" name="hi_curpage" value="1"/>
		<input type="hidden" name="hi_pagenum" value="1"/>
		<div id="div_bodytext" style="display: block">
            <textarea id="bodytext" name="bodytext" rows="30" style="width:100%" cols="20"></textarea>
        </div>
        <div id="hi_bodytext"  style="display: none"><%= sHi_Value.toString() %></div>
        </td>
	</tr>
	<tr>
		<td colspan="2" height="39">
		<p align="center"><input type="button" value="提交" name="B1" onclick='gosubmit(document.all.form1);'>
		 <input type="reset" value="重置" name="B2"></td>
	</tr>
</table>
</form>
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
				if(moduleid.value=="020501"||moduleid.value=="020602"||moduleid.value=="020603"){
					np.style.display="";
					sattm.value="";}
				else{
					np.style.display="none";
					sattm.value="";
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