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
 * 2016年3月1日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.db.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.achievo.framework.util.DateUtil;
import com.yscf.db.dao.escf2.user.CaffP2pAuthIdentityMapper;
import com.yscf.db.dao.escf2.user.CaffP2pBlackUserMapper;
import com.yscf.db.dao.escf2.user.CaffP2pBorrowerDataMapper;
import com.yscf.db.dao.escf2.user.CaffP2pUserIntegralMapper;
import com.yscf.db.dao.escf2.user.CaffP2pUserMapper;
import com.yscf.db.dao.escf2.user.CaffP2pUserReferrerMapper;
import com.yscf.db.dao.escf2.user.CaffP2pVipMapper;
import com.yscf.db.dao.escf2.user.CaffUserDetailMapper;
import com.yscf.db.jdbc.DBConnectionManager;
import com.yscf.db.model.escf2.user.CaffP2pAuthIdentity;
import com.yscf.db.model.escf2.user.CaffP2pBlackUser;
import com.yscf.db.model.escf2.user.CaffP2pBorrowerData;
import com.yscf.db.model.escf2.user.CaffP2pUser;
import com.yscf.db.model.escf2.user.CaffP2pUserIntegral;
import com.yscf.db.model.escf2.user.CaffP2pUserReferrer;
import com.yscf.db.model.escf2.user.CaffP2pVip;
import com.yscf.db.model.escf2.user.CaffUserDetail;
import com.yscf.db.model.escf3.user.CusTomer;
import com.yscf.db.util.IdCardUtils;

/**
 * Description：<br> 
 * 客户 数据迁移
 * @author  JingYu.Dai
 * @date    2016年3月1日
 * @version v1.0.0
 */
public class CustomerServiceImpl {
	
	//新增客户sql
	private static StringBuilder sql = new StringBuilder(
			"insert into t_cust_customer\n(")
			.append("PID,vip_id,customer_name,sname,birthday,res_reg,age,password,tradePassword,phone_no,"
					+ "email,sex,status,registration_time,referee_user,customer_service_user,"
					+ "available_point,empirical_value,is_blacklist,is_forbid_withdraw,is_forbid_transfer,"
					+ "is_freeze,is_attestation,is_vip,is_invest,attestation_time,bor_or_inv_cus,vip_time,"
					+ "attestation_status,is_authentication,identification_no,is_marriage,"
					+ "home_address,now_address,create_time,province,city,index_,"
					+ "error_count,referral_code)")
			.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					 + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
	/**
	 * 用户详细信息表（CaffUserDetail）  一对一
	 */
	private static Map<Long,CaffUserDetail> cudMap = new HashMap<Long,CaffUserDetail>();
	/**
	 * 积分概况表（CaffP2pUserIntegral） 一对一
	 */
	private static Map<Long,CaffP2pUserIntegral> cuiMap = new HashMap<Long, CaffP2pUserIntegral>();
	/**
	 * 黑名单用户表（CaffP2pBlackUser） 一对一
	 */
	private static Map<Long,CaffP2pBlackUser> cbuMap = new HashMap<Long, CaffP2pBlackUser>();
	/**
	 * 身份认证表(CaffP2pAuthIdentity) 一对多
	 */
	private static Map<Long,CaffP2pAuthIdentity> caiMap = new HashMap<Long, CaffP2pAuthIdentity>();
	/**
	 * VIP表(CaffP2pVip) 一对多
	 */
	private static Map<Long,CaffP2pVip> cvMap = new HashMap<Long, CaffP2pVip>();
	/**
	 * 借款者表（CaffP2pBorrowerData）一对一
	 */
	private static Map<Long,CaffP2pBorrowerData> cbdMap = new HashMap<Long, CaffP2pBorrowerData>();
	/**
	 * 推荐人表（CaffP2pUserReferrer）一对一
	 */
	private static Map<Long,CaffP2pUserReferrer> curMap = new HashMap<Long, CaffP2pUserReferrer>();
	
