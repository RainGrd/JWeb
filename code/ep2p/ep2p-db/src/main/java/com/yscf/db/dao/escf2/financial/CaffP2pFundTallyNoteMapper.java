package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pFundTallyNote;

/**
 * Description：<br> 
 * 资金流水备注表（CaffP2pFundTallyNoteNote） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pFundTallyNoteMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  资金流水备注表（CaffP2pFundTallyNote）
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pFundTallyNote> findCaffP2pFundTallyNoteList(){
		List<CaffP2pFundTallyNote> cs = new ArrayList<CaffP2pFundTallyNote>();
		CaffP2pFundTallyNote c;
		StringBuilder sql = new StringBuilder("select caffP2pFundTallyNoteId, createdDateLong, version, "
				+ "fundTallyId, note from CaffP2pFundTallyNote");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pFundTallyNote();
				c.setCaffp2pfundtallynoteid(rs.getLong("caffP2pFundTallyNoteId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setFundtallyid(rs.getLong("fundTallyId"));
				c.setNote(rs.getString("note"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金流水备注表（CaffP2pFundTallyNote）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}