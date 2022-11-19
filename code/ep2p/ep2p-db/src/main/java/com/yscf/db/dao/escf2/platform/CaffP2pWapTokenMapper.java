package com.yscf.db.dao.escf2.platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.platform.CaffP2pWapToken;


/**
 * Description：<br> 
 * 京东支付返回的充值Token值记录表(CaffP2pWapToken) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pWapTokenMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  京东支付返回的充值Token值记录表(CaffP2pWapToken)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pWapToken> findCaffP2pWapTokenList(long userId){
		List<CaffP2pWapToken> cs = new ArrayList<CaffP2pWapToken>();
		CaffP2pWapToken c;
		StringBuilder sql = new StringBuilder("select caffP2pWapTokenId, createdDateLong, version, "
				+ "userId, token from CaffP2pWapToken");
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
				c = new CaffP2pWapToken();
				c.setCaffp2pwaptokenid(rs.getLong("caffP2pWapTokenId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setToken(rs.getString("token"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	京东支付返回的充值Token值记录表（CaffP2pWapToken）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}