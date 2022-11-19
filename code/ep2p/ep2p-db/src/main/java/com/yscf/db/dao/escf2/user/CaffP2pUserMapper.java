package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pUser;

/**
 * Description：<br> 
 * 系统用户表（CaffP2pUser） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserMapper{
	/**
	 * Description：<br> 
	 * 获取escf2.0数据	系统用户表（CaffP2pUser）
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pUser> findCaffp2pUserList(){
		List<CaffP2pUser> users = new ArrayList<CaffP2pUser>();
		CaffP2pUser user;
		StringBuilder sql = new StringBuilder("select caffP2pUserId, bitState, bitState2, bitState3, exkey, sourceInt, loginErrorCount,");
		sql.append("createdDateLong, version, email, phone, name, password, tradePassword from CaffP2pUser");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				user = new CaffP2pUser();
				user.setCaffp2puserid(rs.getLong("caffp2puserid"));
				user.setBitstate(rs.getLong("bitState"));
				user.setBitstate2(rs.getLong("bitState2"));
				user.setBitstate3(rs.getLong("bitState3"));
				user.setExkey(rs.getLong("exkey"));
				user.setSourceint(rs.getInt("sourceInt"));
				user.setLoginerrorcount(rs.getInt("loginErrorCount"));
				user.setCreateddatelong(rs.getLong("createdDateLong"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setTradepassword(rs.getString("tradePassword"));
				user.setName(rs.getString("name"));
				user.setVersion(rs.getLong("version"));
				users.add(user);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	系统用户表（CaffP2pUser）发生错误-------------------------");
			e.printStackTrace();
		}
		return users;
	}
}