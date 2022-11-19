package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysIpPvCount;

/**
 * 
 * Description：<br> 
 * ip pv 统计
 * @author  Yu.Zhang
 * @date    2015年10月19日
 * @version v1.0.0
 */
@MapperScan("sysIpPvCountMapper")
public interface SysIpPvCountMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysIpPvCount record);

    int insertSelective(SysIpPvCount record);

    SysIpPvCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysIpPvCount record);

    int updateByPrimaryKey(SysIpPvCount record);
    
    void executeIpPvCount(SysIpPvCount record);

	List<SysIpPvCount> selectAll(SysIpPvCount record);

	List<SysIpPvCount> selectNearlySevenData(@Param("map")Map<String, Object> map);
}