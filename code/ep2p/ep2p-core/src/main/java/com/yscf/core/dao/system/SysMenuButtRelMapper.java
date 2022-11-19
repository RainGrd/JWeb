package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysMenuButtRel;

@MapperScan("sysMenuButtRelMapper")
public interface SysMenuButtRelMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysMenuButtRel record);

    int insertSelective(SysMenuButtRel record);

    SysMenuButtRel selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysMenuButtRel record);

    int updateByPrimaryKey(SysMenuButtRel record);
    
    int deleteButtonAssign(Map<String,Object> map);
    
    /**
     * Description：<br> 
     * 批量新增
     * @author  JingYu.Dai
     * @date    2015年11月3日
     * @version v1.0.0
     * @param list 
     * @return int
     */
    int insertSelectiveBatch(List<SysMenuButtRel> list);
    
    /**
     * Description：<br> 
     * 根据菜单ID删除
     * @author  JingYu.Dai
     * @date    2015年11月3日
     * @version v1.0.0
     * @param menuId
     * @return
     */
    int deleteByMenuId(String menuId);
    
}