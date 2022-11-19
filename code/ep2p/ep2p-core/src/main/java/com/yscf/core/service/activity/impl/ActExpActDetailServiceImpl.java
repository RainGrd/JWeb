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
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.activity.ActExpActDetailMapper;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.IActExpActDetailService;

/**
 * 
 * @ClassName : ActExpActDetailServiceImpl
 * @Description : 体验金明细商务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月19日 下午3:29:57
 */
@Service("actExpActDetailServiceImpl")
public class ActExpActDetailServiceImpl extends BaseService<BaseEntity, String> implements IActExpActDetailService {

	private Logger logger = LoggerFactory.getLogger(ActVipActDetailServiceImpl.class);

	@Autowired
	public ActExpActDetailServiceImpl(ActExpActDetailMapper dao) {
		super(dao);
	}

	/**
	 * 
	 * @Description : 查询赠送体验金活动列表,带分页
	 * @param actExpActDetail
	 *            赠送体验金活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送体验金活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:40
	 */
	@Override
	public PageList<ActExpActDetail> selectAllPageByCondition(ActExpActDetail actExpActDetail, PageInfo info) {
		ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
		PageList<ActExpActDetail> list = null;
		// 获取分页的数据
		try {
			list = new PageList<ActExpActDetail>();
			list = mapper.selectAllPageByCondition(actExpActDetail, info);
			if (null != list && list.size() != 0) {
				ActExpActDetail aead = new ActExpActDetail();
				aead.setParticipantsNumber(mapper.selectParticipantsNumberByCondition(actExpActDetail));// 设置总的参与人数
				aead.setExpAmount(mapper.selectBaseExpAmount(actExpActDetail));// 设置体验金额的总计
				aead.setGetAmount(mapper.selectBaseGetAmount(actExpActDetail));// 设置已获得总额的总计
				aead.setUseAmount(mapper.selectBaseUseAmount(actExpActDetail));// 设置已使用金额的总计
				// 添加到list
				list.add(aead);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询赠送体验金活动列表:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询赠送体验金活动明细列表,带分页
	 * @param actExpActDetail
	 *            赠送体验金活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送体验金活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:51
	 */
	@Override
	public PageList<ActExpActDetail> selectAllPageDetail(ActExpActDetail actExpActDetail, PageInfo info) {
		ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
		PageList<ActExpActDetail> list = new PageList<ActExpActDetail>();
		list = mapper.selectAllPageDetail(actExpActDetail, info);
		if (null != list && list.size() != 0) {
			// 获取计算的总金额的数据
			ActExpActDetail aead = new ActExpActDetail();
			aead.setGetAmount(mapper.selectGetAmountByCondition(actExpActDetail));// 设置获得的总计
			aead.setUseAmount(mapper.selectUseAmountByCondition(actExpActDetail));// 设置使用的总计
			// 添加到list
			list.add(aead);
		}
		return list;
	}

	@Override
	public PageList<ActExpActDetail> selectTiYanJinDetailsById(String pid, PageInfo info) {
		ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
		ActExpActDetail actExpActDetail = new ActExpActDetail();
		actExpActDetail.setCustomerId(pid);
		PageList<ActExpActDetail> tiYanJin = mapper.selectTiYanJinDetailsById(actExpActDetail, info);
		Map<String, BigDecimal> map = mapper.selectTiYanJinDetailsByIdSum(actExpActDetail);
		Map<String, BigDecimal> map1 = mapper.selectUseTiYanJinDetailsByIdSum(actExpActDetail);
		ActExpActDetail actExpActDetail2 = new ActExpActDetail();
		actExpActDetail2.setExpAmount(map.get("expAmount"));
		actExpActDetail2.setUserExpAmount(map1.get("useExpAmount"));// 统计已使用体验金金额
		tiYanJin.add(actExpActDetail2);
		return tiYanJin;
	}

	@Override
	public PageList<ActExpActDetail> selectAllPage(ActExpActDetail actExpActDetail, PageInfo pageInfo) {
		ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
		PageList<ActExpActDetail> tiyanjin = mapper.selectAllPage(actExpActDetail, pageInfo);
		Map<String, BigDecimal> map = mapper.selectTiYanJinDetailsByIdSum(actExpActDetail);
		ActExpActDetail actExpActDetail2 = new ActExpActDetail();
		actExpActDetail2.setExpAmount(map.get("expAmount"));
		tiyanjin.add(actExpActDetail2);
		return tiyanjin;
	}

	/**
	 * 
	 * @Description : 导出查询
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @param eom
	 *            导出对象
	 * @return 体验金赠送明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 下午3:38:54
	 */
	@Override
	public List<ActExpActDetail> selectAllPageDetailExport(ActExpActDetail actExpActDetail, ExportObjectModel eom) {
		List<ActExpActDetail> list = null;
		try {
			ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("actExpActDetail", actExpActDetail);
			map.put("expm", eom);

			list = mapper.selectAllPageDetailExport(map);

			if (null == list) {
				list = new ArrayList<ActExpActDetail>();
			} else {
				// 设置总计
				BigDecimal getAmount = new BigDecimal(0);
				BigDecimal useAmount = new BigDecimal(0);
				for (ActExpActDetail vo : list) {
					getAmount.add(vo.getGetAmount());
					useAmount.add(vo.getUseAmount());
				}
				ActExpActDetail arad = new ActExpActDetail();
				arad.setGetAmount(getAmount);// 设置获得的总计
				arad.setUseAmount(useAmount);// 设置使用的总计
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
	 * @Description : 查询客户当前可用体验金劵
	 * @param customerId
	 *            客户ID
	 * @return 体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午5:16:33
	 */
	@Override
	public List<ActExpActDetail> selectCustTomerExperienceGold(String customerId) {
		List<ActExpActDetail>  list = new ArrayList<ActExpActDetail>();
		try {
			ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
			list = mapper.selectCustTomerExperienceGold(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询客户当前可用体验金劵:" + e.getMessage());
			}
		}
		return list;
	}

	@Override
	public List<ActExpActDetail> selectExperienceGoldByPidArr(String[] pids) {
		ActExpActDetailMapper mapper = (ActExpActDetailMapper) getDao();
		return mapper.selectExperienceGoldByPidArr(pids);
	}

}
