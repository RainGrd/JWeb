package com.yscf.db.dao.escf2.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.system.CaffP2pBorrowerImage;


/**
 * Description：<br> 
 * 借款者图片材料表(CaffP2pBorrowerImage) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowerImageMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  借款者图片材料表（CaffP2pBorrowerImage）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pBorrowerImage> findCaffP2pBorrowerImageList(){
		List<CaffP2pBorrowerImage> cs = new ArrayList<CaffP2pBorrowerImage>();
		CaffP2pBorrowerImage c;
		StringBuilder sql = new StringBuilder("select caffP2pBorrowerImageId, createdDateLong, version, "
				+ "userId, borrowId, fileId, indexOrder from CaffP2pBorrowerImage");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pBorrowerImage();
				c.setCaffp2pborrowerimageid(rs.getLong("caffP2pBorrowerImageId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setBorrowid(rs.getLong("borrowId"));
				c.setFileid(rs.getLong("fileId"));
				c.setIndexorder(rs.getInt("indexOrder"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	借款者图片材料表(CaffP2pBorrowerImage)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}