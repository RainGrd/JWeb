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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.yscf.core.dao.activity.PubActivityAreaMapper;
import com.yscf.core.exception.CodeIsExistenceException;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.service.activity.IPubActivityAreaService;

/**
 * 
 * @ClassName : PubActivityAreaServiceImpl
 * @Description : 活动专区业务商用接口
 * @Author : Qing.Cai
 * @Date : 2015年12月14日 下午3:54:45
 */
@Service("pubActivityAreaServiceImpl")
public class PubActivityAreaServiceImpl extends BaseService<BaseEntity, String> implements IPubActivityAreaService {

	private Logger logger = LoggerFactory.getLogger(PubActivityAreaServiceImpl.class);

	@Autowired
	public PubActivityAreaServiceImpl(PubActivityAreaMapper dao) {
		super(dao);
	}

	/**
	 * 
	 * @Description : 前台_查询初始化的活动专区显示
	 * @param pageSize
	 *            初始显示数量
	 * @return 活动专区初始显示列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:49:26
	 */
	@Override
	public List<PubActivityArea> selectShowActicityArea(Integer pageSize) {
		List<PubActivityArea> list = new ArrayList<PubActivityArea>();
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			list = mapper.selectShowActicityArea(pageSize);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_查询初始化的活动专区显示:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 前台_查询加载的活动专区信息
	 * @param pageIndex
	 *            第几次加载
	 * @param pageSize
	 *            每次加载的数量
	 * @return 活动专区加载列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:59:25
	 */
	@Override
	public List<PubActivityArea> selectLoadActicityArea(Integer pageIndex, Integer pageSize) {
		List<PubActivityArea> list = new ArrayList<PubActivityArea>();
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			list = mapper.selectLoadActicityArea(pageIndex, pageSize);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_查询加载的活动专区信息:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 后台_分页查询活动专区列表
	 * @param pubActivityArea
	 *            活动专区对象
	 * @param info
	 *            分页对象
	 * @return 活动专区列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月16日 上午11:16:27
	 */
	@Override
	public PageList<PubActivityArea> selectAllPage(PubActivityArea pubActivityArea, PageInfo info) {
		PageList<PubActivityArea> list = new PageList<PubActivityArea>();
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			// 修改活动开始时间格式-begin
			if (null != pubActivityArea.getBeginStartData() && !"".equals(pubActivityArea.getBeginStartData())) {
				pubActivityArea.setBeginStartData(EscfDateUtil.formatterDate(pubActivityArea.getBeginStartData(), 1));
			}
			// 修改活动开始时间格式-end
			if (null != pubActivityArea.getEndStartData() && !"".equals(pubActivityArea.getEndStartData())) {
				pubActivityArea.setEndStartData(EscfDateUtil.formatterDate(pubActivityArea.getEndStartData(), 2));
			}
			// 修改活动开始时间格式-begin
			if (null != pubActivityArea.getBeginFinishData() && !"".equals(pubActivityArea.getBeginFinishData())) {
				pubActivityArea.setBeginFinishData(EscfDateUtil.formatterDate(pubActivityArea.getBeginFinishData(), 1));
			}
			// 修改活动开始时间格式-end
			if (null != pubActivityArea.getEndFinishData() && !"".equals(pubActivityArea.getEndFinishData())) {
				pubActivityArea.setEndFinishData(EscfDateUtil.formatterDate(pubActivityArea.getEndFinishData(), 2));
			}
			list = mapper.selectAllPage(pubActivityArea, info);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("后台_分页查询活动专区列表:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 修改是否展示
	 * @param pubActivityArea
	 *            活动专区对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午2:32:34
	 */
	@Override
	public void updateIsShows(PubActivityArea pubActivityArea) throws FrameworkException {
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置时间
			mapper.updateIsShows(pubActivityArea);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改是否展示:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 新增新手标or体验标信息到活动专区表里面
	 * @param bizBorrow
	 *            借款对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午8:48:54
	 */
	@SuppressWarnings("static-access")
	@Override
	public void pubAreaEditBorrow(BizBorrow bizBorrow) throws FrameworkException {
		PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
		try {
			// 先创建一个活动专区对象
			PubActivityArea pubActivityArea = new PubActivityArea();
			if (null != bizBorrow) {
				pubActivityArea.setPid(pubActivityArea.getPK());// 设置主键
				pubActivityArea.setActivityId(bizBorrow.getPid());// 设置新手标or体验标ID
				pubActivityArea.setActivityCode(bizBorrow.getBorrowCode());// 设置活动编号
				pubActivityArea.setActivityName(bizBorrow.getBorrowName());// 设置活动名称
				pubActivityArea.setIsShows(2);// 设置不展示
				pubActivityArea.setActivityStartDate(bizBorrow.getPublishTime());// 设置活动开始时间
				// 字符串转换日期格式
				SimpleDateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
				// 得到日期格式对象
				Date date = fmtDateTime.parse(bizBorrow.getPublishTime());
				// 创建Calendar对象,并赋值时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				// 加上天数
				calendar.add(calendar.DATE, Integer.parseInt(bizBorrow.getDeadline()));
				// 设置活动结束时间
				pubActivityArea.setActivityEndDate(fmtDateTime.format(calendar.getTime()));
				// 获取活动类型
				Integer activityType = null;
				// 判断是属于什么类型
				if (bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_4)) {
					// 如果是新手标
					activityType = 1;
				} else if (bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_5)) {
					// 如果是体验标
					activityType = 2;
				}
				pubActivityArea.setActivityType(activityType);// 设置活动类型
				// 活动状态
				String activityStatus = null;
				// 判断是什么状态
				if (bizBorrow.getBorStatus().equals(Constant.BORROW_STATUS_2)) {
					// 如果是招标中 = 进行中
					activityStatus = "1";
				} else if (bizBorrow.getBorStatus().equals(Constant.BORROW_STATUS_3)) {
					// 如果是流标 = 流标
					activityStatus = "3";
				} else if (bizBorrow.getBorStatus().equals(Constant.BORROW_STATUS_4)) {
					// 如果是满标 = 满标
					activityStatus = "4";
				}
				pubActivityArea.setActivityStatus(activityStatus);// 设置活动状态
				pubActivityArea.setStatus("1");// 状态为正常
				pubActivityArea.setVersion(Constant.PUBLIC_VERSION);// 设置默认版本号
				// 创建人or创建时间
				pubActivityArea.setCreateUser(bizBorrow.getLastUpdateUser());
				pubActivityArea.setCreateTime(bizBorrow.getLastUpdateTime());
				// 新增
				mapper.insertSelective(pubActivityArea);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增新手标or体验标信息到活动专区表里面:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 新增活动信息到活动专区表里面
	 * @param actActivity
	 *            活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午8:49:04
	 */
	@Override
	public void pubAreaEditActivity(ActActivity actActivity) throws FrameworkException {
		PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
		try {
			// 先创建一个活动专区对象
			PubActivityArea pubActivityArea = new PubActivityArea();
			if (null != actActivity) {
				pubActivityArea.setPid(pubActivityArea.getPK());// 设置主键
				pubActivityArea.setActivityId(actActivity.getPid());// 设置新手标or体验标ID
				pubActivityArea.setActivityCode(actActivity.getActCode());// 设置活动编号
				pubActivityArea.setActivityName(actActivity.getActName());// 设置活动名称
				pubActivityArea.setIsShows(2);// 设置不展示
				pubActivityArea.setActivityStartDate(actActivity.getStartDate());// 设置活动开始时间
				pubActivityArea.setActivityEndDate(actActivity.getEndDate());// 设置活动结束时间
				pubActivityArea.setActivityType(actActivity.getActivityAreaType());// 设置活动类型
				pubActivityArea.setActivityStatus("5");// 设置活动状态为编辑中
				pubActivityArea.setVersion(Constant.PUBLIC_VERSION);// 设置默认版本号
				pubActivityArea.setIsDisable(actActivity.getIsDisable());// 设置是否启用
				pubActivityArea.setStatus("1");// 状态为正常
				// 创建人or创建时间
				pubActivityArea.setCreateUser(actActivity.getLastUpdateUser());
				pubActivityArea.setCreateTime(actActivity.getLastUpdateTime());
				// 新增
				mapper.insertSelective(pubActivityArea);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增活动信息到活动专区表里面:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 根据活动类型and活动ID修改活动状态
	 * @param activityId
	 *            活动ID
	 * @param activityType
	 *            活动类型(1 抢红包 2 加息券 99 其他)
	 * @param activityStatus
	 *            活动状态（1.进行中 2.过期 3.满标 4.流标）
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午8:49:18
	 */
	@Override
	public void updateActivityStatus(String activityId, Integer activityType, String activityStatus) throws FrameworkException {
		PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
		try {
			// 创建条件map
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activityId", activityId);
			map.put("activityType", activityType);
			map.put("activityStatus", activityStatus);
			mapper.updateActivityStatus(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("根据活动类型and活动ID修改活动状态:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description :删除活动专区数据
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午3:02:59
	 */
	@Override
	public void deletePubActivityArea(PubActivityArea pubActivityArea) throws FrameworkException {
		PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
		try {
			pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置时间
			mapper.deletePubActivityArea(pubActivityArea);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除活动专区数据:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 新增活动专区对象(需要跳转到一个制定的HTML页面)
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月18日 下午2:46:20
	 */
	@Override
	public void pubAreaEditOther(PubActivityArea pubActivityArea) throws FrameworkException, CodeIsExistenceException {
		PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
		try {
			// 检验编号的唯一性
			int row = mapper.checkCodeIsExistence(pubActivityArea.getActivityCode());

			if (pubActivityArea.getPid().trim().equals("-1")) {
				// 判断
				if (row > 0) {
					// 编号存在
					throw new CodeIsExistenceException("编号已经存在.");
				}

				// 赋值
				pubActivityArea.setPid(pubActivityArea.getPK());// 设置主键
				pubActivityArea.setStatus("1");// 设置状态
				pubActivityArea.setCreateTime(DateUtil.getToday().toString());// 创建时间
				pubActivityArea.setVersion(Constant.PUBLIC_VERSION);// 设置版本号

				// 新增
				mapper.insertSelective(pubActivityArea);

			} else {
				// 根据主键查询对象信息
				PubActivityArea paa = mapper.selectByPrimaryKey(pubActivityArea.getPid());

				if (!paa.getActivityCode().equals(pubActivityArea.getActivityCode())) {
					// 判断
					if (row > 0) {
						// 编号存在
						throw new CodeIsExistenceException("编号已经存在.");
					}
				}

				pubActivityArea.setLastUpdateUser(pubActivityArea.getCreateUser());// 设置最后修改人
				pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
				// 修改
				mapper.updatePubActivityArea(pubActivityArea);
			}
		} catch (CodeIsExistenceException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增活动专区对象(需要跳转到一个制定的HTML页面):" + e.getMessage());
			}
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description : 修改是否启用
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 上午10:41:33
	 */
	@Override
	public void updateIsDisable(PubActivityArea pubActivityArea) throws FrameworkException {
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			pubActivityArea.setLastUpdateTime(DateUtil.getToday().toString());// 设置时间
			mapper.updateIsDisable(pubActivityArea);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("修改是否启用:" + e.getMessage());
			}
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Description : 查询当前最新的活动(根据类型)
	 * @param activityType
	 *            活动类型(1.抢红包 2.加息劵 99.其他)
	 * @return 最新的活动对象
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午10:15:58
	 */
	@Override
	public PubActivityArea selectPubActivityByType(Integer activityType) {
		PubActivityArea pubActivityArea = null;
		try {
			PubActivityAreaMapper mapper = (PubActivityAreaMapper) getDao();
			pubActivityArea = mapper.selectPubActivityByType(activityType);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询当前最新的活动(根据类型):" + e.getMessage());
			}
			e.printStackTrace();
		}
		return pubActivityArea;
	}

}
