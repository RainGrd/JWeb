package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pAuthIdentity;

/**
 * Description：<br> 
 * 身份认证表(CaffP2pAuthIdentity)	数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pAuthIdentityMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  获取身份认证信息(CaffP2pAuthIdentity)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pAuthIdentity> findCaffP2pAuthIdentityList(long userId){
		List<CaffP2pAuthIdentity> cais = new ArrayList<CaffP2pAuthIdentity>();
		CaffP2pAuthIdentity cai;
		StringBuilder sql = new StringBuilder("select caffP2pAuthIdentityId, createdDateLong, version, auditStateInt, originalRealName,")
		.append("newRealName, userId, note from CaffP2pAuthIdentity");
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
				cai = new CaffP2pAuthIdentity();
				cai.setCaffp2pauthidentityid(rs.getLong("caffP2pAuthIdentityId"));
				cai.setCreateddatelong(rs.getLong("createdDateLong"));
				cai.setVersion(rs.getLong("version"));
				cai.setAuditstateint(rs.getInt("auditStateInt"));
				cai.setOriginalrealname(rs.getString("originalRealName"));
				cai.setNewrealname(rs.getString("newRealName"));
				cai.setUserid(rs.getLong("userId"));
				cai.setNote(rs.getString("note"));
				cais.add(cai);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	身份认证表（CaffP2pAuthIdentity）发生错误-------------------------");
			e.printStackTrace();
		}
		return cais;
	}
}