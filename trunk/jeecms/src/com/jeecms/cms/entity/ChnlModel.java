package com.jeecms.cms.entity;

import static com.jeecms.core.Constants.FILE_SPT;
import static com.jeecms.core.Constants.SPT;
import static com.jeecms.core.Constants.TPL_SUFFIX;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.base.BaseChnlModel;

public class ChnlModel extends BaseChnlModel {
	/**
	 * 获得模板列表
	 * 
	 * @param root
	 * @param prefix
	 * @return
	 */
	private List<String> tplList(String root, final String prefix) {
		String solution = getConfig().getSolution(getSysType());
		StringBuilder relPath = new StringBuilder();
		relPath.append(SPT).append(getSysType()).append(SPT).append(solution);
		String path = getWebsite().getTplRootReal(root).append(relPath)
				.toString().replace(SPT, FILE_SPT);
		File file = new File(path);
		String[] fns = file.list(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				if (name.startsWith(prefix)) {
					return true;
				} else {
					return false;
				}
			}
		});
		List<String> result = new ArrayList<String>();
		relPath.append(SPT);
		if (fns != null) {
			for (String name : fns) {
				result.add(relPath + name);
			}
		}
		return result;
	}

	/**
	 * 栏目模板列表
	 * 
	 * @param root
	 * @return
	 */
	public List<String> tplChannlList(String root) {
		String prefix = getTplPrefixChannel();
		if (StringUtils.isBlank(prefix)) {
			return new ArrayList<String>();
		} else {
			return tplList(root, prefix);
		}
	}

	/**
	 * 内容模板列表
	 * 
	 * @param root
	 * @return
	 */
	public List<String> tplContentList(String root) {
		String prefix = getTplPrefixContent();
		if (StringUtils.isBlank(prefix)) {
			return new ArrayList<String>();
		} else {
			return tplList(root, prefix);
		}
	}

	/**
	 * 获得默认解决方案模板路径。/WEB-INF/user_base/RES_PATH/template/sysType/default/
	 * 
	 * @return
	 */
	public StringBuilder getTplDef() {
		StringBuilder sb = getWebsite().getTplRoot();
		sb.append(SPT).append(getSysType()).append(SPT).append(
				getWebsite().getSolutions().get(getSysType())).append(SPT);
		return sb;
	}

	/**
	 * 默认栏目页模板
	 * 
	 * @return
	 */
	public String defIndexTpl() {
		return getTplDef().append(getTplPrefixChannel()).append(TPL_SUFFIX)
				.toString();
	}

	/**
	 * 默认内容页模板
	 * 
	 * @return
	 */
	public String defContentTpl() {
		return getTplDef().append(getTplPrefixContent()).append(TPL_SUFFIX)
				.toString();
	}

	/**
	 * 表单名称为key，item为value。用于控制界面
	 * 
	 * @return
	 */
	public Map<String, ChnlModelItem> getDiplayItemMap() {
		Set<ChnlModelItem> items = getItems();
		if (items != null) {
			Map<String, ChnlModelItem> itemMap = new HashMap<String, ChnlModelItem>();
			for (ChnlModelItem it : items) {
				if (it.getChecked()) {
					itemMap.put(it.getName(), it);
				}
			}
			return itemMap;
		} else {
			return null;
		}
	}

	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ChnlModel() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ChnlModel(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ChnlModel(java.lang.Long id, com.jeecms.core.entity.Website website,
			com.jeecms.cms.entity.CmsConfig config, java.lang.String sysType,
			java.lang.Boolean hasChild) {

		super(id, website, config, sysType, hasChild);
	}

	/* [CONSTRUCTOR MARKER END] */

}