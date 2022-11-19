package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pRepaymentPlan;


/**
 * Description：<br> 
 * 还款计划表（CaffP2pRepaymentPlan）数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRepaymentPlanMapper {
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  资金流水表（CaffP2pRepaymentPlan）
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pRepaymentPlan> findCaffP2pRepaymentPlanList(){
		List<CaffP2pRepaymentPlan> cs = new ArrayList<CaffP2pRepaymentPlan>();
		CaffP2pRepaymentPlan c;
		StringBuilder sql = new StringBuilder("select caffP2pRepaymentPlanId, createdDateLong, version,"
				+ " userId, borrowId, borrowTypeInt, capital, expireDateActualLong, expireDateLong, interest, "
				+ "interestCalculationTypeInt, lateFee, lateFeeCalculateDateLong, payAmount, payDateLong, "
				+ "planIndex, reference1, reference2, reference3, repaidAmount, repaidDateLong, stateInt, "
				+ "bitState from CaffP2pRepaymentPlan");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pRepaymentPlan();
				c.setCaffp2prepaymentplanid(rs.getLong("caffP2pRepaymentPlanId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setBorrowid(rs.getLong("borrowId"));
				c.setBorrowtypeint(rs.getInt("borrowTypeInt"));
				c.setCapital(rs.getBigDecimal("capital"));
				c.setExpiredateactuallong(rs.getLong("expireDateActualLong"));
				c.setExpiredatelong(rs.getLong("expireDateLong"));
				c.setInterest(rs.getBigDecimal("interest"));
				c.setInterestcalculationtypeint(rs.getInt("interestCalculationTypeInt"));
				c.setLatefee(rs.getBigDecimal("lateFee"));
				c.setLatefeecalculatedatelong(rs.getLong("lateFeeCalculateDateLong"));
				c.setPayamount(rs.getBigDecimal("payAmount"));
				c.setPaydatelong(rs.getLong("payDateLong"));
				c.setPlanindex(rs.getInt("planIndex"));
				c.setReference1(rs.getLong("reference1"));
				c.setReference2(rs.getLong("reference2"));
				c.setReference3(rs.getLong("reference3"));
				c.setRepaidamount(rs.getBigDecimal("repaidAmount"));
				c.setRepaiddatelong(rs.getLong("repaidDateLong"));
				c.setStateint(rs.getInt("stateInt"));
				c.setBitstate(rs.getLong("bitState"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pRepaymentPlan）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}