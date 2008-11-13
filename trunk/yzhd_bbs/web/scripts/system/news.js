function StringBuffer(){
    this.data = [];
}
StringBuffer.prototype.append = function(){
    this.data.push(arguments[0]);    
}
StringBuffer.prototype.toString = function(){
    return this.data.join("");
}
function News(id,title){
	this.id = id ;
	this.title = title;
}
var newsArray = new Array();
newsArray[newsArray.length] = new News("1","test");
var sb = new StringBuffer();
if(newsArray!=null){
	for(i=0;i<newsArray.length;i++){
		news = newsArray[i];
		sb.append("<li><a href=\"http://www.chinascripts.com/news.action?newsId="+news.id+"\" title=\""+news.title+"\" target=\"_blank\">"+news.title+"</a></li>");
	}
}
document.write(sb.toString());