	public static void main(String[] args) {
		try {
			initData();
			insertCustomer(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//环境监测  根据以下sql 判断 以下表跟用户表的关系
	public void environmentalAudit(){
		/**
		 * -- ----------------1/2 一对一
			select count(*) from (select distinct userId from CaffUserDetail ) c;
			select count(*) from CaffUserDetail ;
			-- ----------------3/4一对一
			select count(*) from (select distinct userId from CaffP2pUserIntegral ) c;
			select count(*) from CaffP2pUserIntegral ;
			-- ----------------5/6一对一
			select count(*) from (select distinct userId from CaffP2pBlackUser ) c;
			select count(*) from CaffP2pBlackUser ;
			-- ----------------7/8一对多
			select count(*) from (select distinct userId from CaffP2pAuthIdentity ) c;
			select count(*) from CaffP2pAuthIdentity ;
			-- ----------------9/10一对多
			select count(*) from (select distinct userId from CaffP2pVip ) c;
			select count(*) from CaffP2pVip ;
			-- ----------------11/12一对一
			select count(*) from (select distinct userId from CaffP2pBorrowerData ) c;
			select count(*) from CaffP2pBorrowerData ;
			-- ----------------13/14一对一
			select count(*) from (select distinct userId from CaffP2pUserReferrer ) c;
			select count(*) from CaffP2pUserReferrer ;
		 */
	}
	
	//初始化数据
	public static void initData(){
		//获取escf2.0数据 用户详细信息表（CaffUserDetail）集合
		List<CaffUserDetail> cuds = CaffUserDetailMapper.findCaffUserDetailList(-1);
		for (CaffUserDetail cud : cuds) {
			cudMap.put(cud.getUserid(), cud);
		}
		//获取escf2.0数据 积分概况表（CaffP2pUserIntegral）集合
		List<CaffP2pUserIntegral> cuis = CaffP2pUserIntegralMapper.findCaffP2pUserIntegralList(-1);
		for (CaffP2pUserIntegral cui : cuis) {
			cuiMap.put(cui.getUserid(), cui);
		}
		//获取escf2.0数据  获取黑名单客户(CaffP2pBlackUser)集合
		List<CaffP2pBlackUser> cbus = CaffP2pBlackUserMapper.findCaffP2pBlackUserList(-1);
		for (CaffP2pBlackUser cbu : cbus) {
			cbuMap.put(cbu.getUserid(), cbu);
		}
		//获取escf2.0数据  获取身份认证信息(CaffP2pAuthIdentity)集合
		List<CaffP2pAuthIdentity> cais = CaffP2pAuthIdentityMapper.findCaffP2pAuthIdentityList(-1);
		for (CaffP2pAuthIdentity cai : cais) {
			if(2 == cai.getAuditstateint()){
				caiMap.put(cai.getUserid(), cai);
			}
		}
		//获取escf2.0数据  VIP表(CaffP2pVip) 集合
		List<CaffP2pVip> cvs = CaffP2pVipMapper.findCaffP2pVipList(-1);
		
		//获取escf2.0数据 借款者表（CaffP2pBorrowerData）集合
		List<CaffP2pBorrowerData> cbds = CaffP2pBorrowerDataMapper.findCaffP2pBorrowerDataList(-1);
		for (CaffP2pBorrowerData cbd : cbds) {
			cbdMap.put(cbd.getUserid(), cbd);
		}
		//获取escf2.0数据 借款者表（CaffP2pBorrowerData）集合
		List<CaffP2pUserReferrer> curs = CaffP2pUserReferrerMapper.findCaffP2pUserReferrerList(-1);
		for (CaffP2pUserReferrer cur : curs) {
			curMap.put(cur.getUserid(), cur);
		}
	}
	//系统用户表（CaffP2pUser） -> t_cust_customer
	private static PreparedStatement CaffP2pUserFill(PreparedStatement pstmt ,CaffP2pUser cu) throws SQLException{
		pstmt.setString(3, cu.getName());//customer_name	varchar(50)
		pstmt.setString(8, cu.getPassword());//password	varchar(225)
		pstmt.setString(9, cu.getTradepassword());//tradePassword	varchar(255)
		pstmt.setString(10, cu.getPhone());//phone_no	varchar(11)
		pstmt.setString(11, cu.getEmail());//email	varchar(50)
		pstmt.setLong(38, cu.getCaffp2puserid());//index_	bigInt(20)
		pstmt.setString(39, cu.getLoginerrorcount().toString());//error_count	varchar(2)
		return pstmt;
	}
	//用户详细信息表（CaffUserDetail） -> t_cust_customer
	private static PreparedStatement caffUserDetailFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffUserDetail cud = cudMap.get(userId);
		if(null != cud){
			pstmt.setString(4, cud.getRealname());//sname	varchar(50)
			pstmt.setDate(5, new Date(cud.getBirthdatelong()));//birthday	date
			pstmt.setString(6, IdCardUtils.getProvinceByIdCard(cud.getIdnumber()));//res_reg	varchar(50)
			pstmt.setString(7, IdCardUtils.getBirthByIdCard(cud.getIdnumber()));//age	varchar(10)
			pstmt.setString(12, cud.getSexint().toString());//sex	varchar(2)
			pstmt.setTimestamp(14, Timestamp.valueOf(DateUtil.format(new Date(cud.getCreateddatelong()))));//registration_time	datetime
			pstmt.setString(31, cud.getIdnumber());//identification_no	varchar(50)
			pstmt.setString(32, cud.getMarriageint()==null?"0":cud.getMarriageint().toString());//is_marriage	varchar(2)
			pstmt.setString(33, cud.getHouseaddress());//home_address	varchar(255)
			pstmt.setString(34, cud.getCurrentaddress());//now_address	varchar(255)
			pstmt.setTimestamp(35, Timestamp.valueOf(DateUtil.format(new Date(cud.getCreateddatelong()))));//create_time	datetime
		}else{
			pstmt.setNull(4, Types.VARCHAR);
			pstmt.setNull(5, Types.DATE);
			pstmt.setNull(6, Types.VARCHAR);
			pstmt.setNull(7, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(14, Types.TIMESTAMP);
			pstmt.setNull(31, Types.VARCHAR);
			pstmt.setNull(32, Types.VARCHAR);
			pstmt.setNull(33, Types.VARCHAR);
			pstmt.setNull(34, Types.VARCHAR);
			pstmt.setNull(35, Types.TIMESTAMP);
		}
		return pstmt;
	}
	//积分概况表（CaffP2pUserIntegral） -> t_cust_customer
	private static PreparedStatement CaffP2pUserIntegralFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pUserIntegral cui = cuiMap.get(userId);
		if(null != cui){
			/*积分概况表（CaffP2pUserIntegral）表计算得出 
			 * （CaffP2pUserIntegral.borrowIntegral - CaffP2pUserIntegral.borrowIntegralChecked 
			 *  +CaffP2pUserIntegral.giftIntegral - CaffP2pUserIntegral.giftIntegralChecked
			 *  +CaffP2pUserIntegral.investIntegra l- CaffP2pUserIntegral.investIntegralChecked)
			 */
			int availablePoint = cui.getBorrowintegral()-cui.getBorrowintegralchecked()
				+cui.getGiftintegral()-cui.getGiftintegralchecked()
				+cui.getInvestintegral()-cui.getInvestintegralchecked(); 
			pstmt.setInt(17, availablePoint);//available_point	int(11)
		}else{
			pstmt.setNull(17, Types.INTEGER);
		}
		return pstmt;
	}
	//黑名单用户表（CaffP2pBlackUser）-> t_cust_customer
	private static PreparedStatement CaffP2pBlackUserFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pBlackUser cbu = cbuMap.get(userId);
		if(null != cbu){
			pstmt.setString(19, "0");//is_blacklist	varchar(2)
		}else{
			pstmt.setNull(19, Types.VARCHAR);
		}
		return pstmt;
	}
	//身份认证表(CaffP2pAuthIdentity) -> t_cust_customer
	private static PreparedStatement CaffP2pAuthIdentityFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pAuthIdentity cai = caiMap.get(userId);
		if(null != cai){
			pstmt.setString(23, cai.getAuditstateint().toString());//is_attestation	varchar(2)
			pstmt.setTimestamp(26, Timestamp.valueOf(DateUtil.format(new Date(cai.getCreateddatelong()))));//attestation_time	datetime
		}else{
			pstmt.setNull(23, Types.VARCHAR);
			pstmt.setNull(26, Types.TIMESTAMP);
		}
		return pstmt;
	}
	//VIP表(CaffP2pVip) -> t_cust_customer
	private static PreparedStatement CaffP2pVipFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pVip cv = cvMap.get(userId);
		if(null != cv){
			pstmt.setString(24, cv.getStateint().toString());//is_vip	varchar(2)
			//VIP时长（CaffP2pVip.endDateLong - CaffP2pVip.beginDateLong）
			long vipTime = cv.getEnddatelong()- cv.getBegindatelong();
			pstmt.setInt(28, (int) vipTime);//vip_time	int(11)
		}else{
			pstmt.setNull(24, Types.VARCHAR);
			pstmt.setNull(28, Types.INTEGER);
		}
		return pstmt;
	}
	//借款者表（CaffP2pBorrowerData）-> t_cust_customer
	private static PreparedStatement CaffP2pBorrowerDataFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pBorrowerData cbd = cbdMap.get(userId);
		if(null != cbd){
			//1:仅借款客户2:仅投资客户3:既是借款又是投资客户
			//借款用户类型(13-普通借款者，14-系统借款者)
			int borrowerTypeInt = cbd.getBorrowertypeint();
			String borOrInvCus = "2";
			if(13==borrowerTypeInt){
				borOrInvCus = "3";
			}else if(14 == borrowerTypeInt){
				borOrInvCus = "1"; 
			}
			pstmt.setString(27, borOrInvCus);//bor_or_inv_cus	varchar(2)
		}else{
			pstmt.setNull(27, Types.VARCHAR);
		}
		return pstmt;
	}
	
	//推荐人表（CaffP2pUserReferrer）-> t_cust_customer
	private static PreparedStatement CaffP2pUserReferrerFill(PreparedStatement pstmt ,long userId) throws SQLException{
		CaffP2pUserReferrer cur = curMap.get(userId);
		if(null != cur){
			pstmt.setString(40, cur.getBitstate().toString());//referral_code	varchar(10)
		}else{
			pstmt.setNull(40, Types.VARCHAR);
		}
		return pstmt;
	}
	
	private static PreparedStatement fillSql(PreparedStatement pstmt, CaffP2pUser cu) throws SQLException{
		CusTomer c = new CusTomer();
		pstmt.setString(1, c.getPK());//PID	varchar(48)
		/**/pstmt.setString(2, "");//vip_id	varchar(48)
		pstmt.setString(13, "1");//status	varchar(2)
		
		CaffP2pUserFill(pstmt,cu);
		caffUserDetailFill(pstmt,cu.getCaffp2puserid());
		CaffP2pUserIntegralFill(pstmt,cu.getCaffp2puserid());
		CaffP2pBlackUserFill(pstmt,cu.getCaffp2puserid());
		CaffP2pAuthIdentityFill(pstmt,cu.getCaffp2puserid());
		CaffP2pVipFill(pstmt,cu.getCaffp2puserid());
		CaffP2pBorrowerDataFill(pstmt,cu.getCaffp2puserid());
		CaffP2pUserReferrerFill(pstmt,cu.getCaffp2puserid());
		
		/*id类型不同*/pstmt.setString(15, "");//referee_user	varchar(48)
		/*id类型不同*/pstmt.setString(16, "");//customer_service_user	varchar(48)
		
		pstmt.setInt(18, 0);//empirical_value	int(11)
		pstmt.setString(20, "1");//is_forbid_withdraw	varchar(2)
		pstmt.setString(21, "1");//is_forbid_transfer	varchar(2)
		pstmt.setString(22, "0");//is_freeze	varchar(2)
		
		pstmt.setString(25, "1");//is_invest	varchar(2)
		
		pstmt.setString(29, "");//attestation_status	varchar(2)
		pstmt.setString(30, "");//is_authentication	varchar(2)
		
		pstmt.setString(36, "");//province	varchar(20)
		pstmt.setString(37, "");//city	varchar(20)
		
		return pstmt;
	}

	/**
	 * Description：<br> 
	 * 批量新增客户	批处理执行预处理SQL
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
		//系统用户表（CaffP2pUser）集合
		List<CaffP2pUser> cus = CaffP2pUserMapper.findCaffp2pUserList();
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


