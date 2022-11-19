/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议内容服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.exchange.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.ExchangeConstant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.system.SysDictionaryContentMapper;
import com.yscf.core.dao.system.SysVipinfoMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustExchangeDetailMapper;
import com.yscf.core.dao.user.CustInterestTicketMapper;
import com.yscf.core.dao.user.CustLargessVipWaterMapper;
import com.yscf.core.dao.user.CustPoinWaterMapper;
import com.yscf.core.exception.PointNotEnoughException;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustExchangeDetail;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.model.user.CustLargessVipWater;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.business.impl.BizFundtallyServiceImpl;
import com.yscf.core.service.exchange.IExchangeService;
import com.yscf.core.service.financial.impl.BizAccountCommonServiceImpl;

/**
 * 
 * @ClassName : ExchangeServiceImpl
 * @Description : 兑换通用的商务接口
 * @Author : Qing.Cai
 * @Date : 2016年1月3日 下午6:32:38
 */
@Service("exchangeServiceImpl")
public class ExchangeServiceImpl extends BaseService<BaseEntity, String> implements IExchangeService {

	private Logger logger = LoggerFactory.getLogger(ExchangeServiceImpl.class);

	@Autowired
	public ExchangeServiceImpl(CustPoinWaterMapper dao) {
		super(dao);
	}

	/** 数据字典内容Mapper接口 */
	@Autowired
	public SysDictionaryContentMapper sysDictionaryContentMapper;

	/** 客户信息Mapper接口 */
	@Autowired
	public CusTomerMapper cusTomerMapper;

	/** 加息劵Mapper接口 */
	@Autowired
	public CustInterestTicketMapper custInterestTicketMapper;

	/** 系统VipMapper接口 */
	@Autowired
	public SysVipinfoMapper sysVipinfoMapper;

	/** 客户积分流水Mapper接口 */
	@Autowired
	public CustPoinWaterMapper custPoinWaterMapper;

	/** 客户VIP流水Mapper接口 */
	@Autowired
	public CustLargessVipWaterMapper custLargessVipWaterMapper;

	/** 普通资金处理实现类 */
	@Autowired
	public BizAccountCommonServiceImpl bizAccountCommonServiceImpl;

	/** 普通资金处理实现类 */
	@Autowired
	public BizFundtallyServiceImpl bizFundtallyServiceImpl;

	/** 积分兑换明细Mapper接口 */
	@Autowired
	public CustExchangeDetailMapper custExchangeDetailMapper;

