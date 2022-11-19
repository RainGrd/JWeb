package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pReceiptPlan;

/**
 * Description：<br> 
 * 回款资金表(caffp2paccountreceipt) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pReceiptPlanMapper{
	
	/**
	 * Description：<br> 
	 * 
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pReceiptPlan> findCaffP2pReceiptPlanList(){
		List<CaffP2pReceiptPlan> cs = new ArrayList<CaffP2pReceiptPlan>();
		CaffP2pReceiptPlan c;
		StringBuilder sql = new StringBuilder("select caffP2pReceiptPlanId, createdDateLong, version, "
				+ "userId, borrowId, borrowTypeInt, capital, expireDateActualLong, expireDateLong, "
				+ "interest, interestCalculated, interestCalculationTypeInt, lateFee, lateFeeCalculateDateLong, "
				+ "payAmount, payDateLong, planIndex, reference1, reference2, reference3, repaidAmount, "
				+ "repaidDateLong, stateInt, bidId, bitState, borrowerId, caffP2pRepaymentPlanId, "
				+ "originalUserId, payRate, payTypeInt from CaffP2pReceiptPlan");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pReceiptPlan();
				c.setCaffp2preceiptplanid(rs.getLong("caffP2pReceiptPlanId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setBorrowid(rs.getLong("borrowId"));
				c.setBorrowtypeint(rs.getInt("borrowTypeInt"));
				c.setCapital(rs.getBigDecimal("capital"));
				c.setExpiredateactuallong(rs.getLong("expireDateActualLong"));
				c.setExpiredatelong(rs.getLong("expireDateLong"));
				c.setInterest(rs.getBigDecimal("interest"));
				c.setInterestcalculated(rs.getBigDecimal("interestCalculated"));
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
				c.setBidid(rs.getLong("bidId"));
				c.setBitstate(rs.getLong("bitState"));
				c.setBorrowerid(rs.getLong("borrowerId"));
				c.setCaffp2prepaymentplanid(rs.getLong("caffP2pRepaymentPlanId"));
				c.setOriginaluserid(rs.getLong("originalUserId"));
				c.setPayrate(rs.getBigDecimal("payRate"));
				c.setPaytypeint(rs.getInt("payTypeInt"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pReceiptPlan）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}