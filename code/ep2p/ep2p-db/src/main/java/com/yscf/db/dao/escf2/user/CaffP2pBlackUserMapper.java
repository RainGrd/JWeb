package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pBlackUser;

/**
 * Description：<br> 
 * 黑名单用户表（CaffP2pBlackUser）
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pBlackUserMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  获取黑名单客户(CaffP2pBlackUser)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pBlackUser> findCaffP2pBlackUserList(long userId){
		List<CaffP2pBlackUser> cbus = new ArrayList<CaffP2pBlackUser>();
		CaffP2pBlackUser cbu;
		StringBuilder sql = new StringBuilder("select caffP2pBlackUserId, createdDateLong, version, userId from CaffP2pBlackUser");
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
				cbu = new CaffP2pBlackUser();
				cbu.setCaffp2pblackuserid(rs.getLong("CaffP2pBlackUserId"));
				cbu.setCreateddatelong(rs.getLong("createdDateLong"));
				cbu.setVersion(rs.getLong("version"));
				cbu.setUserid(rs.getLong("userId"));
				cbus.add(cbu);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	借款者表（CaffP2pBlackUser）发生错误-------------------------");
			e.printStackTrace();
		}
		return cbus;
	}
}