<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<head>
<link href="style/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="280" height="230" border="0" cellpadding="0" cellspacing="0" bgcolor="#F4F4F4">
  <tr>
    <td align="center"><span style="padding-top:24px">
      <SCRIPT language=JavaScript>
     	var imgWidth=280;              //ͼƬ��
		var imgHeight=210;             //ͼƬ��
		var textFromHeight=20;         //�����ֿ�߶� (��λΪpx)
		var textStyle="texttitle";           //������class style (��������class)
		var textLinkStyle="texttitle"; //����������class style
		var buttonLineOn="#f60";           //button�»���on����ɫ
		var buttonLineOff="#000";          //button�»���off����ɫ
		var TimeOut=5000;              //ÿ��ͼ�л�ʱ�� (��λ����);
		var imgUrl=new Array(); 
		var imgLink=new Array();
		var imgtext=new Array();
		var imgAlt=new Array();
		var adNum=0;
		//�����ֿ�߶���ʽ�� ��ʼ
		document.write('<style type="text/css">');
		document.write('#focuseFrom{width:'+(imgWidth+2)+';margin: 0px; padding:0px;height:'+(imgHeight+textFromHeight)+'px; overflow:hidden;}');
		document.write('#txtFrom{height:'+textFromHeight+'px;line-height:'+textFromHeight+'px;width:'+imgWidth+'px;overflow:hidden;}');
		document.write('#imgTitle{width:'+imgWidth+';top:-'+(textFromHeight+14)+'px;height:18px}');
		document.write('</style>');
		document.write('<div id="focuseFrom">');
		
		//�����ֿ�߶���ʽ�� ����
		var num=0;
		<%
		String sql = "select * from jview_information where SIFPIC='Y' and IDENTIFY='WORK_DYNAMIC' AND ISOK='Y' and SATTM is not null order by IINFOID desc limit 5";
		ResultSet rs = Table.getRecordsBySql(sql);		
		
			while(rs.next()){
		%>
		num++;			
		imgUrl[num]='<%="admin/attachment/upload/"+rs.getString("SATTM") %>';
		imgtext[num]='<a href="<%="info_detail.jsp?infoid="+rs.getInt("IINFOID") %>" target="_blank" class=linkblack><%=Page.leftStr(rs.getString("SINFOTITLE"),20) %></a>';
		imgLink[num]='<%="info_detail.jsp?infoid="+rs.getInt("IINFOID") %>';
		imgAlt[num]='<%=rs.getString("SINFOTITLE") %>';
		<%
			}
	
		%>
		
		function changeimg(n)
		{
		adNum=n;
		window.clearInterval(theTimer);
		adNum=adNum-1;
		nextAd();
		}
		function goUrl(){
		window.open(imgLink[adNum],'_blank');
		}
		//NetScape��ʼ
		if (navigator.appName == "Netscape")
		{
		document.write('<style type="text/css">');
		document.write('.buttonDiv{height:4px;width:21px;}');
		document.write('</style>');
		function nextAd(){
		if(adNum<(imgUrl.length-1))adNum++;
		else adNum=1;
		theTimer=setTimeout("nextAd()", TimeOut);
		document.images.imgInit.src=imgUrl[adNum];
		document.images.imgInit.alt=imgAlt[adNum];	
		document.getElementById('focustext').innerHTML=imgtext[adNum];
		document.getElementById('imgLink').href=imgLink[adNum];
		}
		document.write('<a id="imgLink" href="'+imgLink[1]+'" target=_blank class="p1"><img src="'+imgUrl[1]+'" name="imgInit" width='+imgWidth+' height='+imgHeight+' border=1 alt="'+imgAlt[1]+'" class="imgClass"></a><div id="txtFrom"><span id="focustext" class="'+textStyle+'">'+imgtext[1]+'</span></div>')
		document.write('<div id="imgTitle">');
		document.write('<div id="imgTitle_down">');
		//���ְ�ť���뿪ʼ
		for(var i=1;i<imgUrl.length;i++){document.write('<a href="javascript:changeimg('+i+')" class="button" style="cursor:hand" title="'+imgAlt[i]+'">'+i+'</a>');}
		//���ְ�ť�������
		document.write('</div>');
		document.write('</div>');
		document.write('</div>');
		nextAd();
		}
		//NetScape����
		//IE��ʼ
		else
		{
		var count=0;
		for (i=1;i<imgUrl.length;i++) {
		if( (imgUrl[i]!="") && (imgLink[i]!="")&&(imgtext[i]!="")&&(imgAlt[i]!="") ) {
		count++;
		} else {
		break;
		}
		}
		function playTran(){
		if (document.all)
		imgInit.filters.revealTrans.play();		
		}
		var key=0;
		function nextAd(){
		if(adNum<count)adNum++ ;
		else adNum=1;
		if( key==0 ){
		key=1;
		} else if (document.all){
		imgInit.filters.revealTrans.Transition=23;
		imgInit.filters.revealTrans.apply();
		playTran();
		}
		document.images.imgInit.src=imgUrl[adNum];
		document.images.imgInit.alt=imgAlt[adNum];	
		document.getElementById('link'+adNum).style.background=buttonLineOn;
		for (var i=1;i<=count;i++)
		{
		if (i!=adNum){document.getElementById('link'+i).style.background=buttonLineOff;}
		}	
		focustext.innerHTML=imgtext[adNum];
		theTimer=setTimeout("nextAd()", TimeOut);
		}
		document.write('<a target=_self href="javascript:goUrl()"><img style="FILTER: revealTrans(duration=1,transition=5);" src="javascript:nextAd()" width='+imgWidth+' height='+imgHeight+' border=0 vspace="0" name=imgInit class="imgClass"></a><br>');
		document.write('<div id="txtFrom"><span id="focustext" class="'+textStyle+'"></span></div>');
		document.write('<div id="imgTitle">');
		document.write(' <div id="imgTitle_down"> <a class="trans"></a>');
		//���ְ�ť���뿪ʼ
		for(var i=1;i<imgUrl.length;i++){document.write('<a id="link'+i+'"  href="javascript:changeimg('+i+')" class="button" style="cursor:hand" title="'+imgAlt[i]+'" onFocus="this.blur()">'+i+'</a>');}
		//���ְ�ť�������
		document.write('</div>');
		document.write('</div>');
		document.write('</div>');
		document.write('</div>');
		}
		//IE����
      </SCRIPT>
    </span></td>
  </tr>
</table>
</body>
