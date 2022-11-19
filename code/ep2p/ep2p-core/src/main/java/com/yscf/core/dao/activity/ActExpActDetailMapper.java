package com.yscf.core.dao.activity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActExpActDetail;

/**
 * 
 * @ClassName : ActExpActDetailMapper
 * @Description : 体验金活动数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月19日 上午10:36:11
 */
public interface ActExpActDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ActExpActDetail record);

	int insertSelective(ActExpActDetail record);

	ActExpActDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ActExpActDetail record);

	int updateByPrimaryKey(ActExpActDetail record);

	/**
	 * Description：根据pid查询客户体验金流水明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActExpActDetail> selectTiYanJinDetailsById(@Param("actExpActDetail") ActExpActDetail actExpActDetail, PageInfo info);

	/**
	 * Description：客户体验金资金流水列表条件查询
	 * 
	 * @author heng.wang
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActExpActDetail> selectAllPage(@Param("actExpActDetail") ActExpActDetail actExpActDetailc, PageInfo info);

	/**
	 * 
	 * @Description : 体验金赠送查询
	 * @param actExpActDetailc
	 *            体验金明细对象
	 * @param info
	 *            分页对象
	 * @return 体验金赠送列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 上午11:40:18
	 */
	public PageList<ActExpActDetail> selectAllPageByCondition(@Param("actExpActDetail") ActExpActDetail actExpActDetailc, PageInfo info);

	/**
	 * 
	 * @Description : 查询赠送体验金活动明细列表,带分页
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @param info
	 *            分页对象
	 * @return 体验金明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 上午11:41:47
	 */
	public PageList<ActExpActDetail> selectAllPageDetail(@Param("actExpActDetail") ActExpActDetail actExpActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询获得金额的总计
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 获得金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:24:13
	 */
	public BigDecimal selectGetAmountByCondition(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 查询使用金额的总计
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 使用金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:24:49
	 */
	public BigDecimal selectUseAmountByCondition(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 统计体验金
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 使用金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:24:49
	 */
	public Map<String, BigDecimal> selectTiYanJinDetailsByIdSum(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 统计已使用的体验金
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 使用金额的总计
	 * @Author :heng.wang
	 * @Date : 2015年10月19日 下午3:24:49
	 */
	public Map<String, BigDecimal> selectUseTiYanJinDetailsByIdSum(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 体验金赠送明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 下午3:42:52
	 */
	public List<ActExpActDetail> selectAllPageDetailExport(HashMap<String, Object> map);

	/**
	 * 
	 * @Description : 查询参与人数的总计
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @return 参与人数的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午3:03:47
	 */
	public Integer selectParticipantsNumberByCondition(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 查询体验金的总计
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @return 体验金的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午3:25:13
	 */
	public BigDecimal selectBaseExpAmount(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 查询已获得总额的总计
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @return 获得总额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午3:25:23
	 */
	public BigDecimal selectBaseGetAmount(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 查询已使用金额的总计
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @return 使用金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午3:25:42
	 */
	public BigDecimal selectBaseUseAmount(@Param("actExpActDetail") ActExpActDetail actExpActDetail);

	/**
	 * 
	 * @Description : 批量新增
	 * @param list
	 *            需要插入的list集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午8:19:22
	 */
	public void insertBatch(List<ActExpActDetail> list);

	/**
	 * 
	 * @Description : 批量修改使用体验金状态为已使用
	 * @param pids
	 *            PID主键数组(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午2:58:33
	 */
	public void batchUpdateExpDetailUseStatus(String[] pids);

	/**
	 * 
	 * @Description : 查询客户当前可用体验金劵
	 * @param customerId
	 *            客户ID
	 * @return 体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午3:16:33
	 */
	public List<ActExpActDetail> selectCustTomerExperienceGold(@Param("customerId") String customerId);

	/**
	 * 
	 * @Description : 查询客户当前可用体验金劵-API
	 * @param customerId
	 *            客户ID
	 * @return 体验金列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午6:59:46
	 */
	public List<ActExpActDetail> selectCustTomerExperienceGoldAPI(@Param("customerId") String customerId);

	/**
	 * 
	 * @Description : 根据PID数组查询体验金对象
	 * @param pids
	 *            PID主键数组(1,2,3)
	 * @return 体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午4:34:58
	 */
	public List<ActExpActDetail> selectExperienceGoldByPidArr(String[] pids);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid得到体验金对象
	 * @author  Jie.Zou
	 * @date    2016年2月1日
	 * @version v1.0.0
	 * @param expId
	 * @return
	 */
	public ActExpActDetail selectExperienceGoldById(@Param("expId")String expId);
}