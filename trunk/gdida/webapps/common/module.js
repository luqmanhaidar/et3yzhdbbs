with (document) {
        write("<STYLE TYPE='text/css'>");
        write(".child {display:none}")
        write("</STYLE>");
}

onload = initIt;

function initIt(){
        divColl = document.all.tags("DIV");
        for (i=0; i<divColl.length; i++) {
            whichEl = divColl(i);
            if (whichEl.className == "child") whichEl.style.display = "none";
        }
}

function expandIt(el,obj,numid,level,master)
{
        whichEl = eval(el + "Child");
		whichIm = event.srcElement;
        if (whichEl.style.display == "none") 
		{
            whichEl.style.display = "block";
			whichIm.src = "../../images/admin/55.gif";
			//obj.style.color='red';
			obj.innerHTML="&nbsp;&nbsp;<span class='clsNotReady' style='color:#000000;'><font size=2>正在读取数据，请稍后.......</fonr></span>";
			setTimeout("GetSelfArea(document.all."+obj.id+",'"+numid+"','"+level+"','"+master+"');",100);
        }
        else {
            whichEl.style.display = "none";
			whichIm.src = "../../images/admin/44.gif";
			obj.innerHTML="";
        }
}

function RetValue(RetIndex)//Int
{
	//GetAllName(RetIndex);
	//alert(RetIndex+","+document.all.allname.value);
	window.returnValue = RetIndex;
	window.close()
}

function GetSelfArea(obj,numid,level,master)
{
		var SAreaName="";
		var YDFNUMXH=new ActiveXObject("Microsoft.XMLHTTP");	

		YDFNUMXH.open("post","module.jsp?pid="+numid+"&level="+level+"&master="+master,false);
		YDFNUMXH.send();

	    if(YDFNUMXH.readyState!=4)
		{
			setTimeout("GetSelfArea(obj,numid,level,master)",5000);
		}
		else
		{
			GetBody=YDFNUMXH.responseText;
			if(GetBody.indexOf("<div")>=0)
			{
				SAreaName=GetBody.substring(GetBody.indexOf("<div"));
			}
		}
		obj.innerHTML=SAreaName;
}