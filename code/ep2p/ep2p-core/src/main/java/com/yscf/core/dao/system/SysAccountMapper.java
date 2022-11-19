package com.yscf.core.dao.system;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysAccount;

/**
 * Description：<br> 
 * 系统账户记录系统总金额   数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年1月4日
 * @version v1.0.0
 */
@MapperScan("sysAccountMapper")
public interface SysAccountMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysAccount record);

    int insertSelective(SysAccount record);

    SysAccount selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysAccount record);

    int updateByPrimaryKey(SysAccount record);
    
    /**
     * 
     * Description：<br> 
     * 增加系统金额
     * @author  JunJie.Liu
     * @date    2015年12月17日
     * @version v1.0.0
     * @param amount
     */
	void updateToAddBlance(@Param("amount") BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 减系统金额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 */
	int updateToSubBlance(@Param("amount") BigDecimal amount);

	List<SysAccount> selectAll();
}