package com.ntsky.bbs.web.webwork.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;
import com.opensymphony.xwork.util.OgnlValueStack;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.SAUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.service.AdminService;
import com.ntsky.bbs.util.WebworkUtil;

/**
 * URL合法性检测
 * 
 * @author ntsky
 * 
 */
public class ValidityInterceptor extends AroundInterceptor {

	private final static Logger logger = Logger.getLogger(ValidityInterceptor.class.getName());
	/**
	 * URL合法性检测
	 * 
	 * <ol>
	 * </ol>
	 */
	protected void before(ActionInvocation invocation) throws Exception {		
	}

	protected void after(ActionInvocation invocation, String result)
			throws Exception {
	}

	/**
	 * 如果用户没有登陆就跳转到登陆画面
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		
		before(invocation);
		if(logger.isDebugEnabled()){
			logger.debug("validity start......");
		}
		// 校验码
		String sig = WebworkUtil.getParameter("sig");
		String base64Data = WebworkUtil.getParameter("data");
		
		if(("".equals(sig)) || ("".equals(base64Data))){
			// 如果检验串sig和数据串base64Data不存在
			return "lawless";
		}
		
		/**
		 * 设置sig和data到Ognl
		 */
		OgnlValueStack ovStack = (OgnlValueStack)WebworkUtil.getRequest().getAttribute("webwork.valueStack");
		ovStack.setValue("sig",sig);
		ovStack.setValue("data",base64Data);
		
		if(logger.isDebugEnabled()){
			logger.debug("sig = " + sig);
			logger.debug("data = " + base64Data);
		}
		// 将base64的data转换成原始数据
		String data = SAUtil.decode(base64Data);
		
		if(logger.isDebugEnabled()){
			logger.info("权限数据的URL : " + data);
		}
		StringBuffer st = new StringBuffer();
		st.append("a");
		// 检测 URL 是否正常 
		if(SAUtil.checkSig(sig,base64Data)){
			
			//Map parMap = new HashMap();
			
			// 解析参数 (rp=2_2&id=3)
			String[] pars = data.split("&");
			for (int i = 0; i < pars.length; i++) {
				String[] pv = pars[i].split("=");
				if(!"rp".equals(pv[0])){
					// id对应值
					ovStack.setValue(pv[0],pv[1]);
				}
				else{
					// rp对应值
					//parMap.put(pv[0],pv[1]);
				}
			}
			
			// 判断角色权限情况
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			/*Object object = session.get(Symbols.SESSION_ADMIN);
			if (object == null) {
				return Action.LOGIN;
			}
			else{
				Admin admin = (Admin)object;
				// 0表示是管理员
				if(!"0".equals(admin.getRoles())){
					admin.getRoles();
				}
				//admin.getRoles();
				
				// 检测角色是否包含该权限点
				
				//没有就退出到没有noPermisson页面
			}
			
			ovStack.setValue("rp", urls[0].substring(urls[0].indexOf("=")+1));
			if(urls.length>1){
				ovStack.setValue("id", urls[1].substring(urls[1].indexOf("=")+1));
			}
			*/
			if(logger.isDebugEnabled()){
				logger.debug("validity end, result success......");
			}
			String result = invocation.invoke();
			after(invocation, result);
			return result;
		}
		else{
			if(logger.isDebugEnabled()){
				logger.debug("validity end, result lawless......");
			}
			// URL 非法
			return "lawless";
		}

	}
}
