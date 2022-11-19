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
 * 2015年10月13日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.activity.ActBirPriItemActivityRelMapper;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;
import com.yscf.core.service.activity.IActBirPriItemActivityRelService;

/**
 * Description：<br>
 * 生日特权项和活动关系表 服务实现
 * 
 * @author fengshiliang
 * @date 2015年10月13日
 * @version v1.0.0
 */
@Service("actBirPriItemActivityRelServiceImpl")
public class ActBirPriItemActivityRelServiceImpl extends
		BaseService<BaseEntity, String> implements
		IActBirPriItemActivityRelService {

	@Autowired
	public ActBirPriItemActivityRelServiceImpl(
			ActBirPriItemActivityRelMapper dao) {
		super(dao);
	}

	// 特权与活动关系
	@Autowired
	public ActBirPriItemActivityRelMapper activityRelMapper;

	@Override
	public List<ActBirPriItemActivityRel> selectPrivilege() {
		return activityRelMapper.selectPrivilege();
	}

	@Override
	public PageList<ActBirPriItemActivityRel> selectBirPriItem(
			ActBirPriItemActivityRel activityRel, PageInfo info) {
		PageList<ActBirPriItemActivityRel> list = activityRelMapper
				.selectBirPriItem(activityRel, info);
		ActBirPriItemActivityRel detail = new ActBirPriItemActivityRel();
		detail.setGetAmount(activityRelMapper.selectTotalCondition());
		list.add(detail);
		return list;
	}

	@Override
	public List<ActBirPriItemActivityRel> selectBirPriRelByActivityId(
			String activityId) {
		return activityRelMapper.selectBirPriRelByActivityId(activityId);
	}
}
