/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.yscf.core.dao.activity.ActRedpActDetailMapper;
import com.yscf.core.exception.RedNotEnoughException;
import com.yscf.core.model.activity.ActRedpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.IActRedpActDetailService;
import com.yscf.core.service.financial.impl.BizAccountCommonServiceImpl;
import com.yscf.core.service.system.impl.SysAccountServiceImpl;

/**
 * 
 * @ClassName : ActRedpActDetailServiceImpl
 * @Description : 红包明细商务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月10日 下午3:07:31
 */
@Service("actRedpActDetailServiceImpl")
public class ActRedpActDetailServiceImpl extends BaseService<BaseEntity, String> implements IActRedpActDetailService {

	private Logger logger = LoggerFactory.getLogger(ActRedpActDetailServiceImpl.class);

	@Autowired
	public ActRedpActDetailServiceImpl(ActRedpActDetailMapper dao) {
		super(dao);
	}

	/** 普通资金处理实现类 */
	@Autowired
	public BizAccountCommonServiceImpl bizAccountCommonServiceImpl;

	/** 普通资金处理实现类 */
	@Autowired
	public SysAccountServiceImpl sysAccountServiceImpl;

	/**
	 * 
	 * @Description : 查询红包活动列表,带分页
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @param info
	 *            分页对象
	 * @return 红包活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:40
	 */
	@Override
	public PageList<ActRedpActDetail> selectAllPage(ActRedpActDetail actRedpActDetail, PageInfo info) {
		ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
		PageList<ActRedpActDetail> list = new PageList<ActRedpActDetail>();
		list = mapper.selectAllPage(actRedpActDetail, info);
		if (null != list && list.size() != 0) {
			ActRedpActDetail arad = new ActRedpActDetail();
			// 设置红包金额总计
			arad.setBonusTotal(mapper.selectBonusTotalByCondition(actRedpActDetail));
			// 设置已领取金额的总计
			arad.setHasReceiveTotal(mapper.selectHasReceiveTotalByCondition(actRedpActDetail));
			// 设置未领取金额的总计
			arad.setNotReceiveTotal(mapper.selectNotReceiveTotalByCondition(actRedpActDetail));
			// 设置参与人数的总计
			arad.setParticipantsNumber(mapper.selectParticipantsNumberByCondition(actRedpActDetail));
			// 添加到列表
			list.add(arad);
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询红包活动明细列表,带分页
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @param info
	 *            分页对象
	 * @return 红包活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:51
	 */
	@Override
	public PageList<ActRedpActDetail> selectAllPageDetail(ActRedpActDetail actRedpActDetail, PageInfo info) {
		ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
		PageList<ActRedpActDetail> list = new PageList<ActRedpActDetail>();
		list = mapper.selectAllPageDetail(actRedpActDetail, info);
		if (null != list && list.size() != 0) {
			ActRedpActDetail arad = new ActRedpActDetail();
			// 设置领取金额明细的总计
			arad.setGetAmount(mapper.selectReceiveTotalDetail(actRedpActDetail));
			// 添加到列表
			list.add(arad);
		}
		return list;
	}

	/**
	 * 
	 * @Description : 导出查询
	 * @param actVipActDetail
	 *            红包活动明细对象
	 * @param eom
	 *            导出对象
	 * @return 红包活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午11:44:54
	 */
	@Override
	public List<ActRedpActDetail> selectAllPageDetailExport(ActRedpActDetail actRedpActDetail, ExportObjectModel eom) {
		List<ActRedpActDetail> list = null;
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("actRedpActDetail", actRedpActDetail);
			map.put("expm", eom);

			list = mapper.selectAllPageDetailExport(map);

			if (null == list) {
				list = new ArrayList<ActRedpActDetail>();
			} else {
				// 设置总计
				BigDecimal getAmount = new BigDecimal(0);
				for (ActRedpActDetail vo : list) {
					getAmount.add(vo.getGetAmount());
					if (vo.getGetTime().indexOf(".") != -1) {
						// 格式化日期
						vo.setGetTime(vo.getGetTime().substring(0, vo.getGetTime().indexOf(".")));
					}
				}
				ActRedpActDetail arad = new ActRedpActDetail();
				// 设置领取金额明细的总计
				arad.setGetAmount(getAmount);
				arad.setCustomerName("总计");
				// 添加到列表
				list.add(arad);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}

	/**
	 * 
	 * @Description : 判断用户是否领取
	 * @param redpacketId
	 *            红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 0.未领取 1.已领取
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午3:06:21
	 */
	@Override
	public Integer checkUserIsAttend(String redpacketId, String userId) throws FrameworkException {
		// 是否领取,默认未领取
		Integer isAttend = 2;
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
			// 获取用户是否参与到此次活动
			isAttend = mapper.checkUserIsAttend(redpacketId, userId) > 0 ? 1 : 0;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("判断用户是否领取:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return isAttend;
	}

	/**
	 * 
	 * @Description : 领取红包
	 * @param redpacketId
	 *            红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 领取金额
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午3:06:18
	 */
	@Override
	public Double receiveRed(String redpacketId, String userId) throws FrameworkException, RedNotEnoughException {
		// 领取的金额
		Double receiveAmount = 0.0;
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
			// 获取所有未领取的红包数
			List<ActRedpActDetail> list = mapper.selectRedListByRedpacketId(redpacketId);
			// 获取未领取的红包的大小
			int size = list.size();
			// 判断大小是否大于1,如果小于1说明当前红包已经领取完毕
			if (size == 0) {
				throw new RedNotEnoughException("红包不够(已经领取完):");
			}
			// 生成随机数,领取其中的随机红包
			int index = (int) (Math.random() * size);
			// 获取用户领取的红包对象
			ActRedpActDetail arad = list.get(index);
			// 赋值
			arad.setCustomerId(userId);// 领取人
			arad.setUseStatus(Constant.USE_STATUS_1);// 使用状态
			arad.setUseTime(DateUtil.getToday().toString());// 使用时间
			arad.setGetTime(DateUtil.getToday().toString());// 获得使用
			arad.setLastUpdateTime(DateUtil.getToday().toString());// 最后修改时间
			arad.setLastUpdateUser(userId);// 最后修改人
			// 修改
			mapper.updateRedReceiveUser(arad);
			// 备注
			String note = "【领取红包】：" + arad.getGetAmount() + "元";
			// 修改系统资金
			sysAccountServiceImpl.updateSubSystemAmount(arad.getGetAmount(), userId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1030, null, note);
			// 修改单个用户普通资金的接口
			bizAccountCommonServiceImpl.modifyAvailableAmountWithTallyCreateNoTran(userId, Constant.CUST_FUND_BUSINESS_TYPE_1, TradeTypeConstant.RETURNS_TYPE_415, arad.getGetAmount(), note, DateUtil.getToday());
			// 领取成功后,返回领取的实际金额
			receiveAmount = arad.getGetAmount().doubleValue();
		} catch (RedNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("领取红包:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return receiveAmount;
	}

	/**
	 * 
	 * @Description : 查询红包领取明细
	 * @param redpacketId
	 *            红包ID
	 * @return 已领取明细
	 * @Author : Qing.Cai
	 * @Date : 2016年3月8日 下午7:10:13
	 */
	@Override
	public List<ActRedpActDetail> selectReceiveRedDetail(String redpacketId) {
		List<ActRedpActDetail> list = null;
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
			// 获取所有已领取的红包数
			list = mapper.selectReceiveRedDetail(redpacketId);
			// 循环改变值
			for (int i = 0; i < list.size(); i++) {
				// 设置日期
				list.get(i).setGetTime(list.get(i).getRemark());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询红包领取明细:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Description : 检查当前用户在当前抢红包活动下的状态
	 * @param redpacketId
	 *            抢红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 活动状态(1.未领取 2.已领取 3.抢光了)
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午11:55:33
	 */
	@Override
	public String checkUserReceiveRedStatus(String redpacketId, String userId) {
		String redStatus = "";
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
			List<ActRedpActDetail> list = null;
			// 根据活动ID查询红包未领取的集合
			list = mapper.selectRedListByRedpacketId(redpacketId);
			// 判断是否为空s
			if (null == list) {
				list = new ArrayList<ActRedpActDetail>();
			}
			if (list.size() == 0) {
				// 未领取的红包已经为0,说明已经抢光了
				redStatus = "3";
			} else {
				// 查询当前用户是否领取了当前红包活动
				if (mapper.checkUserIsAttend(redpacketId, userId) == 0) {
					// 如果等于0,未领取
					redStatus = "1";
				} else {
					// 不等于0,已领取
					redStatus = "2";
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("检查当前用户在当前抢红包活动下的状态:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return redStatus;
	}

	/**
	 * 
	 * @Description : 查询在当前抢红包活动下用户领取金额
	 * @param redpacketId
	 *            抢红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 领取金额
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 下午3:20:30
	 */
	@Override
	public Double selectUserReceiveAmount(String redpacketId, String userId) {
		Double receiveAmount = 0.0;
		try {
			ActRedpActDetailMapper mapper = (ActRedpActDetailMapper) getDao();
			receiveAmount = mapper.selectUserReceiveAmount(redpacketId, userId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询在当前抢红包活动下用户领取金额:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return receiveAmount;
	}

}
