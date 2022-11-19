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
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.dao.business.BizBorrowCondRelMapper;
import com.yscf.core.dao.business.BizBorrowInfoVOMapper;
import com.yscf.core.dao.business.BizFinanceProductsMapper;
import com.yscf.core.model.business.BizBorrowCondRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.system.ISysCreateCodeRuleService;

/**
 * Description：<br>
 * TODO
 * 
 * @author Yu.Zhang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@Service("bizBorrowInfoVOService")
public class BizBorrowInfoVOServiceImpl extends BaseService<BaseEntity, String>
		implements IBizBorrowInfoVOService {

	private Logger logger = LoggerFactory
			.getLogger(BizBorrowInfoVOServiceImpl.class);

	@Autowired
	public BizBorrowInfoVOServiceImpl(BizBorrowInfoVOMapper dao) {
		super(dao);
	}

	@Resource(name = "bizFinanceProductsMapper")
	private BizFinanceProductsMapper bizFinanceProductsMapper;

	@Resource(name = "bizBorrowCondRelMapper")
	private BizBorrowCondRelMapper borrowCondRelMapper;

	@Resource(name = "sysCreateCodeRuleService")
	private ISysCreateCodeRuleService sysCreateCodeRuleService;

	@Resource
	private IBizRepaymentPlanService bizRepaymentPlanService;

	@Resource(name = "bizBorrowInfoVOMapper")
	private BizBorrowInfoVOMapper borrowInfoMapper;

	@Resource
	private IBizBorrowService bizBorrowService;

	@Override
	public PageList<BizBorrowInfoVO> selectAllPage(BizBorrowInfoVO bizBorrow,
			PageInfo info) {

		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.searchAllPage(bizBorrow, info);
	}

	@Override
	public List<BizBorrowInfoVO> selectAll(BizBorrowInfoVO bizBorrow) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.selectAll(bizBorrow);
	}

	@Override
	public List<BizBorrowInfoVO> getBorrowApplyTimeIsNull(BizBorrowInfoVO borrow) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.getBorrowApplyTimeIsNull(borrow);
	}

	@Override
	public BigDecimal getSumborrowMoneyByApparoveNode(BizBorrowInfoVO borrow) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.getSumborrowMoneyByApparoveNode(borrow);
	}

	@Override
	public PageList<BizBorrowInfoVO> searchAllPage(BizBorrowInfoVO bizBorrow,
			PageInfo info) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.searchAllPage(bizBorrow, info);
	}

	@Override
	public BizBorrowInfoVO getBizBorrowById(String pid) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public int getApproverCount(String approveNode) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		BizBorrowInfoVO vo = new BizBorrowInfoVO();
		vo.setApproveNode(approveNode);
		return mapper.getApproverCount(vo);
	}

	@Override
	public void savePrimaryKeySelective(BizBorrowInfoVO infoVO)
			throws FrameworkException {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		// 标记为理财产品
		infoVO.setBorrowType("3");
		// 理财产品品为有效
		infoVO.setStatus("1");
		// 未投金额为借款金额
		infoVO.setSurplusMoney(infoVO.getBorrowMoney());
		infoVO.setBorrowProgress(BigDecimal.ZERO);// 完成进度
		infoVO.setAlreadyMoney(BigDecimal.ZERO);// 已投金额
		// 保存主表
		if (StringUtil.isBlank(infoVO.getPid()) || "1".equals(infoVO.getPid())) {
			infoVO.setPid(infoVO.getPK());
			infoVO.setBorrowCode(sysCreateCodeRuleService.generatLabelNoRule(
					"C", "8", infoVO.getCustomerId()));
			mapper.savePrimaryKeySelective(infoVO);
		} else {
			mapper.updateByPrimaryKey(infoVO);
		}

		// 保存条件关系表
		if (!StringUtil.isBlank(infoVO.getCondIds())) {
			// 先删除条件表对应信息
			borrowCondRelMapper.deleteByBorrowId(infoVO.getPid());
			String condIds = infoVO.getCondIds();
			String[] condId = condIds.split(",");
			// 循环添加
			for (int i = 0; i < condId.length; i++) {
				// 创建理财参与条件关系对象
				BizBorrowCondRel bizBorrowCondRel = new BizBorrowCondRel();
				// 赋值
				bizBorrowCondRel.setPid(bizBorrowCondRel.getPK());// 设置PID
				bizBorrowCondRel.setBorrowId(infoVO.getPid());// 赋值ID
				bizBorrowCondRel.setCondId(condId[i]);// 设置条件ID
				bizBorrowCondRel.setStatus("1");// 设置状态为1:表示正常
				bizBorrowCondRel.setCreateUser(infoVO.getCreateUser());// 设置创建人
				bizBorrowCondRel.setCreateTime(DateUtil.getToday());// 设置创建时间
				// 新增理财参与条件信息
				borrowCondRelMapper.insertSelective(bizBorrowCondRel);
			}
		}

	}

	@Override
	public void deleteBorrowManageByPid(String pid) throws FrameworkException {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		mapper.deleteBorrowManageByPid(pid);
	}

	@Override
	public void updateByPrimaryKey(BizBorrowInfoVO products)
			throws FrameworkException {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		mapper.updateByPrimaryKey(products);
	}

	@Override
	public void saveOrUpdateNewStandardAndFinanceProducts(
			BizBorrowInfoVO borrow, BizFinanceProducts financeProducts)
			throws FrameworkException {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		// 理财产品品为有效
		borrow.setStatus("1");
		// 保存主表
		if (StringUtil.isBlank(borrow.getPid()) || "1".equals(borrow.getPid())
				|| "-1".equals(borrow.getPid())) {
			borrow.setPid(borrow.getPK());

			financeProducts.setBorrowId(borrow.getPid());
			financeProducts.setPid(financeProducts.getPK());

			if (Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())) {
				borrow.setBorrowCode(sysCreateCodeRuleService
						.generatLabelNoRule("X", "8", borrow.getCreateUser()));
			} else if (Constant.BORROW_TYPE_5.equals(borrow.getBorrowType())) {
				borrow.setBorrowCode(sysCreateCodeRuleService
						.generatLabelNoRule("T", "8", borrow.getCreateUser()));
			}
			mapper.savePrimaryKeySelective(borrow);
			bizFinanceProductsMapper.insert(financeProducts);
		} else {
			mapper.updateByPrimaryKey(borrow);
			bizFinanceProductsMapper.updateByPrimaryKey(financeProducts);
		}

		// 先删除条件表对应信息
		borrowCondRelMapper.deleteByBorrowId(borrow.getPid());
		// 保存条件关系表
		if (!StringUtil.isBlank(borrow.getCondIds())) {
			String condIds = borrow.getCondIds();
			String[] condId = condIds.split(",");
			// 循环添加
			for (int i = 0; i < condId.length; i++) {
				if (!StringUtils.hasLength(condId[i])) {
					continue;
				}
				// 创建理财参与条件关系对象
				BizBorrowCondRel bizBorrowCondRel = new BizBorrowCondRel();
				// 赋值
				bizBorrowCondRel.setPid(bizBorrowCondRel.getPK());// 设置PID
				bizBorrowCondRel.setBorrowId(borrow.getPid());// 赋值ID
				bizBorrowCondRel.setCondId(condId[i]);// 设置条件ID
				bizBorrowCondRel.setStatus("1");// 设置状态为1:表示正常
				bizBorrowCondRel.setCreateUser(borrow.getCreateUser());// 设置创建人
				bizBorrowCondRel.setCreateTime(DateUtil.getToday());// 设置创建时间
				// 新增理财参与条件信息
				borrowCondRelMapper.insertSelective(bizBorrowCondRel);
			}
		}

	}

	@Override
	public BigDecimal getSumborrowMoneyByWhere(BizBorrowInfoVO borrow) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		return mapper.getSumborrowMoneyByWhere(borrow);
	}

	@Override
	public int getReleaseCount(String approveNode) throws FrameworkException {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		BizBorrowInfoVO vo = new BizBorrowInfoVO();
		vo.setApproveNode(approveNode);
		vo.setBorrowReleaseStatu("borrowRelease");
		return mapper.getReleaseCount(vo);
	}

	@Override
	public BizBorrowInfoVO getApproverCountByBorrowType(BizBorrowInfoVO vo) {
		BizBorrowInfoVO result = new BizBorrowInfoVO();
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();

		Integer approveCount = mapper.getApproverCountByBorrowType(vo);
		Integer approveViaCount = mapper.getApproverViaCountByBorrowType(vo);
		result.setApproveCount(approveCount);
		result.setApproveViaCount(approveViaCount);

		if (approveViaCount > 0) {
			// 计算 申请通过率
			BigDecimal big = ArithmeticUtil.div(approveCount.toString(),
					approveViaCount.toString(), 4);
			result.setApproveViaRate(big.multiply(new BigDecimal("100")));
		} else {
			result.setApproveViaRate(BigDecimal.valueOf(0));
		}

		return result;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_招标中
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 我的招标中的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:42:23
	 */
	@Override
	public List<BizBorrowInfoVO> selectReceptionTender(
			BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		List<BizBorrowInfoVO> list = new ArrayList<BizBorrowInfoVO>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizBorrowInfoVO.getCustomerId());
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectReceptionTender(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_招标中：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_招标中的总数
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:46:27
	 */
	@Override
	public Integer selectReceptionTenderCount(BizBorrowInfoVO bizBorrowInfoVO) {
		Integer count = 0;
		try {
			BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizBorrowInfoVO.getCustomerId());
			// 获取总数
			count = mapper.selectReceptionTenderCount(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_招标中的总数：", e.getMessage());
			}
		}
		return count;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 我的申请进度的列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:42:46
	 */
	@Override
	public List<BizBorrowInfoVO> selectReceptionApplication(
			BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize) {
		BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
		List<BizBorrowInfoVO> list = new ArrayList<BizBorrowInfoVO>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizBorrowInfoVO.getCustomerId());
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectReceptionApplication(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_申请进度：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度的总数
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:46:32
	 */
	@Override
	public Integer selectReceptionApplicationCount(
			BizBorrowInfoVO bizBorrowInfoVO) {
		Integer count = 0;
		try {
			BizBorrowInfoVOMapper mapper = (BizBorrowInfoVOMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizBorrowInfoVO.getCustomerId());
			// 获取总数
			count = mapper.selectReceptionApplicationCount(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_申请进度的总数：", e.getMessage());
			}
		}
		return count;
	}

	@Override
	public List<BizBorrowInfoVO> selectAppRecommendProjects() {
		List<BizBorrowInfoVO> resultList = new ArrayList<BizBorrowInfoVO>();
		// 查询 e计划 进行中的标的
		List<BizBorrowInfoVO> temp = borrowInfoMapper
				.selectAppRecommendProjectsEplanBidding();
		// 没有进行中的e计划，查询已结束的e计划
		if (temp == null || temp.size() <= 0) {
			temp = borrowInfoMapper.selectAppRecommendProjectsEplanBidding();
		}
		// 先查询 进行中的e计划 再查询已结束的e计划
		if (temp != null && temp.size() > 0) {
			resultList.add(temp.get(0));
			temp = new ArrayList<BizBorrowInfoVO>();
		}
		// 查询进行中的 e首房 e抵押数据
		temp = borrowInfoMapper.selectAppRecommendProjectsEmortgageBidding();
		// 没有进行中的e首房 e抵押，查询已结束的e首房 e抵押
		if (temp == null || temp.size() <= 0) {
			temp = borrowInfoMapper.selectAppRecommendProjectsEplanEnd();
		}

		if (temp != null && temp.size() > 0) {
			for (BizBorrowInfoVO bizBorrowInfoVO : temp) {
				resultList.add(bizBorrowInfoVO);
			}
		}
		return resultList;
	}
}
