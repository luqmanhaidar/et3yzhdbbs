<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.db.Table"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>

<LINK href="style/tab_menu.css" type=text/css rel=stylesheet>
<SCRIPT src="js/prototype.js" type=text/javascript></SCRIPT>
<SCRIPT src="js/fabtabulous.js" type=text/javascript></SCRIPT>
</head>
<body>
<%String sUrlHeader = request.getContextPath();%>
	<table width="1002" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
    <td height="120" valign="top" background="images/index_01.jpg"><table width="100%" height="32" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="81%" align="right">&nbsp;</td>
        <td width="14%" align="left" class="white">
        <script language="JavaScript">
		today=new Date();
		var hours = today.getHours();
		var minutes = today.getMinutes();
		var seconds = today.getSeconds();
		var timeValue = "<FONT COLOR=black>" + ((hours >12) ? hours -12 :hours); timeValue += ((minutes < 10) ? "<BLINK><FONT COLOR=black>:</FONT></BLINK>0" : "<BLINK><FONT COLOR=black>:</FONT></BLINK>") + minutes+"</FONT></FONT>";
		timeValue += (hours >= 12) ? "PM" : "AM";
		function initArray(){
		this.length=initArray.arguments.length
		for(var i=0;i<this.length;i++)
		this[i+1]=initArray.arguments[i]  }
		var d=new initArray("<font color=RED>������","<font color=white>����һ","<font color=white>���ڶ�","<font color=white>������","<font color=white>������","<font color=white>������","<font color=red>������");
		 document.write("<font color=white>",today.getYear(),"<font color=white>��","<font color=white>",today.getMonth()+1,"<font color=white>��","<font color=white>",today.getDate(),"<font color=white>�� </FONT>",d[today.getDay()+1]);  //-->
      </script></td>
        <td width="5%" align="right">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="1002" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="images/index_03.gif" width="1002" height="10" alt="" /></td>
      </tr>
      <tr>
        <td><table width="1002" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="17"><img src="images/index_04.gif" width="17" height="35" alt="" /></td>
            <td width="960" background="images/index_05.gif">
            	
<DIV id=xcx_tabmenu>
      <DIV class=cx_tabmenu id=cx_tabmenu style="width:1000">
      <DIV id=ddimagetabs >
	  <A href="<%=sUrlHeader %>/index.jsp" class=sclink id=sclink1 onMouseOver="expandcontent('sc1', this)">�� ҳ</A> 
      <A class=sclink id=sclink2 onMouseOver="expandcontent('sc2', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=NEWS_CENTER">��������</A>
	  <A class=sclink id=sclink3 onMouseOver="expandcontent('sc3', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=ORGANIZATION_FUNCTION">����ְ��</A>
	  <A class=sclink id=sclink4 onMouseOver="expandcontent('sc4', this)" href="#">��ҵ��Ŀ</A>
	  <A class=sclink id=sclink5 onMouseOver="expandcontent('sc5', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_POLICY">��ҵ����</A>
	  <A class=sclink id=sclink6 onMouseOver="expandcontent('sc6', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=DEVELOP_LAYOUT">��չ�滮</A>    
      <A class=sclink id=sclink7 onMouseOver="expandcontent('sc7', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DATA">��ҵ����</A>  
      <A class=sclink id=sclink8 onMouseOver="expandcontent('sc8', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=PRODUCT_STUDY">��ҵ�о�</A>  
      <A class=sclink id=sclink9 onMouseOver="expandcontent('sc9', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=MODEL_CASE">���Ͱ���</A>  
      <A class=sclink id=sclink10 onMouseOver="expandcontent('sc10', this)" href="">��ҵר��</A>  
       </DIV>
      <BR style="CLEAR: left">
      </DIV></DIV>
      <SCRIPT type=text/javascript>
    // The idea borrowed from dynamicDrive.com

    var initialtab = [1, "sc1"]

    var previoustab = "";
    var intv;

    function expandcontent(cid, aobject)
        {
        stopTimer();

        highlighttab(aobject)

        if (previoustab != "")
            document.getElementById(previoustab).style.display = "none"

        document.getElementById(cid).style.display = "block"
        previoustab = cid
        }

    function highlighttab(aobject)
        {
        stopTimer();

        if (typeof tabobjlinks == "undefined")
            collectddimagetabs()

        for (i = 0; i < tabobjlinks.length; i++)
            tabobjlinks[i].className = ""

        aobject.className = "current"
        }

    function collectddimagetabs()
        {
        var tabobj = document.getElementById("ddimagetabs")
        tabobjlinks = tabobj.getElementsByTagName("A")
        }

    function do_onload()
        {
        collectddimagetabs()
        expandcontent(initialtab[1], tabobjlinks[initialtab[0] - 1])
        }

    function startTimer()
        {
        intv = setTimeout("expandcontent(initialtab[1], tabobjlinks[initialtab[0]-1])", 2000);
        }

    function stopTimer()
        {
        clearTimeout(intv);
        }

    Event.observe(window, 'load', do_onload, false);
    Event.observe('xcx_tabmenu', 'mouseover', function(event)
        {
        stopTimer();
        },        false);

    Event.observe('xcx_tabmenu', 'mouseout', function(event)
        {
        var reltg = (event.relatedTarget) ? event.relatedTarget : event.toElement;

        var tg = (window.event) ? event.srcElement : event.target;

        if (tg.nodeName != 'DIV')
            return;

        while (reltg != tg && reltg.nodeName != 'BODY')
            {
            reltg = reltg.parentNode;

            if (reltg.id == "xcx_tabmenu")
                return;
            }

        if (reltg == tg)
            return;

        stopTimer();
        startTimer();
        },        false);
