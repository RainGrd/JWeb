/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.activity.ActInvWealthCoopDetailMapper;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.IActInvWealthCoopDetailService;

/**
 * Description：<br>
 * 财富合伙人明细表 服务实现
 * 
 * @author fengshiliang
 * @date 2015年10月22日
 * @version v1.0.0
 */
@Service("actInvWealthCoopDetailServiceImpl")
public class ActInvWealthCoopDetailServiceImpl extends
		BaseService<BaseEntity, String> implements
		IActInvWealthCoopDetailService {
	@Resource
	private ActInvWealthCoopDetailMapper coopDetailMapper;

	@Autowired
	public ActInvWealthCoopDetailServiceImpl(ActInvWealthCoopDetailMapper dao) {
		super(dao);
	}

	@Override
	public PageList<ActInvWealthCoopDetail> selectAllPageByCondition(
			ActInvWealthCoopDetail coopDetail, PageInfo info) {
		PageList<ActInvWealthCoopDetail> list = coopDetailMapper
				.selectAllPageByCondition(coopDetail, info);
		ActInvWealthCoopDetail detail = new ActInvWealthCoopDetail();
		detail.setGetAmount(coopDetailMapper.selectTotalCondition());
		list.add(detail);
		return list;
	}

	@Override
	public PageList<ActInvWealthCoopDetail> selectAllPageDetail(
			ActInvWealthCoopDetail coopDetail, PageInfo info) {
		return coopDetailMapper.selectAllPageDetail(coopDetail, info);
	}

	@Override
	public List<ActInvWealthCoopDetail> selectActInvWealthCoopDetailVOEom(
			ActInvWealthCoopDetail actInvWealthCoopDetail, ExportObjectModel eom) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("map", actInvWealthCoopDetail);
		map.put("expm", eom);
		List<ActInvWealthCoopDetail> elist = coopDetailMapper
				.selectActInvWealthCoopDetailVOEom(map);

		return elist;
	}

}
