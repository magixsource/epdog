package com.linpeng.epdog.controller;

import java.util.List;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linpeng.epdog.core.PlanExecuter;
import com.linpeng.epdog.model.Explain;
import com.linpeng.epdog.model.ExplainDetail;
import com.linpeng.epdog.model.Plan;
import com.linpeng.epdog.validator.PlanFormValidator;

/**
 * Explain-plan Plan controller
 * 
 * @author linpeng
 *
 */
public class PlanController extends Controller {

	/**
	 * Show all plan in system
	 */
	public void index() {
		// page
		int pageNumber = getParaToInt("page", 1);
		int pageSize = getParaToInt("pagesize", 10);
		Page<Plan> page = Plan.dao.paginate(pageNumber, pageSize, "select *",
				" from t_plan");
		setAttr("plans", page.getList());
		setAttr("totalpage", page.getTotalPage());
		setAttr("page", page.getPageNumber());
		setAttr("pagesize", page.getPageSize());

		render("index.html");
	}

	/**
	 * Create a new Plan
	 */
	public void create() {

	}

	/**
	 * Update plan information
	 */
	public void update() {
		// TODO unsupported
	}

	/**
	 * View plan
	 */
	public void view() {
		String id = getPara("id");
		Plan plan = Plan.dao.findById(id);
		setAttr("plan", plan);
	}

	/**
	 * Stone plan into database
	 */
	@Before(PlanFormValidator.class)
	public void save() {
		String id = getPara("id", null);
		String name = getPara("name");
		String content = getPara("content");
		if (null == id) {
			id = UUID.randomUUID().toString();
			new Plan().set("id", id).set("name", name).set("content", content)
					.save();
		} else {
			Plan.dao.findById(id).set("name", name).set("content", content)
					.update();
		}

		redirect("/plan");
	}

	/**
	 * Clear Plan and Explain of Plan and Detail of Explain
	 */
	@Before(Tx.class)
	public void delete() {
		String id = getPara("id");
		// delete explain detail
		String findExplainDetailSql = "select t.* from t_explain_detail t,t_explain t1 where t1.id=t.explain_id and t1.plan_id=?";
		List<ExplainDetail> details = ExplainDetail.dao.find(
				findExplainDetailSql, id);
		for (ExplainDetail explainDetail : details) {
			explainDetail.delete();
		}

		// delete explain
		String findExplainSql = "select * from t_explain where PLAN_ID = ?";
		List<Explain> explains = Explain.dao.find(findExplainSql, id);
		for (Explain explain : explains) {
			explain.delete();
		}
		// delete plan
		Plan.dao.deleteById(id);
		redirect("/plan");
	}

	/**
	 * Try to explain SQL and stone explain result into database
	 */
	public void run() {
		String id = getPara("id");
		Plan plan = Plan.dao.findById(id);

		PlanExecuter.run(plan);
		redirect("/plan");
	}

}
