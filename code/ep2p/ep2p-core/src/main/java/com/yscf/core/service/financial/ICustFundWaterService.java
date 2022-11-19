/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 客户资金流水管理服务接口
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.ptp.financial.CustFundWaterModel;

/**
 * Description：<br> 
 * 客户资金流水管理服务接口
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface ICustFundWaterService {
	
	/**
	 * Description：查询客户资金流水列表
	 * @author  Allen
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param custFundWater 系统客户资金流水
	 * @return List<CustFundWater>
	 * @throws FrameworkException
	 */
	List<CustFundWater> selectAll(CustFundWater custFundWater, PageInfo pageInfo);
	
	/**
	 * Description：查询客户资金流水列表，带分页功能
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param CustFundWater custFundWater 系统客户资金流水
	 * @return PageList<CustFundWater>
	 * @throws FrameworkException
	 */
	PageList<CustFundWater> selectAllPage(HashMap<String,Object> parasMap, PageInfo info) ;
	
	/**
	 * 查询所有数据的总计数据
	 * Description：<br> 
	 * TODO
	 * @author  Allen
	 * @date    2015年10月10日
	 * @version v1.0.0
	 * @param parasMap
	 * @return
	 */
	List<CustFundWater> getTotalData(HashMap<String,Object> parasMap);
	/**
	 * Description：根据客户id查询客户账户总额明细
	 * @author  heng.wang
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustFundWater> selectAccountTotalDetailsById(String pid,String flag,PageInfo info);
	/**
	 * Description：查询客户账户总额明细列表
	 * @author  heng.wang
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CustFundWater> selectAllPages(CustFundWater custFundWater, PageInfo pageInfo);
	
	/**
	 * Description：根据客户id查询客户投资总额明细
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustFundWater> selectTouziDetailsById(String pid,PageInfo info);
	/**
	 * Description：查询资金流水类型
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustFundWater> selectWaterType(CustFundWater custFundWater,PageInfo info);
	/**
	 * Description：查询资金流水详细
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustFundWater> selectZiJinWater(String pid,PageInfo info);
	/**
	 * Description：根据条件查询资金流水
	 * @author  heng.wang
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustFundWater> selectZiJinWaterAllPage(CustFundWater custFundWater,PageInfo info);
	
	/**
	 * 
	 * Description：<br> 
	 * 导出客户明细列表
	 * @author  JunJie.Liu
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param custFundWaterVO
	 * @param eom
	 * @return
	 */
	List<CustFundWaterModel> selectCustFundWaterVOEom(
			CustFundWaterModel custFundWaterVO, ExportObjectModel eom);
	/**
	 * 
	 * Description：<br> 
	 * 前台：查资金流水
	 * @author  heng.wang
	 * @date    2015年12月16日
	 * @version v1.0.0
	 * @param custFundWater
	 * @param eom
	 * @return
	 */
	public List<CustFundWater> selectZiJinWater(HashMap<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 添加一个资金流水，并且需要进行事物控制
	 * @author  Jie.Zou
	 * @date    2015年12月12日
	 * @version v1.0.0
	 * @param userId 用户ID
	 * @param fundWaterType 流水类型
	 * @param amount 发生额度
	 * @param balance 发生流水后账户余额
	 * @param note 流水备注
	 * @param time 发生时间
	 * @return
	 */
	CustFundWater addFundWater(String userId,String fundWaterType,String tradeType,BigDecimal amount,BigDecimal balance,String note,Date time,String fk) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * api：查资金流水
	 * @author  heng.wang
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param custFundWater
	 * @param userId
	 * @return
	 */
	public List<CustFundWater> selectCapitalFlow(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量添加多个资金流水
	 * @author  Jie.Zou
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param userids
	 * @param fundWateType
	 * @param tradeType
	 * @param amount
	 * @param balance
	 * @param note
	 * @param time
	 * @param fk
	 * @return
	 */
	List<CustFundWater> addFundWaters(List<String> userIds,String fundWateType,String tradeType,BigDecimal amount,String note,Date time,String fk);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量添加多个资金流水
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @return
	 */
	List<CustFundWater> addFundWaters(Map<String, Object> maps,String fundWateType,String fundtallyType,String note,Date time,String fk);
}


