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
import java.util.Date;
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
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActParConRelMapper;
import com.yscf.core.dao.activity.ActRedpActDetailMapper;
import com.yscf.core.dao.activity.PubActivityAreaMapper;
import com.yscf.core.dao.activity.PubRedpacketMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.ActRedpActDetail;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.activity.PubRedpacket;
import com.yscf.core.service.activity.IPubActivityAreaService;
import com.yscf.core.service.activity.IPubRedpacketService;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;

/**
 * 
 * @ClassName : PubRedpacketServiceImpl
 * @Description : 红包信息业务服务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月8日 下午4:48:44
 */
@Service("pubRedpacketServiceImpl")
public class PubRedpacketServiceImpl extends BaseService<BaseEntity, String> implements IPubRedpacketService {

	private Logger logger = LoggerFactory.getLogger(PubRedpacketServiceImpl.class);

	@Autowired
	public PubRedpacketServiceImpl(PubRedpacketMapper dao) {
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
	public ActRedpActDetailMapper actRedpActDetailMapper;
	
	@Autowired
	public PubActivityAreaMapper pubActivityAreaMapper;

	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	/**
	 * 
	 * @Description : 查询红包信息列表,带分页
	 * @param pubExpGold
	 *            红包信息对象
	 * @param info
	 *            分页对象
	 * @return 红包信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:49:04
	 */
	@Override
	public PageList<PubRedpacket> selectAllPage(PubRedpacket pubRedpacket, PageInfo info) {
		PubRedpacketMapper mapper = (PubRedpacketMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != pubRedpacket.getBeginApplyStartData() && !"".equals(pubRedpacket.getBeginApplyStartData())) {
			pubRedpacket.setBeginApplyStartData(EscfDateUtil.formatterDate(pubRedpacket.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubRedpacket.getEndApplyStartData() && !"".equals(pubRedpacket.getEndApplyStartData())) {
			pubRedpacket.setEndApplyStartData(EscfDateUtil.formatterDate(pubRedpacket.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != pubRedpacket.getBeginApplyFinishData() && !"".equals(pubRedpacket.getBeginApplyFinishData())) {
			pubRedpacket.setBeginApplyFinishData(EscfDateUtil.formatterDate(pubRedpacket.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubRedpacket.getEndApplyFinishData() && !"".equals(pubRedpacket.getEndApplyFinishData())) {
			pubRedpacket.setEndApplyFinishData(EscfDateUtil.formatterDate(pubRedpacket.getEndApplyFinishData(), 2));
		}
		return mapper.selectAllPage(pubRedpacket, info);
	}

	/**
	 * 
	 * @Description : 编辑红包活动
	 * @param pubExpGold
	 *            红包信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:49:31
	 */
	@Override
	public void pubRedpacketEdit(PubRedpacket pubRedpacket) throws FrameworkException {
		PubRedpacketMapper mapper = (PubRedpacketMapper) getDao();
		// 判断适用开始时间是否填写
		if (null == pubRedpacket.getEffTime() || pubRedpacket.getEffTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubRedpacket.setEffTime(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
		}
		// 判断适用结束时间是否填写
		if (null == pubRedpacket.getExpTime() || pubRedpacket.getExpTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubRedpacket.setExpTime("2099-12-31 23:59:59");
		}
		// 不为空修改，为空新增
		if (!pubRedpacket.getPid().trim().equals("") && !pubRedpacket.getPid().trim().equals("-1")) {
			pubRedpacket.setLastCreateTime(DateUtil.getToday().toString());// 设置修改时间
			pubRedpacket.setLastCreateUser(pubRedpacket.getCreateUser());// 设置修改人
			// 修改红包信息
			mapper.updateByPrimaryKeySelective(pubRedpacket);
			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 赋值
			actActivity.setActTag(pubRedpacket.getActTag());// 设置活动标签
			actActivity.setActName(pubRedpacket.getRepName());// 设置活动名称
			actActivity.setStartDate(pubRedpacket.getEffTime());// 设置活动开始时间
			actActivity.setEndDate(pubRedpacket.getExpTime());// 设置活动结束时间
			actActivity.setObjectId(pubRedpacket.getPid());// 设置红包ID
			actActivity.setActivityType("2");// 设置活动类型为红包
			actActivity.setIsBirPrivilege(pubRedpacket.getIsBirPrivilege());// 设置是否生日特权
			actActivity.setIsSmsWarn(pubRedpacket.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubRedpacket.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubRedpacket.getRedDesc());// 设置活动描述
			actActivity.setStatus(Constant.PUBLIC_STATUS);// 设置活动状态
			actActivity.setIsDisable(Integer.parseInt(pubRedpacket.getStatus()));// 设置活动是否启用
			actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
			actActivity.setLastUpdateUser(pubRedpacket.getCreateUser());// 设置最后修改人ID
			// 修改活动信息
			actActivityMapper.updateByObjectIdAndActivityType(actActivity);

			// 修改活动专区的信息
			if(pubRedpacket.getRedpacketType().equals("2")){
				// 创建活动专区对象 
				PubActivityArea pubActivityArea = new PubActivityArea();
				pubActivityArea.setActivityCode(pubRedpacket.getActCode());// 设置活动编号
				pubActivityArea.setActivityName(pubRedpacket.getRepName());// 设置活动名称
				pubActivityArea.setActivityStartDate(pubRedpacket.getEffTime());// 设置活动开始时间
				pubActivityArea.setActivityEndDate(pubRedpacket.getExpTime());// 设置活动结束时间
				pubActivityArea.setLastUpdateUser(pubRedpacket.getCreateUser());// 设置最后修改时间
				pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改人ID
				pubActivityArea.setIsDisable(Integer.parseInt(pubRedpacket.getStatus()));// 设置是否启用
				// 新增数据到活动专区
				pubActivityAreaMapper.updateActiviayByArea(pubActivityArea);
			}
			
			// 先删除原条件信息
			actParConRelMapper.deleteByObjectIdAndActivityType(pubRedpacket.getPid(), "2");
			// 判断是否选取了条件信息
			if (null != pubRedpacket.getCondIds() && !pubRedpacket.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubRedpacket.getCondIds().split(",");
				// 查询出来活动ID
				String activityId = actActivityMapper.selectByObjectIdAndActivityType(pubRedpacket.getPid(), "2");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubRedpacket.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}
			// 判断是否是抢红包
			if (pubRedpacket.getRedpacketType().equals("2")) {
				// 获取活动ID
				String activityId = actActivityMapper.selectActivityId("2", pubRedpacket.getPid().trim());
				// 删除当前活动和红包关联的红包数据
				actRedpActDetailMapper.deleteRedpacketDetail(activityId, pubRedpacket.getPid().trim());
				// 随机生成活动金额的数组 >> 随机生成模式
				double[] allocationMoney = new double[pubRedpacket.getRepNumber()];
				// 判断是什么生成模式
				if (pubRedpacket.getAllotType().equals("1")) {
					// 如果是平均分配模式
					// 平均生成金额
					double num = pubRedpacket.getRepAmount().doubleValue() / (pubRedpacket.getRepNumber() * 1.0);
					// 生成数组,大小为红包的数量
					allocationMoney = new double[pubRedpacket.getRepNumber()];
					// 如果是平常生成模式
					for (int i = 0; i < pubRedpacket.getRepNumber(); i++) {
						allocationMoney[i] = num;
					}
				} else {
					allocationMoney = randomGenerationRed(pubRedpacket.getRepAmount().doubleValue(), pubRedpacket.getRepNumber());
				}
				// 生成红包明细的数据
				generationDetail(allocationMoney, activityId, pubRedpacket.getPid().trim(), pubRedpacket.getSmsId(), pubRedpacket.getCreateUser());
			}
		} else {
			String pubRedpacketId = pubRedpacket.getPK();// 获取红包活动ID
			pubRedpacket.setPid(pubRedpacketId);// 设置PID
			pubRedpacket.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			pubRedpacket.setCreateUser(pubRedpacket.getCreateUser());// 设置创建人
			// 新增红包信息
			mapper.insertSelective(pubRedpacket);

			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 获取活动ID
			String activityId = actActivity.getPK();
			// 赋值
			actActivity.setPid(activityId);// 设置PID
			actActivity.setObjectId(pubRedpacketId);// 设置红包ID
			// 根据红包类型判断. 1 = 送红包 ，2 = 抢红包
			if (pubRedpacket.getRedpacketType().equals("1")) {
				// 设置活动编号
				actActivity.setActCode(sysCreateCodeRuleService.generatNoRule("SHB", "7", 4, pubRedpacket.getCreateUser()));
				actActivity.setIsBirPrivilege(pubRedpacket.getIsBirPrivilege());// 设置是否生日特权
			} else {
				// 设置活动编号
				actActivity.setActCode(sysCreateCodeRuleService.generatNoRule("QHB", "7", 4, pubRedpacket.getCreateUser()));
			}
			actActivity.setActTag(pubRedpacket.getActTag());// 设置活动标签
			actActivity.setActName(pubRedpacket.getRepName());// 设置活动名称
			actActivity.setStartDate(pubRedpacket.getEffTime());// 设置活动开始时间
			actActivity.setEndDate(pubRedpacket.getExpTime());// 设置活动结束时间
			actActivity.setActivityType("2");// 设置活动类型为红包
			actActivity.setIsSmsWarn(pubRedpacket.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubRedpacket.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubRedpacket.getRedDesc());// 设置活动描述
			actActivity.setStatus(Constant.PUBLIC_STATUS);// 设置活动状态
			actActivity.setIsDisable(Integer.parseInt(pubRedpacket.getStatus()));// 设置活动是否启用
			actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			actActivity.setCreateUser(pubRedpacket.getCreateUser());// 设置创建人
			// 新增活动
			actActivityMapper.insertSelective(actActivity);
			// 判断是否是抢红包
			if (pubRedpacket.getRedpacketType().equals("2")) {
				actActivity.setActivityAreaType(1);// 设置活动专区类型为抢红包
				// 新增数据到活动专区
				pubActivityAreaServiceImpl.pubAreaEditActivity(actActivity);
				// 随机生成活动金额的数组
				double[] allocationMoney = new double[pubRedpacket.getRepNumber()];
				// 判断是什么生成模式
				if (pubRedpacket.getAllotType().equals("1")) {
					// 如果是平均分配模式
					// 平均生成金额
					double num = pubRedpacket.getRepAmount().doubleValue()/(pubRedpacket.getRepNumber() * 1.0);
					// 生成数组,大小为红包的数量
					allocationMoney = new double[pubRedpacket.getRepNumber()];
					// 如果是平常生成模式
					for (int i = 0; i < pubRedpacket.getRepNumber(); i++) {
						allocationMoney[i] = num;
					}
				} else {
					allocationMoney = randomGenerationRed(pubRedpacket.getRepAmount().doubleValue(), pubRedpacket.getRepNumber());
				}
				// 生成红包明细的数据
				generationDetail(allocationMoney, activityId, pubRedpacketId, pubRedpacket.getSmsId(), pubRedpacket.getCreateUser());
			}
			// 判断是否选取了条件信息
			if (null != pubRedpacket.getCondIds() && !pubRedpacket.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubRedpacket.getCondIds().split(",");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubRedpacket.getCreateUser());// 设置创建人
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
	 * @return 红包活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:52:05
	 */
	@Override
	public PubRedpacket getPubRedpacketByPid(String pid) {
		PubRedpacketMapper mapper = (PubRedpacketMapper) getDao();
		PubRedpacket pubRedpacket = new PubRedpacket();
		pubRedpacket = mapper.selectByPrimaryKey(pid);
		return pubRedpacket;
	}

	/**
	 * 
	 * @Description : 批量删除红包活动
	 * @param pids
	 *            红包ID(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:52:22
	 */
	@Override
	public void deleteBatch(String pids) {
		PubRedpacketMapper mapper = (PubRedpacketMapper) getDao();
		try {
			if (null != pids && !pids.equals("")) {
				String[] str = pids.split(",");
				// 删除红包活动
				mapper.deleteBatch(str);
				// 删除活动信息
				actActivityMapper.updateStatusBatch("-1", str, "2");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除红包活动:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 随机生成红包金额
	 * @param totalAmount
	 *            红包总金额
	 * @param redNumber
	 *            红包个数
	 * @return 随机生成的红包的金额的数组
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午5:17:39
	 */
	public double[] randomGenerationRed(double totalAmount, int redNumber) {
		double[] allocationMoney = new double[redNumber];
		for (int i = redNumber; i > 1; i--) {
			// 安全线
			double surplusMoney = (totalAmount - (i - 1) * 0.01) / i * 3;
			// 判断是否是最后第二个,如果是第二个的话,安全线的生成规则需要改变
			if (i == 2) {
				surplusMoney = (totalAmount - (i - 1) * 0.01);
			}
			// 随机生成红包金额
			double randomlyAssignedMoney = ((int) (Math.random() * surplusMoney * 100) + 1) * 0.01;
			// 截取小数点后两位输出
			BigDecimal aBigDecimal = new BigDecimal(randomlyAssignedMoney);
			// 格式化金额
			double moneyOfOnePerson = aBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			// double类型精确不丢失的算法 工具类
			totalAmount = (new BigDecimal(totalAmount).subtract( new BigDecimal(moneyOfOnePerson))).doubleValue();
			// 赋值
			allocationMoney[i - 1] = moneyOfOnePerson;
		}
		BigDecimal bg = new BigDecimal(totalAmount);
		// 获取最后一值
		double remainingMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		allocationMoney[0] = remainingMoney;
		return allocationMoney;
	}

	/**
	 * 
	 * @Description : 生成红包的详细数据
	 * @param redArr
	 *            红包的金额的数组
	 * @param activityId
	 *            活动ID
	 * @param redpacketId
	 *            相关联的红包ID
	 * @param smsId
	 *            短信ID
	 * @param createUser
	 *            创建ID
	 * @Author : Qing.Cai
	 * @Date : 2015年11月24日 下午3:10:12
	 */
	public void generationDetail(double[] redArr, String activityId, String redpacketId, String smsId, String createUser) {
		try {
			// 1.创建list对象集
			List<ActRedpActDetail> list = new PageList<ActRedpActDetail>();
			// 2.循环生成红包详情对象,并添加到list里面
			for (int i = 0; i < redArr.length; i++) {
				// 创建对象
				ActRedpActDetail arad = new ActRedpActDetail();
				// 获取当前时间
				String dateTime = DateUtil.getToday().toString();
				arad.setPid(arad.getPK());// 设置PID
				arad.setActivityId(activityId);// 设置活动ID
				arad.setRedpacketId(redpacketId);// 设置红包ID
				arad.setSmsId(smsId);// 设置短信ID
				arad.setGetAmount(new BigDecimal(redArr[i]));// 设置领取金额
				arad.setStatus("1");// 设置状态为正常:1
				arad.setCreateUser(createUser);// 设置创建人
				arad.setCreateTime(dateTime);// 设置创建时间
				// 添加到list列表
				list.add(arad);
			}
			// 批量新增红包信息
			actRedpActDetailMapper.insertBatch(list);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("生成红包的详细数据:" + e.getMessage());
			}
		}

	}

}
