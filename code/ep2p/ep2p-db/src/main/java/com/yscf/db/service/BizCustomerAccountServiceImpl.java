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
import com.yscf.db.dao.escf2.financial.CaffP2pAccountAmountSumMapper;
import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pAccountAmountSum;
import com.yscf.db.model.escf3.financial.BizCustomerAccount;

/**
 * Description：<br> 
 * 数据迁移 escf2 -> escf3
 * 客户账户表（t_biz_customer_account）
 * @author  JingYu.Dai
 * @date    2016年3月3日
 * @version v1.0.0
 */
public class BizCustomerAccountServiceImpl {
	private static StringBuilder sql = new StringBuilder("insert into t_biz_customer_account ("
			+ "PID,customer_id,recharge_amount,withdraw_amount,due_amount,available_amount,"
			+ "freeze_amount,tender_amount,experience_amount,create_time) values (?,?,?,?,?,?,?,?,?,?)");
	
	public static void main(String[] args) {
		try {
			insertCustomer(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static PreparedStatement fillSql(PreparedStatement pstmt, CaffP2pAccountAmountSum c) throws SQLException{
		BizCustomerAccount b = new BizCustomerAccount();
		pstmt.setString(1, b.getPK());//PID	varchar(48)
		/*主键*/pstmt.setString(2, c.getUserid().toString());//customer_id	varchar(48)
		/*资金流水*/pstmt.setBigDecimal(3, new BigDecimal(0));//recharge_amount	decimal(18,2)
		/*资金流水*/pstmt.setBigDecimal(4, new BigDecimal(0));//withdraw_amount	decimal(18,2)
		pstmt.setBigDecimal(5, c.getReceiptpendingamount());//due_amount	decimal(18,2)
		pstmt.setBigDecimal(6, c.getAvailableamount());//available_amount	decimal(18,2)
		pstmt.setBigDecimal(7, c.getFrozenamount());//freeze_amount	decimal(18,2)
		/*资金流水*/pstmt.setBigDecimal(8, new BigDecimal(0));//tender_amount	decimal(18,2)
		pstmt.setBigDecimal(9, c.getExperienceavailabeamount());//experience_amount	decimal(18,2)
		pstmt.setTimestamp(10, Timestamp.valueOf(DateUtil.format(new Date(c.getCreateddatelong()))));//create_time	datetime
		return pstmt;
	}
	
	/**
	 * Description：<br> 
	 * 批量新增资金类别变化明细	批处理执行预处理SQL
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
		//系统用户表（CaffP2pAccountAmountSum）集合
		List<CaffP2pAccountAmountSum> cus = CaffP2pAccountAmountSumMapper.findCaffP2pAccountAmountSumList();
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


