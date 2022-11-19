package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pRechargeOnline;


/**
 * Description：<br> 
 * 线上充值表（CaffP2pRechargeOnline） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRechargeOnlineMapper {
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  线下充值表（CaffP2pRechargeOnline）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pRechargeOnline> findCaffP2pRechargeOnlineList(){
		List<CaffP2pRechargeOnline> cs = new ArrayList<CaffP2pRechargeOnline>();
		CaffP2pRechargeOnline c;
		StringBuilder sql = new StringBuilder("select caffP2pRechargeOnlineId, createdDateLong, version, "
				+ "userId, amount, payConfigId, stateInt, paySystemTypeInt from CaffP2pRechargeOnline");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pRechargeOnline();
				c.setCaffp2prechargeonlineid(rs.getLong("caffP2pRechargeOnlineId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAmount(rs.getBigDecimal("amount"));
				c.setPayconfigid(rs.getLong("payConfigId"));
				c.setStateint(rs.getInt("stateInt"));
				c.setPaysystemtypeint(rs.getInt("paySystemTypeInt"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	线上充值表（CaffP2pRechargeOnline）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}