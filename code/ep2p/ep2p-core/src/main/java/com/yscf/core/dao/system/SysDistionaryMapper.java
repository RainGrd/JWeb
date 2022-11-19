package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysDistionary;

/**
 * 
 * Description：数据字典 mapper 接口类
 * TODO
 * @author  Yu.Zhang
 * @date    2015年9月7日
 * @version v1.0.0
 */

@MapperScan("sysDistionaryMapper")
public interface SysDistionaryMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysDistionary record);

    int insertSelective(SysDistionary record);

    SysDistionary selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysDistionary record);

    int updateByPrimaryKey(SysDistionary record);
    
    /**
     * 
     * Description：<br> 
     * 分页查询
     * @author  Yu.Zhang
     * @date    2015年9月11日
     * @version v1.0.0
     * @param sysDistionary
     * @param info
     */
    PageList<SysDistionary> selectAllPage(@Param("map")SysDistionary sysDistionary, PageInfo info);
    
    /**
     * 
     * Description：<br> 
     * 查询
     * @author  Yu.Zhang
     * @date    2015年9月11日
     * @version v1.0.0
     * @param sysDistionary
     * @return
     */
    List<SysDistionary> selectAll(SysDistionary sysDistionary);
    
    /**
	 * 
	 * Description：<br> 
	 * 批量更新状态
	 * @author  Yu.Zhang
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param map status,pids 
	 * @return 成功更新数据条数
	 */
	int updateStatusBatch(@Param("map") Map<String,Object> map );

	/**
	 * 
	 * Description：<br> 
	 * 验证数据字典code是否存在
	 * @author  Yu.Zhang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param sysDistionary
	 */
	List<SysDistionary> validateCode(SysDistionary sysDistionary);

	
}