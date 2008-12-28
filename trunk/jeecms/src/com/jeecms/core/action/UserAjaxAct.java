package com.jeecms.core.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.core.JeeCoreAjaxAction;
import com.jeecms.core.entity.User;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("core.userAjaxAct")
public class UserAjaxAct extends JeeCoreAjaxAction {
	public String doUpdatePassword() {
		User user = getUser();
		boolean isSucess = userMng.updatePassword(user, oldPwd, newPwd);
		if (isSucess) {
			jsonRoot.put("success", isSucess);
			jsonRoot.put("msg", "修改成功！");
		} else {
			jsonRoot.put("success", isSucess);
			jsonRoot.put("msg", "原密码错误！");
		}
		return SUCCESS;
	}

	public String doCheckUserName() {
		User u = userMng.getUserByLoginName(username);
		if (u == null) {
			jsonRoot.put("success", true);
		} else {
			jsonRoot.put("success", false);
		}
		return SUCCESS;
	}

	private String oldPwd;
	private String newPwd;
	private String username;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
