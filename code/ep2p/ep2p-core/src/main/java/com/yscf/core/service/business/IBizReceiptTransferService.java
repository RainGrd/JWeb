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
 * 2015年10月20日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.Date;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferCenterModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferFrontModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;


/**
 * 
 * Description：<br> 
 * 债权转让Service接口
 * @author  JunJie.Liu
 * @date    2015年10月23日
 * @version v1.0.0
 */
public interface IBizReceiptTransferService {

	BizReceiptTransfer getBizReceiptTransferById(String pid);

	void updateByPrimaryKeySelective(BizReceiptTransfer receiptTransf);
	
	/**
	 * 
	 * Description：<br> 
	 * 统计债权转让发布数量
	 * @author  JunJie.Liu
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param date  
	 * 			截止时间，如：传当前时间即可
	 * @param days	
	 * 			截止时间往前推移天数，如：15天即为15即可
	 * @return
	 */
	public Integer selectCountByCreateTime(Date date,int days);
	
	/**
	 * 
	 * Description：<br> 
	 * 前台查询债权转让列表
	 * @author  JunJie.Liu
	 * @date    2015年11月10日
	 * @version v1.0.0
	 * @param condition 
	 * 			条件
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	public List<BizReceiptTransferFrontModel> findTransferList(BizReceiptTransferFrontModel condition, Integer pageIndex, Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 前台查询债权转让列表总条数
	 * @author  JunJie.Liu
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param condition
	 * @return
	 */
	public Integer findTransferCount(BizReceiptTransferFrontModel condition);
	
	/**
	 * 
	 * Description：<br> 
	 * 插入债权转让记录
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param brt
	 * @return
	 */
	public int insert(BizReceiptTransfer brt);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询债权购买详情
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public BizReceiptTransferModel getByPid(String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 购买债权<br>
	 * 
	 * 购买债权，购买人扣费，转让人收费，收费的时候，扣除转让手续费，记录变为已转让。<br>
	 * 产生新收款记录，并且新增，持有人为购买人。<br>
	 * 更新购买人积分，以及购买人的交易时间。<br>
	 * 
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param pid
	 * @param userId
	 */
	public void buyTransfer(String pid, String userId,String investmentType) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 债权撤销
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param receiptTransfer
	 * 			债权id
	 * @param userId 
	 * 			
	 */
	public void revokeReceiptTransfer(BizReceiptTransfer receiptTransfer, String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询个人中心债权转让管理
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param condition
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @param string
	 * 			1.持有中
	 * 			2.转让中
	 * 			3.已转让
	 * @return
	 */
	public List<BizReceiptTransferCenterModel> findTrasferCenterVos(
			BizReceiptTransferCenterModel condition, String userId,
			Integer pageIndex, Integer pageSize, String string);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询个人中心债权转让管理总条数
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param condition
	 * @param string
	 * 			1.持有中
	 * 			2.转让中
	 * 			3.已转让
	 * @param flag 
	 * @return
	 */
	public Integer findTrasferCenterVosCount(BizReceiptTransferCenterModel condition,
			String userId, String flag);

	/**
	 * 
	 * Description：<br> 
	 * 执行债权转让定时器<br>
	 * 获取所有转让中的债权，过期下架<br>
	 * @author  JunJie.Liu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 */
	public void executeTimer();
	
	/**
	 * 
	 * Description：<br> 
	 * 获取
	 * @author  JunJie.Liu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param rePid
	 * @return
	 */
	public BizReceiptTransfer getCustomerIdByReceiptPlanId(String rePid);
	
	/**
	 * 
	 * Description：<br> 
	 * 债权转让列表【API】
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param type
	 * @param desc
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<BizReceiptTransferFrontModel> findTransferList(String type,
			boolean desc, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 债权转让列表条数【API】
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @return
	 */
	public Integer findTransferListByApiCount();

	PageList<BizReceiptTransferModel> selectAllPage(
			BizReceiptTransferModel bizReceiptTransferVo, PageInfo info);

	BizReceiptTransferModel sumBizReceiptTransferVoWhere(
			BizReceiptTransferModel bizReceiptTransferVo);

	List<BizReceiptTransferModel> selectBizReceiptTransferVOEom(
			BizReceiptTransferModel bizReceiptTransferVO, ExportObjectModel eom);

	void soldOutByTransferId(String transferId, String des);
	
	
}
