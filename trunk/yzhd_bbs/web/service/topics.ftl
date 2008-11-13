<#assign isType = request.getParameter("type")?exists>
<#if isType>
	<#assign type = request.getParameter("type")>
	<#if "js"==type>
		function Topic(id,title,dateCreated,username){
			this.id = id;
			this.title = title;
			this.dateCreated = dateCreated;
			this.username = username;
		}
		var topics = new Array();
		<#list topics as topic>
		topics[topics.length] = new Topic(${topic.id},"${topic.title}","${topic.dateCreated?string("MM-dd")}","${URLEncoder.encode(topic.username)}");
		</#list>
	<#else>
		<#list topics as topic>
		<ul>
          <li><a href="http://bbs.ntsky.com/topic.action?topicId=${topic.id}" title="${topic.title}">${topic.title}</a><span style="padding-left:8px;color:#666;"> (${topic.dateCreated?datetime}) </span></li>
		</ul>
		</#list>
	</#if>
</#if>
