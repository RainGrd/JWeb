package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pVip;


/**
 * Description：<br> 
 * VIP表(CaffP2pVip)  数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pVipMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  VIP表(CaffP2pVip)
	 * @author  JingYu.Dai
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public static List<CaffP2pVip> findCaffP2pVipList(long userId){
		List<CaffP2pVip> cvs = new ArrayList<CaffP2pVip>();
		CaffP2pVip cv;
		StringBuilder sql = new StringBuilder("select caffP2pVipId, createdDateLong, version, userId, beginDateLong, endDateLong, fee, ")
		.append("serviceId, stateInt, vipAchieveTypeInt, bitState, flagInt from CaffP2pVip");
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
				cv = new CaffP2pVip();
				cv.setCaffp2pvipid(rs.getLong("caffP2pVipId"));
				cv.setCreateddatelong(rs.getLong("createdDateLong"));
				cv.setVersion(rs.getLong("version"));
				cv.setUserid(rs.getLong("userId"));
				cv.setBegindatelong(rs.getLong("beginDateLong"));
				cv.setEnddatelong(rs.getLong("endDateLong"));
				cv.setFee(rs.getLong("fee"));
				cv.setServiceid(rs.getLong("serviceId"));
				cv.setStateint(rs.getInt("stateInt"));
				cv.setVipachievetypeint(rs.getInt("vipAchieveTypeInt"));
				cv.setBitstate(rs.getLong("bitState"));
				cv.setFlagint(rs.getInt("flagInt"));
				cvs.add(cv);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	用户详细信息表（CaffP2pVip）发生错误-------------------------");
			e.printStackTrace();
		}
		return cvs;
	}
}