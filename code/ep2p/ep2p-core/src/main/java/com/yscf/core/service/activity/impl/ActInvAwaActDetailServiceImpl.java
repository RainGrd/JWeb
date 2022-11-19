package com.yscf.core.service.activity.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActInvAwaActDetailMapper;
import com.yscf.core.dao.activity.PubActivityAreaMapper;
import com.yscf.core.dao.activity.PubInvestAwardMapper;
import com.yscf.core.dao.user.CustInterestTicketMapper;
import com.yscf.core.exception.InterestNotEnoughException;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.service.activity.IActInvAwaActDetailService;

/**
 * 
 * @ClassName : ActInvAwaActDetailServiceImpl
 * @Description : 投资奖励明细商务接口
 * @Author : Qing.Cai
 * @Date : 2016年3月8日 下午4:03:24
 */
@Service("actInvAwaActDetailServiceImpl")
public class ActInvAwaActDetailServiceImpl extends BaseService<BaseEntity, String> implements IActInvAwaActDetailService {

	private Logger logger = LoggerFactory.getLogger(ActInvAwaActDetailServiceImpl.class);

	@Autowired
	public ActInvAwaActDetailServiceImpl(ActInvAwaActDetailMapper dao) {
		super(dao);
	}

	@Autowired
	private ActInvAwaActDetailMapper actInvAwaActDetailMapper;
	@Autowired
	private PubInvestAwardMapper pubInvestAwardMapper;
	/** 加息劵Mapper接口 */
	@Autowired
	public CustInterestTicketMapper custInterestTicketMapper;
	/** 活动Mapper接口 */
	@Autowired
	public ActActivityMapper actActivityMapper;
	/** 活动专区Mapper接口 */
	@Autowired
	public PubActivityAreaMapper pubActivityAreaMapper;

