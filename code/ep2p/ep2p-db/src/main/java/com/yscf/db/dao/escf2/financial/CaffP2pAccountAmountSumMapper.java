package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAccountAmountSum;

/**
 * Description：<br> 
 * 汇总用户的金额数据（CaffP2pAccountAmountSum） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountAmountSumMapper{
	
	public static List<CaffP2pAccountAmountSum> findCaffP2pAccountAmountSumList(){
		List<CaffP2pAccountAmountSum> cs = new ArrayList<CaffP2pAccountAmountSum>();
		CaffP2pAccountAmountSum c;
		StringBuilder sql = new StringBuilder("select caffP2pAccountAmountSumId, createdDateLong,"
				+ " version, userId, availableAmount, frozenAmount, bidReward, experienceAvailabeAmount, "
				+ "experienceFrozenAmount, hongbaoAmount, interestDone, interestFee, interestPending, "
				+ "latestReceipt, latestReceiptDateLong, receiptPendingAmount, repayPendingAmount")
		.append(" from CaffP2pAccountAmountSum");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pAccountAmountSum();
				c.setCaffp2paccountamountsumid(rs.getLong("CaffP2pAccountAmountSumId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAvailableamount(rs.getBigDecimal("availableAmount"));
				c.setFrozenamount(rs.getBigDecimal("frozenAmount"));
				c.setBidreward(rs.getBigDecimal("bidReward"));
				c.setExperienceavailabeamount(rs.getBigDecimal("experienceAvailabeAmount"));
				c.setExperiencefrozenamount(rs.getBigDecimal("experienceFrozenAmount"));
				c.setHongbaoamount(rs.getBigDecimal("hongbaoAmount"));
				c.setInterestdone(rs.getBigDecimal("interestDone"));
				c.setInterestfee(rs.getBigDecimal("interestFee"));
				c.setInterestpending(rs.getBigDecimal("interestPending"));
				c.setLatestreceipt(rs.getBigDecimal("latestReceipt"));
				c.setLatestreceiptdatelong(rs.getLong("latestReceiptDateLong"));
				c.setReceiptpendingamount(rs.getBigDecimal("receiptPendingAmount"));
				c.setRepaypendingamount(rs.getBigDecimal("repayPendingAmount"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pAccountAmountSum）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}