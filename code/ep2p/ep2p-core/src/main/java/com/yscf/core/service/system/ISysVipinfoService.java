/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月11日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysVipCondRel;
import com.yscf.core.model.system.SysVipinfo;

/**
 * 
 * @ClassName : ISysVipinfoService
 * @Description : VIP信息业务层接口
 * @Author : Qing.Cai
 * @Date : 2015年11月3日 上午10:10:10
 */
public interface ISysVipinfoService {

	/**
	 * 
	 * @Description : 查询所有VIP信息,带分页
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @param info
	 *            分页对象
	 * @return VIP列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:09:07
	 */
	public PageList<SysVipinfo> selectAllPage(SysVipinfo sysVipinfo, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            VIP信息ID数组(1,2,3)
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:09:14
	 */
	public void deleteBatch(String pids) throws FrameworkException;
	
	/**
	 * 
	 * @Description : VIP信息编辑操作
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:14:24
	 */
	public void sysVipinfoEdit(SysVipinfo sysVipinfo) throws FrameworkException;
	
	/**
	 * 
	 * @Description : 根据vipId查询条件数据
	 * @param vipId
	 *            VIP主键
	 * @return 条件List
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午5:02:08
	 */
	List<SysVipCondRel> seleCondRelByVipId(String vipId);

	/**
	 * 
	 * Description：<br> 
	 * 根据pid查询vip对象
	 * @author  JunJie.Liu
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param vipinfoId
	 * @return
	 */
	public SysVipinfo getById(String vipinfoId);
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查询vip等级
	 * @author  heng.wang
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	List<SysVipinfo> selectVipLevelById(String userId);

	/**
	 * 
	 * Description：<br> 
	 * 根据vip等级查询vip对象
	 * @author  JunJie.Liu
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param vipinfoId
	 * @return
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
	public BigDecimal selectFeeByUserId(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询vipInfo表的pid
	 * @author  wangheng
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @return
	 */
	public SysVipinfo selectVipInfoPid();
}
