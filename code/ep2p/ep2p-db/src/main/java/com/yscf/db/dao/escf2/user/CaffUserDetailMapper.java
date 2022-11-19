package com.yscf.db.dao.escf2.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffUserDetail;


public class CaffUserDetailMapper {
	/**
	 * Description：<br> 
	 * 获取escf2.0数据	用户详细信息表（CaffUserDetail）
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffUserDetail> findCaffUserDetailList(long userId){
		List<CaffUserDetail> uds = new ArrayList<CaffUserDetail>();
		CaffUserDetail ud;
		StringBuilder sql = new StringBuilder("select caffUserDetailId, createdDateLong, version, birthDateLong, currentAddress, eduBackgroundInt,")
		.append("houseAddress, houseConditionInt, idNumber, idTypeInt, incomeGradeInt, kidCount, marriageInt, ")
		.append("msn, nationalityInt, nativeCityInt, nativeProvinceInt, phoneDistrict, phoneNumber,")
		.append("qq, realName, sexInt, socialInssanceCode, socialRoleInt, userId, ww, zip, reference1, ")
		.append("reference2, reference3 from CaffUserDetail");
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
				ud = new CaffUserDetail();
				ud.setCaffuserdetailid(rs.getLong("caffUserDetailId"));
				ud.setCreateddatelong(rs.getLong("createdDateLong"));
				ud.setVersion(rs.getLong("version"));
				ud.setBirthdatelong(rs.getLong("birthDateLong"))  ;
				ud.setCurrentaddress(rs.getString("currentAddress"));
				ud.setEdubackgroundint(rs.getInt("eduBackgroundInt"));
				ud.setHouseaddress(rs.getString("houseAddress"));
				ud.setHouseconditionint(rs.getInt("houseConditionInt"));
				ud.setIdnumber(rs.getString("idNumber"));
				ud.setIdtypeint(rs.getInt("idTypeInt"));
				ud.setIncomegradeint(rs.getInt("incomeGradeInt"));
				ud.setKidcount(rs.getInt("kidCount"));
				ud.setMarriageint(rs.getInt("marriageInt"));
				ud.setMsn(rs.getString("msn"));
				ud.setNationalityint(rs.getInt("nationalityInt"));
				ud.setNativecityint(rs.getInt("nativeCityInt"));
				ud.setNativeprovinceint(rs.getInt("nativeProvinceInt"));
				ud.setPhonedistrict(rs.getString("phoneDistrict"));
				ud.setPhonenumber(rs.getString("phoneNumber"));
				ud.setQq(rs.getString("qq"));
				ud.setRealname(rs.getString("realName"));
				ud.setSexint(rs.getInt("sexInt"));
				ud.setSocialinssancecode(rs.getString("socialInssanceCode"));
				ud.setSocialroleint(rs.getInt("socialRoleInt"));
				ud.setUserid(rs.getLong("userId"));
				ud.setWw(rs.getString("ww"));
				ud.setZip(rs.getString("zip"));
				ud.setReference1(rs.getLong("reference1"));
				ud.setReference2(rs.getLong("reference2"));
				ud.setReference3(rs.getLong("reference3"));
				uds.add(ud);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	用户详细信息表（CaffUserDetail）发生错误-------------------------");
			e.printStackTrace();
		}
		return uds;
	}
}