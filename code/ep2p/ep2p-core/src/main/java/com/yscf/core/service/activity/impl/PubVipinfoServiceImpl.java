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
import com.yscf.core.dao.activity.PubVipinfoMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.PubVipinfo;
import com.yscf.core.service.activity.IPubVipinfoService;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;

/**
 * 
 * @ClassName : PubVipinfoServiceImpl
 * @Description : VIP信息业务服务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:20:26
 */
@Service("pubVipinfoServiceImpl")
public class PubVipinfoServiceImpl extends BaseService<BaseEntity, String> implements IPubVipinfoService {

	private Logger logger = LoggerFactory.getLogger(PubVipinfoServiceImpl.class);

	@Autowired
	public PubVipinfoServiceImpl(PubVipinfoMapper dao) {
		super(dao);
	}

	@Autowired
	public ActActivityMapper actActivityMapper;

	@Autowired
	public ActParConRelMapper actParConRelMapper;

	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	/**
	 * 
	 * @Description : 查询VIP信息信息列表,带分页
	 * @param pubVipinfo
	 *            VIP信息信息对象
	 * @param info
	 *            分页对象
	 * @return VIP信息信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:22:04
	 */
	@Override
	public PageList<PubVipinfo> selectAllPage(PubVipinfo pubVipinfo, PageInfo info) {
		PubVipinfoMapper mapper = (PubVipinfoMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != pubVipinfo.getBeginApplyStartData() && !"".equals(pubVipinfo.getBeginApplyStartData())) {
			pubVipinfo.setBeginApplyStartData(EscfDateUtil.formatterDate(pubVipinfo.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubVipinfo.getEndApplyStartData() && !"".equals(pubVipinfo.getEndApplyStartData())) {
			pubVipinfo.setEndApplyStartData(EscfDateUtil.formatterDate(pubVipinfo.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != pubVipinfo.getBeginApplyFinishData() && !"".equals(pubVipinfo.getBeginApplyFinishData())) {
			pubVipinfo.setBeginApplyFinishData(EscfDateUtil.formatterDate(pubVipinfo.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubVipinfo.getEndApplyFinishData() && !"".equals(pubVipinfo.getEndApplyFinishData())) {
			pubVipinfo.setEndApplyFinishData(EscfDateUtil.formatterDate(pubVipinfo.getEndApplyFinishData(), 2));
		}
		return mapper.selectAllPage(pubVipinfo, info);
	}

	/**
	 * 
	 * @Description : 编辑VIP信息活动
	 * @param pubVipinfo
	 *            VIP信息信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:03:21
	 */
	@Override
	public void pubVipinfoEdit(PubVipinfo pubVipinfo) throws FrameworkException {
		PubVipinfoMapper mapper = (PubVipinfoMapper) getDao();
		// 判断适用开始时间是否填写
		if (null == pubVipinfo.getStartTime() || pubVipinfo.getStartTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubVipinfo.setStartTime(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
		}
		// 判断适用结束时间是否填写
		if (null == pubVipinfo.getEndTime() || pubVipinfo.getEndTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubVipinfo.setEndTime("2099-12-31 23:59:59");
		}
		// 不为空修改，为空新增
		if (!pubVipinfo.getPid().trim().equals("") && !pubVipinfo.getPid().trim().equals("-1")) {
			pubVipinfo.setLastUpdateTime(DateUtil.getToday().toString());// 设置修改时间
			pubVipinfo.setLastUpdateUser(pubVipinfo.getCreateUser());// 设置修改人
			// 修改VIP信息信息
			mapper.updateByPrimaryKeySelective(pubVipinfo);
			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 赋值
			actActivity.setActTag(pubVipinfo.getActTag());// 设置活动标签
			actActivity.setActName(pubVipinfo.getVipName());// 设置活动名称
			actActivity.setStartDate(pubVipinfo.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubVipinfo.getEndTime());// 设置活动结束时间
			actActivity.setObjectId(pubVipinfo.getPid());// 设置VIP信息ID
			actActivity.setActivityType("1");// 设置活动类型为VIP信息
			actActivity.setIsBirPrivilege(pubVipinfo.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubVipinfo.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubVipinfo.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubVipinfo.getActDesc());// 设置活动描述
			actActivity.setStatus(pubVipinfo.getStatus());// 设置活动状态
			actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
			actActivity.setLastUpdateUser(pubVipinfo.getCreateUser());// 设置最后修改人ID
			// 修改活动信息
			actActivityMapper.updateByObjectIdAndActivityType(actActivity);
			// 先删除原条件信息
			actParConRelMapper.deleteByObjectIdAndActivityType(pubVipinfo.getPid(), "1");
			// 判断是否选取了条件信息
			if (null != pubVipinfo.getCondIds() && !pubVipinfo.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubVipinfo.getCondIds().split(",");
				// 查询出来活动ID
				String activityId = actActivityMapper.selectByObjectIdAndActivityType(pubVipinfo.getPid(), "1");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubVipinfo.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}

		} else {
			String pubVipinfoId = pubVipinfo.getPK();// 获取VIP信息活动ID
			pubVipinfo.setPid(pubVipinfoId);// 设置PID
			pubVipinfo.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			pubVipinfo.setCreateUser(pubVipinfo.getCreateUser());// 设置创建人
			// 新增VIP信息信息
			mapper.insertSelective(pubVipinfo);

			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 获取活动ID
			String activityId = actActivity.getPK();
			// 赋值
			actActivity.setPid(activityId);// 设置PID
			actActivity.setObjectId(pubVipinfoId);// 设置VIP信息ID
			actActivity.setActCode(sysCreateCodeRuleService.generatNoRule("VIP", "7", 4, pubVipinfo.getCreateUser()));
			actActivity.setActTag(pubVipinfo.getActTag());// 设置活动标签
			actActivity.setActName(pubVipinfo.getVipName());// 设置活动名称
			actActivity.setStartDate(pubVipinfo.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubVipinfo.getEndTime());// 设置活动结束时间
			actActivity.setActivityType("1");// 设置活动类型为VIP信息
			actActivity.setIsBirPrivilege(pubVipinfo.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubVipinfo.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubVipinfo.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubVipinfo.getActDesc());// 设置活动描述
			actActivity.setStatus(pubVipinfo.getStatus());// 设置活动状态
			actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			actActivity.setCreateUser(pubVipinfo.getCreateUser());// 设置创建人
			// 新增活动
			actActivityMapper.insertSelective(actActivity);
			// 判断是否选取了条件信息
			if (null != pubVipinfo.getCondIds() && !pubVipinfo.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubVipinfo.getCondIds().split(",");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubVipinfo.getCreateUser());// 设置创建人
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
	 * @return VIP信息活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午3:11:54
	 */
	@Override
	public PubVipinfo getPubVipinfoByPid(String pid) {
		PubVipinfoMapper mapper = (PubVipinfoMapper) getDao();
		PubVipinfo pubVipinfo = new PubVipinfo();
		pubVipinfo = mapper.selectByPrimaryKey(pid);
		return pubVipinfo;
	}

	/**
	 * 
	 * @Description : 批量删除VIP信息活动
	 * @param pids
	 *            VIP信息ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	@Override
	public void deleteBatch(String pids) {
		try {
			PubVipinfoMapper mapper = (PubVipinfoMapper) getDao();
			if (null != pids && !pids.equals("")) {
				String[] str = pids.split(",");
				// 删除VIP信息
				mapper.deleteBatch(str);
				// 删除活动信息
				actActivityMapper.updateStatusBatch("-1", str, "1");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除VIP信息活动:" + e.getMessage());
			}
		}
	}

	@Override
	public PubVipinfo getById(String pid) {
		PubVipinfoMapper mapper = (PubVipinfoMapper) getDao();
		return mapper.getById(pid);
	}

}
