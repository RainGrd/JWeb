/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体现管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.ptp.financial.BizRechargeOnlineModel;


/**
 * Description：线上充值业务类接口
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
public interface IBizRechargeOnlineService {
	
	/**
     * Description：查询线上充值列表数据  分页
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizRechargeOnline）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizRechargeOnline> 
     */
	PageList<BizRechargeOnline> selectOnlineRechargePage(
			BizRechargeOnline bizRechargeOnline, PageInfo info)
			throws FrameworkException;

	/**
	 * Description：补单
	 * @author  JingYu.Dai
	 * @date    2015年10月8日
	 * @version v1.0.0
	 * @param bizRechargeOnline
	 * @return int 受影响的行数
	 */
	int replacementOrder(BizRechargeOnline bizRechargeOnline);
	
	/**
	 * 
	 * Description：<br> 
	 * 线上充值客户列表导出
	 * @author  JunJie.Liu
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizRechargeOnline
	 * @param eom
	 * @return
	 */
	List<BizRechargeOnlineModel> selectBizRechargeOnlineEom(
			BizRechargeOnline bizRechargeOnline, ExportObjectModel eom);
	
	 /**
	  * 
	  * Description：<br> 
	  * 系统任务补充执行入账操作，这个入账操作跟手动补单操作不同
	  * 手动补单是支付成功的前提下，系统完成没有收到支付平台的通知的情况，有财务人员执行
	  * 系统任务入账是支付成功，并且系统也收到了支付平台的通知，但是执行入账时发送错误的情况下
	  * 后续有系统来完成资金入账操作
	  * 每次任务单词入账的最大个数为20
	  * @author  Jie.Zou
	  * @date    2015年12月25日
	  * @version v1.0.0
	  */
	 void doRechargeByTask();
	
	/**
	 * 
	 * Description：<br> 
	 * 新增线上充值记录
	 * @author  Jie.Zou
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param userId 客户ID
	 * @param amount 充值金额
	 * @param payConfigId 充值接口对象
	 * @param bankCard 银行卡卡号
	 */
	BizRechargeOnline addRechargeOnline(String userId, BigDecimal amount,String payConfigId,String bankCard);
}


