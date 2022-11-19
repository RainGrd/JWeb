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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.PubConditionMapper;
import com.yscf.core.model.system.PubCondition;
import com.yscf.core.service.system.IPubConditionService;

/**
 * 
 * @ClassName : PubConditionServiceImpl
 * @Description : 条件信息服务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月23日 上午10:33:50
 */
@Service("pubConditionServiceImpl")
public class PubConditionServiceImpl extends BaseService<BaseEntity, String> implements IPubConditionService {

	@Autowired
	public PubConditionServiceImpl(PubConditionMapper dao) {
		super(dao);
	}

	// 编号管理Service
	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;
	
	/**
	 * 
	 * @Description : 查询所有有效的条件信息
	 * @return 有效的条件信息list
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:35:10
	 */
	@Override
	public List<PubCondition> selectPubConditionBySatusEffective() {
		PubConditionMapper mapper = (PubConditionMapper) getDao();
		List<PubCondition> list = new ArrayList<PubCondition>();
		list = mapper.selectPubConditionBySatusEffective();
		return list;
	}

	/**
	 * 
	 * @Description : 查询条件信息,带分业
	 * @param pubCondition
	 *            条件信息对象
	 * @param info
	 *            分页对象
	 * @return 条件信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:38:10
	 */
	@Override
	public PageList<PubCondition> selectAllPage(PubCondition pubCondition, PageInfo info) {
		PubConditionMapper mapper = (PubConditionMapper) getDao();
		return mapper.selectAllPage(pubCondition, info);
	}

	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            条件信息ID数组(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:24:03
	 */
	@Override
	public void deleteBatch(String pids) throws FrameworkException {
		PubConditionMapper mapper = (PubConditionMapper) getDao();
		if (null != pids && !pids.equals("")) {
			String[] str = pids.split(",");
			mapper.deleteBatch(str);
		}
	}

	/**
	 * 
	 * @Description : 条件设定编辑
	 * @param pubCondition
	 *            条件设定对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 下午4:55:22
	 */
	@Override
	public void pubConditionEdit(PubCondition pubCondition) throws FrameworkException {
		PubConditionMapper mapper = (PubConditionMapper) getDao();
		if(!pubCondition.getPid().trim().equals("") && !pubCondition.getPid().trim().equals("-1") ){
			// 修改
			pubCondition.setLastUpdateUser(pubCondition.getCreateUser());
			pubCondition.setLastUpdateTime(DateUtil.getToday());
			mapper.updateByPrimaryKeySelective(pubCondition);
		}else{
			// 新增
			pubCondition.setPid(pubCondition.getPK());// 设置主键
			// 设置条件设定编号
			pubCondition.setCondCode(sysCreateCodeRuleService.generatNoRuleNoDateTime("T","1",4,pubCondition.getCreateUser()));
			pubCondition.setCreateTime(DateUtil.getToday());// 创建时间
			mapper.insertSelective(pubCondition);
		}
	}

	@Override
	public List<PubCondition> findListByBorrowId(String borrowId) {
		PubConditionMapper mapper = (PubConditionMapper) getDao();
		return mapper.findListByBorrowId(borrowId);
	}
}
