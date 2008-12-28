package com.jeecms.cms.action.login;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.core.JeeCoreAction;
import com.jeecms.core.entity.Admin;
import com.jeecms.core.entity.User;
import com.jeecms.core.manager.FunctionMng;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.ponyjava.common.struts2.ContextPvd;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("cms.adminLoginAct")
public class AdminLoginAct extends JeeCoreAction {
	public String doLoginInput() {
		return "loginInput";
	}

	public String doLogin() {
		boolean isHuman = imageCaptchaService.validateResponseForID(contextPvd
				.getSessionId(false), checkCode);
		if (!isHuman) {
			addActionError("验证码错误！");
			return doLoginInput();
		}
		User user = userMng.authenticate(loginName, password);
		if (user == null) {
			addActionError("用户名不存在或密码错误！");
			return doLoginInput();
		}
		Admin admin = adminMng.getAdminByUserId(getWebId(), user.getId());
		if (admin == null) {
			addActionError("您没有本站的管理权限！");
			return doLoginInput();
		}
		// 清除以前登录信息
		contextPvd.logout();
		// 保存当前登录信息
		contextPvd.setSessionAttr(User.USER_KEY, user.getId());
		contextPvd.setSessionAttr(Admin.ADMIN_KEY, admin.getId());
		userMng.updateLoginInfo(user);
		// 将权限集放入session
		Set<String> fiSet = functionMng.getFunctionItems(admin.getId());
		contextPvd.setSessionAttr(Admin.RIGHTS_KEY, fiSet);
		return SUCCESS;
	}

	public String doLogout() {
		contextPvd.logout();
		return "logout";
	}

	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private ContextPvd contextPvd;
	@Autowired
	private FunctionMng functionMng;
	private String loginName;
	private String password;
	private String checkCode;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
