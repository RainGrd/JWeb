package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizFundtally;

/**
 * Description：业务--系统资金流水	 数据访问层接口
 * @author  JingYu.Dai
 * @date    2015年10月28日
 * @version v1.0.0
 */
@MapperScan("bizFundtallyMapper")
public interface BizFundtallyMapper extends IBaseDao<BaseEntity, String> {
	
	/**
     * Description：<br>
     * 资金流水（通用）,条件查询,带分页功能的
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizFundtally）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizFundtally> 
     */
    List<BizFundtally> selectBizFundtallyPage(BizFundtally bizFundtally);
    
    /**
     * Description：<br>
     * 资金流水（通用）,条件查询  	统计总金额
     * @author  JingYu.Dai
     * @date    2015年10月10日
     * @version v1.0.0
     * @param BizFundtally
     * @return  Map<String,BigDecimal> 总金额集合
     */
    Map<String,BigDecimal> selectSumAmountSelective(BizFundtally bizFundtally);
    
    /**
     * Description：<br> 
     * 资金流水,条件查询  统计总记录条数
     * @author  JingYu.Dai
     * @date    2015年10月28日
     * @version v1.0.0
     * @param bizFundtally
     * @return
     */
    int selectBizFundtallyPageTotal(BizFundtally bizFundtally);
    
    /**
     * 
     * Description：<br> 
     * 批量插入系统资金流水
     * @author  Jie.Zou
     * @date    2016年1月14日
     * @version v1.0.0
     * @param bizFundtallys
     * @return
     */
    int insertBizFundtallyByUserIds(Map<String, Object> bizFundtallys);
	
	
    int deleteByPrimaryKey(String pid);

    int insert(BizFundtally record);

    int insertSelective(BizFundtally record);

    BizFundtally selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizFundtally record);

    int updateByPrimaryKey(BizFundtally record);

	List<BizFundtally> selectBizFundtallyEom(HashMap<String, Object> map);
}