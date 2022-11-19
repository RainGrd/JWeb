package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAccountAmountDetail;


/**
 * Description：<br> 
 * 资金类别变化明细表（CaffP2pAccountAmountDetail） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountAmountDetailMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  资金类别变化明细表（CaffP2pAccountAmountDetail） 
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pAccountAmountDetail> findCaffP2pAccountAmountDetailList(){
		List<CaffP2pAccountAmountDetail> caads = new ArrayList<CaffP2pAccountAmountDetail>();
		CaffP2pAccountAmountDetail caad;
		StringBuilder sql = new StringBuilder("select caffP2pAccountAmountDetailId, createdDateLong, version, userId, accountAmountTypeInt,")
		.append(" amount, balance, fundTallyId, fundTallyTypeInt from CaffP2pAccountAmountDetail");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				caad = new CaffP2pAccountAmountDetail();
				caad.setCaffp2paccountamountdetailid(rs.getLong("caffP2pAccountAmountDetailId"));
				caad.setCreateddatelong(rs.getLong("createdDateLong"));
				caad.setVersion(rs.getLong("version"));
				caad.setUserid(rs.getLong("userId"));
				caad.setAccountamounttypeint(rs.getInt("accountamounttypeint"));
				caad.setAmount(rs.getBigDecimal("amount"));
				caad.setBalance(rs.getBigDecimal("balance"));
				caad.setFundtallyid(rs.getLong("fundTallyId"));
				caad.setFundtallytypeint(rs.getInt("fundTallyTypeInt"));
				caads.add(caad);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pAccountAmountDetail）发生错误-------------------------");
			e.printStackTrace();
		}
		return caads;
	}
}