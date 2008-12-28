package com.jeecms.cms.action.login;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeecms.core.JeeCoreAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("cms.adminConsoleAct")
public class AdminConsoleAct extends JeeCoreAction {
	public String doIndex() {
		// @ TODO ������Ա�Ƿ����ڸ�ϵͳ��
		return INDEX;
	}

	public String doMain() {
		return "main";
	}

	public String doLeft() {
		return LEFT;
	}

	public String doRight() {
		return RIGHT;
	}
}
