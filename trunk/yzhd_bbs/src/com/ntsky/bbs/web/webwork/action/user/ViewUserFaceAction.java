package com.ntsky.bbs.web.webwork.action.user;

import java.util.List;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.service.CommonService;
import com.ntsky.bbs.service.UserFaceService;

/**
 * 列表用户头像
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public class ViewUserFaceAction extends UserActionSupport {
	
	/**
	 * 头像
	 */
	private List faceTypes;
	public List getFaceTypes(){
		return faceTypes;
	}
	
	private UserFaceService userFaceService;
	
	public void setUserFaceService(UserFaceService userFaceService){
		this.userFaceService = userFaceService;
	}
	
	private CommonService commonService;
	public void setCommonService(CommonService commonService){
		this.commonService = commonService;
	}
	
	
	
	/**
	 * 列表用户头像
	 */
	public String execute() throws Exception {
		
		//faceTypes = userFaceService.getFaces();
		faceTypes = commonService.getAllFaces();
		/*Set set = faceTypes.entrySet();
        Iterator iterator = set.iterator();
        Map.Entry entry = null;
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            logger.info("key = " + entry.getKey());
            logger.info("value = " + entry.getValue());
        }*/
		
		if(logger.isInfoEnabled()){
			logger.info("取得全部用户头像.....");
		}
		return SUCCESS;
		
	}
		
}
