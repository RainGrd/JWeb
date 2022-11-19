package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pBorrow;

/**
 * Description：<br> 
 * 借款信息表(CaffP2pBorrow) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据 借款信息表(CaffP2pBorrow)
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBorrow> findCaffP2pBorrowList(){
		List<CaffP2pBorrow> cs = new ArrayList<CaffP2pBorrow>();
		CaffP2pBorrow c;
		StringBuilder sql = new StringBuilder("select caffP2pBorrowId, createdDateLong, version, userId, "
				+ "amount, successAmount, bidCount, bidDays, borrowCount, bidExpireDateLong, bidTimeLong, "
				+ "successDateLong, bondingCompanyId, borrowStateInt, borrowTypeInt, repayGuaranteeTypeInt, "
				+ "interestCalculationTypeInt, maxBidAmount, minBidAmount, password, purposeTypeInt, rate, "
				+ "rewardRate, rootBorrowId, createUserId, timeCount, title, reference1, reference2, reference3, "
				+ "bitState, cdregistertime, cdregistertimeend, cdbidamount from CaffP2pBorrow");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pBorrow();
				c.setCaffp2pborrowid(rs.getLong("caffP2pBorrowId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAmount(rs.getBigDecimal("amount"));
				c.setSuccessamount(rs.getBigDecimal("successAmount"));
				c.setBidcount(rs.getInt("bidCount"));
				c.setBiddays(rs.getInt("bidDays"));
				c.setBorrowcount(rs.getInt("borrowCount"));
				c.setBidexpiredatelong(rs.getLong("bidExpireDateLong"));
				c.setBidtimelong(rs.getLong("bidTimeLong"));
				c.setSuccessdatelong(rs.getLong("successDateLong"));
				c.setBondingcompanyid(rs.getLong("bondingCompanyId"));
				c.setBorrowstateint(rs.getInt("borrowStateInt"));
				c.setBorrowtypeint(rs.getInt("borrowTypeInt"));
				c.setRepayguaranteetypeint(rs.getInt("repayGuaranteeTypeInt"));
				c.setInterestcalculationtypeint(rs.getInt("interestCalculationTypeInt"));
				c.setMaxbidamount(rs.getBigDecimal("maxBidAmount"));
				c.setMinbidamount(rs.getBigDecimal("minBidAmount"));
				c.setPassword(rs.getString("password"));
				c.setPurposetypeint(rs.getInt("purposeTypeInt"));
				c.setRate(rs.getBigDecimal("rate"));
				c.setRewardrate(rs.getBigDecimal("rewardRate"));
				c.setRootborrowid(rs.getLong("rootBorrowId"));
				c.setCreateuserid(rs.getLong("createUserId"));
				c.setTimecount(rs.getInt("timeCount"));
				c.setTitle(rs.getString("title"));
				c.setReference1(rs.getLong("reference1"));
				c.setReference2(rs.getLong("reference2"));
				c.setReference3(rs.getLong("reference3"));
				c.setBitstate(rs.getLong("bitState"));
				c.setCdregistertime(rs.getString("cdregistertime"));
				c.setCdregistertimeend(rs.getString("cdregistertimeend"));
				c.setCdbidamount(rs.getString("cdbidamount"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	借款信息表(CaffP2pBorrow) 发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}