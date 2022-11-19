package com.yscf.core.dao.pub;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.pub.Contract;

@MapperScan("contractMapper")
public interface ContractMapper extends IBaseDao<BaseEntity, String> {
    int insert(Contract record);

    int insertSelective(Contract record);
    
    /**
     * 
     * Description：<br> 
     * 根据用户id，业务id,合同类型，获取合同
     * @author  JunJie.Liu
     * @date    2016年3月9日
     * @version v1.0.0
     * @param userId
     * @param borrowId
     * @param type
     * @return
     */
	public Contract getByCustIdAndBusinessId(@Param("userId") String userId, @Param("borrowId") String borrowId,
			@Param("type") String type);
}