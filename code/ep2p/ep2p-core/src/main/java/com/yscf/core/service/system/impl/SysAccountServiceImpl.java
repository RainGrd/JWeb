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
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.business.BizFundtallyMapper;
import com.yscf.core.dao.system.SysAccountMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.business.BizFundtally;
import com.yscf.core.model.system.SysAccount;
import com.yscf.core.service.business.IBizFundtallyService;
import com.yscf.core.service.system.ISysAccountService;

/**
 * 
 * Description：<br> 
 * 系统账户
 * @author  JunJie.Liu
 * @date    2015年12月18日
 * @version v1.0.0
 */
@Service("sysAcountService")
public class SysAccountServiceImpl extends BaseService<BaseEntity, String> implements ISysAccountService{

	private Logger logger = LoggerFactory.getLogger(SysAccountServiceImpl.class);
	
	@Resource
	private BizFundtallyMapper bizFundtallyMapper;
	
	@Resource
	private IBizFundtallyService bizFundtallyServiceImpl;
	
	@Autowired
	public SysAccountServiceImpl(SysAccountMapper dao) {
		super(dao);
	}

	@Override
	public void updateAddSystemAmount(BigDecimal amount, String userId,
			String tradeType, String pkId, String desc)
			throws FrameworkException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<= 0){
			throw new NullArgumentException("amount is null or amount less 0 ");
		}
		if(!StringUtils.hasLength(tradeType)){
			throw new NullArgumentException("tradeType is null");
		}
		SysAccountMapper mapper =  (SysAccountMapper) getDao();
		
		// 进行增加系统金额
		mapper.updateToAddBlance(amount);
		
		// 插入流水
		BigDecimal blance = BigDecimal.ZERO;
		
		List<SysAccount> list = mapper.selectAll();
		if(null!= list && list.size()>0){
			SysAccount ensureMoney = list.get(0);
			if(ensureMoney == null){
				throw new RuntimeException("系统资金对象为null");
			}
			blance =  ensureMoney.getBalance();
		}
		try{
			
			this.insertDetail(pkId, userId, tradeType, blance, amount, desc);
			
		}catch(Exception e){
			
			logger.error("新增系统资金流水失败"+e.getMessage());
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateSubSystemAmount(BigDecimal amount, String userId,
			String tradeType, String pkId, String desc)
			throws FrameworkException {
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new NullArgumentException("amount is null or amount less 0 ");
		}
		if(!StringUtils.hasLength(tradeType)){
			throw new NullArgumentException("tradeType is null");
		}
		SysAccountMapper mapper =  (SysAccountMapper) getDao();
		
		// 进行减少系统金额
		int r = mapper.updateToSubBlance(amount);
		if(r < 1){
			throw new UpdateChangeVersionException("系统资金余额不足");
		}
		// 插入流水
		BigDecimal blance = BigDecimal.ZERO;
		
		List<SysAccount> list = mapper.selectAll();
		if(null!= list && list.size()>0){
			SysAccount ensureMoney = list.get(0);
			if(ensureMoney == null){
				throw new RuntimeException("系统资金对象为null");
			}
			blance =  ensureMoney.getBalance();
		}
		try{
			
			this.insertDetail(pkId, userId, tradeType, blance, amount, desc);
			
		}catch(Exception e){
			
			logger.error("新增系统资金流水失败"+e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private void insertDetail(String pkId, String userId, String tradeType,
		BigDecimal blance, BigDecimal amount, String desc) {
		Integer ft = Integer.parseInt(tradeType);
		
		String feeType = Constant.FEE_TYPE_1;
		if( ft % 2 == 0){
			//支出
			feeType = Constant.FEE_TYPE_2;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		BizFundtally bf = new BizFundtally();
		bf.setBalance(blance);
		bf.setAmount(amount);
		bf.setCustomerId(userId);
		bf.setCreateTime(sdf.format(new Date()));
		bf.setDealType(feeType);
		bf.setDetailType(tradeType);
		bf.setPid(bf.getPK());
		bf.setStatus(Constant.ACTIVATE);
		bf.setFunDesc(desc);
		bf.setCreateUser(userId);
		
		bizFundtallyMapper.insert(bf);
		
	}

	@Override
	public SysAccount getSysAccount(){
		SysAccountMapper mapper =  (SysAccountMapper) getDao();
		List<SysAccount> list = mapper.selectAll();
		if(null!= list && list.size()>0){
			SysAccount ensureMoney = list.get(0);
			if(ensureMoney == null){
				throw new RuntimeException("系统资金对象为null");
			}else{
				return ensureMoney;
			}
		}
		return null;
	}

	
}


