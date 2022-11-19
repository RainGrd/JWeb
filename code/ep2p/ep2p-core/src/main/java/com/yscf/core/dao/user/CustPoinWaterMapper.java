package com.yscf.core.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustPoinWater;

/**
 * 
 * @ClassName : CustPoinWaterMapper
 * @Description : 积分历史纪录 数据层接口
 * @Author : Qing.Cai
 * @Date : 2016年1月3日 下午6:35:37
 */
public interface CustPoinWaterMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustPoinWater record);

	int insertSelective(CustPoinWater record);

	CustPoinWater selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustPoinWater record);

	int updateByPrimaryKey(CustPoinWater record);

	/**
	 * Description：根据pid查询客户积分明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustPoinWater> selectAvailablePointDetailsById(@Param("custPoinWater") CustPoinWater custPoinWater, PageInfo info);

	/**
	 * Description：客户积分列表条件查询
	 * 
	 * @author heng.wang
	 * @date 2015年10月08日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustPoinWater> selectAllPage(@Param("custPoinWater") CustPoinWater CustPoinWater, PageInfo info);

	/**
	 * 
	 * @Description : 查询个人积分明细
	 * @param map
	 *            查询条件Map
	 * @return 个人积分明细集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月2日 下午9:27:31
	 */
	public List<CustPoinWater> selectUserPointDetail(Map<String, Object> map);

	/**
	 * 
	 * @Description : API_查询个人明细积分
	 * @param map
	 *            查询条件Map
	 * @return 个人积分明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月22日 下午3:07:56
	 */
	public List<CustPoinWater> selectUserPointDetailAPI(Map<String, Object> map);

	/**
	 * 
	 * @Description : 查询个人积分明细的总记录数
	 * @param map
	 *            查询条件Map
	 * @return 个人积分明细的总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:29:37
	 */
	public Integer selectUserPointDetailCount(Map<String, Object> map);

	/**
	 * 
	 * @Description : 查询个人的可用积分and累计积分
	 * @param customerId
	 *            客户ID
	 * @return 积分对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月3日 下午5:57:31
	 */
	public CustPoinWater selectUserPoint(String customerId);

	/**
	 * 
	 * @Description : 批量新增积分流水记录信息
	 * @param list
	 *            积分流水记录
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午9:33:05
	 */
	public void insertBatch(List<CustPoinWater> list);
	
	/**
	 * 
	 * @Description : 新增积分流水记录信息
	 *            积分流水记录
	 * @Author : heng.wang
	 * @Date : 2016年2月16日 
	 */
	public int insertCustPointWater(CustPoinWater custPoinWater);

}