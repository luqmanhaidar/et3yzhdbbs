<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>BBS管理 —— 公告管理</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
</head>
<body style="margin: 0px;padding:0px;">
<br/>
<!-- basic info begin -->
<div class="box1 dashed">
  <h5>说明</h5>
  <ul>
  	<li>文件后缀名称以","分隔</li>
	<li>每次上传文件大小在用户角色中设置</li>
  </ul>
</div>
<!-- end #basic info -->
<div class="box1" style="border:0px;">
  <!-- tab-pane begin -->
  <div class="tab-pane" id="tabPane1">
    <div class="tab-page" id="tabPage1">
      <h2 class="tab">文件属性</h2>
		<div class="box1" style="margin-top:2px;padding-top:2px;">
			<form action="updateRole.action" method="post">
			  <input name="id" type="hidden" class="t" value="1"/>
			  <div class="content" style="border-top:0px;">
				<div class="ibox">
				  <div class="it">每次上传文件数: *</div>
				  <div class="iv">
					<input name="name" type="text" class="t" value="系统管理员" size="40"/></div>
				</div>
				<div class="ibox" style="height:120px;">
				  <div class="it">允许的文件后缀名: * </div>
				  <div class="iv" style="height:112px; width:68%">
					<textarea name="description" cols="50" rows="7" id="copyright_d">system manager</textarea>            
				  </div>
				</div>         
				
				<div class="ibox">
				  <div class="it">每次上传文件大小:*</div>
				  <div class="iv">
					<input id="minTopic" name="minTopic" type="text" class="t" value="-1"/>
				  </div>
				</div>
			 </div>
			   <div class="box3" style="text-align:center; clear:both">
				<div>
				  <input type="submit" value="设置上传文件属性" name="submit" class="b"/>
				</div>
			  </div>
	    </div>
    </div>
    <div class="tab-page" id="tabPage2">
      <h2 class="tab">图片属性</h2>
      <div style="border:5px;border: 0px solid silver;">
        <div class="ibox" style="border-bottom: 0px">
          <div class="it">每次上传图片数: *</div>
          <div class="iv" style="border-left: 0px">
            <input type="text" name="name" class="t"/>
          </div>
        </div>
        <div class="ibox" style="border-bottom: 0px">
          <div class="it">允许的图片后缀名: * </div>
          <div class="iv" style="border-left: 0px">
            <input type="text" name="newPassword" class="t"/>
          </div>
        </div>
        <div class="ibox" style="border-bottom: 0px">
          <div class="it">每次上传图片总大小:*</div>
          <div class="iv" style="border-left: 0px">
            <input type="text" name="confirmPassword" class="t"/>
          </div>
        </div>
        <div class="ibox" style="border-bottom: 0px">
          <div class="it">上传单个图片大小:*</div>
          <div class="iv" style="border-left: 0px">
            <input type="text" name="confirmPassword" class="t"/>
          </div>
        </div>
        <div class="box3" style="text-align:center; clear:both">
          <div>
            <input type="submit" value="设置上传图片属性" name="agree" class="b"/>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end #tab-pane  -->
</div>
<!-- footer begin -->
<div id="footer">
  <div id="copyleft">Copyright &#169; 2001-2005, The NtSky NetWork Studio.<br/>
    This blog's version 1.0. Connect Author : <a href="mailto:yntsky@gmail.com">yntsky@gmail.com</a> <a href="http://validator.w3.org/check?uri=referer">Valid HTML 4.01</a> </div>
</div>
<!-- end #footer -->
</body>
</html>
