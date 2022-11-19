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
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizBorrowTimeVO;
import com.yscf.core.model.ptp.financial.BizBorrowStatModel;
import com.yscf.core.model.pub.PubFile;

/**
 * Description：<br>
 * 数据字典业务类服务接口
 * 
 * @author Simon.Hoo
 * @date 2015年9月6日
 * @version v1.0.0
 */
public interface IBizBorrowService {

	/**
	 * 
	 * Description：<br>
	 * 分页查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @param info
	 * @return
	 */
	public PageList<BizBorrow> selectAllPage(BizBorrow bizBorrow, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 借款数据查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param dis
	 * @return
	 */
	public List<BizBorrow> selectAll(BizBorrow bizBorrow);

	/**
	 * 
	 * Description：<br>
	 * 批量更新状态
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月9日
	 * @version v1.0.0
	 * @param map
	 *            status,pids
	 * @return 成功更新数据条数
	 */
	public int updateStatusBatch(BizBorrow bizBorrow);

	/**
	 * 
	 * Description：<br>
	 * 动态更新数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @return
	 */
	public int updateByPrimaryKeySelective(BizBorrow bizBorrow);

	/**
	 * 
	 * Description：<br>
	 * 保存文件信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 */
	public void saveFileInfo(BizBorrowFileRel fileRel, PubFile file);

	/**
	 * 
	 * Description：<br>
	 * 数据插入
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @return
	 * @throws Exception
	 */
	public int insert(BizBorrowInfoVO bizBorrow) throws Exception;

	/**
	 * 
	 * Description：<br>
	 * 通过ID得到借款信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param pid
	 */
	public BizBorrow getBizBorrowById(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据id删除标的
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public int deleteByPrimaryKey(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据时间查询新手标的发布数量
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Integer getBorrowCountByNewStandard(String startTime, String endTime);

	/**
	 * 
	 * Description：<br>
	 * 根据时间查询体验标的发布数量
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Integer getBorrowCountByExperienceStandard(String startTime, String endTime);

	/**
	 * 
	 * Description：<br>
	 * 查询借款项目类型统计占比
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @param borrowStatus4
	 * @return
	 */
	public List<BizBorrowStatModel> selectStatByBorrowType(String borrowStatus);

	/**
	 * 
	 * Description：<br>
	 * 统计借款数量占比
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月9日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowStatModel> findByBorrowCountData();

	/**
	 * 
	 * Description：<br>
	 * 根据借款期限得到借款项目占比情况
	 * 
	 * @author Jie.Zou
	 * @date 2015年11月11日
	 * @version v1.0.0
	 * @return
	 */
	public List<BizBorrowTimeVO> getProportionByBorrowTime();

	/**
	 * 
	 * Description：<br>
	 * 根据借款类型获取最新的标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月2日
	 * @version v1.0.0
	 * @return
	 */
	public BizBorrow getLastBorrowByBorrowType(String borrowType);

	/**
	 * 
	 * Description：<br>
	 * 更新标的，并且创建还款计划,针对新手标，体验标（此类标按日计息）
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param bizBorrow
	 */
	public int updateByPrimaryKeySelectiveAndCreateRepaymentPlan(BizBorrow bizBorrow);

	/**
	 * 
	 * Description：<br>
	 * 投资新手标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param amount
	 * @param userId
	 */
	public void invest(BizBorrow bizBorrow, BigDecimal amount, String userId, String investmentType);

	/**
	 * 
	 * Description：<br>
	 * 根据借款类型得到最新的标（后台进行格式化）
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月22日
	 * @version v1.0.0
	 * @param borrowType
	 *            借款类型
	 * @return
	 */
	public BizBorrow getLastBorrowDByBorrowType(String borrowType);

	/**
	 * 
	 * Description：<br>
	 * 投资体验金
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月22日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param amount
	 *            投资体验金金额
	 * @param userId
	 *            投资人
	 * @param investmentType
	 *            投资客户端
	 */
	public void investExperience(BizBorrow bizBorrow, BigDecimal amount, String userId, String investmentType, String[] epces);

	/**
	 * 
	 * Description：<br>
	 * 预发布的标的 到预期时间发布
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月24日
	 * @version v1.0.0
	 */
	public void executeTimer();

	/**
	 * 
	 * Description：<br>
	 * 使所有借款成功的任务结束招标进入满标（还款中）
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月26日
	 * @version v1.0.0
	 */
	void checkBorrowSuccess();

	/**
	 * 
	 * Description：<br>
	 * 检查借款是否到期
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param time
	 */
	void checkBorrowExpire(Date time);

	/**
	 * 
	 * Description：<br>
	 * 格式化借款信息（例如借款金额，借款利率等）
	 * 
	 * @author Jie.Zou
	 * @date 2016年1月6日
	 * @version v1.0.0
	 * @param bizBorrow
	 */
	BizBorrow formatBorrow(BizBorrow bizBorrow);

	/**
	 * 
	 * @Description : API>>已还款列表
	 * @param userId
	 *            用户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 客户已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午9:51:24
	 */
	public List<BizBorrow> selectAlreadyRepaymentAPI(String userId, Integer pageIndex, Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询未结清的借款数据条数
	 * @author  Yu.Zhang
	 * @date    2016年2月18日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public int getNoSettleCount(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 借款撤销,借款信息修改(如:借款状态),投资客户资金回流
	 * @author  Jie.Zou
	 * @date    2016年2月25日
	 * @version v1.0.0
	 * @param borrow
	 */
	public void revokeBorrow(BizBorrow borrow);

}
