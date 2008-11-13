/**
 * System class 
 */
var System = {

	getSelectedValue : function(optionsArray){
		for(var i=0;i<optionsArray.length;i++){
			if(optionsArray[i].selected){
				return optionsArray[i].value;
			}
		}
		return "";
		optionsArray = null;
	},
	
	batchExecute : function( formId, tip, url, parTarget ) {

		var isRadios = false;
		var form = document.getElementById(formId);
		for (var i=0; i<form.elements.length; i++) {
			var e = form.elements[i];
			if (e.name == "id" && e.type=='checkbox') {
				isRadios = true;
				break;
			}
		}

		if(!isRadios){
			alert("Sorry,数据为空！");
			return;
		}

		if( !Forms.isCheckedBox( formId ) ) {
			alert("请先选择记录");
			return ;
		}
		if (confirm(tip)){
			with(document.getElementById(formId)) {
				action = url ;
				if( parTarget == null ) {
					target = "_self" ;
				}
				submit();
			}
		}
	},
	
	checkedAll : function( ids, formId ) {
		if( ids.checked ) {
			Forms.checkedAllBox( formId ) ;
		}
		else{
			Forms.unCheckedAllBox( formId ) ;
		}
	}

}

window.onload = function(){
	// stat
	//var currentLocation = window.location.href;
	//document.getElementById("Stat").src="createStat.action?referer="+window.location.href+"("+document.title+")";
	//alert(document.getElementById("Stat").src);
}


function action_btn( elementId, btnInfo ){
	if($(elementId).style.display=='block'){
		Util.hidden(elementId);
		$("btn_"+elementId).value="打开"+btnInfo;
	}
	else{
		Util.show(elementId);
		$("btn_"+elementId).value="关闭"+btnInfo;
	}
}

/**
 * 文本编辑器加载类
 */
var replaceMyTextarea = function(textareaId,toolbarName,path,height,width) {
	var oFCKeditor = new FCKeditor(textareaId) ;
	if( (toolbarName==null) || (toolbarName=="") ) {
		oFCKeditor.ToolbarSet = "Basic";
	}
	else{
		oFCKeditor.ToolbarSet = toolbarName;
	}
	if( (path==null) || (path=="") ) {
		oFCKeditor.BasePath = '../scripts/FCKeditor/';
	}
	else{
		oFCKeditor.BasePath = path + '/scripts/FCKeditor/';
	}
	if( (height == null) || (height=="") ) {
		oFCKeditor.Height = 200;
	}
	else{
		oFCKeditor.Height = height;
	}
	if( (width == null) || (width=="") ) {

	}
	else{
		oFCKeditor.Width = width;
	}
	oFCKeditor.ReplaceTextarea() ;
}

/*************** Class ****************/
// link
function Link(id,name,url,logo,description){
	this.id = id;
	this.name = name;
	this.url = url;
	this.logo = logo;
	this.description = description;
}
function LinkClass(){
	this.textlinks = [];
	this.imageLinks = [];
}
LinkClass.prototype.addText = function(id,name,url,logo,description) {
	this.textlinks[this.textlinks.length] = new Link(id,name,url,logo,description);
}
LinkClass.prototype.addImage = function(id,name,url,logo,description) {
	this.imageLinks[this.imageLinks.length] = new Link(id,name,url,logo,description);
}
LinkClass.prototype.writeTextLink = function(){
	for(var i=0;i<this.textlinks.length;i++){
		link = this.textlinks[i];
		document.write("<div class=\"textlink\"><a title=\""+link.name+"\" href=\""+link.url+"\" rel=\"_blank\">"+link.name+"</a></div>");
		// 每六条记录换行
		if((i+1)%6==0){
		document.write("<br/>");
		}
	}
}
LinkClass.prototype.writeImageLink = function(){
	for(var i=0;i<this.imageLinks.length;i++){
		link = this.imageLinks[i];
		document.write("<div class=\"imagelink\"><a title=\""+link.description+"\" href=\""+link.url+"\" rel=\"_blank\"><img width=\"88\" height=\"31\" alt=\""+link.description+"\" src=\""+link.logo+"\" /></a></div>");
	}
}
var linkClass = new LinkClass();


// announcement
function Announcement(id,title,dateCreated){
	this.id = id;
	this.title = title;
	this.dateCreated = dateCreated;
}
function AnnouncementClass(){
	this.announcements = [];
	this.announcementStr = "";
}
AnnouncementClass.prototype.add = function(id,title,dateCreated) {
	this.announcements[this.announcements.length] = new Announcement(id,title,dateCreated);
}
AnnouncementClass.prototype.getAnnouncements = function(){
	for(var i=0;i<this.announcements.length;i++){
		announcement = this.announcements[i];
		this.announcementStr = this.announcementStr + "<li><a href=\"#\" onClick=\"javascript:Util.openWindow('popup-announcement.action?id="+announcement.id+"',500,300);\" title=\""+announcement.title+"\">"+announcement.title+"</a></li>";
		if(i==2) break;
	}
	return this.announcementStr;
}
AnnouncementClass.prototype.getScrollAnnouncements = function(){
	for(var i=0;i<this.announcements.length;i++){
		announcement = this.announcements[i];
		this.announcementStr = this.announcementStr + "<a href=\"#\" onClick=\"javascript:Util.openWindow('popup-announcement.action?id="+announcement.id+"',500,300);\" title=\""+announcement.title+"\">"+announcement.title+"</a> ";
		if(i==2) break;
	}
	return this.announcementStr;
}
var announcementClass = new AnnouncementClass();