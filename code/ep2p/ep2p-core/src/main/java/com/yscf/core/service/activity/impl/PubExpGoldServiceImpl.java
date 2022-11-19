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

import java.util.Date;

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
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActParConRelMapper;
import com.yscf.core.dao.activity.PubExpGoldMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.PubExpGold;
import com.yscf.core.service.activity.IPubExpGoldService;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;

/**
 * 
 * @ClassName : PubExpGoldServiceImpl
 * @Description : 体验金业务服务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:20:26
 */
@Service("pubExpGoldServiceImpl")
public class PubExpGoldServiceImpl extends BaseService<BaseEntity, String> implements IPubExpGoldService {

	private Logger logger = LoggerFactory.getLogger(PubExpGoldServiceImpl.class);

	@Autowired
	public PubExpGoldServiceImpl(PubExpGoldMapper dao) {
		super(dao);
	}
	
	@Autowired
	public ActActivityMapper actActivityMapper;

	@Autowired
	public ActParConRelMapper actParConRelMapper;

	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	@Autowired
	public PubExpGoldMapper pubExpGoldMapper;

	/**
	 * 
	 * @Description : 查询体验金信息列表,带分页
	 * @param pubExpGold
	 *            体验金信息对象
	 * @param info
	 *            分页对象
	 * @return 体验金信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:22:04
	 */
	@Override
	public PageList<PubExpGold> selectAllPage(PubExpGold pubExpGold, PageInfo info) {
		PubExpGoldMapper mapper = (PubExpGoldMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != pubExpGold.getBeginApplyStartData() && !"".equals(pubExpGold.getBeginApplyStartData())) {
			pubExpGold.setBeginApplyStartData(EscfDateUtil.formatterDate(pubExpGold.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubExpGold.getEndApplyStartData() && !"".equals(pubExpGold.getEndApplyStartData())) {
			pubExpGold.setEndApplyStartData(EscfDateUtil.formatterDate(pubExpGold.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != pubExpGold.getBeginApplyFinishData() && !"".equals(pubExpGold.getBeginApplyFinishData())) {
			pubExpGold.setBeginApplyFinishData(EscfDateUtil.formatterDate(pubExpGold.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubExpGold.getEndApplyFinishData() && !"".equals(pubExpGold.getEndApplyFinishData())) {
			pubExpGold.setEndApplyFinishData(EscfDateUtil.formatterDate(pubExpGold.getEndApplyFinishData(), 2));
		}
		return mapper.selectAllPage(pubExpGold, info);
	}

	/**
	 * 
	 * @Description : 编辑体验金活动
	 * @param pubExpGold
	 *            体验金信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:03:21
	 */
	@Override
	public void pubExpGoldEdit(PubExpGold pubExpGold) throws FrameworkException {
		PubExpGoldMapper mapper = (PubExpGoldMapper) getDao();
		// 判断适用开始时间是否填写
		if (null == pubExpGold.getEffTime() || pubExpGold.getEffTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubExpGold.setEffTime(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
		}
		// 判断适用结束时间是否填写
		if (null == pubExpGold.getExpTime() || pubExpGold.getExpTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubExpGold.setExpTime("2099-12-31 23:59:59");
		}
		// 不为空修改，为空新增
		if (!pubExpGold.getPid().trim().equals("") && !pubExpGold.getPid().trim().equals("-1")) {
			pubExpGold.setLastCreateTime(DateUtil.getToday().toString().toString());// 设置修改时间
			pubExpGold.setLastCreateUser(pubExpGold.getCreateUser());// 设置修改人
			// 修改体验金信息
			mapper.updateByPrimaryKeySelective(pubExpGold);
			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 赋值
			actActivity.setActTag(pubExpGold.getActTag());// 设置活动标签
			actActivity.setActName(pubExpGold.getExpName());// 设置活动名称
			actActivity.setStartDate(pubExpGold.getEffTime());// 设置活动开始时间
			actActivity.setEndDate(pubExpGold.getExpTime());// 设置活动结束时间
			actActivity.setObjectId(pubExpGold.getPid());// 设置体验金ID
			actActivity.setActivityType("3");// 设置活动类型为体验金
			actActivity.setIsBirPrivilege(pubExpGold.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubExpGold.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubExpGold.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubExpGold.getExpGolDesc());// 设置活动描述
			actActivity.setStatus(pubExpGold.getStatus());// 设置活动状态
			actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
			actActivity.setLastUpdateUser(pubExpGold.getCreateUser());// 设置最后修改人ID
			// 修改活动信息
			actActivityMapper.updateByObjectIdAndActivityType(actActivity);
			// 先删除原条件信息
			actParConRelMapper.deleteByObjectIdAndActivityType(pubExpGold.getPid(), "3");
			// 判断是否选取了条件信息
			if (null != pubExpGold.getCondIds() && !pubExpGold.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubExpGold.getCondIds().split(",");
				// 查询出来活动ID
				String activityId = actActivityMapper.selectByObjectIdAndActivityType(pubExpGold.getPid(), "3");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubExpGold.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}

		} else {
			String pubExpGoldId = pubExpGold.getPK();// 获取体验金活动ID
			pubExpGold.setPid(pubExpGoldId);// 设置PID
			pubExpGold.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			pubExpGold.setCreateUser(pubExpGold.getCreateUser());// 设置创建人
			// 新增体验金信息
			mapper.insertSelective(pubExpGold);

			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 获取活动ID
			String activityId = actActivity.getPK();
			// 赋值
			actActivity.setPid(activityId);// 设置PID
			actActivity.setObjectId(pubExpGoldId);// 设置体验金ID
			actActivity.setActCode(sysCreateCodeRuleService.generatNoRule("TY", "7", 4, pubExpGold.getCreateUser()));
			actActivity.setActTag(pubExpGold.getActTag());// 设置活动标签
			actActivity.setActName(pubExpGold.getExpName());// 设置活动名称
			actActivity.setStartDate(pubExpGold.getEffTime());// 设置活动开始时间
			actActivity.setEndDate(pubExpGold.getExpTime());// 设置活动结束时间
			actActivity.setActivityType("3");// 设置活动类型为体验金
			actActivity.setIsBirPrivilege(pubExpGold.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubExpGold.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubExpGold.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubExpGold.getExpGolDesc());// 设置活动描述
			actActivity.setStatus(pubExpGold.getStatus());// 设置活动状态
			actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			actActivity.setCreateUser(pubExpGold.getCreateUser());// 设置创建人
			// 新增活动
			actActivityMapper.insertSelective(actActivity);
			// 判断是否选取了条件信息
			if (null != pubExpGold.getCondIds() && !pubExpGold.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubExpGold.getCondIds().split(",");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubExpGold.getCreateUser());// 设置创建人
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
	 * @return 体验金活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午3:11:54
	 */
	@Override
	public PubExpGold getPubExpGoldByPid(String pid) {
		PubExpGoldMapper mapper = (PubExpGoldMapper) getDao();
		PubExpGold pubExpGold = new PubExpGold();
		pubExpGold = mapper.selectByPrimaryKey(pid);
		return pubExpGold;
	}

	/**
	 * 
	 * @Description : 批量删除体验金活动
	 * @param pids
	 *            体验金ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	@Override
	public void deleteBatch(String pids) {
		PubExpGoldMapper mapper = (PubExpGoldMapper) getDao();
		try {
			if (null != pids && !pids.equals("")) {
				String[] str = pids.split(",");
				// 删除体验金活动
				mapper.deleteBatch(str);
				// 删除活动信息
				actActivityMapper.updateStatusBatch("-1", str, "3");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除体验金活动:" + e.getMessage());
			}
		}
	}

	@Override
	public PubExpGold selectByActivityId(String activityId) {
		return pubExpGoldMapper.selectByActivityId(activityId);
	}

}
