package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustPoinWaterMapper;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.user.ICustPoinWaterService;

/**
 * Description：<br>
 * 客户积分明细服务实现
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@Service("custPoinWaterServiceImpl")
public class CustPoinWaterServiceImpl extends BaseService<BaseEntity, String> implements ICustPoinWaterService {

	private Logger logger = LoggerFactory.getLogger(CustPoinWaterServiceImpl.class);

	@Autowired
	public CustPoinWaterServiceImpl(CustPoinWaterMapper dao) {
		super(dao);
	}

	@Autowired
	public SysParamsMapper sysParamsMapper;

	@Autowired
	public CusTomerMapper cusTomerMapper;

	@Override
	public PageList<CustPoinWater> selectAvailablePointDetailsById(String pid, PageInfo info) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		CustPoinWater custPoinWater = new CustPoinWater();
		custPoinWater.setCustomerId(pid);
		return mapper.selectAvailablePointDetailsById(custPoinWater, info);
	}

	@Override
	public PageList<CustPoinWater> selectAllPage(CustPoinWater custPoinWater, PageInfo pageInfo) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		return mapper.selectAllPage(custPoinWater, pageInfo);
	}

	/**
	 * 
	 * @Description : 积分对外接口
	 * @param pointGetType
	 *            积分类型 (自己去查看数据字典的积分类型-数据字典类型编码是：POINT_GET_TYPE)
	 * @param investmentMoney
	 *            金额(只有当类型为投资积分,金额才需要传入,其他类型金额为0)
	 * @param customerId
	 *            当前登录的客户ID
	 * @return 0:失败 其他:数值表示获得积分值
	 * @Author : Qing.Cai
	 * @Date : 2015年12月1日 下午3:13:36
	 */
	@Override
	public Integer pointObtain(String pointGetType, BigDecimal investmentMoney, String customerId) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		Integer point = 0;
		// 根据类型获取系统参数对象
		SysParams sysParams = sysParamsMapper.getParamsByParamKey(pointGetType);
		// 判断是否为null，如果为null说明当前类型没有添加
		if (null != sysParams) {
			// 根据当前登录的客户ID查询客户的信息
			// 获取积分基数比例值
			Double pointValue = 0.0;
			// 解析
			String paramValue = sysParams.getParamValue();
			// 判断是否存在<,>
			if (paramValue.indexOf(",") != -1) {
				String[] pointArr = paramValue.split(",");
				// 存在<,>就判断是否是VIP
				if ("1".equals(cusTomerMapper.getVip(customerId))) {
					// 是VIP
					pointValue = Double.parseDouble(pointArr[1]);
				} else {
					// 不是VIP
					pointValue = Double.parseDouble(pointArr[0]);
				}
			} else {
				// 不存在
				pointValue = Double.parseDouble(paramValue);
			}

			// 在判断类型是否属于投资奖励
			if (pointGetType.equals(Constant.POINT_TYPE_8)) {
				BigDecimal pointbd = new BigDecimal(pointValue).multiply(investmentMoney).divide(new BigDecimal(100),0,BigDecimal.ROUND_HALF_UP);
				point = pointbd.intValue();
			} else {
				point = pointValue.intValue();
			}
			// 原积分
			Integer srcPoint = cusTomerMapper.getSrcPointByUserId(customerId);
			srcPoint = srcPoint == null ? 0 : srcPoint;
			// 添加一条数据到积分明细里面去
			CustPoinWater cpw = new CustPoinWater();
			cpw.setPid(cpw.getPK());// 设置主键
			cpw.setPointValue(point);// 设置积分值
			cpw.setPointGetType(pointGetType);// 设置积分获取类型
			cpw.setPointType(pointGetType);// 设置积分类型
			cpw.setAvailablePoint(srcPoint + point);// 设置积分值
			cpw.setCustomerId(customerId);// 设置客户ID
			cpw.setStatus(Constant.PUBLIC_STATUS);// 设置状态为1
			cpw.setHappenTime(DateUtil.getToday().toString());// 设置发生时间
			cpw.setCreateUser(customerId);// 设置创建人ID
			cpw.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			cpw.setPotWatDesc("获取积分：" + point);// 设置备注

			// 添加积分流水
			mapper.insertSelective(cpw);

			// 往用户增加积分
			cusTomerMapper.updateAddSrcPointByUserId(customerId, point);

		}
		return point;
	}

	/**
	 * 
	 * @Description : 获取预算积分
	 * @param investmentMoney
	 *            投资金额
	 * @param customerId
	 *            当前登录的客户ID
	 * @return 可获得的积分
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午4:49:37
	 */
	@Override
	public Integer pointObtainTemp(BigDecimal investmentMoney, String customerId) {
		Integer point = 0;
		try {
			// 根据类型获取系统参数对象
			SysParams sysParams = sysParamsMapper.getParamsByParamKey("pointGetType_tzjf");
			// 判断是否为null，如果为null说明当前类型没有添加
			if (null != sysParams) {
				// 根据当前登录的客户ID查询客户的信息
				CusTomer cusTomer = cusTomerMapper.getBuyUserId(customerId);
				// 获取积分基数比例值
				Double pointValue = 0.0;
				// 解析
				String paramValue = sysParams.getParamValue();
				// 判断是否存在<,>
				if (paramValue.indexOf(",") != -1) {
					String[] pointArr = paramValue.split(",");
					// 存在<,>就判断是否是VIP
					if (null != cusTomer.getIsVip() && "1".equals(cusTomerMapper.getVip(customerId))) {
						// 是VIP
						pointValue = Double.parseDouble(pointArr[1]);
					} else {
						// 不是VIP
						pointValue = Double.parseDouble(pointArr[0]);
					}
				} else {
					// 不存在
					pointValue = Double.parseDouble(paramValue);
				}
				BigDecimal pointbd = new BigDecimal(pointValue).multiply(investmentMoney).divide(new BigDecimal(100),0,BigDecimal.ROUND_HALF_UP);
				point = pointbd.intValue();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("获取预算积分：", e.getMessage());
			}
		}
		return point;
	}

	/**
	 * 
	 * @Description : 查询个人的可用积分and累计积分
	 * @param customerId
	 *            客户ID
	 * @return 积分对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月3日 下午5:59:26
	 */
	@Override
	public CustPoinWater selectUserPoint(String customerId) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		CustPoinWater custPoinWater = new CustPoinWater();
		try {
			custPoinWater = mapper.selectUserPoint(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人的可用积分and累计积分：", e.getMessage());
			}
		}
		return custPoinWater;
	}

	/**
	 * 
	 * @Description : 查询个人积分明细
	 * @param custPoinWater
	 *            积分明细对象
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 个人积分明细
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:22:49
	 */
	@Override
	public List<CustPoinWater> selectUserPointDetail(CustPoinWater custPoinWater, Integer pageIndex, Integer pageSize) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		List<CustPoinWater> list = null;
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			// 修改获得时间格式-begin
			if (null != custPoinWater.getHappenBeginTime() && !"".equals(custPoinWater.getHappenBeginTime())) {
				custPoinWater.setHappenBeginTime(EscfDateUtil.formatterDate(custPoinWater.getHappenBeginTime(), 1));
			}
			// 修改获得时间格式-end
			if (null != custPoinWater.getHappenEndTime() && !"".equals(custPoinWater.getHappenEndTime())) {
				custPoinWater.setHappenEndTime(EscfDateUtil.formatterDate(custPoinWater.getHappenEndTime(), 2));
			}
			// 赋值查询条件
			map.put("custPoinWater", custPoinWater);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			// 查询
			list = mapper.selectUserPointDetail(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人积分明细：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询个人积分明细-API
	 * @param custPoinWater
	 *            积分明细对象
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 个人积分明细
	 * @Author : Qing.Cai
	 * @Date : 2016年2月24日 下午2:35:09
	 */
	@Override
	public List<CustPoinWater> selectUserPointDetailAPI(String userId, Integer pageIndex, Integer pageSize) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		List<CustPoinWater> list = null;
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			// 赋值查询条件
			map.put("userId", userId);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			// 查询
			list = mapper.selectUserPointDetailAPI(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人积分明细：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询个人积分明细的总记录数
	 * @param custPoinWater
	 *            积分明细对象
	 * @return 积分明细的总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:51:37
	 */
	@Override
	public Integer selectUserPointDetailCount(CustPoinWater custPoinWater) {
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		Integer count = 0;
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custPoinWater", custPoinWater);
			count = mapper.selectUserPointDetailCount(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人积分明细的总记录数：", e.getMessage());
			}
		}
		return count;
	}

	@Override
	public int insertCustPointWater(CustPoinWater custPoinWater) {
		int i = 0;
		CustPoinWaterMapper mapper = (CustPoinWaterMapper) getDao();
		String custId = custPoinWater.getCustomerId();
		// 原积分
		Integer srcPoint = cusTomerMapper.getSrcPointByUserId(custId);
		srcPoint = srcPoint == null ? 0 : srcPoint;
		custPoinWater.setAvailablePoint(srcPoint + custPoinWater.getPointValue());// 设置积分值
		mapper.insertCustPointWater(custPoinWater);
		// 往用户增加积分
		cusTomerMapper.updateAddSrcPointByUserId(custId, custPoinWater.getPointValue());
		return i;
	}

}
