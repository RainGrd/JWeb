package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pBorrowBiddingInfo;

/**
 * Description：<br> 
 * 投标信息表（CaffP2pBorrowBiddingInfo） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pBorrowBiddingInfoMapper{
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  投标信息表（CaffP2pBorrowBiddingInfo）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBorrowBiddingInfo> findCaffP2pBorrowBiddingInfoList(){
		List<CaffP2pBorrowBiddingInfo> cs = new ArrayList<CaffP2pBorrowBiddingInfo>();
		CaffP2pBorrowBiddingInfo c;
		StringBuilder sql = new StringBuilder("select caffP2pBorrowBiddingInfoId, createdDateLong, version,"
				+ " bidCount, borrowAmount, borrowId, currentAmount from CaffP2pBorrowBiddingInfo");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pBorrowBiddingInfo();
				c.setCaffp2pborrowbiddinginfoid(rs.getLong("caffP2pBorrowBiddingInfoId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setBidcount(rs.getInt("bidCount"));
				c.setBorrowamount(rs.getBigDecimal("borrowAmount"));
				c.setBorrowid(rs.getLong("borrowId"));
				c.setCurrentamount(rs.getBigDecimal("currentAmount"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	投标信息表 (CaffP2pBorrowBiddingInfo)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}