package com.yscf.core.dao.financial;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizEnsureMoney;
/**
 * Description：<br> 
 * 备用金
 * @author  jenkin.yu
 * @date    2015年10月21日
 * @version v1.0.0
 */
@MapperScan("bizEnsureMoneyMapper")
public interface BizEnsureMoneyMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizEnsureMoney record);

    int insertSelective(BizEnsureMoney record);

    BizEnsureMoney selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizEnsureMoney record);

    int updateByPrimaryKey(BizEnsureMoney record);

	List<BizEnsureMoney> selectAll();
	
	/**
	 * 
	 * Description：<br> 
	 * 增加备付金
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param beiImFee
	 */
	void updateToAddBlance(@Param("amount") BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 减少备付金
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param beiImFee
	 */
	int updateToSubBlance(@Param("amount") BigDecimal amount);

	/**
	 * 
	 * Description：<br> 
	 * 获取备付金
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	BigDecimal getMoney();
}