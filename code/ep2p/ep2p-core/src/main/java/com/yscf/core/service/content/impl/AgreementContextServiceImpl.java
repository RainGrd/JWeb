/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议内容服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Lin Xu		Initial Version.
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
import com.yscf.core.dao.content.AgreementContentMapper;
import com.yscf.core.model.content.AgreementContent;
import com.yscf.core.service.content.IAgreementContextService;

/**
 * Description：<br> 
 * 协议内容服务层
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Service("agreementContextServiceImpl")
public class AgreementContextServiceImpl extends BaseService<BaseEntity, String> implements IAgreementContextService {
	
	@Autowired
	public AgreementContextServiceImpl(AgreementContentMapper dao) {
		super(dao);
	}

	/**
	 * 通过查询的条件进行查询协议内容信息
	 */
	@Override
	public PageList<AgreementContent> selectByPrimaryObj(
			AgreementContent agccont, PageInfo pageinfo) {
		AgreementContentMapper accMap = (AgreementContentMapper) getDao();
		return accMap.selectByPrimaryObj(agccont, pageinfo);
	}

	/**
	 * 批量修改对象信息
	 */
	@Override
	public void updateBatchObj(List<AgreementContent> agccont) {
		AgreementContentMapper accMap = (AgreementContentMapper) getDao();
		accMap.updateBatchObj(agccont);
	}

	/**
	 * 通过协议模板内容的id查询对应的模板信息
	 */
	@Override
	public PageList<AgreementContent> selectByPrimaryObjAll(String atempid) {
		AgreementContentMapper accMap = (AgreementContentMapper) getDao();
		AgreementContent agccont = new AgreementContent();
		agccont.setAgrConTemId(atempid);
		agccont.setStatus("1");
		return accMap.selectByPrimaryObj(agccont);
	}

	@Override
	public AgreementContent getById(String borrowAgreementId) {
		AgreementContentMapper accMap = (AgreementContentMapper) getDao();
		return accMap.selectByPrimaryKey(borrowAgreementId);
	}

	
}


