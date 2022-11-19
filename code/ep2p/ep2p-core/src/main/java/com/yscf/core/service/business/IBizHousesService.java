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
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizHouses;

/**
 * Description：<br>
 * TODO
 * 
 * @author Yu.Zhang
 * @date 2015年9月22日
 * @version v1.0.0
 */
public interface IBizHousesService {

	/**
	 * 
	 * Description：<br>
	 * 查询过滤重复的省
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<BizHouses> selectDistinctProvince(BizHouses record);

	/**
	 * 
	 * Description：<br>
	 * 根据省查询过滤重复的市
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<BizHouses> selectDistinctCityByProvince(BizHouses record);

	/**
	 * 
	 * Description：<br>
	 * 根据省,市 查询过滤重复的区
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<BizHouses> selectDistinctAreaByCity(BizHouses record);

	/**
	 * 
	 * Description：<br>
	 * 根据省,市,区 查询过滤重复的楼盘名称
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<BizHouses> selectDistinctHomesNameByAddress(BizHouses record);

	/**
	 * 
	 * Description：<br>
	 * 根据省,市,区,楼盘名称 查询户型
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<BizHouses> selectDistinctHomesTypeByHomesName(BizHouses record);

	public int updateByPrimaryKeySelective(BizHouses record);

	/**
	 * 
	 * Description：<br>
	 * 查询数据带分页
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param record
	 * @param info
	 * @return
	 */
	public PageList<BizHouses> selectAllPage(BizHouses record, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 根据借款id 查询房产信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public BizHouses selectHousesByBorrowId(String borrowId);
}
