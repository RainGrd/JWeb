package com.yscf.core.dao.system;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysVipinfo;

/**
 * 
 * @ClassName : SysVipinfoMapper
 * @Description : VIP信息表数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月29日 下午3:43:10
 */
public interface SysVipinfoMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(SysVipinfo record);

	int insertSelective(SysVipinfo record);

	SysVipinfo selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysVipinfo record);

	int updateByPrimaryKey(SysVipinfo record);

	/**
	 * 
	 * @Description : 查询系统VIP信息,带分页
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @param info
	 *            分页对象
	 * @return VIP信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月2日 下午4:15:16
	 */
	public PageList<SysVipinfo> selectAllPage(@Param("sysVipinfo") SysVipinfo sysVipinfo, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            VIP信息ID数组(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:22:20
	 */
	public void deleteBatch(String[] pids);

	/**
	 * 
	 * @Description : 根据客户id查vip等级
	 * @param pids
	 * @Author : heng.wang
	 * @Date : 2015年12月11日
	 */
	public List<SysVipinfo> selectVipLevelById(String userId);

	/**
	 * 
	 * @Description : 根据VIP等级查询VIP对象
	 * @param vipLevel
	 *            VIP等级
	 * @return VIP对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午4:51:27
	 */
	public SysVipinfo getByLevel(String vipLevel);
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户现在的利息管理费率
	 * @author  JunJie.Liu
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal selectFeeByUserId(@Param("userId") String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据经验值查询所在VIP级别
	 * @author  Yu.Zhang
	 * @date    2016年1月22日
	 * @version v1.0.0
	 * @param experienceEnd
	 * @return
	 */
	public SysVipinfo selectVipInfoByEmpiricalValue(@Param("experienceEnd") Integer experienceEnd);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询vipinfoId
	 * @author  wangheng
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @return
	 */
	public SysVipinfo selectVipInfoPid(); 

}