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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.business.BizReceiptPlanMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizReceiptPlanModel;
import com.yscf.core.model.ptp.investment.BizReceiptPlanInfoModel;
import com.yscf.core.model.reflect.InvestUser;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.ISysCreateCodeRuleService;

import freemarker.template.utility.NullArgumentException;

/**
 * 
 * Description：<br> 
 * 收款计划ServiceImpl
 * @author  JunJie.Liu
 * @date    2015年10月23日
 * @version v1.0.0
 */
@Service("bizReceiptPlanService")
public class BizReceiptPlanServiceImpl extends
		BaseService<BaseEntity, String> implements IBizReceiptPlanService {
	@Autowired
	public BizReceiptPlanServiceImpl(BizReceiptPlanMapper dao) {
		super(dao);
	}

	@Resource(name = "bizReceiptPlanMapper")
	private BizReceiptPlanMapper mapper;
	
	@Resource(name = "bizReceiptTransferService")
	private IBizReceiptTransferService bizReceiptTransferService;
	
	@Resource(name = "sysCreateCodeRuleService")
	private ISysCreateCodeRuleService sysCreateCodeRuleService;

	@Resource
	private IBizBorrowService bizBorrowService;
	
	@Resource
	private ICustomerAccountService customerAccountService;

	@Resource
	private ISysAccountService sysAccountService;
	
	@Override
	public BizReceiptPlan getBizReceiptPlanById(String pid) {
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public int updateByPrimaryKeySelective(BizReceiptPlan receiptTransf) {
		return mapper.updateByPrimaryKey(receiptTransf);
	}

	@Override
	public Integer findTotalCount(BizReceiptPlanModel condition) {
		
		return mapper.findTotalCount(condition);
	}


	@Override
	public List<BizReceiptPlanModel> findList(BizReceiptPlanModel condition,
			 Integer pageIndex,Integer pageSize) {
		if(pageIndex == null){
			pageIndex = 0;
		}else{
			pageIndex = pageIndex * pageSize;
		}
		if(	pageSize == null){
			pageSize = 10;
		}
		return mapper.findList( condition,pageIndex, pageSize);
	}

	@Override
	public BizReceiptPlanModel findReceiptPlanById(BizReceiptPlanModel condition) {
		
		return mapper.findReceiptPlanById(condition);
	}

	@Override
	@Deprecated
	public String updateReceiptPlanById(BizReceiptPlanModel condition) {
		String transferCode = null;
		BizReceiptPlan bizReceiptPlan = mapper.selectByPrimaryKey(condition.getPid());
		if(bizReceiptPlan == null){
			throw new RuntimeException("收款记录不存在,pid:"+condition.getPid());
		}
		if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(bizReceiptPlan.getReceiptStatus())){
			throw new RuntimeException("债权状态不可进行转让,pid:"+condition.getPid());
		}
		// 修改状态为 转让中
		bizReceiptPlan.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_3);
		
//		BizBorrow bb = bizBorrowService.getBizBorrowById(bizReceiptPlan.getBorrowId());
		
		// 生成转让记录
		BizReceiptTransfer brt = new BizReceiptTransfer();
		brt.setPid(brt.getPK());
		try {
			transferCode = sysCreateCodeRuleService.generatNoRule("ZQ", "8", 3,condition.getCustomerId());
			brt.setTransferCode(transferCode);
		} catch (FrameworkException e) {
			throw new RuntimeException("生成债权转让编号异常:"+e);
		}
		brt.setBorrowId(bizReceiptPlan.getBorrowId());
		brt.setCreateTime(new Date());
		brt.setCreateUser(bizReceiptPlan.getCustomerId());
//		
//		brt.setRateScopeMin(bb.getStartValue());
//		brt.setRateScopeMax(bb.getEndValue());
		
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, condition.getReturnedDate()); 
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			brt.setExpireTime(sdf.parse(sdf2.format(c.getTime())));
		}catch (Exception e){
			throw new RuntimeException("待收时间异常:"+e);
		}
		
		brt.setStatus(Constant.BIZ_TRANSFER_STATUS_1);
		
		// 修改收款计划状态
		int bn = mapper.updateByPrimaryKey(bizReceiptPlan);
		if(bn<=0){
			// 更新失败
			throw new UpdateChangeVersionException("转让确认时，修改收款计划失败");
		}
		
		// 保存转让记录
		bizReceiptTransferService.insert(brt);
		
		return transferCode;
	}

	@Override
	public int insert(BizReceiptPlan bizReceiptPlan) {
		
		return mapper.insert(bizReceiptPlan);
	}

	@Override
	public PageList<BizReceiptPlanInfoModel> searchAllPage(
			BizReceiptPlanInfoModel brpm, PageInfo info) {
		
		return mapper.searchAllPage(brpm,info);
	}

	@Override
	public BizReceiptPlanInfoModel sumAllPage(BizReceiptPlanInfoModel brpm) {
		BigDecimal bgsum = mapper.sumAllPage(brpm);
		bgsum = bgsum == null ? BigDecimal.ZERO:bgsum;
		BizReceiptPlanInfoModel brp = new BizReceiptPlanInfoModel();
		brp.setBenXi(bgsum);
		brp.setBorrowName(Constant.AGGREGATE_STRING);
		return brp;
	}

	@Override
	public List<BizReceiptPlanInfoModel> selectBizReceiptPlanInfoModelVOEom(
			BizReceiptPlanInfoModel bizReceiptPlanInfoModel,
			ExportObjectModel eom) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("map", bizReceiptPlanInfoModel);
			map.put("expm", eom);
			List<BizReceiptPlanInfoModel> elist = mapper.selectBizReceiptPlanInfoVOEom(map); 
			if(null == elist){
				elist = new ArrayList<BizReceiptPlanInfoModel>(0);
			}else{
				//统计总数
				BigDecimal initInvestAmount =BigDecimal.ZERO;
				for(BizReceiptPlanInfoModel vo : elist){
					initInvestAmount = initInvestAmount.add(vo.getBenXi()==null? BigDecimal.ZERO : vo.getBenXi());
				}
				BizReceiptPlanInfoModel bbal = new BizReceiptPlanInfoModel();
				bbal.setBorrowCode(Constant.AGGREGATE_STRING);
				//获取统计的值
				bbal.setBenXi(initInvestAmount);
		//		添加到集合中
				elist.add(bbal);
			}
		return elist;
	}

	@Override
	public BigDecimal sumBenXi(String recPlaId) {
	
		return mapper.sumBenXi(recPlaId);
	}

	@Override
	public List<BizReceiptPlan> findListByRepaymentId(String pid) {
	
		return mapper.findListByRepaymentId(pid);
	}

	@Override
	public BigDecimal getSurpAmount(String borrowId, String userId) {
	
		return mapper.getSurpAmount(borrowId,userId);
	}

	@Override
	public BigDecimal getAlreadyAmount(String borrowId, String userId) {
	
		return mapper.getAlreadyAmount(borrowId,userId);
	}

	@Override
	public BigDecimal sumCapitalAndInterest(String borrowId, String userId) {
		
		return mapper.sumCapitalAndInterest(borrowId,userId);
	}

	@Override
	public BigDecimal sumNetHike(String borrowId, String userId) {
		
		return mapper.sumNetHike(borrowId,userId);
	}

	@Override
	public BigDecimal sumVipAmount(String bizReceiptplanStatus, String pid) {
		
		return mapper.sumVipAmount(bizReceiptplanStatus,pid);
	}

	@Override
	public List<BizReceiptPlan> findListByRepaymentIdNotVip(String repaymentId,
			String bizReceiptplanStatus) {
		
		return mapper.findListByRepaymentNotVip(repaymentId,bizReceiptplanStatus);
	}

	@Override
	public BigDecimal sumBenJin(String borrowId, String customerId) {
	
		return mapper.sumBenJin(borrowId,customerId);
	}

	@Override
	public List<BizReceiptPlan> findListByTransfer(String transferId,
			String investSrcType) {
		
		return mapper.findListByTransfer(transferId,investSrcType);
	}

	@Override
	public int updateByTransfer(String pid, String investSrcType,
			String bizReceiptplanStatus) {
		
		return mapper.updateByTransfer(pid,investSrcType,bizReceiptplanStatus);
	}

	@Override
	public void insertBatch(List<BizReceiptPlan> list) {
		mapper.insertBatch(list);
	}

	@Override
	public List<BizReceiptPlan> findList(String userId, String borrowId, String investSrcType,
			String transferId) {
		
		return mapper.findListByUser(userId,borrowId,investSrcType,transferId);
	}

	@Override
	public String updateTransfer(String borrowId, String transferId,
			String userId, BigDecimal transferAmount, BizReceiptTransfer brt) {
		// 1.将收款计划列表改为转让中，并且将转让id加上，如果有转让id，则无需修改

		if (StringUtils.hasLength(transferId)) {
			// 表示再次债权转让
			mapper.updateTransfer(borrowId,userId,null,Constant.BIZ_RECEIPTPLAN_STATUS_3,transferId);
		} else {
			mapper.updateTransfer(borrowId,userId,brt.getPid(),Constant.BIZ_RECEIPTPLAN_STATUS_3,null);

		}
		
		// 2.产生转让编号
		String transferCode = null;
		try {
			transferCode = sysCreateCodeRuleService.generatLabelNoRule("Z", "8", userId);
			brt.setTransferCode(transferCode);
		} catch (FrameworkException e) {
			throw new RuntimeException("生成债权转让编号异常:"+e);
		}
		// 保存转让记录
		bizReceiptTransferService.insert(brt);
		return transferCode;
	}

	public List<BizReceiptPlan> findValidByRepaymentId(String pid) {
		return mapper.findValidByRepaymentId(pid);
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 提前还款批量更新
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param updateReceiptPlanList
	 */
	public void batchUpdateToPrepayment(
			List<BizReceiptPlan> updateReceiptPlanList) {
		mapper.batchUpdateToPrepayment(updateReceiptPlanList);
	}

	@Override
	public PageList<BizReceiptPlan> selectAllPage(
			BizReceiptPlan bizReceiptPlan, PageInfo pageInfo) {
		BizReceiptPlanMapper mapper = (BizReceiptPlanMapper) getDao();
		PageList<BizReceiptPlan>  totalDaiShou = mapper.selectAllPage(bizReceiptPlan, pageInfo);
//		Map<String,BigDecimal> map = mapper.selectAllPageSum(bizReceiptPlan);
		BizReceiptPlan bizReceiptPlan2 = new BizReceiptPlan();
//		if(map!=null){
//			bizReceiptPlan2.setDueinTotalValue(map.get("dueinTotalValue"));
//			bizReceiptPlan2.setCorpus(map.get("corpus"));
//			bizReceiptPlan2.setAccrual(map.get("accrual"));
//			totalDaiShou.add(bizReceiptPlan2);
//		}
 		return totalDaiShou;
	}

	@Override
	public void repayNetHike(String time) throws FrameworkException {
		List<BizReceiptPlan> brps = mapper.findListByNetHikeStatus(Constant.NET_HIKE_STATUS_0,time);
		for(int i=0;i<brps.size();i++){
			BizReceiptPlan brp = brps.get(i);
			BizBorrow borrow = bizBorrowService.getBizBorrowById(brp.getBorrowId());
			if (null == borrow) {
				throw new NullArgumentException("标的不存在");
			}
			String userId = brp.getCustomerId();
			// 6.加息利息
			BigDecimal netHike = brp.getNetHike() == null ? BigDecimal.ZERO : brp.getNetHike();
			if (netHike.compareTo(BigDecimal.ZERO) > 0) {
				// 用户增加
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName() + " ," + brp.getPlanIndex() + "/" + borrow.getDeadline();
				customerAccountService.updateAddAvailableAmount(netHike, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_401, note);
				// 从风险备用金扣除
				sysAccountService.updateSubSystemAmount(netHike, userId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1012, brp.getBorrowId(), note);

				brp.setNetHikeStatus(Constant.NET_HIKE_STATUS_1);
			}
			// 7.加息利息管理费
			BigDecimal netManageFee = brp.getIncreaseInterest() == null ? BigDecimal.ZERO : brp.getIncreaseInterest();
			if (netManageFee.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
				customerAccountService.updateDeductedAvailableAmount(netManageFee, userId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, note);
				customerAccountService.updateDeductedInterestManagementFee(netManageFee, userId, brp.getBorrowId(), "");
			}
			mapper.updateByPrimaryKeySelective(brp);
			
		}
		
	}

	@Override
	public void updateStatusByVip(String srcStatus, String toStatus,
			String brpId) {
	
		mapper.updateStatusByVip(srcStatus,toStatus,brpId);
	}

	@Override
	public List<InvestUser> findInvestList(String borrowId, String userId) {
		return mapper.findInvestList(borrowId,userId);
	}

	/**
	 * 
	 * Description：<br> 
	 * 根据借款ID，回款人ID，借款期限获取最后一期数据
	 * @author  Yu.Zhang
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param borrowId
	 * @param customerId
	 * @param borDeadline
	 * @return
	 */
	public BizReceiptPlan getLastDeline(String borrowId, String customerId,String borDeadline) {
		return mapper.getLastDeline(borrowId,customerId,borDeadline);
	}

	

	

}
