package com.jeecms.core.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.core.Constants;
import com.jeecms.core.util.UploadRule;
import com.ponyjava.common.util.ImageScale;
import com.ponyjava.common.util.StrUtils;

/**
 * jee系统公用Action
 * 
 * 包含图片剪切，图片上传等功能。
 * 
 * @author liufang
 * 
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.imgUploadAct")
public class ImgUploadAct extends com.jeecms.core.JeeCoreAction {
	private static final Logger log = LoggerFactory
			.getLogger(ImgUploadAct.class);

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	public String doUploadImg() {
		// 进入上传页面
		log.debug("上传文件：" + uploadFileFileName);
		if (uploadFileFileName == null) {
			error = "上传失败！没有找到上传文件";
			log.info(error);
			return SUCCESS;
		}
		UploadRule rule = (UploadRule) contextPvd
				.getSessionAttr(UploadRule.KEY);
		if (rule == null) {
			error = "上传失败！未找到上传规则！";
			log.info(error);
			return SUCCESS;
		}
		int suffixIndex = uploadFileFileName.indexOf('.');
		if (suffixIndex == -1) {
			error = "上传失败！文件没有后缀名，不允许上传！";
			log.info(error);
			return SUCCESS;
		}
		String name = uploadFileFileName.substring(0, suffixIndex);
		String suffix = uploadFileFileName.substring(suffixIndex + 1);
		if (StringUtils.isBlank(name)) {
			error = "上传失败！该文件名没有文件名，不允许上传！";
			log.info(error);
			return SUCCESS;
		}
		if (rule.isGenName() && StrUtils.countCn(name) > name.length()) {
			error = "上传失败！该文件名包含中文，不允许上传！";
			log.info(error);
			return SUCCESS;
		}
		if (!rule.getAcceptImg().contains(suffix.toLowerCase())) {
			error = "上传失败！该后缀名不允许上传：" + suffix;
			log.info(error);
			return SUCCESS;
		}
		uploadPath = rule.getPathName(name, suffix, Constants.UPLOAD_IMAGE);
		String imgRelPath = getWeb().getUploadRoot().append(uploadPath)
				.toString();
		String realPath = contextPvd.getAppRealPath(imgRelPath);
		try {
			if (isZoom) {
				long start = System.currentTimeMillis();
				ImageScale.resizeFix(uploadFile, new File(realPath), suffix,
						zoomWidth, zoomHeight);
				long end = System.currentTimeMillis();
				log.info("上传并压缩文件：{}；压缩耗时：{}ms。", realPath, end - start);
			} else {
				FileUtils.copyFile(uploadFile, new File(realPath));
				log.info("上传文件成功：{}", realPath);
			}
			return SUCCESS;
		} catch (IOException e) {
			error = "上传失败！拷贝文件失败！";
			log.error("上传文件时，拷贝文件失败！", e);
			return SUCCESS;
		}
	}

	private java.io.File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	private boolean isZoom = false;
	private int zoomWidth = 139;
	private int zoomHeight = 139;

	private String uploadPath;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public java.io.File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(java.io.File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public int getZoomWidth() {
		return zoomWidth;
	}

	public void setZoomWidth(int zoomWidth) {
		this.zoomWidth = zoomWidth;
	}

	public boolean isZoom() {
		return isZoom;
	}

	public void setZoom(boolean isZoom) {
		this.isZoom = isZoom;
	}

	public int getZoomHeight() {
		return zoomHeight;
	}

	public void setZoomHeight(int zoomHeight) {
		this.zoomHeight = zoomHeight;
	}
}
