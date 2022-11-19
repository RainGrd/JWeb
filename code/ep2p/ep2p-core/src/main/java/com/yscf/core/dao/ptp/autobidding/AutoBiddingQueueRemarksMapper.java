/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标队列备注表(监控每一次自动投标，每一个人失败和成功的原因表)
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.ptp.autobidding;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueueRemarks;

/**
 * Description：<br> 
 * 自动投标队列备注表(监控每一次自动投标，每一个人失败和成功的原因表)
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
@MapperScan("autoBiddingQueueRemarksMapper")
public interface AutoBiddingQueueRemarksMapper extends IBaseDao<BaseEntity, String>  {
	
    int deleteByPrimaryKey(String pid);

    int insert(AutoBiddingQueueRemarks record);

    int insertSelective(AutoBiddingQueueRemarks record);

    AutoBiddingQueueRemarks selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(AutoBiddingQueueRemarks record);

    int updateByPrimaryKeyWithBLOBs(AutoBiddingQueueRemarks record);

    int updateByPrimaryKey(AutoBiddingQueueRemarks record);

}