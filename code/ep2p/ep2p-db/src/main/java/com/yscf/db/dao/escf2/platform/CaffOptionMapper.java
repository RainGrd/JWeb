package com.yscf.db.dao.escf2.platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.platform.CaffOption;


/**
 * Description：<br> 
 * 系统选项表(CaffOption) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffOptionMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  系统选项表(CaffOption)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffOption> findCaffOptionList(long userId){
		List<CaffOption> cs = new ArrayList<CaffOption>();
		CaffOption c;
		StringBuilder sql = new StringBuilder("select caffOptionId, createdDateLong, version, bitState,"
				+ " name, sort, title, value from CaffOption");
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
				c = new CaffOption();
				c.setCaffoptionid(rs.getLong("caffOptionId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setBitstate(rs.getLong("bitState"));
				c.setName(rs.getString("name"));
				c.setSort(rs.getInt("sort"));
				c.setTitle(rs.getString("title"));
				c.setValue(rs.getString("value"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	系统选项表（CaffOption）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}