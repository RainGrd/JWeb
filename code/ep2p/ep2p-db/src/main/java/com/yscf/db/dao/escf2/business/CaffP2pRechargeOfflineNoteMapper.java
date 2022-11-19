package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pRechargeOfflineNote;

/**
 * Description：<br> 
 * 线下充值备注表（CaffP2pRechargeOfflineNoteNote) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pRechargeOfflineNoteMapper {
	/**
	 * Description：<br> 
	 *  获取escf2.0数据  线下充值表（CaffP2pRechargeOfflineNote）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pRechargeOfflineNote> findCaffP2pRechargeOfflineNoteList(){
		List<CaffP2pRechargeOfflineNote> cs = new ArrayList<CaffP2pRechargeOfflineNote>();
		CaffP2pRechargeOfflineNote c;
		StringBuilder sql = new StringBuilder("select caffP2pRechargeOfflineNoteId, createdDateLong, version, "
				+ "caffP2pRechargeOfflineId, note, audiNote from CaffP2pRechargeOfflineNote");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pRechargeOfflineNote();
				c.setCaffp2prechargeofflinenoteid(rs.getLong("caffP2pRechargeOfflineNoteId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setCaffp2prechargeofflineid(rs.getLong("caffP2pRechargeOfflineId"));
				c.setNote(rs.getString("note"));
				c.setAudinote(rs.getString("audiNote"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pRechargeOfflineNote）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}