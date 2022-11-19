package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pUserIntegral;

/**
 * Description：<br> 
 * 积分概况表（CaffP2pUserIntegral） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserIntegralMapper {
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据	积分概况表（CaffP2pUserIntegral）
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pUserIntegral> findCaffP2pUserIntegralList(long userId){
		List<CaffP2pUserIntegral> uis = new ArrayList<CaffP2pUserIntegral>();
		CaffP2pUserIntegral ui;
		StringBuilder sql = new StringBuilder("select caffP2pUserIntegralId, createdDateLong, version, userId, borrowIntegral, borrowIntegralChecked,")
		.append(" giftIntegral, giftIntegralChecked, investIntegral, investIntegralChecked from CaffP2pUserIntegral");
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
				ui = new CaffP2pUserIntegral();
				ui.setCaffp2puserintegralid(rs.getLong("caffP2pUserIntegralId"));
				ui.setCreateddatelong(rs.getLong("createdDateLong"));
				ui.setVersion(rs.getLong("version"));
				ui.setUserid(rs.getLong("userId"));
				ui.setBorrowintegral(rs.getInt("borrowIntegral"));
				ui.setBorrowintegralchecked(rs.getInt("borrowIntegralChecked"));
				ui.setGiftintegral(rs.getInt("giftIntegral"));
				ui.setGiftintegralchecked(rs.getInt("giftIntegralChecked"));
				ui.setInvestintegral(rs.getInt("investIntegral"));
				ui.setInvestintegralchecked(rs.getInt("investIntegralChecked"));
				uis.add(ui);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	积分概况表（CaffP2pUserIntegral）发生错误-------------------------");
			e.printStackTrace();
		}
		return uis;
	}
    
}