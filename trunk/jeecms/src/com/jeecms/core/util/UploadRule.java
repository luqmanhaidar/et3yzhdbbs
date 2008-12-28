package com.jeecms.core.util;

import static com.jeecms.core.Constants.SPT;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.ponyjava.common.util.StrUtils;

/**
 * 上传规则定义类。
 * 
 * 在上传之前将上传规则对象保存在session中，之后编辑器或其他上传对象将根据上传规则上传文件。
 * 
 * @author liufang
 * 
 */
public class UploadRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 在session中的key
	 */
	public static final String KEY = "_upload_rule";

	public UploadRule() {
	}

	/**
	 * 构造器
	 * 
	 * @param path
	 *            上传路径前缀
	 * @param isGenName
	 *            是否生成图片
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
	 * 上传路径
	 */
	private String pathPerfix;
	/**
	 * 根路径
	 */
	private String rootPath;

	/**
	 * 获得文件全名
	 * 
	 * 目录前缀/年+季度/月+日/文件名.suffix
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
	 * 是否生成文件名
	 */
	private boolean isGenName = true;

	/**
	 * 获得文件名
	 * 
	 * 4位随机数加上当前时间
	 * 
	 * @return
	 */
	public String getFileName() {
		String name = StrUtils.longToN36(System.currentTimeMillis());
		return RandomStringUtils.random(4, StrUtils.N36_CHARS) + name;
	}

	/**
	 * 可以上传的文件后缀
	 */
	private Set<String> acceptImg;

	/**
	 * 获得可图片的后缀，如没有指定，则使用默认的后缀集合。
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
	 * 默认的可上传文件后缀
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
