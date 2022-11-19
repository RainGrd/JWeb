/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年3月3日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.db.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.achievo.framework.util.DateUtil;
import com.yscf.db.dao.escf2.financial.CaffP2pAccountCommonMapper;
import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAccountCommon;
import com.yscf.db.model.escf3.financial.BizAccountCommon;

/**
 * Description：<br> 
 * 数据迁移 escf2 -> escf3
 * 普通资金表（t_biz_account_common）
 * @author  JingYu.Dai
 * @date    2016年3月3日
 * @version v1.0.0
 */
public class BizAccountCommonServiceImpl {
	private static StringBuilder sql = new StringBuilder("insert into t_biz_account_common"
			+ "(PID,customer_id,available_amount,frozen_amount,create_time) values (?,?,?,?,?)");
	
	public static void main(String[] args) {
		try {
			insertCustomer(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static PreparedStatement fillSql(PreparedStatement pstmt, CaffP2pAccountCommon c) throws SQLException{
		BizAccountCommon b = new BizAccountCommon();
		pstmt.setString(1, b.getPK());//PID	varchar(48)
		/**/pstmt.setString(2, c.getUserid().toString());//customer_id	varchar(48)
		/**/pstmt.setBigDecimal(3, new BigDecimal(0));//available_amount	decimal(18,2)
		/**/pstmt.setBigDecimal(4, new BigDecimal(0));//frozen_amount	decimal(18,2)
		pstmt.setTimestamp(5, Timestamp.valueOf(DateUtil.format(new Date(c.getCreateddatelong()))));//create_time	datetime
		return pstmt;
	}
	
	/**
	 * Description：<br> 
	 * 批量新增 普通资金表（t_biz_account_common）批处理执行预处理SQL
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @param m 批次 
	 * @param n 每批数量 
	 * @throws Exception
	 */
	public static void insertCustomer(int n) throws Exception {
		Long start = System.currentTimeMillis();
		DBConnectionManager db = DBConnectionManager.getInstance();
		//系统用户表（CaffP2pAccountCommon）集合
		List<CaffP2pAccountCommon> cus = CaffP2pAccountCommonMapper.findCaffP2pAccountCommonList();
		int ml = cus.size();
		int m = (int) Math.ceil(((double)ml)/n);
		for (int i = 0; i < m; i++) {
			// 从池中获取连接
			Connection conn = db.getConnection("escf");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int j = i*n;
			for (int k = j ; k < (i+1)*n; k++) {
				if(k<ml){
					// 加入批处理
					fillSql(pstmt,cus.get(k)).addBatch();
				}else{
					break;
				}
			}
			pstmt.executeBatch(); // 执行批处理
			conn.commit();
			// pstmt.clearBatch(); //清理批处理
			pstmt.close();
			db.freeConnection("escf", conn); // 连接归池
		}
		Long end = System.currentTimeMillis();
		System.out.println("批量执行" + m + "*" + n + "=" + m * n
				+ "条Insert操作，共耗时：" + (end - start) / 1000f + "秒！");
	}
}


