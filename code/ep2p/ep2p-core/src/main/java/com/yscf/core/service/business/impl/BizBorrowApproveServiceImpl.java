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
 * 2015年9月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.business.BizBorrowApproveMapper;
import com.yscf.core.dao.business.BizBorrowHisMapper;
import com.yscf.core.dao.business.BizBorrowMapper;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowApprove;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.service.business.IBizBorrowApproveService;

/**
 * Description：<br> 
 * 借款审批业务处理类
 * @author  Yu.Zhang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("bizBorrowApproveService")
public class BizBorrowApproveServiceImpl  extends BaseService<BaseEntity, String>  implements IBizBorrowApproveService {
	
	@Autowired
	public BizBorrowApproveServiceImpl(BizBorrowApproveMapper dao) {
		super(dao);
	}

	private Logger logger = LoggerFactory.getLogger(BizBorrowApproveServiceImpl.class);
	
	@Resource
	private BizBorrowMapper bizBorrowMapper;	// 借款	
	
	@Resource
	private BizBorrowHisMapper bizBorrowHisMapper;	// 借款历史
	
	
	
	@Override
	public void executeApprove(BizBorrowInfoVO borrowInfoVo) {
		if(null!= borrowInfoVo && null!=borrowInfoVo.getPid()){
			BizBorrowApproveMapper bizBorrowApproveMapper = (BizBorrowApproveMapper) getDao();
			if(logger.isDebugEnabled()){
				logger.debug("-----------"+borrowInfoVo.getApproveUser()+"   借款流程审批开始---------------");
			}
			
			// 1 提交担保初审，设置申请时间
			if(Constant.BORROW_APPROVE_STATUS_1.equals(borrowInfoVo.getApproveStatus())){
				BizBorrow borrow = new BizBorrow();
				borrow.setPid(borrowInfoVo.getPid());
				borrow.setApplyTime(DateUtil.format(DateUtil.getToday()));
				borrow.setBorrowTime(DateUtil.format(DateUtil.getToday()));
				borrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
				borrow.setLastUpdateUser(borrowInfoVo.getApproveUser());
				bizBorrowMapper.updateByPrimaryKeySelective(borrow);
				
				// 清空审批人
				borrowInfoVo.setApproveUser(null);
				
				// 设置审核状态为已通过
				borrowInfoVo.setApproveStatus(Constant.BORROW_APPROVE_STATUS_2);
			}
			
			// 2 担保拒绝后重新提交，则设置状态为担保审核
			if(Constant.BORROW_APPROVE_STATUS_3.equals(borrowInfoVo.getApproveStatus()) && Constant.BORROW_NODE_STATUS_0.equals(borrowInfoVo.getApproveNode())){
				borrowInfoVo.setApproveStatus(Constant.BORROW_APPROVE_STATUS_2);
			}
			
			BizBorrowApprove approver = getBizBorrowApprove(borrowInfoVo);
			bizBorrowApproveMapper.updateByPrimaryKeySelective(approver);
			
			// 后期可添加审批历史
			if(logger.isDebugEnabled()){
				logger.debug("-----------"+borrowInfoVo.getApproveUser()+"   借款流程审批结束---------------");
			}
		}
	}

	/**
	 * 
	 * Description：<br> 
	 * 获取审批信息
	 * @author  Yu.Zhang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param borrowInfoVo
	 * @return
	 */
	private BizBorrowApprove getBizBorrowApprove(BizBorrowInfoVO borrowInfoVo) {
		BizBorrowApprove approver = new BizBorrowApprove();
		approver.setPid(borrowInfoVo.getApproveId());
		approver.setBorHisId(borrowInfoVo.getBorrowHisId());
		approver.setLastUpdateUser(borrowInfoVo.getApproveUser());
		approver.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
		approver.setApproveStatus(borrowInfoVo.getApproveStatus());
		approver.setLendOpinion(borrowInfoVo.getLendOpinion());
		approver.setVouchOpinion(borrowInfoVo.getVouchOpinion());
		approver.setApproveTime(DateUtil.format(DateUtil.getToday()));
		approver.setApproveUser(borrowInfoVo.getApproveUser());
		approver.setBorCondDesc(borrowInfoVo.getBorCondDesc());
		return approver;
	}

}


