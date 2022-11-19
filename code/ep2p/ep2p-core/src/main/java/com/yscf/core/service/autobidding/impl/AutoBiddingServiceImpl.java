/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标服务接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月8日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.autobidding.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingMapper;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueMapper;
import com.yscf.core.model.ptp.autobidding.AutoBidding;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;
import com.yscf.core.service.autobidding.IAutoBiddingService;

/**
 * Description：<br> 
 * 自动投标服务接口实现
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
@Service("autoBiddingServiceImpl")
public class AutoBiddingServiceImpl extends BaseService<BaseEntity, String> implements IAutoBiddingService {

	Logger logger = Logger.getLogger(AutoBiddingServiceImpl.class);
	
	@Autowired
	public AutoBiddingServiceImpl(AutoBiddingMapper dao) {
		super(dao);
	}
	
	@Resource(name="autoBiddingQueueMapper")
	private AutoBiddingQueueMapper autoBidQueueDao;
	
	/**
	 * 通过参数获取信息参数插入数据
	 */
	@Override
	public void insertSelective(AutoBidding autobid){
		AutoBiddingMapper mapper = (AutoBiddingMapper) getDao();
		mapper.insertSelective(autobid);
	}

	/**
	 * 通过用户的ID获取自动投标的列表信息
	 */
	@Override
	public List<AutoBidding> selectAutoBiddingList(String userid) {
		AutoBiddingMapper mapper = (AutoBiddingMapper) getDao();
		HashMap<String, Object> paramap = new HashMap<String, Object>();
		paramap.put("customerId", userid);
		return mapper.selectAutoBiddingList(paramap);
	}

	/**
	 * 通过主键和版本号查询对应用户的自动投标信息
	 */
	@Override
	public AutoBidding selectKeyVersionAutoBidding(String userid, String pid,
			String vsion) {
		AutoBiddingMapper mapper = (AutoBiddingMapper) getDao();
		return mapper.selectKeyVersionAutoBidding(userid, pid, vsion);
	}

	/**
	 * 修改自动投标信息
	 */
	@Override
	public int updateByPrimaryKeySelective(AutoBidding record) {
		AutoBiddingMapper mapper = (AutoBiddingMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 失效已经启动的自动投标的数据
	 */
	@Override
	public boolean updateInvalidStartBidding(String userid) {
		AutoBiddingMapper mapper = (AutoBiddingMapper) getDao();
		try{
			//获取
			List<AutoBidding> startBidList = mapper.selectStartBiddingList(userid);
			if(null != startBidList && startBidList.size() > 0){
				//1.失效
				mapper.updateInvalidStartBidding(userid);
				//2.进行移除队列中的信息数据
				for(AutoBidding abm : startBidList){
					AutoBiddingQueue abq = new AutoBiddingQueue();
					abq.setCustomerId(userid);
					abq.setAutoBiddingId(abm.getPid());
					abq.setLastUpdateUser(userid);
					autoBidQueueDao.updateRemoveQueue(abq);
				}
			}
			return true;
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("失效自动投标中已启动的投标：" + e.getMessage());
				e.printStackTrace();
			}
			return false;
		}
	}
	
}


