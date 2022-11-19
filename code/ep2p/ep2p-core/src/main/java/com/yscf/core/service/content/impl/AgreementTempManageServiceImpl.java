/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议模板管理服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.AgreementContTempMapper;
import com.yscf.core.model.content.AgreementContTemp;
import com.yscf.core.service.content.IAgreementTempManageService;

/**
 * Description：<br> 
 * 协议模板管理服务层
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
@Service("agreementTempManageServiceImpl")
public class AgreementTempManageServiceImpl extends BaseService<BaseEntity, String> implements IAgreementTempManageService {

	@Autowired
	public AgreementTempManageServiceImpl(AgreementContTempMapper dao) {
		super(dao);
	}

	
	/**
	 * 通过查询的条件进行查询协议模板信息
	 */
	@Override
	public PageList<AgreementContTemp> selectByPrimaryObj(AgreementContTemp agctemp, PageInfo pageinfo) {
		AgreementContTempMapper actempMap = (AgreementContTempMapper) getDao();
		return actempMap.selectByPrimaryObj(agctemp, pageinfo);
	}


	/**
	 * 通过一定的条件去查询数据进行更新数据
	 */
	@Override
	public int updateByPrimaryKeySelective(AgreementContTemp record) {
		AgreementContTempMapper actempMap = (AgreementContTempMapper) getDao();
		return actempMap.updateByPrimaryKeySelective(record);
	}


	/**
	 * 查询所有的协议模板信息
	 */
	@Override
	public List<AgreementContTemp> selectByAllObj(AgreementContTemp record) {
		AgreementContTempMapper actempMap = (AgreementContTempMapper) getDao();
		return actempMap.selectByAllObj(record);
	}

	
	
}


