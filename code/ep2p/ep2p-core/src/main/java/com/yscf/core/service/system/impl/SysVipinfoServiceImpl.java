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
package com.yscf.core.service.system.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.system.SysVipCondRelMapper;
import com.yscf.core.dao.system.SysVipinfoMapper;
import com.yscf.core.model.system.SysVipCondRel;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.service.system.ISysVipinfoService;

/**
 * 
 * @ClassName : SysVipinfoServiceImpl
 * @Description : VIP信息商务接口
 * @Author : Qing.Cai
 * @Date : 2015年11月3日 上午10:17:33
 */
@Service("sysVipinfoServiceImpl")
public class SysVipinfoServiceImpl extends BaseService<BaseEntity, String> implements ISysVipinfoService {

	@Autowired
	public SysVipinfoServiceImpl(SysVipinfoMapper dao) {
		super(dao);
	}
	
	// VIP条件信息
	@Autowired
	public SysVipCondRelMapper sysVipCondRelMapper;
	// 编号管理Service
	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	/**
	 * 
	 * @Description : 查询所有VIP信息,带分页
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @param info
	 *            分页对象
	 * @return VIP列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:11:29
	 */
	@Override
	public PageList<SysVipinfo> selectAllPage(SysVipinfo sysVipinfo, PageInfo info) {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != sysVipinfo.getBeginApplyStartData() && !"".equals(sysVipinfo.getBeginApplyStartData())) {
			sysVipinfo.setBeginApplyStartData(EscfDateUtil.formatterDate(sysVipinfo.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != sysVipinfo.getEndApplyStartData() && !"".equals(sysVipinfo.getEndApplyStartData())) {
			sysVipinfo.setEndApplyStartData(EscfDateUtil.formatterDate(sysVipinfo.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != sysVipinfo.getBeginApplyFinishData() && !"".equals(sysVipinfo.getBeginApplyFinishData())) {
			sysVipinfo.setBeginApplyFinishData(EscfDateUtil.formatterDate(sysVipinfo.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != sysVipinfo.getEndApplyFinishData() && !"".equals(sysVipinfo.getEndApplyFinishData())) {
			sysVipinfo.setEndApplyFinishData(EscfDateUtil.formatterDate(sysVipinfo.getEndApplyFinishData(), 2));
		}
		return mapper.selectAllPage(sysVipinfo, info);
	}

	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            VIP信息ID数组(1,2,3)
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:11:51
	 */
	@Override
	public void deleteBatch(String pids) throws FrameworkException {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		if (null != pids && !pids.equals("")) {
			String[] str = pids.split(",");
			mapper.deleteBatch(str);
		}
	}

	/**
	 * 
	 * @Description : VIP信息编辑
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:17:54
	 */
	@Override
	public void sysVipinfoEdit(SysVipinfo sysVipinfo) throws FrameworkException {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		if (!sysVipinfo.getPid().trim().equals("") && !sysVipinfo.getPid().trim().equals("-1")) {
			// 修改
			sysVipinfo.setDiscount(sysVipinfo.getDiscount().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));// 设置管理汇率
			sysVipinfo.setLastUpdateUser(sysVipinfo.getCreateUser());
			sysVipinfo.setLastUpdateTime(DateUtil.getToday().toString());
			mapper.updateByPrimaryKeySelective(sysVipinfo);
			// 先删除当前VIP信息的所有条件信息
			sysVipCondRelMapper.deleteByVipId(sysVipinfo.getPid());
		} else {
			// 新增
			sysVipinfo.setPid(sysVipinfo.getPK());// 设置主键
			sysVipinfo.setDiscount(sysVipinfo.getDiscount().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));// 设置管理汇率
			sysVipinfo.setVipCode(sysCreateCodeRuleService.generatNoRuleNoDateTime("VIP","1",4,sysVipinfo.getCreateUser()));
			sysVipinfo.setCreateTime(DateUtil.getToday().toString());// 创建时间
			mapper.insertSelective(sysVipinfo);
		}
		// 判断是否选取了条件信息
		if(null != sysVipinfo.getCondIds() && !sysVipinfo.getCondIds().equals("")){
			// 解析出来选择的条件信息
			String [] conIds = sysVipinfo.getCondIds().split(",");
			List<SysVipCondRel> list = new ArrayList<SysVipCondRel>();
			// 循环添加
			for(int i = 0; i <conIds.length ; i++){
				// 创建活动参与条件关系对象 
				SysVipCondRel sysVipCondRel = new SysVipCondRel();
				// 赋值
				sysVipCondRel.setPid(sysVipCondRel.getPK());// 设置PID
				sysVipCondRel.setVipId(sysVipinfo.getPid());// 设置vipId
				sysVipCondRel.setCondId(conIds[i]);// 设置条件ID 
				sysVipCondRel.setCreateUser(sysVipinfo.getCreateUser());// 设置创建人
				sysVipCondRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
				list.add(sysVipCondRel);
			}
			// 批量新增
			sysVipCondRelMapper.insertSelectiveBatch(list);
		}
	}

	/**
	 * 
	 * @Description : 根据vipId查询条件数据
	 * @param vipId
	 *            VIP主键
	 * @return 条件List
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午5:03:08
	 */
	@Override
	public List<SysVipCondRel> seleCondRelByVipId(String vipId) {
		List<SysVipCondRel> list = new ArrayList<SysVipCondRel>();
		list = sysVipCondRelMapper.seleCondRelByVipId(vipId);
		return list;
	}

	@Override
	public SysVipinfo getById(String vipinfoId) {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		return mapper.selectByPrimaryKey(vipinfoId);
	}

	@Override
	public List<SysVipinfo> selectVipLevelById(String userId) {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		return mapper.selectVipLevelById(userId);
	}

	@Override
	public SysVipinfo getByLevel(String vipLevel) {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		return mapper.getByLevel(vipLevel);
	}

	@Override
	public BigDecimal selectFeeByUserId(String userId) {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		return mapper.selectFeeByUserId(userId);
	}

	@Override
	public SysVipinfo selectVipInfoPid() {
		SysVipinfoMapper mapper = (SysVipinfoMapper) getDao();
		return mapper.selectVipInfoPid();
	}
}
