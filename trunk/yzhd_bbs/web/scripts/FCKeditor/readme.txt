��Դjavascript����һ����

һ��Fckeditor

1��FCKeditor/editor/css/fck_editorarea.css
P, UL, LI
{
	margin-top: 0px;
	margin-bottom: 0px;
}
�������ı��༭����������Ϣʱ��,�����м����ܴ�,ȥ��ע�Ϳ��Խ����

2��FCKeditor/fckeditor.js

	this.Width			= width			|| '98%' ;
	this.Height			= height		|| '400' ;
	this.ToolbarSet		= toolbarSet	|| 'Default' ;
	this.Value			= value			|| '' ;
	this.BasePath		= 'scripts/FCKeditor/' ;

3��FCKeditor/fckconfig.js
FCKConfig.DefaultLanguage		= 'zh-cn' ;

4��FCKeditor��default��basic�����ѱ仯

5��editor\filemanager\browser\default\frmupload.html
		case 201 :
			window.parent.frames['frmResourcesList'].Refresh() ;
			alert( ' ���� : ϵͳ�д���ͬ�����ļ�. ���ϴ����ļ���������Ϊ "' + data + '"' ) ;
			break ;
		case 202 :
			alert( ' ���� : ϵͳ��֧�����ϴ����ļ���ʽ. ' ) ;
6�������޸ĺ�5����
FCKeditor\editor\dialog\fck_flash\fck_flash.js
FCKeditor\editor\dialog\fck_image\fck_image.js
FCKeditor\editor\dialog\fck_link\fck_link.js

7��FCKeditor\editor\css\����img��borderΪ0