	@Override
	public PageList<ActInvAwaActDetail> selectCusPointDetailsById(String pid, PageInfo info) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		ActInvAwaActDetail actInvAwaActDetail = new ActInvAwaActDetail();
		actInvAwaActDetail.setCustomerId(pid);
		PageList<ActInvAwaActDetail> cusPoint = mapper.selectCusPointDetailsById(actInvAwaActDetail, info);
		Map<String, BigDecimal> map = mapper.selectCusPointDetailsByIdSum(actInvAwaActDetail);
		ActInvAwaActDetail actInvAwaActDetails = new ActInvAwaActDetail();
		actInvAwaActDetails.setInvestAwardValue(map.get("investAwardValue"));
		cusPoint.add(actInvAwaActDetails);
		return cusPoint;
	}

	@Override
	public PageList<ActInvAwaActDetail> selectJingyanDetailsById(String pid, PageInfo info) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		ActInvAwaActDetail actInvAwaActDetail = new ActInvAwaActDetail();
		actInvAwaActDetail.setCustomerId(pid);
		PageList<ActInvAwaActDetail> jingyan = mapper.selectJingyanDetailsById(actInvAwaActDetail, info);
		Map<String, BigDecimal> map = mapper.selectJingyanDetailsByIdSum(actInvAwaActDetail);
		ActInvAwaActDetail actInvAwaActDetails = new ActInvAwaActDetail();
		actInvAwaActDetails.setInvestAwardValue(map.get("investAwardValue"));
		jingyan.add(actInvAwaActDetails);
		return jingyan;
	}

	@Override
	public PageList<ActInvAwaActDetail> selectJiaXiDetailsById(String pid, PageInfo info) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		ActInvAwaActDetail actInvAwaActDetail = new ActInvAwaActDetail();
		actInvAwaActDetail.setCustomerId(pid);
		return mapper.selectJiaXiDetailsById(actInvAwaActDetail, info);
	}

	@Override
	public PageList<ActInvAwaActDetail> selectAllPageByCondition(ActInvAwaActDetail actInvAwaActDetail, PageInfo info) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		PageList<ActInvAwaActDetail> list = new PageList<ActInvAwaActDetail>();
		// 获取分页的数据
		list = mapper.selectAllPageByCondition(actInvAwaActDetail, info);
		if (null != list && list.size() != 0) {
			ActInvAwaActDetail aiaad = new ActInvAwaActDetail();
			aiaad.setParticipantsNumber(mapper.selectParticipantsNumberByCondition(actInvAwaActDetail).toString());// 设置总的参与人数
			// 添加到list
			list.add(aiaad);
		}
		return list;
	}

	@Override
	public PageList<ActInvAwaActDetail> selectAllPageDetail(ActInvAwaActDetail actExpActDetail, PageInfo info) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		PageList<ActInvAwaActDetail> list = new PageList<ActInvAwaActDetail>();
		// 获取分页的数据
		list = mapper.selectAllPageDetail(actExpActDetail, info);
		return list;
	}

	@Override
	public List<ActInvAwaActDetail> selectAllPageDetailEom(ActInvAwaActDetail actInvAwaActDetail, ExportObjectModel eom) {
		ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("map", actInvAwaActDetail);
		map.put("expm", eom);

		return mapper.selectAllPageDetailEom(map);
	}

	/**
	 * 
	 * Description：<br>
	 * 查询客户拥有的 投资奖励 根据奖励类型
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param customerId
	 *            客户id
	 * @param investAwardType
	 *            活动类型 1 积分 2经验 3加息劵
	 * @return
	 */
	@Override
	public List<ActInvAwaActDetail> selectAllPageByTypeId(String customerId, String investAwardType) {
		return actInvAwaActDetailMapper.selectAllPageByTypeId(customerId, investAwardType);
	}

	/**
	 * 
	 * @Description : 领取加息劵
	 * @param investAwardId
	 *            加息劵活动ID
	 * @param customerId
	 *            领取人ID
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午8:04:27
	 */
	@Override
	public void receiveInterest(String investAwardId, String customerId) throws FrameworkException, InterestNotEnoughException {
		try {
			ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
			// 获取当前时间
			String dateTime = DateUtil.getToday().toString();
			// 获取投资奖励活动对象
			PubInvestAward pubInvestAward = pubInvestAwardMapper.selectByPrimaryKey(investAwardId);
			// 获取当前活动领取的人数
			Integer count = mapper.selectInterestCount(investAwardId);
			// 如果加息劵总数大于等于当前已领取的人数,就表示当前活动加息劵已经领取完毕
			if (count >= pubInvestAward.getInterest()) {
				throw new InterestNotEnoughException("加息劵不够(已经领取完):");
			}
			// 新增活动加息劵
			ActInvAwaActDetail actInvAwaActDetail = new ActInvAwaActDetail();
			// 赋值
			actInvAwaActDetail.setPid(actInvAwaActDetail.getPK());// 设置主键
			actInvAwaActDetail.setActivityId(actActivityMapper.selectActivityId(Constant.ACTIVITY_TYPE_4, investAwardId));// 设置活动ID
			actInvAwaActDetail.setInvestAwardId(investAwardId);// 设置投资奖励活动ID
			actInvAwaActDetail.setSmsId(pubInvestAward.getSmsId());// 设置短信ID
			actInvAwaActDetail.setCustomerId(customerId);// 设置领取人ID
			actInvAwaActDetail.setGetTime(dateTime);// 是指获得时间
			actInvAwaActDetail.setUseStatus(Constant.USE_STATUS_2);// 设置使用状态:未使用
			actInvAwaActDetail.setCreateUser(customerId);// 设置创建人
			actInvAwaActDetail.setCreateTime(dateTime);// 设置创建时间
			actInvAwaActDetail.setVersion(Constant.PUBLIC_VERSION);// 设置版本号
			// 新增投资奖励
			mapper.insertSelective(actInvAwaActDetail);

			// 新增客户加息劵
			CustInterestTicket custInterestTicket = new CustInterestTicket();
			// 赋值
			custInterestTicket.setPid(custInterestTicket.getPK());// 设置主键
			custInterestTicket.setActInvAwaActDetailId(actInvAwaActDetail.getPid());// 设置活动ID
			custInterestTicket.setCustomerId(customerId);// 设置客户ID
			custInterestTicket.setName(pubInvestAward.getInvestAwardName());// 设置加息劵名称
			custInterestTicket.setScale(pubInvestAward.getInvestAwardValue().doubleValue());// 设置加息劵比例
			custInterestTicket.setValidity(pubInvestAward.getValidity());// 设置有效期
			// 创建计算时间的对象
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(DateUtil.getToday());
			gc.add(5, pubInvestAward.getValidity());
			String endTime = DateUtil.format(gc.getTime());
			custInterestTicket.setStartTime(dateTime);// 设置有效期开始时间
			custInterestTicket.setEndTime(endTime);// 设置有效期结束时间
			custInterestTicket.setGetType(Constant.INTEREST_GET_TYPE_1);// 设置加息劵获得类型
			custInterestTicket.setUseStatus(Constant.USE_STATUS_2);// 设置加息劵使用状态
			custInterestTicket.setInterestDesc("领取加息劵一张，比例为：" + pubInvestAward.getInvestAwardValue().doubleValue() * 100 + "%加息劵");// 设置描述
			custInterestTicket.setCreateUser(customerId);// 设置创建人
			custInterestTicket.setCreateTime(dateTime);// 设置创建时间
			// 新增到客户加息劵表
			custInterestTicketMapper.insertSelective(custInterestTicket);

		} catch (InterestNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("领取加息劵:" + e.getMessage());
			}
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Description : 检查当前用户在当前领取加息劵活动下的状态
	 * @param investAwardId
	 *            加息劵活动ID
	 * @param customerId
	 *            用户ID
	 * @return (1.未领取 2.已领取 3.抢光了)
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午2:50:02
	 */
	@Override
	public String checkUserReceiveInterestStatus(String investAwardId, String customerId) {
		String redStatus = "";
		try {
			ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
			List<ActInvAwaActDetail> list = null;
			// 根据活动ID查询红包未领取的集合
			list = mapper.selectInterestListByInvestAwardId(investAwardId);
			// 判断是否为空
			if(null == list || list.size() == 0){
				list = new ArrayList<ActInvAwaActDetail>();
			}
			// 查询出来当前加息劵的对象
			PubInvestAward pubInvestAward = pubInvestAwardMapper.selectByPrimaryKey(investAwardId);
			// 加息劵数量进行比较
			if (list.size() == pubInvestAward.getInterest()) {
				// 如果已领取的数量=总数量,就表示已经抢光了
				redStatus = "3";
			} else {
				// 查询当前用户是否领取了当前加息劵活动
				if (mapper.checkUserIsAttend(investAwardId, customerId) == 0) {
					// 如果等于0,未领取
					redStatus = "1";
				} else {
					// 不等于0,已领取
					redStatus = "2";
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("检查当前用户在当前领取加息劵活动下的状态:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return redStatus;
	}

	/**
	 * 
	 * @Description : 查询已领取加息劵的列表
	 * @param investAwardId
	 *            加息劵活动
	 * @return 领取加息劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午4:49:51
	 */
	@Override
	public List<ActInvAwaActDetail> selectReceiveInvestDetail(String investAwardId) {
		List<ActInvAwaActDetail> list = null;
		try {
			ActInvAwaActDetailMapper mapper = (ActInvAwaActDetailMapper) getDao();
			// 查询已领取的加息劵集合
			list = mapper.selectInterestListByInvestAwardId(investAwardId);
			// 判断是否为空
			if(null == list || list.size() == 0){
				list = new ArrayList<ActInvAwaActDetail>();
			}
			// 循环改变值
			for (int i = 0; i < list.size(); i++) {
				// 设置日期
				list.get(i).setGetTime(list.get(i).getInvAwaDesc());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询已领取加息劵的列表:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

}
