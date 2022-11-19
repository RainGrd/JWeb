package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pUserReferrer;

/**
 * Description：<br> 
 * 推荐人表（CaffP2pUserReferrer） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserReferrerMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据	推荐人表（CaffP2pUserReferrer）
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pUserReferrer> findCaffP2pUserReferrerList(long userId){
		 List<CaffP2pUserReferrer> urs = new ArrayList<CaffP2pUserReferrer>();
		 	CaffP2pUserReferrer ur;
			StringBuilder sql = new StringBuilder("select caffP2pUserReferrerId, createdDateLong, version, userId, bitState, referrerId")
			.append(" from CaffP2pUserReferrer");
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
					ur = new CaffP2pUserReferrer();
					ur.setCaffp2puserreferrerid(rs.getLong("caffP2pUserReferrerId"));
					ur.setCreateddatelong(rs.getLong("createdDateLong"));
					ur.setVersion(rs.getLong("version"));
					ur.setUserid(rs.getLong("userId"));
					ur.setBitstate(rs.getLong("bitState"));
					ur.setReferrerid(rs.getLong("referrerId"));
					urs.add(ur);
				}
				rs.close();
				stmt.close();
				db.freeConnection("escf2", conn);
			} catch (SQLException e) {
				System.out.println("error---------------------------获取escf2.0数据	推荐人表（CaffP2pUserReferrer）发生错误-------------------------");
				e.printStackTrace();
			}
			return urs;
	}
}