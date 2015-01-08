package com.linpeng.epdog.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.linpeng.epdog.core.EncrypMD5;
import com.linpeng.epdog.model.User;
import com.linpeng.epdog.validator.LoginValidator;

/**
 * Explain-plan dog system login & logout controller
 * 
 * @author linpeng
 *
 */
@ClearInterceptor
public class LoginController extends Controller {

	@Before(LoginValidator.class)
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		User user = User.dao.findFirst(
				"select * from t_user where username =? and password = ?",
				username, EncrypMD5.eccryptString(password));
		if (null != user) {
			setSessionAttr("loginUser", user);
			redirect("/plan");
		} else {
			setAttr("loginErrorMsg", "username or password error!");
		}

	}

	public void logout() {
		setAttr("loginMsg", "You have logout success !");
		render("login");
	}
}
