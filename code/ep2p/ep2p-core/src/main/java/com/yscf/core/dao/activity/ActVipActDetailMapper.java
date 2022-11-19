package com.yscf.core.dao.activity;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActVipActDetail;

/**
 * 
 * @ClassName : ActVipActDetailMapper
 * @Description : 赠送VIP活动明细数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月9日 下午4:33:29
 */
public interface ActVipActDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ActVipActDetail record);

	int insertSelective(ActVipActDetail record);

	ActVipActDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ActVipActDetail record);

	int updateByPrimaryKey(ActVipActDetail record);

	/**
	 * 
	 * @Description : 查询赠送VIP活动列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月9日 下午4:36:13
	 */
	public PageList<ActVipActDetail> selectAllPage(@Param("actVipActDetail") ActVipActDetail actVipActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询赠送VIP活动明细列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月9日 下午5:04:08
	 */
	public PageList<ActVipActDetail> selectAllPageDetail(@Param("actVipActDetail") ActVipActDetail actVipActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 赠送VIP活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午10:42:47
	 */
	public List<ActVipActDetail> selectAllPageDetailExport(HashMap<String, Object> map);

	/**
	 * 
	 * @Description : 查询参与人数的总计
	 * @param actVipActDetail
	 *            赠送VIP明细对象
	 * @return 参与人数总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午2:34:22
	 */
	public Integer selectParticipantsNumberByCondition(@Param("actVipActDetail") ActVipActDetail actVipActDetail);

	/**
	 * 
	 * @Description : 批量新增赠送VIP信息
	 * @param list
	 *            赠送明细list
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午8:02:52
	 */
	public void insertBatch(List<ActVipActDetail> list);
}