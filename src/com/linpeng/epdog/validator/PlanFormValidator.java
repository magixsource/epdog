package com.linpeng.epdog.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 创建计划表单验证器
 * 
 * @author linpeng
 *
 */
public class PlanFormValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("name", "nameMsg", "please enter name");
		validateRequiredString("content", "contentMsg", "please enter content");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("id");
		c.render("create.html");
	}

}
