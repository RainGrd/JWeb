package com.yscf.db.dao.escf2.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.system.CaffP2pPayConfig;


/**
 * Description：<br> 
 * 支付账户配置表(CaffP2pPayConfig) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pPayConfigMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  支付账户配置表（CaffP2pPayConfig）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pPayConfig> findCaffP2pPayConfigList(){
		List<CaffP2pPayConfig> cs = new ArrayList<CaffP2pPayConfig>();
		CaffP2pPayConfig c;
		StringBuilder sql = new StringBuilder("select caffP2pPayConfigId, createdDateLong, version, userId, "
				+ "paySystemInt,exInfo1, exInfo2, merchantId, signKey from CaffP2pPayConfig");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pPayConfig();
				c.setCaffp2ppayconfigid(rs.getLong("caffP2pPayConfigId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setPaysystemint(rs.getInt("paySystemInt"));
				c.setExinfo1(rs.getString("exInfo1"));
				c.setExinfo2(rs.getString("exInfo2"));
				c.setMerchantid(rs.getString("merchantId"));
				c.setSignkey(rs.getString("signKey"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	 支付账户配置表 (CaffP2pPayConfig)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}