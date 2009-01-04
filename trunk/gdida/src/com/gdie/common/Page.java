package com.gdie.common;

/**
 * <p>Title: 通用程序包</p>
 * <p>Description: 分页类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gdie</p>
 * @author ycm@gdie.com
 * @version 1.0
 */

public class Page {

    /**
     * 分页时每页的记录数
     */
    public static final int pageViewCount = 15;
	/**
	 * 总页数
	 */
	private int pageCount = 0;
	/**
	 * 开始记录数，表示rs需要定位到begin，即rs.absolute(begin)
	 */
	private int begin = 0;
	/**
	 * 需要显示的条数，最后一页有可能会小于viewNo
	 */
	private int viewCount = 0;
	/**
	 * 要显示的页码
	 */
	private int pageNo = 0;

	public Page() {
	}

	/**
	 * 构造函数，初始化分页参数
	 * @param rowCount 总记录数
	 * @param viewNo 每页显示的数目
	 * @param pageNo 要显示的页码
	 */
	public Page(int rowCount, int viewNo, int pageNo) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		pageCount = rowCount / viewNo; //总页数
		if (rowCount > pageCount * viewNo) { //不满一页也算做一页
			pageCount++;
		}
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		begin = (pageNo - 1) * viewNo;
		viewCount = viewNo;
		if (rowCount - begin < viewNo) {
			viewCount = rowCount - begin;
		}
		this.pageNo = pageNo;
	}

	/**
	 * 应该显示的行数，如果是最后一页，可能会少于viewNo
	 * @return 应该显示的行数
	 */
	public int getViewCount() {
		return viewCount;
	}

	/**
	 * 总页数
	 * @return 总页数
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 应该向前滚动的条数
	 * @return 向前滚动的条数
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 应该显示的页码，如果页码超出了总页数，则页码改变为总页数
	 * @return 应该显示的页码
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 分页HTML代码
	 * @param iPage 页码
	 * @param iPageSize 页面大小
	 * @param iTotal 总页数
	 * @param pageUrl 页面URL
	 * @param searchStr Query String
	 * @return HTML代码
	 */
	public static String pageNav(int iPage, int iPageSize, int iTotal, String pageUrl, String searchStr) {
		String contentStr = new String("");
		int iTotalPage = (int) ( (iTotal + iPageSize - 1) / iPageSize);
		contentStr = contentStr + "第" + String.valueOf(iPage) + "页/共" + String.valueOf(iTotalPage) + "页(" + String.valueOf(iTotal) +
			"条记录)&nbsp;&nbsp;";
		if (iPage > 1) {
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=1&" + searchStr + "'>首页</a>&nbsp;&nbsp;";
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iPage - 1) + "&" + searchStr + "'>上一页</a>&nbsp;&nbsp;";
		} else {
			contentStr = contentStr + "首页&nbsp;&nbsp;";
			contentStr = contentStr + "上一页&nbsp;&nbsp;";
		}
		if (iPage < iTotalPage) {
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iPage + 1) + "&" + searchStr + "'>下一页</a>&nbsp;&nbsp;";
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iTotalPage) + "&" + searchStr + "'>尾页</a>&nbsp;&nbsp;";
		} else {
			contentStr = contentStr + "下一页&nbsp;&nbsp;";
			contentStr = contentStr + "尾页&nbsp;&nbsp;";
		}
		contentStr = contentStr + "<select onchange='javascript:go2page(this.value)'>";
		for (int i = 1; i <= iTotalPage; i++) {
			contentStr = contentStr + "<option value=" + String.valueOf(i);
			if (i == iPage) {
				contentStr = contentStr + " selected";
			}
			contentStr = contentStr + ">" + String.valueOf(i) + "</option>";
		}
		contentStr = contentStr + "</select>\n";
		contentStr = contentStr + "<script>\n";
		contentStr = contentStr + "function go2page(i){\n";
		contentStr = contentStr + "location.href='" + pageUrl + "?pageno='+i+'&" + searchStr + "';\n";
		contentStr = contentStr + "}\n";
		contentStr = contentStr + "</script>\n";
		return contentStr;
	}

	/**
	 * 页面跳转
	 * @param actionUrl 跳转路径
	 * @param waitSeconds 等待秒数
	 * @param pageMsg 提示信息
	 * @return HTML代码
	 */
	public static String redirectHeader(String actionUrl, int waitSeconds, String pageMsg) {
		return redirectHeader(actionUrl, waitSeconds, pageMsg, "_self");
	}

	/**
	 * 页面跳转
	 * @param actionUrl 跳转路径
	 * @param waitSeconds 等待秒数
	 * @param pageMsg 提示信息
	 * @param targetForm target窗口
	 * @return HTML代码
	 */
	public static String redirectHeader(String actionUrl, int waitSeconds, String pageMsg, String targetForm) {
		String contentStr = new String("");
		if (actionUrl == null || actionUrl.equals("")) {
			actionUrl = "/";
		}
		contentStr = contentStr + "<html>\n<head>\n";
		contentStr = contentStr + "<meta http-equiv='Content-Type' content='text/html; charset='GBK' />\n";
		contentStr = contentStr + "<meta http-equiv='Refresh' content='" + String.valueOf(waitSeconds) + "; url=" + actionUrl + "; target=" +
			targetForm + "' />\n";
		contentStr = contentStr + "</head><body>\n";
		contentStr = contentStr + "<div style='text-align:center; background-color: #EBEBEB; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF; border-right: 1px solid #AAAAAA; border-bottom: 1px solid #AAAAAA; font-weight : bold;'>\n";
		contentStr = contentStr + "<h4>" + pageMsg + "</h4>\n";
		contentStr = contentStr + "<p>假如系统没有跳转，请点击<a href=\"" + actionUrl + "\" target=\"" + targetForm + "\">这里</a>继续</p>\n";
		contentStr = contentStr + "</div>\n</body>\n</html>\n";
		return contentStr;
	}

	/**
	 * 关闭窗口
	 * @param waitSeconds 等待秒数
	 * @param pageMsg=提示信息
	 */
	public static String closeWindow(int waitSeconds, String pageMsg) {
		return closeWindow("", waitSeconds, pageMsg);
	}

	public static String closeWindow(String functionlist, int waitSeconds, String pageMsg) {
		if (functionlist == null) {
			functionlist = "";
		}
		String contentStr = new String("");
		contentStr = contentStr + "<html>\n<head>\n";
		contentStr = contentStr + "<meta http-equiv='Content-Type' content='text/html; charset='GBK' />\n";
		contentStr = contentStr + "<title></title>\n";
		contentStr = contentStr + "</head><body>\n";
		contentStr = contentStr + "<div style='text-align:center; background-color: #EBEBEB; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF; border-right: 1px solid #AAAAAA; border-bottom: 1px solid #AAAAAA; font-weight : bold;'>\n";
		contentStr = contentStr + "<h4>" + pageMsg + "</h4>\n";
		contentStr = contentStr + "<h5>" + String.valueOf(waitSeconds) + "秒后关闭窗口</h5>\n";
		contentStr = contentStr + "<p>假如窗口没有关闭，请点击<a href=\"javascript:" + functionlist + ";window.close();opener.focus();\">这里</a></p>\n";
		contentStr = contentStr + "<script>\nsetTimeout('" + functionlist + ";window.close();opener.focus();'," + String.valueOf(waitSeconds * 1000) +
			");</script>\n";
		contentStr = contentStr + "</div>\n</body>\n</html>\n";
		return contentStr;
	}

	/**
	 * 页面输出
	 * @author Samland
	 * @param o
	 * @return
	 */
	public static String output(Object o){
		if (o==null) return "";
		else return o.toString();
	}
	public static String output(int i){
		return String.valueOf(i);
	}
	public static String output(boolean i){
		return String.valueOf(i);
	}
	public static String output(double i){
		return String.valueOf(i);
	}
	public static String output(float i){
		return String.valueOf(i);
	}
	/**
	 * 页面输出，如果o为空，则输出sDef
	 * @author Samland
	 * @param o
	 * @param sDef
	 * @return
	 */
	/*public static String output(Object o, String sDef){
		if (o==null) return sDef;
		if (o.equals("")) return sDef;
		else return Format.htmlspecialcharector(o.toString());
	}*/
	/**
	 * 文本转换成HTML输出
	 * @param s
	 * @return
	 */
	public static String text2html(String s){
		String sResult = Page.nl2br(s);
		return Page.blank2nbsp(sResult);
	}
	/**
	 * 中文字符串长度
	 * @param str
	 * @return
	 */
	public static int CN_length(String s){
		if (s==null) return 0;
		int enLen=0;
		int chLen=0;
		for(int i=0;i<s.length();i++) {
			if (new Character(s.charAt(i)).charValue()<128){
				enLen++;
			}else{
				chLen++;
			}
		}
		//System.out.println("CN_Length:"+s+":"+(enLen + chLen*2)+"/"+s.length());
		return (enLen + chLen*2);
	}
	/**
	 * 输出字符串左边部分
	 * @param s
	 * @param l
	 * @return
	 */
	public static String leftStr(String s, int l){
		if (s==null) return "";
		if (CN_length(s)<=l) return s;
		StringBuffer sResult = new StringBuffer();
		int i=0;
		while (i<l){
			sResult.append(s.charAt(i));
			if (new Character(s.charAt(i)).charValue()>127){
				l--;
			}
			i++;
		}
		
		return sResult.toString().concat("...");
	}
	/**
	 * 把回车转换成br
	 * @param o
	 * @return
	 */
	public static String nl2br(Object o){
		if (o==null) return "";
		else {
			String sResult = o.toString().replaceAll("\n","<br>");
			/*sResult = sResult.replaceAll("\r\n","<br>");
			sResult = sResult.replaceAll("\n","<br>");
			sResult = sResult.replaceAll("\r","<br>");*/
			return sResult;
		}
	}
	/**
	 * 把空格换成[&nbsp];
	 * @param o
	 * @return
	 */
	public static String blank2nbsp(Object o){
		if (o==null) return "";
		else return o.toString().replaceAll(" ","&nbsp;");
	}	
    public static String escapeHtml(String o){
        if (o==null) return o;
        StringBuffer t = new StringBuffer();
        int i = 0;
        while (i<o.length()) {
            if (o.charAt(i)=='<') {
                i++;
                while (i<o.length()) {
                    if (o.charAt(i)=='>') break;
                    i++;
                }
                i++;
            }else{
                t.append(o.charAt(i));
                i++;    
            }
        }
        return t.toString();
    }
    

}
