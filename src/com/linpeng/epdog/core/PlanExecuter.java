package com.linpeng.epdog.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linpeng.epdog.model.Explain;
import com.linpeng.epdog.model.ExplainDetail;
import com.linpeng.epdog.model.Plan;

/**
 * 负责执行分析计划，并将结果持久化，方便管理员及用户查看 <br>
 * 后面可能需要自动执行功能和按时汇总报告发送邮件功能
 * 
 * @author linpeng
 *
 */
public class PlanExecuter {

	@Before(Tx.class)
	public static void run(Plan plan) {
		String planId = plan.getStr("id");
		String content = plan.getStr("content");
		// explain plan execute
		try {

			String explainId = UUID.randomUUID().toString();
			new Explain().set("ID", explainId).set("PLAN_ID", planId).save();

			Connection conn = DbKit.getConfig().getConnection();
			String statementId = SID.id(10);
			String sql = "explain plan set STATEMENT_ID ='" + statementId
					+ "' for " + content;
			Statement statement = conn.createStatement();
			statement.execute(sql);

			String sql2 = "select * from table(dbms_xplan.DISPLAY('','"
					+ statementId + "'))";
			statement.executeQuery(sql2);
			ResultSet resultSet = statement.getResultSet();
			String record = "";
			int index = 0;
			while (resultSet.next()) {
				index++;
				if (index < 6) {
					continue;
				}

				record = resultSet.getString(1);

				if (record.startsWith("|")) {

					String explainDetailId = UUID.randomUUID().toString();

					String[] array = record.split("\\|");
					String idx = array[1];
					idx = idx.replaceAll("\\*", "").trim();
					Integer integer = Integer.valueOf(idx);
					String operation = array[2];
					String name = array[3];
					String rows = array[4];
					String bytes = array[5];
					String cost = array[6];
					String time = array[7];
					new ExplainDetail().set("id", explainDetailId)
							.set("EXPLAIN_ID", explainId)
							.set("EXPLAIN_IDX", integer)
							.set("EXPLAIN_OPERATION", operation)
							.set("EXPLAIN_NAME", name)
							.set("EXPLAIN_ROWS", rows)
							.set("EXPLAIN_BYTES", bytes)
							.set("EXPLAIN_COST", cost)
							.set("EXPLAIN_TIME", time).save();
				}

				if (record.startsWith("---")) {
					break;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
