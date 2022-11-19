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
 * 2015年11月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.ptp.homepage;

import java.math.BigDecimal;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 首页新数据查询mapper
 * @author  Lin Xu
 * @date    2015年11月13日
 * @version v1.0.0
 */
@MapperScan("homePageMapper")
public interface HomePageMapper extends IBaseDao<BaseEntity, String> {
	
	/**
	 * Description：<br> 
	 * 获取最高年利率
	 * @author  Lin Xu
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getMaximumAPR();

}


