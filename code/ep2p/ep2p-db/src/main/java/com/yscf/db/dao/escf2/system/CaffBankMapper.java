package com.yscf.db.dao.escf2.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.system.CaffBank;


/**
 * Description：<br> 
 * 银行表(CaffBank) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffBankMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  银行表（CaffBank）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffBank> findCaffBankList(){
		List<CaffBank> cs = new ArrayList<CaffBank>();
		CaffBank c;
		StringBuilder sql = new StringBuilder("select caffBankId, createdDateLong, version, accountNumber, "
				+ "bankTypeInt, bitState, cityInt, openingBank, provinceInt, userId from CaffBank");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffBank();
				c.setCaffbankid(rs.getLong("caffBankId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setAccountnumber(rs.getString("accountNumber"));
				c.setBanktypeint(rs.getInt("bankTypeInt"));
				c.setBitstate(rs.getLong("bitState"));
				c.setCityint(rs.getInt("cityInt"));
				c.setOpeningbank(rs.getString("openingBank"));
				c.setProvinceint(rs.getInt("provinceInt"));
				c.setUserid(rs.getLong(rs.getInt("userId")));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	银行表(CaffBank)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}