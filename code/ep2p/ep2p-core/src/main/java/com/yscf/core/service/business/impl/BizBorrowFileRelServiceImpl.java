/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BizBorrowFileRelMapper;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.service.business.IBizBorrowFileRelService;

/**
 * Description：<br> 
 * 借款文件中间表业务处理类
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Service("bizBorrowFileRelService")
public class BizBorrowFileRelServiceImpl extends BaseService<BaseEntity, String> implements
	IBizBorrowFileRelService{

	@Autowired
	public BizBorrowFileRelServiceImpl(BizBorrowFileRelMapper dao) {
		super(dao);
	}

	@Override
	public List<BizBorrowFileRel> selectAll(BizBorrowFileRel borrow) {
		BizBorrowFileRelMapper mapper = (BizBorrowFileRelMapper) getDao();
		return  mapper.selectAll(borrow);
	}

	@Override
	public int updateByPrimaryKeySelective(BizBorrowFileRel record) {
		BizBorrowFileRelMapper mapper = (BizBorrowFileRelMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<BizBorrowFileRel> selectByBorrowId(String borrowId) {
		BizBorrowFileRelMapper mapper = (BizBorrowFileRelMapper) getDao();
		return mapper.selectByBorrowId(borrowId);
	}

	@Override
	public int insertSelective(BizBorrowFileRel borrowFile) {
		BizBorrowFileRelMapper mapper = (BizBorrowFileRelMapper) getDao();
		return mapper.insertSelective(borrowFile);
	}

}


