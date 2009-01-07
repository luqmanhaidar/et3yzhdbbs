var editor = null;

function initEditor(id) {
	try {
		editor = new HTMLArea(id);
		editor.generate();
	}catch(ee){}
}

function insertHTML(html) {
	if (editor==null) return;
	//document.all.div_bodytext.focus();
    editor.insertHTML(html);
}

function appendHTML(html) {
	if (editor==null) return;
    editor.appendHTML(html);
}

function highlight() {
	if (editor==null) return;
    editor.surroundHTML('<span style="background:yellow">', '</span>');
}

function lenB(str) {
	return str.replace(/[^\x00-\xff]/g,"**").length;
}

function pageAddFirst() {
	if (setCurPagetoHidden()) {
		var innerHtml = "";
		var pagenum = document.all("hi_pagenum").value;
   		pagenum++;
		//处理隐藏域
		innerHtml = "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + pagenum + "\" />";
		document.all("hi_bodytext").innerHTML += innerHtml;
		for (var i=pagenum; i>1; i--) {
			document.all("bodytext"+i).value = document.all("bodytext"+(i-1)).value;
		}
		document.all("bodytext"+1).value = "";
		//总页数
		document.all("hi_pagenum").value = pagenum;

		gotopage(1);
	}
}

function pageAdd() {
    if (setCurPagetoHidden()) {
        var innerHtml = "";
    	var pagenum = document.all("hi_pagenum").value;
   		pagenum++;
    	//增加隐藏域
    	innerHtml = "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + pagenum + "\" />";
    	document.all("hi_bodytext").innerHTML += innerHtml;
    	//总页数
    	document.all("hi_pagenum").value = pagenum;
		gotopage(pagenum);
    }
}

function pageInsert() {
	if (setCurPagetoHidden()) {
		var innerHtml = "";
		var curpage = document.all("hi_curpage").value;
    	var pagenum = document.all("hi_pagenum").value;
   		pagenum++;
    	//处理隐藏域
		innerHtml = "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + pagenum + "\" />";
    	document.all("hi_bodytext").innerHTML += innerHtml;
		var icurpage = parseInt(curpage);
		for (var i=pagenum; i>icurpage+1; i--) {
			document.all("bodytext"+i).value = document.all("bodytext"+(i-1)).value;
		}
		document.all("bodytext"+(icurpage+1)).value = "";
		//总页数
    	document.all("hi_pagenum").value = pagenum;
		gotopage(icurpage+1);
	}
}

function pageDeleteCurrent() {
	if (setCurPagetoHidden()) {
		var innerHtml = "";
		var curpage = document.all("hi_curpage").value;
		var pagenum = document.all("hi_pagenum").value;
		if (pagenum == "1") {
			alert("当前只有一页，不能删除！");
			return false;
		}
		//删除隐藏域
		var icurpage = parseInt(curpage);
		var pagecontent = new Array();
		for (var i=1; i<pagenum; i++)	{
			if (i<icurpage) {
				pagecontent[i] = document.all("bodytext"+i).value;
			} else {
				pagecontent[i] = document.all("bodytext"+(i+1)).value;
			}
		}
		document.all("hi_bodytext").innerHTML = "";
		for (var i=1; i<pagenum; i++) {
			document.all("hi_bodytext").innerHTML += "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + i + "\"/>";
			document.all("bodytext" + i).value = pagecontent[i];
		}
    	//总页数
		pagenum--;
		document.all("hi_pagenum").value = pagenum;
		//设置当前页的内容
		if (pagenum == 1) {
			editor.setHTML(document.all("bodytext" + 1).value);
			curpage = pagenum;
			document.all("hi_curpage").value = curpage;
		} else if (icurpage > pagenum) {
			editor.setHTML(document.all("bodytext" + pagenum).value);
			curpage = pagenum;
			document.all("hi_curpage").value = curpage;
		} else {
			editor.setHTML(document.all("bodytext" + icurpage).value);
		}
		gotopage(curpage);
	}
}

function pageDelete() {
	if (setCurPagetoHidden()) {
		var innerHtml = "";
		var pagenum = document.all("hi_pagenum").value;
		if (pagenum == "1") {
			alert("当前只有一页，不能删除！");
			return false;
		}
		//删除隐藏域
		var pagecontent = new Array();
		for (var i=1; i<pagenum; i++)	{
			pagecontent[i] = document.all("bodytext"+i).value;
		}
		document.all("hi_bodytext").innerHTML = "";
		for (var i=1; i<pagenum; i++) {
			document.all("hi_bodytext").innerHTML += "<input type=\"hidden\" name=\"cinfo\" id=\"bodytext" + i + "\"/>";
			document.all("bodytext" + i).value = pagecontent[i];
		}
    	pagenum--;
		//总页数
    	document.all("hi_pagenum").value = pagenum;
		//回到前一页
		editor.setHTML(document.all("bodytext" + pagenum).value);
		gotopage(pagenum);
	}
}

