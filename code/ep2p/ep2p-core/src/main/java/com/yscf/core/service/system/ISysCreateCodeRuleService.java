/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysCreateCodeRule;

/**
 * 
 * @ClassName : ISysCreateCodeRuleService
 * @Description : 编号生成规则业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月15日 上午10:27:14
 */
public interface ISysCreateCodeRuleService {

	/**
	 * 
	 * @Description : 查询编号生成规则信息,带分页
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @param info
	 *            分页对象
	 * @return 编号生成规则对象集合
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:33:35
	 */
	public PageList<SysCreateCodeRule> selectAllPage(SysCreateCodeRule sysCreateCodeRule, PageInfo info);

	/**
	 * 
	 * @Description : 编号生成规则
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
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:54:07
	 */
	public String generatNoRule(String rulePrefix, String ruleType, Integer seqLength, String UserId) throws FrameworkException;

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
	 * @Date : 2015年11月3日 下午5:59:28
	 */
	public String generatNoRuleNoDateTime(String rulePrefix, String ruleType, Integer seqLength, String UserId) throws FrameworkException;

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
	 * @return 临时编号
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:56:49
	 */
	public String generatNoRuleTem(String rulePrefix, String ruleType, Integer seqLength) throws FrameworkException;

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
	 * @return 临时编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:04:36
	 */
	public String generatNoRuleTemNoDateTime(String rulePrefix, String ruleType, Integer seqLength) throws FrameworkException;

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
	 * @return 标的编号
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月5日 下午2:39:56
	 */
	public String generatLabelNoRule(String rulePrefix, String ruleType, String UserId) throws FrameworkException;

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
	 * @Date : 2015年12月31日 上午11:31:29
	 */
	public String generatLabelNoRuleTem(String rulePrefix, String ruleType) throws FrameworkException;
}
