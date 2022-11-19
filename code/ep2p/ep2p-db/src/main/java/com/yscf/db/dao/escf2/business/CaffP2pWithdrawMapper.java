package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pWithdraw;

/**
 * Description：<br> 
 * 提现表(CaffP2pWithdraw) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pWithdrawMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  提现表（CaffP2pWithdraw）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffP2pWithdraw> findCaffP2pWithdrawList(){
		List<CaffP2pWithdraw> cs = new ArrayList<CaffP2pWithdraw>();
		CaffP2pWithdraw c;
		StringBuilder sql = new StringBuilder("select caffP2pWithdrawId, createdDateLong, version, userId, "
				+ "accountNumber, amount, cost, fee, bankTypeInt, cityInt, openingBank, provinceInt, stateInt, "
				+ "transferedDateLong, auditDateLong, bitState from CaffP2pWithdraw");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pWithdraw();
				c.setCaffp2pwithdrawid(rs.getLong("caffP2pWithdrawId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setAccountnumber(rs.getString("accountNumber"));
				c.setAmount(rs.getLong("amount"));
				c.setCost(rs.getLong("cost"));
				c.setFee(rs.getLong("fee"));
				c.setBanktypeint(rs.getInt("bankTypeInt"));
				c.setCityint(rs.getInt("cityInt"));
				c.setOpeningbank(rs.getString("openingBank"));
				c.setProvinceint(rs.getInt("provinceInt"));
				c.setStateint(rs.getInt("stateInt"));
				c.setTransfereddatelong(rs.getLong("transferedDateLong"));
				c.setAuditdatelong(rs.getLong("auditDateLong"));
				c.setBitstate(rs.getLong("bitState"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	提现表 (CaffP2pWithdraw)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}