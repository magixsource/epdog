package com.linpeng.epdog.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.linpeng.epdog.model.Explain;

/**
 * 负责执行Plan并返回结果
 * 
 * @author linpeng
 *
 */
public class ExplainController extends Controller {
	/**
	 * 展示分析报告
	 */
	public void index() {

	}
	
	/**
	 * 查询并展示数据
	 */
	public void query() {
		String pid = getPara("pid");
		int pageNumber = getParaToInt("page", 1);
		int pageSize = getParaToInt("pagesize", 10);

		Page<Explain> page = Explain.dao.paginate(pageNumber, pageSize,
				"select *", " from t_explain where plan_id=?", pid);

		setAttr("pid", pid);
		setAttr("explains", page.getList());
		setAttr("page", page.getPageNumber());
		setAttr("pagesize", page.getPageSize());
		setAttr("total", page.getTotalRow());
		setAttr("totalpage", page.getTotalPage());
		render("list.html");
	}
}
