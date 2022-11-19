package com.yscf.core.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.Bank;
/**
 * Description：银行卡数据访问层接口
 * @author  Allen
 * @date    2015年9月14日
 * @version v1.0.0
 */
@MapperScan("bankMapper")
public interface BankMapper extends IBaseDao<BaseEntity, String> {
	/**
	 * Description：根据PID来删除
	 * @author  Allen
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param userName 系统银行卡
	 * @return boolean
	 * @throws FrameworkException
	 */
	int deleteByPrimaryKey(String pid);
	/**
	 * Description：新增银行卡
	 * @author  Allen
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param userName 系统银行卡
	 * @return boolean
	 * @throws FrameworkException
	 */
    int insert(Bank bank);

    int insertSelective(Bank bank);
    /**
	 * Description：根据PID查银行卡
	 * @author  Allen
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param userName 系统银行卡
	 * @return boolean
	 * @throws FrameworkException
	 */
    Bank selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Bank bank);
    /**
	 * Description：根据PID来更新银行卡
	 * @author  Allen
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param userName 系统银行卡
	 * @return boolean
	 * @throws FrameworkException
	 */
    int updateByPrimaryKey(Bank bank);
    /**
	 * Description：查询银行卡列表,带分页功能的
	 * @author  Allen
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param userName 系统银行卡
	 * @return boolean
	 * @throws FrameworkException
	 */
    public PageList<Bank> selectAllPage(@Param("cusTomer") Bank bank, PageInfo info);
    /**
   	 * Description：根据pid来批量更新客服ID
   	 * @author  Allen
   	 * @date    2015年9月16日
   	 * @version v1.0.0
   	 * @param userName 系统银行卡
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int updatCusIdByPrimaryKey(@Param("map")Map<String, Object> map);
    /**
   	 * Description：根据银行卡pid来查银行卡
   	 * @author  Allen
   	 * @date    2015年9月17日
   	 * @version v1.0.0
   	 * @param userName 系统银行卡
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<Bank> selectCusByPid(@Param("map")Map<String, Object> map);
    /**
	 * Description：根据客户id查询客户银行账户信息列表
	 * @author  heng.wang
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<Bank> selectBankInfoById(String pid);
	/**
	 * Description：根据银行卡号校验交易密码是否正确
	 * @author  heng.wang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int selectBankTradingPwdByBankCar(@Param("bank")Bank bank);
	/**
	 * Description：根据银行卡号逻辑删除
	 * @author  heng.wang
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updateBankStatus(@Param("bank")Bank bank);
	/**
	 * Description：验证交易密码
	 * @author  heng.wang
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int validateTradingPwd(@Param("bank")Bank bank);
	/**
	 * Description：保存新的交易密码
	 * @author  heng.wang
	 * @date    2015年12月02日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int saveTradingPwd(@Param("bank")Bank bank);
	/**
	 * Description：查询用户名在系统是否存在
	 * @author  heng.wang
	 * @date    2015年12月07日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int selectUserName(String userName);
	/**
	 * Description：保存新的登录密码
	 * @author  heng.wang
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updateLoginPwd(@Param("bank")Bank bank);
	
	/**
	 * Description：<br> 
	 * 根据客户 id 查询银行账户总记录条数
	 * @author  JingYu.Dai
	 * @date    2015年12月22日
	 * @version v1.0.0
	 * @param cusId
	 * @return int 查询客户的银行账户总记录条数
	 */
	int selectBankCountByCusId(String cusId);
	
	/**
	 * Description：<br> 
	 * Api添加银行卡信息
	 * @author  heng.wang
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param cusId
	 * @return map
	 */
	public int addBankInfo(@Param("map") HashMap<String, Object> map);
	
	/**
	 * Description：<br> 
	 * Api：完善银行卡信息
	 * @author  heng.wang
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param cusId
	 * @return map
	 */
	public int updateBankInfoApi(@Param("bank")Bank bank);
	
	/**
	 * Description：<br> 
	 * Api：是否可快捷支付
	 * @author  heng.wang
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param cusId
	 * @return map
	 */
	public int selectQuickPayment(String bankCardId); 
	
	/**
	 * 
	 * Description：<br> 
	 * 通过银行卡号得到银行卡
	 * @author  Jie.Zou
	 * @date    2016年3月11日
	 * @version v1.0.0
	 * @param bankCard
	 * @return
	 */
	public Bank selectByBankCard(String bankCard);
	
}