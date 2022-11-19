package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustExperienceWaterMapper;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustExperienceWater;
import com.yscf.core.service.user.ICustExperienceService;

/**
 * Description：<br>
 * 客户经验明细服务实现
 * 
 * @author heng.wang
 * @date 2015年10月9日
 * @version v1.0.0
 */
@Service("custExperienceServiceImpl")
public class CustExperienceServiceImpl extends BaseService<BaseEntity, String> implements ICustExperienceService {

	private Logger logger = LoggerFactory.getLogger(CustExperienceServiceImpl.class);

	/** 客户信息Maper接口 */
	@Autowired
	public CusTomerMapper cusTomerMapper;

	@Autowired
	public SysParamsMapper sysParamsMapper;

	
	@Autowired
	public CustExperienceServiceImpl(CustExperienceWaterMapper dao) {
		super(dao);
	}

	@Override
	public PageList<CustExperienceWater> selectExperienceDetailsById(String pid, PageInfo info) {
		CustExperienceWaterMapper mapper = (CustExperienceWaterMapper) getDao();
		CustExperienceWater custExperienceWater = new CustExperienceWater();
		custExperienceWater.setCustomerId(pid);
		return mapper.selectExperienceDetailsById(custExperienceWater, info);
	}

	@Override
	public PageList<CustExperienceWater> selectAllPage(CustExperienceWater custExperienceWater, PageInfo pageInfo) {
		CustExperienceWaterMapper mapper = (CustExperienceWaterMapper) getDao();
		return mapper.selectAllPage(custExperienceWater, pageInfo);
	}

	/**
	 * 
	 * @Description : 计算投资经验值
	 * @param investmentAmount
	 *            投资总额
	 * @param projectNum
	 *            项目总期数
	 * @param userId
	 *            用户ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 下午5:06:08
	 */
	@Override
	public void updateUserExperience(BigDecimal investmentAmount, Integer projectNum, String userId) throws FrameworkException {
		try {
			// 判断用户是否是VIP
			CusTomer ct = cusTomerMapper.selectByPrimaryKey(userId);
			// 判断是否是VIP
			if (ct.getIsVip().equals("是")) {
				// 根据类型获取系统参数对象
				SysParams sysParams = sysParamsMapper.getParamsByParamKey(Constant.INVESTMENT_EXPERIENCE_BASE);
				// 计算出当前所得经验值
				BigDecimal expbd = new BigDecimal(sysParams.getParamValue()).multiply(investmentAmount).multiply(new BigDecimal(projectNum)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
				Integer exp = expbd.intValue();
				// 修改客户的经验值
				List<CusTomer> list = new ArrayList<CusTomer>();
				ct.setEmpiricalValue( (ct.getEmpiricalValue() == null ? 0 : ct.getEmpiricalValue()) + exp );// 设置经验值
				list.add(ct);
				// 批量修改客户经验值信息
				cusTomerMapper.batchUpdateExperience(list);
				
				// 添加信息到客户经验值流水表里面
				CustExperienceWaterMapper mapper = (CustExperienceWaterMapper) getDao();
				// 创建对象 
				CustExperienceWater cew = new CustExperienceWater();
				// 赋值
				cew.setPid(cew.getPK());// 主键
				cew.setCustomerId(userId);// 设置用户ID
				cew.setExpValue(exp);// 设置本次所获得经验值
				cew.setExpGetType("2");// 设置经验值类型：1、VIP每日经验  2、投资经验  3、活动获得
				cew.setAvailableExp(ct.getEmpiricalValue());// 设置用户当前经验值
				cew.setHappenTime(DateUtil.getToday().toString());// 设置发生时间
				cew.setStatus(Constant.PUBLIC_STATUS);// 设置状态
				cew.setCreateUser(userId);// 设置创建用户ID
				cew.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
				cew.setExpWatDesc("投资所获得");// 设置备注
				cew.setVersion(Constant.PUBLIC_VERSION);// 设置版本号
				
				// 新增
				mapper.insertSelective(cew);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("计算投资经验值:" + e.getMessage());
			}
			e.printStackTrace();
		}
	}

}
