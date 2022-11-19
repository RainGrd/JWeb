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
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.dao.business.BizReceiptTransferMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.exception.ConditionExcepiton;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferCenterModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferFrontModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.ISysVipinfoService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.service.user.ICustPoinWaterService;

/**
 * 
 * Description：<br>
 * 债权转让ServiceImpl
 * 
 * @author JunJie.Liu
 * @date 2015年10月23日
 * @version v1.0.0
 */
@Service("bizReceiptTransferService")
public class BizReceiptTransferServiceImpl extends
		BaseService<BaseEntity, String> implements IBizReceiptTransferService {
	
	private Logger logger = LoggerFactory.getLogger(BizReceiptTransferServiceImpl.class);
	
	@Autowired
	public BizReceiptTransferServiceImpl(BizReceiptTransferMapper dao) {
		super(dao);
	}

	@Resource(name = "bizReceiptTransferMapper")
	private BizReceiptTransferMapper mapper;

	@Resource(name = "customerAccountService")
	private ICustomerAccountService customerAccountService;

	@Resource(name = "bizReceiptPlanService")
	private IBizReceiptPlanService bizReceiptPlanService;
	
	@Resource(name = "sysParamsService")
	private ISysParamsService sysParamsService;
	
	@Resource
	private ICustPoinWaterService custPoinWaterServiceImpl;
	
	@Resource
	private ISysAccountService sysAccountService;
	
	@Resource
	private IBizBorrowService bizBorrowService;
	
	@Resource
	private ICusTomerService cusTomerService;
	
	@Resource
	private CusTomerMapper cusTomerMapper;
	
	@Resource
	private ISysVipinfoService sysVipInfoService;
	
	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;
	
	
	@Override
	public BizReceiptTransfer getBizReceiptTransferById(String pid) {
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public void updateByPrimaryKeySelective(BizReceiptTransfer receiptTransf) {
		mapper.updateByPrimaryKey(receiptTransf);
	}

	@Override
	public Integer selectCountByCreateTime(Date date, int days) {
		if (null == date) {
			date = new Date();
		}
		// 将时间转换为 yyyy-MM-dd 00:00:00格式
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -days);
		Integer count = mapper.selectCountByCreateTime(
				DateUtil.format(c.getTime(), "yyyy-MM-dd 00:00:00"),
				Constant.BIZ_TRANSFER_STATUS_1);
		return count;
	}

	@Override
	public List<BizReceiptTransferFrontModel> findTransferList(
			BizReceiptTransferFrontModel condition, Integer pageIndex,
			Integer pageSize) {

		if (pageIndex == null || pageSize == null) {
			pageIndex = 0;
			pageSize = 10;
		} else {
			pageIndex = pageIndex * pageSize;
		}

		return mapper.findTransferList(condition, pageIndex, pageSize);
	}

	@Override
	public Integer findTransferCount(BizReceiptTransferFrontModel condition) {

		return mapper.findTransferCount(condition);
	}

	@Override
	public int insert(BizReceiptTransfer brt) {
		return mapper.insert(brt);
	}

	@Override
	public BizReceiptTransferModel getByPid(String pid) {

		return mapper.getByPid(pid);
	}


	@Override
	public void buyTransfer(String pid, String userId,String investmentType) throws FrameworkException {

		// 查询转让记录
		BizReceiptTransfer brt = mapper.selectByPrimaryKey(pid);
		// 判断其状态是否为转让中 ,余额不足，抛出余额不足异常编码
		if (brt  == null ) {
			// 数据异常
			throw new RuntimeException("转让记录为null");
		}
		// 判断用户是否逾期
		if(cusTomerService.isOverdue(userId)){
			throw new RuntimeException("有逾期记录，操作冻结");
		}
		BizBorrow  borrow = bizBorrowService.getBizBorrowById(brt.getBorrowId());
		

		if(borrow==null){
			throw new RuntimeException("标的不存在");
		}
		if(userId.equals(brt.getCreateUser())){
			// 状态不是转让中
			throw new RuntimeException("不可购买用户本身转让的债权！");
		}
		
		if(!Constant.BIZ_TRANSFER_STATUS_1.equals(brt.getStatus())){
			// 状态不是转让中
			throw new RuntimeException("状态已经改变");
		}
		// 求出转让手续费
		BigDecimal transferFee = brt.getFee() == null ? BigDecimal.ZERO : brt.getFee();
		
		// 购买人减钱
		// 购买债权备注
		String des1 = brt.getTransferCode()+" "+borrow.getBorrowCode()+" "+borrow.getBorrowName();
		customerAccountService.updateDeductedAvailableAmount(brt.getSuccessAmount(), userId, borrow.getPid() , TradeTypeConstant.OTHER_TYPE_518, des1);
		
		// 转让人加钱
		customerAccountService.updateAddAvailableAmount(brt.getSuccessAmount(), brt.getCreateUser(), borrow.getPid(), TradeTypeConstant.RETURNS_TYPE_413, des1);
	
		if(transferFee.compareTo(BigDecimal.ZERO) > 0){
			// 转让人减去债权转让手续费
			String des3 = des1 + ",手续费"+transferFee.setScale(2, BigDecimal.ROUND_DOWN)+"元";
			String des4 = "债权总价："+brt.getProjectValue().setScale(2, BigDecimal.ROUND_DOWN)+des1;
			customerAccountService.updateDeductedAvailableAmount(transferFee, brt.getCreateUser(), borrow.getPid(), TradeTypeConstant.OTHER_TYPE_516, des3);
			//　手续费给系统
			sysAccountService.updateAddSystemAmount(transferFee, brt.getCreateUser(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1009, borrow.getPid(),des4);
		}

		// 修改转让记录状态为已转让
		brt.setStatus(Constant.BIZ_TRANSFER_STATUS_2);
		// 新债权人
		brt.setCustomerId(userId);
		brt.setSuccessTime(new Date());
		brt.setLastUpdateTime(new Date());
		
		// 更新转让记录
		int rt = mapper.updateByPrimaryKey(brt);
		if(rt<=0){
			throw new UpdateChangeVersionException("购买债权，更新债权转让记录时失败");
		}
		
		// 将记录改成已转让
		
		int r = -1;
		if(StringUtils.isNotBlank(brt.getParentTransferId())){
			// 否则是债权再次转让
			r = bizReceiptPlanService.updateByTransfer(brt.getParentTransferId(),Constant.INVEST_SRC_TYPE_2,Constant.BIZ_RECEIPTPLAN_STATUS_4);
		}else{
			// 如果父id为null，表示原标转让
			r = bizReceiptPlanService.updateByTransfer(brt.getPid(),Constant.INVEST_SRC_TYPE_1,Constant.BIZ_RECEIPTPLAN_STATUS_4);
		}
			
		if(r < 0){
			throw new UpdateChangeVersionException("购买债权，更新转让人持有的收款计划变为已转让");
		}
		BigDecimal vipFee = sysVipInfoService.selectFeeByUserId(userId);
		if(vipFee == null){
			vipFee = new BigDecimal(0.1);
		}
		// 产生新收款记录,并添加
		List<BizReceiptPlan> list = this.addNewPlan(brt,userId,vipFee);
		
		// 批量新增
		if(list==null || list.size() <1){
			throw new RuntimeException("新收款记录失败");
		}
		bizReceiptPlanService.insertBatch(list);
		
		// 产生新的投资记录
		BizBorrowDetail bbd = new BizBorrowDetail();
		
		bbd.setPid(bbd.getPK());
		bbd.setBorrowId(brt.getBorrowId());
		bbd.setCreateTime(DateUtil.format(new Date()));
		bbd.setCreateUser(userId);
		bbd.setCustomerId(userId);
		// bbd.setVipinfoId(vipinfoId);
		bbd.setTransferId(brt.getPid());
		bbd.setInvestType(Constant.INVEST_SRC_TYPE_2);
		bbd.setInvestmentType(investmentType);
		bbd.setInvestmentTime(DateUtil.format(new Date()));
		bbd.setManagerRate(vipFee);
		bbd.setInvestmentAmount(brt.getSuccessAmount());
		bbd.setStatus("1");
		bbd.setBorDetDesc("债权购买");
		
		
		bizBorrowDetailService.insert(bbd);
		
		// 更新用户积分
		custPoinWaterServiceImpl.pointObtain(Constant.POINT_INVEST_TYPE,brt.getSuccessAmount(), userId);
		
		// 更新交易时间
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createTime", DateUtil.format(new Date()));
		map.put("userId", userId);
		cusTomerMapper.updateLastTransacTime(map);
		
	}

	/**
	 * 
	 * Description：<br> 
	 * 将新的收款计划添加到数据库
	 * @author  JunJie.Liu
	 * @date    2016年1月20日
	 * @version v1.0.0
	 * @param brt
	 */
	private List<BizReceiptPlan> addNewPlan(BizReceiptTransfer brt,String userId,BigDecimal vipFee) {
		if(brt == null){
			return null;
		}
		
		List<BizReceiptPlan> list = new ArrayList<BizReceiptPlan>();
		List<BizReceiptPlan> newList = new ArrayList<BizReceiptPlan>();
		if(StringUtils.isNotBlank(brt.getParentTransferId())){
			// 否则是债权再次转让
			list = bizReceiptPlanService.findListByTransfer(brt.getParentTransferId(),Constant.INVEST_SRC_TYPE_2);
		}else{
			// 如果父id为null，表示原标转让
			list = bizReceiptPlanService.findListByTransfer(brt.getPid(),Constant.INVEST_SRC_TYPE_1);
		}
		String isVip = "0";
		
		if(cusTomerService.isVip(userId)){
			isVip = "1";
		}
		
		for(int i=0;i<list.size();i++){
			
			BizReceiptPlan bp = list.get(i);
			
			if(bp!=null){
				if(bp.getNetInterest() == null){
					bp.setNetInterest(BigDecimal.ZERO);
				}
				bp.setPid(bp.getPK());
				BigDecimal managementCost = bp.getNetInterest().multiply(vipFee);
				bp.setCreateTime(new Date());
				bp.setCreateUser(userId);
				bp.setCustomerId(userId);
				bp.setIsVip(isVip);
				bp.setTransferId(brt.getPid());
				bp.setManagementCost(ArithmeticUtil.round(managementCost,2));
				bp.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_1);
				bp.setPlayType(Constant.INVEST_SRC_TYPE_2);
				bp.setVersion("1");
				bp.setIncreaseInterest(BigDecimal.ZERO);
				bp.setNetHike(BigDecimal.ZERO);
				bp.setReceivableHike(BigDecimal.ZERO);
				bp.setReceivableInterest(bp.getNetInterest().subtract(bp.getManagementCost()));
				
				newList.add(bp);
			}
		}
		

		return newList;
		
	}

	@Override
	public void revokeReceiptTransfer(BizReceiptTransfer brt, String userId) {
		BizReceiptTransferMapper mapper = (BizReceiptTransferMapper) getDao();
		if(brt==null || brt.getPid()==null){
			throw new RuntimeException("BizReceiptTransfer is null ");
		}
		BizReceiptTransfer receiptTransf = mapper.selectByPrimaryKey(brt.getPid());
		
		if(Constant.BIZ_TRANSFER_STATUS_1.equals(receiptTransf.getStatus())){
			// 判断是否可撤销
			// 修改状态
			receiptTransf.setLastUpdateUser(userId);
			receiptTransf.setLastUpdateTime(new Date());
			receiptTransf.setRecTraDesc(brt.getRecTraDesc());
			receiptTransf.setStatus(Constant.BIZ_TRANSFER_STATUS_4);
		}
			
			// 将收款计划改为待收中
//			if(StringUtils.isNotBlank(receiptTransf.getRecPlaId())){
				
//				BizReceiptPlan brp = bizReceiptPlanService.getBizReceiptPlanById(receiptTransf.getRecPlaId());
				
//				if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(brp.getReceiptStatus())){
//					brp.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_1);
//					brp.setLastUpdateUser(userId);
//					brp.setLastUpdateTime(new Date());
//					
//					int bn = bizReceiptPlanService.updateByPrimaryKeySelective(brp);
//					if(bn <= 0 ){
//						throw new UpdateChangeVersionException("债权撤销，更新收款计划失败");
//					}
//				}
//			}
//			int rt = mapper.updateByPrimaryKeySelective(receiptTransf);
//			if(rt <= 0 ){
//				throw new UpdateChangeVersionException("债权撤销，更新债权转让记录失败");
//			}
//		}else{
//			logger.info("债权转让状态已经改变");
//			throw new RuntimeException("债权转让状态已经改变");
//		}
//		
	}

	@Override
	public List<BizReceiptTransferCenterModel> findTrasferCenterVos(
			BizReceiptTransferCenterModel condition, String userId,
			Integer pageIndex, Integer pageSize, String flag) {
		Integer start = 0;
		Integer end = 10;
		if(pageSize!=null){
			end = pageSize;
		}
		if(pageIndex!=null){
			start = pageIndex * end;
		}
		if(StringUtils.isBlank(flag)){
			flag = "1";
		}
		if(condition==null){
			condition = new BizReceiptTransferCenterModel();
		}
		
		if("1".equals(flag)){
			// 持有中
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_2);
			condition.setCustomerId(userId);
		}else if("2".equals(flag)){
			// 转让中
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_1);
			condition.setCreateUserId(userId);
		}else if("3".equals(flag)){
			// 已转让
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_2);
			condition.setCreateUserId(userId);
			condition.setCustomerId(userId);
		}
		
		
		BizReceiptTransferMapper mapper = (BizReceiptTransferMapper) getDao();
		
		return mapper.findTrasferCenterVos(condition, flag, start, end);
	}

	@Override
	public Integer findTrasferCenterVosCount(
			BizReceiptTransferCenterModel condition,String userId, String flag) {
		if(condition==null){
			condition = new BizReceiptTransferCenterModel();
		}
		
		if("1".equals(flag)){
			// 持有中
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_2);
			condition.setCustomerId(userId);
		}else if("2".equals(flag)){
			// 转让中
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_1);
			condition.setCreateUserId(userId);
		}else if("3".equals(flag)){
			// 已转让
			condition.setTransferStatus(Constant.BIZ_TRANSFER_STATUS_2);
			condition.setCreateUserId(userId);
			condition.setCustomerId(userId);
		}
		return mapper.findTrasferCenterVosCount(condition, flag);
	}

	@Override
	public void executeTimer() {
		logger.info("债权定时器执行中");
		int soldCount = 0;
		int exceptionCount = 0;
		//　1.获取所有转让中的债权
		List<BizReceiptTransfer> list = mapper.findList(Constant.BIZ_TRANSFER_STATUS_1);
		int count = list.size();
		for(int i=0;i < list.size();i++){
			//  2.取对象，判断是否已过期，过期下架
			try{
				BizReceiptTransfer brt = list.get(i);
				if(brt.isExipre()){
					soldOut(brt,"过期下架");
					soldCount++;
					continue;
				}
			}catch(Exception e){
				if(logger.isDebugEnabled()){
					logger.error(e.getMessage());
				}
				exceptionCount++;
			}
		}
		logger.info("债权定时器执行结束：本次总共债权"+count+"个，出现异常"+exceptionCount+"个，下架总共"+soldCount+"个债权。）");
	}
	
	private void soldOut(BizReceiptTransfer brt,String des){
		if(Constant.BIZ_TRANSFER_STATUS_3.equals(brt.getStatus())){
			// 已经下架了
			return;
		}
		brt.setStatus(Constant.BIZ_TRANSFER_STATUS_3);
		brt.setLastUpdateTime(new Date());
		brt.setRecTraDesc(des);
		mapper.updateByPrimaryKey(brt);
		// 更新对应的收款计划为待收中
		String pid = brt.getPid();
		String investType = Constant.INVEST_SRC_TYPE_1;
		if (StringUtils.isNotBlank(brt.getParentTransferId())) {
			// 再次转让
			pid = brt.getParentTransferId();
			investType = Constant.INVEST_SRC_TYPE_2;
		}
		bizReceiptPlanService.updateByTransfer(pid,investType, Constant.BIZ_RECEIPTPLAN_STATUS_1);
	}

	@Override
	public BizReceiptTransfer getCustomerIdByReceiptPlanId(String rePid) {
		
		return mapper.getCustomerIdByReceiptPlanId(rePid);
	}

	@Override
	public List<BizReceiptTransferFrontModel> findTransferList(
			String type, boolean desc,
			Integer pageNum, Integer pageSize) {
	
		return mapper.findTransferListByApi(type,desc,pageNum,pageSize);
	}

	@Override
	public Integer findTransferListByApiCount() {
		
		return mapper.findTransferListByApiCount();
	}

	@Override
	public PageList<BizReceiptTransferModel> selectAllPage(BizReceiptTransferModel bizBorrow,
			PageInfo info) {
		return mapper.selectAllPage(bizBorrow, info);
	}

	@Override
	public List<BizReceiptTransferModel> selectBizReceiptTransferVOEom(
			BizReceiptTransferModel bizReceiptTransferVO, ExportObjectModel eom) {
			
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("map", bizReceiptTransferVO);
				map.put("expm", eom);
			List<BizReceiptTransferModel> elist = mapper.selectBizReceiptTransferVOEom(map); 
			if(null == elist){
				elist = new ArrayList<BizReceiptTransferModel>(0);
			}else{
				//统计总数
				BigDecimal successAmount = BigDecimal.ZERO;
				for(BizReceiptTransferModel vo : elist){
//					initInvestAmount = initInvestAmount.add(vo.getInitInvestAmount()==null? new BigDecimal("0") : vo.getInitInvestAmount());
					successAmount = successAmount.add(vo.getSuccessAmount()==null? new BigDecimal("0") : vo.getSuccessAmount());
//					balanceAmount = balanceAmount.add(vo.getBalanceAmount()==null? new BigDecimal("0") : vo.getBalanceAmount());
				}
				BizReceiptTransferModel bbal = new BizReceiptTransferModel();
				bbal.setBorrowCode(Constant.AGGREGATE_STRING);
				//获取统计的值
//				bbal.setInitInvestAmount(initInvestAmount);
				bbal.setSuccessAmount(successAmount);
//				bbal.setBalanceAmount(balanceAmount);
				bbal.setPid("1");
//				bbal.setIssue("");
//				添加到集合中
				elist.add(bbal);
			}
			return elist;
	}

	@Override
	public BizReceiptTransferModel sumBizReceiptTransferVoWhere(
			BizReceiptTransferModel bizReceiptTransferVo) {
		
		return mapper.sumBizReceiptTransferVoWhere(bizReceiptTransferVo);
	}

	@Override
	public void soldOutByTransferId(String transferId,String des) {
		if(StringUtils.isNotBlank(transferId)){
			BizReceiptTransfer brt =  mapper.selectByPrimaryKey(transferId);
			if(brt!=null){
				this.soldOut(brt, des);
			}
		}
		
	}



}
