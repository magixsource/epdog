package com.linpeng.epdog.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.linpeng.epdog.model.User;

/**
 * Authentic or not
 * 
 * @author linpeng
 *
 */
public class AuthInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		User loginUser = controller.getSessionAttr("loginUser");
		if (loginUser != null) {
			ai.invoke();
		} else {
			controller.redirect("/login/login");
		}
	}

}
