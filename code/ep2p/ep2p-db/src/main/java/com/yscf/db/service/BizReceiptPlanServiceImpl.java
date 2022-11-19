/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
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
import com.yscf.db.dao.escf2.financial.CaffP2pReceiptPlanMapper;
import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.financial.CaffP2pReceiptPlan;
import com.yscf.db.model.escf3.business.BizReceiptPlan;

/**
 * Description：<br> 
 * @author  JingYu.Dai
 * @date    2016年3月3日
 * @version v1.0.0
 */
public class BizReceiptPlanServiceImpl {
	private static StringBuilder sql = new StringBuilder("insert into t_biz_receipt_plan("
			+ "PID,rep_pla_pid,borrow_id,customer_id,transfer_id,play_type,is_vip,receivable_interest,"
			+ "net_interest,repaid_capital,repaid_interest,capital,remaining_capital,pay_rate,pay_amount,"
			+ "management_cost,receipt_status,expireactual_time,expire_time,late_fee,late_fee_calculate_time,"
			+ "pay_type,pay_amount_time,plan_index,repaid_time,interest,interest_type,create_time) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
	public static void main(String[] args) {
		try {
			insertCustomer(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static PreparedStatement fillSql(PreparedStatement pstmt, CaffP2pReceiptPlan c) throws SQLException{
		BizReceiptPlan b = new BizReceiptPlan();
		pstmt.setString(1, b.getPK());//PID	varchar(48)
		/**/pstmt.setString(2, c.getCaffp2prepaymentplanid().toString());//rep_pla_pid	varchar(48)
		/**/pstmt.setString(3, c.getBorrowid().toString());//borrow_id	varchar(48)
		pstmt.setString(4, c.getUserid().toString());//customer_id	varchar(48)
		/**/pstmt.setString(5, "");//transfer_id	varchar(48)
		/**/pstmt.setString(6, "");//play_type	varchar(2)
		/**/pstmt.setString(7, "");//is_vip	varchar(2)
		//CaffP2pReceiptPlan.interest  -  CaffP2pReceiptPlan.interest  *  系统参数表（CaffOption.name=data_interest_fee_rate)
		/**/pstmt.setBigDecimal(8,new BigDecimal(0));//receivable_interest	decimal(16,4)
		pstmt.setBigDecimal(9,c.getInterest());//net_interest	decimal(16,4)
		//CaffP2pReceiptPlan.stateInt=(2-已还，4-已垫付，5-垫付后已还)和 CaffP2pReceiptPlan.borrowId 相等 的所有用 CaffP2pReceiptPlan.interest之和
		/**/pstmt.setBigDecimal(10,new BigDecimal(0));//repaid_capital	decimal(18,2)
		//CaffP2pReceiptPlan.stateInt=(2-已还，4-已垫付，5-垫付后已还)和 CaffP2pReceiptPlan.borrowId 相等 的所有用 CaffP2pReceiptPlan.interest之和
		/**/pstmt.setBigDecimal(11,new BigDecimal(0));//repaid_interest	decimal(16,4)
		pstmt.setBigDecimal(12,c.getCapital());//capital	decimal(18,2)
		//CaffP2pReceiptPlan.stateInt=(1-待还，3-逾期)和 CaffP2pReceiptPlan.borrowId 相等 的所有用 CaffP2pReceiptPlan.capital之和
		/**/pstmt.setBigDecimal(13,new BigDecimal(0));//remaining_capital	decimal(18,2)
		pstmt.setBigDecimal(14,c.getPayrate());//pay_rate	decimal(16,4)
		pstmt.setBigDecimal(15,c.getPayamount());//pay_amount	decimal(18,2)
		//CaffP2pReceiptPlan.interest  *  系统参数表（CaffOption.name=data_interest_fee_rate)
		/**/pstmt.setBigDecimal(16,new BigDecimal(0));//management_cost	decimal(18,2)
		/**/pstmt.setString(17, c.getStateint().toString());//receipt_status	varchar(2)
		pstmt.setTimestamp(18, Timestamp.valueOf(DateUtil.format(new Date(c.getExpiredateactuallong()))));//expireactual_time	datetime
		pstmt.setTimestamp(19, Timestamp.valueOf(DateUtil.format(new Date(c.getExpiredatelong()))));//expire_time	datetime
		pstmt.setBigDecimal(20,c.getLatefee());//late_fee	decimal(16,4)
		pstmt.setTimestamp(21, Timestamp.valueOf(DateUtil.format(new Date(c.getLatefeecalculatedatelong()))));//late_fee_calculate_time	datetime
		/**/pstmt.setString(22, c.getPaytypeint().toString());//pay_type	varchar(2)
		pstmt.setTimestamp(23, Timestamp.valueOf(DateUtil.format(new Date(c.getPaydatelong()))));//pay_amount_time	datetime
		/**/pstmt.setString(24, c.getPlanindex().toString());//plan_index	varchar(50)
		pstmt.setTimestamp(25, Timestamp.valueOf(DateUtil.format(new Date(c.getRepaiddatelong()))));//repaid_time	datetime
		pstmt.setBigDecimal(26,c.getInterest());//interest	decimal(16,4)
		/**/pstmt.setString(27, c.getInterestcalculationtypeint().toString());//interest_type	varchar(2)
		pstmt.setTimestamp(28, Timestamp.valueOf(DateUtil.format(new Date(c.getCreateddatelong()))));//create_time	datetime
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
		//系统用户表（CaffP2pReceiptPlan）集合
		List<CaffP2pReceiptPlan> cus = CaffP2pReceiptPlanMapper.findCaffP2pReceiptPlanList();
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


