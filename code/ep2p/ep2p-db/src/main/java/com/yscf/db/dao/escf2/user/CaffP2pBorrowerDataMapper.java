package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pBorrowerData;

/**
 * Description：<br> 
 * 借款者表（CaffP2pBorrowerData） 数据据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowerDataMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据	借款者表（CaffP2pBorrowerData）
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBorrowerData> findCaffP2pBorrowerDataList(long userId){
		List<CaffP2pBorrowerData> bds = new ArrayList<CaffP2pBorrowerData>();
		CaffP2pBorrowerData bd;
		StringBuilder sql = new StringBuilder("select caffP2pBorrowerDataId, createdDateLong, version,")
		.append(" userId, availableCreditAmount, borrowerTypeInt, maxCreditAmount from CaffP2pBorrowerData");
		if(-1!=userId){
			sql.append(" where userId = ").append(userId);
		}
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				bd = new CaffP2pBorrowerData();
				bd.setCaffp2pborrowerdataid(rs.getLong("caffP2pBorrowerDataId"));
				bd.setCreateddatelong(rs.getLong("createdDateLong"));
				bd.setVersion(rs.getLong("version"));
				bd.setUserid(rs.getLong("userId"));
				bd.setAvailablecreditamount(rs.getBigDecimal("availableCreditAmount"));
				bd.setBorrowertypeint(rs.getInt("borrowerTypeInt"));
				bd.setMaxcreditamount(rs.getBigDecimal("maxCreditAmount"));
				bds.add(bd);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	借款者表（CaffP2pBorrowerData）发生错误-------------------------");
			e.printStackTrace();
		}
		return bds;
	}
}