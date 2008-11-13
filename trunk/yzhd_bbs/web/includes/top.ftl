<div id="wrap">
  <!-- tmenu begin -->
  
  <!-- end #tmenu -->
  <#assign isLogo = application["basic"]["logo"]?exists>
  <#assign isBanner = application["basic"]["banner"]?exists>
  <!-- header begin -->
  <div id="header">
    <!-- logo -->
    <div id="logo"><#if isLogo><a href="${application["basic"]["bbsDomain"]?if_exists}"><img src="${application["basic"]["logo"]?if_exists}" alt="网站LOGO"/></a></#if></div>
    <!-- /logo -->
    <!-- banner -->
    <div id="banner">
   
    </div>
    <!-- /banner -->
  </div>
  <!-- end #header -->