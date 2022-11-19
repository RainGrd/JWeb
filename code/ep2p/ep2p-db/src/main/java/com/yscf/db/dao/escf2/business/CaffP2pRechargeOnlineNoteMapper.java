package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pRechargeOnlineNote;

/**
 * Description：<br> 
 * 线上充值备注表（CaffP2pRechargeOnlineNote）  数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pRechargeOnlineNoteMapper{
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  线上充值备注表（CaffP2pRechargeOnlineNote）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pRechargeOnlineNote> findCaffP2pRechargeOnlineNoteList(){
		List<CaffP2pRechargeOnlineNote> cs = new ArrayList<CaffP2pRechargeOnlineNote>();
		CaffP2pRechargeOnlineNote c;
		StringBuilder sql = new StringBuilder("select caffP2pRechargeOnlineNoteId, createdDateLong, version,"
				+ " caffP2pRechargeOnlineId, note from CaffP2pRechargeOnlineNote");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pRechargeOnlineNote();
				c.setCaffp2prechargeonlinenoteid(rs.getLong("caffP2pRechargeOnlineNoteId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setCaffp2prechargeonlineid(rs.getLong("caffP2pRechargeOnlineId"));
				c.setNote(rs.getString("note"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	线上充值备注表（CaffP2pRechargeOnlineNote）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}