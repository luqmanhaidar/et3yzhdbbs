package com.jeecms.auxiliary.manager;

import com.jeecms.auxiliary.entity.Msg;
import com.jeecms.core.JeeCoreManager;
import com.ponyjava.common.page.Pagination;

public interface MsgMng extends JeeCoreManager<Msg> {
	public Pagination getForTag(Long webId, Long ctgId, boolean isRecommand,
			boolean isCheck, int orderBy, boolean isPage, int firstResult,
			int pageNo, int pageSize);
}