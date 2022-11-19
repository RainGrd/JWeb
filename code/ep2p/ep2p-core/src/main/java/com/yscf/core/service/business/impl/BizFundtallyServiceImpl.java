/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月28日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.mybatis.paginator.domain.Paginator;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.business.BizFundtallyMapper;
import com.yscf.core.dao.system.SysAccountMapper;
import com.yscf.core.model.business.BizFundtally;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizFundtallyModel;
import com.yscf.core.model.system.SysAccount;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizFundtallyService;
import com.yscf.core.service.system.ISysAccountService;

/**
 * Description：业务--资金流水	 业务逻辑层实现类
 * @author  JingYu.Dai
 * @date    2015年10月28日
 * @version v1.0.0
 */
@Service("bizFundtallyService")
public class BizFundtallyServiceImpl extends BaseService<BaseEntity, String> implements IBizFundtallyService {

	@Autowired
	public BizFundtallyServiceImpl(BizFundtallyMapper dao) {
		super(dao);
	}
	
	@Resource
	private ISysAccountService sysAccountService;
	
	@Resource
	private SysAccountMapper sysAccountMapper;

	@Override
	public PageList<BizFundtally> selectBizFundtallyPage(
			BizFundtally bizFundtally, PageInfo info) throws FrameworkException{
		BizFundtallyMapper dao = (BizFundtallyMapper) getDao();
		bizFundtally = bizFundtally==null?new BizFundtally():bizFundtally;
		bizFundtally.setPage((info.getPage()-1)*info.getLimit());
		bizFundtally.setLimit(info.getLimit());
		List<BizFundtally> list =dao.selectBizFundtallyPage(bizFundtally);
		//创建 线下充值类 对象
		BizFundtally bf = new BizFundtally();
		//创建 客户对象
		CusTomer customer = new CusTomer();
		customer.setCustomerName("合计");
		bf.setCustomer(customer);
		//根据查询条件查询 统计（收入、支出）  总金额  
		Map<String,BigDecimal> map = dao.selectSumAmountSelective(bizFundtally);
		if(null != map && null != bf){
			bf.setIncomeAmount(map.get("incomeAmount"));
			bf.setOutlayAmount(map.get("outlayAmount"));
			list.add(bf);
		}
		int total = dao.selectBizFundtallyPageTotal(bizFundtally);
		Paginator paginator = new Paginator(info.getPage(), info.getLimit(), total);
		PageList<BizFundtally>  pageList= new  PageList<BizFundtally>(list,paginator);
		return pageList;
	}

	@Override
	public List<BizFundtallyModel> selectBizFundtallyEom(BizFundtally fundtally,
			ExportObjectModel eom) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		BizFundtallyMapper mapper = (BizFundtallyMapper) getDao();
		map.put("fundtally", fundtally);
		map.put("expm", eom);
		List<BizFundtally> elist = mapper.selectBizFundtallyEom(map); 
		
		List<BizFundtallyModel> listVo = new ArrayList<BizFundtallyModel>();
		
		BigDecimal incomeAmount = new BigDecimal("0");
		BigDecimal outlayAmount = new BigDecimal("0");
		
		
		for(BizFundtally bf : elist){
			BizFundtallyModel vo = new BizFundtallyModel();
			if(bf!=null){
				if(bf.getCustomer()!=null){
					vo.setCustomerName(bf.getCustomer().getCustomerName());
					vo.setSname(bf.getCustomer().getSname());
					vo.setPhoneNo(bf.getCustomer().getPhoneNo());
				}
				vo.setActorTime(bf.getActorTime());
				vo.setDetailTypeName(vo.getTypeName(bf.getDetailType()));
				vo.setFunDesc(bf.getFunDesc());
				vo.setIncomeAmount(bf.getIncomeAmount());
				vo.setOutlayAmount(bf.getOutlayAmount());
				
				listVo.add(vo);
				
				incomeAmount.add(bf.getIncomeAmount()==null ? new BigDecimal(0) : bf.getIncomeAmount());
				outlayAmount.add(bf.getOutlayAmount()==null ? new BigDecimal(0) : bf.getOutlayAmount());
			}
			
		}
		BizFundtallyModel vo = new BizFundtallyModel();
		vo.setCustomerName(Constant.AGGREGATE_STRING);
		vo.setIncomeAmount(incomeAmount);
		vo.setOutlayAmount(outlayAmount);
		
		listVo.add(vo);
		
		
		return listVo;
	}

	@Override
	public void insertBizFundtallyByUserIds(List<String> userIds,BigDecimal amount,String dealType,String detailType,Date time,String note){
		List<BizFundtally> list = new ArrayList<BizFundtally>();
		//得到系统账户
		SysAccount account = sysAccountService.getSysAccount();
		for (String userId : userIds) { 
			//减去每个用户的系统支出
			account.setBalance(account.getBalance().subtract(amount));
			//生成每条记录的资金流水对象
			BizFundtally bizFundtally = new BizFundtally();
			bizFundtally.setPid(bizFundtally.getPK());
			bizFundtally.setCustomerId(userId);
			bizFundtally.setAmount(amount);
			bizFundtally.setBalance(account.getBalance());
			bizFundtally.setDetailType(detailType);
			bizFundtally.setDealType(dealType);
			bizFundtally.setActorTime(DateUtil.format(time));
			bizFundtally.setStatus(Constant.PUBLIC_STATUS);
			bizFundtally.setCreateTime(DateUtil.format(time));
			bizFundtally.setFunDesc(note);
			list.add(bizFundtally);
		}
		//更新系统账户表
		int result = sysAccountMapper.updateByPrimaryKey(account);
		if(result<1){
			throw new RuntimeException("更新系统账户表失败");
		}
		BizFundtallyMapper mapper = (BizFundtallyMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizFundtallys", list);
		mapper.insertBizFundtallyByUserIds(map);
	}


}


