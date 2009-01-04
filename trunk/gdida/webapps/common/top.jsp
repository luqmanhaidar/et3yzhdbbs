<%@ page contentType="text/html; charset=GBK" %>
<%String sUrlTop = request.getContextPath();%>
<form name="sform" action="<%=sUrlTop %>/info_search_result.jsp" method=post>
<input name="stitle" type=hidden>
</form>

              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr height="32">
                  <td width="418"></td>
                  <td width="58"><img src="<%=sUrlTop %>/images/shousuo.jpg" width="58" height="28"></td>
                  <td width="100"><input name=sword type=text value="" size="14"></td>
                  <td width="35"><a href='javascript:gosearch();'><img src="<%=sUrlTop %>/images/shousuo1.jpg" width="35" height="28" border="0"></a></td>
				  <td width="10"></td>
                </tr>
</table>
              
<script language="javascript">
function gosearch(){
	with(document.all){
		if (sword.value!='') {
        	stitle.value=sword.value;
        	sform.target="_blank";
        	sform.submit();
        }
	}
}
</script>