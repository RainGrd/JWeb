package com.yscf.core.dao.activity;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubRedpacket;

/**
 * 
 * @ClassName : PubRedpacketMapper
 * @Description : 红包信息数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月8日 下午4:15:27
 */
public interface PubRedpacketMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(PubRedpacket record);

	int insertSelective(PubRedpacket record);

	PubRedpacket selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(PubRedpacket record);

	int updateByPrimaryKey(PubRedpacket record);

	/**
	 * 
	 * @Description : 查询红包信息列表,带分页
	 * @param pubRedpacket
	 *            红包信息对象
	 * @param info
	 *            分页对象
	 * @return 红包信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:19:37
	 */
	PageList<PubRedpacket> selectAllPage(@Param("pubRedpacket") PubRedpacket pubRedpacket, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除红包信息
	 * @param pids
	 *            主键ID(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:20:55
	 */
	public void deleteBatch(String[] pids);
	
	/**
	 * 
	 * @Description : 修改活动的状态
	 * @param pid
	 *            　活动ＩＤ
	 * @param stastus
	 *            　状态
	 * @Author : Qing.Cai
	 * @Date : 2015年12月26日 下午2:57:52
	 */
	public void updateActiivtyStatus(@Param("pid")String pid, @Param("status")String status);

}