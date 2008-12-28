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
 * jeeϵͳ����Action
 * 
 * ����ͼƬ���У�ͼƬ�ϴ��ȹ��ܡ�
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
	 * �ϴ�ͼƬ
	 * 
	 * @return
	 */
	public String doUploadImg() {
		// �����ϴ�ҳ��
		log.debug("�ϴ��ļ���" + uploadFileFileName);
		if (uploadFileFileName == null) {
			error = "�ϴ�ʧ�ܣ�û���ҵ��ϴ��ļ�";
			log.info(error);
			return SUCCESS;
		}
		UploadRule rule = (UploadRule) contextPvd
				.getSessionAttr(UploadRule.KEY);
		if (rule == null) {
			error = "�ϴ�ʧ�ܣ�δ�ҵ��ϴ�����";
			log.info(error);
			return SUCCESS;
		}
		int suffixIndex = uploadFileFileName.indexOf('.');
		if (suffixIndex == -1) {
			error = "�ϴ�ʧ�ܣ��ļ�û�к�׺�����������ϴ���";
			log.info(error);
			return SUCCESS;
		}
		String name = uploadFileFileName.substring(0, suffixIndex);
		String suffix = uploadFileFileName.substring(suffixIndex + 1);
		if (StringUtils.isBlank(name)) {
			error = "�ϴ�ʧ�ܣ����ļ���û���ļ������������ϴ���";
			log.info(error);
			return SUCCESS;
		}
		if (rule.isGenName() && StrUtils.countCn(name) > name.length()) {
			error = "�ϴ�ʧ�ܣ����ļ����������ģ��������ϴ���";
			log.info(error);
			return SUCCESS;
		}
		if (!rule.getAcceptImg().contains(suffix.toLowerCase())) {
			error = "�ϴ�ʧ�ܣ��ú�׺���������ϴ���" + suffix;
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
				log.info("�ϴ���ѹ���ļ���{}��ѹ����ʱ��{}ms��", realPath, end - start);
			} else {
				FileUtils.copyFile(uploadFile, new File(realPath));
				log.info("�ϴ��ļ��ɹ���{}", realPath);
			}
			return SUCCESS;
		} catch (IOException e) {
			error = "�ϴ�ʧ�ܣ������ļ�ʧ�ܣ�";
			log.error("�ϴ��ļ�ʱ�������ļ�ʧ�ܣ�", e);
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
