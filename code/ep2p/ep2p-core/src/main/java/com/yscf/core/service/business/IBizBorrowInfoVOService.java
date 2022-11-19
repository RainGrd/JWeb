/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizFinanceProducts;

/**
 * Description：<br>
 * 借款VO业务类服务接口
 * 
 * @author Simon.Hoo
 * @date 2015年9月6日
 * @version v1.0.0
 */
public interface IBizBorrowInfoVOService {

	/**
	 * 
	 * Description：<br>
	 * 分页查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @param info
	 * @return
	 */
	public PageList<BizBorrowInfoVO> selectAllPage(BizBorrowInfoVO bizBorrow,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 借款数据查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param dis
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAll(BizBorrowInfoVO bizBorrow);

	/**
	 * 
	 * Description：<br>
	 * 根据客户ID,借款类型 查询 借款申请时间为空的数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param borrow
	 * @return
	 */
	public List<BizBorrowInfoVO> getBorrowApplyTimeIsNull(BizBorrowInfoVO borrow);

	/**
	 * 
	 * Description：<br>
	 * 根据审批节点获取借款金额合计
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @param approveNode
	 * @return
	 */
	public BigDecimal getSumborrowMoneyByApparoveNode(BizBorrowInfoVO borrow);

	/**
	 * 
	 * Description：<br>
	 * 分页查询所有有效的借款项目
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param info
	 * @return
	 */
	public PageList<BizBorrowInfoVO> searchAllPage(BizBorrowInfoVO bizBorrow,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 通过Id获得借款信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public BizBorrowInfoVO getBizBorrowById(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据审批节点获取待审批条数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param approveNode
	 *            1 担保初审 2 借前审批
	 * @return
	 */
	public int getApproverCount(String approveNode) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 保存或修改BizBorrowInfoVO
	 * 
	 * @author fengshiliang
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param infoVO
	 * @throws FrameworkException
	 */
	public void savePrimaryKeySelective(BizBorrowInfoVO infoVO)
			throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 根据pid 把借款信息表记录 status 设置为-1
	 * 
	 * @author fengshiliang
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param pid
	 * @throws FrameworkException
	 */
	public void deleteBorrowManageByPid(String pid) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 根据id 修改理财产品
	 * 
	 * @author fengshiliang
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param borrowInfo
	 * @throws FrameworkException
	 */
	public void updateByPrimaryKey(BizBorrowInfoVO borrowInfo)
			throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 保存或更新新手标体验标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param borrow
	 * @param financeProducts
	 */
	public void saveOrUpdateNewStandardAndFinanceProducts(
			BizBorrowInfoVO borrow, BizFinanceProducts financeProducts)
			throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 通过条件查询合计金额
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param borrow
	 * @return
	 */
	public BigDecimal getSumborrowMoneyByWhere(BizBorrowInfoVO borrow);

	/**
	 * 
	 * Description：<br>
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月29日
	 * @version v1.0.0
	 * @param approveNode
	 * @return
	 * @throws FrameworkException
	 */
	public int getReleaseCount(String approveNode) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 
	 * 根据借款类型获取申请总数与申请通过数
	 * 
	 * 类型为空，则查询所有申请
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param object
	 * @return
	 */
	public BizBorrowInfoVO getApproverCountByBorrowType(BizBorrowInfoVO object);

	/**
	 * 
	 * @Description : 前台_我的借款_招标中
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 我的招标中的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:47:47
	 */
	List<BizBorrowInfoVO> selectReceptionTender(
			BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 前台_我的借款_招标中的总数
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:45:42
	 */
	Integer selectReceptionTenderCount(BizBorrowInfoVO bizBorrowInfoVO);

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 我的申请进度的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:58:30
	 */
	List<BizBorrowInfoVO> selectReceptionApplication(
			BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度的总数
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:46:03
	 */
	Integer selectReceptionApplicationCount(BizBorrowInfoVO bizBorrowInfoVO);

	/**
	 * Description：<br>
	 * APP 端推荐项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAppRecommendProjects();

}
