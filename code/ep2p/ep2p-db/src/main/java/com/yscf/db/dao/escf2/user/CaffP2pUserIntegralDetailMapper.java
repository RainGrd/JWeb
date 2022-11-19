package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pUserIntegralDetail;


/**
 * Description：<br> 
 * 积分明细表（CaffP2pUserIntegralDetail）  数据据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserIntegralDetailMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  积分明细表（CaffP2pUserIntegralDetail）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pUserIntegralDetail> findCaffP2pUserIntegralDetailList(){
		List<CaffP2pUserIntegralDetail> cs = new ArrayList<CaffP2pUserIntegralDetail>();
		CaffP2pUserIntegralDetail c;
		StringBuilder sql = new StringBuilder("select caffP2pUserIntegralDetailId, createdDateLong, version, userId, "
				+ "fkey, note, point, userIntegralTypeInt from CaffP2pUserIntegralDetail");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pUserIntegralDetail();
				c.setCaffp2puserintegraldetailid(rs.getLong("caffP2pUserIntegralDetailId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setFkey(rs.getLong("fkey"));
				c.setNote(rs.getString("note"));
				c.setPoint(rs.getInt("point"));
				c.setUserintegraltypeint(rs.getInt("userIntegralTypeInt"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	 积分明细表(CaffP2pUserIntegralDetail)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}