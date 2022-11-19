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
 * 2015年10月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity.impl;

import java.math.BigDecimal;
import java.util.Date;
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
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActParConRelMapper;
import com.yscf.core.dao.activity.PubActivityAreaMapper;
import com.yscf.core.dao.activity.PubInvestAwardMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.service.activity.IPubActivityAreaService;
import com.yscf.core.service.activity.IPubInvestAwardService;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;

/**
 * 
 * @ClassName : PubInvestAwardServiceImpl
 * @Description : 投资奖励业务服务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月14日 下午4:20:26
 */
@Service("pubInvestAwardServiceImpl")
public class PubInvestAwardServiceImpl extends BaseService<BaseEntity, String> implements IPubInvestAwardService {

	private Logger logger = LoggerFactory.getLogger(PubInvestAwardServiceImpl.class);

	@Autowired
	public PubInvestAwardServiceImpl(PubInvestAwardMapper dao) {
		super(dao);
	}
		
	/** 活动专区接口 */
	@Autowired
	public IPubActivityAreaService pubActivityAreaServiceImpl;

	@Autowired
	public ActActivityMapper actActivityMapper;

	@Autowired
	public ActParConRelMapper actParConRelMapper;
	
	@Autowired
	public PubActivityAreaMapper pubActivityAreaMapper;

	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	/**
	 * 
	 * @Description : 查询投资奖励信息列表,带分页
	 * @param pubInvestAward
	 *            投资奖励信息对象
	 * @param info
	 *            分页对象
	 * @return 投资奖励信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 下午4:22:04
	 */
	@Override
	public PageList<PubInvestAward> selectAllPage(PubInvestAward pubInvestAward, PageInfo info) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != pubInvestAward.getBeginApplyStartData() && !"".equals(pubInvestAward.getBeginApplyStartData())) {
			pubInvestAward.setBeginApplyStartData(EscfDateUtil.formatterDate(pubInvestAward.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubInvestAward.getEndApplyStartData() && !"".equals(pubInvestAward.getEndApplyStartData())) {
			pubInvestAward.setEndApplyStartData(EscfDateUtil.formatterDate(pubInvestAward.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != pubInvestAward.getBeginApplyFinishData() && !"".equals(pubInvestAward.getBeginApplyFinishData())) {
			pubInvestAward.setBeginApplyFinishData(EscfDateUtil.formatterDate(pubInvestAward.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubInvestAward.getEndApplyFinishData() && !"".equals(pubInvestAward.getEndApplyFinishData())) {
			pubInvestAward.setEndApplyFinishData(EscfDateUtil.formatterDate(pubInvestAward.getEndApplyFinishData(), 2));
		}
		return mapper.selectAllPage(pubInvestAward, info);
	}

	/**
	 * 
	 * @Description : 编辑投资奖励活动
	 * @param pubInvestAward
	 *            投资奖励信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 下午2:03:21
	 */
	@Override
	public void pubInvestAwardEdit(PubInvestAward pubInvestAward) throws FrameworkException {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		// 判断适用开始时间是否填写
		if (null == pubInvestAward.getStartTime() || pubInvestAward.getStartTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubInvestAward.setStartTime(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
		}
		// 判断适用结束时间是否填写
		if (null == pubInvestAward.getEndTime() || pubInvestAward.getEndTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubInvestAward.setEndTime("2099-12-31 23:59:59");
		}
		// 不为空修改，为空新增
		if (!pubInvestAward.getPid().trim().equals("") && !pubInvestAward.getPid().trim().equals("-1")) {
			pubInvestAward.setLastCreateTime(DateUtil.getToday().toString());// 设置修改时间
			pubInvestAward.setLastCreateUser(pubInvestAward.getCreateUser());// 设置修改人
			// 判断是否是加息劵
			if(pubInvestAward.getInvestAwardType().equals("3")){
				pubInvestAward.setInvestAwardValue(pubInvestAward.getInvestAwardValue().divide(new BigDecimal(100)));
			}
			// 修改投资奖励信息
			mapper.updateByPrimaryKeySelective(pubInvestAward);
			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 赋值
			actActivity.setActTag(pubInvestAward.getActTag());// 设置活动标签
			actActivity.setActName(pubInvestAward.getInvestAwardName());// 设置活动名称
			actActivity.setStartDate(pubInvestAward.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubInvestAward.getEndTime());// 设置活动结束时间
			actActivity.setObjectId(pubInvestAward.getPid());// 设置投资奖励ID
			actActivity.setActivityType("4");// 设置活动类型为投资奖励
			actActivity.setIsBirPrivilege(pubInvestAward.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubInvestAward.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubInvestAward.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubInvestAward.getInvAwaDesc());// 设置活动描述
			actActivity.setStatus(Constant.PUBLIC_STATUS);// 设置活动状态
			actActivity.setIsDisable(Integer.parseInt(pubInvestAward.getStatus()));// 设置活动是否启用
			actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
			actActivity.setLastUpdateUser(pubInvestAward.getCreateUser());// 设置最后修改人ID
			// 修改活动信息
			actActivityMapper.updateByObjectIdAndActivityType(actActivity);
			
			// 修改活动专区的信息
			if(pubInvestAward.getInvestAwardType().equals("3")){
				// 创建活动专区对象 
				PubActivityArea pubActivityArea = new PubActivityArea();
				pubActivityArea.setActivityCode(pubInvestAward.getActCode());// 设置活动编号
				pubActivityArea.setActivityName(pubInvestAward.getInvestAwardName());// 设置活动名称
				pubActivityArea.setActivityStartDate(pubInvestAward.getStartTime());// 设置活动开始时间
				pubActivityArea.setActivityEndDate(pubInvestAward.getEndTime());// 设置活动结束时间
				pubActivityArea.setLastUpdateUser(pubInvestAward.getCreateUser());// 设置最后修改时间
				pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改人ID
				pubActivityArea.setIsDisable(Integer.parseInt(pubInvestAward.getStatus()));// 设置是否启用
				// 新增数据到活动专区
				pubActivityAreaMapper.updateActiviayByArea(pubActivityArea);
			}
			
			// 先删除原条件信息
			actParConRelMapper.deleteByObjectIdAndActivityType(pubInvestAward.getPid(), "4");
			// 判断是否选取了条件信息
			if (null != pubInvestAward.getCondIds() && !pubInvestAward.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubInvestAward.getCondIds().split(",");
				// 查询出来活动ID
				String activityId = actActivityMapper.selectByObjectIdAndActivityType(pubInvestAward.getPid(), "4");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubInvestAward.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}

		} else {
			String pubInvestAwardId = pubInvestAward.getPK();// 获取投资奖励活动ID
			pubInvestAward.setPid(pubInvestAwardId);// 设置PID
			pubInvestAward.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			pubInvestAward.setCreateUser(pubInvestAward.getCreateUser());// 设置创建人
			// 判断是否是加息劵
			if(pubInvestAward.getInvestAwardType().equals("3")){
				pubInvestAward.setInvestAwardValue(pubInvestAward.getInvestAwardValue().divide(new BigDecimal(100)));
			}
			// 新增投资奖励信息
			mapper.insertSelective(pubInvestAward);

			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 获取活动ID
			String activityId = actActivity.getPK();
			// 赋值
			actActivity.setPid(activityId);// 设置PID
			actActivity.setObjectId(pubInvestAwardId);// 设置投资奖励ID
			actActivity.setActCode(sysCreateCodeRuleService.generatNoRule("TO", "7", 4, pubInvestAward.getCreateUser()));
			actActivity.setActTag(pubInvestAward.getActTag());// 设置活动标签
			actActivity.setActName(pubInvestAward.getInvestAwardName());// 设置活动名称
			actActivity.setStartDate(pubInvestAward.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubInvestAward.getEndTime());// 设置活动结束时间
			actActivity.setActivityType("4");// 设置活动类型为投资奖励
			actActivity.setIsBirPrivilege(pubInvestAward.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubInvestAward.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubInvestAward.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubInvestAward.getInvAwaDesc());// 设置活动描述
			actActivity.setStatus(Constant.PUBLIC_STATUS);// 设置活动状态
			actActivity.setIsDisable(Integer.parseInt(pubInvestAward.getStatus()));// 设置活动是否启用
			actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			actActivity.setCreateUser(pubInvestAward.getCreateUser());// 设置创建人
			// 新增活动
			actActivityMapper.insertSelective(actActivity);
			if(pubInvestAward.getInvestAwardType().equals("3")){
				actActivity.setActivityAreaType(2);// 设置活动专区类型为加息劵
				// 新增数据到活动专区
				pubActivityAreaServiceImpl.pubAreaEditActivity(actActivity);
			}
			// 判断是否选取了条件信息
			if (null != pubInvestAward.getCondIds() && !pubInvestAward.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubInvestAward.getCondIds().split(",");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubInvestAward.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}
		}

	}

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return 投资奖励活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 下午3:11:54
	 */
	@Override
	public PubInvestAward getPubInvestAwardByPid(String pid) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		PubInvestAward pubInvestAward = new PubInvestAward();
		pubInvestAward = mapper.selectByPrimaryKey(pid);
		return pubInvestAward;
	}

	/**
	 * 
	 * @Description : 批量删除投资奖励活动
	 * @param pids
	 *            投资奖励ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 下午6:53:20
	 */
	@Override
	public void deleteBatch(String pids) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		try {
			if (null != pids && !pids.equals("")) {
				String[] str = pids.split(",");
				// 删除投资奖励活动
				mapper.deleteBatch(str);
				// 删除活动信息
				actActivityMapper.updateStatusBatch("-1", str, "4");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除投资奖励活动:" + e.getMessage());
			}
		}
	}

	/**
	 * Description：根据客户id查我的赠劵
	 * 
	 * @author heng.wang
	 * @date 2015年11月24日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	@Override
	public List<PubInvestAward> selectMyCoupon(String customerId,Integer pageIndex,Integer pageSize) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		Map<String,Object> map = new HashMap<String, Object>();
		if("".equals(pageIndex) || pageIndex==null){
			pageIndex=0;
		}
		if("".equals(pageSize) || pageSize==null){
			pageSize=12;
		}
		map.put("customerId", customerId);
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		return mapper.selectMyCoupon(map);
	}

	@Override
	public Integer selectMyCouponCount(String customerId) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		return mapper.selectMyCouponCount(customerId);
	}

	//查我已过期的赠劵
	@Override
	public List<PubInvestAward> selectMyCouponExpired(String customerId,
			Integer pageIndex, Integer pageSize) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		Map<String,Object> map = new HashMap<String, Object>();
		if("".equals(pageIndex) || pageIndex==null){
			pageIndex=0;
		}
		if("".equals(pageSize) || pageSize==null){
			pageSize=12;
		}
		map.put("customerId", customerId);
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		return mapper.selectMyCouponExpired(map);
	}

	@Override
	public Integer selectMyCouponExpiredCount(String customerId) {
		PubInvestAwardMapper mapper = (PubInvestAwardMapper) getDao();
		return mapper.selectMyCouponExpiredCount(customerId);
	}
}
