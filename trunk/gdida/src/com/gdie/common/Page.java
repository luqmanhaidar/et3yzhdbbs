package com.gdie.common;

/**
 * <p>Title: ͨ�ó����</p>
 * <p>Description: ��ҳ��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gdie</p>
 * @author ycm@gdie.com
 * @version 1.0
 */

public class Page {

    /**
     * ��ҳʱÿҳ�ļ�¼��
     */
    public static final int pageViewCount = 15;
	/**
	 * ��ҳ��
	 */
	private int pageCount = 0;
	/**
	 * ��ʼ��¼������ʾrs��Ҫ��λ��begin����rs.absolute(begin)
	 */
	private int begin = 0;
	/**
	 * ��Ҫ��ʾ�����������һҳ�п��ܻ�С��viewNo
	 */
	private int viewCount = 0;
	/**
	 * Ҫ��ʾ��ҳ��
	 */
	private int pageNo = 0;

	public Page() {
	}

	/**
	 * ���캯������ʼ����ҳ����
	 * @param rowCount �ܼ�¼��
	 * @param viewNo ÿҳ��ʾ����Ŀ
	 * @param pageNo Ҫ��ʾ��ҳ��
	 */
	public Page(int rowCount, int viewNo, int pageNo) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		pageCount = rowCount / viewNo; //��ҳ��
		if (rowCount > pageCount * viewNo) { //����һҳҲ����һҳ
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
	 * Ӧ����ʾ����������������һҳ�����ܻ�����viewNo
	 * @return Ӧ����ʾ������
	 */
	public int getViewCount() {
		return viewCount;
	}

	/**
	 * ��ҳ��
	 * @return ��ҳ��
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Ӧ����ǰ����������
	 * @return ��ǰ����������
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * Ӧ����ʾ��ҳ�룬���ҳ�볬������ҳ������ҳ��ı�Ϊ��ҳ��
	 * @return Ӧ����ʾ��ҳ��
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * ��ҳHTML����
	 * @param iPage ҳ��
	 * @param iPageSize ҳ���С
	 * @param iTotal ��ҳ��
	 * @param pageUrl ҳ��URL
	 * @param searchStr Query String
	 * @return HTML����
	 */
	public static String pageNav(int iPage, int iPageSize, int iTotal, String pageUrl, String searchStr) {
		String contentStr = new String("");
		int iTotalPage = (int) ( (iTotal + iPageSize - 1) / iPageSize);
		contentStr = contentStr + "��" + String.valueOf(iPage) + "ҳ/��" + String.valueOf(iTotalPage) + "ҳ(" + String.valueOf(iTotal) +
			"����¼)&nbsp;&nbsp;";
		if (iPage > 1) {
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=1&" + searchStr + "'>��ҳ</a>&nbsp;&nbsp;";
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iPage - 1) + "&" + searchStr + "'>��һҳ</a>&nbsp;&nbsp;";
		} else {
			contentStr = contentStr + "��ҳ&nbsp;&nbsp;";
			contentStr = contentStr + "��һҳ&nbsp;&nbsp;";
		}
		if (iPage < iTotalPage) {
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iPage + 1) + "&" + searchStr + "'>��һҳ</a>&nbsp;&nbsp;";
			contentStr = contentStr + "<a href='" + pageUrl + "?pageno=" + String.valueOf(iTotalPage) + "&" + searchStr + "'>βҳ</a>&nbsp;&nbsp;";
		} else {
			contentStr = contentStr + "��һҳ&nbsp;&nbsp;";
			contentStr = contentStr + "βҳ&nbsp;&nbsp;";
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
	 * ҳ����ת
	 * @param actionUrl ��ת·��
	 * @param waitSeconds �ȴ�����
	 * @param pageMsg ��ʾ��Ϣ
	 * @return HTML����
	 */
	public static String redirectHeader(String actionUrl, int waitSeconds, String pageMsg) {
		return redirectHeader(actionUrl, waitSeconds, pageMsg, "_self");
	}

	/**
	 * ҳ����ת
	 * @param actionUrl ��ת·��
	 * @param waitSeconds �ȴ�����
	 * @param pageMsg ��ʾ��Ϣ
	 * @param targetForm target����
	 * @return HTML����
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
		contentStr = contentStr + "<p>����ϵͳû����ת������<a href=\"" + actionUrl + "\" target=\"" + targetForm + "\">����</a>����</p>\n";
		contentStr = contentStr + "</div>\n</body>\n</html>\n";
		return contentStr;
	}

	/**
	 * �رմ���
	 * @param waitSeconds �ȴ�����
	 * @param pageMsg=��ʾ��Ϣ
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
		contentStr = contentStr + "<h5>" + String.valueOf(waitSeconds) + "���رմ���</h5>\n";
		contentStr = contentStr + "<p>���細��û�йرգ�����<a href=\"javascript:" + functionlist + ";window.close();opener.focus();\">����</a></p>\n";
		contentStr = contentStr + "<script>\nsetTimeout('" + functionlist + ";window.close();opener.focus();'," + String.valueOf(waitSeconds * 1000) +
			");</script>\n";
		contentStr = contentStr + "</div>\n</body>\n</html>\n";
		return contentStr;
	}

	/**
	 * ҳ�����
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
	 * ҳ����������oΪ�գ������sDef
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
	 * �ı�ת����HTML���
	 * @param s
	 * @return
	 */
	public static String text2html(String s){
		String sResult = Page.nl2br(s);
		return Page.blank2nbsp(sResult);
	}
	/**
	 * �����ַ�������
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
	 * ����ַ�����߲���
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
	 * �ѻس�ת����br
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
	 * �ѿո񻻳�[&nbsp];
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
