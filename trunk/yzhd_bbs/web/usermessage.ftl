<TABLE style="BORDER-BOTTOM: #ccc 1px solid" cellSpacing=0 cellPadding=0 
width=769 border=0>
  <TBODY>
  <TR>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
href="#">收件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
      href="#">发件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j1.gif" 
width=6></TD>
    <TD class=tit1>写信息</TD>
    <TD width=3 height=31><IMG height=31 src="images/zx-j2.gif" 
  width=3></TD></TR></TBODY></TABLE>
<DIV class=zx4>
<TABLE cellSpacing=10 cellPadding=0 width=700 border=0>
  <TBODY>
  <TR>
    <TD class=sj width=112>验证码：</TD>
    <TD width=400>
      <DIV style="FLOAT: left"><INPUT class=headline id=txtCode 
      style="WIDTH: 5em" 
      onfocus="document.getElementById('imgcode').style.display='';" maxLength=4 
      name=txtCode>&nbsp; </DIV>
      <DIV style="FLOAT: left"><IMG id=imgcode title=看不清楚，请点击图片刷新 
      style="DISPLAY: none; CURSOR: hand" 
      onclick="this.src='../Common/ConfirmCode.aspx?q='+Math.random();" 
      src="images/ConfirmCode.gif"> </DIV></TD>
    <TD width=150><SPAN id=RequiredFieldValidator4 
      style="VISIBILITY: hidden; COLOR: red">验证码不能为空</SPAN> </TD></TR>
  <TR>
    <TD class=sj width=112>收件人：</TD>
    <TD width=400><INPUT class=headline id=Send_Who style="WIDTH: 400px" 
      maxLength=100 name=Send_Who> </TD>
    <TD width=150><SPAN id=RequiredFieldValidator1 
      style="VISIBILITY: hidden; COLOR: red">用户名不能为空</SPAN> </TD></TR>
  <TR>
    <TD class=sj width=112>标　题：</TD>
    <TD width=400><INPUT class=headline id=txtTopic style="WIDTH: 400px" 
      maxLength=100 name=txtTopic></TD>
    <TD width=150><SPAN id=RequiredFieldValidator2 
      style="VISIBILITY: hidden; COLOR: red">标题不能为空</SPAN> </TD></TR>
  <TR>
    <TD class=sj vAlign=center>内　容：</TD>
    <TD rowSpan=2><TEXTAREA class=ly1 id=txtContent style="WIDTH: 400px; HEIGHT: 260px" name=txtContent rows=10></TEXTAREA> 
    </TD>
    <TD><SPAN id=RequiredFieldValidator3 
      style="VISIBILITY: hidden; COLOR: red">内容不能为空</SPAN> </TD></TR>
  <TR>
    <TD height=180>&nbsp;</TD></TR></TBODY></TABLE>