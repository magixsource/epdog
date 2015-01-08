package com.linpeng.epdog.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.linpeng.epdog.model.ExplainDetail;

/**
 * ִ�������ϸ���
 * 
 * @author linpeng
 *
 */
public class ExplainDetailController extends Controller {

	public void query() {
		String explainId = getPara("explainid");

		List<ExplainDetail> explainDetails = ExplainDetail.dao
				.find("select * from t_explain_detail where EXPLAIN_ID=? order by EXPLAIN_IDX",
						explainId);
		setAttr("explainid", explainId);
		setAttr("explainDetails", explainDetails);
		render("report.html");
	}

}
