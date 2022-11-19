/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标操作
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.ptp.autobidding;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.ptp.autobidding.AutoBidding;

/**
 * Description：<br> 
 * 自动投标操作
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
@MapperScan("autoBiddingMapper")
public interface AutoBiddingMapper extends IBaseDao<BaseEntity, String>  {
	
    int deleteByPrimaryKey(String pid);

    int insert(AutoBidding record);

    int insertSelective(AutoBidding record);

    AutoBidding selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(AutoBidding record);

    int updateByPrimaryKey(AutoBidding record);
    
    /**
     * Description：<br> 
     * 通过用户的ID获取自动投标的列表信息
     * @author  Lin Xu
     * @date    2015年12月8日
     * @version v1.0.0
     * @param param
     * @return
     */
    public List<AutoBidding> selectAutoBiddingList(HashMap<String, Object> param);
    
    /**
     * Description：<br> 
     * 查询已经启动的投标列表
     * @author  Lin Xu
     * @date    2015年12月11日
     * @version v1.0.0
     * @param userid
     * @return
     */
    public List<AutoBidding> selectStartBiddingList(@Param("userid")String userid);
    
    /**
	 * Description：<br> 
	 * 通过主键和版本号查询对应用户的自动投标信息
	 * @author  Lin Xu
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param userid
	 * @param pid
	 * @param vsion
	 * @return
	 */
	public AutoBidding selectKeyVersionAutoBidding(@Param("userid")String userid, @Param("pid")String pid, @Param("vsion")String vsion);
	
	/**
	 * Description：<br> 
	 * 失效已经启动的自动投标的数据
	 * @author  Lin Xu
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @param userid
	 * @return
	 */
	public int updateInvalidStartBidding(@Param("userid")String userid);
	
	
	
	
	
}