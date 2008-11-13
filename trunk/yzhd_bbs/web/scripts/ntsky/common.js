function CheckAll(chkName)
{
    var chk=document.getElementsByName(chkName);
    for (var i=0;i<chk.length;i++)
    {
        var e = chk.item(i);
        if(e.checked==true)
        {
            e.checked = false;
        }
        else
        {
            e.checked = true;
        }
    }
}
//内容部分图片控制
function SetImgWidth() {
    var div = document.getElementsByTagName("div");

    for (i = 0; i < div.length; i++) {
        if (div[i].className == "uMain") {
//            var _img = div[i].getElementsByTagName("img");

//            for (j = 0; j < _img.length; j++) {
//                if (_img[j].width > 700) {
//                    _img[j].style.width = 700 + "px";
//                }
//            }
            var tab = div[i].getElementsByTagName("table");
            for (n = 0; n < tab.length; n++) {
                if (tab[n].width > 700) {
                    tab[n].style.width = 700 + "px";
                }
            }
        }
        if (div[i].className == "contextc") {
//            var _img = div[i].getElementsByTagName("img");

//            for (j = 0; j < _img.length; j++) {
//                if (_img[j].width > 550) {
//                    _img[j].style.width = 550 + "px";
//                }
//            }
            var tab = div[i].getElementsByTagName("table");
            for (n = 0; n < tab.length; n++) {
                if (tab[n].width > 550) {
                    tab[n].style.width = 550 + "px";
                }
            }
        }
    }
}

//旧系统js图片大小控制
function picsize(obj)
{
	img=new Image();
	img.src=obj.src;
	if (img.width>600)
	{
		return 550;
	}
	else
	{
		return img.width;
	}
}
function ajaxRequest( showElementById,getUrl){
var xmlHttp=null; 
try {
    xmlHttp = new XMLHttpRequest(); 
} catch (e) {
    try {
       xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
       xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
}
xmlHttp.onreadystatechange = function() {
if (xmlHttp.readyState == 4)
   try { 
      if (xmlHttp.status == 200) {
        if( showElementById=='' ){
		
	        var resp = xmlHttp.responseText + ''; 
	        alert(  resp );
            
        }
        else{
		
	         document.getElementById(showElementById).innerHTML 
                = xmlHttp.responseText;
        }
      }
   } catch (e) {
   alert( '' + e.description );
   }

}
xmlHttp.open("get",getUrl);
xmlHttp.send(null); 
}