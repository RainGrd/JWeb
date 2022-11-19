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
 * 2015年9月11日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.PubCondition;

/**
 * 
 * @ClassName : IPubConditionService
 * @Description : 条件管理业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月23日 上午10:32:01
 */
public interface IPubConditionService {

	/**
	 * 
	 * @Description : 查询所有有效的条件信息
	 * @return 有效的条件信息list
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:30:10
	 */
	List<PubCondition> selectPubConditionBySatusEffective();

	/**
	 * 
	 * @Description : 查询条件信息,带分业
	 * @param pubCondition
	 *            条件信息对象
	 * @param info
	 *            分页对象
	 * @return 条件信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:37:10
	 */
	public PageList<PubCondition> selectAllPage(PubCondition pubCondition, PageInfo info);
	
	/**
	 * 
	 * @Description	:  TODO
	 * @param pubCondition 
	 * @Author 		:  Qing.Cai 
	 * @Date		:  2015年10月27日 下午4:50:29
	 */
	public void pubConditionEdit(PubCondition pubCondition) throws FrameworkException;
	
	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            条件信息ID数组(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:23:42
	 */
	public void deleteBatch(String pids) throws FrameworkException ;
	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款id，查询关联的条件对象集合
	 * @author  JunJie.Liu
	 * @date    2016年2月25日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<PubCondition> findListByBorrowId(String borrowId);
}
