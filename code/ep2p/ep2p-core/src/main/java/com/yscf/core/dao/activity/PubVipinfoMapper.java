package com.yscf.core.dao.activity;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubVipinfo;

/**
 * 
 * @ClassName : PubVipinfoMapper
 * @Description : VIPD信息数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年9月25日 上午3:37:21
 */
public interface PubVipinfoMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(PubVipinfo record);

	int insertSelective(PubVipinfo record);

	PubVipinfo selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(PubVipinfo record);

	int updateByPrimaryKey(PubVipinfo record);

	/**
	 * 
	 * @Description : 查询VIP信息列表,带分页
	 * @param pubVipinfo
	 *            VIP信息对象
	 * @param info
	 *            分页对象
	 * @return VIP信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午4:16:13
	 */
	PageList<PubVipinfo> selectAllPage(@Param("pubVipinfo") PubVipinfo pubVipinfo, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除体验金活动
	 * @param pids
	 *            体验金ID数组
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:58:20
	 */
	public void deleteBatch(String[] pids);

	/**
	 * 
	 * Description：<br>
	 * 根据pid获取vip信息
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月24日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public PubVipinfo getById(String pid);

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