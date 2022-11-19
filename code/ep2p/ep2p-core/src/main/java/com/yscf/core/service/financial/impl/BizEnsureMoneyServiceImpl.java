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
 * 2015年10月21日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.financial.BizEnsureMoneyDetailMapper;
import com.yscf.core.dao.financial.BizEnsureMoneyMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.financial.BizEnsureMoneyDetail;
import com.yscf.core.service.financial.IBizEnsureMoneyService;

/**
 * Description：<br> 
 * TODO
 * @author  jenkin.yu
 * @date    2015年10月21日
 * @version v1.0.0
 */
@Service("bizEnsureMoneyServiceImpl")
public class BizEnsureMoneyServiceImpl extends BaseService<BaseEntity, String> implements IBizEnsureMoneyService{
	
	private Logger logger = LoggerFactory.getLogger(BizEnsureMoneyServiceImpl.class);
	
	@Resource(name = "bizEnsureMoneyDetailMapper")
	private BizEnsureMoneyDetailMapper bizEnsureMoneyDetailMapper;
	
	@Autowired
	public BizEnsureMoneyServiceImpl(BizEnsureMoneyMapper dao) {
		super(dao);
	}

	@Override
	public int saveEnsureAdjustment(BizEnsureMoney bizEnsureMoney) {
		BizEnsureMoneyMapper mapper =  (BizEnsureMoneyMapper) getDao();
		int result = 0 ;
		BizEnsureMoney ensureMoney = null;
		BizEnsureMoneyDetail detail = new BizEnsureMoneyDetail();
		detail.setPid(bizEnsureMoney.getPK());
		// 初始化备付金 新增数据
//		if(TradeTypeConstant.RISK_TRADE_TYPE_2007.equals(bizEnsureMoney.getEnsureType())){
			bizEnsureMoney.setBalance(bizEnsureMoney.getAmount());
			bizEnsureMoney.setPid(bizEnsureMoney.getPK());
			bizEnsureMoney.setStatus(Constant.ACTIVATE);
			result = mapper.insert(bizEnsureMoney);
			ensureMoney = bizEnsureMoney;
			ensureMoney.setBalance(ensureMoney.getAmount());
			// 设置创建人创建时间，处理人，处理时间
			detail.setFeeType(Constant.FEE_TYPE_1);
			detail.setCreateTime(bizEnsureMoney.getCreateTime());
			detail.setCreateUser(bizEnsureMoney.getCreateUser());
			detail.setActorUser(bizEnsureMoney.getCreateUser());
			detail.setCatorTime(DateUtil.format(bizEnsureMoney.getCreateTime()));
		/*}else{
			// 1 先查询出备付金数据，
			List<BizEnsureMoney> list = mapper.selectAll();
			if(null!= list && list.size()>0){
				ensureMoney = list.get(0);
			}
			BigDecimal amount = new BigDecimal(0);
			BigDecimal balance = new BigDecimal(0);
			// 2 调减备付金
			if (TradeTypeConstant.RISK_TRADE_TYPE_2010.equals(bizEnsureMoney.getEnsureType())){
//				insertDetail(null,bizEnsureMoney.getCreateUser(),radeTypeConstant.RISK_TRADE_TYPE_2010,BigDecimal blance,BigDecimal amount,String desc)
				amount = ensureMoney.getAmount().subtract(bizEnsureMoney.getAmount()) ;
				ensureMoney.setAmount(amount);
				
				balance = ensureMoney.getBalance().subtract(bizEnsureMoney.getAmount());
				ensureMoney.setBalance(balance);
				detail.setFeeType(Constant.FEE_TYPE_2);
			}
			
			// 3 调增备付金
			if (TradeTypeConstant.RISK_TRADE_TYPE_2009.equals(bizEnsureMoney.getEnsureType())){
				amount =ensureMoney.getAmount().add(bizEnsureMoney.getAmount());
				ensureMoney.setAmount(amount);
				balance = ensureMoney.getBalance().add(bizEnsureMoney.getAmount());
				ensureMoney.setBalance(balance);
				detail.setFeeType(Constant.FEE_TYPE_1);
			}
			
			// 4 设置最后更新人最后更新时间,操作人，操作时间
			ensureMoney.setLastUpdateTime(bizEnsureMoney.getLastUpdateTime());
			ensureMoney.setLastUpdateUser(bizEnsureMoney.getLastUpdateUser());
			detail.setCreateTime(bizEnsureMoney.getLastUpdateTime());
			detail.setCreateUser(bizEnsureMoney.getLastUpdateUser());
			detail.setActorUser(bizEnsureMoney.getLastUpdateUser());
			detail.setCatorTime(DateUtil.format(bizEnsureMoney.getLastUpdateTime()));
			
			// 5 保存备付金
			result = mapper.updateByPrimaryKeySelective(ensureMoney);
		}*/
		// 6 往历史表中插入记录
		
		detail.setEnsMonDetType(bizEnsureMoney.getEnsureType()+"");
		detail.setBalance(ensureMoney.getBalance());
		detail.setHappenValue(bizEnsureMoney.getAmount());
		detail.setStatus(Constant.ACTIVATE);
		detail.setEnsMonDetDesc(bizEnsureMoney.getEnsMonDetDesc());
		bizEnsureMoneyDetailMapper.insertSelective(detail);
		return result;
	}

