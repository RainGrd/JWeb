package com.yscf.core.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizHouses;

/**
 * 
 * Description：<br>
 * 楼盘管理数据交互接口
 * 
 * @author Yu.Zhang
 * @date 2015年9月22日
 * @version v1.0.0
 */
public interface BizHousesMapper extends IBaseDao<BaseEntity, String> {

	int deleteByPrimaryKey(String pid);

	int insert(BizHouses record);

	int insertSelective(BizHouses record);

	BizHouses selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(BizHouses record);

	int updateByPrimaryKey(BizHouses record);

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
	List<BizHouses> selectDistinctProvince(BizHouses record);

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
	List<BizHouses> selectDistinctCityByProvince(BizHouses record);

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
	List<BizHouses> selectDistinctAreaByCity(BizHouses record);

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
	List<BizHouses> selectDistinctHomesNameByAddress(BizHouses record);

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
	List<BizHouses> selectDistinctHomesTypeByHomesName(BizHouses record);

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
	PageList<BizHouses> selectAllPage(@Param("map") BizHouses record,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 根据借款id 查询对应的楼盘信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	BizHouses selectHousesByBorrowId(String borrowId);
}