</SCRIPT>
            </td>
            <td width="25"><img src="images/index_09.gif" width="25" height="35" alt="" /></td>
          </tr>
          
        </table></td>
      </tr>
      
      <tr>
        <td height="54" background="images/index_10.gif"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="5%">&nbsp;</td>
            <td width="93%"><DIV id=tabcontentcontainer>
      <DIV class=tabcontent id=sc1></DIV>
      <DIV class=tabcontent id=sc2>
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=WORK_DYNAMIC">������̬</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=NOTIFY">֪ͨ����</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DYNAMIC">��ҵ��̬</A> 
      </DIV>
      <DIV class=tabcontent id=sc3>      
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=116">Ժ�쵼���</A> 
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=117">��Ҫְ��</A> 
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=118">��������</A> 
      </DIV>
      <DIV class=tabcontent id=sc4>
      <A href="">��Ŀ����</A> 
      <A href="">��Ŀ��չ</A> 
      <A href="<%=sUrlHeader %>/project_list.jsp?identify=PROJECT_CAR">��Ŀֱͨ��</A>      
      <A href="">Ͷ��ֱͨ��</A>       
      </DIV>
      <DIV class=tabcontent id=sc5>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=GUANGDONG">�㶫��ҵ����</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=DOMESTIC">���ڲ�ҵ����</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INTERNATION">���ʲ�ҵ����</A> 
      </DIV>
      <DIV class=tabcontent id=sc6>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=LAYOUT_DESIGN">�滮��Ҫ</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=PIVOTAL_PROJECT">�ص�ר��滮</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_LAYOUT">��ҵ�滮</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=AREA_LAYOUT">����滮</A> 
      </DIV>
      
      
      <DIV class=tabcontent id=sc7>   
      </DIV>  
      <DIV class=tabcontent id=sc8>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_SURVEY">��ҵ�ſ�</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_POSITION">��ҵ����</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=HOT_TOPIC">�ȵ�ר��</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=STUDY_REPORT">�о�����</A>  
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=GUANGDONG_INDUSTY_DEVELOP">�㶫��ҵ��չ</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DEVELOP_MESSAGE">��ҵ��չͨѶ</A>
      </DIV>
      <DIV class=tabcontent id=sc9>   
      </DIV>
      
      
      
      </DIV></td>
            <td width="2%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><marquee scrollamount='1'   scrolldelay='30'   direction="left" onmouseover="this.stop()"  onmouseout="this.start()">
            	<%
            		String sql="select * from j_information where (IMODULEID=8 or IMODULEID=9 or IMODULEID=11) and ISOK='Y'   order by DPUBDATE DESC  limit 5";
            		ResultSet rs=null;
            		rs=Table.getRecordsBySql(sql);
            		while(rs.next()){
            	%>
            	
            	<a href="info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>"><span class="yellow">
            	<%=rs.getString("SINFOTITLE") %> </span>
            	</a>
            	&nbsp;&nbsp;&nbsp;&nbsp;
            	<%} %>
            	<%
	if(rs!=null){
		Table.close(rs);
	}
%>
			</marquee></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
	</table>
</body>
</html>
