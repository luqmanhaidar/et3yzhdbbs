package com.ntsky.bbs.util.page;

import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.framework.util.StringUtil;
import com.opensymphony.xwork.ActionContext;

public class PermissonUtil {
	private Admin admin=null;
	public PermissonUtil(){}
	public PermissonUtil(Object object){
		admin=(Admin)object;
	}
	public boolean isPermisson(String point){	    
	    	if( admin==null ){
	    		return false;
	    	}
	    	else{
	    		if("admin".equals(admin.getUsername())&& ("0".equals(admin.getPermissions()))) return true;	    		
	    		return StringUtil.isArrayContainString(admin.getPermissions().split(","), point);
	    	}
	    }
}
