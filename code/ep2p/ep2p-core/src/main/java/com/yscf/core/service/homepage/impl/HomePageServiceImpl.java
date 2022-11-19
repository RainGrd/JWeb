/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 首页特殊查询服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.homepage.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.ptp.homepage.HomePageMapper;
import com.yscf.core.service.homepage.IHomePageService;

/**
 * Description：<br> 
 * 首页特殊查询服务层
 * @author  Lin Xu
 * @date    2015年11月13日
 * @version v1.0.0
 */
@Service("homepServiceImpl")
public class HomePageServiceImpl extends BaseService<BaseEntity, String> implements IHomePageService {
	
	Logger logger = Logger.getLogger(HomePageServiceImpl.class);

	@Autowired
	public HomePageServiceImpl(HomePageMapper dao) {
		super(dao);
	}

	/**
	 * 获取最高年利率
	 */
	@Override
	public BigDecimal getMaximumAPR() {
		HomePageMapper homempper = (HomePageMapper) getDao();
		return homempper.getMaximumAPR();
	}
	
	

}


