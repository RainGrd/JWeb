/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标操作队列接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.autobidding.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueMapper;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;
import com.yscf.core.service.autobidding.IAutoBiddingQueueService;

/**
 * Description：<br> 
 * 自动投标操作队列接口   
 * @author  Lin Xu
 * @date    2015年12月12日
 * @version v1.0.0
 */
@Service("autoBiddingQueueServiceImpl")
public class AutoBiddingQueueServiceImpl extends BaseService<BaseEntity, String> implements IAutoBiddingQueueService {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(AutoBiddingQueueServiceImpl.class);
	
	@Autowired	
	public AutoBiddingQueueServiceImpl(AutoBiddingQueueMapper dao) {
		super( dao);
	}

	/**
	 * 添加队列排队信息
	 */
	@Override
	public void insertSelective(AutoBiddingQueue abqmodel) {
		AutoBiddingQueueMapper abqmapper = (AutoBiddingQueueMapper) getDao();
		abqmapper.insertSelective(abqmodel);
	}

	/**
	 * 通过用户id和自动投标id查询排队信息 
	 */
	@Override
	public List<AutoBiddingQueue> selectByPrimaryUbdId(String userid,
			String autobdid) {
		AutoBiddingQueueMapper abqmapper = (AutoBiddingQueueMapper) getDao();
		return abqmapper.selectByPrimaryUbdId(userid, autobdid);
	}

	/**
	 * 强制移除队列信息
	 */
	@Override
	public int updateRemoveQueue(AutoBiddingQueue record) {
		AutoBiddingQueueMapper abqmapper = (AutoBiddingQueueMapper) getDao();
		return abqmapper.updateRemoveQueue(record);
	}
	
	
	

}


