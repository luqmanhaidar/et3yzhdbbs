开源javascript更新一览表

一、Fckeditor

1、FCKeditor/editor/css/fck_editorarea.css
P, UL, LI
{
	margin-top: 0px;
	margin-bottom: 0px;
}
由于在文本编辑器中输入信息时候,出现行间距离很大,去掉注释可以解决。

2、FCKeditor/fckeditor.js

	this.Width			= width			|| '98%' ;
	this.Height			= height		|| '400' ;
	this.ToolbarSet		= toolbarSet	|| 'Default' ;
	this.Value			= value			|| '' ;
	this.BasePath		= 'scripts/FCKeditor/' ;

3、FCKeditor/fckconfig.js
FCKConfig.DefaultLanguage		= 'zh-cn' ;

4、FCKeditor中default和basic属性已变化

5、editor\filemanager\browser\default\frmupload.html
		case 201 :
			window.parent.frames['frmResourcesList'].Refresh() ;
			alert( ' 警告 : 系统中存在同名的文件. 新上传的文件将被改名为 "' + data + '"' ) ;
			break ;
		case 202 :
			alert( ' 错误 : 系统不支持您上传的文件格式. ' ) ;
6、下面修改和5类似
FCKeditor\editor\dialog\fck_flash\fck_flash.js
FCKeditor\editor\dialog\fck_image\fck_image.js
FCKeditor\editor\dialog\fck_link\fck_link.js

7、FCKeditor\editor\css\增加img的border为0


