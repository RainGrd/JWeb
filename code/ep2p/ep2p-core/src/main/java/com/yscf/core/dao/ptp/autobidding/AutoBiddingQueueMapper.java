/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标队列表信息
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
import com.yscf.core.model.ptp.autobidding.AutoBiddPersonModel;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;

/**
 * Description：<br> 
 * 自动投标队列表信息
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
@MapperScan("autoBiddingQueueMapper")
public interface AutoBiddingQueueMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

    int insert(AutoBiddingQueue record);

    int insertSelective(AutoBiddingQueue record);

    AutoBiddingQueue selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(AutoBiddingQueue record);

    int updateByPrimaryKey(AutoBiddingQueue record);
    
    /**
     * Description：<br> 
     * 强制移除队列信息
     * @author  Lin Xu
     * @date    2015年12月11日
     * @version v1.0.0
     * @param record
     * @return
     */
    public int updateRemoveQueue(AutoBiddingQueue record);
    
    /**
     * Description：<br> 
     * 通过用户id和自动投标id查询排队信息 
     * @author  Lin Xu
     * @date    2015年12月12日
     * @version v1.0.0
     * @param userid
     * @param autobdid
     * @return
     */
    public List<AutoBiddingQueue> selectByPrimaryUbdId(@Param("userid")String userid,@Param("autobdid")String autobdid);
    
    /**
     * Description：<br> 
     * 查询投资人的投资队列
     * @author  Lin Xu
     * @date    2015年12月19日
     * @version v1.0.0
     * @param param
     * @return
     */
    public List<AutoBiddPersonModel> selectInsertQeneList(HashMap<String, Object> param);
    
}