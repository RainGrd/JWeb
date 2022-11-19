package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAccountRecharge;


/**
 * Description：<br> 
 * 资金流水表（CaffP2pFundTally） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountRechargeMapper{
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  普通资金表（CaffP2pAccountRecharge）
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pAccountRecharge> findCaffP2pAccountRechargeList(){
		List<CaffP2pAccountRecharge> cs = new ArrayList<CaffP2pAccountRecharge>();
		CaffP2pAccountRecharge c;
		StringBuilder sql = new StringBuilder("select caffP2pAccountRechargeId, createdDateLong, "
				+ "version, userId, availableAmount, frozenAmount from CaffP2pAccountRecharge");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pAccountRecharge();
				c.setCaffp2paccountrechargeid(rs.getLong("caffP2pAccountRechargeId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAvailableamount(rs.getBigDecimal("availableAmount"));
				c.setFrozenamount(rs.getBigDecimal("frozenAmount"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pAccountRecharge）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}


}