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
 * 2015年10月20日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.core.dao.business.BizBorrowCondRelMapper;
import com.yscf.core.model.business.BizBorrowCondRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.service.business.IBizBorrowCondRelService;

/**
 * Description：<br>
 * 借款信息与条件关系表 服务接口
 * 
 * @author fengshiliang
 * @date 2015年10月20日
 * @version v1.0.0
 */
@Service("bizBorrowCondRelServiceImpl")
public class BizBorrowCondRelServiceImpl extends
		BaseService<BaseEntity, String> implements IBizBorrowCondRelService {
	@Autowired
	public BizBorrowCondRelServiceImpl(BizBorrowCondRelMapper dao) {
		super(dao);
	}

	@Resource(name = "bizBorrowCondRelMapper")
	private BizBorrowCondRelMapper mapper;

	@Override
	public void batchAdd(BizBorrowInfoVO borrowInfoVO) {
		String condIds = borrowInfoVO.getCondIds();
		if (!StringUtil.isBlank(condIds)) {
			String[] condId = condIds.split(",");
			// 循环添加
			for (int i = 0; i < condId.length; i++) {
				// 创建活动参与条件关系对象
				BizBorrowCondRel bizBorrowCondRel = new BizBorrowCondRel();
				// 赋值
				bizBorrowCondRel.setPid(bizBorrowCondRel.getPK());// 设置PID
				bizBorrowCondRel.setBorrowId(borrowInfoVO.getPid());// 赋值ID
				bizBorrowCondRel.setCondId(condId[i]);// 设置条件ID
				bizBorrowCondRel.setStatus("1");// 设置状态为1:表示正常
				bizBorrowCondRel.setCreateUser(borrowInfoVO.getCreateUser());// 设置创建人
				bizBorrowCondRel.setCreateTime(DateUtil.getToday());// 设置创建时间
				// 新增活动参与条件信息
				mapper.insertSelective(bizBorrowCondRel);
			}
		}

	}

	@Override
	public void deleteByBorrowId(String borrowId) throws Exception {
		mapper.deleteByBorrowId(borrowId);
	}

	@Override
	public List<BizBorrowCondRel> selectNewStandardConRelByObjectId(
			String objectId) {
		return mapper.selectNewStandardConRelByObjectId(objectId);
	}

}
