package com.jeecms.core.util;

import static com.jeecms.core.Constants.SPT;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.ponyjava.common.util.StrUtils;

/**
 * �ϴ��������ࡣ
 * 
 * ���ϴ�֮ǰ���ϴ�������󱣴���session�У�֮��༭���������ϴ����󽫸����ϴ������ϴ��ļ���
 * 
 * @author liufang
 * 
 */
public class UploadRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ��session�е�key
	 */
	public static final String KEY = "_upload_rule";

	public UploadRule() {
	}

	/**
	 * ������
	 * 
	 * @param path
	 *            �ϴ�·��ǰ׺
	 * @param isGenName
	 *            �Ƿ�����ͼƬ
	 */
	public UploadRule(String pathPerfix, boolean isGenName) {
		this.pathPerfix = pathPerfix;
		this.isGenName = isGenName;
	}

	public UploadRule(String rootPath, String pathPerfix, boolean isGenName) {
		this.rootPath = rootPath;
		this.pathPerfix = pathPerfix;
		this.isGenName = isGenName;
	}

	/**
	 * �ϴ�·��
	 */
	private String pathPerfix;
	/**
	 * ��·��
	 */
	private String rootPath;

	/**
	 * ����ļ�ȫ��
	 * 
	 * Ŀ¼ǰ׺/��+����/��+��/�ļ���.suffix
	 * 
	 * @return
	 */
	public String getPathName(String fileName, String suffix, String type) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(pathPerfix)) {
			sb.append(pathPerfix);
		}
		sb.append(type).append(getFilePath());
		if (isGenName) {
			sb.append(getFileName());
		} else {
			sb.append(fileName);
		}
		sb.append('.').append(suffix);
		return sb.toString();
	}

	public static String getFilePath() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		sb.append(SPT).append(cal.get(Calendar.YEAR)).append('_').append(
				cal.get((Calendar.MONTH)) / 3 + 1).append(SPT).append(
				cal.get(Calendar.MONTH) + 1).append('_').append(
				cal.get(Calendar.DAY_OF_MONTH)).append(SPT);
		return sb.toString();
	}

	/**
	 * �Ƿ������ļ���
	 */
	private boolean isGenName = true;

	/**
	 * ����ļ���
	 * 
	 * 4λ��������ϵ�ǰʱ��
	 * 
	 * @return
	 */
	public String getFileName() {
		String name = StrUtils.longToN36(System.currentTimeMillis());
		return RandomStringUtils.random(4, StrUtils.N36_CHARS) + name;
	}

	/**
	 * �����ϴ����ļ���׺
	 */
	private Set<String> acceptImg;

	/**
	 * ��ÿ�ͼƬ�ĺ�׺����û��ָ������ʹ��Ĭ�ϵĺ�׺���ϡ�
	 * 
	 * @return
	 */
	public Set<String> getAcceptImg() {
		if (acceptImg == null) {
			acceptImg = new HashSet<String>();
			for (String s : DEF_IMG_ACCEPT) {
				acceptImg.add(s);
			}
		}
		return acceptImg;
	}

	/**
	 * Ĭ�ϵĿ��ϴ��ļ���׺
	 */
	public static final String[] DEF_IMG_ACCEPT = { "jpg", "gif", "jpeg",
			"png", "bmp", };

	public static void main(String[] args) {
		UploadRule rule = new UploadRule();
		System.out.println(rule.getPathName("", "jpg", "img"));
	}

	public boolean isGenName() {
		return isGenName;
	}

	public void setGenName(boolean isGenName) {
		this.isGenName = isGenName;
	}

	public String getPathPerfix() {
		return pathPerfix;
	}

	public void setPathPerfix(String pathPerfix) {
		this.pathPerfix = pathPerfix;
	}

	public void setAcceptImg(Set<String> acceptImg) {
		this.acceptImg = acceptImg;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

}
