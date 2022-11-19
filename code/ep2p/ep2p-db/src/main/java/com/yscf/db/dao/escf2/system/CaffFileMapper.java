package com.yscf.db.dao.escf2.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.system.CaffFile;


/**
 * Description：<br> 
 * 用户上传文件表(CaffFile) 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffFileMapper{
	
	/**
	 * Description：<br> 
	 * 获取escf2.0数据  银行表（CaffFile）
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @return
	 */
	public static List<CaffFile> findCaffFileList(){
		List<CaffFile> cs = new ArrayList<CaffFile>();
		CaffFile c;
		StringBuilder sql = new StringBuilder("select caffFileId, createdDateLong, version, albumId,"
				+ " bitState, fileTypeInt, ioName, name, userId from CaffFile");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf2");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				c = new CaffFile();
				c.setCafffileid(rs.getLong("caffFileId"));
				c.setCreateddatelong(rs.getLong("createdDateLong"));
				c.setVersion(rs.getLong("version"));
				c.setAlbumid(rs.getLong("albumId"));
				c.setBitstate(rs.getLong("bitState"));
				c.setFiletypeint(rs.getInt("fileTypeInt"));
				c.setIoname(rs.getString("ioName"));
				c.setName(rs.getString("name"));
				c.setUserid(rs.getLong("userId"));
				cs.add(c);
			}
			rs.close();
			stmt.close();
			db.freeConnection("escf2", conn);
		} catch (SQLException e) {
			System.out.println("error---------------------------获取escf2.0数据	用户上传文件表(CaffFile)发生错误-------------------------");
			e.printStackTrace();
		}
		return cs;
	}
}