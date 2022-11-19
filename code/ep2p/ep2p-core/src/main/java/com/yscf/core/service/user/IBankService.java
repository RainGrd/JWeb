package com.yscf.core.service.user;
import java.util.HashMap;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.Bank;

/**
 * Description：<br> 
 * 银行卡管理服务接口
 * @author  Allen
 * @date    2015年9月14日
 * @version v1.0.0
 */
public interface IBankService {
	
	/**
	 * Description：新增银行卡
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(Bank bank)  throws FrameworkException; 
	
	
	/**
	 * Description：查询银行卡列表
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<Bank> selectAll(Bank bank, PageInfo pageInfo);
	/**
	 * Description：批量删除银行卡列表
	 * @author  Allen
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(String pids);
	/**
	 * Description：查询银行卡列表，带分页功能
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<Bank> selectAllPage(Bank bank, PageInfo info) ;
	/**
	 * Description：根据银行卡pid来更新客服id
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updatCusIdByPrimaryKey(Bank bank);
	/**
	 * Description：根据银行卡id查询列表
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<Bank> selectCusByPid(String pid);
	/**
	 * Description：根据客户id查询银行账户列表
	 * @author  Allen
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<Bank> selectBankInfoById(String pid);
	
	/**
	 * Description：<br> 
	 * 根据客户 id 查询银行账户总记录条数
	 * @author  JingYu.Dai
	 * @date    2015年12月22日
	 * @version v1.0.0
	 * @param pid
	 * @return int 查询客户的银行账户总记录条数
	 */
	int selectBankCountByCusId(String cusId) throws FrameworkException;
	/**
	 * Description：根据客户银行卡号查询银行交易密码
	 * @author heng.wang
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return int
	 * @throws FrameworkException
	 */
	public int selectBankTradingPwdByBankCar(Bank bank);
	/**
	 * Description：根据客户银行卡号修改状态
	 * @author heng.wang
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return int
	 * @throws FrameworkException
	 */
	public int updateBankStatus(Bank bank);
	/**
	 * 
	 * Description：<br> 
	 * 校验交易密码
	 * @author  heng.wang
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	int validateTradingPwd(Bank bank);
	/**
	 * 
	 * Description：<br> 
	 * 保存交易密码
	 * @author  heng.wang
	 * @date    2015年12月02日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int saveTradingPwd(Bank bank);
	/**
	 * 
	 * Description：<br> 
	 * 查询当前用户名是否存在
	 * @author  heng.wang
	 * @date    2015年12月07日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	int selectUserName(String userName);
	/**
	 * 
	 * Description：<br> 
	 * 保存修改后的登录密码
	 * @author  heng.wang
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int updateLoginPwd(Bank bank);
	
	/**
	 * 
	 * Description：<br> 
	 * api添加银行卡
	 * @author  heng.wang
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int addBankInfo(HashMap<String, Object> map);
	
	
	/**
	 * 
	 * Description：<br> 
	 * api完善银行卡信息
	 * @author  heng.wang
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int updateBankInfoApi(Bank bank);
	
	/**
	 * 
	 * Description：<br> 
	 * api查客户是否可快捷支付
	 * @author  heng.wang
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int selectQuickPayment(String bankCardId);
	
	/**
	 * Description：<br> 
	 * 根据银行卡号和key 获取银行卡信息
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param card，key
	 * @return Bank
	 * @throws FrameworkException
	 */
	public Bank getByCardBank(String key,String card) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 根据银行卡号 获取银行卡信息  提供给邹杰的接口
	 * @author  wangheng
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param card
	 * @return Bank
	 * @throws FrameworkException
	 */
	public Bank getByCardBank(String card) throws FrameworkException;
}
