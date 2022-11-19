package com.yscf.core.dao.user;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.user.CustomerDto;

/**
 * Description：
 *  客户vo数据接口层
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@MapperScan("cusTomerDtoMapper")
public interface CusTomerDtoMapper extends IBaseDao<BaseEntity, String> {
	
	CustomerDto getCustomerDtoByPid(String pid);
}