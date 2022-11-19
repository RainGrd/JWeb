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
package com.yscf.core.service.autobidding;

import java.util.List;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;

/**
 * Description：<br> 
 * 自动投标操作队列接口     
 * @author  Lin Xu
 * @date    2015年12月12日
 * @version v1.0.0
 */
public interface IAutoBiddingQueueService {
	
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
	 * 添加队列排队信息
	 * @author  Lin Xu
	 * @date    2015年12月12日
	 * @version v1.0.0
	 * @param abqmodel
	 */
	public void insertSelective(AutoBiddingQueue abqmodel);
	
	
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
    public List<AutoBiddingQueue> selectByPrimaryUbdId(String userid,String autobdid);
    
    
    
}


