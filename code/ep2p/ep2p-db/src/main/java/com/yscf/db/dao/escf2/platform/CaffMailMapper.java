package com.yscf.db.dao.escf2.platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.platform.CaffMail;


/**
 * Description：<br> 
 * 邮件表（CaffMail） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffMailMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  邮件表(CaffMail)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffMail> findCaffMailList(long userId){
		List<CaffMail> cs = new ArrayList<CaffMail>();
		CaffMail c;
		StringBuilder sql = new StringBuilder("select caffMailId, createdDateLong, version, bitState, description, "
				+ "fromEmail, host, pass, templateFolder, username from CaffMail");
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
				c = new CaffMail();
				c.setCaffmailid(rs.getLong("caffMailId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setBitstate(rs.getLong("bitState"));
				c.setDescription(rs.getString("description"));
				c.setFromemail(rs.getString("fromEmail"));
				c.setHost(rs.getString("host"));
				c.setPass(rs.getString("pass"));
				c.setTemplatefolder(rs.getString("templateFolder"));
				c.setUsername(rs.getString("username"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	邮件表（CaffMail）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}