package com.linpeng.epdog.config;

import java.util.Properties;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.linpeng.epdog.controller.ExplainController;
import com.linpeng.epdog.controller.ExplainDetailController;
import com.linpeng.epdog.controller.LoginController;
import com.linpeng.epdog.controller.PlanController;
import com.linpeng.epdog.interceptor.AuthInterceptor;
import com.linpeng.epdog.model.Explain;
import com.linpeng.epdog.model.ExplainDetail;
import com.linpeng.epdog.model.Plan;
import com.linpeng.epdog.model.User;

/**
 * Basic config for Explain Plan Dog
 * 
 * @author linpeng
 *
 */
public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setBaseViewPath("/views");
		me.setViewType(ViewType.FREE_MARKER);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/plan", PlanController.class, "plan");
		me.add("/explain", ExplainController.class, "explain");
		me.add("/explainDetail", ExplainDetailController.class,
				"explain_detail");
		me.add("/login", LoginController.class, "login");
	}

	@Override
	public void configPlugin(Plugins me) {
		Properties properties = loadPropertyFile("classes/db.properties");
		C3p0Plugin cp = new C3p0Plugin(properties);
		me.add(cp);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.setDialect(new OracleDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		arp.addMapping("t_plan", Plan.class);
		arp.addMapping("t_explain", Explain.class);
		arp.addMapping("t_explain_detail", ExplainDetail.class);
		arp.addMapping("t_user", User.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new AuthInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {

	}

}
