package com.yscf.core.dao.business;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.ptp.financial.BizReceiptTransferCenterModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferFrontModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;
@MapperScan("bizReceiptTransferMapper")
public interface BizReceiptTransferMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizReceiptTransfer record);

    int insertSelective(BizReceiptTransfer record);

    BizReceiptTransfer selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizReceiptTransfer record);

    int updateByPrimaryKey(BizReceiptTransfer record);
    
	/**
	 * 
	 * Description：<br> 
	 * 统计债权转让发布数量
	 * @author  JunJie.Liu
	 * @date    2015年11月2日
     * @version v1.0.0
     * @param date
     * @return
     */
	public Integer selectCountByCreateTime(@Param("date") String date,@Param("statusValue") String statusValue);

	/**
	 * 
	 * Description：<br> 
	 * 前台查询债权转让列表分页
	 * @author  JunJie.Liu
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param condition
	 * @return
	 */
	public List<BizReceiptTransferFrontModel> findTransferList(
			@Param("condition") BizReceiptTransferFrontModel condition,@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 前台查询债权转让列表总条数
	 * @author  JunJie.Liu
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param condition
	 * @return
	 */
	public Integer findTransferCount(@Param("condition") BizReceiptTransferFrontModel condition);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询个人中心债权转让列表
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param condition
	 * @param flag
	 * 			1:持有  2：转让中   3：已转让
	 * @param start
	 * @param end
	 * @return
	 */
	public List<BizReceiptTransferCenterModel> findTrasferCenterVos(@Param("condition") BizReceiptTransferCenterModel condition,@Param("flag") String flag,@Param("start")Integer start,@Param("end")Integer end);
	/**
	 * 
	 * Description：<br> 
	 * 查询个人中心债权转让列表总条数
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param condition
	 * @param flag
	 * 			1:持有  2：转让中   3：已转让
	 * @return
	 */
	public Integer findTrasferCenterVosCount(@Param("condition") BizReceiptTransferCenterModel condition,@Param("flag") String flag);

	/**
	 * 
	 * Description：<br> 
	 * 查询债权购买详情
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public BizReceiptTransferModel getByPid(String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据转让状态获取转让列表
	 * @author  JunJie.Liu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param bizReceiptplanStatus3
	 * @return
	 */
	List<BizReceiptTransfer> findList(@Param("status") String transferStatus);

	BizReceiptTransfer getCustomerIdByReceiptPlanId(@Param("rePid") String rePid);
	
	/**
	 * 
	 * Description：<br> 
	 * 获取债权转让列表【api】
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param yearRate
	 * @param transferPrice
	 * @param orderType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<BizReceiptTransferFrontModel> findTransferListByApi(
			@Param("type") String type,
			@Param("desc") boolean desc, 
			@Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 债权转让列表条数【api】
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @return
	 */
	public Integer findTransferListByApiCount();
	
	
	/**
	 * 
	 * Description：<br> 
	 * 后台列表查询
	 * @author  JunJie.Liu
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param bizReceiptTransferVo
	 * @param info
	 * @return
	 */
	public PageList<BizReceiptTransfer> selectAllPage(
			BizReceiptTransfer bizReceiptTransferVo, PageInfo info);

	/**
	 * 
	 * Description：<br> 
	 * 后台列表 查询，条数统计
	 * @author  JunJie.Liu
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param bizReceiptTransferVo
	 * @return
	 */
	public BizReceiptTransfer sumBizReceiptTransferVoWhere(
			BizReceiptTransfer bizReceiptTransferVo);

	PageList<BizReceiptTransferModel> selectAllPage(
	@Param("map") BizReceiptTransferModel bizBorrow, PageInfo info);


	List<BizReceiptTransferModel> selectBizReceiptTransferVOEom(
		HashMap<String, Object> maps);
	
	
	BizReceiptTransferModel sumBizReceiptTransferVoWhere(
	@Param("map") BizReceiptTransferModel bizReceiptTransferVo);
	
	
}