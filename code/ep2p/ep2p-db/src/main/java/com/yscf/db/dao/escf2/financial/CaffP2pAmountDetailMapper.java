package com.yscf.db.dao.escf2.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAmountDetail;


/**
 * Description：<br> 
 * 资金组成详情表（CaffP2pAmountDetail） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAmountDetailMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  资金组成详情表(CaffP2pAmountDetail)
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pAmountDetail> findCaffP2pAmountDetailList(){
		List<CaffP2pAmountDetail> cs = new ArrayList<CaffP2pAmountDetail>();
		CaffP2pAmountDetail c;
		StringBuilder sql = new StringBuilder("select caffP2pAmountDetailId, createdDateLong, version, userId, "
				+ "accountAmountTypeInt, amount, fkey, fundTallyTypeInt from CaffP2pAmountDetail");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pAmountDetail();
				c.setCaffp2pamountdetailid(rs.getLong("caffP2pAmountDetailId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAccountamounttypeint(rs.getInt("accountAmountTypeInt"));
				c.setAmount(rs.getBigDecimal("amount"));
				c.setFkey(rs.getLong("fkey"));
				c.setFundtallytypeint(rs.getInt("fundTallyTypeInt"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pAmountDetail）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}


}