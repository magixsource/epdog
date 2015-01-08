package com.linpeng.epdog.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Plan validator after save plan on create form
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
