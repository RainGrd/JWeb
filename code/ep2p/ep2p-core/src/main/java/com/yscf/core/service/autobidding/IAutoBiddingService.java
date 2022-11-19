/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月8日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.autobidding;

import java.util.List;

import com.yscf.core.model.ptp.autobidding.AutoBidding;

/**
 * Description：<br> 
 * 自动投标服务层
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
public interface IAutoBiddingService {
	
	/**
	 * Description：<br> 
	 * 通过参数获取信息参数插入数据
	 * @author  Lin Xu
	 * @date    2016年1月7日
	 * @version v1.0.0
	 * @param autobid
	 */
	public void insertSelective(AutoBidding autobid);
	
	/**
	 * Description：<br> 
	 * 通过用户的ID获取自动投标的列表信息
	 * @author  Lin Xu
	 * @date    2015年12月8日
	 * @version v1.0.0
	 * @param userid
	 * @return
	 */
	public List<AutoBidding> selectAutoBiddingList(String userid);
	
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
	public AutoBidding selectKeyVersionAutoBidding(String userid,String pid,String vsion);
	
	/**
	 * Description：<br> 
	 * 修改自动投标信息
	 * @author  Lin Xu
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(AutoBidding record);
	
	/**
	 * Description：<br> 
	 * 失效已经启动的自动投标的数据
	 * @author  Lin Xu
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @param userid
	 * @return
	 */
	public boolean updateInvalidStartBidding(String userid);
	
}