	@Override
	public void updateAddProvisions(BigDecimal amount, String userId,
			String tradeType, String pkId, String desc)
			throws FrameworkException {
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new NullArgumentException("amount is null or amount less 0 ");
		}
		
		BizEnsureMoneyMapper mapper =  (BizEnsureMoneyMapper) getDao();
		
		// 进行增加备付金
		mapper.updateToAddBlance(amount);
		
		// 插入流水
		BigDecimal blance = BigDecimal.ZERO;
		
		List<BizEnsureMoney> list = mapper.selectAll();
		if(null!= list && list.size()>0){
			BizEnsureMoney ensureMoney = list.get(0);
			if(ensureMoney == null){
				throw new RuntimeException("备付金对象为null");
			}
			blance =  ensureMoney.getBalance();
		}
		try{
			
			this.insertDetail(pkId, userId, tradeType, blance, amount, desc);
			
		}catch(Exception e){
			
			logger.error("增加系统备付金流水失败"+e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	public void updateSubProvisions(BigDecimal amount, String userId,
			String tradeType, String pkId, String desc)
			throws FrameworkException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new NullArgumentException("amount is null or amount less 0 ");
		}
		if(!StringUtils.hasLength(tradeType)){
			throw new NullArgumentException("tradeType is null");
		}
		BizEnsureMoneyMapper mapper =  (BizEnsureMoneyMapper) getDao();
		
		// 
		int r = mapper.updateToSubBlance(amount);
		if(r < 1){
			throw new UpdateChangeVersionException("备付金余额不足");
		}
		
		// 插入流水
		BigDecimal blance = BigDecimal.ZERO;
		
		List<BizEnsureMoney> list = mapper.selectAll();
		if(null!= list && list.size()>0){
			BizEnsureMoney ensureMoney = list.get(0);
			if(ensureMoney == null){
				throw new RuntimeException("备付金对象为null");
			}
			blance =  ensureMoney.getBalance();
		}
		try{
			
			this.insertDetail(pkId, userId, tradeType, blance, amount, desc);
			
		}catch(Exception e){
			logger.error("增加系统备付金流水失败"+e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private void insertDetail(String borrowId,String userId,String tradeType,BigDecimal blance,BigDecimal amount,String desc){
		
		BizEnsureMoneyDetail detail = new BizEnsureMoneyDetail();
		
		Integer ft = Integer.parseInt(tradeType);
		
		String feeType = Constant.FEE_TYPE_1;
		if( ft % 2 == 0){
			//支出
			feeType = Constant.FEE_TYPE_2;
		}
		
		detail.setPid(detail.getPK());
		detail.setBorrowId(borrowId);
		detail.setCustomerId(userId);
		detail.setCreateTime(new Date());
		detail.setLastUpdateTime(new Date());
		detail.setLastUpdateUser(userId);
		detail.setCreateUser(userId);
		detail.setEnsMonDetType(tradeType);
		detail.setBalance(blance);
		detail.setHappenValue(amount);
		detail.setStatus(Constant.ACTIVATE);
		detail.setFeeType(feeType);
		detail.setEnsMonDetDesc(desc);
		detail.setCatorTime(DateUtil.format(new Date()));
		detail.setActorUser(userId);
		bizEnsureMoneyDetailMapper.insertSelective(detail);
		
	}

	@Override
	public BigDecimal getMoney() {
		BizEnsureMoneyMapper mapper =  (BizEnsureMoneyMapper) getDao();
		return mapper.getMoney();
	}

}


