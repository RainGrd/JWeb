package com.yscf.core.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustLargessVipWater;

/**
 * Description：<br>
 * 赠送vip服务实现
 * 
 * @author heng.wang
 * @date 2015年9月22日
 * @version v1.0.0
 */
@MapperScan("custLargessVipWaterServiceImpl")
public interface CustLargessVipWaterMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustLargessVipWater record);

	/**
	 * Description：新增赠送vip时长
	 * 
	 * @author heng.wang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	int insertSelective(CustLargessVipWater record);

	CustLargessVipWater selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustLargessVipWater record);

	int updateByPrimaryKey(CustLargessVipWater record);

	/**
	 * Description：根据客户pid来查赠送vip历史详情
	 * 
	 * @author heng.wang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustLargessVipWater> getVipHistoryDetailedById(@Param("custLargessVipWater") CustLargessVipWater custLargessVipWater, PageInfo info);

	/**
	 * 
	 * @Description : 批量新增赠送VIP流水记录信息
	 * @param list
	 *            赠送VIPlist集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午9:33:05
	 */
	public void insertBatch(List<CustLargessVipWater> list);
	
	/**
	 * 查要到期的vip流水交易记录
	 * @return
	 */
	public List<CustLargessVipWater> selectVipInfo();
}