	/**
	 * 
	 * @Description : 积分兑换话费
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @param phoneNo
	 *            兑换成功的手机号码
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:05:42
	 */
	@Override
	public String exchangePhoneFees(String userId, String dictionaryContentId, String phoneNo) throws FrameworkException, PointNotEnoughException {
		String resultStr = "";
		try {
			// 获取客户对象
			CusTomer cusTomer = cusTomerMapper.getBuyUserId(userId);
			// 获取兑换所需的积分数据字典内容对象
			SysDictionaryContent sysDictionaryContent = sysDictionaryContentMapper.selectByPrimaryKey(dictionaryContentId);
			// 获取当前时间
			String dataTime = DateUtil.getToday().toString();
			// 话费
			int telephoneFare = 0;
			// 兑换所需的积分
			int point = 0;
			// 获取兑换的话费的值or兑换所需积分
			if (null != sysDictionaryContent) {
				// 如果是兑换的第一种
				telephoneFare = Integer.parseInt(sysDictionaryContent.getDictContName());
				point = Integer.parseInt(sysDictionaryContent.getDictContDesc());
			}

			// 判断用户可用积分是否足够兑换
			if (cusTomer.getAvailablePoint() < point) {
				throw new PointNotEnoughException("用户可用积分不够.");
			}

			cusTomer.setAvailablePoint(cusTomer.getAvailablePoint() - point);// 再设置客户的可用积分
			cusTomer.setLastUpdateUser(userId);// 设置最后修改人
			cusTomer.setLastUpdateTime(dataTime);// 设置最后修改时间
			// 修改客户资料
			cusTomerMapper.updateCustTomerByExchange(cusTomer);

			// 调用修改交易密码的时间
			Map<String, Object> map = new HashMap<String, Object>();
			// 创建Map
			map.put("userId", userId);
			map.put("createTime", dataTime);
			cusTomerMapper.updateLastTransacTime(map);

			// 充值话费外部接口
			String urlApix = "http://p.apix.cn/apixlife/pay/phone/phone_recharge";
			// 获取订单号
			Date date = new Date();
			SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String orderid = myFmt2.format(date);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((phoneNo+telephoneFare+orderid).getBytes());
            byte b[] = md.digest();
            int i;
            // MD5加密
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 获取32位小写加密
			String sign = buf.toString();
			// 参数
			urlApix += "?phone="+phoneNo+"&price="+telephoneFare+"&orderid="+orderid+"&sign="+sign;
			// 创建URL对象
			URL url = new URL(urlApix);
			// 创建Connection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("content-type", "application/json");
			conn.setRequestProperty("apix-key", "b7cb9a5777a8468259092a3e0b44d055");
			resultStr = inputStream2String(conn.getInputStream(), "ISO-8859-1");
			
			
			// 创建客户积分流水明细对象
			CustPoinWater custPoinWater = new CustPoinWater();
			// 赋值
			custPoinWater.setPid(custPoinWater.getPK());// 设置主键
			custPoinWater.setCustomerId(userId);// 设置客户ID
			custPoinWater.setPointValue(point);// 消耗多少积分
			custPoinWater.setPointType(Constant.POINT_TYPE_2);// 设置积分类型
			custPoinWater.setAvailablePoint(cusTomer.getAvailablePoint());// 设置兑换后的可用积分
			custPoinWater.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常
			custPoinWater.setHappenTime(dataTime);// 设置发生时间
			custPoinWater.setCreateUser(userId);// 设置创建人
			custPoinWater.setCreateTime(dataTime);// 设置创建时间
			custPoinWater.setPotWatDesc("消耗" + point + "积分兑换" + telephoneFare + "元话费(" + phoneNo + ")");// 设置描述
			// 新增客户积分流水表信息
			custPoinWaterMapper.insertSelective(custPoinWater);

			// 创建兑换明细列表
			CustExchangeDetail custExchangeDetail = new CustExchangeDetail();
			// 赋值
			custExchangeDetail.setPid(custExchangeDetail.getPK());// 设置主键
			custExchangeDetail.setCustomerId(userId);// 设置用户ID
			custExchangeDetail.setExchangePoint(point);// 设置兑换所需积分
			custExchangeDetail.setExchangeRemark("兑换话费" + telephoneFare + "元（" + phoneNo + "）");// 设置兑换备注(兑换商品+手机号码)
			custExchangeDetail.setExchangeStatus(ExchangeConstant.EXCHANGE_STATUS_1);// 设置兑换状态
			custExchangeDetail.setExchangeType(ExchangeConstant.EXCHANGE_DETAIL_TYPE_1);// 设置兑换类型
			custExchangeDetail.setExchangeTime(dataTime);// 设置兑换时间
			custExchangeDetail.setCreateUser(userId);// 设置创建人ID
			custExchangeDetail.setCreateTime(dataTime);// 设置创建时间
			// 新增兑换明细列表
			custExchangeDetailMapper.insertSelective(custExchangeDetail);

		} catch (PointNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换话费:" + e.getMessage());
			}
		}
		return resultStr;
	}

	/**
	 * 
	 * @Description : 积分兑换加息卷
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:05:22
	 */
	@Override
	public void exchangeInterestTicket(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException {
		try {
			// 获取客户对象
			CusTomer cusTomer = cusTomerMapper.getBuyUserId(userId);
			// 获取兑换所需的积分数据字典内容对象
			SysDictionaryContent sysDictionaryContent = sysDictionaryContentMapper.selectByPrimaryKey(dictionaryContentId);
			// 获取当前时间
			String dataTime = DateUtil.getToday().toString();
			// 加息卷值
			Double interestTicket = 0.0;
			// 兑换所需的积分
			int point = 0;
			// 获取兑换的加息卷的值or兑换所需积分
			if (null != sysDictionaryContent) {
				// 如果是兑换的第一种
				interestTicket = Double.parseDouble(sysDictionaryContent.getDictContName());
				point = Integer.parseInt(sysDictionaryContent.getDictContDesc());
			}

			// 判断用户可用积分是否足够兑换
			if (cusTomer.getAvailablePoint() < point) {
				throw new PointNotEnoughException("用户可用积分不够.");
			}

			cusTomer.setAvailablePoint(cusTomer.getAvailablePoint() - point);// 再设置客户的可用积分
			cusTomer.setLastUpdateUser(userId);// 设置最后修改人
			cusTomer.setLastUpdateTime(dataTime);// 设置最后修改时间
			// 修改客户资料
			cusTomerMapper.updateCustTomerByExchange(cusTomer);

			// 调用修改交易密码的时间
			Map<String, Object> map = new HashMap<String, Object>();
			// 创建Map
			map.put("userId", userId);
			map.put("createTime", dataTime);
			cusTomerMapper.updateLastTransacTime(map);

			// 创建客户积分流水明细对象
			CustPoinWater custPoinWater = new CustPoinWater();
			// 赋值
			custPoinWater.setPid(custPoinWater.getPK());// 设置主键
			custPoinWater.setCustomerId(userId);// 设置客户ID
			custPoinWater.setPointValue(point);// 消耗多少积分
			custPoinWater.setPointType(Constant.POINT_TYPE_3);// 设置积分类型
			custPoinWater.setAvailablePoint(cusTomer.getAvailablePoint());// 设置兑换后的可用积分
			custPoinWater.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常
			custPoinWater.setHappenTime(dataTime);// 设置发生时间
			custPoinWater.setCreateUser(userId);// 设置创建人
			custPoinWater.setCreateTime(dataTime);// 设置创建时间
			custPoinWater.setPotWatDesc("消耗" + point + "积分兑换" + interestTicket + "%加息劵");// 设置描述
			// 新增客户积分流水表信息
			custPoinWaterMapper.insertSelective(custPoinWater);

			// 新增客户加息劵
			CustInterestTicket custInterestTicket = new CustInterestTicket();
			// 赋值
			custInterestTicket.setPid(custInterestTicket.getPK());// 设置主键
			custInterestTicket.setCustomerId(userId);// 设置客户ID
			custInterestTicket.setName("");// 设置加息劵名称
			custInterestTicket.setScale(interestTicket / 100);// 设置加息劵比例
			custInterestTicket.setStartTime(dataTime);// 设置有效期开始时间
			// 创建计算时间的对象
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(DateUtil.format(dataTime));
			gc.add(5, ExchangeConstant.EXCHANGE_INTEREST_TICKET_VALIDITY);
			String endTime = DateUtil.format(gc.getTime());
			custInterestTicket.setEndTime(endTime);// 设置有效期结束时间
			custInterestTicket.setValidity(ExchangeConstant.EXCHANGE_INTEREST_TICKET_VALIDITY);// 设置加息劵有效期
			custInterestTicket.setGetType(Constant.INTEREST_GET_TYPE_2);// 设置加息劵获得类型
			custInterestTicket.setUseStatus(Constant.USE_STATUS_2);// 设置加息劵使用状态
			custInterestTicket.setInterestDesc("消耗" + point + "积分兑换" + interestTicket + "%加息劵");// 设置描述
			custInterestTicket.setCreateUser(userId);// 设置创建人
			custInterestTicket.setCreateTime(dataTime);// 设置创建时间
			// 新增到客户加息劵表
			custInterestTicketMapper.insertSelective(custInterestTicket);

			// 创建兑换明细列表
			CustExchangeDetail custExchangeDetail = new CustExchangeDetail();
			// 赋值
			custExchangeDetail.setPid(custExchangeDetail.getPK());// 设置主键
			custExchangeDetail.setCustomerId(userId);// 设置用户ID
			custExchangeDetail.setExchangePoint(point);// 设置兑换所需积分
			custExchangeDetail.setExchangeRemark("兑换" + interestTicket + "%加息券");// 设置兑换备注
			custExchangeDetail.setExchangeStatus(ExchangeConstant.EXCHANGE_STATUS_1);// 设置兑换状态
			custExchangeDetail.setExchangeType(ExchangeConstant.EXCHANGE_DETAIL_TYPE_2);// 设置兑换类型
			custExchangeDetail.setExchangeTime(dataTime);// 设置兑换时间
			custExchangeDetail.setCreateUser(userId);// 设置创建人ID
			custExchangeDetail.setCreateTime(dataTime);// 设置创建时间
			// 新增兑换明细列表
			custExchangeDetailMapper.insertSelective(custExchangeDetail);

		} catch (PointNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换加息卷:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 积分兑换VIP
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @return VIP到期时间
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:05:03
	 */
	@Override
	public String exchangeVIP(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException {
		String vipEndTime = "";
		try {
			// 获取客户对象
			CusTomer cusTomer = cusTomerMapper.getBuyUserId(userId);
			// 获取兑换所需的积分数据字典内容对象
			SysDictionaryContent sysDictionaryContent = sysDictionaryContentMapper.selectByPrimaryKey(dictionaryContentId);
			// 获取当前时间
			String dataTime = DateUtil.getToday().toString();
			// 兑换的VIP月长
			int vipMonth = 0;
			// 需要消耗的积分值
			int point = 0;
			// 获取兑换的VIP的值or兑换所需积分
			if (null != sysDictionaryContent) {
				// 如果是兑换的第一种
				vipMonth = Integer.parseInt(sysDictionaryContent.getDictContName());
				point = Integer.parseInt(sysDictionaryContent.getDictContDesc());
			}

			// 判断用户可用积分是否足够兑换
			if (cusTomer.getAvailablePoint() < point) {
				throw new PointNotEnoughException("用户可用积分不够.");
			}

			// 判断是否是VIP
			if (cusTomer.getIsVip().equals("1")) {
				// 计算出当前时间加上几个月后的天数是多少天
				GregorianCalendar gcd = new GregorianCalendar();
				// 获取当前时间
				gcd.setTime(new Date());
				// 添加原来的日期
				gcd.add(Calendar.DATE, cusTomer.getVipTime());
				// 再添加兑换的月份
				gcd.add(Calendar.MONTH, vipMonth);
				// 获取兑换后的日期
				Date dt = gcd.getTime();
				// 计算兑换后的日期距离现在多少天
				long s1 = dt.getTime();// 将时间转为毫秒
				long s2 = System.currentTimeMillis();// 得到当前的毫秒
				// 获取距离时长
				int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
				// 设置VIP时长
				cusTomer.setVipTime(day);
			} else {
				// 如果不是VIP,先修改客户为VIP,并且VIP等级为VIP1
				cusTomer.setIsVip("1"); // 设置为VIP
				cusTomer.setVipLevel("1");// 设置VIP等级
				// 计算出当前时间加上几个月后的天数是多少天
				GregorianCalendar gcd = new GregorianCalendar();
				// 获取当前时间
				gcd.setTime(new Date());
				// 添加兑换的月份
				gcd.add(Calendar.MONTH, vipMonth);
				// 获取兑换后的日期
				Date dt = gcd.getTime();
				// 计算兑换后的日期距离现在多少天
				long s1 = dt.getTime();// 将时间转为毫秒
				long s2 = System.currentTimeMillis();// 得到当前的毫秒
				// 获取距离时长
				int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
				cusTomer.setVipTime(day);// 设置VIP时长
				// 再修改VIP等级ID
				String vipId = sysVipinfoMapper.getByLevel("1").getPid();
				cusTomer.setVipId(vipId);// 设置VipId
			}
			cusTomer.setAvailablePoint(cusTomer.getAvailablePoint() - point);// 再设置客户的可用积分
			cusTomer.setLastUpdateUser(userId);// 设置最后修改人
			cusTomer.setLastUpdateTime(dataTime);// 设置最后修改时间
			// 修改客户资料
			cusTomerMapper.updateCustTomerByExchange(cusTomer);

			// 调用修改交易密码的时间
			Map<String, Object> map = new HashMap<String, Object>();
			// 创建Map
			map.put("userId", userId);
			map.put("createTime", dataTime);
			cusTomerMapper.updateLastTransacTime(map);

			// 创建客户积分流水明细对象
			CustPoinWater custPoinWater = new CustPoinWater();
			// 赋值
			custPoinWater.setPid(custPoinWater.getPK());// 设置主键
			custPoinWater.setCustomerId(userId);// 设置客户ID
			custPoinWater.setPointValue(point);// 消耗多少积分
			custPoinWater.setPointType(Constant.POINT_TYPE_4);// 设置积分类型
			custPoinWater.setAvailablePoint(cusTomer.getAvailablePoint());// 设置兑换后的可用积分
			custPoinWater.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常
			custPoinWater.setHappenTime(dataTime);// 设置发生时间
			custPoinWater.setCreateUser(userId);// 设置创建人
			custPoinWater.setCreateTime(dataTime);// 设置创建时间
			custPoinWater.setPotWatDesc("消耗" + point + "积分兑换" + vipMonth + "个月VIP");// 备注
			// 新增客户积分流水表信息
			custPoinWaterMapper.insertSelective(custPoinWater);

			// 创建客户VIP流水表
			CustLargessVipWater custLargessVipWater = new CustLargessVipWater();
			// 赋值
			custLargessVipWater.setPid(custLargessVipWater.getPK());// 设置主键
			custLargessVipWater.setCustomerId(userId);// 设置客户ID
			custLargessVipWater.setVipinfoId("");// 设置VIP活动ID,因为是兑换,所有没有VIP活动ID
			custLargessVipWater.setLargessValue(vipMonth + "月");// 设置兑换时长
			custLargessVipWater.setGetType(Constant.LARGESS_VIP_TYPE_2);// 设置获得VIP类型
			custLargessVipWater.setStatus(Constant.PUBLIC_STATUS);// 状态为1,表示正常
			custLargessVipWater.setDistributionTime(dataTime);
			custLargessVipWater.setCreateUser(userId);// 设置创建人
			custLargessVipWater.setCreateTime(dataTime);// 设置创建时间
			custLargessVipWater.setLarVipWatDesc("消耗" + point + "积分兑换" + vipMonth + "个月VIP");

			// 新增VIP兑换明细
			custLargessVipWaterMapper.insertSelective(custLargessVipWater);

			// 创建兑换明细列表
			CustExchangeDetail custExchangeDetail = new CustExchangeDetail();
			// 赋值
			custExchangeDetail.setPid(custExchangeDetail.getPK());// 设置主键
			custExchangeDetail.setCustomerId(userId);// 设置用户ID
			custExchangeDetail.setExchangePoint(point);// 设置兑换所需积分
			custExchangeDetail.setExchangeRemark("兑换VIP" + vipMonth + "个月");// 设置兑换备注
			custExchangeDetail.setExchangeStatus(ExchangeConstant.EXCHANGE_STATUS_1);// 设置兑换状态
			custExchangeDetail.setExchangeType(ExchangeConstant.EXCHANGE_DETAIL_TYPE_3);// 设置兑换类型
			custExchangeDetail.setExchangeTime(dataTime);// 设置兑换时间
			custExchangeDetail.setCreateUser(userId);// 设置创建人ID
			custExchangeDetail.setCreateTime(dataTime);// 设置创建时间
			// 新增兑换明细列表
			custExchangeDetailMapper.insertSelective(custExchangeDetail);

			// 创建计算时间的对象
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			gc.add(Calendar.DATE, cusTomer.getVipTime());
			vipEndTime = DateUtil.format(gc.getTime());

		} catch (PointNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换VIP:" + e.getMessage());
			}
		}
		return vipEndTime;
	}

	/**
	 * 
	 * @Description : 积分兑换现金
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:04:06
	 */
	@Override
	public void exchangeCash(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException {
		try {
			// 获取客户对象
			CusTomer cusTomer = cusTomerMapper.getBuyUserId(userId);
			// 获取兑换所需的积分数据字典内容对象
			SysDictionaryContent sysDictionaryContent = sysDictionaryContentMapper.selectByPrimaryKey(dictionaryContentId);
			// 获取当前时间
			String dataTime = DateUtil.getToday().toString();
			// 现金
			int cash = 0;
			// 兑换所需的积分
			int point = 0;
			// 获取兑换的现金的值or兑换所需积分
			if (null != sysDictionaryContent) {
				// 如果是兑换的第一种
				cash = Integer.parseInt(sysDictionaryContent.getDictContName());
				point = Integer.parseInt(sysDictionaryContent.getDictContDesc());
			}

			// 判断用户可用积分是否足够兑换
			if (cusTomer.getAvailablePoint() < point) {
				throw new PointNotEnoughException("用户可用积分不够.");
			}

			cusTomer.setAvailablePoint(cusTomer.getAvailablePoint() - point);// 再设置客户的可用积分
			cusTomer.setLastUpdateUser(userId);// 设置最后修改人
			cusTomer.setLastUpdateTime(dataTime);// 设置最后修改时间
			// 修改客户资料
			cusTomerMapper.updateCustTomerByExchange(cusTomer);

			// 创建list作为参数
			List<String> userIds = new ArrayList<String>();
			userIds.add(userId);
			// 备注
			String note = cusTomer.getSname() + " " + point + "积分兑换" + cash + "元";
			// 修改系统资金
			bizFundtallyServiceImpl.insertBizFundtallyByUserIds(userIds, new BigDecimal(cash), Constant.CUST_FUND_BUSINESS_TYPE_2, TradeTypeConstant.SYSTEM_TRADE_TYPE_1018, DateUtil.format(dataTime), note);
			// 修改客户个人资金
			bizAccountCommonServiceImpl.modifyAvailableAmountWithTallyCreateNoTran(userId, Constant.CUST_FUND_BUSINESS_TYPE_1, TradeTypeConstant.RETURNS_TYPE_417, new BigDecimal(cash), note, DateUtil.format(dataTime));
			// 调用修改交易密码的时间
			Map<String, Object> map = new HashMap<String, Object>();
			// 创建Map
			map.put("userId", userId);
			map.put("createTime", dataTime);
			cusTomerMapper.updateLastTransacTime(map);

			// 创建客户积分流水明细对象
			CustPoinWater custPoinWater = new CustPoinWater();
			// 赋值
			custPoinWater.setPid(custPoinWater.getPK());// 设置主键
			custPoinWater.setCustomerId(userId);// 设置客户ID
			custPoinWater.setPointValue(point);// 消耗多少积分
			custPoinWater.setPointType(Constant.POINT_TYPE_5);// 设置积分类型
			custPoinWater.setAvailablePoint(cusTomer.getAvailablePoint());// 设置兑换后的可用积分
			custPoinWater.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常
			custPoinWater.setHappenTime(dataTime);// 设置发生时间
			custPoinWater.setCreateUser(userId);// 设置创建人
			custPoinWater.setCreateTime(dataTime);// 设置创建时间
			custPoinWater.setPotWatDesc("消耗" + point + "积分兑换" + cash + "元现金");// 设置描述
			// 新增客户积分流水表信息
			custPoinWaterMapper.insertSelective(custPoinWater);

			// 创建兑换明细列表
			CustExchangeDetail custExchangeDetail = new CustExchangeDetail();
			// 赋值
			custExchangeDetail.setPid(custExchangeDetail.getPK());// 设置主键
			custExchangeDetail.setCustomerId(userId);// 设置用户ID
			custExchangeDetail.setExchangePoint(point);// 设置兑换所需积分
			custExchangeDetail.setExchangeRemark("兑换" + cash + "元现金");// 设置兑换备注
			custExchangeDetail.setExchangeStatus(ExchangeConstant.EXCHANGE_STATUS_1);// 设置兑换状态
			custExchangeDetail.setExchangeType(ExchangeConstant.EXCHANGE_DETAIL_TYPE_4);// 设置兑换类型
			custExchangeDetail.setExchangeTime(dataTime);// 设置兑换时间
			custExchangeDetail.setCreateUser(userId);// 设置创建人ID
			custExchangeDetail.setCreateTime(dataTime);// 设置创建时间
			// 新增兑换明细列表
			custExchangeDetailMapper.insertSelective(custExchangeDetail);

		} catch (PointNotEnoughException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换现金:" + e.getMessage());
			}
		}
	}

	// 输出流的处理
	private String inputStream2String(InputStream is, String charset) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
		}
		return null;
	}

}
