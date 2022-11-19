/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.SysCreateCodeRuleMapper;
import com.yscf.core.model.system.SysCreateCodeRule;
import com.yscf.core.service.system.ISysCreateCodeRuleService;

/**
 * 
 * @ClassName : SysCreateCodeRuleServiceImpl
 * @Description : 编号生成规则服务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月15日 上午11:11:44
 */
@Service("sysCreateCodeRuleService")
public class SysCreateCodeRuleServiceImpl extends BaseService<BaseEntity, String> implements ISysCreateCodeRuleService {

	private Logger logger = LoggerFactory.getLogger(SysCreateCodeRuleServiceImpl.class);

	@Autowired
	public SysCreateCodeRuleServiceImpl(SysCreateCodeRuleMapper dao) {
		super(dao);
	}

	/**
	 * 
	 * @Description : 查询编号生成规则信息,带分页
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @param info
	 *            分页对象
	 * @return 编号生成规则对象集合
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:37:07
	 */
	@Override
	public PageList<SysCreateCodeRule> selectAllPage(SysCreateCodeRule sysCreateCodeRule, PageInfo info) {
		SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();

		return mapper.selectAllPage(sysCreateCodeRule, info);
	}

	/**
	 * 
	 * @Description : 编号生成规则
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理)
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @param UserId
	 *            当前操作人的ID
	 * @return 新的编号
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:57:27
	 */
	@Override
	public String generatNoRule(String rulePrefix, String ruleType, Integer seqLength, String UserId) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 获取年份
			String year = DateUtil.format(new Date(), "yyyy");
			// 获取月份
			String month = DateUtil.format(new Date(), "MM");
			// 获取日期
			String day = DateUtil.format(new Date(), "dd");
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			sysCreateCodeRule.setRuleYear(year);// 设置年份
			sysCreateCodeRule.setRuleMonth(month);// 设置月份
			sysCreateCodeRule.setRuleDay(day);// 设置日期
			sysCreateCodeRule.setCreateUser(UserId);// 设置创建人
			sysCreateCodeRule.setCreateTime(DateUtil.getToday());// 设置创建时间
			// 查询序号
			String order = mapper.selectOrderByPrefixAnDateTime(sysCreateCodeRule);
			sysCreateCodeRule.setRuleOrder(Integer.parseInt(order));// 设置序号
			// 判断
			if (order.equals("1")) {
				// 如果序号等于1,就需要新增一条数据到编号表里面
				sysCreateCodeRule.setPid(sysCreateCodeRule.getPK());// 设置PID
				sysCreateCodeRule.setStatus("1");// 设置状态为1,表示正常
				// 新增数据
				mapper.insertSelective(sysCreateCodeRule);
			} else {
				// 如果序号大于1,就修改当前编号的序号为最新的
				sysCreateCodeRule.setLastCreateUser(UserId);// 最后修改人
				sysCreateCodeRule.setLastCreateTime(DateUtil.getToday());// 最后修改时间
				// 修改数据
				mapper.updateByPrimaryKeySelective(sysCreateCodeRule);
			}
			// 拼接序号
			String num = "";
			for (int i = 0; i < seqLength - order.length(); i++) {
				num += "0";
			}
			// 拼上序号
			num = num + order;
			// 拼出编号
			no = rulePrefix + year + month + day + num;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("编号生成规则:" + e.getMessage());
			}
		}
		return no;
	}

	/**
	 * 
	 * @Description : 临时编号生成规则
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @return 新的编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:57:30
	 */
	@Override
	public String generatNoRuleTem(String rulePrefix, String ruleType, Integer seqLength) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 获取年份
			String year = DateUtil.format(new Date(), "yyyy");
			// 获取月份
			String month = DateUtil.format(new Date(), "MM");
			// 获取日期
			String day = DateUtil.format(new Date(), "dd");
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			sysCreateCodeRule.setRuleYear(year);// 设置年份
			sysCreateCodeRule.setRuleMonth(month);// 设置月份
			sysCreateCodeRule.setRuleDay(day);// 设置日期
			// 查询序号
			String order = mapper.selectOrderByPrefixAnDateTime(sysCreateCodeRule);
			// 拼接序号
			String num = "";
			for (int i = 0; i < seqLength - order.length(); i++) {
				num += "0";
			}
			// 拼上序号
			num = num + order;
			// 拼出编号
			no = rulePrefix + year + month + day + num;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("临时编号生成规则:" + e.getMessage());
			}
		}
		return no;
	}

	/**
	 * 
	 * @Description : 临时编号生成规则(不带年月日)
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @return 新的编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:05:22
	 */
	@Override
	public String generatNoRuleTemNoDateTime(String rulePrefix, String ruleType, Integer seqLength) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			// 查询序号
			String order = mapper.selectOrderByPrefix(sysCreateCodeRule);
			// 拼接序号
			String num = "";
			for (int i = 0; i < seqLength - order.length(); i++) {
				num += "0";
			}
			// 拼上序号
			num = num + order;
			// 拼出编号
			no = rulePrefix + num;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("临时编号生成规则(不带年月日):" + e.getMessage());
			}
		}

		return no;
	}

	/**
	 * 
	 * @Description : 编号生成规则(不带年月日)
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @param UserId
	 *            当前操作人的ID
	 * @return 新的编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午6:00:05
	 */
	@Override
	public String generatNoRuleNoDateTime(String rulePrefix, String ruleType, Integer seqLength, String UserId) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			sysCreateCodeRule.setCreateUser(UserId);// 设置创建人
			sysCreateCodeRule.setCreateTime(DateUtil.getToday());// 设置创建时间
			// 查询序号
			String order = mapper.selectOrderByPrefix(sysCreateCodeRule);
			sysCreateCodeRule.setRuleOrder(Integer.parseInt(order));// 设置序号
			// 判断
			if (order.equals("1")) {
				// 如果序号等于1,就需要新增一条数据到编号表里面
				sysCreateCodeRule.setPid(sysCreateCodeRule.getPK());// 设置PID
				sysCreateCodeRule.setStatus("1");// 设置状态为1,表示正常
				// 新增数据
				mapper.insertSelective(sysCreateCodeRule);
			} else {
				// 如果序号大于1,就修改当前编号的序号为最新的
				sysCreateCodeRule.setLastCreateUser(UserId);// 最后修改人
				sysCreateCodeRule.setLastCreateTime(DateUtil.getToday());// 最后修改时间
				// 修改数据
				mapper.updateByPrimaryKeySelective(sysCreateCodeRule);
			}
			// 拼接序号
			String num = "";
			for (int i = 0; i < seqLength - order.length(); i++) {
				num += "0";
			}
			// 拼上序号
			num = num + order;
			// 拼出编号
			no = rulePrefix + num;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("编号生成规则(不带年月日):" + e.getMessage());
			}
		}
		return no;
	}

	/**
	 * 
	 * @Description : 各种标-生成编号规则
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param UserId
	 *            当前操作人的ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月5日 下午3:08:12
	 */
	@Override
	public String generatLabelNoRule(String rulePrefix, String ruleType, String UserId) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 获取年份
			String year = DateUtil.format(new Date(), "yy");
			// 获取月份
			String month = DateUtil.format(new Date(), "MM");
			// 获取日期
			String day = DateUtil.format(new Date(), "dd");
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			sysCreateCodeRule.setRuleYear(year);// 设置年份
			sysCreateCodeRule.setRuleMonth(month);// 设置月份
			sysCreateCodeRule.setRuleDay(day);// 设置日期
			sysCreateCodeRule.setCreateUser(UserId);// 设置创建人
			sysCreateCodeRule.setCreateTime(DateUtil.getToday());// 设置创建时间
			// 查询序号
			String order = mapper.selectOrderByPrefixAnDateTime(sysCreateCodeRule);
			sysCreateCodeRule.setRuleOrder(Integer.parseInt(order));// 设置序号
			// 判断
			if (order.equals("1")) {
				// 如果序号等于1,就需要新增一条数据到编号表里面
				sysCreateCodeRule.setPid(sysCreateCodeRule.getPK());// 设置PID
				sysCreateCodeRule.setStatus("1");// 设置状态为1,表示正常
				// 新增数据
				mapper.insertSelective(sysCreateCodeRule);
			} else {
				// 如果序号大于1,就修改当前编号的序号为最新的
				sysCreateCodeRule.setLastCreateUser(UserId);// 最后修改人
				sysCreateCodeRule.setLastCreateTime(DateUtil.getToday());// 最后修改时间
				// 修改数据
				mapper.updateByPrimaryKeySelective(sysCreateCodeRule);
			}
			// 拼出编号
			no = rulePrefix + year + month + day + order;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("各种标-生成编号规则:" + e.getMessage());
			}
		}
		return no;
	}

	/**
	 * 
	 * @Description : 各种标-生成编号规则临时
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @return 临时标的编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月31日 上午11:32:37
	 */
	@Override
	public String generatLabelNoRuleTem(String rulePrefix, String ruleType) throws FrameworkException {
		// 需要返回的编号
		String no = "";
		try {
			// 获取dao接口
			SysCreateCodeRuleMapper mapper = (SysCreateCodeRuleMapper) getDao();
			// 获取年份
			String year = DateUtil.format(new Date(), "yy");
			// 获取月份
			String month = DateUtil.format(new Date(), "MM");
			// 获取日期
			String day = DateUtil.format(new Date(), "dd");
			// 创建查询条件对象
			SysCreateCodeRule sysCreateCodeRule = new SysCreateCodeRule();
			sysCreateCodeRule.setRulePrefix(rulePrefix);// 设置前缀
			sysCreateCodeRule.setRuleType(ruleType);// 设置编号类型
			sysCreateCodeRule.setRuleYear(year);// 设置年份
			sysCreateCodeRule.setRuleMonth(month);// 设置月份
			sysCreateCodeRule.setRuleDay(day);// 设置日期
			// 查询序号
			String order = mapper.selectOrderByPrefixAnDateTime(sysCreateCodeRule);
			// 拼出编号
			no = rulePrefix + year + month + day + order;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("各种标-生成编号规则临时:" + e.getMessage());
			}
		}
		return no;
	}

}
