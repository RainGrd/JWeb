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
 * 2015年9月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrowInfoVO;

/**
 * Description：<br>
 * 借款申请VO
 * 
 * @author Yu.Zhang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@MapperScan("bizBorrowInfoVOMapper")
public interface BizBorrowInfoVOMapper extends IBaseDao<BaseEntity, String> {

	PageList<BizBorrowInfoVO> selectAllPage(
			@Param("map") BizBorrowInfoVO bizBorrow, PageInfo info);

	PageList<BizBorrowInfoVO> searchAllPage(
			@Param("map") BizBorrowInfoVO bizBorrow, PageInfo info);

	List<BizBorrowInfoVO> selectPublishedFinData(
			@Param("map") BizBorrowInfoVO bizBorrow,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize,
			@Param("sortType") String sortType,
			@Param("sortModel") String sortModel);

	PageList<BizBorrowInfoVO> selectFinProdData(
			@Param("map") BizBorrowInfoVO bizBorrow,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);

	List<BizBorrowInfoVO> selectAll(BizBorrowInfoVO bizBorrow);

	List<BizBorrowInfoVO> getBorrowApplyTimeIsNull(BizBorrowInfoVO borrow);

	BigDecimal getSumborrowMoneyByWhere(BizBorrowInfoVO borrow);

	BigDecimal getSumborrowMoneyByApparoveNode(BizBorrowInfoVO borrow);

	public BizBorrowInfoVO selectByPrimaryKey(String pid);

	/**
	 * 
	 * Description：<br>
	 * 获取申请数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param vo
	 * @return
	 */
	public int getApproverCount(BizBorrowInfoVO vo);

	public int getReleaseCount(BizBorrowInfoVO vo);

	public void savePrimaryKeySelective(BizBorrowInfoVO infoVO);

	public void deleteBorrowManageByPid(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据借款类型获取申请总数类型为空，则查询所有申请
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param vo
	 * @return
	 */
	public int getApproverCountByBorrowType(BizBorrowInfoVO vo);

	/**
	 * 
	 * Description：<br>
	 * 根据借款类型获取申请通过数 类型为空，则查询所有申请
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param vo
	 * @return
	 */
	public int getApproverViaCountByBorrowType(BizBorrowInfoVO vo);

	/**
	 * 
	 * @Description : 前台_我的借款_招标中
	 * @param map
	 *            条件map
	 * @return 我的招标中的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:37:47
	 */
	List<BizBorrowInfoVO> selectReceptionTender(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_招标中的总数
	 * @param map
	 *            条件map
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 上午11:30:58
	 */
	int selectReceptionTenderCount(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度
	 * @param map
	 *            条件map
	 * @return 我的申请进度的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:38:30
	 */
	List<BizBorrowInfoVO> selectReceptionApplication(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度的总数
	 * @param map
	 *            条件map
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 上午11:30:07
	 */
	int selectReceptionApplicationCount(Map<String, Object> map);

	/**
	 * 
	 * Description：<br>
	 * 查询总条数
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月20日
	 * @version v1.0.0
	 * @param borrow
	 *            查询条件
	 * @return
	 */
	public Integer selectFinProdCountPage(BizBorrowInfoVO borrow);

	/**
	 * Description：<br>
	 * APP 端推荐e计划 招标中 项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAppRecommendProjectsEplanBidding();

	/**
	 * Description：<br>
	 * APP 端推荐e计划 已结束 项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAppRecommendProjectsEplanEnd();

	/**
	 * Description：<br>
	 * APP 端推荐e首房e抵押 招标中 项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAppRecommendProjectsEmortgageBidding();

	/**
	 * Description：<br>
	 * APP 端推荐e首房e抵押 已结束 项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowInfoVO> selectAppRecommendProjectsEmortgageEnd();
}
