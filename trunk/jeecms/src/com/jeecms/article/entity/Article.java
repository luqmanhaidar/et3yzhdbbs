package com.jeecms.article.entity;

import static com.jeecms.core.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.article.entity.base.BaseArticle;
import com.jeecms.cms.Constants;
import com.jeecms.core.util.ContentInterface;
import com.ponyjava.common.util.ComUtils;
import com.ponyjava.common.util.StrUtils;

public class Article extends BaseArticle implements ContentInterface {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Article.class);
	/**
	 * ���±����ݵĴ�ŵ�ַ
	 */
	public static final String ARTICLE_PATH = "article_content";
	/**
	 * �ϴ�����ͼƬ��Ե�ַ
	 */
	public static final String UPLOAD_PATH = SPT + Constants.ARTICLE_SYS;
	public static final String SUFFIX = ".txt";
	public static final int SPLIT_COUNT = 1000;
	public static final String SPLIT = "<div>[NextPage][/NextPage]</div>";
	public static final String SPLIT_REG = "<div>\\[NextPage\\]\\[/NextPage\\]</div>";

	/**
	 * ���url��ַ
	 * 
	 * @return
	 */
	public String getUrl() {
		if (!StringUtils.isBlank(getOuterUrl())) {
			return getOuterUrl();
		}
		StringBuilder sb = getWebsite().getWebUrlBuf();
		String path = getChannel().getPath();
		if (!StringUtils.isBlank(path)) {
			sb.append(SPT).append(path);
		}
		sb.append(SPT).append(getId()).append(".").append(
				getWebsite().getSuffix());
		return sb.toString();
	}

	/**
	 * ѡ��ģ��
	 * 
	 * @return
	 */
	// @ TODO ��������ָ��ģ��
	public String chooseTpl() {
		return getChannel().chooseTplContent();
	}

	/**
	 * ��õ�Nҳ���������·�����û������ļ���
	 * 
	 * @param pageNo
	 * @return
	 */
	public String relPath(int pageNo) {
		StringBuilder sb = getWebsite().getUserRoot();
		sb.append(SPT).append(ARTICLE_PATH).append(SPT).append(
				(getId() / SPLIT_COUNT) + 1).append(SPT).append(getId())
				.append("_").append(pageNo).append(SUFFIX);
		return sb.toString();
	}

	/**
	 * ��õ�Nҳ�ľ��Ե�ַ
	 * 
	 * @param root
	 * @param pageNo
	 * @return
	 */
	private String getRealPath(String root, int pageNo) {
		StringBuilder sb = new StringBuilder(root);
		sb.append(relPath(pageNo));
		return sb.toString().replace(SPT, File.separatorChar);
	}

	/**
	 * ���ļ���ȡ����
	 * 
	 * @return
	 */
	public String getContentFromFile() {
		if (rootReal == null) {
			throw new RuntimeException("�������÷���������·��rootReal");
		}
		return getContentFromFile(rootReal);
	}

	/**
	 * ���ļ���ȡ����
	 * 
	 * @param root
	 * @return
	 */
	public String getContentFromFile(String root) {
		// @ TODO ������Դ·����������������·���ı������
		StringBuilder sb = new StringBuilder();
		try {
			File f = null;
			Integer count = getPageCount();
			if (count == null) {
				count = 0;
			}
			for (int i = 0; i < count; i++) {
				f = new File(getRealPath(root, i + 1));
				sb.append(FileUtils.readFileToString(f));
				if (i + 1 < count) {
					sb.append(SPLIT);
				}
			}
		} catch (IOException e) {
			log.error("��ȡ��������ʧ��", e);
		}
		return sb.toString();
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param root
	 */
	public void deleteContentFile(String root) {
		File f = null;
		Integer count = getPageCount();
		if (count == null) {
			count = 0;
		}
		for (int i = 0; i < count; i++) {
			f = new File(getRealPath(root, i + 1));
			f.deleteOnExit();
		}
	}

	/**
	 * ������д���ļ�����ɾ�������ҳ��
	 * 
	 * @param origCount
	 *            ԭ�з�ҳ��0Ϊԭ����û������
	 * 
	 * @param root
	 */
	public void writeContent(String root, int origCount) {
		String c = getContent();
		if (c == null) {
			c = "";
		}
		String[] arr = c.split(SPLIT_REG);
		try {
			int currCount = arr.length;
			for (int i = 0; i < currCount; i++) {
				File f = new File(getRealPath(root, i + 1));
				FileUtils.writeStringToFile(f, arr[i]);
			}
			for (int i = currCount; i < origCount; i++) {
				File f = new File(getRealPath(root, i + 1));
				f.deleteOnExit();
			}
		} catch (IOException e) {
			log.error("д��������ʧ��", e);
		}
	}

	/**
	 * ��������е�ҳ��
	 * 
	 * @return
	 */
	public int getPageCountFromContent() {
		String c = getContent();
		if (StringUtils.isEmpty(c)) {
			return 0;
		} else {
			return StringUtils.countMatches(getContent(), SPLIT) + 1;
		}
	}

	
	public String desc(int len) {
		String s = getDescription();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			return StrUtils.getCn(s, len);
		}
	}

	
	public String getImgUrl() {
		String img = getTitleImg();
		if (StringUtils.isBlank(img)) {
			// TODO ���ӵ�ͼƬĬ�ϵ���ʾͼƬ
			return "";
		} else {
			return getWebsite().getUploadUrlBuf().append(img).toString();
		}
	}

	
	public boolean isTitBold() {
		return getBold();
	}

	
	public String stit(int len) {
		String s = getShortTitle();
		if (StringUtils.isBlank(s)) {
			return getTitle();
		} else {
			return StrUtils.getCn(s, len);
		}
	}

	
	public String tit(int len) {
		String s = getTitle();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			return StrUtils.getCn(s, len);
		}
	}

	
	public String getTitCol() {
		String s = getTitleColor();
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	
	public String getCtgName() {
		return getChannel().getName();
	}

	
	public String getCtgUrl() {
		return getChannel().getUrl();
	}

	
	public String getWebName() {
		return getWebsite().getShortName();
	}

	
	public String getWebUrl() {
		return getWebsite().getWebUrl();
	}

	
	public String getDate(int style) {
		Date date = getReleaseDate();
		return ComUtils.formatDate(date, style);
	}

	private String content;
	private String rootReal;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Article() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Article(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Article(java.lang.Long id, com.jeecms.article.entity.ArtiCtg ctg,
			com.jeecms.cms.entity.CmsChannel channel,
			com.jeecms.core.entity.Website website,
			java.lang.Boolean hasTitleImg, java.lang.Boolean bold,
			java.lang.Boolean draft, java.lang.Boolean recommend,
			java.lang.Boolean check, java.lang.Boolean disabled,
			java.lang.Boolean reject) {

		super(id, ctg, channel, website, hasTitleImg, bold, draft, recommend,
				check, disabled, reject);
	}

	/* [CONSTRUCTOR MARKER END] */

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRootReal() {
		return rootReal;
	}

	public void setRootReal(String rootReal) {
		this.rootReal = rootReal;
	}
}