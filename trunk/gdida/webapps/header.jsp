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
		var d=new initArray("<font color=RED>星期日","<font color=white>星期一","<font color=white>星期二","<font color=white>星期三","<font color=white>星期四","<font color=white>星期五","<font color=red>星期六");
		 document.write("<font color=white>",today.getYear(),"<font color=white>年","<font color=white>",today.getMonth()+1,"<font color=white>月","<font color=white>",today.getDate(),"<font color=white>日 </FONT>",d[today.getDay()+1]);  //-->
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
	  <A href="<%=sUrlHeader %>/index.jsp" class=sclink id=sclink1 onMouseOver="expandcontent('sc1', this)">首 页</A> 
      <A class=sclink id=sclink2 onMouseOver="expandcontent('sc2', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=NEWS_CENTER">新闻中心</A>
	  <A class=sclink id=sclink3 onMouseOver="expandcontent('sc3', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=ORGANIZATION_FUNCTION">机构职能</A>
	  <A class=sclink id=sclink4 onMouseOver="expandcontent('sc4', this)" href="#">产业项目</A>
	  <A class=sclink id=sclink5 onMouseOver="expandcontent('sc5', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_POLICY">产业政策</A>
	  <A class=sclink id=sclink6 onMouseOver="expandcontent('sc6', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=DEVELOP_LAYOUT">发展规划</A>    
      <A class=sclink id=sclink7 onMouseOver="expandcontent('sc7', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DATA">产业数据</A>  
      <A class=sclink id=sclink8 onMouseOver="expandcontent('sc8', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=PRODUCT_STUDY">产业研究</A>  
      <A class=sclink id=sclink9 onMouseOver="expandcontent('sc9', this)" href="<%=sUrlHeader %>/info_list.jsp?identify=MODEL_CASE">典型案例</A>  
      <A class=sclink id=sclink10 onMouseOver="expandcontent('sc10', this)" href="">产业专家</A>  
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
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=WORK_DYNAMIC">工作动态</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=NOTIFY">通知公告</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DYNAMIC">产业动态</A> 
      </DIV>
      <DIV class=tabcontent id=sc3>      
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=116">院领导组成</A> 
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=117">主要职能</A> 
      <A href="<%=sUrlHeader %>/info_detail.jsp?infoid=118">机构设置</A> 
      </DIV>
      <DIV class=tabcontent id=sc4>
      <A href="">项目介绍</A> 
      <A href="">项目进展</A> 
      <A href="<%=sUrlHeader %>/project_list.jsp?identify=PROJECT_CAR">项目直通车</A>      
      <A href="">投资直通车</A>       
      </DIV>
      <DIV class=tabcontent id=sc5>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=GUANGDONG">广东产业政策</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=DOMESTIC">国内产业政策</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INTERNATION">国际产业政策</A> 
      </DIV>
      <DIV class=tabcontent id=sc6>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=LAYOUT_DESIGN">规划纲要</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=PIVOTAL_PROJECT">重点专项规划</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_LAYOUT">产业规划</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=AREA_LAYOUT">区域规划</A> 
      </DIV>
      
      
      <DIV class=tabcontent id=sc7>   
      </DIV>  
      <DIV class=tabcontent id=sc8>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_SURVEY">产业概况</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_POSITION">产业布局</A> 
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=HOT_TOPIC">热点专题</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=STUDY_REPORT">研究报告</A>  
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=GUANGDONG_INDUSTY_DEVELOP">广东产业发展</A>      
      <A href="<%=sUrlHeader %>/info_list.jsp?identify=INDUSTRY_DEVELOP_MESSAGE">产业发展通讯</A>
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
