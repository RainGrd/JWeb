package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pRechargeOffline;


/**
 * Description：<br> 
 * 线下充值表（CaffP2pRechargeOffline） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRechargeOfflineMapper {
	
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  线下充值表（CaffP2pRechargeOffline）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pRechargeOffline> findCaffP2pRechargeOfflineList(){
		List<CaffP2pRechargeOffline> cs = new ArrayList<CaffP2pRechargeOffline>();
		CaffP2pRechargeOffline c;
		StringBuilder sql = new StringBuilder("select caffP2pRechargeOfflineId, createdDateLong, version, "
				+ "userId, amount, bankId, stateInt, tradeCode, tradeDateLong, bitState from CaffP2pRechargeOffline");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pRechargeOffline();
				c.setCaffp2prechargeofflineid(rs.getLong("caffP2pRechargeOfflineId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAmount(rs.getBigDecimal("amount"));
				c.setBankid(rs.getLong("bankId"));
				c.setStateint(rs.getInt("stateInt"));
				c.setTradecode(rs.getString("tradeCode"));
				c.setTradedatelong(rs.getLong("tradeDateLong"));
				c.setBitstate(rs.getLong("bitState"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pRechargeOffline）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}