function setCurPagetoHidden() {
	if (editor==null) return true;
	//var curpage = document.all("hi_curpage").value;
	//document.all("bodytext" + curpage).value = editor.getHTML();
	
	if (editor.getHTML() == "") {
	    	alert("当前页的内容不能为空！");
    		//editor.setHTML(document.all("bodytext" + curpage).value);
    		return false;
    	}/**
     	else if (lenB(editor.getHTML()) > 4000) {
    	alert("当前页的内容太长了！");
    	//editor.setHTML(document.all("bodytext" + curpage).value);
    	return false;
	}**/
	var hicurpage = document.all("hi_curpage");
	if (typeof hicurpage == "undefined") return;
	var curpage = hicurpage.value;
	var bodytextcurpage = document.all("bodytext" + curpage);
	if (typeof bodytextcurpage == "undefined") return;
	bodytextcurpage.value = editor.getHTML();
	return true;
}

function gotopage(pageno) {
	if (editor==null) return;
    if (editor.getHTML() == "") {
    	alert("当前页的内容不能为空！");
    	return false;
    }
   	editor.setHTML(document.all("bodytext" + pageno).value);
	//处理页标签
	var innerHtml = "";
	var pagenum = document.all("hi_pagenum").value;
	for (var i=1; i<=pagenum; i++) {
		if (pageno == i) {
			innerHtml += "&nbsp;<" + i + ">&nbsp;";
		} else {
			innerHtml += "<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: getpage(" + i + ");\">&nbsp;<" + i + ">&nbsp;</a>";
		}
	}
	document.all("breakpage").innerHTML = innerHtml;
   	//当前页
   	document.all("hi_curpage").value = pageno;
}

function getpage(pageno) {
	if (editor==null) return;
    if (setCurPagetoHidden()) {
    	editor.setHTML(document.all("bodytext" + pageno).value);
		//处理页标签
		var innerHtml = "";
		var pagenum = document.all("hi_pagenum").value;
		for (var i=1; i<=pagenum; i++) {
			if (pageno == i) {
				innerHtml += "&nbsp;<" + i + ">&nbsp;";
			} else {
				innerHtml += "<a href=\"#\" style=\"text-decoration: none\" onclick=\"javascript: getpage(" + i + ");\">&nbsp;<" + i + ">&nbsp;</a>";
			}
		}
		document.all("breakpage").innerHTML = innerHtml;
    	//当前页
    	document.all("hi_curpage").value = pageno;
    }
}

var blocked = false;
function frm_submit(url) {
    if (form_check()) {
    	if (blocked) {
    		alert('提交正在进行中，不能重复提交');
    		return;
    	}
    	blocked = true;
    	form1.action = url;
    	form1.submit();
    	blocked = false;
    }
}

function pagePreview() {
	if (setCurPagetoHidden()) {
    	window.open("infoPreview.jsp?pagenum=" + document.all("hi_pagenum").value, "flag", "");
    }
}

function form_check() {
    return setCurPagetoHidden();
}

function setText(e){
	var obj = document.getElementById("upload");
	if (typeof obj == "undefined") return;
	if(e.checked){
		 obj.style.visibility="visible";
	}else{
		 obj.style.visibility="hidden";
	}
}

var xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")
xmlhttp.onreadystatechange=xmlhttpChange
var keyObj;

var btnBuild = null;
function buildKeyword(btn, obj, Str){
	if (typeof btn =="undefined") return;
	if (typeof obj =="undefined") return;
	if (typeof Str =="undefined") return;
	btnBuild = btn;
	btnBuild.disabled = true;
	keyObj = obj;
	if (xmlhttp){
	    xmlhttp.open("GET","splitWord.jsp?w="+Str,true)
    	xmlhttp.send()
	  }
}
function xmlhttpChange(){
	var rs = xmlhttp.readyState;
	var ss = 0;
	if (rs==4){
		ss = xmlhttp.status;
	  if (ss==200){
	    	xmlDoc=xmlhttp.responseText;
	    	try {
		    	keyObj.value = RTrim(LTrim(xmlDoc));
    		}catch(e){
	    		alert(e.message);
    		}
    		if (typeof btnBuild =="undefined") return;
			btnBuild.disabled = false;
   		}
	}
}

function checkPubDate(frm){
	var p = frm.dPubDate;
	var e = frm.dEndValidDate;
	if (typeof p =="undefined") return false;
	if (typeof e =="undefined") return false;
	if (p.value==""||p.value>e.value){
		alert('请正确选择发布日期与截止日期');
		return false;
	}
	return true;
}

//打开窗口,可以放大
function OpenNewWin(url)
{
var NewWin
NewWin=window.open(url,"","toolbar=0,scrollbars=1,resizable=1 ");
}