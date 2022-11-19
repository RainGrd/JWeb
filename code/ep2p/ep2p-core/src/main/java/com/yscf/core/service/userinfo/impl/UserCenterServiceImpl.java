/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户中心账户总览和我的投资Service
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.userinfo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.ptp.userinfo.UserCenterMapper;
import com.yscf.core.model.ptp.investment.DueInDetailModel;
import com.yscf.core.model.ptp.investment.InvestDueInModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.ptp.investment.InviteTendersModel;
import com.yscf.core.model.ptp.investment.TransferTendersMode;
import com.yscf.core.service.userinfo.IUserCenterService;

/**
 * Description：<br> 
 * 用户中心账户总览和我的投资Service
 * @author  Lin Xu
 * @date    2015年11月24日
 * @version v1.0.0
 */
@Service("userCenterServiceImpl")
public class UserCenterServiceImpl extends BaseService<BaseEntity, String> implements IUserCenterService {

	Logger logger = Logger.getLogger(UserCenterServiceImpl.class);
	
	@Autowired
	public UserCenterServiceImpl(UserCenterMapper dao) {
		super(dao);
	}

	/**
	 * 获取用户的待收金额信息
	 */
	@Override
	public HashMap<String, BigDecimal> getUserDueInMoney(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserDueInMoney(userId);
	}

	/**
	 * 获取用户投资利息
	 */
	@Override
	public BigDecimal getUserInvestInterest(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserInvestInterest(userId);
	}

	/**
	 * 加息利息
	 */
	@Override
	public BigDecimal getUserRateInterest(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserRateInterest(userId);
	}

	/**
	 * 投资奖励
	 */
	@Override
	public BigDecimal getUserInIncentive(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserInIncentive(userId);
	}

	/**
	 * 红包收益
	 */
	@Override
	public BigDecimal getUserRedEnvelope(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserRedEnvelope(userId);
	}

	/**
	 * 推荐奖励
	 */
	@Override
	public BigDecimal getUserRecommendedAwards(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserRecommendedAwards(userId);
	}

	/**
	 * 获取用户的投资总额
	 */
	@Override
	public BigDecimal getUserInvestMoney(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserInvestMoney(userId);
	}

	/**
	 * 获取待收金额
	 */
	@Override
	public BigDecimal getUserIDueinMoney(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserIDueinMoney(userId);
	}

	/**
	 * 净利息
	 */
	@Override
	public BigDecimal getUserNetmargin(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.getUserNetmargin(userId);
	}

	/**
	 * 获取我的投资-待收中的投标信息
	 */
	@Override
	public PageList<InvestDueInModel> selectDueinBorrow(
			HashMap<String, Object> map, PageInfo pageInfo) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectDueinBorrow(map, pageInfo);
	}

	/**
	 * 查询我的投资-招标中投标信息
	 */
	@Override
	public PageList<InviteTendersModel> selectInviteTendersInfo(
			HashMap<String, Object> map, PageInfo pageInfo) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectInviteTendersInfo(map, pageInfo);
	}

	/**
	 * 查询我的投资-转让投标信息
	 */
	@Override
	public PageList<TransferTendersMode> selectTransferInfo(
			HashMap<String, Object> map, PageInfo pageInfo) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectTransferInfo(map, pageInfo);
	}

	/**
	 * 查询我的投资-已结清信息数据
	 */
	@Override
	public PageList<InvestmentInfoModel> selectInvestmentInfo(
			HashMap<String, Object> map, PageInfo pageInfo) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectInvestmentInfo(map, pageInfo);
	}
	
	/**
	 * 查询投资排名
	 */
	@Override
	public HashMap<String, Object> selectInvestmentRanking(String userId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectInvestmentRanking(userId);
	}

	@Override
	public InvestmentInfoModel selectInvestmentInfoByBorrowId(String userId,
			String borrowId) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		return ucmaper.selectInvestmentInfoByBorrowId(userId,borrowId);
	}

	/**
	 * 根据借款的id和用户查询对应投资的详细信息
	 */
	@Override
	public DueInDetailModel selectDueInDetailList(HashMap<String, Object> map) {
		UserCenterMapper ucmaper = (UserCenterMapper) getDao();
		List<DueInDetailModel> duieModlist =  ucmaper.selectDueInDetailList(map);
		if(null != duieModlist && duieModlist.size() == 1 ){
			return duieModlist.get(0);
		}else{
			return null;
		}
	}
	
	
}


