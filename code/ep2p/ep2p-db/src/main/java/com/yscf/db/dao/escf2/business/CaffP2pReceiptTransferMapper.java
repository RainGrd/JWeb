package com.yscf.db.dao.escf2.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.business.CaffP2pReceiptTransfer;


/**
 * Description：<br> 
 * 债权转让实体（CaffP2pReceiptTransfer） 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pReceiptTransferMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  债权转让实体（CaffP2pReceiptTransfer）
	 * @author  JingYu.Dai
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pReceiptTransfer> findCaffP2pReceiptTransferList(){
		List<CaffP2pReceiptTransfer> cs = new ArrayList<CaffP2pReceiptTransfer>();
		CaffP2pReceiptTransfer c;
		StringBuilder sql = new StringBuilder("select caffP2pReceiptTransferId, createdDateLong, version, "
				+ "userId, bitState, caffP2pReceiptPlanId, expireDateLong, newUserId, profitRate, receiptDateLong, "
				+ "rootTransferId, serviceFeeRate, stateEnum, stateInt, successAmount, successDateLong,"
				+ "transferAmount from CaffP2pReceiptTransfer");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffP2pReceiptTransfer();
				c.setCaffp2preceipttransferid(rs.getLong("caffP2pReceiptTransferId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setUserid(rs.getLong("userId"));
				c.setBitstate(rs.getLong("bitState"));
				c.setCaffp2preceiptplanid(rs.getLong("caffP2pReceiptPlanId"));
				c.setExpiredatelong(rs.getLong("expireDateLong"));
				c.setNewuserid(rs.getLong("newUserId"));
				c.setProfitrate(rs.getBigDecimal("profitRate"));
				c.setReceiptdatelong(rs.getLong("receiptDateLong"));
				c.setRoottransferid(rs.getLong("rootTransferId"));
				c.setServicefeerate(rs.getBigDecimal("serviceFeeRate"));
				c.setStateenum(rs.getInt("stateEnum"));
				c.setStateint(rs.getInt("stateInt"));
				c.setSuccessamount(rs.getBigDecimal("successAmount"));
				c.setSuccessdatelong(rs.getLong("successDateLong"));
				c.setTransferamount(rs.getBigDecimal("transferAmount"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	资金类别变化明细表（CaffP2pReceiptTransfer）发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}

    
}