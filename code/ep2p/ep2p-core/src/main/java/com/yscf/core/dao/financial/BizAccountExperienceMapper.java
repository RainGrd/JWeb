package com.yscf.core.dao.financial;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizAccountExperience;

/**
 * 
 * Description：<br> 
 * 体验金资金类别
 * @author  Jie.Zou
 * @date    2015年12月23日
 * @version v1.0.0
 */
@MapperScan("bizAccountExperienceMapper")
public interface BizAccountExperienceMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizAccountExperience record);

    int insertSelective(BizAccountExperience record);

    BizAccountExperience selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizAccountExperience record);

    int updateByPrimaryKey(BizAccountExperience record);
    
    /**
     * 
     * Description：<br> 
     * 通过客户ID得到体验金资金类别对象
     * @author  Jie.Zou
     * @date    2015年12月23日
     * @version v1.0.0
     * @param customerId 客户ID
     * @return
     */
    BizAccountExperience selectAccountExperienceByCustomerId(String customerId);
    
    /**
     * 
     * Description：<br> 
     * 通过体验金卷集合Id得到体验金集合
     * @author  Jie.Zou
     * @date    2015年12月31日
     * @version v1.0.0
     * @param pids
     * @return
     */
    List<BizAccountExperience> selectExperienceByPids(String[] pids);
    
    /**
	 * 
	 * Description：<br> 
	 * 通过客户ID结合批量修改体验资金可用余额
	 * @author  Jie.Zou
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param userid
	 * @param amount
	 * @return
	 */
	int updateAmountByCustIds(Map<String, Object> map);
    
}