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
package com.yscf.core.service.activity.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.activity.ActVipActDetailMapper;
import com.yscf.core.model.activity.ActVipActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.IActVipActDetailService;

/**
 * 
 * @ClassName : ActVipActDetailServiceImpl
 * @Description : TODO
 * @Author : Qing.Cai
 * @Date : 2015年10月10日 下午3:07:31
 */
@Service("actVipActDetailServiceImpl")
public class ActVipActDetailServiceImpl extends BaseService<BaseEntity, String> implements IActVipActDetailService {

	private Logger logger = LoggerFactory.getLogger(ActVipActDetailServiceImpl.class);
	
	@Autowired
	public ActVipActDetailServiceImpl(ActVipActDetailMapper dao) {
		super(dao);
	}

	/**
	 * 
	 * @Description : 查询赠送VIP活动列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:40
	 */
	@Override
	public PageList<ActVipActDetail> selectAllPage(ActVipActDetail actVipActDetail, PageInfo info) {
		ActVipActDetailMapper mapper = (ActVipActDetailMapper) getDao();
		PageList<ActVipActDetail> list = null;
		try {
			list = new PageList<ActVipActDetail>();
			list = mapper.selectAllPage(actVipActDetail, info);
			if(null != list && list.size() != 0){
				ActVipActDetail avad = new ActVipActDetail();
				avad.setParticipantsNumber(mapper.selectParticipantsNumberByCondition(actVipActDetail));// 设置总的参与人数
				// 添加到list
				list.add(avad);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询赠送VIP活动列表:"+e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询赠送VIP活动明细列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:07:51
	 */
	@Override
	public PageList<ActVipActDetail> selectAllPageDetail(ActVipActDetail actVipActDetail, PageInfo info) {
		ActVipActDetailMapper mapper = (ActVipActDetailMapper) getDao();
		PageList<ActVipActDetail> list = new PageList<ActVipActDetail>();
		list = mapper.selectAllPageDetail(actVipActDetail, info);
		return list;
	}

	/**
	 * 
	 * @Description : 导出查询
	 * @param actVipActDetail
	 *            赠送VIP信息对象
	 * @param eom
	 *            导出对象
	 * @return 赠送VIP活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午10:46:14
	 */
	@Override
	public List<ActVipActDetail> selectAllPageDetailExport(ActVipActDetail actVipActDetail, ExportObjectModel eom) {
		List<ActVipActDetail> list = null;
		try {
			ActVipActDetailMapper bamapper = (ActVipActDetailMapper) getDao();
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("actVipActDetail", actVipActDetail);
			map.put("expm", eom);
			
			list = bamapper.selectAllPageDetailExport(map); 
			
			if(null == list){
				list = new ArrayList<ActVipActDetail>();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}

}
