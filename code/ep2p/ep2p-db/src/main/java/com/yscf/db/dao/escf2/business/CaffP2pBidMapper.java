package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pBid;


/**
 * Description：<br> 
 * 投标记录表(CaffP2pBid) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBidMapper {
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  投标记录表(CaffP2pBid)
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBid> findCaffP2pBidList(){
		List<CaffP2pBid> cs = new ArrayList<CaffP2pBid>();
		CaffP2pBid c;
		StringBuilder sql = new StringBuilder("select caffP2pBidId, createdDateLong, lastestBidTimeLong, "
				+ "successDateLong, version, userId, amount, autoBidAmount, availableAmount, bidCount, "
				+ "bidStateInt, bitState, borrowId from CaffP2pBid");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pBid();
				c.setCaffp2pbidid(rs.getLong("caffP2pBidId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setLastestbidtimelong(rs.getLong("lastestBidTimeLong"));
				c.setSuccessdatelong(rs.getLong("successDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAmount(rs.getBigDecimal("amount"));
				c.setAutobidamount(rs.getBigDecimal("autoBidAmount"));
				c.setAvailableamount(rs.getBigDecimal("availableAmount"));
				c.setBidcount(rs.getInt("bidCount"));
				c.setBidstateint(rs.getInt("bidStateInt"));
				c.setBitstate(rs.getLong("bitState"));
				c.setBorrowid(rs.getLong("borrowId"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	投标记录表(CaffP2pBid)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}