package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pBorrowDescription;


/**
 * Description：<br> 
 * 借款详情表(CaffP2pBorrowDescription)  数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowDescriptionMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  借款详情表（CaffP2pBorrowDescription）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBorrowDescription> findCaffP2pBorrowDescriptionList(){
		List<CaffP2pBorrowDescription> cs = new ArrayList<CaffP2pBorrowDescription>();
		CaffP2pBorrowDescription c;
		StringBuilder sql = new StringBuilder("select caffP2pBorrowDescriptionId, createdDateLong, version,"
				+ " borrowId, counterGuarantee, description, riskNote from CaffP2pBorrowDescription");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pBorrowDescription();
				c.setCaffp2pborrowdescriptionid(rs.getLong("caffP2pBorrowDescriptionId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setBorrowid(rs.getLong("borrowId"));
				c.setCounterguarantee(rs.getString("counterGuarantee"));
				c.setDescription(rs.getString("description"));
				c.setRisknote(rs.getString("riskNote"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	借款详情表(CaffP2pBorrowDescription)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}